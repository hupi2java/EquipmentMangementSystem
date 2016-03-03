package com.hp.web.action;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hp.domain.Users;
import com.hp.serviceInter.UsersServiceInter;
import com.opensymphony.xwork2.ActionSupport;

@Controller
@Scope("prototype")
public class ChangePasswordAction extends ActionSupport implements RequestAware,SessionAware{

	private String oldPassword;
	private String newPassword;
	private String secondPassword;
	
	@Resource
	private UsersServiceInter usersServiceInter;
	private Map<String, Object> request;
	private Map<String,Object> session;
	
	//跳转到改密UI
	public String goChangUI(){
		
		request.put("type", "用户设置");
		return SUCCESS;
	}
	
	//改密action 成功跳转到OK UI  失败跳转到ERR UI
	public String changePassword(){
		Users user = (Users) session.get("user");
		if(user.getPassword().equals(oldPassword)){
			if(newPassword.equals(secondPassword)){
				user.setPassword(newPassword);
				usersServiceInter.update(user);
				return SUCCESS;
			}
			return ERROR;
		}
		return ERROR;
	}
	
	@Override
	public void setRequest(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		request=arg0;
	}
	
	@Override
	public void setSession(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		session=arg0;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getSecondPassword() {
		return secondPassword;
	}

	public void setSecondPassword(String secondPassword) {
		this.secondPassword = secondPassword;
	}

}
