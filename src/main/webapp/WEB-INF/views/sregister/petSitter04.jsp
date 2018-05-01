<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>캣메이트 펫 시터 등록</title>
  <jsp:include page="../set/setLink/link.jsp" flush="false"/>
  <link href="${pageContext.request.contextPath }/resources/petSitter/css/common.css" rel="stylesheet"/>
  <link href="${pageContext.request.contextPath }/resources/reserve/css/sitter_detail.css" rel="stylesheet"/>
  <script>
    function readURL(input) {
        
        if (input.files && input.files[0]) {
            var reader = new FileReader();
     
            reader.onload = function (e) {
                $(input).next().attr("src", e.target.result);
            }
     
            reader.readAsDataURL(input.files[0]);
        }
    }
    
    function filePreview(input) {
        readURL(input);
     }
  </script>
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
              <b>사진을 등록해주세요! </b>
            </h3>
            <br>
            <dl>
              <c:forEach begin="1" end="5" var="i">
                <dt>
                <h4 class="text-primary">${i } 번째 사진 등록</h4>
                </dt>
                <dd>
                  <input type="file" onchange="javascript:filePreview(this)">
                  <img id="img0${i }" src="${pageContext.request.contextPath }/resources/icon/home_icon.png">
                </dd>
              </c:forEach>
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