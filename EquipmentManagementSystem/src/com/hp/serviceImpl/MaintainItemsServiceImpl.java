package com.hp.serviceImpl;

import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.hp.base.service.BaseService;
import com.hp.domain.*;
import com.hp.serviceInter.MaintainItemsServiceInter;

@Service
public class MaintainItemsServiceImpl extends BaseService implements
		MaintainItemsServiceInter {

	public List<MaintainItems> getMaintainItems(String equipmentName,String type){
		
//		String sql = "select m.* from maintainItems as m "+
//				"join machine "+
//				"on machine.id=m.m_id "+
//			"join datecycle "+
//				"on m.d_id=datecycle.id "+
//			"where datecycle.type=? and machine.name=?";
//		String[] parameters={type,equipmentName};
//		
//		List<Object[]> list = getResultBySQL(sql, parameters);
//		System.out.println(list.size());
//		MaintainItems[] maintainItems = new MaintainItems[list.size()];
//		if(list!=null&&list.size()>0){
//			for(int i=0;i<list.size();i++){
//				System.out.println(Integer.parseInt(list.get(i)[0].toString()));
//				maintainItems[i].setId(Integer.parseInt(list.get(i)[0].toString()));
//				maintainItems[i].setProject(list.get(i)[1].toString());
//				maintainItems[i].setNorm(list.get(i)[2].toString());
//				maintainItems[i].setSelection(Integer.parseInt(list.get(i)[3].toString()));
//				System.out.println(maintainItems[i].getId()+" "+maintainItems[i].getProject());
//			}
//		}
		
		String hql = "from MaintainItems m where m.datecycle.type=? and m.machine.name=?";
		String[] parameters={type,equipmentName};
		
		List<MaintainItems> list = getResult(hql, parameters);
	 		
		return list;
	}
	
	public List<MaintainItems> getMaintainItems(Integer equipmentId,String type){
		Equipment equipment=(Equipment) getById(equipmentId, Equipment.class);
		String hql = "from MaintainItems m where m.datecycle.type=? and m.machine.id=?";
		Object[] parameters={type,equipment.getMachine().getId()};
		List<MaintainItems> list = getResult(hql, parameters);	 		
		return list;
	}
	
	@Override
	public List<MaintainItems> getMaintainItemsByMachineId(Integer machineId,
			String type) {
		// TODO Auto-generated method stub
		String hql = "FROM MaintainItems m WHERE m.datecycle.type=? AND m.machine.id=?";
		Object[] parameters = {type,machineId};
		List<MaintainItems> list = getResult(hql, parameters);
		return list;
	}
}
