package com.hp.ajax.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hp.domain.Line;
import com.hp.serviceInter.DepartmentServiceInter;
import com.hp.serviceInter.LineServiceInter;
import com.opensymphony.xwork2.ActionSupport;

@Controller
@Scope("prototype")
public class GetDepartment extends ActionSupport {

	private String bu;
	private Integer departmentId;
	@Resource
	private DepartmentServiceInter departmentServiceInter;
	
	@Resource
	private LineServiceInter lineServiceInter;
	
	public String getDeparmentInfo() throws IOException{
		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter out= response.getWriter();
		
		List<String> list = departmentServiceInter.getDepartmentByBuName(bu);
		String res ="";
		for(int i=0;i<list.size();i++){
			res +="<option value="+list.get(i).toString()+">"+list.get(i).toString()+"</option>";			
		}
		out.write(res);
		return null;
	}
	
	public String getLineInfoByDepartmentId() throws IOException{
		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter out= response.getWriter();
		
		List<Line> list = lineServiceInter.getLineByDepartmentId(departmentId);
		String res="";
		for(Line line: list ){
			res +="<option value="+line.getId()+">"+line.getName()+"</option>";
		}
		out.write(res);
		return null;
	}
	

	public String getBu() {
		return bu;
	}

	public void setBu(String bu) {
		this.bu = bu;
	}

	public DepartmentServiceInter getDepartmentServiceInter() {
		return departmentServiceInter;
	}

	public void setDepartmentServiceInter(
			DepartmentServiceInter departmentServiceInter) {
		this.departmentServiceInter = departmentServiceInter;
	}

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}
}
