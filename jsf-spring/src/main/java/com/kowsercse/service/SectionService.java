package com.kowsercse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.kowsercse.dao.GenericDao;
import com.kowsercse.entity.Section;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW)
@Scope
public class SectionService {

	@Autowired
	private GenericDao<Section> sectionDao;

	public GenericDao<Section> getSectionDao() {
		return sectionDao;
	}

	public void setSectionDao(GenericDao<Section> sectionDao) {
		this.sectionDao = sectionDao;
	}

	public void addSection(Section currentSection) {
		System.out.println("name:=" + currentSection.getId());
		if (currentSection.getId() == null)
			sectionDao.persist(currentSection);
		else
			sectionDao.merge(currentSection);
	}

	public void deleteSection(Section currentSection) {
		if (currentSection.getId() != null)	
		{
			int primaryKey=currentSection.getId();
			currentSection=sectionDao.findById(primaryKey);
			sectionDao.remove(currentSection);
		}
	}

	public List<Section> getSections() {
		return sectionDao.findAll();
	}

}
