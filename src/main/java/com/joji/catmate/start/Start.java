package com.joji.catmate.start;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class Start {

	@RequestMapping(value="/", method=RequestMethod.GET)
	public String main(Model model) {
		
		model.addAttribute("scrren", "main");
		return "start/main";
	}
	
	@RequestMapping(value="/start/login", method=RequestMethod.GET)
	public String login() {
		
		return "start/login";
	}
	
	@RequestMapping(value="/start/login", method=RequestMethod.POST)
	public String login(HttpServletRequest request) {
		
		return "redirect:/";
	}
	
	@RequestMapping(value="/start/signup", method=RequestMethod.GET)
	public String signup() {
		
		return "start/signup";
	}
	
	@RequestMapping(value="/start/signup", method=RequestMethod.POST)
	public String signup(HttpServletRequest request) {
		
		return "redirect:/";
	}
}
