package com.catmate.Interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.catmate.reserve.service.ReserveService;

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
            Map<String, Object> map = reserveService.getSitter_detail(idx);
            request.setAttribute("pet_sitter_house", map.get("pet_sitter_house"));
            request.setAttribute("room_photoList", map.get("room_photo"));
        }
        return true;
    }
}