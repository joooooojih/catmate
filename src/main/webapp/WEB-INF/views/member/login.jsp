<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <title>캣메이트 펫시터 로그인</title>
  <jsp:include page="../set/setLink/link.jsp" flush="false"/>
  
</head>
<body>
  <jsp:include page="../set/setLayout/navigation.jsp" flush="false"/>
  <section class="py-5 bg-light">
    <div class="container">
      <div class="row">
        <div class="col-md-3"> </div>
        <div class="col-md-6">
          <div class="card text-dark p-5 bg-default">
            <div class="card-body">
              <h1 class="mb-4 text-primary">Catmate Login</h1>
              <form action="login" method="post">
                <div class="form-group">
                  <input type="email" name="user_email" class="form-control" placeholder="이메일" required value="${user_profileDto.user_email }"><br>
                  <input type="password" name="user_password" class="form-control" placeholder="비밀번호" required> </div>
                <input type="submit" class="btn btn-primary" value="Login">
              </form>
              <br>
              <p>아직도 캣메이트 계정이 없으세요?&nbsp;
              <a href="${pageContext.request.contextPath }/start/signup"><b class="mb-3 text-primary">가입하기</b></a>
              </p>
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>
</body>
</html>