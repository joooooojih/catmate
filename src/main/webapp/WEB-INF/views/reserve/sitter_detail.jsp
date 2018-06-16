<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="https://service.iamport.kr/js/iamport.payment-1.1.5.js"></script>
<title>캣메이트 예약하기</title>
  <jsp:include page="../set/setLink/link.jsp" flush="false"/>
  <link href="${pageContext.request.contextPath }/resources/css/sitter_detail.css" rel="stylesheet"/>
  <script>
  $(document).ready(function() { // 이전
    $("#previous").click(function() {
      $(location).attr("href", "pet_sitter04");
    });
  });
  
  $(document).ready(function() {  // 펫시터 등록 예약하기 disabled 하기
    if(${pet_sitter_house.sregister eq 'no'}) {
      $("#reservation").prop("disabled", true);
    }
  });
  
  $(document).ready(function() {  // 가격 변동
    
    var pet_add_text = 0;
    var pet_add_price = 0;  // 추가 된 가격
    var excess_amount = ${pet_sitter_house.nightly_rate};
    var vat = excess_amount / 10;  // 부가세
    var total_price = excess_amount + vat;  // 전체 금액
    var day_price = ${pet_sitter_house.nightly_rate};
    var day = 1;
    
    var disabledDays = new Array;  // disable 할 날짜
    
    $("input[id=disable_day]").each(function() {
      disabledDays.push(this.value);
    });
    
    function setPrice() {  // 가격 셋팅
      $("#pet_add_text").text(pet_add_text + " 마리");
      $("#pet_add_price").text(pet_add_price + "원");
      $("#excess_amount").text(excess_amount + "원");
      $("#vat").text(vat + "원");
      $("#total_price").text(total_price + "원");
    }
    
    setPrice();  // 초기 값 설정
    
    function disableAllTheseDays(date) {  // 특정 날짜 막기
      var yyyy = date.getFullYear();
      var mm = date.getMonth() + 1;
      var dd = date.getDate();
      
      mm = mm.length == 2 ? "-" + mm : "-0" + mm;
      dd = dd > 10 ? "-" + dd : "-0" + dd;
      
      for (var i = 0; i < disabledDays.length; i++) {
          if($.inArray(yyyy + mm + dd, disabledDays) != -1) {
              return [false];
          }
      }
      return [true];
    }
    
    function getDateDiff(start_date, end_date) {  // 날짜 차이 계산 함수
      
      var arrDate1 = start_date.split("-");
      var arrDate2 = end_date.split("-");
      
      var getDate1 = new Date(parseInt(arrDate1[0]), parseInt(arrDate1[1])-1, parseInt(arrDate1[2]));
      var getDate2 = new Date(parseInt(arrDate2[0]), parseInt(arrDate2[1])-1, parseInt(arrDate2[2]));
      
      var getDiffTime = getDate2.getTime() - getDate1.getTime();
      
      day =  getDiffTime / (1000*60*60*24);
    
    }
    
    function setDay_price() {  // 예약 날짜
      if(day == 0) { // 데이케어
        day = 1;
        day_price = ${pet_sitter_house.day_care};
        pet_add_price = ${pet_sitter_house.surcharge} * pet_add_text;
        
      } else {  // 몇 박
        day_price = ${pet_sitter_house.nightly_rate} * day;
        pet_add_price = (${pet_sitter_house.surcharge} * day) * pet_add_text;
      }
    
      excess_amount = pet_add_price + day_price;
      vat = excess_amount / 10;
      total_price = excess_amount + vat;
      
      setPrice();
    }
    
    $("#minus").click(function () {  // - 클릭시
      if(pet_add_text != 0) {
        
        pet_add_text--;
        pet_add_price = pet_add_price - (${pet_sitter_house.surcharge} * day);
        excess_amount = day_price + pet_add_price;
        vat = excess_amount / 10;
        total_price = excess_amount + vat;
        
        setPrice();
      }
    });
  
    $("#plus").click(function() {  // + 클릭시
      if(pet_add_text != 5) {
        
        pet_add_text++;
        pet_add_price = pet_add_price + (${pet_sitter_house.surcharge} * day);
        excess_amount =  pet_add_price + day_price;
        vat = excess_amount / 10;
        total_price = excess_amount + vat;
        
        setPrice();
      }
    });
   
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
      beforeShowDay: disableAllTheseDays,
      minDate: 0,
      maxDate: "1y"
    });
    
    $("#start_day").datepicker({  // 시작 날짜
      onClose: function( selectedDate ) {
        var start_time = new Date($("#start_day").val());
        var end_time = new Date($("#end_day").val());
        var overlap = false;
        
        for(var i = 0; i < disabledDays.length; i++) {
            var disableDays_time = new Date(disabledDays[i]);
            if(start_time <= disableDays_time && end_time >= disableDays_time ) {
              overlap = true;
            }
          }
        
        if(overlap) {
          alert("예약이 불가능한 날짜가 있습니다.");
          $("#start_day").val("");
        } else {
          if($("#start_day").val() != "" && $("#end_day").val()) {
            getDateDiff($("#start_day").val(), $("#end_day").val());  // 날짜 차이 구함
            setDay_price();  // 그 에 따른 값 셋팅
          }
        }
        $("#end_day").datepicker( "option", "minDate", selectedDate );
      }
    });
    
    $("#end_day").datepicker({  // 마침 날짜
      onClose: function( selectedDate ) {
        var start_time = new Date($("#start_day").val());
        var end_time = new Date($("#end_day").val());
        var overlap = false;
        for(var i = 0; i < disabledDays.length; i++) {
            var disableDays_time = new Date(disabledDays[i]);
            if(start_time <= disableDays_time && end_time >= disableDays_time ) {
              overlap = true;
            }
          }
        
        if(overlap) {
          alert("예약이 불가능한 날짜가 있습니다.");
          $("#end_day").val("");
        } else {
          if($("#start_day").val() != "" && $("#end_day").val()) {
            getDateDiff($("#start_day").val(), $("#end_day").val());  // 날짜 차이 구함
            setDay_price();  // 그 에 따른 값 셋팅
          }
        }
        $("#start_day").datepicker( "option", "maxDate", selectedDate );
      }
    });
    
    
    $("#reservation").click(function() {
      
      $.ajax({  // 금액 유효성 검사
        type: "get",
        url: "sitter_detail_price_check",
        data: {
          idx: ${pet_sitter_house.idx},
          start_date: $("#start_day").val(),
          end_date: $("#end_day").val(),
          how_many: pet_add_text
        },
        dataType: "text",
        success: function(responseData, status, xhr) {
          
          if(responseData == total_price) {
              IMP.init('iamport');  // 결제
              IMP.request_pay({
                  pg : 'inicis', // version 1.1.0부터 지원.
                  pay_method : 'card',
                  merchant_uid : 'merchant_' + new Date().getTime(),
                  name : '반려견 예약 : ' + Number(pet_add_text + 1) + "마리",
                  amount : 10,  // 금액
                  buyer_email : '${user_profile.user_email}',
                  buyer_name : '${user_profile.user_name}',
                  buyer_tel : '${user_profile.user_photo}',
                  buyer_addr : '${user_profile.user_address}',
                  buyer_postcode : '${user_profile.user_zip_code}',
                  m_redirect_url : 'https://www.yourdomain.com/payments/complete'
              }, function(rsp) {  // 결제성공
                  if ( rsp.success ) {
                      var msg = '결제가 완료되었습니다.';
                      msg += '고유ID : ' + rsp.imp_uid;
                      msg += '상점 거래ID : ' + rsp.merchant_uid;
                      msg += '결제 금액 : ' + rsp.paid_amount;
                      msg += '카드 승인번호 : ' + rsp.apply_num;
                      
                      $.ajax({  // 결제 성공 후 reservation에 insert
                        type: "post",
                        url: "sitter_detail",
                        data: {
                          idx: ${pet_sitter_house.idx},
                          start_date: $("#start_day").val(),
                          end_date: $("#end_day").val(),
                          how_many: pet_add_text
                        },
                        success: function(responseData, status, xhr) {
                          alert("예약 되었습니다.");
                          $(location).attr("href", "sitter_detail?idx=${pet_sitter_house.idx}");
                        },
                         error: function(xhr, status, error) {
                         alert("예약에 실패 했습니다.");
                        }
                      });
                    
                  } else {  // 결제 실패
                      var msg = '결제에 실패하였습니다.';
                      msg += '에러내용 : ' + rsp.error_msg;
                  }
                alert(msg);
             });
          } else {
            alert("잘못된 접근 입니다.");
          }
          
        },
        error: function(xhr, status, error) {
          alert("날짜를 선택해 주세요.");
        }
      });
      
    });
  });
  
  </script>
