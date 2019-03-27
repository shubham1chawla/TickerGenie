package com.bfm.app.controller.api;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bfm.app.dao.UsersDao;
import com.bfm.app.entity.Users;
import com.bfm.app.service.impl.UserAuthentication;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthAPIController {

	@Autowired
	private UsersDao usersDao;
	
	@Autowired
	private UserAuthentication userAuth;
	
	@PostMapping("/login")
	public Users shouldAuth(@RequestBody Map<String, String> payload,
			                HttpServletRequest request) {
		String username = payload.get("username");
		String password = payload.get("password");
		
		if(userAuth.loginAuthenticate(username, password)) {
			return usersDao.findByUsername(username);
		}
		return new Users();
	}
	
}
