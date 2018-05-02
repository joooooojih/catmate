package com.catmate.member;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.catmate.dto.User_profileDto;
import com.catmate.member.service.MemberService;

@Controller
public class Member {
    
    @Autowired
    MemberService memberService;
    @Autowired
    BCryptPasswordEncoder passwordEncoder;

	@RequestMapping(value="/", method=RequestMethod.GET)
	public String main(Model model) {
		
		model.addAttribute("scrren", "main");
		return "main";
	}
	
	/*로그인*/
	@RequestMapping(value="/member/login", method=RequestMethod.GET)
	public String login() {
		
		return "member/login";
	}
	
	@RequestMapping(value="/member/login", method=RequestMethod.POST)
	public String login(HttpServletRequest request, User_profileDto user_profileDto) {
	    User_profileDto tmpUser_profileDto = memberService.getUser_profile(user_profileDto);
	    
	    if(tmpUser_profileDto == null) {
	        return "member/login"; // 로그인 실패
	    } else if(!passwordEncoder.matches(user_profileDto.getUser_password(), tmpUser_profileDto.getUser_password())) {
	        return "member/login"; // 로그인 실패
	    } else {
	        return "redirect:/"; // 로그인 성공
	    }
	}
	
	/*회원가입*/
	@RequestMapping(value="/member/signup", method=RequestMethod.GET)
	public String signup() {
		
		return "member/signup";
	}
	
	@RequestMapping(value="/member/signup", method=RequestMethod.POST)
	public String signup(HttpServletRequest request, User_profileDto user_profileDto) {
	    user_profileDto.setUser_password(passwordEncoder.encode(user_profileDto.getUser_password()));

	    memberService.insertUser_profile(user_profileDto);
		return "redirect:/";
	}
}
