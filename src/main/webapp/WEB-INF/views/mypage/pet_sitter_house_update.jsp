<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>캣메이트 펫시터 수정</title>
  <jsp:include page="./../set/setLink/link.jsp" flush="false"/>
  <link href="${pageContext.request.contextPath }/resources/css/mypage_common.css" rel="stylesheet"/>
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
              <h4 class="mb-4 text-warning text-center">펫시터 수정</h4>
              <br>
              <table class="table text-center" id="mypage_table">
                <tr>
                  <th>제목</th>
                  <th>등록</th>
                  <th>비고</th>
                </tr>
                <c:forEach var="pet_sitter_house" items="${pet_sitter_houseList }">
                  <tr>
                    <td>
                      <c:choose>
                        <c:when test="${pet_sitter_house.sregister == 'yes' }">
                          <a href="${pageContext.request.contextPath }/reserve/sitter_detail?idx=${pet_sitter_house.idx}">
                            ${pet_sitter_house.house_title }
                          </a>
                        </c:when>
                        <c:otherwise>
                          ${pet_sitter_house.house_title }
                        </c:otherwise>
                      </c:choose>
                    </td>
                    <td>${pet_sitter_house.sregister }</td>
                    <td>
                      <a href="${pageContext.request.contextPath }/mypage/pet_sitter_house_update_idx?idx=${pet_sitter_house.idx }">수정</a>
                    </td>
                  </tr>
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