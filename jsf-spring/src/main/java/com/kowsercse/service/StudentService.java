package com.kowsercse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.kowsercse.dao.GenericDao;
import com.kowsercse.entity.Student;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW)
@Scope
public class StudentService {

	@Autowired
	private GenericDao<Student> studentDao;
	

	public GenericDao<Student> getStudentDao() {
		return studentDao;
	}

	public void setStudentDao(GenericDao<Student> studentDao) {
		this.studentDao = studentDao;
	}

	public void addStudent(Student currentStudent) {
		System.out.println("name:=" + currentStudent.getId());
		if (currentStudent.getId() == null)
			studentDao.persist(currentStudent);
		else
			studentDao.merge(currentStudent);
	}

	public void deleteStudent(Student currentStudent) {
		if (currentStudent.getId() != null)	
		{
			int primaryKey=currentStudent.getId();
			currentStudent=studentDao.findById(primaryKey);
			studentDao.remove(currentStudent);
		}
	}

	public List<Student> getStudents() {
		return studentDao.findAll();
	}

}
