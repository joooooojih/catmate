package com.catmate.mypage;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.catmate.dto.User_profileDto;
import com.catmate.mypage.service.MypageService;

@Controller
public class Account {

    @Autowired
    MypageService mypageService;
    @Autowired
    BCryptPasswordEncoder passwordEncoder;
    /*password 검사*/
    @RequestMapping(value="/mypage/account/account_check", method=RequestMethod.GET)
    public String account_check() {
        
        return "mypage/account/account_check";
    }
    
    @RequestMapping(value="/mypage/account/account_check", method=RequestMethod.POST)
    public String account_check(HttpSession session, User_profileDto check_user_profileDto) {
        User_profileDto user_profileDto = (User_profileDto) session.getAttribute("user_profile");
        user_profileDto = mypageService.getUser_profile(user_profileDto.getUser_email());
        
        if(!passwordEncoder.matches(check_user_profileDto.getUser_password(), user_profileDto.getUser_password())) {
            return "mypage/account/account_check"; // 비밀번호가 다름
        } else {
            session.setAttribute("pass_check", "yes");
            return "redirect:/mypage/account/account_info"; // 비밀번호 성공
        }
    }
    
    /*개인정보 변경*/
    @RequestMapping(value="/mypage/account/account_info", method=RequestMethod.GET)
    public String account_info() {
        
        return "mypage/account/account_info";
    }
    
    @RequestMapping(value="/mypage/account/account_info", method=RequestMethod.POST)
    public String account_info(HttpServletRequest request, HttpSession session, User_profileDto updateUser_profileDto) {
        String savePath = request.getServletContext().getRealPath("\\resources\\img\\user_img\\");
        User_profileDto user_profileDto = (User_profileDto) session.getAttribute("user_profile");
        
        if(!updateUser_profileDto.getUploadFile().isEmpty()) {  // 파일을 바꿨다면
            
            String fileName = System.currentTimeMillis() + updateUser_profileDto.getUploadFile().getOriginalFilename();
            
            try {
                updateUser_profileDto.getUploadFile().transferTo(new File(savePath, fileName));
                updateUser_profileDto.setUser_photo(fileName);
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            if(!user_profileDto.getUser_photo().equals("base.png")) {  // 기존 사진 삭제
                File delFile = new File(savePath, user_profileDto.getUser_photo());
                delFile.delete();
            }
            
        } else {  // 파일을 바꾸지 않았다면
            updateUser_profileDto.setUser_photo(user_profileDto.getUser_photo());
        }
        updateUser_profileDto.setUser_email(user_profileDto.getUser_email());
        
        mypageService.updateUser_profile(updateUser_profileDto);  // update
        
        session.setAttribute("user_profile", mypageService.getUser_profile(user_profileDto.getUser_email()));  // 세션 다시 설정
        
        return "mypage/account/account_info";
    }
    
    /*비밀번호 변경*/
    @RequestMapping(value="/mypage/account/account_pass", method=RequestMethod.GET)
    public String account_pass() {
        
        return "mypage/account/account_pass";
    }
    
    @RequestMapping(value="/mypage/account/account_pass", method=RequestMethod.POST)
    public String account_pass(HttpSession session, String user_password) {
        
        User_profileDto user_profileDto = (User_profileDto) session.getAttribute("user_profile");
        user_profileDto.setUser_password(passwordEncoder.encode(user_password));  // password 인코딩
        mypageService.updateUser_profile_pass(user_profileDto);
        
        return "mypage/account/account_pass";
    }
    
    /*회원탈퇴*/
    @RequestMapping(value="/mypage/account/account_breakup", method=RequestMethod.GET)
    public String account_breakup() {
        
        return "mypage/account/account_breakup";
    }
    
    @RequestMapping(value="/mypage/account/account_breakup", method=RequestMethod.POST)
    public String account_breakup(HttpSession session) {
        mypageService.deleteUser_profile((User_profileDto) session.getAttribute("user_profile"));
        session.invalidate();
        return "redirect:/";
    }
    
}
