<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>캣메이트 예약하기</title>
  <jsp:include page="../set/setLink/link.jsp" flush="false"/>
  <link href="${pageContext.request.contextPath }/resources/reserve/css/sitter_detail.css" rel="stylesheet"/>
</head>
<body>
  <jsp:include page="../set/setLayout/navigation.jsp" flush="false"/>
  <section class="py-5 bg-white">
    <jsp:include page="./common/area.jsp" flush="false"/>
    <div class="container">
      <div class="row">
        <div class="col-md-8">
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
                <img class="d-block img-fluid w-100" id="imgHeight" src="${pageContext.request.contextPath }/resources/sregister/petSitter/img/test${i }.png">
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
          <br><br>
          <div id="middle" class="text-secondary">
            <div class="row">
              <div class="col-md-3" id="topCon1">
                <div class="carousel-inner">
                  <img class="rounded-circle" src="${pageContext.request.contextPath }/resources/sregister/petSitter/img/test1.png">
                </div>
              </div>
              <div class="col-md-9">
                <h4>조지헌</h4><br>
                <button class="btn btn-warning text-light">아파트</button>&nbsp;&nbsp;
                <button class="btn btn-warning text-light">마당</button>&nbsp;&nbsp;
                <button class="btn btn-warning text-light">아파트</button>&nbsp;&nbsp;
                <button class="btn btn-warning text-light">아파트</button>&nbsp;&nbsp;
              </div>
            </div>
          </div>
          <div id="middle" class="text-secondary">
             <div class="row" id="middle2">
               <div class="col-md-6">
                 <div class="row">
                   <div class="col-md-3">
                     <img src="${pageContext.request.contextPath }/resources/icon/icon-dog2.png">
                   </div>
                   <div class="col-md-9" >
                     <ul>
                       <li>1~15kg 까지 가능합니다.</li>
                       <li>강아지, 성견, 노령견 케어 가능합니다.</li>
                     </ul>
                   </div>
                 </div>
               </div>
               <div class="col-md-6">
                 <div class="row">
                   <div class="col-md-3">
                     <img src="${pageContext.request.contextPath }/resources/icon/icon-clock.png">
                   </div>
                   <div class="col-md-9">
                     <ul>
                       <li>체&nbsp;&nbsp;크&nbsp;&nbsp;인 08:00 ~ 23:00</li>
                       <li>체크아읏 08:00 ~ 23:00</li>
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
                    <td id="left">돌봄공간</td>
                    <td id="right">아파트</td>
                  </tr>
                  <tr> 
                    <td id="left">인근지하철역</td>
                    <td id="right">탄헌역 15뷴</td>
                  </tr>
                   <tr> 
                    <td id="left">마당유무</td>
                    <td id="right">없습니다.</td>
                  </tr>
                </table>
               </div>
               <div class="col-md-6">
                <table class="table table-striped">
                  <tr> 
                    <td id="left">14세 미만 아동</td>
                    <td id="right">없습니다.</td>
                  </tr>
                  <tr> 
                    <td id="left">가족 동거 유무</td>
                    <td id="right">가족과 함께 살고있습니다.</td>
                  </tr>
                   <tr> 
                    <td id="left">다른 동물 유무</td>
                    <td id="right">없습니다.</td>
                  </tr>
                </table>
               </div>
             </div>
           </div>
           <div id="middle" class="text-secondary">
           
              <div class="row">
                <div class="col-md-12">
                  <h5 class="text-primary">후기 (0개)</h5>
                  <br><br>
                </div>
              </div>
              
              <div class="row" id="middle4">
                <div class="col-md-3" id="topCon1">
                  <div class="carousel-inner">
                    <img class="rounded-circle" src="${pageContext.request.contextPath }/resources/sregister/petSitter/img/test1.png">
                  </div>
                </div>
                <div class="col-md-9">
                  <div class="row">
                    <div class="col-md-6" id="left">
                      <b id="left">조지헌</b>
                    </div>
                    <div class="col-md-6" id="right">
                      <b id="right" >24시간 돌봄</b>
                    </div>
                  </div>
                  <p>너무너무 감사드려요! 사전만남부터 꼼꼼히 챙겨주시고 정말 잘 돌봐주셨어요 저도 못 갔던 피크닉도 가주셨어요ㅜㅜ 사진,돌봄일지,카톡 너무 감사드리며 특히 가족분들께서도 자상하게 잘 돌봐주셔서 감사드립니다! 자주 연락드릴께요 :)
                  </p>
                  2018.03.02
                </div>
             </div>
           </div>
           
           <div id="middle" class="text-secondary">
              <div class="row" id="middle4">
                <div class="col-md-3" id="topCon1">
                  <div class="carousel-inner">
                    <img class="rounded-circle" src="${pageContext.request.contextPath }/resources/sregister/petSitter/img/test1.png">
                  </div>
                </div>
                <div class="col-md-9">
                  <div class="row">
                    <div class="col-md-6" id="left">
                      <b id="left">조지헌</b>
                    </div>
                    <div class="col-md-6" id="right">
                      <b id="right" >24시간 돌봄</b>
                    </div>
                  </div>
                  <p>너무너무 감사드려요! 사전만남부터 꼼꼼히 챙겨주시고 정말 잘 돌봐주셨어요 저도 못 갔던 피크닉도 가주셨어요ㅜㅜ 사진,돌봄일지,카톡 너무 감사드리며 특히 가족분들께서도 자상하게 잘 돌봐주셔서 감사드립니다! 자주 연락드릴께요 :)
                  </p>
                  2018.03.02
                </div>
             </div>
           </div>
        </div>
        
        
        <div class="col-md-4">
          <div id="sidebar">
            <dl>
              <dt class="text-secondary">날짜</dt>
              <dd class="text-secondary" id="day">시작 날짜 00:00&nbsp;&nbsp;&nbsp;&nbsp;>&nbsp;&nbsp;&nbsp;&nbsp;마침날짜 00:00</dd>
            </dl>
            <dl>
              <dt class="text-warning" id="price">30,000원</dt>
              <dd>
                <span class="text-secondary">반려견 추가 당</span> 
                <span class="text-warning">20,000원</span>
                <div class="btn-group">
                  <input type="button" class="btn btn-warning text-light" value="-">
                  <span class="btn btn-warning text-light">0 마리</span>
                  <input type="button" class="btn btn-warning text-light" value="+">
                  </div>
              </dd>
            </dl>
            <table class="table table-condensed text-secondary">
              <tr>
                <td>1박</td>
                <td>30,000원</td>
              </tr>
              <tr>
                <td>초과금액</td>
                <td>30,000원</td>
              </tr>
              <tr>
                <td>반려견 추가</td>
                <td>0원</td>
              </tr>
              <tr>
                <td>부가세</td>
                <td>3000원</td>
              </tr>
              <tr>
                <td>총 합계</td>
                <td>33,000원</td>
              </tr>
            </table>
            <input type="button" class="btn btn-warning text-light" value="예약하기" id="reservation">
            <br><br>
            <img src="${pageContext.request.contextPath }/resources/icon/inquiry.png">
            <a href="#" class="text-secondary">메세지 보내기</a>
          </div>
        </div>
      </div>
    </div>
  </section>
  <jsp:include page="../set/setLayout/footer.jsp" flush="false"/>
  <jsp:include page="../set/setLink/script.jsp" flush="false"/>
</body>
</html>