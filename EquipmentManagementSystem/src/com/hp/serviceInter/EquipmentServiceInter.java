package com.hp.serviceInter;



import java.util.List;

import com.hp.base.service.BaseServiceInter;
import com.hp.domain.Equipment;
import com.hp.domain.Users;

public interface EquipmentServiceInter extends BaseServiceInter {
	/**
	 * �����豸�루eid����ȡ�豸��Ϣ
	 * @param equipment
	 * @return
	 */
	public Equipment getResultByEid(Equipment equipment);
	
	/**
	 * ��ȡ�����豸��Ϣ
	 * @return
	 */
	public List<Equipment> getAll();
	
	/**
	 * ��ȡ�û����ڲ����µ������豸��Ϣ
	 * @param user
	 * @return
	 */
	public List<Equipment> getEquipmentListByUser(Users user);
}
