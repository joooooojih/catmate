package com.catmate.mypage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.catmate.dto.Pet_sitter_houseDto;
import com.catmate.dto.User_profileDto;
import com.catmate.dto.Wish_listDto;
import com.catmate.mypage.service.MypageService;
import com.catmate.reserve.service.ReserveService;

@Controller
public class Wish_list {

    @Autowired
    MypageService mypageService;
    @Autowired
    ReserveService reserveService;
    
    @RequestMapping(value="/mypage/wish_list", method=RequestMethod.GET)
    public String wish_list(HttpServletRequest request, HttpSession session) {
        List<User_profileDto> user_profileList = new ArrayList<User_profileDto>();
        List<Pet_sitter_houseDto> pet_sitter_houseList = new ArrayList<Pet_sitter_houseDto>();
        List<Wish_listDto> wish_listList = mypageService.getWish_listList((User_profileDto) session.getAttribute("user_profile"));
        for(Wish_listDto wish_listDto : wish_listList) {
            pet_sitter_houseList.add(reserveService.getPet_sitter_house(wish_listDto.getIdx()));
        }
        for(Pet_sitter_houseDto pet_sitter_houseDto : pet_sitter_houseList) {
            user_profileList.add(mypageService.getUser_profile(pet_sitter_houseDto.getUser_email()));
        }
        
        request.setAttribute("user_profileList", user_profileList);
        request.setAttribute("pet_sitter_houseList", pet_sitter_houseList);
        return "mypage/wish_list";
    }
    
    @RequestMapping(value="/mypage/wish_list_reserve", method=RequestMethod.POST)
    public void wish_list_reserve(HttpServletResponse response, HttpSession session, Wish_listDto wish_listDto) throws IOException {
        
        User_profileDto user_profileDto = (User_profileDto) session.getAttribute("user_profile");
        wish_listDto.setUser_email(user_profileDto.getUser_email());
        int wish_list_count = mypageService.getWish_list(wish_listDto);
        
        if(wish_list_count == 0) { // 없으면 추가
            mypageService.insertWish_list(wish_listDto);
        } else {  // 있으면 삭제
            mypageService.deleteWish_list(wish_listDto);
        }
        
        response.getWriter().print(wish_listDto.getIdx());
    }
}
