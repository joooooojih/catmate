package com.joji.catmate.sregister;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PetSitter {
    
    /*제목, 집 주소*/
    @RequestMapping(value="/sregister/petSitter01", method=RequestMethod.GET)
    public String petSitter01() {
        
        return "sregister/petSitter01";
    }
    
    @RequestMapping(value="/sregister/petSitter01", method=RequestMethod.POST)
    public String petSitter01(HttpServletRequest request) {
        
        return "sregister/petSitter02";
    }
    
    /*체크인 체크아웃, 데이케어 1박 마리당 추가 금액*/
    @RequestMapping(value="/sregister/petSitter02", method=RequestMethod.GET)
    public String petSitter02() {
        
        return "sregister/petSitter02";
    }
    
    @RequestMapping(value="/sregister/petSitter02", method=RequestMethod.POST)
    public String petSitter02(HttpServletRequest request) {
        
        return "sregister/petSitter03";
    }
    
    /*돌봄 환경, 태그*/
    @RequestMapping(value="/sregister/petSitter03", method=RequestMethod.GET)
    public String petSitter03() {
        
        return "sregister/petSitter03";
    }
    
    @RequestMapping(value="/sregister/petSitter03", method=RequestMethod.POST)
    public String petSitter03(HttpServletRequest request) {
        
        return "sregister/petSitter04";
    }
    
    /*사진 업로드*/
    @RequestMapping(value="/sregister/petSitter04", method=RequestMethod.GET)
    public String petSitter04() {
        
        return "sregister/petSitter04";
    }
    
    @RequestMapping(value="/sregister/petSitter04", method=RequestMethod.POST)
    public String petSitter04(HttpServletRequest request) {
        
        return "sregister/petSitter05";
    }
    
    /*미리보기*/
    @RequestMapping(value="/sregister/petSitter05", method=RequestMethod.GET)
    public String petSitter05() {
        
        return "reserve/sitter_detail";
    }
    
    @RequestMapping(value="/sregister/petSitter05", method=RequestMethod.POST)
    public String petSitter05(HttpServletRequest request) {
        
        return "reserve/sitter_detail";
    }
}
