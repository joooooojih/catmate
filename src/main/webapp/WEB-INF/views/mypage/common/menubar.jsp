<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
          <ul class="list-group text-center">
            <li class="list-group-item d-flex justify-content-between align-items-center">
              <dl class="col-md-12 text-secondary">
                <dt>
                  <img class="img-fluid d-block rounded-circle mx-auto" id="profile_img" src="${pageContext.request.contextPath }/resources/img/user_img/${user_profile.user_photo }">
                </dt>
                <dd>
                  <br>
                  <h4>조지헌</h4>
                </dd>
              </dl>
            </li>
            <li class="list-group-item d-flex justify-content-between align-items-center"> 
              <h5><a class="text-secondary" href="#">메시지</a></h5>
              <span class="badge badge-light badge-pill">2</span>
            </li>
            <li class="list-group-item d-flex justify-content-between align-items-center">
              <h5><a class="text-secondary" href="${pageContext.request.contextPath }/mypage/wish_list">위시리스트</a></h5>
            </li>
            <li class="list-group-item d-flex justify-content-between align-items-center">
              <h5><a class="text-secondary" href="${pageContext.request.contextPath }/mypage/reservation_history/to_sitter">예약내역</a></h5>
            </li>
            <li class="list-group-item d-flex justify-content-between align-items-center">
              <h5><a class="text-secondary" href="${pageContext.request.contextPath }/mypage/pet_sitter_house_update">펫시터 수정</a></h5>
            </li>
            <li class="list-group-item d-flex justify-content-between align-items-center">
              <h5><a class="text-secondary" href="${pageContext.request.contextPath }/mypage/pet_profile/pet_profile">강아지 정보 관리</a></h5>
            </li>
            <li class="list-group-item d-flex justify-content-between align-items-center">
              <h5><a class="text-secondary" href="${pageContext.request.contextPath }/mypage/account/account_check">개인정보 변경</a></h5>
            </li>
          </ul>