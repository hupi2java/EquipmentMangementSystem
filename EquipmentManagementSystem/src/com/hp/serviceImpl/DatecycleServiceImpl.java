package com.hp.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hp.base.service.BaseService;
import com.hp.domain.Datecycle;
import com.hp.serviceInter.DatecycleServiceInter;

@Service
public class DatecycleServiceImpl extends BaseService implements
		DatecycleServiceInter {
	
	@Override
	public List<Datecycle> getAll() {
		// TODO Auto-generated method stub
		String hql = "FROM Datecycle";
		return getResult(hql, null);
	}
}
