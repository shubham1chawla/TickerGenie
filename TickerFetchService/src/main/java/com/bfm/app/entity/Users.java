package com.bfm.app.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name = "find_by_email", query = "select u from Users u where u.email = :email")
@NamedQuery(name = "find_by_username", query = "select u from Users u where u.username = :username")
public class Users {

	@Id
	@GeneratedValue
	private Integer id;
	private String username, email;

	public Users() {}
	
	public Users(String username, String email) {
		this.username = username;
		this.email = email;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Users [id=" + id + ", username=" + username + ", email=" + email + "]";
	}
}
