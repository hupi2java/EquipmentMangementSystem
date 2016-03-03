package com.hp.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * Line entity. @author MyEclipse Persistence Tools
 */

public class Line implements java.io.Serializable {

	// Fields

	private Integer id;
	private Department department;
	private String name;
	private Set equipments = new HashSet(0);

	// Constructors

	/** default constructor */
	public Line() {
	}

	/** full constructor */
	public Line(Department department, String name, Set equipments) {
		this.department = department;
		this.name = name;
		this.equipments = equipments;
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

	public Set getEquipments() {
		return this.equipments;
	}

	public void setEquipments(Set equipments) {
		this.equipments = equipments;
	}

}