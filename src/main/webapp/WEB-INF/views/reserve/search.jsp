<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>캣메이트 펫시터 맡기기 검색</title>
  <jsp:include page="../set/setLink/link.jsp" flush="false"/>
  <link href="${pageContext.request.contextPath }/resources/reserve/css/search.css" rel="stylesheet"/>
</head>
<body draggable="true">
  <jsp:include page="../set/setLayout/navigation.jsp" flush="false"/>
  <section class="py-5 bg-light">
    <jsp:include page="./common/area.jsp" flush="false"/>
    <div class="container" id="container"><br>
      <div class="row">
        <div class="col-md-12">
          <div class="btn-group">
            <select class="btn btn-light">
              <option>서비스</option>
              <option>24시간 돌봄</option>
              <option>데이케어</option>
            </select>&nbsp;&nbsp;&nbsp;
            <input type="button" class="btn btn-light" value="예약일">&nbsp;&nbsp;&nbsp;
            <select class="btn btn-light">
              <option>모든연령</option>
              <option>강아지 (1살 이하)</option>
              <option>성견 (2~6살)</option>
              <option>노견 (7살 이상)</option>
            </select>&nbsp;&nbsp;&nbsp;
            <select class="btn btn-light">
              <option>모든크기</option>
              <option>소형견 (0 ~ 4.9kg)</option>
              <option>중형견 (5 ~ 14.9kg)</option>
              <option>대형견 (15kg이상)</option>
            </select>&nbsp;&nbsp;&nbsp;
          </div>
        </div>
      </div>
      <br><br>
      <div class="row">
        <div class="col-md-12">
          <p>&nbsp;원하는 태그를 모두 선택해 주세요!</p>
          <div class="btn-group" data-toggle="buttons">
            <c:forEach var="tags" items="${tags }">
              <label class="btn btn-warning text-light">${tags }
                <input type="checkbox" autocomplete="off">
              </label>&nbsp;
            </c:forEach>
          </div>
        </div>
      </div>
      <br><br>
    </div>
  </section>
  
  <section class="py-5 bg-white">
    <div class="container">
    
    
      <div class="row">
        <div class="col-md-4">
          <div id="carousel" class="carousel slide" data-ride="carousel">
            <div class="carousel-inner">
            <c:set var="j" value="1"/>
            <c:forEach var="i" begin="1" end="5">
            
              <c:choose>
                <c:when test="${j == 1 }">
                  <c:set var="carouselClass" value="carousel-item active"/>
                </c:when>
                <c:otherwise>
                  <c:set var="carouselClass" value="carousel-item"/>
                </c:otherwise>
              </c:choose>
              
              <div class="${carouselClass }">
                <img class="d-block img-fluid w-100" id="imgHeight" src="${pageContext.request.contextPath }/resources/pet_sitter/img/test${i }.png">
                <div class="carousel-caption">
                  <h5>
                    <img src="${pageContext.request.contextPath }/resources/icon/addressIcon.png">
                                   부산시 서구 충무대로
                  </h5>
                </div>
              </div>
              
              <c:set var="j" value="${j + 1 }"/>
              </c:forEach>
              
            </div>
            <a class="carousel-control-prev" href="#carousel" role="button" data-slide="prev">
              <span class="carousel-control-prev-icon" aria-hidden="true"></span>
              <span class="sr-only">Previous</span>
            </a>
            <a class="carousel-control-next" href="#carousel" role="button" data-slide="next">
              <span class="carousel-control-next-icon" aria-hidden="true"></span>
              <span class="sr-only">Next</span>
            </a>
          </div>
        </div>
        <div class="col-md-7" id="bodyRow">
          <h3 class="text-primary pt-2">
          <a href="${pageContext.request.contextPath }/reserve/sitter_detail">
            <b>강아지를 17년 키워본 펫시터입니다.</b>
          </a>
            <i class="d-block fa  fa-heart-o fa-fw pull-right"></i>
          </h3>
          <p id="subtitle">
           <b>
            <img class="rounded-circle" id="subtitleImg" src="${pageContext.request.contextPath }/resources/icon/icon-dog.png">&nbsp;&nbsp;반려견 1마리
           </b>
          </p>
          <hr>
          <p>
          <button class="btn btn-warning text-light">아파트</button>&nbsp;&nbsp;
          <button class="btn btn-warning text-light">마당</button>
          </p>
          <div class="row">
            <div class="col-md-12" id="price">
              <span class="text-secondary">day care / <b>15,000원</b></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="text-warning">
              1박 / <b>30,000원</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
            </div>
          </div><br>
          <div class="row" id="bottomRow">
            <div class="col-md-4 text-secondary" id="searchName">
              <div class="carousel-inner">
                <img class="rounded-circle" id="subtitleImg" src="${pageContext.request.contextPath }/resources/pet_sitter/img/test1.png">
                <b>조지헌</b>
              </div>
            </div>
            <div class="col-md-4 text-secondary" id="petGrade">
              <img id="subtitleImg" src="${pageContext.request.contextPath }/resources/icon/icon-star.png">&nbsp;
              <b>펫시터 평점</b>&nbsp;&nbsp;&nbsp;0점
            </div>
            <div class="col-md-4 text-secondary" id="reviewsGrade">
             <img id="subtitleImg" src="${pageContext.request.contextPath }/resources/icon/icon-review.png">
              <b>후기 평점</b>&nbsp;&nbsp;&nbsp;0점
            </div>
          </div>
        </div>
      </div>
      <br><br>
      
      
      
      
      
      
    </div>
  </section>
  
  
  <jsp:include page="../set/setLayout/footer.jsp" flush="false"/>
  <jsp:include page="../set/setLink/script.jsp" flush="false"/>
</body>
</html>