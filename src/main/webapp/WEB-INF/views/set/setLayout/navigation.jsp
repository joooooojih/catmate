<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <!-- Navigation -->
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
              <a class="nav-link js-scroll-trigger" href="${pageContext.request.contextPath }/member/logout">로그아웃</a>
            </li>
          </c:if>
        </ul>
      </div>
    </div>
  </nav>
  <c:if test="${scrren ne 'main' }">
    <br><br>
  </c:if>