package com.hp.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hp.base.service.BaseService;
import com.hp.domain.Machine;
import com.hp.serviceInter.MachineServiceInter;

@Service
public class MachineServiceImpl extends BaseService implements
		MachineServiceInter {
	@Override
	public List<Machine> getAll() {
		// TODO Auto-generated method stub
		String hql="FROM Machine";	
		return getResult(hql, null);
	}
}
