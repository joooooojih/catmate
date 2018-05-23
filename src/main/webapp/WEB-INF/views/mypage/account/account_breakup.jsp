<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>캣메이트 계정 관리 : 회원탈퇴</title>
  <jsp:include page="../../set/setLink/link.jsp" flush="false"/>
  <link href="${pageContext.request.contextPath }/resources/css/mypage_common.css" rel="stylesheet"/>
  
  <script>
  $(document).ready(function() {
	  
	  $("#breakup_form").submit(function() {
		  if($("#check").prop("checked")) {
			  return true;
	   } else {
		   alert('동의서에 체크해 주세요');
		   return false;
	   }
	  });
  });
  </script>
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
          <div class="card text-secondary p-5 bg-default">
            <div class="card-body">
              <h4 class="mb-4 text-warning">회원탈퇴</h4>
              <p>
                <b>${user_profile.user_name }</b>님  돌보미의 경우 보유하고 계신 수익금과 관련된 이력이 모두 삭제되며 진행중인 의뢰에 대해서는 취소 또는 완료가 된 후에 탈퇴해야 합니다.
                <br>이를 지키지 않아 발생하는 문제에 대한 책임은 회원 본인에게 있으니 신중하게 결정해 주시기 바랍니다. 
              </p>
              <br>
              <label class="checkbox-inline">
                <input type="checkbox" id="check">&nbsp;안내 사항을 모두 확인하였으며, 이에 동의합니다.
              </label>
              <br><br>
              <form action="account_breakup" method="post" id="breakup_form">
                <input type="submit" id="submit" class="btn btn-warning text-white" value="회원탈퇴">
              </form>
              <br>
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>
  <jsp:include page="../../set/setLayout/footer.jsp" flush="false"/>
</body>
</html>