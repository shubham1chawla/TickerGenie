package com.bfm.app.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

	@Autowired
	private SessionController sessionController;
	
	@RequestMapping("/")
	public String getHomePageDomainOnly(HttpServletRequest request, Model model) {
		return this.getHomePageJSP(request, model);
	}
	
	@RequestMapping("/index")
	public String getHomePageJSP(HttpServletRequest request, Model model) {
		model.addAttribute("nav_bar_button_text", "Login");
		model.addAttribute("nav_bar_button_forward_link", "/login");
		if(sessionController.validateSession(request)) {
			model.addAttribute("nav_bar_button_forward_link", "/logout");
			model.addAttribute("nav_bar_button_text", sessionController.getSessionInfo(request) + " | Logout");
		}
		return "index";
	}
}
