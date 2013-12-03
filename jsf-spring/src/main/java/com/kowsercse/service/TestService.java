package com.kowsercse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.kowsercse.dao.GenericDao;
import com.kowsercse.entity.Section;

@Service
@Scope
public class TestService {

	@Autowired
	@Qualifier("sectionDao")
	private GenericDao<Section> sectionDao;

	public void testGenericDao() {
		Section section1 = sectionDao.findById(2);
		System.out.println(section1);
	}

	public void findAll() {
		System.out.println(sectionDao.findAll());
		;
	}

}
