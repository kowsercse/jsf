package com.kowsercse.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.kowsercse.entity.Role;

@Repository
public class RoleDao extends AbstractDao<Role> {

	public RoleDao() {
		super(Role.class);
	}

	@Override
	public List<Role> findAll() {
		return null;
	}

}
