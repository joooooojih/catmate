<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>캣메이트 강아지 정보 관리</title>
  <jsp:include page="../../set/setLink/link.jsp" flush="false"/>
  <link href="${pageContext.request.contextPath }/resources/css/mypage_common.css" rel="stylesheet"/>
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
            <h4 class="mb-4 text-warning text-center">강아지 정보 관리</h4>
              <div class="row text-right">
                <div class="col-md-12">
                  <b><a href="${pageContext.request.contextPath }/mypage/pet_profile/pet_profile_reg">+ 반려견 추가하기</a></b>
                </div>
              </div>
              <br><br>
              <table class="table text-center" id="mypage_table">
                <tr>
                  <th>사진</th>
                  <th>이름</th>
                  <th>성별</th>
                  <th>품종</th>
                </tr>
                
                <c:forEach var="pet_profile" items="${pet_profileList }">
                  <tr>
                    <td>
                      <a href="${pageContext.request.contextPath }/mypage/pet_profile/pet_profile_reg?idx=${pet_profile.idx }">
                        <img class="rounded-circle" src="${pageContext.request.contextPath }/resources/img/dog_img/${pet_profile.pet_photo }" id="pet_profile_img">
                      </a>
                    </td>
                    <td>${pet_profile.pet_name }</td>
                    <td>${pet_profile.pet_gender }</td>
                    <td>${pet_profile.pet_kind }</td>
                  </tr>
                </c:forEach>
                  
              </table>
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>
  <jsp:include page="../../set/setLayout/footer.jsp" flush="false"/>
</body>
</html>