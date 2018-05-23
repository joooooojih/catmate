<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>캣메이트 위시리스트</title>
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
              <h4 class="mb-4 text-warning text-center">위시리스트</h4>
              <br>
              <table class="table text-center" id="mypage_table">
                <tr>
                  <th>펫시터</th>
                  <th>펫시터 평점</th>
                  <th>주소</th>
                </tr>
                <tr>
                  <td>${user_profile.user_name }</td>
                  <td>0점
                  <td class="text-left">서울 강남구 강남대로 238 (도곡동, 스카이쏠라빌딩)<br>201동 202호</td>
                </tr>
                <tr>
                  <td>${user_profile.user_name }</td>
                  <td>0점
                  <td class="text-left">서울 강남구 강남대로 238 (도곡동, 스카이쏠라빌딩)<br>201동 202호</td>
                </tr>
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