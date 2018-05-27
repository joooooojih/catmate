package com.catmate.mypage;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.catmate.dto.Pet_sitter_houseDto;
import com.catmate.dto.User_profileDto;
import com.catmate.mypage.service.MypageService;

@Controller
public class Pet_sitter_house_update {

    @Autowired
    MypageService mypageService;
    
    @RequestMapping(value="/mypage/pet_sitter_house_update", method=RequestMethod.GET)
    public String pet_sitter_house_update(HttpSession session, HttpServletRequest request) {
        
        List<Pet_sitter_houseDto> pet_sitter_houseList = mypageService.getMy_pet_sitter_houseList((User_profileDto) session.getAttribute("user_profile"));
        
        request.setAttribute("pet_sitter_houseList", pet_sitter_houseList);
        return "mypage/pet_sitter_house_update";
    }
    
    @RequestMapping(value="/mypage/pet_sitter_house_update_idx", method=RequestMethod.GET)
    public String pet_sitter_house_update_idx(HttpSession session, HttpServletRequest request) {
        session.setAttribute("idx", Integer.parseInt(request.getParameter("idx")));
        return "redirect:/sregister/pet_sitter01";
    }
}
