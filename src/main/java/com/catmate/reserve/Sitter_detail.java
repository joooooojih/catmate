package com.catmate.reserve;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class Sitter_detail {

    @RequestMapping(value="reserve/sitter_detail", method=RequestMethod.GET)
    public String sitter_detail() {
        
        return "reserve/sitter_detail";
    }
    
}
