<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>캣페이트 펫시터 등록</title>
  <jsp:include page="../set/setLink/link.jsp" flush="false"/>
  <link href="${pageContext.request.contextPath }/resources/css/pet_sitter_common.css" rel="stylesheet"/>
  <script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
</head>
<body>
  <jsp:include page="../set/setLayout/navigation.jsp" flush="false"/>
  <section class="py-5">
    <div class="container">
      <div class="row">
      <div class="col-md-2"> </div>
        <div class="col-md-8">
          <div class="card text-dark p-5 bg-default">
          <h3>
            <b>${user_profile.user_name }님 안녕하세요!
              <br> 회원님의 펫시터 등록을 도와드리겠습니다. </b>
          </h3>
          <br>
          <form action="pet_sitter01" method="post">
          <dl>
            <dt>
              <h4 class="text-primary">펫시터 제목</h4>
            </dt>
            <dd>
              <div class="col-md-12">
                <input type="text" name="house_title" class="form-control" placeholder="제목" value="${pet_sitter_house.house_title }" required>
              </div>
            </dd>
          </dl>
          <dl>
            <dt>
              <h4 class="text-primary">주소</h4>
            </dt>
            <dd>
              <div class="col-md-6">
                <input type="text" name="house_zip_code" id="sample6_postcode" class="form-control" onclick="sample6_execDaumPostcode()" placeholder="우편번호" value="${pet_sitter_house.house_zip_code }" required><br>
              </div>
              <div class="col-md-12">
                <input type="text" name="house_address" id="sample6_address" class="form-control" onclick="sample6_execDaumPostcode()" placeholder="주소" value="${pet_sitter_house.house_address }" required><br>
                <input type="text" name="house_daddress" id="sample6_address2" class="form-control" placeholder="상세주소" value="${pet_sitter_house.house_daddress }"> 
              </div>
            </dd>
          </dl>
          <br>
          <div class="row" id="buttonRow">
            <div class="col-md-12">
              <input type="submit" class="btn btn-primary" value="다음"> 
            </div>
          </div>
          </form>
        </div>
      </div>
    </div>
    </div>
  </section>
  <jsp:include page="../set/setLayout/footer.jsp" flush="false"/>
</body>
</html>