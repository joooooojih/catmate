package com.catmate.Interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class Account_inter extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try {
            if(request.getSession().getAttribute("pass_check") == null ){
                    response.sendRedirect("/catmate/mypage/account/account_check");
                    return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
}
