package com.bfm.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bfm.app.dao.UsersDao;
import com.bfm.app.dao.UsersPasswordDAO;
import com.bfm.app.entity.Users;
import com.bfm.app.util.SanitizeUtils;

@Service
public class UserAuthentication {

	@Autowired
	private UsersDao usersDao;
	
	@Autowired
	private UsersPasswordDAO usersPassDao;

	public boolean loginAuthenticate(String username, String password) {

		// Sanatizing inputs
		username = SanitizeUtils.sanatizeInput(username);
		password = SanitizeUtils.sanatizeInput(password);

		// Getting user object
		Users user = usersDao.findByUsername(username);
		if (user == null) {
			return false;
		}		
		return usersPassDao.shouldAuthById(user.getId(), password);
	}

	public String signUpAuthentication(String username, String password, String confirmPassword, String email) {

		// Sanatizing inputs
		username = SanitizeUtils.sanatizeInput(username);
		password = SanitizeUtils.sanatizeInput(password);
		confirmPassword = SanitizeUtils.sanatizeInput(confirmPassword);
		email = SanitizeUtils.sanatizeInput(email);

		// Empty fields check
		if (username.equals("") || password.equals("") || confirmPassword.equals("") || email.equals("")) {
			return "Fields are empty.";
		}

		// Confirm password check
		if (!confirmPassword.equals(password)) {
			return "Password and Confirm Password didn't match.";
		}

		// Email Check

		// Already email in database check
		if (usersDao.findByEmail(email) != null) {
			return "Email already exists. Use different email address.";
		}

		// Already username in database check
		if (usersDao.findByUsername(username) != null) {
			return "Username already exists. Use different Username.";
		}

		// Password constraints check
		Users newUser = new Users(username, email);
		usersDao.insertUser(newUser);
		usersPassDao.mergePassword(username, confirmPassword);
		// Returns error message, so null in case everything went alright.
		return null;
	}
}
