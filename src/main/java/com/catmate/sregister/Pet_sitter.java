package com.catmate.sregister;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class Pet_sitter {
    
    /*제목, 집 주소*/
    @RequestMapping(value="/sregister/pet_sitter01", method=RequestMethod.GET)
    public String pet_sitter01() {
        
        return "sregister/pet_sitter01";
    }
    
    @RequestMapping(value="/sregister/pet_sitter01", method=RequestMethod.POST)
    public String pet_sitter01(HttpServletRequest request) {
        
        return "sregister/pet_sitter02";
    }
    
    /*체크인 체크아웃, 데이케어 1박 마리당 추가 금액*/
    @RequestMapping(value="/sregister/pet_sitter02", method=RequestMethod.GET)
    public String pet_sitter02() {
        
        return "sregister/pet_sitter02";
    }
    
    @RequestMapping(value="/sregister/pet_sitter02", method=RequestMethod.POST)
    public String pet_sitter02(HttpServletRequest request) {
        
        return "sregister/pet_sitter03";
    }
    
    /*돌봄 환경, 태그*/
    @RequestMapping(value="/sregister/pet_sitter03", method=RequestMethod.GET)
    public String pet_sitter03() {
        
        return "sregister/pet_sitter03";
    }
    
    @RequestMapping(value="/sregister/pet_sitter03", method=RequestMethod.POST)
    public String pet_sitter03(HttpServletRequest request) {
        
        return "sregister/pet_sitter04";
    }
    
    /*사진 업로드*/
    @RequestMapping(value="/sregister/pet_sitter04", method=RequestMethod.GET)
    public String pet_sitter04() {
        
        return "sregister/pet_sitter04";
    }
    
    @RequestMapping(value="/sregister/pet_sitter04", method=RequestMethod.POST)
    public String pet_sitter04(HttpServletRequest request) {
        
        return "sregister/pet_sitter05";
    }
    
    /*미리보기*/
    @RequestMapping(value="/sregister/pet_sitter05", method=RequestMethod.GET)
    public String pet_sitter05() {
        
        return "reserve/sitter_detail";
    }
    
    @RequestMapping(value="/sregister/pet_sitter05", method=RequestMethod.POST)
    public String pet_sitter05(HttpServletRequest request) {
        
        return "reserve/sitter_detail";
    }
}
