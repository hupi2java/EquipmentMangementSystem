package com.hp.web.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hp.base.service.BaseAction;
import com.hp.domain.Department;
import com.hp.domain.Equipment;
import com.hp.domain.Line;
import com.hp.domain.Machine;
import com.hp.serviceInter.LineServiceInter;
import com.hp.utils.DepartmentUtils;
import com.opensymphony.xwork2.ActionContext;


@Controller
@Scope("prototype")
public class EquipmentAction extends BaseAction<Equipment> {

	private Integer departmentId;
	private Integer lineId;
	private Integer machineId;
	
	public String goEqupimentList(){
		List<Equipment> list = equipmentServiceInter.getAll();
		ActionContext.getContext().put("equipment", list);
		return "goEqupimentList";
	}
	
	public String goAddUI(){
		List<Department> topList = departmentServiceInter.getTopDepartment();
		List<Department> departmentList = DepartmentUtils.getAllDepartments(topList);
		List<Machine> machineList = machineServiceInter.getAll();
		ActionContext.getContext().put("departmentList", departmentList);
		ActionContext.getContext().put("machineList", machineList);
		return "goAddUI";
	}
	
	public String add(){
		Line line = (Line) lineServiceInter.getById(lineId, Line.class);
		Machine machine = (Machine) machineServiceInter.getById(machineId, Machine.class);
		Equipment equipment = new Equipment();
		equipment.setEid(model.getEid());
		equipment.setLine(line);
		equipment.setMachine(machine);
		equipment.setSit(model.getSit());
		equipmentServiceInter.save(equipment);
		return "add";
	}

	public String delete(){
		equipmentServiceInter.delete(equipmentServiceInter.getById(model.getId(), Equipment.class));
		return "delete";
	}
	
	public String goEditUI(){
		List<Department> topList = departmentServiceInter.getTopDepartment();
		List<Department> departmentList = DepartmentUtils.getAllDepartments(topList);
		List<Machine> machineList = machineServiceInter.getAll();
		Equipment equipment = (Equipment) equipmentServiceInter.getById(model.getId(), Equipment.class);
		ActionContext.getContext().put("departmentList", departmentList);
		ActionContext.getContext().put("machineList", machineList);
		ActionContext.getContext().getValueStack().push(equipment);
		return "goEditUI";
	}
	
	public String edit(){
		Line line = (Line) lineServiceInter.getById(lineId, Line.class);
		Machine machine = (Machine) machineServiceInter.getById(machineId, Machine.class);
		Equipment equipment = (Equipment) equipmentServiceInter.getById(model.getId(), Equipment.class);
		equipment.setEid(model.getEid());
		equipment.setLine(line);
		equipment.setMachine(machine);
		equipment.setSit(model.getSit());
		equipmentServiceInter.save(equipment);
		return "edit";
	}
	
	
	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public Integer getLineId() {
		return lineId;
	}

	public void setLineId(Integer lineId) {
		this.lineId = lineId;
	}

	public Integer getMachineId() {
		return machineId;
	}

	public void setMachineId(Integer machineId) {
		this.machineId = machineId;
	}
}
