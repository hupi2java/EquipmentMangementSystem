package com.hp.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * MaintainItems entity. @author MyEclipse Persistence Tools
 */

public class MaintainItems implements java.io.Serializable {

	// Fields

	private Integer id;
	private Datecycle datecycle;
	private Machine machine;
	private String project;
	private String norm;
	private Integer selection;
	private Set maintainRecords = new HashSet(0);

	// Constructors

	/** default constructor */
	public MaintainItems() {
	}

	/** full constructor */
	public MaintainItems(Datecycle datecycle, Machine machine, String project,
			String norm, Integer selection, Set maintainRecords) {
		this.datecycle = datecycle;
		this.machine = machine;
		this.project = project;
		this.norm = norm;
		this.selection = selection;
		this.maintainRecords = maintainRecords;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Datecycle getDatecycle() {
		return this.datecycle;
	}

	public void setDatecycle(Datecycle datecycle) {
		this.datecycle = datecycle;
	}

	public Machine getMachine() {
		return this.machine;
	}

	public void setMachine(Machine machine) {
		this.machine = machine;
	}

	public String getProject() {
		return this.project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public String getNorm() {
		return this.norm;
	}

	public void setNorm(String norm) {
		this.norm = norm;
	}

	public Integer getSelection() {
		return this.selection;
	}

	public void setSelection(Integer selection) {
		this.selection = selection;
	}

	public Set getMaintainRecords() {
		return this.maintainRecords;
	}

	public void setMaintainRecords(Set maintainRecords) {
		this.maintainRecords = maintainRecords;
	}

}