package com.hp.web.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hp.base.service.BaseAction;
import com.hp.domain.Department;
import com.hp.utils.DepartmentUtils;
import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class DepartmentAction extends BaseAction<Department> {

	private Integer parentId;
	
	public String goDepartmentList(){
		List<Department> list;
		if(model.getId()!=null){
			list = departmentServiceInter.getDepartmentByParentId(model.getId());
			parentId=model.getId();
		}else{
			list = departmentServiceInter.getTopDepartment();
			parentId=null;
		}
		ActionContext.getContext().put("list", list);
		return "goDepartmentList";
	}
	
	public String goAddUI(){
		List<Department> topList = departmentServiceInter.getTopDepartment();
		List<Department> departmentList = DepartmentUtils.getAllDepartments(topList);
		ActionContext.getContext().put("departmentList", departmentList);
		return "goAddUI";
	}

	public String add(){
		Department department = new Department();
		if(parentId!=null){
			Department parent = (Department) departmentServiceInter.getById(parentId, Department.class);
			department.setDepartment(parent);
		}
		department.setName(model.getName());
		department.setSit(model.getSit());
		departmentServiceInter.save(department);
		return "add";
	}
	
	public String delete(){
		
		departmentServiceInter.delete(departmentServiceInter.getById(model.getId(), Department.class));
		return "delete";
	}
	
	public String goEditUI(){
		List<Department> topList = departmentServiceInter.getTopDepartment();
		List<Department> departmentList = DepartmentUtils.getAllDepartments(topList);
		ActionContext.getContext().put("departmentList", departmentList);
		Department department = (Department) departmentServiceInter.getById(model.getId(), Department.class);
		if(department.getDepartment()!=null){
			parentId=department.getDepartment().getId();
		}
		ActionContext.getContext().getValueStack().push(department);
		return "goEditUI";
	}
	
	public String edit(){
		Department department = (Department) departmentServiceInter.getById(model.getId(), Department.class);
		 
		if(parentId!=null){
			Department parent = (Department) departmentServiceInter.getById(parentId, Department.class);
			department.setDepartment(parent);
		}

		department.setName(model.getName());
		department.setSit(model.getSit());
		departmentServiceInter.save(department);
		
		return "edit";
	}
	
	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
}
