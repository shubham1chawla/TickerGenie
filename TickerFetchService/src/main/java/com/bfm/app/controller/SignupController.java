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
public class SignupController {
	
	@Autowired
	private UserAuthentication userAuth;
	
	@Autowired
	private SessionController sessionController;

	@RequestMapping(value="/signup")
	public String signupPageRedirection(HttpServletRequest request) {
		if(sessionController.validateSession(request)) {
			return "redirect:dashboard";
		}
		return "signup";
	}
	
	@RequestMapping(value="/signup", method= RequestMethod.POST)
	public String signingUpUserActionHandler(@RequestParam(value="username") String username,
											 @RequestParam(value="email") String email,
											 @RequestParam(value="password") String password,
											 @RequestParam(value="confirm_password") String confirmPassword,
											 Model model) {
		
		String signUpReport = userAuth.signUpAuthentication(username, password, confirmPassword, email);
		if(signUpReport == null) {
			return "redirect:login";
		}
		model.addAttribute("error_message", signUpReport);
		return "signup";
	}
}
