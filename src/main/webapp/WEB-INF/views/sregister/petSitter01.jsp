<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>캣페이트 펫시터 등록</title>
  <jsp:include page="../set/setLink/link.jsp" flush="false"/>
  <link href="${pageContext.request.contextPath }/resources/petSitter/css/common.css" rel="stylesheet"/>
  <script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
</head>
<body>
  <jsp:include page="../set/setLayout/navigation.jsp" flush="false"/>
  <section class="py-5">
    <div class="container">
      <div class="row">
      <div class="col-md-3"> </div>
        <div class="col-md-6">
          <div class="card text-dark p-5 bg-default">
          <h3>
            <b>지헌님 안녕하세요!
              <br> 회원님의 펫시터 등록을 도와드리겠습니다. </b>
          </h3>
          <br>
          <dl>
            <dt>
              <h4 class="text-primary">펫시터 제목</h4>
            </dt>
            <dd>
              <input type="text" class="form-control" placeholder="제목">
            </dd>
          </dl>
          <dl>
            <dt>
              <h4 class="text-primary">주소</h4>
            </dt>
            <dd>
              <input type="text" id="sample6_postcode" class="form-control" onclick="sample6_execDaumPostcode()" placeholder="우편번호">
              <br>
              <input type="text" id="sample6_address" class="form-control" onclick="sample6_execDaumPostcode()" placeholder="주소">
              <br>
              <input type="text" id="sample6_address2" class="form-control" placeholder="상세주소"> </dd>
          </dl>
          <br>
          <div class="row" id="buttonRow">
            <div class="col-md-12">
              <input type="submit" class="btn btn-primary" value="다음" id="next"> 
            </div>
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