package com.catmate.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.catmate.reserve.service.ReserveService;

@Component
public class Sregister_inter extends HandlerInterceptorAdapter {

    @Autowired
    ReserveService reserveService;
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        
        int idx = 0;
        try {
            idx = (Integer) request.getSession().getAttribute("idx");
        } catch(Exception e) {
            request.getSession().setAttribute("idx", 0);
        }
        if(idx != 0) {
            request.setAttribute("pet_sitter_house", reserveService.getPet_sitter_house(idx));
            request.setAttribute("room_photoList", reserveService.getRoom_photo(idx));
        }
        return true;
    }
}