<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <jsp:include page="./../set/setLink/link.jsp" flush="false"/>
  <link href="${pageContext.request.contextPath }/resources/css/msg.css" rel="stylesheet"/>
<title>${from_user_profile.user_name}</title>
  <script type="text/javascript">
    $(document).ready(function() {
    	var page_num = 1;
    	var before_scroll_height;
    	var msg_content;
    	var msg_date
    	
    	function from_msg(user_profile, msg_search, msg_dates, i) {
    		var msg_read;
    		var msg_div = "";
    		if(i == -1) {  // 방금 추가한 메세지
    			msg_content = msg_search.msg_content;
    			msg_date = msg_dates;
    			if(msg_search.msg_read == 'X') {
    	      msg_read = 1;
          }
    		} else {
    			msg_content = msg_search[i].msg_content;
    	    msg_date = msg_dates[i];
    	    if(msg_search[i].msg_read == 'X') {
            msg_read = 1;
          }
    		}
    		
    		msg_div += 
    	  '<div class="row">'+
          '<div class="col-md-8">'+
            '<dl>'+
              '<dt>' + 
                '<img class="rounded-circle" id="msg_user_img" src="${pageContext.request.contextPath}/resources/img/user_img/' + user_profile.user_photo + '">'+
                '&nbsp;&nbsp;' + user_profile.user_name + '&nbsp;&nbsp;' + 
              '</dt>'+
              '<dd id="from_dd">'+
                '<span class="bg-white" id="msg_text">' + msg_content + '</span>' + 
                '&nbsp;&nbsp;<small>' + msg_read + '</small><br>' + 
                '<small>' + msg_date + '</small>'+
              '</dd>'+
            '</dl>'+
          '</div>'+
        '</div>';
        
        if(i == -1) {
          $("#msg_texts").append(
              msg_div
            );
        } else {
          $("#msg_texts").prepend(
              msg_div
          );
        }
    	}
    	
    	function to_msg(msg_search, msg_dates, i) {
    		var msg_read;
    		var msg_div = "";
        if(i == -1) {  // 방금 추가한 메세지
            msg_content = msg_search.msg_content;
            msg_date = msg_dates;
            if(msg_search.msg_read == 'X') {
            	msg_read = 1;
            }
            
        } else {
          msg_content = msg_search[i].msg_content;
          msg_date = msg_dates[i];
          if(msg_search[i].msg_read == 'X') {
            msg_read = 1;
          }
        }
        msg_div +=
        	'<div class="row text-right">'+
            '<div class="col-md-4"></div>'+
            '<div class="col-md-8">'+
              '<small>' + msg_read + '</small>&nbsp;&nbsp;' + 
              '<span class="bg-warning" id="msg_text">' + msg_content + '</span><br>'+
              '<small>' + msg_date + '</small>'+
            '</div>'+
          '</div><br>';
          if(i == -1) {
        	  $("#msg_texts").append(
                msg_div
            );
          } else {
        	  $("#msg_texts").prepend(
                msg_div
            );
          }
    		
    	}
    	
    	function msg_list() {  // 예전 메세지 데이터
    	  $.ajax({
    		  type: "get",
    		  url: "${pageContext.request.contextPath}/mypage/msg_list",
    		  data: {
    			  from_user_email: "${from_user_profile.user_email}",
    			  page_num: page_num
    		  },
    		  contentType: "application/x-www-form-urlencoded; charset=UTF-8",
          dataType: "json",
          success: function(responseData, status, xhr) {
          	var msg_search = responseData.msgList;
          	var msg_dates = responseData.msg_date;
        	
          	var to_user_profile = responseData.to_user_profile;
           	var from_user_profile = responseData.from_user_profile;
          	var set_user;
          	for(var i = 0; i < msg_search.length; i++) {
          		if(msg_search[i].to_user_email == from_user_profile.user_email) {
          			from_msg(from_user_profile, msg_search, msg_dates, i);
           		} else {
           			to_msg(msg_search, msg_dates, i);
          		}
          	}
          	if(page_num == 1) {
          		$(window).scrollTop($(document).height());  // 처음에 젤 밑을 보도록
          	} else {
          		$(window).scrollTop($(document).height() - before_scroll_height);
          	}
          	before_scroll_height = $(document).height();
          }
      	});
    	}
    	
    	// 창 꾸미기!
    	
    	var webSocket = new WebSocket('ws://localhost:8080/catmate/msg');
      webSocket.onerror = function(event) {
        onError(event)
      };
      webSocket.onopen = function(event) {
        onOpen(event)
      };
      webSocket.onmessage = function(event) {
        onMessage(event)
      };
      function onMessage(event) {
      	$.ajax({
          type: "get",
          url: "${pageContext.request.contextPath}/mypage/msg_new",
          data: {
            from_user_email: "${from_user_profile.user_email}"
          },
          contentType: "application/x-www-form-urlencoded; charset=UTF-8",
          dataType: "json",
          success: function(responseData, status, xhr) {
            var msg_search = responseData.msg_search;
            var msg_dates = responseData.msg_date;
              
            var to_user_profile = responseData.to_user_profile;
            var from_user_profile = responseData.from_user_profile;
            var set_user;
            if(msg_search.to_user_email == from_user_profile.user_email) {
            	from_msg(from_user_profile, msg_search, msg_dates, -1);
            } else {
            	to_msg(msg_search, msg_dates, -1);
            }
            $(window).scrollTop($(document).height());
          }
        });
      }
      function onOpen(event) {
      	msg_list();
      	$("#inputMessage").focus();
      }
      function onError(event) {
        alert(event.data);
      }
      $("#send").click(function() {  // 메세지 보내기
        if($("#inputMessage").val() != "") {
            webSocket.send("${from_user_profile.user_email}:" + $("#inputMessage").val());
            $("#inputMessage").val("");
            $("#inputMessage").focus();
        }
      });
      
      $('#inputMessage').keypress(function(event){  // 엔터 처리
    	  if(event.which == 13) {
    	     $('#send').click();
    	 }
    	});
      
      $(window).scroll(function() {  // 무한 스크롤
        if($(window).scrollTop() == 0) {
          ++page_num;
          msg_list();
        }
      });
    });
  </script>
</head>
<body class="bg-light">
  <div class="row" id="msg_texts_row">
    <div class="col-md-12">
      <span id="msg_texts" class="bg-light"></span>
    </div>
  </div>
  <footer>
    <div class="row">
      <div class="col-md-10">
        <input id="inputMessage" type="text" class="form-control" maxlength="1000" placeholder="메세지"/>
      </div>
      <div class="col-md-2">
        <input type="button" value="전송" class="form-control bg-warning text-white" id="send"/>
      </div>
    </div>
  </footer>
</body>
    
</html>