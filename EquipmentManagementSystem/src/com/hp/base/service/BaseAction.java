package com.hp.base.service;

import java.lang.reflect.ParameterizedType;

import javax.annotation.Resource;

import com.hp.serviceInter.DatecycleServiceInter;
import com.hp.serviceInter.DepartmentServiceInter;
import com.hp.serviceInter.EquipmentServiceInter;
import com.hp.serviceInter.LineServiceInter;
import com.hp.serviceInter.MachineServiceInter;
import com.hp.serviceInter.MaintainItemsServiceInter;
import com.hp.serviceInter.PrivilegeServiceInter;
import com.hp.serviceInter.UsersServiceInter;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public abstract class BaseAction<T> extends ActionSupport implements ModelDriven<T> {
	
	protected T model;
	
	public BaseAction(){
		Class<T> clazz;
		ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
		clazz = (Class<T>) pt.getActualTypeArguments()[0];
		try {
			model = clazz.newInstance();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new RuntimeException();
		} 
	}
	
	@Override
	public T getModel() {
		// TODO Auto-generated method stub
		return model;
	}
	
	@Resource
	protected UsersServiceInter usersServiceInter;
	
	@Resource
	protected DepartmentServiceInter departmentServiceInter;
	
	@Resource
	protected EquipmentServiceInter equipmentServiceInter;
	
	@Resource
	protected MachineServiceInter machineServiceInter;
	
	@Resource
	protected MaintainItemsServiceInter maintainItemsServiceInter;
	
	@Resource
	protected DatecycleServiceInter datecycleServiceInter;
	
	@Resource
	protected LineServiceInter lineServiceInter;
	
	@Resource
	protected PrivilegeServiceInter privilegeServiceInter;
}

