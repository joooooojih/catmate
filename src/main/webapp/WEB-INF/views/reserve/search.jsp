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
  <script>
    $(document).ready(function() {
    	$.datepicker.setDefaults({  // 달력 공통 설정
    	  dateFormat: 'yy-mm-dd',
    	  prevText: '이전 달',
        nextText: '다음 달',
        monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
        monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
        dayNames: ['일','월','화','수','목','금','토'],
        dayNamesShort: ['일','월','화','수','목','금','토'],
    	  dayNamesMin: ['일','월','화','수','목','금','토'],
    	  showMonthAfterYear: true,
        changeMonth: true,
        changeYear: true,
    	  yearSuffix: '년',
        minDate: 0,
    	  maxDate: "1y"
      });
    	    
    	$("#start_day").datepicker({  // 시작 날짜
    	  onClose: function( selectedDate ) {
	        $("#end_day").datepicker( "option", "minDate", selectedDate );
    	  }
      });
    	    
    	$("#end_day").datepicker({  // 마침 날짜
    	  onClose: function( selectedDate ) {
	        $("#start_day").datepicker( "option", "maxDate", selectedDate );
    	  }
      });
    	
    	$("label[id^=tag_search]").click(function() {
    	  alert('dd');
    	});
    	
    	$("select[id^=select_search]").change(function() {
    		alert('aa');
    	});
    	
    	function search() {  // ajax를 이용한 검색
    		
    		
    	};
    });
    
  </script>
