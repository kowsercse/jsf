package com.kowsercse.controller;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kowsercse.bean.Student;

@ManagedBean(name = "studentForm")
@ViewScoped
public class StudentForm {

	private Student student;
	
	public Student getStudent() {
		FacesContext        facesContext = FacesContext.getCurrentInstance();         
		HttpServletResponse httpResponse = (HttpServletResponse)facesContext.getExternalContext().getResponse();         
		HttpServletRequest  httpRequest  = (HttpServletRequest)facesContext.getExternalContext().getRequest();  
		String              contextPath  = httpRequest.getContextPath();
		System.out.println(contextPath);
		
		FacesContext currentContext = FacesContext.getCurrentInstance();
		Map<Object, Object> attributes = currentContext.getAttributes();
		System.out.println(attributes);

		Student student = new Student("kowser", 27);
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("student", student);
		return student;
	}

	public void save() {
		System.out.println("save");
	}

	public void save(Object object) {
		System.out.println("save object" + object);
	}

}
