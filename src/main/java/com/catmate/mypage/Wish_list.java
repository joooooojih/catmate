package com.catmate.mypage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class Wish_list {

    @RequestMapping(value="/mypage/wish_list", method=RequestMethod.GET)
    public String wish_list() {
        
        return "mypage/wish_list";
    }
}
