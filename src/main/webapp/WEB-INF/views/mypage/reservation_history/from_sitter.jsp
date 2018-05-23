<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>캣메이트 예약 내역</title>
  <jsp:include page="../../set/setLink/link.jsp" flush="false"/>
  <link href="${pageContext.request.contextPath }/resources/css/mypage_common.css" rel="stylesheet"/>
</head>
<body>
  <jsp:include page="../../set/setLayout/navigation.jsp" flush="false"/>
  <section class="py-5">
    <div class="container">
      <div class="row">
        <div class="col-md-4">
          <jsp:include page="../common/menubar.jsp"/>
        </div>
        <div class="col-md-8">
          <jsp:include page="./common/top_bar.jsp"/>
          <div class="card text-secondary p-5 bg-default">
            <div class="card-body">
              <h4 class="mb-4 text-warning text-center">받은 내역</h4>
              <br>
              <table class="table text-center" id="mypage_table">
                <tr>
                  <th>이름</th>
                  <th>날짜</th>
                  <th>주소</th>
                  <th>마리 수</th>
                </tr>
                <c:set var="i" value="${0 }"/>
                <c:forEach var="reservation" items="${reservationList }">
                  <tr>
                    <td>${user_profileList[i].user_name }</td>
                    <td>${start_dayList[i] }<br>${end_dayList[i] }</td>
                    <td>
                      <a href="${pageContext.request.contextPath }/reserve/sitter_detail?idx=${pet_sitter_houseList[i].idx}">
                        ${pet_sitter_houseList[i].house_address }<br>${pet_sitter_houseList[i].house_daddress }
                      </a>
                    </td>
                    <td>${reservation.how_many+1 }</td>
                  </tr>
                  <c:set var="i" value="${i+1 }"/>
                </c:forEach>
              </table>
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>
  <jsp:include page="../../set/setLayout/footer.jsp" flush="false"/>
</body>
</html>