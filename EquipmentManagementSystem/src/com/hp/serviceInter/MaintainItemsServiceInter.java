package com.hp.serviceInter;

import java.util.List;

import com.hp.base.service.BaseServiceInter;
import com.hp.domain.MaintainItems;

public interface MaintainItemsServiceInter extends BaseServiceInter {

	public List<MaintainItems> getMaintainItems(String equipmentName,String type);
	public List<MaintainItems> getMaintainItems(Integer equipmentId,String type);
	public List<MaintainItems> getMaintainItemsByMachineId(Integer machineId,
			String type);
}
