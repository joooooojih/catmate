<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>캣메이트 펫 시터 등록</title>
  <jsp:include page="../set/setLink/link.jsp" flush="false"/>
  <link href="${pageContext.request.contextPath }/resources/pet_sitter/css/common.css" rel="stylesheet"/>
</head>
<body>
  <jsp:include page="../set/setLayout/navigation.jsp" flush="false"/>
  <section class="py-5">
    <div class="container">
      <div class="row" >
        <div class="col-md-3"> </div>
        <div class="col-md-6">
          <div class="card text-dark p-5 bg-default">
            <h3>
              <b>돌봄 환경을 입력해주세요.</b>
            </h3>
            <br>
            <dl>
              <dt>
                <h4 class="text-primary">돌봄 가능한 크기</h4>
              </dt>
              <dd>
                <div class="btn-group" data-toggle="buttons">
                  <label class="btn btn-warning text-light">소 (0~4.9kg)
                    <input type="checkbox" autocomplete="off"> </label>&nbsp;
                  <label class="btn btn-warning text-light">중 (5~14.9kg)
                    <input type="checkbox" autocomplete="off"> </label>&nbsp;
                  <label class="btn btn-warning text-light">대 (15kg 이상)
                    <input type="checkbox" autocomplete="off"> </label>
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
                    <input type="checkbox" autocomplete="off"> </label>&nbsp;
                  <label class="btn btn-warning text-light">성견(2~6살)
                    <input type="checkbox" autocomplete="off"> </label>&nbsp;
                  <label class="btn btn-warning text-light">노령견(7살 이상)
                    <input type="checkbox" autocomplete="off"> </label>
                </div>
              </dd>
              <dt>
                <h4 class="text-primary">돌봄 환경</h4>
              </dt>
              <dd>
                <div class="row">
                  <div class="col-md-12">
                    <input type="text" class="form-control" placeholder="돌봄 공간"> 
                  </div>
                </div>
                <br>
                <div class="row">
                  <div class="col-md-12">
                    <input type="text" class="form-control" placeholder="인근 지하철 역"> 
                  </div>
                </div>
                <br>
                <div class="row">
                  <div class="col-md-12">
                    <select class="form-control">
                      <option>픽업 가능합니다.</option>
                      <option>픽업은 안됩니다.</option>
                    </select>
                  </div>
                </div>
                <br>
                <div class="row">
                  <div class="col-md-12">
                    <select class="form-control">
                      <option>마당 있습니다.</option>
                      <option>마당 없습니다.</option>
                    </select>
                  </div>
                </div>
                <br>
                <div class="row">
                  <div class="col-md-12">
                    <select class="form-control">
                      <option>14세 미만 아동이 있습니다.</option>
                      <option>14세 미만 아동이 없습니다</option>
                    </select>
                  </div>
                </div>
                <br>
                <div class="row">
                  <div class="col-md-12">
                    <select class="form-control">
                      <option>가족과 함께 살고 있습니다.</option>
                      <option>혼자 살고 있습니다.</option>
                    </select>
                  </div>
                </div>
                <br>
                <div class="row">
                  <div class="col-md-12">
                    <select class="form-control">
                      <option>다른 동물이 있습니다.</option>
                      <option>다른 동물은 없습니다.</option>
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
                        <input type="checkbox" autocomplete="off"> </label>&nbsp;
                      <label class="btn btn-warning text-light">자격증 보유
                        <input type="checkbox" autocomplete="off"> </label>&nbsp;
                      <label class="btn btn-warning text-light">실외 배변
                        <input type="checkbox" autocomplete="off"> </label>&nbsp;
                        <label class="btn btn-warning text-light">수제간식
                        <input type="checkbox" autocomplete="off"> </label>&nbsp;
                      <label class="btn btn-warning text-light">응급처치
                        <input type="checkbox" autocomplete="off"> </label>&nbsp;
                      <label class="btn btn-warning text-light">투약가능
                        <input type="checkbox" autocomplete="off"> </label>&nbsp;
                    </div>
                  </div>
                </div>
              </dd>
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