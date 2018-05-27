package com.catmate.mypage;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.catmate.dto.ReviewDto;

@Controller
public class Review {

    @RequestMapping(value="/mypage/review", method=RequestMethod.GET)
    public String review(HttpSession session, ReviewDto reviewDto) {
        
        return "mypage/review";
    }
}
