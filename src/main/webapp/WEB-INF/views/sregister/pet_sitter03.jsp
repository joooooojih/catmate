<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>캣메이트 펫 시터 등록</title>
  <jsp:include page="../set/setLink/link.jsp" flush="false"/>
  <link href="${pageContext.request.contextPath }/resources/css/pet_sitter_common.css" rel="stylesheet"/>
  <script>
    $(document).ready(function() { // 이전
	    $("#previous").click(function() {
	    	$(location).attr("href", "pet_sitter02");
	    })
	  });
  
    $(document).ready(function() {
	  
	    $("#care_size").val("${pet_sitter_house.care_size}");
	    $("#pick_up").val("${pet_sitter_house.pick_up}");
	    $("#yard").val("${pet_sitter_house.yard}");
	    $("#children_under_14").val("${pet_sitter_house.children_under_14}");
	    $("#live_together").val("${pet_sitter_house.live_together}");
	    $("#other_animal").val("${pet_sitter_house.other_animal}");
	  
	    $("input[id^=care_age_value]").each(function() { // care_age checked
	  	  $("input:checkbox[id^=care_age]").filter("input[value="+this.value+"]").prop("checked", true);
	    });
	  
	    $("input[id^=tag_value]").each(function(index) { // tag checked
	  	  if(this.value == "yes") {
	  		  $("input:checkbox[id^=tag]").eq(index).prop("checked", true);
	  	  }
	    });
    });
  
  </script>
