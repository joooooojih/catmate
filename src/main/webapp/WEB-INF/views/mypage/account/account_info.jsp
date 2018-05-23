<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>캣메이트 계정 관리 : 정보 변경</title>
  <jsp:include page="../../set/setLink/link.jsp" flush="false"/>
  <link href="${pageContext.request.contextPath }/resources/css/mypage_common.css" rel="stylesheet"/>
  <link href="${pageContext.request.contextPath }/resources/css/file.css" rel="stylesheet"/>
  <script src="${pageContext.request.contextPath}/resources/js/file.js"></script>
  <script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
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
          <div class="card p-5 bg-default">
            <div class="card-body">
              <form action="account_info" method="post" enctype="multipart/form-data">
                <div class="form-group">
                  <div class="col-md-12">
                    <div class="text-center">
                      <div class="filebox"> 
                        <label for="mypage_photo">사진 업로드</label> 
                      </div>
                      <input type="file" id="mypage_photo" name="uploadFile" onchange="javascript:filePreview(this)"> 
                      <img class="rounded-circle" src="${pageContext.request.contextPath }/resources/img/user_img/${user_profile.user_photo}" id="mypage_photo_img">
                    </div>
                  
                    <b class="text-secondary">이메일</b>
                    <div class="input-group">
                      <input type="email" class="form-control" value="${user_profile.user_email }" disabled>
                    </div>
                    <br>
                    <b class="text-secondary">이름</b>
                    <input type="text" class="form-control" value="${user_profile.user_name }" disabled><br>
                    <b class="text-secondary">생년월일</b>
                    <input type="text" class="form-control" maxlength="8" placeholder="생년월일 8자리" value="${user_profile.user_birth }" required name="user_birth"><br>
                    <b class="text-secondary">휴대폰 번호</b>
                    <input type="text" class="form-control" maxlength="11" placeholder="휴대폰 번호 -없이 11자리 입력" value="${user_profile.user_phone }" required name="user_phone"><br>
                    <b class="text-secondary">주소</b>
                    </div>
                    <div class="col-md-6">
                      <input type="text" id="sample6_postcode" class="form-control" onclick="sample6_execDaumPostcode()" placeholder="우편번호" value="${user_profile.user_zip_code }" required name="user_zip_code"><br>
                    </div>
                    <div class="col-md-12">
                      <input type="text" id="sample6_address" class="form-control" onclick="sample6_execDaumPostcode()" placeholder="주소" value="${user_profile.user_address }" required name="user_address"><br>
                      <input type="text" id="sample6_address2" class="form-control" placeholder="상세주소" value="${user_profile.user_daddress }" name="user_daddress"><br>
                    </div>
                </div>
                <input type="submit" class="btn btn-warning text-white" value="수정" id="submit">
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>
  <jsp:include page="../../set/setLayout/footer.jsp" flush="false"/>
</body>
</html>