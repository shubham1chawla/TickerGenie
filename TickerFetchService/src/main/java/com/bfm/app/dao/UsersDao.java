package com.bfm.app.dao;

import com.bfm.app.entity.Users;

public interface UsersDao {

	public Users findById(int id);

	public Users findByUsername(String username);

	public Users findByEmail(String email);

	public Users insertUser(Users user);

	public Users updateUser(Users user);
}
