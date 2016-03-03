package com.hp.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * Machine entity. @author MyEclipse Persistence Tools
 */

public class Machine implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private String type;
	private String version;
	private Set maintainItemses = new HashSet(0);
	private Set equipments = new HashSet(0);

	// Constructors

	/** default constructor */
	public Machine() {
	}

	/** full constructor */
	public Machine(String name, String type, String version,
			Set maintainItemses, Set equipments) {
		this.name = name;
		this.type = type;
		this.version = version;
		this.maintainItemses = maintainItemses;
		this.equipments = equipments;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getVersion() {
		return this.version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public Set getMaintainItemses() {
		return this.maintainItemses;
	}

	public void setMaintainItemses(Set maintainItemses) {
		this.maintainItemses = maintainItemses;
	}

	public Set getEquipments() {
		return this.equipments;
	}

	public void setEquipments(Set equipments) {
		this.equipments = equipments;
	}

}