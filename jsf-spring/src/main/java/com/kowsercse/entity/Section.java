package com.kowsercse.entity;

// Generated Jan 13, 2011 12:40:37 PM by Hibernate Tools 3.3.0.GA

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
 * Section generated by hbm2java
 */
@Entity
@Table(name = "section", catalog = "school")
public class Section implements java.io.Serializable {

	private Integer id;
	private String name;
	private Set<Student> students = new HashSet<Student>(0);

	public Section() {
	}

	public Section(String name, Set<Student> students) {
		this.name = name;
		this.students = students;
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "section")
	public Set<Student> getStudents() {
		return this.students;
	}

	public void setStudents(Set<Student> students) {
		this.students = students;
	}

}
