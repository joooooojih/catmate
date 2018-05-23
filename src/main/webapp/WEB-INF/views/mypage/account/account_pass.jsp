<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>캣메이트 계정 관리 : 비밀번호 변경</title>
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
              <h4 class="mb-4 text-warning">비밀번호 변경</h4>
              <br>
              <h5>이메일</h5>
              <b class="text-warning">${user_profile.user_email }</b>
              <br><br>
              <h5>비밀번호</h5>
              <form action="account_pass" method="post">
                <div class="form-group">
                  <input type="password" name="user_password" class="form-control" placeholder="변경 할 비밀번호" required>
                </div>
                <br>
                <input type="submit" id="submit" class="btn btn-warning text-white" value="확인">
              </form>
              <br>
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>
  <jsp:include page="../../set/setLayout/footer.jsp" flush="false"/>
</body>
</html>