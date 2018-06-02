<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="./../set/setLink/link.jsp" flush="false"/>
<title>메시지</title>
  <script type="text/javascript">
    $(document).ready(function() {
    	var page_num = 1;
    	
    	function msg_list() {
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
        	
          	var to_user_profile = responseData.to_user_profile;
           	var from_user_profile = responseData.from_user_profile;
          	var set_user;
          	for(var i = 0; i < msg_search.length; i++) {
          		if(msg_search[i].to_user_email == to_user_profile.user_email) {
          			set_user = to_user_profile  // 나
           		} else {
           			set_user = from_user_profile  // 상대
          		}
          		$("#texts").prepend(set_user.user_name + "&nbsp;&nbsp;&nbsp;" + msg_search[i].msg_content + "<br>");  // 뿌리기만 변경
          	}
          }
      	});
    	}
    	
    	// 스크롤 올리면  ++page_num 후 list 부르기
    	// 창 꾸미기 끝!
    	
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
              var msg_new = responseData.msg_new;
                
              var to_user_profile = responseData.to_user_profile;
              var from_user_profile = responseData.from_user_profile;
              var set_user;
              if(msg_new.to_user_email == to_user_profile.user_email) {
                set_user = to_user_profile  // 나
              } else {
                set_user = from_user_profile  // 상대
              }
              $("#texts").append(set_user.user_name + "&nbsp;&nbsp;&nbsp;" + msg_new.msg_content + "<br>");  // 뿌리기만 변경
            }
          });
        }
        function onOpen(event) {
        	msg_list();
        }
        function onError(event) {
          alert(event.data);
        }
        $("#send").click(function() {  // 메세지 보내기
          if($("#inputMessage").val() != "") {
              webSocket.send("${from_user_profile.user_email}:" + $("#inputMessage").val());
              $("#inputMessage").val("");
          }
        });
    });
    
  </script>
</head>
<body>


    <!-- 여기도 바꿔야됨 -->
    <fieldset>
        <p id="texts">
        </p>
        <br/>
        <input id="inputMessage" type="text"/>
        <input type="submit" value="send" id="send"/>
    </fieldset>
    
    
</body>
    
</html>