package com.kowsercse.dao;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.kowsercse.entity.Student;

@Repository("studentDao")
@Scope
@Transactional
public class StudentDao extends AbstractDao<Student> {

	public StudentDao() {
		super(Student.class);
		System.out.println(clazz);
		System.out.println(Student.class.getName());
		System.out.println(Student.class.getSimpleName());
	}
	
	public List<Student> findAll() {
		return entityManager.createQuery("from Student", clazz).getResultList();
	}
	
}
