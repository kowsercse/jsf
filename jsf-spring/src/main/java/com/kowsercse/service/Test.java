package com.kowsercse.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;

import com.kowsercse.entity.Section;

@Component
public class Test {

	@PersistenceContext
	private EntityManager em;
	
	public List<Section> getSections() {
		List<Section> resultList = em.createQuery("from Section", Section.class).getResultList();
		return resultList;
	}
	
}
