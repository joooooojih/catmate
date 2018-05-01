package com.joji.catmate.reserve;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class Search {

    
    /*이제된건가*/
	@RequestMapping(value="/reserve/search", method=RequestMethod.GET)
	public String search(HttpServletRequest request) {
	  
	    String[] tags = {"아파트", "마당", "환자견케어", "자격증보유", "실외배변", "반려견 없는 곳", "픽업가능", "수제간식", "응급처치", "투약가능"};
	    
	    request.setAttribute("tags", tags);
		return "reserve/search";
	}
	
}
