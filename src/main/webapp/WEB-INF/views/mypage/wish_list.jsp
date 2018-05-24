<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>캣메이트 위시리스트</title>
  <jsp:include page="./../set/setLink/link.jsp" flush="false"/>
  <link href="${pageContext.request.contextPath }/resources/css/mypage_common.css" rel="stylesheet"/>
  <script>
    $(document).ready(function() {
    	$("td").children("a").click(function() {
    		$.ajax({
    			type: "post",
    			url: "wish_list_reserve",
    			data: {
    				idx: this.id
    			},
    			dataType: "text",
    			success: function(responseData, status, xhr) {
    				$("#" + responseData).parents("tr").remove();
    			},
    			error: function(xhr, status, error) {
    				alert("위시리스트에 접근 하지 못했습니다.");
    			}
    		});
    	});
    });
  </script>
</head>
<body>
  <jsp:include page="./../set/setLayout/navigation.jsp" flush="false"/>
  <section class="py-5">
    <div class="container">
      <div class="row">
        <div class="col-md-4">
          <jsp:include page="./common/menubar.jsp"/>
        </div>
        <div class="col-md-8">
          <div class="card text-secondary p-5 bg-default">
            <div class="card-body">
              <h4 class="mb-4 text-warning text-center">위시리스트</h4>
              <br>
              <table class="table text-center" id="mypage_table">
                <tr>
                  <th>펫시터</th>
                  <th>펫시터 평점</th>
                  <th>주소</th>
                  <th>비고</th>
                </tr>
                <c:set var="i" value="${0 }"/>
                <c:forEach var="user_profileDto" items="${user_profileList }">
                  <tr>
                    <td>${user_profileDto.user_name }</td>
                    <td>${user_profileDto.user_grade }</td>
                    <td>${pet_sitter_houseList[i].house_address }<br>${pet_sitter_houseList[i].house_daddress }</td>
                    <td>
                      <a href="#" id="${pet_sitter_houseList[i].idx }">취소</a>
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
  <jsp:include page="./../set/setLayout/footer.jsp" flush="false"/>
</body>
</html>