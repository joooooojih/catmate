<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <title>캣메이트 펫시터</title>
  <jsp:include page="./set/setLink/link.jsp" flush="false"/>

</head>

<body id="page-top">
  <jsp:include page="./set/setLayout/navigation.jsp" flush="false"/>

  <header class="masthead text-center text-white d-flex" id="top">
    <div class="container my-auto">
      <div class="row">
        <div class="col-lg-10 mx-auto">
          <h1 class="text-uppercase">
            <strong>내 반려견을 사랑하는 방법<br>캣메이트</strong>
          </h1>
          <hr> </div>
        <div class="col-lg-8 mx-auto">
          <p class="text-faded mb-5">답답한 케이지가 아닌 펫시터의 집에서 당신의 강아지만 돌봐드립니다.<br>
		    	애견호텔 보다 이젠 캣메이트에 맡기세요!
		      </p>
          <a class="btn btn-primary btn-xl js-scroll-trigger" href="${pageContext.request.contextPath }/reserve/search">펫시터 집에 맡기기</a>&nbsp;&nbsp;&nbsp;&nbsp;
          <a class="btn btn-primary btn-xl js-scroll-trigger" href="${pageContext.request.contextPath }/sregister/pet_sitter01">펫시터 등록하기</a>
        </div>
      </div>
    </div>
  </header>
  <section class="py-5 bg-light" >
    <div class="container">
    <div class="row">
        <div class="col-lg-12 text-center">
          <h2 class="section-heading">What Catmate?</h2>
          <hr class="my-4"> </div>
      </div>
      <div class="row">
        <div class="py-5 col-md-6">
          <div class="row">
            <div class="text-center col-4">
              <img src="${pageContext.request.contextPath }/resources/start/img/main_catmate1.png">
            </div>
            <div class="col-8">
              <h5 class="mb-3 text-primary">
                <b>엄선된 펫시터</b>
              </h5>
              <p class="my-1">펫메이트에서 직접 검증한 내 주변의 믿을 수 있는 펫시터를 만나보세요! </p>
            </div>
          </div>
        </div>
        <div class="py-5 col-md-6">
          <div class="row">
            <div class="text-center col-4">
              <img src="${pageContext.request.contextPath }/resources/start/img/main_catmate2.png">
            </div>
            <div class="col-8">
              <h5 class="mb-3 text-primary">
                <b>오직 한마리!</b>
              </h5>
              <p class="my-1">한가정의 강아지만 맡기 떄문에 내 반려견을 위한 맞춤 돌봄을 경험해보세요!</p>
            </div>
          </div>
        </div>
      </div>
      <div class="row">
        <div class="py-5 col-md-6">
          <div class="row">
            <div class="text-center col-4">
              <img src="${pageContext.request.contextPath }/resources/start/img/main_catmate3.png">
            </div>
            <div class="col-8">
              <h5 class="mb-3 text-primary">
                <b>안심 서비스</b>
              </h5>
              <p class="my-1">1일 1회 산책은 기본! 떨어져 있어도 걱정 No! 사진 전송 서비스로 내 반려견을 확인할 수 있습니다.</p>
            </div>
          </div>
        </div>
        <div class="py-5 col-md-6">
          <div class="row">
            <div class="text-center col-4">
              <img src="${pageContext.request.contextPath }/resources/start/img/main_catmate4.png">
            </div>
            <div class="col-8">
              <h5 class="mb-3 text-primary">
                <b>돌봄일지</b>
              </h5>
              <p class="my-1">내가 없는 동안 반려견이 어떻게 지냈는지 알 수 있도록 매일 매일 돌봄일지를 작성합니다.</p>
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>
  <section class="bg-primary" >
    <div class="container text-center">
      <h2 class="section-heading text-white">믿을 수 있는 펫시터의 집에서 반려견을 돌봐드립니다.</h2>
      <a class="nav-link js-scroll-trigger" href="#top"><input type="button" class="btn btn-light btn-xl sr-button" value="시작하기!"></a>
    </div>
  </section>
  
  <jsp:include page="./set/setLayout/footer.jsp" flush="false"/>
</body>

</html>