package com.catmate.interceptor;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.catmate.reserve.service.ReserveService;

@Component
public class Reserve_inter extends HandlerInterceptorAdapter {

    @Autowired
    ReserveService reserveService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        List<String> area_textList = new ArrayList<String>();
        area_textList.add("전체");
        area_textList.add("서울");
        area_textList.add("인천");
        area_textList.add("경기");
        area_textList.add("부산");
        area_textList.add("세종");
        area_textList.add("대전");
        area_textList.add("대구");
        area_textList.add("강원");

        List<Integer> area_countList = new ArrayList<Integer>();
        for(String area_text : area_textList) {
            if(area_text.equals("전체")) {
                area_countList.add(reserveService.getAreaAllCount());
            } else {
                area_countList.add(reserveService.getAreaCount(area_text));
            }
        }

        request.setAttribute("area_text", area_textList);
        request.setAttribute("area_count", area_countList);
        return true;
    }
}