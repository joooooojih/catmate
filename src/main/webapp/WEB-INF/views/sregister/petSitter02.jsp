<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>캣메이트 펫시터 등록</title>
  <jsp:include page="../set/setLink/link.jsp" flush="false"/>
  <link href="${pageContext.request.contextPath }/resources/petSitter/css/common.css" rel="stylesheet"/>
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
              <b>체크인, 체크아웃 시간과
                <br>금액 설정을 해주세요.</b>
            </h3>
            <br>
            <dl>
              <dt>
                <h4 class="text-primary">체크인 가능 시간</h4>
                <span class="text-secondary">
                  (ex) 08:00 ~ 23:00
                </span>
              </dt>
              <dd>
                <div class="row">
                  <div class="col-md-3">
                    <input type="text" class="form-control" placeholder="시작 시간"> </div>
                  <div class="col-md-3">
                    <input type="text" class="form-control" placeholder="시작 분"> </div>
                  <div class="col-md-3">
                    <input type="text" class="form-control" placeholder="끝 시간"> </div>
                  <div class="col-md-3">
                    <input type="text" class="form-control" placeholder="끝 분"> </div>
                </div>
              </dd>
            </dl>
            <dl>
              <dt>
                <h4 class="text-primary">체크아웃</h4>
                <span class="text-secondary">
                  (ex) 08:00 ~ 23:00
                </span>
              </dt>
              <dd>
                <div class="row">
                  <div class="col-md-3">
                    <input type="text" class="form-control" placeholder="시작 시간"> </div>
                  <div class="col-md-3">
                    <input type="text" class="form-control" placeholder="시작 분"> </div>
                  <div class="col-md-3">
                    <input type="text" class="form-control" placeholder="끝 시간"> </div>
                  <div class="col-md-3">
                    <input type="text" class="form-control" placeholder="끝 분"> </div>
                </div>
              </dd>
            </dl>
            <br>
            <dl>
              <dt>
                <h4 class="text-primary">Day Care 가격</h4>
              </dt>
              <dd>
                <input type="text" class="form-control" placeholder="Day Care 가격 (숫자만 입력)"> </dd>
              <dt>
                <h4 class="text-primary">1박 가격</h4>
              </dt>
              <dd>
                <input type="text" class="form-control" placeholder="1박 가격 (숫자만 입력)"> </dd>
              <dt>
                <h4 class=text-primary>마리당 추가 요금</h4>
              </dt>
              <dd>
                <input type="text" class="form-control" placeholder="마리당 추가 요금 (숫자만 입력)"> </dd>
            </dl>
            <br>
            <div class="row" id="buttonRow">
              <div class="col-md-6">
                <input type="button" class="btn btn-primary" value="이전" id="next" >
              </div>
              <div class="col-md-6">
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