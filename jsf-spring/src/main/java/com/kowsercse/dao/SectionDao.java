package com.kowsercse.dao;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.kowsercse.entity.Section;

@Repository("sectionDao")
@Scope
@Transactional
public class SectionDao extends AbstractDao<Section> {

	public SectionDao() {
		super(Section.class);
		System.out.println(clazz);
		System.out.println(Section.class.getName());
		System.out.println(Section.class.getSimpleName());
	}
	
	public List<Section> findAll() {
		return entityManager.createQuery("from Section", clazz).getResultList();
	}
	
}
