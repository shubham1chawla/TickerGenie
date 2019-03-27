package com.bfm.app.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.bfm.app.dao.UsersDao;
import com.bfm.app.entity.Users;

@Repository
@Transactional
public class UsersDaoImpl implements UsersDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Users findById(int id) {
		return entityManager.find(Users.class, id);
	}

	@Override
	public Users findByUsername(String username) {
		List<Users> users = entityManager.createNamedQuery("find_by_username", Users.class)
				.setParameter("username", username).getResultList();
		if (users.isEmpty()) {
			return null;
		}
		return users.get(0);
	}

	@Override
	public Users findByEmail(String email) {
		List<Users> users = entityManager.createNamedQuery("find_by_email", Users.class).setParameter("email", email)
				.getResultList();
		if (users.isEmpty()) {
			return null;
		}
		return users.get(0);
	}

	@Override
	public Users insertUser(Users user) {
		return entityManager.merge(user);
	}

	@Override
	public Users updateUser(Users user) {
		return entityManager.merge(user);
	}
}
