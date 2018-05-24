<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>캣메이트 강아지 정보 등록</title>
  <jsp:include page="../../set/setLink/link.jsp" flush="false"/>
  <link href="${pageContext.request.contextPath }/resources/css/mypage_common.css" rel="stylesheet"/>
  <link href="${pageContext.request.contextPath }/resources/css/file.css" rel="stylesheet"/>
  <script src="${pageContext.request.contextPath}/resources/js/file.js"></script>
  <script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
  <script>
    $(document).ready(function() {  // 값 셋팅
    	if(${!empty pet_profile}) {
    		$("#pet_name").val("${pet_profile.pet_name}")
    		$("#pet_gender").val("${pet_profile.pet_gender}");
    		$("#pet_kind").val("${pet_profile.pet_kind}");
    		$("#pet_weight").val("${pet_profile.pet_weight}");
    		$("#pet_birth").val("${pet_profile.pet_birth}");
    		
    		$("input:radio[name=registration]").filter("[value='${pet_profile.registration}']").prop("checked", true);
    		$("input:radio[name=neutralization]").filter("[value='${pet_profile.neutralization}']").prop("checked", true);
    		$("input:radio[name=friendly]").filter("[value='${pet_profile.friendly}']").prop("checked", true);
    		$("input:radio[name=barks]").filter("[value='${pet_profile.barks}']").prop("checked", true);
    		$("input:radio[name=disease]").filter("[value='${pet_profile.disease}']").prop("checked", true);
    		$("input:radio[name=feces]").filter("[value='${pet_profile.feces}']").prop("checked", true);
    		$("input:radio[name=mark]").filter("[value='${pet_profile.mark}']").prop("checked", true);
    		$("input:radio[name=separation_anxiety]").filter("[value='${pet_profile.separation_anxiety}']").prop("checked", true);
    		$("input:radio[name=heartworm]").filter("[value='${pet_profile.heartworm}']").prop("checked", true);
    		$("input:radio[name=vaccine]").filter("[value='${pet_profile.vaccine}']").prop("checked", true);
    		$("input:radio[name=corona_enteritis]").filter("[value='${pet_profile.corona_enteritis}']").prop("checked", true);
    		$("input:radio[name=kennelkov]").filter("[value='${pet_profile.kennelkov}']").prop("checked", true);
    		
    		$("#animal_hospital_name").val("${pet_profile.animal_hospital_name}");
        $("#animal_hospital_telephone").val("${pet_profile.animal_hospital_telephone}");
        $("#sample6_postcode").val("${pet_profile.animal_hospital_zip_code}");
        $("#sample6_address").val("${pet_profile.animal_hospital_address}");
        $("#sample6_address2").val("${pet_profile.animal_hospital_daddress}");
        
        if(${pet_profileDto.user_email != user_profile.user_email}) {
        	$("body").find("input, select, textarea").each(function() {
            $(this).prop("disabled", true);
           });
        }
    	}
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
          <div class="card text-secondary p-5 bg-default">
            <div class="card-body">
            <h4 class="mb-4 text-warning text-center">강아지 정보 등록</h4>
              <br><br>
              
              <form action="${root }" method="post" enctype="multipart/form-data">
                <c:if test="${!empty pet_profile }">
                  <input type="hidden" name="idx" value="${pet_profile.idx }">
                  <input type="hidden" name="pet_photo" value="${pet_profile.pet_photo }">
                </c:if>
                <dl class="text-center">
                  <dt>
                    <h5 class="text-primary">강아지 사진 등록</h5>
                  </dt>
                  <dd>
                    <div class="filebox"> 
                      <label for="mypage_photo">사진 업로드</label> 
                    </div>
                    <input type="file" id="mypage_photo" name="uploadFile" onchange="javascript:filePreview(this)">
                    <c:choose>
                      <c:when test="${empty pet_profile }">
                        <img class="rounded-circle" src="${pageContext.request.contextPath }/resources/img/dog_img/base.png" id="mypage_photo_img">
                      </c:when>
                      <c:otherwise>
                        <img class="rounded-circle" src="${pageContext.request.contextPath }/resources/img/dog_img/${pet_profile.pet_photo }" id="mypage_photo_img">
                      </c:otherwise>
                    </c:choose>
                    
                  </dd>
                </dl>
                <br>
                <div class="row">
                  <div class="col-md-6">
                    <input type="text" class="form-control" name="pet_name" id="pet_name" placeholder="이름" required>
                  </div>
                  <div class="col-md-6">
                    <select class="form-control" name="pet_gender" id="pet_gender">
                      <option value="남자" selected>남자</option>
                      <option value="여자">여자</option>
                    </select>
                  </div>
                </div>
                <br>
                <div class="row">
                  <div class="col-md-6">
                    <input type="text" class="form-control" name="pet_kind" id="pet_kind" placeholder="품종" required>
                  </div>
                  <div class="col-md-6">
                    <select class="form-control" name="pet_weight" id="pet_weight">
                      <option value="5" selected>0~5kg미만</option>
                      <option value="15">5~15kg미만</option>
                      <option value="16">15kg이상</option>
                    </select>
                  </div>
                </div>
                <br>
                <dl>
                  <dt>강아지 생일</dt>
                  <dd>
                    <div class="row">
                      <div class="col-md-6">
                        <input type="text" name="pet_birth" id="pet_birth" class="form-control" placeholder="생년월 6자리를 입력해주세요" maxlength="6" required>
                      </div>
                    </div>
                  </dd>
                </dl>
                <br>
                
                <dl>
                  <dt>관리 지침 및 요청 사항</dt>
                  <dd>
                    <div class="row">
                      <div class="col-md-10">
                        <textarea rows="8" class="form-control" name="requests" id="requests" maxlength="1000" placeholder="관리 지침 및 요청사항이 있다면 적어주세요" style="resize: none;">${pet_profile.requests}</textarea>
                      </div>
                    </div>
                  </dd>
                </dl>
                <br>
                
                <dl>
                  <dt>Q. 반려동물 등록을 하셨습니까?</dt>
                  <dd>
                    <br>
                    <label class="checkbox-inline">
                      <input type="radio" name="registration" value="내장형 무선식별장치 개체삽입" required>&nbsp;내장형 무선식별장치 개체삽입
                    </label>
                    <br>
                    <label class="checkbox-inline">
                      <input type="radio" name="registration" value="외장형 무선식별장치 부착">&nbsp;외장형 무선식별장치 부착
                    </label>
                    <br>
                    <label class="checkbox-inline">
                      <input type="radio" name="registration" value="등록인식표 부착">&nbsp;등록인식표 부착
                    </label>
                    <br>
                    <label class="checkbox-inline">
                      <input type="radio" name="registration" value="하지 않음">&nbsp;하지 않음
                    </label>
                  </dd>
                </dl>
                <br>
                <c:set var="i" value="${0 }"/>
                <c:forEach var="title" items="${titleList }">
                
                  <c:if test="${nameList[i] eq 'heartworm' }">
                    <b class="text-primary">!!!! 예방접종 (아래 사항 중 한 가지라도 접종(복용)이 안 될 경우 산책이 금지됩니다.)</b>
                    <br><br>
                  </c:if>
                  <dl>
                    <dt>Q. ${title }</dt>
                    <dd>
                      <br>
                      <label class="checkbox-inline">
                        <input type="radio" name="${nameList[i] }" value="yes" required>&nbsp;YES
                      </label>&nbsp;&nbsp;&nbsp;&nbsp;
                      <label class="checkbox-inline">
                        <input type="radio" name="${nameList[i] }" value="no">&nbsp;NO
                      </label>&nbsp;&nbsp;&nbsp;&nbsp;
                      <label class="checkbox-inline" id="${nameList[i] }_last_label">
                        <input type="radio" name="${nameList[i] }" value="모름">&nbsp;모름
                      </label>
                    </dd>
                  </dl>
                  <br>
                  <c:set var="i" value="${i+1 }"/>
                </c:forEach>
                
                <dl>
                  <dt class="text-primary">반려동물이 자주 다니는 동물병원을 입력해주세요.
                    <br>위급시 사용될 수 있는 중요한 정보입니다.
                  </dt>
                  <dd>
                    <br>
                    <div class="col-md-6">
                      <input type="text" class="form-control" name="animal_hospital_name" id="animal_hospital_name" placeholder="병원명" required><br>
                      <input type="text" class="form-control" name="animal_hospital_telephone" id="animal_hospital_telephone" placeholder="병원 전화번호 -없이 입력" maxlength="11" required><br>
                    </div>
                    
                    <div class="col-md-6">
                      <input type="text" id="sample6_postcode" class="form-control" name="animal_hospital_zip_code" onclick="sample6_execDaumPostcode()" placeholder="우편번호" required><br>
                    </div>
                    <div class="col-md-12">
                      <input type="text" id="sample6_address" class="form-control" name="animal_hospital_address" id="animal_hospital_address" onclick="sample6_execDaumPostcode()" placeholder="주소" required><br>
                      <input type="text" id="sample6_address2" class="form-control" name="animal_hospital_daddress" id="animal_hospital_daddress" placeholder="상세주소"><br>
                    </div>
                  </dd>
                </dl>
                <input type="submit" id="submit" class="btn btn-warning text-white" value="확인"> 
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