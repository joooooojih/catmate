package com.catmate.member;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.catmate.dto.User_profileDto;
import com.catmate.mail.Send_mail;
import com.catmate.mypage.service.MypageService;

@Controller
public class Member {
    
    @Autowired
    MypageService mypageService;
    @Autowired
    BCryptPasswordEncoder passwordEncoder;
    @Autowired
    Send_mail send_mail;

	@RequestMapping(value="/", method=RequestMethod.GET)
	public String main(HttpServletRequest request, HttpSession session) {
	    
	    request.setAttribute("scrren", "main");
		return "main";
	}
	
	/*로그인*/
	@RequestMapping(value="/member/login", method=RequestMethod.GET)
	public String login(HttpSession session) {
	
		return "member/login";
	}
	
	@RequestMapping(value="/member/login", method=RequestMethod.POST)
	public String login(HttpServletRequest request, User_profileDto loginUser_profileDto, HttpSession session) {
	    User_profileDto user_profileDto = mypageService.getUser_profile(loginUser_profileDto.getUser_email());
	    
	    if(user_profileDto == null) {
	        return "member/login"; // 일치하는 이메일 없음 //로그인 실패
	        
	    } else if(!passwordEncoder.matches(loginUser_profileDto.getUser_password(), user_profileDto.getUser_password())) {
	        return "member/login"; // 비밀번호가 다름 //로그인 실패
	        
	    } else {
	        session.setAttribute("user_profile", user_profileDto);
	        return "redirect:/"; // 로그인 성공
	    }
	}
	
	/*로그아웃*/
	@RequestMapping(value="/member/logout", method=RequestMethod.GET)
    public String logout(HttpSession session) {
    
	    session.invalidate();
	    return "redirect:/";
    }
	
	/*회원가입*/
	@RequestMapping(value="/member/signup", method=RequestMethod.GET)
	public String signup() {
		
		return "member/signup";
	}
	
	@RequestMapping(value="/member/signup", method=RequestMethod.POST)
	public String signup(HttpServletRequest request, HttpSession session, User_profileDto user_profileDto) {
	    
	    user_profileDto.setUser_password(passwordEncoder.encode(user_profileDto.getUser_password())); // 비밀번호 인코딩
	    mypageService.insertUser_profile(user_profileDto);
	    session.setAttribute("user_profile", mypageService.getUser_profile(user_profileDto.getUser_email()));
		return "redirect:/";
	}
	
	// 회원가입 이메일 AJAX
	@RequestMapping(value="/member/signupEmail", method=RequestMethod.GET)
    public void signupEmail(User_profileDto confirmUser_profile, HttpServletResponse response) throws AddressException, MessagingException, IOException {
	    User_profileDto user_profileDto = mypageService.getUser_profile(confirmUser_profile.getUser_email());
	    if(user_profileDto == null) {
	        
	        String content = "";
	        for(int i = 0; i < 5; i++) {
	            int random = (int) (Math.random() * 10);
	            content += random;
	        }
	        
	        send_mail.send_mail(confirmUser_profile.getUser_email(), content);
	        response.getWriter().print(content);
	        
	    } else {
	        response.getWriter().print("existence");
	    }
	    
    }
}
