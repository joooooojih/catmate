package com.catmate.Interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class Login_inter extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try {
            if(request.getSession().getAttribute("user_profile") == null ){
                    response.sendRedirect("/catmate/member/login");
                    return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
}
