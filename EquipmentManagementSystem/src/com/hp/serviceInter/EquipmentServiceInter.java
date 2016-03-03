package com.hp.serviceInter;



import java.util.List;

import com.hp.base.service.BaseServiceInter;
import com.hp.domain.Equipment;
import com.hp.domain.Users;

public interface EquipmentServiceInter extends BaseServiceInter {
	/**
	 * 根据设备码（eid）获取设备信息
	 * @param equipment
	 * @return
	 */
	public Equipment getResultByEid(Equipment equipment);
	
	/**
	 * 获取所有设备信息
	 * @return
	 */
	public List<Equipment> getAll();
	
	/**
	 * 获取用户所在部门下的所有设备信息
	 * @param user
	 * @return
	 */
	public List<Equipment> getEquipmentListByUser(Users user);
}
