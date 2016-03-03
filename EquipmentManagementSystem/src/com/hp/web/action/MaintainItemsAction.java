package com.hp.web.action;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hp.base.service.BaseAction;
import com.hp.domain.Datecycle;
import com.hp.domain.Machine;
import com.hp.domain.MaintainItems;
import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class MaintainItemsAction extends BaseAction<MaintainItems> {
	
	private Integer machineId;
	private Integer cycleId;
	
	public String goMaintainItemsUI(){
		List<MaintainItems> MaintainItemsList;
		List<List> list=new ArrayList<List>();
		String[] type = {"day","week","month","quarter","halfyear","year"};
		for(int i=0;i<type.length;i++){
			MaintainItemsList = maintainItemsServiceInter.getMaintainItemsByMachineId(machineId, type[i]);
			list.add(MaintainItemsList);
		}
		
		ActionContext.getContext().put("list", list);
		return "goMaintainItemsUI";
	}
	
	public String goAddUI(){
		List<Datecycle> list = datecycleServiceInter.getAll();
		ActionContext.getContext().put("list", list);
		return "goAddUI";
	}
	
	public String add(){
		Datecycle datecycle = (Datecycle) datecycleServiceInter.getById(cycleId, Datecycle.class);
		Machine machine = (Machine) machineServiceInter.getById(machineId, Machine.class);
		MaintainItems m = new MaintainItems();
		m.setDatecycle(datecycle);
		m.setMachine(machine);
		m.setNorm(model.getNorm());
		m.setProject(model.getProject());
		m.setSelection(model.getSelection());
		maintainItemsServiceInter.save(m);
		return "add";
	}

	public String delete(){
		maintainItemsServiceInter.delete(maintainItemsServiceInter.getById(model.getId(), MaintainItems.class));
		return "delete";
	}
	
	public String goEditUI(){
		List<Datecycle> list = datecycleServiceInter.getAll();
		ActionContext.getContext().put("list", list);
		MaintainItems m = (MaintainItems) maintainItemsServiceInter.getById(model.getId(), MaintainItems.class);
		ActionContext.getContext().getValueStack().push(m);
		return "goEditUI";
	}
	
	public String edit(){
		Datecycle datecycle = (Datecycle) datecycleServiceInter.getById(cycleId, Datecycle.class);
		Machine machine = (Machine) machineServiceInter.getById(machineId, Machine.class);
		MaintainItems m = (MaintainItems) maintainItemsServiceInter.getById(model.getId(), MaintainItems.class);
		m.setDatecycle(datecycle);
		m.setMachine(machine);
		m.setNorm(model.getNorm());
		m.setProject(model.getProject());
		m.setSelection(model.getSelection());
		maintainItemsServiceInter.update(m);
		
		return "edit";
	}
	
	public Integer getMachineId() {
		return machineId;
	}

	public void setMachineId(Integer machineId) {
		this.machineId = machineId;
	}

	public Integer getCycleId() {
		return cycleId;
	}

	public void setCycleId(Integer cycleId) {
		this.cycleId = cycleId;
	}
}
