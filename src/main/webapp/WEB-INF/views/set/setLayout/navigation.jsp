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
          <li class="nav-item">
            <a class="nav-link js-scroll-trigger" href="${pageContext.request.contextPath }/member/login">로그인</a>
          </li>
          <li class="nav-item">
            <a class="nav-link js-scroll-trigger" href="${pageContext.request.contextPath }/member/signup">회원가입</a>
          </li>
        </ul>
      </div>
    </div>
  </nav>
  <c:if test="${scrren ne 'main' }">
    <br><br>
  </c:if>