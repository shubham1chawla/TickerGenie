package com.bfm.app.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DashboardController {

	@Autowired
	private SessionController sessionController;
	
	@RequestMapping("/dashboard")
	public String getDashPage(HttpServletRequest request, Model model) {
		if(sessionController.validateSession(request)) {
			model.addAttribute("nav_bar_button_forward_link", "/logout");
			model.addAttribute("nav_bar_button_text", sessionController.getSessionInfo(request) + " | Logout");
			return "dashboard";
		}
		return "redirect:login";
	}
	
}
