package com.bfm.app.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bfm.app.dao.UsersDao;
import com.bfm.app.entity.Users;

@Controller
public class SessionController {

	@Autowired
	private UsersDao usersDao;
	
	public void setSession(HttpServletRequest request, String username) {
		HttpSession userSession = request.getSession();
		Users userEntity = usersDao.findByUsername(username);
		userSession.setAttribute("USER_DETAILS", userEntity.getUsername());
	}
	
	public String getSessionInfo(HttpServletRequest request) {
		return (String) request.getSession().getAttribute("USER_DETAILS");
	}
	
	public boolean validateSession(HttpServletRequest request) {
		String userInfoUsername = (String) request.getSession().getAttribute("USER_DETAILS");
		if(userInfoUsername == null || usersDao.findByUsername(userInfoUsername) == null) {
			return false;
		}		
		return true;
	}
	
	public void destroySession(HttpServletRequest request) {
		request.getSession().invalidate();
	}
	
	@RequestMapping("/logout")
	public String logoutAction(HttpServletRequest request) {
		if(this.validateSession(request)) {
			this.destroySession(request);
		}
		return "redirect:index";
	}
}
