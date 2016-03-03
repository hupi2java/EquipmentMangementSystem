package com.hp.web.action;

import java.util.HashSet;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hp.base.service.BaseAction;
import com.hp.domain.Department;
import com.hp.domain.Privilege;
import com.hp.domain.Users;
import com.hp.utils.DepartmentUtils;
import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class UserAction extends BaseAction<Users> {

	private Integer departmentId;
	private Integer[] privilegeIds;
	
	public String goList() throws Exception{
		List<Users> list = usersServiceInter.getAllUser();
		ActionContext.getContext().put("user", list);
		return "goList";
	}
	
	public String goAddUI() throws Exception{
		List<Department> topList = departmentServiceInter.getTopDepartment();
		List<Department> departmentList = DepartmentUtils.getAllDepartments(topList);
		ActionContext.getContext().put("departmentList", departmentList);
		return "goAddUI";
	}
	
	public String add() throws Exception{		
		Department dep = (Department) departmentServiceInter.getById(departmentId, Department.class);
		
		Users u = new Users();
		u.setDepartment(dep);
		u.setJobId(model.getJobId());
		u.setName(model.getName());
		u.setPost(model.getPost());
		u.setPassword(model.getJobId());
		
		usersServiceInter.save(u);
		
		return "add";
	}
	
	public String delete() throws Exception{
		usersServiceInter.delete(usersServiceInter.getById(model.getId(), Users.class));
		return "delete";
	}
	
	public String goEditUI() throws Exception{
		List<Department> topList = departmentServiceInter.getTopDepartment();
		List<Department> departmentList = DepartmentUtils.getAllDepartments(topList);
		ActionContext.getContext().put("departmentList", departmentList);
		
		Users u = (Users) usersServiceInter.getById(model.getId(), Users.class);
		ActionContext.getContext().getValueStack().push(u);
		return "goEditUI";
	}
	
	public String edit() throws Exception{
		Users u = (Users) usersServiceInter.getById(model.getId(), Users.class);
		u.setDepartment((Department)departmentServiceInter.getById(departmentId, Department.class));
		u.setJobId(model.getJobId());
		u.setName(model.getName());
		u.setPost(model.getPost());
		usersServiceInter.update(u);
		return "edit";
	}
	
	public String initPassword() throws Exception{
		Users u = (Users) usersServiceInter.getById(model.getId(), Users.class);
		u.setPassword(u.getJobId());
		usersServiceInter.update(u);
		return "initPassword";
	}
	
	public String setPrivilegeUI() throws Exception{
//		List<Privilege> privilegeList = privilegeServiceInter.findAll();
//		ActionContext.getContext().put("privilegeList", privilegeList);
		List<Privilege> topPrivilegeList = privilegeServiceInter.findTopList();
		ActionContext.getContext().put("topPrivilegeList", topPrivilegeList);
		Users user = (Users) usersServiceInter.getById(model.getId(), Users.class);
		if(user.getPrivileges()!=null){
			privilegeIds = new Integer[user.getPrivileges().size()];
			int index=0;
			for(Privilege priv : user.getPrivileges()){
				privilegeIds[index++] = priv.getId();
			}
		}
		
		return "setPrivilegeUI";
	}
	
	public String setPrivilege() throws Exception{
		List<Privilege> list = privilegeServiceInter.getByIds(privilegeIds);
		Users user = (Users) usersServiceInter.getById(model.getId(), Users.class);
		user.setPrivileges( new HashSet(list));
		usersServiceInter.save(user);
		return "setPrivilege";
	}

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public Integer[] getPrivilegeIds() {
		return privilegeIds;
	}

	public void setPrivilegeIds(Integer[] privilegeIds) {
		this.privilegeIds = privilegeIds;
	}
}
