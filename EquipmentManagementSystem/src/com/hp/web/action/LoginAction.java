package com.hp.web.action;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hp.base.service.BaseAction;
import com.hp.domain.*;
import com.hp.serviceInter.UsersServiceInter;
import com.hp.utils.DepartmentUtils;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Controller
@Scope("prototype")
public class LoginAction extends BaseAction<Users>{
	
		/**
		 * 登录页面需要选择部门时的数据准备action
		 * @return
		 */
//		public  String goLoginUI(){
//			List<Department> top = departmentServiceInter.getTopDepartment();
//			List<Department> departmentList= DepartmentUtils.getAllDepartments(top);
//			if(departmentList != null){
//				ActionContext.getContext().put("departmentList", departmentList);
//			}
//			return "goLoginUI";
//		}
		
		//登录
		public String login(){

			Users user = new Users();
			user.setJobId(model.getJobId());
			user.setPassword(model.getPassword());
			user = usersServiceInter.checkLogin(user);
		
			if(user!=null){
				ActionContext.getContext().getSession().put("user", user);
				return "login";
			}
			return "goLogin";
		}
		
		//登出
		public String logout(){
			ActionContext.getContext().getSession().clear();
			return "logout";
		}

}
