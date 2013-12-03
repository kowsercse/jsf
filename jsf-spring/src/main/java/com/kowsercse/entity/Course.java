package com.kowsercse.entity;

// Generated Jan 13, 2011 12:26:22 PM by Hibernate Tools 3.3.0.GA

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Course generated by hbm2java
 */
@Entity
@Table(name = "course", catalog = "school")
public class Course implements java.io.Serializable {

	private Integer id;
	private String name;
	private Set<StudentCourse> studentCourses = new HashSet<StudentCourse>(0);

	public Course() {
	}

	public Course(String name, Set<StudentCourse> studentCourses) {
		this.name = name;
		this.studentCourses = studentCourses;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "name", length = 45)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "course")
	public Set<StudentCourse> getStudentCourses() {
		return this.studentCourses;
	}

	public void setStudentCourses(Set<StudentCourse> studentCourses) {
		this.studentCourses = studentCourses;
	}

}
