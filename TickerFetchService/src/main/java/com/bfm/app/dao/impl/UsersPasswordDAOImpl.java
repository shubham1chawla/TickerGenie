package com.bfm.app.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.bfm.app.dao.UsersDao;
import com.bfm.app.dao.UsersPasswordDAO;
import com.bfm.app.entity.UsersPassword;

@Repository
@Transactional
public class UsersPasswordDAOImpl implements UsersPasswordDAO {

	@Autowired
	private EntityManager em;
	
	@Autowired
	private UsersDao usersDao;
	
	@Override
	public Boolean shouldAuthById(Integer userId, String password) {	
		List<UsersPassword> resultList = em.createNamedQuery("PASS_CHECK", UsersPassword.class)
				                           .setParameter("pass", password)
				                           .setParameter("uid", userId)
				                           .getResultList();
		return !CollectionUtils.isEmpty(resultList);
	}

	@Override
	public void mergePassword(String username, String password) {
		em.merge(new UsersPassword(usersDao.findByUsername(username).getId(), password));
	}

}
