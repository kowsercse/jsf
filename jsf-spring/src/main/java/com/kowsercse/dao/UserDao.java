package com.kowsercse.dao;

import java.util.List;

import com.kowsercse.entity.User;

public class UserDao extends AbstractDao<User> {

	public UserDao() {
		super(User.class);
	}
	
	@Override
	public List<User> findAll() {
		return null;
	}
	
	public User findByUserName(String userName) {
		return null;
	}

}
