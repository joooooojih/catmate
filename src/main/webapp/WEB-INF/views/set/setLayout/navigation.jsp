<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <!-- Navigation -->
  <script>
    function msg_window_open(user_email) {
    	var x = 600;
    	var y = 600;
    	var msgX = (window.screen.width / 2) - (x / 2);
    	var msgY= (window.screen.height /2) - (y / 2);
      window.open("${pageContext.request.contextPath}/mypage/msg?user_email="+ user_email +"","msg_window","top=" + msgY + ", left=" + msgX + ", width=" + x + ", height=" + y + ", directories=0, location=0, menubar=0, resizable=0, scrollbars=0, status=0, toolbar=0");
    }
    $(document).ready(function() {
    	
    	function msg_user_list() {
    		$.ajax({
    	    type: "get",
    	    url: "${pageContext.request.contextPath }/mypage/msg_user_list",
    	    dataType: "json",
    	    contentType: "application/x-www-form-urlencoded; charset=UTF-8",
    	    success: function(responseData, status, xhr) {
    	      var user_profileList = responseData.user_profileList;
    	      var msgList = responseData.msgList;
    	      
    	      $("#modal-body").text(""); // 초기화
    	      var user_profile_text = "";
    	      var msg_content_color = "";
    	      for(var i = 0; i < user_profileList.length; i++) {
    	    	  if(msgList[i].msg_read == 'X' && msgList[i].to_user_email == user_profileList[i].user_email) {
    	    		  msg_content_color = "text-primary";
              } else {
            	  msg_content_color = "text-dark";
              }
    	    	  
    	        user_profile_text += 
    	        	'<tr>'+
    	            '<td id="user_name">'+
    	              '<img class="rounded-circle" id="msg_user_img" src="${pageContext.request.contextPath }/resources/img/user_img/'+ user_profileList[i].user_photo +'"><br>'+
    	              user_profileList[i].user_name + 
    	              '</a>'+
    	            '</td>'+
    	            '<td>'+
    	              '<a href="#" class="' + msg_content_color + '" onclick=javascript:msg_window_open("'+user_profileList[i].user_email+'")>'+
    	              msgList[i].msg_content+
    	              '</a>'+
    	            '</td>'+
    	          '</tr>'
    	      }
    	      $("#modal-body").append(
    	            '<table class="table table-hover text-left">'+
    	                user_profile_text+
    	            '</table>'
    	      );
    	    },
    	  });
    	}
    	
    	$("#msg_user").unbind("click").bind("click", function() {  // 메시지 클릭 시 셋팅 
    		msg_user_list();
    	});
    	
    });
  </script>
  <c:choose>
    <c:when test="${scrren eq 'main' }">
      <c:set var="navClass" value="navbar navbar-expand-lg navbar-light fixed-top"/>
    </c:when>
    <c:otherwise>
      <c:set var="navClass" value="navbar navbar-expand-lg bg-warning fixed-top"/>
    </c:otherwise>
  </c:choose>
  <nav class="${navClass }" id="mainNav">
    <div class="container">
      <a class="navbar-brand js-scroll-trigger" href="${pageContext.request.contextPath }/">CatMate</a>
      <div class="collapse navbar-collapse" id="navbarResponsive">
        <ul class="navbar-nav ml-auto">
        <c:choose>
          <c:when test="${!empty user_profile}">
            <c:set var="item_one_href" value="${pageContext.request.contextPath }/reserve/search"/>
            <c:set var="item_one_title" value="펫시터 집에 맡기기"/>
            <c:set var="item_two_href" value="${pageContext.request.contextPath }/sregister/pet_sitter01"/>
            <c:set var="item_two_title" value="펫시터 등록하기"/>
          </c:when>
          <c:otherwise>
            <c:set var="item_one_href" value="${pageContext.request.contextPath }/member/login"/>
            <c:set var="item_one_title" value="로그인"/>
            <c:set var="item_two_href" value="${pageContext.request.contextPath }/member/signup"/>
            <c:set var="item_two_title" value="회원가입"/>
          </c:otherwise>
        </c:choose>
          <li class="nav-item">
            <a class="nav-link js-scroll-trigger" href="${item_one_href }">${item_one_title }</a>
          </li>
          <li class="nav-item">
            <a class="nav-link js-scroll-trigger" href="${item_two_href }">${item_two_title }</a>
          </li>
          <c:if test="${!empty user_profile }">
          <li class="nav-item">
            <div class="btn-group">
              <a class="nav-link js-scroll-trigger text-muted dropdown-toggle" data-toggle="dropdown"> ${user_profile.user_name } </a>
              <div class="dropdown-menu">
                <a class="dropdown-item" data-toggle="modal" href="#msg_modal" id="msg_user">메시지</a>
                <div class="dropdown-divider"></div>
                <a class="dropdown-item" href="${pageContext.request.contextPath }/mypage/wish_list">위시리스트</a>
                <div class="dropdown-divider"></div>
                <a class="dropdown-item" href="${pageContext.request.contextPath }/mypage/reservation_history/to_sitter">예약 내역</a>
                <div class="dropdown-divider"></div>
                <a class="dropdown-item" href="${pageContext.request.contextPath }/mypage/pet_sitter_house_update">펫시터 수정</a>
                <div class="dropdown-divider"></div>
                <a class="dropdown-item" href="${pageContext.request.contextPath }/mypage/pet_profile/pet_profile">강아지 정보 관리</a>
                <div class="dropdown-divider"></div>
                <a class="dropdown-item" href="${pageContext.request.contextPath }/mypage/account/account_info">개인정보변경</a>
                <div class="dropdown-divider"></div>
                <a class="dropdown-item" href="${pageContext.request.contextPath }/member/logout">로그아웃</a>
              </div>
            </div>
          </li>
        </c:if>
        </ul>
      </div>
    </div>
  </nav>
  
  <!-- 메시지 모달창 -->
  <div class="modal fade" id="msg_modal" role="dialog">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header bg-primary text-white">
          <h4 class="modal-title">메시지 목록</h4>
        </div>
        <div class="modal-body" id="modal-body">
        
        </div>
      </div>
    </div>
  </div>
  <c:if test="${scrren ne 'main' }">
    <br><br>
  </c:if>