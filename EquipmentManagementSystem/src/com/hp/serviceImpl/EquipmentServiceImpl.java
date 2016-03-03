package com.hp.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hp.base.service.BaseService;
import com.hp.domain.Equipment;
import com.hp.domain.Users;
import com.hp.serviceInter.EquipmentServiceInter;

@Service
public class EquipmentServiceImpl extends BaseService implements
		EquipmentServiceInter {

	@Override
	public Equipment getResultByEid(Equipment equipment) {
		// TODO Auto-generated method stub
		String hql = "from Equipment where eid=?";
		String[] parameters = {equipment.getEid()};
		List list = getResult(hql, parameters);
		
		if(list != null && list.size()==1){
			Equipment newEquipment = (Equipment) list.get(0);
			if(newEquipment.getLine().getName().equals(equipment.getLine().getName())){
				return newEquipment;
			}
			return null;
		}
		return null;
	}
	
	//获取所有的设备信息
	public List<Equipment> getAll(){
		String hql="from Equipment";
		List<Equipment> list = getResult(hql, null);
		return list;
	}
	
	public List<Equipment> getEquipmentListByUser(Users user){
		String hql="from Equipment e where e.line.department.id=?";
		Object[] parameters={user.getDepartment().getId()};
		List<Equipment> list = getResult(hql, parameters);
		return list;
	}
	
}
