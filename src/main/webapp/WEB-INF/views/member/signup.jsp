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
	
	var submit_switch = false;
	
  $("#certification").click(function() {
	  alert("인증 번호를 보내는 중입니다.");
	  $("#certificationDiv2").remove();
	  
    $.ajax({
    	
      type: "get",
      url: "signupEmail",
      data: {
        user_email: $("#user_email").val()
      },
      dataType: "text",
      
      success: function(responseData, status, xhr) {
    	  if(responseData == "existence") { // 이미 존재하는 아이디
    		  alert("다른 이메일을 사용해주세요.");
    	  } else {
    		  alert("인증번호가 전송되었습니다.")
    		  
    		  // 인증번호 input 생성
    		  $("#certificationDiv").after(
    				         '<div class="input-group" id="certificationDiv2">'+
    		                '<input type="text" id="certification_number" class="form-control" placeholder="인증번호 5자 입력" required maxlength="5">'+
    		                '<div class="input-group-append">'+
    		                  '<input type="button" id="certification_check" class="btn btn-warning text-light" value="확인">'+
    		                '</div>'+
    		             '</div>');
    		  
    		  $("#certification_check").click(function() { // 인증번호 확인
    			  if(responseData == $("#certification_number").val()) {
    			     submit_switch = true;
    			     alert("인증되었습니다.");
    			   } else {
    				   submit_switch = false;
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
	    submit_switch = false;
	  });
  
  $("#signup_form").submit(function() { // 인증 해야 submit 가능
	  if(submit_switch) {
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
                <div class="form-group">
                  <div class="col-md-12">
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
                  </div>
                  <div class="col-md-6">
                    <input type="text" id="sample6_postcode" class="form-control" onclick="sample6_execDaumPostcode()" placeholder="우편번호" required name="user_zip_code"><br>
                  </div>
                  <div class="col-md-12">
                    <input type="text" id="sample6_address" class="form-control" onclick="sample6_execDaumPostcode()" placeholder="주소" required name="user_address"><br>
                    <input type="text" id="sample6_address2" class="form-control" placeholder="상세주소" name="user_daddress"><br>
                  </div>
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
</body>
</html>