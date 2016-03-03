package com.hp.domain;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import com.opensymphony.xwork2.ActionContext;

/**
 * Users entity. @author MyEclipse Persistence Tools
 */

public class Users implements java.io.Serializable {

	// Fields

	private Integer id;
	private Department department;
	private String jobId;
	private String name;
	private String password;
	private String post;
	private Integer limit;
	private Set maintainRecordsForEnId = new HashSet(0);
	private Set maintainRecordsForUId = new HashSet(0);
	private Set<Privilege> privileges = new HashSet<Privilege>(); 
	// Constructors

	
	/**
	 * �ж��û��Ƿ��ǳ����û�
	 */
	public boolean isAdmin(){
		return "admin".equals(jobId);
	}
	
	/**
	 * �ж��û��Ƿ���ָ�����Ƶ�Ȩ��
	 */
	public boolean hasPrivilegeByName(String name){
		//����ǳ����û� return true
		if(isAdmin()){
			return true;
		}else{
			for(Privilege privilege : privileges){
				if(privilege.getName().equals(name))
					return true;
			}
			return false;
		}
	}
	
	/**
	 * �ж��û��Ƿ���ָ��url��Ȩ��
	 */
	public boolean hasPrivilegeByUrl(String url){
		if(isAdmin()){
			return true;
		}
		//ȥ�� url��Ĳ��� �� ǰ���"/"
		int pos = url.indexOf("?");
		if(pos > -1)
			url = url.substring(0, pos);
		url = url.substring(1);
		
		//���url����Ҫ���� �����û���¼�Ϳ���ʹ��
		Collection<String> allPrivilegeUrl = (Collection<String>) ActionContext.getContext().getApplication().get("allPrivilegeUrlList"); 
		if(!allPrivilegeUrl.contains(url)){
			return true;
		}else{
			
			for(Privilege privilege : privileges){
				if(privilege.getUrl().equals(url))
					return true;
			}
			return false;
		}
		
	}
	
	/** default constructor */
	public Users() {
	}

	/** full constructor */
	public Users(Department department, String jobId, String name,
			String password, String post, Integer limit,
			Set maintainRecordsForEnId, Set maintainRecordsForUId) {
		this.department = department;
		this.jobId = jobId;
		this.name = name;
		this.password = password;
		this.post = post;
		this.limit = limit;
		this.maintainRecordsForEnId = maintainRecordsForEnId;
		this.maintainRecordsForUId = maintainRecordsForUId;
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

	public String getJobId() {
		return this.jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPost() {
		return this.post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public Integer getLimit() {
		return this.limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public Set getMaintainRecordsForEnId() {
		return this.maintainRecordsForEnId;
	}

	public void setMaintainRecordsForEnId(Set maintainRecordsForEnId) {
		this.maintainRecordsForEnId = maintainRecordsForEnId;
	}

	public Set getMaintainRecordsForUId() {
		return this.maintainRecordsForUId;
	}

	public void setMaintainRecordsForUId(Set maintainRecordsForUId) {
		this.maintainRecordsForUId = maintainRecordsForUId;
	}

	public Set<Privilege> getPrivileges() {
		return privileges;
	}

	public void setPrivileges(Set<Privilege> privileges) {
		this.privileges = privileges;
	}

}