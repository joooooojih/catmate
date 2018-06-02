<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>캣메이트 예약 내역</title>
  <jsp:include page="../../set/setLink/link.jsp" flush="false"/>
  <link href="${pageContext.request.contextPath }/resources/css/mypage_common.css" rel="stylesheet"/>
  <script>
    $(document).ready(function() {
    	$("td").children("a.cancel").unbind("click").bind("click", function() {
    		$.ajax({
    	    type: "post",
    	    url: "delete_to_sitter",
    	    data: {
    	      num: this.id,
    	      idx: $("#idx"+this.id).val(),
    	      start_date: $("#start_day"+this.id).text(),
    	      end_date: $("#end_day"+this.id).text()
    	    },
    	    dataType: "text",
    	    success: function(responseData, status, xhr) {
    	      $("#" + responseData).parents("tr").remove();
         },
    	    error: function(xhr, status, error) {
    	      alert("취소에 실패하였습니다.");
    	    }
    	  });
    	});
    });
  </script>
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
              <h4 class="mb-4 text-warning text-center">예약 내역</h4>
              <br>
              <table class="table text-center" id="mypage_table">
                <tr>
                  <th>펫시터</th>
                  <th>날짜</th>
                  <th>주소</th>
                  <th>비고</th>
                </tr>
                <c:set var="i" value="${0 }"/>
                <c:forEach var="reservation" items="${reservationList }">
                  <input type="hidden" id="idx${i }" value="${reservation.idx }">
                  <tr>
                    <td>${user_profileList[i].user_name }</td>
                    <td>
                      <span id="start_day${i }"><fmt:formatDate value="${reservation.start_day}" pattern="yyyy-MM-dd"/></span>
                      <br>
                      <span id="end_day${i }"><fmt:formatDate value="${reservation.end_day}" pattern="yyyy-MM-dd"/></span>
                    </td>
                    <td>
                      <a href="${pageContext.request.contextPath }/reserve/sitter_detail?idx=${reservation.idx}">
                        ${pet_sitter_houseList[i].house_address }<br>${pet_sitter_houseList[i].house_daddress }
                      </a>
                    </td>
                    <td>
                      <c:choose>
                        <c:when test="${to_day > reservation.end_day }">
                          <c:if test="${reservation.review_check eq 'X' }">
                            <a href="${pageContext.request.contextPath }/mypage/review?idx=${reservation.idx}&start_date=<fmt:formatDate value='${reservation.start_day}' pattern='yyyy-MM-dd'/>&end_date=<fmt:formatDate value='${reservation.end_day}' pattern='yyyy-MM-dd'/>">후기</a>
                          </c:if>
                        </c:when>
                        <c:otherwise>
                          <a href="#" id="${i }" class="cancel">취소</a>
                        </c:otherwise>
                      </c:choose>
                    </td>
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