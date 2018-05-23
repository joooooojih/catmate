<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
          <div class="card p-2 bg-default">
            <div class="row">
              <div class="col-md-4">
                <span><a href="${pageContext.request.contextPath }/mypage/account/account_info" id="account_button" class="btn btn-light">개인정보 변경</a></span>
              </div>
              <div class="col-md-4">
                <span><a href="${pageContext.request.contextPath }/mypage/account/account_pass" id="account_button" class="btn btn-light">비밀번호 변경</a></span>
              </div>
              <div class="col-md-4">
                <span><a href="${pageContext.request.contextPath }/mypage/account/account_breakup" id="account_button" class="btn btn-light">회원탈퇴</a></span>
              </div>
            </div>
          </div>
          <br>