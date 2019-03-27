package com.bfm.app.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name="PASS_CHECK", query="SELECT u FROM UsersPassword u WHERE u.password = :pass AND u.userId = :uid")
public class UsersPassword {

	@Id
	@GeneratedValue
	private Integer id;
	
	private Integer userId;
	private String password;
	
	public UsersPassword() {}
	
	public UsersPassword(Integer userId, String password) {
		this.userId = userId;
		this.password = password;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
		
}
