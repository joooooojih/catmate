<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <div class="container bg-white" id="container" >
      <div class="row" id="area">
        <div class="col-md-12">
          <c:set var="i" value="${0 }"/>
          <c:forEach var="area" items="${area_text }">
            <c:choose>
              <c:when test="${i == 0 }">
                <c:set var="area_class_text" value="dark"/>
              </c:when>
              <c:otherwise>
                <c:set var="area_class_text" value="secondary"/>
              </c:otherwise>
            </c:choose>
            
            <span><a href="${pageContext.request.contextPath }/reserve/search?area=${area}" class="btn btn-outline-${area_class_text }">${area }&nbsp;&nbsp;${area_count[i] }</a></span>
            
            <c:set var="i" value="${i + 1 }"/>
          </c:forEach>
        </div>
      </div>
    </div>
    <br>