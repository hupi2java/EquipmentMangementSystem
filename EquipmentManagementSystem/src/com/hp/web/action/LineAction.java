package com.hp.web.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hp.base.service.BaseAction;
import com.hp.domain.Department;
import com.hp.domain.Line;
import com.hp.utils.DepartmentUtils;
import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class LineAction extends BaseAction<Line> {

	private Integer departmentId; 
	
	public String goLineList(){
		List<Line> list = lineServiceInter.getAll();
		ActionContext.getContext().put("list", list);
		return "goLineList";
	}
	
	public String goAddUI(){
		List<Department> topList = departmentServiceInter.getTopDepartment();
		List<Department> departmentList = DepartmentUtils.getAllDepartments(topList);
		ActionContext.getContext().put("departmentList", departmentList);
		return "goAddUI";
	}
	
	public String add(){
		if(departmentId!=null){
			Department department = (Department) departmentServiceInter.getById(departmentId, Department.class);
			Line line = new Line();
			line.setDepartment(department);
			line.setName(model.getName());
			lineServiceInter.save(line);
		}
		return "add";
	}
	
	public String delete(){
		lineServiceInter.delete(lineServiceInter.getById(model.getId(), Line.class));
		return "delete";
	}
	
	public String goEditUI(){
		List<Department> topList = departmentServiceInter.getTopDepartment();
		List<Department> departmentList = DepartmentUtils.getAllDepartments(topList);
		ActionContext.getContext().put("departmentList", departmentList);
		Line line = (Line) lineServiceInter.getById(model.getId(), Line.class);
		ActionContext.getContext().getValueStack().push(line);
		return "goEditUI";
	}
	
	public String edit(){
		if(departmentId!=null){
			Department department = (Department) departmentServiceInter.getById(departmentId, Department.class);
			Line line = (Line) lineServiceInter.getById(model.getId(), Line.class);
			line.setDepartment(department);
			line.setName(model.getName());
			lineServiceInter.update(line);
		}
		return "edit";
	}

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}
}
