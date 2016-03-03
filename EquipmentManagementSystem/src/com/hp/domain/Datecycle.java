package com.hp.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * Datecycle entity. @author MyEclipse Persistence Tools
 */

public class Datecycle implements java.io.Serializable {

	// Fields

	private Integer id;
	private String type;
	private Set maintainItemses = new HashSet(0);

	// Constructors

	/** default constructor */
	public Datecycle() {
	}

	/** full constructor */
	public Datecycle(String type, Set maintainItemses) {
		this.type = type;
		this.maintainItemses = maintainItemses;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Set getMaintainItemses() {
		return this.maintainItemses;
	}

	public void setMaintainItemses(Set maintainItemses) {
		this.maintainItemses = maintainItemses;
	}

}