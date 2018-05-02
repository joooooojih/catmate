<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style>
  #join {
    width: 25em;
  }
</style>
<title>펫메이트 펫시터 회원가입</title>
  <jsp:include page="../set/setLink/link.jsp" flush="false"/>
  <script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
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
              <h1 class="mb-4 text-primary">회원가입</h1>
              <form action="signup" method="post">
                <div class="form-group" >
                  <div class="input-group">
                    <input type="email" class="form-control" placeholder="이메일" required name="user_email">
                    <div class="input-group-append">
                      <input type="button" class="btn btn-warning text-light" value="인증하기">
                    </div>
                  </div><br>
                  <input type="password" class="form-control" placeholder="비밀번호" required name="user_password"><br>
                  <input type="text" class="form-control" placeholder="이름" required name="user_name"><br>
                  <input type="text" class="form-control" placeholder="생년월일  8자리" maxlength="8" required name="user_birth"><br>
                  <input type="text" class="form-control" placeholder="휴대폰 번호 -없이 11자리 입력" maxlength="11" required name="user_phone"><br>
                  <input type="text" id="sample6_postcode" class="form-control" onclick="sample6_execDaumPostcode()" placeholder="우편번호" required name="user_zip_code"><br>
  				        <input type="text" id="sample6_address" class="form-control" onclick="sample6_execDaumPostcode()" placeholder="주소" required name="user_address"><br>
				          <input type="text" id="sample6_address2" class="form-control" placeholder="상세주소" name="user_daddress"><br>
                </div>
                <input type="submit" class="btn btn-primary" value="가입하기" id="join">
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>
  <jsp:include page="../set/setLayout/footer.jsp" flush="false"/>
  <jsp:include page="../set/setLink/script.jsp" flush="false"/>
</body>
</html>