</head>
<body>
  <jsp:include page="../set/setLayout/navigation.jsp" flush="false"/>
  <section class="py-5 bg-white">
    <jsp:include page="./common/area.jsp" flush="false"/>
    <div class="container">
      <div class="row">
        <div class="col-md-8">
        <c:forEach var="disable_day" items="${disable_dayList }">
          <input type="hidden" id="disable_day" value="${disable_day }">
        </c:forEach>
          <div id="carousel" class="carousel slide" data-ride="carousel">
            <div class="carousel-inner">
            <c:set var="j" value="1"/>
            <c:forEach var="room_photo" items="${room_photoList }">
            
              <c:choose>
                <c:when test="${j == 1 }">
                  <c:set var="carouselClass" value="carousel-item active"/>
                </c:when>
                <c:otherwise>
                  <c:set var="carouselClass" value="carousel-item"/>
                </c:otherwise>
              </c:choose>
              <div class="${carouselClass }">
                <img class="d-block img-fluid w-100" id="mainImg" src="${pageContext.request.contextPath }/resources/img/pet_sitter_img/${room_photo.room_photo }">
                <div class="carousel-caption">
                  <h5>
                    <img src="${pageContext.request.contextPath }/resources/img/icon/addressIcon.png"> ${pet_sitter_house.house_address }
                    <c:if test="${pet_sitter_house.house_daddress != '' && pet_sitter_house.house_daddress != null }">
                      <br> ${pet_sitter_house.house_daddress }
                    </c:if>
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
          <br><br>
          <div id="middle" class="text-secondary">
            <div class="row">
              <div class="col-md-3" id="topCon1">
                <div class="carousel-inner">
                  <img class="rounded-circle" src="${pageContext.request.contextPath }/resources/img/user_img/${house_user_profile.user_photo }">
                </div>
              </div>
              <div class="col-md-9" id="tags">
                <h4>&nbsp;&nbsp;${house_user_profile.user_name }</h4><br>
                <c:if test="${pet_sitter_house.care_space eq '아파트' }">
                  <button class="btn btn-warning text-light">아파트</button>&nbsp;&nbsp;
                </c:if>
                <c:if test="${pet_sitter_house.pick_up eq 'yes'}">
                  <button class="btn btn-warning text-light">픽업</button>&nbsp;&nbsp;
                </c:if>
                <c:if test="${pet_sitter_house.yard eq '있습니다.'}">
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
              </div>
            </div>
          </div>
          <div id="middle" class="text-secondary">
             <div class="row" id="middle2">
               <div class="col-md-6">
                 <div class="row">
                   <div class="col-md-3">
                     <img src="${pageContext.request.contextPath }/resources/img/icon/icon-dog2.png">
                   </div>
                   <div class="col-md-9" >
                     <ul>
                       <li>1~${pet_sitter_house.care_size }kg 까지 가능합니다.</li>
                       <li>${pet_sitter_house.care_age } 케어 가능합니다.</li>
                     </ul>
                   </div>
                 </div>
               </div>
               <div class="col-md-6">
                 <div class="row">
                   <div class="col-md-3">
                     <img src="${pageContext.request.contextPath }/resources/img/icon/icon-clock.png">
                   </div>
                   <div class="col-md-9">
                     <ul>
                       <li>체&nbsp;&nbsp;크&nbsp;&nbsp;인 ${pet_sitter_house.check_in }</li>
                       <li>체크아읏 ${pet_sitter_house.check_out }</li>
                     </ul>
                   </div>
                 </div>
               </div>
             </div>
           </div>
           <div id="middle" class="text-secondary">
            <div class="row">
              <div class="col-md-12">
                <h5 class="text-primary">돌봄 환경</h5>
                <br>
              </div>
            </div>
             <div class="row" id="middle3">
               <div class="col-md-6">
                <table class="table table-striped">
                  <tr> 
                    <td class="text-left">돌봄공간</td>
                    <td class="text-right">${pet_sitter_house.care_space }</td>
                  </tr>
                  <tr> 
                    <td class="text-left">인근지하철역</td>
                    <td class="text-right">${pet_sitter_house.subway_station }</td>
                  </tr>
                   <tr> 
                    <td class="text-left">마당유무</td>
                    <td class="text-right">${pet_sitter_house.yard }</td>
                  </tr>
                </table>
               </div>
               <div class="col-md-6">
                <table class="table table-striped">
                  <tr> 
                    <td class="text-left">14세 미만 아동</td>
                    <td class="text-right">${pet_sitter_house.children_under_14 }</td>
                  </tr>
                  <tr> 
                    <td class="text-left">가족 동거 유무</td>
                    <td class="text-right">${pet_sitter_house.live_together }</td>
                  </tr>
                   <tr> 
                    <td class="text-left">다른 동물 유무</td>
                    <td class="text-right">${pet_sitter_house.other_animal }</td>
                  </tr>
                </table>
               </div>
             </div>
           </div>
           
           <div id="middle" class="text-secondary">
           
              <div class="row" id="review">
                <div class="col-md-12">
                  <h5 class="text-primary">후기 (${review_count }개)</h5>
                  <br><br>
                </div>
              </div>
              
              <c:if test="${review_check eq 'yes' }">
                <form action="../mypage/review" method="post">
                  <div class="row" id="middle4">
                    <div class="col-md-3 text-center">
                      <div class="carousel-inner">
                        <img class="rounded-circle" src="${pageContext.request.contextPath }/resources/img/user_img/${user_profile.user_photo }">
                      </div>
                    </div>
                    <div class="col-md-9">
                      <div class="row">
                        <div class="col-md-6 text-left">
                          <b>${user_profile.user_name }</b>
                          <input type="hidden" name="idx" value="${reservation.idx }">
                        </div>
                        <div class="col-md-6 text-right">
                          <b>
                            <input type="hidden" name="start_date" value="<fmt:formatDate value='${reservation.start_day}' pattern='yyyy-MM-dd'/>">
                            <input type="hidden" name="end_date" value="<fmt:formatDate value='${reservation.end_day}' pattern='yyyy-MM-dd'/>">
                            <c:choose>
                              <c:when test="${reservation.start_day == reservation.end_day }">
                                day care
                                <input type="hidden" name="review_kind" value="day care">
                              </c:when>
                              <c:otherwise>
                                24시간 돌봄
                                <input type="hidden" name="review_kind" value="24시간 돌봄">
                              </c:otherwise>
                            </c:choose>
                          </b>
                        </div>
                      </div>
                      <textarea class="form-control" name="review_content" rows="5" required style="resize: none;"></textarea>
                      <br>
                      <div class="row">
                        <div class="col-md-6">
                          <select class="form-control" name="review_scope" required>
                            <option value="0">펫시터 점수</option>
                            <option value="0">0점</option>
                            <option value="1">1점</option>
                            <option value="2">2점</option>
                            <option value="3">3점</option>
                            <option value="4">4점</option>
                            <option value="5">5점</option>
                          </select>
                        </div>
                        <div class="col-md-6">
                          <input type="submit" class="btn btn-primary" value="작성 완료">
                        </div>
                      </div>
                    </div>
                  </div>
                </form>
              </c:if>
              
              <c:set var="i" value="0"/>
              <c:forEach var="review" items="${reviewList }">
                <div class="row" id="middle4">
                  <div class="col-md-3 text-center">
                    <div class="carousel-inner">
                      <img class="rounded-circle" src="${pageContext.request.contextPath }/resources/img/user_img/${review_user_profileList[i].user_photo}">
                    </div>
                  </div>
                  <div class="col-md-9">
                    <div class="row">
                      <div class="col-md-6 text-left">
                        <b>${review_user_profileList[i].user_name }</b>
                      </div>
                      <div class="col-md-6 text-right">
                        <b>${review.review_kind }</b>
                      </div>
                    </div>
                    <p>${review.review_content }</p>
                    <fmt:formatDate value="${review.review_date}" pattern="yyyy-MM-dd"/>
                  </div>
                </div>
                <c:set var="i" value="${i+1 }"/>
              </c:forEach>
              
           </div>
        </div>
        
        
        <div class="col-md-4">
          <div id="sidebar">
            <dl>
              <dt class="text-secondary">예약 날짜</dt>
              <dd class="text-secondary" id="day">
                <input type="text" class="form-control text-secondary" id="start_day" placeholder="시작 날짜 0000-00-00" required>
                <br>
                <input type="text" class="form-control text-secondary" id="end_day" placeholder="마침 날짜 0000-00-00" required>
              </dd>
            </dl>
              <span class="text-secondary">반려견 추가 당</span> 
              <span class="text-warning">${pet_sitter_house.surcharge }원</span>
              <div class="btn-group">
                <input type="button" class="btn btn-warning text-light" id="minus" value="-">
                <span class="btn btn-warning text-light" id="pet_add_text">0 마리</span>
                <input type="button" class="btn btn-warning text-light" id="plus" value="+">
              </div>
              <br><br>
            <table class="table table-condensed text-secondary">
              <tr>
                <td>1박</td>
                <td>${pet_sitter_house.nightly_rate }원</td>
              </tr>
              <tr>
                <td>데이케어</td>
                <td>${pet_sitter_house.day_care }원</td>
              </tr>
              <tr>
                <td>초과금액</td>
                <td id="excess_amount"></td> <!-- 1박 * 날짜 -->
              </tr>
              <tr>
                <td>반려견 추가</td>
                <td id="pet_add_price"></td>
              </tr>
              <tr>
                <td>부가세</td>
                <td id="vat"></td>
              </tr>
              <tr>
                <td>총 합계</td>
                <td id="total_price"></td>
              </tr>
            </table>
            <input type="button" class="btn btn-warning text-light" value="예약하기" id="reservation">
            <br><br>
            <img src="${pageContext.request.contextPath }/resources/img/icon/inquiry.png">
            <a href="#" class="text-secondary" onclick="javascript:msg_window_open('${pet_sitter_house.user_email}')">메세지 보내기</a>
          </div>
        </div>
      </div>
      <br><br><br>
      <c:if test="${pet_sitter_house.sregister eq 'no' }">
        <div class="row">
          <div class="col-md-12">
            <form action="pet_sitter05" method="post" id="pet_sitter05_form">
              <div class="row text-center" id="buttonRow">
                <div class="col-md-6">
                  <input type="button" id="previous" class="btn btn-primary" value="이전">
                </div>
                <div class="col-md-6">
                  <input type="submit" class="btn btn-primary" value="등록">
                </div>
              </div>
            </form>
          </div>
        </div>
       </c:if>
    </div>
  </section>
  
  <jsp:include page="../set/setLayout/footer.jsp" flush="false"/>
</body>
</html>