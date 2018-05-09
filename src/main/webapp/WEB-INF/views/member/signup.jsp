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
  <script>
$(document).ready(function() {
	
	var submit_check = false;
	
  $("#certification").click(function() {
	  alert("잠시만 기다려주세요.");
    $.ajax({
    	
      type: "get",
      url: "signupEmail",
      data: {
        user_email: $("#user_email").val()
      },
      dataType: "text",
      
      success: function(responseData, status, xhr) {
    	  $("#certificationDiv2").remove();
    	  
    	  if(responseData == "existence") {
    		  alert("다른 이메일을 사용해주세요.");
    	  } else {
    		  alert("인증번호가 전송되었습니다.")
    		  
    		  $("#certificationDiv").after().append(
    		            '<div class="input-group" id="certificationDiv2">'+
    		                '<input type="text" id="certification_number" class="form-control" placeholder="인증번호 5자 입력" required maxlength="5">'+
    		                '<div class="input-group-append">'+
    		                  '<input type="button" id="certification_check" class="btn btn-warning text-light" value="확인">'+
    		                '</div>'+
    		             '</div>');
    		  
    		  $("#certification_check").click(function() {
    			  if(responseData == $("#certification_number").val()) {
    			     submit_check = true;
    			     alert("인증되었습니다.");
    			   } else {
    				   submit_check = false;
    				   alert("인증 번호가 다릅니다.");
    			   }
    			});
    		  
    	  }
      },
      
      error: function(xhr, status, error) {
        alert("다른 이메일을 사용해주세요.");
      }
    });
  });
  
  $("#user_email").keyup(function() { // 다른 이메일 입력 시 인증 다시 받게 하기 위함
	   $("#certificationDiv2").remove();
	    submit_check = false;
	  });
  
  $("#signup_form").submit(function() { // 인증 해야 submit 가능
	  if(submit_check) {
		  return true;
		  
	  } else {
		  alert("이메일 인증을 해주세요.")
		  return false;
	  }
  });
  
});
</script>
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
              <form action="signup" method="post" id="signup_form">
                <div class="form-group" >
                  <div class="input-group" id="certificationDiv">
                    <input type="email" id="user_email" class="form-control" placeholder="이메일" required name="user_email">
                      <div class="input-group-append">
                       <input type="button" id="certification" class="btn btn-warning text-light" value="인증">
                      </div>
                  </div>
                  <br>
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