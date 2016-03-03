package com.hp.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * Department entity. @author MyEclipse Persistence Tools
 */

public class Department implements java.io.Serializable {

	// Fields

	private Integer id;
	private Department department;
	private String name;
	private String sit;
	private Set lines = new HashSet(0);
	private Set userses = new HashSet(0);
	private Set departments = new HashSet(0);

	// Constructors

	/** default constructor */
	public Department() {
	}

	/** full constructor */
	public Department(Department department, String name, String sit,
			Set lines, Set userses, Set departments) {
		this.department = department;
		this.name = name;
		this.sit = sit;
		this.lines = lines;
		this.userses = userses;
		this.departments = departments;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Department getDepartment() {
		return this.department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSit() {
		return this.sit;
	}

	public void setSit(String sit) {
		this.sit = sit;
	}

	public Set getLines() {
		return this.lines;
	}

	public void setLines(Set lines) {
		this.lines = lines;
	}

	public Set getUserses() {
		return this.userses;
	}

	public void setUserses(Set userses) {
		this.userses = userses;
	}

	public Set getDepartments() {
		return this.departments;
	}

	public void setDepartments(Set departments) {
		this.departments = departments;
	}

}