</head>
<body draggable="true">
  <jsp:include page="../set/setLayout/navigation.jsp" flush="false"/>
  <section class="py-5 bg-light">
    <jsp:include page="./common/area.jsp" flush="false"/>
    <div class="container" id="container"><br>
      <div class="row">
        <div class="col-md-12">
          <div class="btn-group">
            <select class="btn btn-light" id="select_search">
              <option>모든연령</option>
              <option>강아지 (1살 이하)</option>
              <option>성견 (2~6살)</option>
              <option>노견 (7살 이상)</option>
            </select>&nbsp;&nbsp;&nbsp;
            <select class="btn btn-light" id="select_search">
              <option>모든크기</option>
              <option>소형견 (0 ~ 4.9kg)</option>
              <option>중형견 (5 ~ 14.9kg)</option>
              <option>대형견 (15kg이상)</option>
            </select>&nbsp;&nbsp;&nbsp;
          </div>
        </div>
      </div>
      <br>
      <div class="row">
        <div class="col-md-6">
          <dl>
            <dt class="text-secondary">예약 날짜</dt>
            <dd class="text-secondary" id="day">
              <div class="row">
                <div class="col-md-5">
                  <input type="text" class="form-control text-secondary" id="start_day" placeholder="시작 날짜 0000-00-00" required>
                </div>
                <div class="col-md-5">
                  <input type="text" class="form-control text-secondary" id="end_day" placeholder="마침 날짜 0000-00-00" required>
                </div>
              </div>
            </dd>
          </dl>
        </div>
      </div>
      <div class="row">
        <div class="col-md-12">
          <p>&nbsp;원하는 태그를 모두 선택해 주세요!</p>
          <div class="btn-group" data-toggle="buttons">
            <c:set var="i" value="${0 }"/>
            <c:forEach var="tags" items="${tags }">
              <label class="btn btn-warning text-light" id="tag_search">${tags }
                <input type="checkbox" id="${tags_name[i] }" autocomplete="off" value="${tags_value[i] }">
              </label>&nbsp;
              <c:set var="i" value="${i+1 }"/>
            </c:forEach>
          </div>
        </div>
      </div>
      <br><br>
    </div>
  </section>
  
  <section class="py-5 bg-white">
    <div class="container">
      <c:set var="i" value="0"/>
      <c:forEach var="pet_sitter_house" items="${pet_sitter_houseList }">
        <div class="row">
        <div class="col-md-4">
          <div id="carousel${i }" class="carousel slide" data-ride="carousel">
            <div class="carousel-inner">
            <c:set var="room_photo" value="${room_photoList[i] }"/>
            <c:forEach var="j" begin="0" end="4">
              <c:choose>
                <c:when test="${j == 0 }">
                  <c:set var="carouselClass" value="carousel-item active"/>
                </c:when>
                <c:otherwise>
                  <c:set var="carouselClass" value="carousel-item"/>
                </c:otherwise>
              </c:choose>
              
              <div class="${carouselClass }">
                <img class="d-block img-fluid w-100" id="mainImg" src="${pageContext.request.contextPath }/resources/pet_sitter/img/${room_photo[j].room_photo }">
                <div class="carousel-caption">
                  <h5>
                    <img src="${pageContext.request.contextPath }/resources/icon/addressIcon.png"> ${pet_sitter_house.house_address }
                  </h5>
                </div>
              </div>
              </c:forEach>
              
            </div>
            <a class="carousel-control-prev" href="#carousel${i }" role="button" data-slide="prev">
              <span class="carousel-control-prev-icon" aria-hidden="true"></span>
              <span class="sr-only">Previous</span>
            </a>
            <a class="carousel-control-next" href="#carousel${i }" role="button" data-slide="next">
              <span class="carousel-control-next-icon" aria-hidden="true"></span>
              <span class="sr-only">Next</span>
            </a>
          </div>
        </div>
        <div class="col-md-7" id="bodyRow">
          <h3 class="text-primary pt-2">
          <a href="${pageContext.request.contextPath }/reserve/sitter_detail?idx=${pet_sitter_house.idx}">
            <b>${pet_sitter_house.house_title }</b>
          </a>
            <i class="d-block fa  fa-heart-o fa-fw pull-right"></i>
          </h3>
          <p id="subtitle">
           <b>
            <img class="rounded-circle" id="subImg" src="${pageContext.request.contextPath }/resources/icon/icon-dog.png">&nbsp;&nbsp;반려견 1마리  <!-- 게시자의 반려견 수 --> <!-- 강아지 등록 후에 변경 -->
           </b>
          </p>
          <hr>
          <p>
            <c:if test="${pet_sitter_house.care_space eq '아파트' }">
              <button class="btn btn-warning text-light">아파트</button>&nbsp;&nbsp;
            </c:if>
            <c:if test="${pet_sitter_house.pick_up eq 'yes'}">
              <button class="btn btn-warning text-light">픽업</button>&nbsp;&nbsp;
            </c:if>
            <c:if test="${pet_sitter_house.yard eq '없습니다.'}">
              <button class="btn btn-warning text-light">마당</button>&nbsp;&nbsp;
            </c:if>
            <c:if test="${pet_sitter_house.other_animal eq '없습니다.'}">
              <button class="btn btn-warning text-light">반려견 없음</button>&nbsp;&nbsp;
            </c:if>
            <c:if test="${pet_sitter_house.patient_dog eq 'yes'}">
              <button class="btn btn-warning text-light">환자견 케어</button>&nbsp;&nbsp;
            </c:if>
            <c:if test="${pet_sitter_house.license eq 'yes'}">
              <button class="btn btn-warning text-light">자격증</button>&nbsp;&nbsp;
            </c:if>
            <c:if test="${pet_sitter_house.outdoor_bowels eq 'yes'}">
              <button class="btn btn-warning text-light">실외 배변</button>&nbsp;&nbsp;
            </c:if>
            <c:if test="${pet_sitter_house.homemade_snacks eq 'yes'}">
              <button class="btn btn-warning text-light">수제간식</button>&nbsp;&nbsp;
            </c:if>
            <c:if test="${pet_sitter_house.first_aid eq 'yes'}">
              <button class="btn btn-warning text-light">응급처치</button>&nbsp;&nbsp;
            </c:if>
            <c:if test="${pet_sitter_house.medication_possible eq 'yes'}">
              <button class="btn btn-warning text-light">투약가능</button>&nbsp;&nbsp;
            </c:if>
          </p>
          <div class="row">
            <div class="col-md-12" id="price">
              <span class="text-secondary">day care / <b>${pet_sitter_house.day_care }원</b></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="text-warning">
              1박 / <b>${pet_sitter_house.nightly_rate }원</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
            </div>
          </div><br>
          <div class="row" id="bottomRow">
            <div class="col-md-4 text-secondary" id="searchName">
              <div class="carousel-inner">
                <img class="rounded-circle" id="subImg" src="${pageContext.request.contextPath }/resources/profile/user_img/${user_profileList[i].user_photo }">
                <b>${user_profileList[i].user_name }</b>
              </div>
            </div>
            <div class="col-md-4 text-secondary" id="petGrade">
              <img id="subtitleImg" src="${pageContext.request.contextPath }/resources/icon/icon-star.png">&nbsp;
              <b>펫시터 평점</b>&nbsp;&nbsp;&nbsp;${user_profileList[i].user_grade }점
            </div>
            <div class="col-md-4 text-secondary" id="reviewsGrade">
             <img id="subtitleImg" src="${pageContext.request.contextPath }/resources/icon/icon-review.png">
              <b>후기 평점</b>&nbsp;&nbsp;&nbsp;0점   <!-- 후기를 하고 마저.. -->
            </div>
          </div>
        </div>
      </div>
      <br><br>
      <c:set var="i" value="${i + 1 }"/>
      </c:forEach>
    </div>
  </section>
  
  
  <jsp:include page="../set/setLayout/footer.jsp" flush="false"/>
</body>
</html>