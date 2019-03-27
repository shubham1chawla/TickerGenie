package com.bfm.app.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bfm.app.service.impl.UserAuthentication;

@Controller
public class LoginController {

	@Autowired
	private UserAuthentication userAuth;
	
	@Autowired
	private SessionController sessionController;
	
	@RequestMapping("/login")
	public String getLoginPage(HttpServletRequest request) {
		if(sessionController.validateSession(request)) {
			return "redirect:dashboard";
		}
		return "login";
	}
	
	@RequestMapping(value="/login", method= RequestMethod.POST)
	public String receiveUserCredentials(@RequestParam(value = "username") String username, 
										 @RequestParam(value = "password") String password, 
										 Model model, HttpServletRequest request) {
		if(userAuth.loginAuthenticate(username, password)) {
			sessionController.setSession(request, username);
			return "redirect:dashboard";
		}
		model.addAttribute("error_message", "Incorrect Credentials, Please try again.");
		return "login";
	}
}
