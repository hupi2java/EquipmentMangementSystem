package com.hp.web.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hp.base.service.BaseAction;
import com.hp.domain.Machine;
import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class MachineAction extends BaseAction<Machine> {

	public String goMachineList(){
		List<Machine> list = machineServiceInter.getAll();
		ActionContext.getContext().put("machine", list);
		return "goMachineList";
	}
	
	public String goAddUI(){
		
		return "goAddUI";
	}
	
	public String add(){
		machineServiceInter.save(model);
		return "add";
	}
	
	public String delete(){
		machineServiceInter.delete(machineServiceInter.getById(model.getId(), Machine.class));
		return "delete";
	}
	
	public String goEditUI(){
		Machine m = (Machine) machineServiceInter.getById(model.getId(), Machine.class);
		ActionContext.getContext().getValueStack().push(m);
		return "goEditUI";
	}
	
	public String edit(){
		Machine m = (Machine) machineServiceInter.getById(model.getId(), Machine.class);
		m.setName(model.getName());
		m.setType(model.getType());
		m.setVersion(model.getVersion());
		machineServiceInter.update(m);
		return "edit";
	}
}
