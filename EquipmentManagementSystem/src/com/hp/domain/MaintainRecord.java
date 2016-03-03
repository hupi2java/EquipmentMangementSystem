package com.hp.domain;

import java.sql.Timestamp;

/**
 * MaintainRecord entity. @author MyEclipse Persistence Tools
 */

public class MaintainRecord implements java.io.Serializable {

	// Fields

	private Integer id;
	private Users usersByEnId;
	private Equipment equipment;
	private Users usersByUId;
	private MaintainItems maintainItems;
	private Timestamp maintaintime;
	private String firstResult;
	private String secResult;
	private String tip;

	// Constructors

	/** default constructor */
	public MaintainRecord() {
	}

	/** minimal constructor */
	public MaintainRecord(Timestamp maintaintime) {
		this.maintaintime = maintaintime;
	}

	/** full constructor */
	public MaintainRecord(Users usersByEnId, Equipment equipment,
			Users usersByUId, MaintainItems maintainItems,
			Timestamp maintaintime, String firstResult, String secResult,
			String tip) {
		this.usersByEnId = usersByEnId;
		this.equipment = equipment;
		this.usersByUId = usersByUId;
		this.maintainItems = maintainItems;
		this.maintaintime = maintaintime;
		this.firstResult = firstResult;
		this.secResult = secResult;
		this.tip = tip;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Users getUsersByEnId() {
		return this.usersByEnId;
	}

	public void setUsersByEnId(Users usersByEnId) {
		this.usersByEnId = usersByEnId;
	}

	public Equipment getEquipment() {
		return this.equipment;
	}

	public void setEquipment(Equipment equipment) {
		this.equipment = equipment;
	}

	public Users getUsersByUId() {
		return this.usersByUId;
	}

	public void setUsersByUId(Users usersByUId) {
		this.usersByUId = usersByUId;
	}

	public MaintainItems getMaintainItems() {
		return this.maintainItems;
	}

	public void setMaintainItems(MaintainItems maintainItems) {
		this.maintainItems = maintainItems;
	}

	public Timestamp getMaintaintime() {
		return this.maintaintime;
	}

	public void setMaintaintime(Timestamp maintaintime) {
		this.maintaintime = maintaintime;
	}

	public String getFirstResult() {
		return this.firstResult;
	}

	public void setFirstResult(String firstResult) {
		this.firstResult = firstResult;
	}

	public String getSecResult() {
		return this.secResult;
	}

	public void setSecResult(String secResult) {
		this.secResult = secResult;
	}

	public String getTip() {
		return this.tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

}