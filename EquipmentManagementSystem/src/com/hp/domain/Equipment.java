package com.hp.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * Equipment entity. @author MyEclipse Persistence Tools
 */

public class Equipment implements java.io.Serializable {

	// Fields

	private Integer id;
	private Line line;
	private Machine machine;
	private String eid;
	private Integer sit;
	private Set maintainRecords = new HashSet(0);

	// Constructors

	/** default constructor */
	public Equipment() {
	}

	/** full constructor */
	public Equipment(Line line, Machine machine, String eid, Integer sit,
			Set maintainRecords) {
		this.line = line;
		this.machine = machine;
		this.eid = eid;
		this.sit = sit;
		this.maintainRecords = maintainRecords;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Line getLine() {
		return this.line;
	}

	public void setLine(Line line) {
		this.line = line;
	}

	public Machine getMachine() {
		return this.machine;
	}

	public void setMachine(Machine machine) {
		this.machine = machine;
	}

	public String getEid() {
		return this.eid;
	}

	public void setEid(String eid) {
		this.eid = eid;
	}

	public Integer getSit() {
		return this.sit;
	}

	public void setSit(Integer sit) {
		this.sit = sit;
	}

	public Set getMaintainRecords() {
		return this.maintainRecords;
	}

	public void setMaintainRecords(Set maintainRecords) {
		this.maintainRecords = maintainRecords;
	}

}