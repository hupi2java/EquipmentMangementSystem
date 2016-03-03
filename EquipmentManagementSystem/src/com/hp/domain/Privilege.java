package com.hp.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * Privilege entity. @author MyEclipse Persistence Tools
 */

public class Privilege implements java.io.Serializable {

	// Fields

	private Integer id;
	private Privilege privilege;//上级权限
	private String url;
	private String name;
	private Set privileges = new HashSet(0);//下级权限
	private Set<Users> users = new HashSet<Users>();
	// Constructors

	/** default constructor */
	public Privilege() {
	}

	/** full constructor */
	public Privilege(Privilege privilege, String url, String name,
			Set privileges) {
		this.privilege = privilege;
		this.url = url;
		this.name = name;
		this.privileges = privileges;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Privilege getPrivilege() {
		return this.privilege;
	}

	public void setPrivilege(Privilege privilege) {
		this.privilege = privilege;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set getPrivileges() {
		return this.privileges;
	}

	public void setPrivileges(Set privileges) {
		this.privileges = privileges;
	}

	public Set<Users> getUsers() {
		return users;
	}

	public void setUsers(Set<Users> users) {
		this.users = users;
	}

}