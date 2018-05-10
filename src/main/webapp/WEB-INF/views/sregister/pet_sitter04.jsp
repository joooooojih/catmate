<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>캣메이트 펫 시터 등록</title>
  <jsp:include page="../set/setLink/link.jsp" flush="false"/>
  <link href="${pageContext.request.contextPath }/resources/pet_sitter/css/common.css" rel="stylesheet"/>
  <link href="${pageContext.request.contextPath }/resources/reserve/css/sitter_detail.css" rel="stylesheet"/>
  <style>
    .filebox label { 
      display: inline-block; 
      padding: .5em .75em; 
      color: #999; font-size: inherit; 
      line-height: normal; 
      vertical-align: middle; 
      background-color: #fdfdfd; 
      cursor: pointer; 
      border: 1px solid #ebebeb; 
      border-bottom-color: #e2e2e2; 
      border-radius: .25em; 
    } 
    input[type="file"] { /* 파일 필드 숨기기 */ 
      position: absolute; 
      width: 1px; 
      height: 1px; 
      padding: 0; 
      margin: -1px; 
      overflow: hidden; 
      clip:rect(0,0,0,0); 
      border: 0; 
    }
  </style>
  <script>
    $(document).ready(function() { // 이전
      $("#previous").click(function() {
        location.href = "pet_sitter03";
      })
    });
    
    $(document).ready(function() {
    	$("#pet_sitter04_form").submit(function() {
    		 var submit_switch = true;
    		 if(!${submit_check}) {  // 처음이면
    			 $("input[type=file]").each(function(index) {  // 파일 5개 다 올려야 가능
                if($("#file" + index).val() == "") {
                  submit_switch = false;
               }
           });
    		 }
    		if(!submit_switch) {
    			alert("5개 전부 올려주세요.");
    		} 
    		return submit_switch; 
    	});
    });
    
    $(document).ready(function() {  // 이미 파일을 한 번 올린 경우 이미지 셋팅
    	if(${submit_check}) {
    		for(var i = 0; i < 5; i++) {
    			$("#file"+i).next().prop("src", "${pageContext.request.contextPath }/resources/pet_sitter/img/"+$("input[type=hidden]").eq(i).val());
    		}
    	}
    });
    
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
            <c:if test="${submit_check }">
              <c:forEach var="room_photo_value" items="${room_photo }">
                <input type="hidden" value="${room_photo_value.room_photo }">
              </c:forEach>
            </c:if>
            <br>
            <form action="pet_sitter04" method="post" enctype="multipart/form-data" id="pet_sitter04_form">
            <dl>
              <c:forEach begin="0" end="4" var="i">
                <dt>
                <h4 class="text-primary">${i+1 } 번째 사진 등록</h4>
                </dt>
                <dd>
                  <div class="filebox"> 
                    <label for="file${i }">사진 업로드</label> 
                  </div>
                  <input type="file" id="file${i }" name="uploadFile" onchange="javascript:filePreview(this)"> 
                  <img src="${pageContext.request.contextPath }/resources/icon/home_icon.png">
                </dd>
              </c:forEach>
            </dl>
            <br>
            <div class="row" id="buttonRow">
              <div class="col-md-6">
                <input type="button" id="previous" class="btn btn-primary" value="이전">
              </div>
              <div class="col-md-6">
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