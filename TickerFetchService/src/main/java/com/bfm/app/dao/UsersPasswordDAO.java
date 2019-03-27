package com.bfm.app.dao;

public interface UsersPasswordDAO {
	public Boolean shouldAuthById(Integer userId, String password);
	public void mergePassword(String username, String password);
}
