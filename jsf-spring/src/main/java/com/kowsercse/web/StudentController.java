package com.kowsercse.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.WebApplicationContext;

import com.kowsercse.entity.Student;
import com.kowsercse.service.StudentService;

@Controller
@Scope(value = WebApplicationContext.SCOPE_SESSION)
public class StudentController {

	public static final String PATH = "/student";

	@Autowired
	private StudentService studentService;

	private Student currentStudent;

	public void create() {
		currentStudent = new Student();
		System.out.println("id:=" + currentStudent.getId());		
	}

	public void save() {
		System.out.println("name:=" + currentStudent.getName());
		studentService.addStudent(currentStudent);		
	}

	public String delete(Student student) {
		studentService.deleteStudent(student);
		return PATH+"/list.jsf";
	}

	public void edit(Student student) {
		currentStudent = student;		
	}

	public StudentService getStudentService() {
		return studentService;
	}

	public void setStudentService(StudentService studentService) {
		this.studentService = studentService;
	}

	public Student getCurrentStudent() {
		return currentStudent;
	}

	public void setCurrentStudent(Student currentStudent) {
		this.currentStudent = currentStudent;
	}

}