</head>
<body>
  <jsp:include page="../set/setLayout/navigation.jsp" flush="false"/>
  <section class="py-5">
    <div class="container">
      <div class="row" >
        <div class="col-md-2"> </div>
        <div class="col-md-8">
          <div class="card text-dark p-5 bg-default">
            <h3>
              <b>돌봄 환경을 입력해주세요.</b>
            </h3>
            <br>
              <input type="hidden" id="tag_value" value="${pet_sitter_house.patient_dog }">
              <input type="hidden" id="tag_value" value="${pet_sitter_house.license }">
              <input type="hidden" id="tag_value" value="${pet_sitter_house.outdoor_bowels }">
              <input type="hidden" id="tag_value" value="${pet_sitter_house.homemade_snacks }">
              <input type="hidden" id="tag_value" value="${pet_sitter_house.first_aid }">
              <input type="hidden" id="tag_value" value="${pet_sitter_house.medication_possible }">
              <c:forEach var="care_age_values" items="${care_age }">
                <input type="hidden" id="care_age_value" value="${care_age_values}">
              </c:forEach>
            <form action="pet_sitter03" method="post">
            <dl>
              <dt>
                <h4 class="text-primary">돌봄 가능한 크기</h4>
              </dt>
              <dd>
                <div class="col-md-12">
                  <select class="form-control" name="care_size" id="care_size" >
                    <option value="5">소형견(0~4.9kg) 케어 가능합니다.</option>
                    <option value="15">중형견(0~14.9kg) 케어 가능합니다.</option>
                    <option value="16">대형견(모든 무게) 케어 가능합니다.</option>
                  </select>
                </div>
              </dd>
            </dl>
            <dl>
              <dt>
                <h4 class="text-primary">돌봄 가능한 나이</h4>
              </dt>
              <dd>
                <div class="btn-group" data-toggle="buttons">
                  <label class="btn btn-warning text-light">강아지(1살 이하)
                    <input type="checkbox" name="care_age" id="care_age" autocomplete="off" value="강아지" required> </label>&nbsp;
                  <label class="btn btn-warning text-light">성견(2~6살)
                    <input type="checkbox" name="care_age" id="care_age" autocomplete="off" value="성견"> </label>&nbsp;
                  <label class="btn btn-warning text-light">노령견(7살 이상)
                    <input type="checkbox" name="care_age" id="care_age" autocomplete="off" value="노령견"> </label>
                </div>
              </dd>
              <dt>
                <h4 class="text-primary">돌봄 환경</h4>
              </dt>
              <dd>
                <div class="row">
                  <div class="col-md-12">
                    <input type="text" name="care_space" id="care_space" class="form-control" placeholder="돌봄 공간" value="${pet_sitter_house.care_space }"> 
                  </div>
                </div>
                <br>
                <div class="row">
                  <div class="col-md-12">
                    <c:choose>
                      <c:when test="${pet_sitter_house.subway_station eq '없습니다.'}">
                        <input type="text" name="subway_station" id="subway_station" class="form-control" placeholder="인근 지하철 역">
                      </c:when>
                      <c:otherwise>
                        <input type="text" name="subway_station" id="subway_station" class="form-control" placeholder="인근 지하철 역" value="${pet_sitter_house.subway_station }">
                      </c:otherwise>
                    </c:choose>
                  </div>
                </div>
                <br>
                <div class="row">
                  <div class="col-md-12">
                    <select class="form-control" name="pick_up" id="pick_up">
                      <option value="no">픽업은 안됩니다.</option>
                      <option value="yes">픽업 가능합니다.</option>
                    </select>
                  </div>
                </div>
                <br>
                <div class="row">
                  <div class="col-md-12">
                    <select class="form-control" name="yard" id="yard">
                      <option value="없습니다." selected>마당 없습니다.</option>
                      <option value="있습니다.">마당 있습니다.</option>
                    </select>
                  </div>
                </div>
                <br>
                <div class="row">
                  <div class="col-md-12">
                    <select class="form-control" name="children_under_14" id="children_under_14">
                      <option value="없습니다.">14세 미만 아동이 없습니다</option>
                      <option value="있습니다.">14세 미만 아동이 있습니다.</option>
                    </select>
                  </div>
                </div>
                <br>
                <div class="row">
                  <div class="col-md-12">
                    <select class="form-control" name="live_together" id="live_together">
                      <option value="혼자 살고 있습니다.">혼자 살고 있습니다.</option>
                      <option value="가족과 함께 살고 있습니다.">가족과 함께 살고 있습니다.</option>
                    </select>
                  </div>
                </div>
                <br>
                <div class="row">
                  <div class="col-md-12">
                    <select class="form-control" name="other_animal" id="other_animal">
                      <option value="다른 동물은 없습니다.">다른 동물은 없습니다.</option>
                      <option value="다른 동물이 있습니다.">다른 동물이 있습니다.</option>
                    </select>
                  </div>
                </div>
              </dd>
            </dl>
            <dl>
              <dt>
                <h4 class="text-primary">기타 태그</h4>
              </dt>
              <dd>
                <div class="row">
                  <div class="btn-group" data-toggle="buttons">
                    <div class="col-md-12">
                      <label class="btn btn-warning text-light">환자견 케어
                        <input type="checkbox" autocomplete="off" name="patient_dog" id="tag" value="yes"> </label>&nbsp;
                      <label class="btn btn-warning text-light">자격증 보유
                        <input type="checkbox" autocomplete="off" name="license" id="tag" value="yes"> </label>&nbsp;
                      <label class="btn btn-warning text-light">실외 배변
                        <input type="checkbox" autocomplete="off" name="outdoor_bowels" id="tag" value="yes"> </label>&nbsp;
                        <label class="btn btn-warning text-light">수제간식
                        <input type="checkbox" autocomplete="off" name="homemade_snacks" id="tag" value="yes"> </label>&nbsp;
                      <label class="btn btn-warning text-light">응급처치
                        <input type="checkbox" autocomplete="off" name="first_aid" id="tag" value="yes"> </label>&nbsp;
                      <label class="btn btn-warning text-light">투약가능
                        <input type="checkbox" autocomplete="off" name="medication_possible" id="tag" value="yes"> </label>&nbsp;
                    </div>
                  </div>
                </div>
              </dd>
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