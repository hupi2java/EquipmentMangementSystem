package com.hp.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hp.base.service.BaseService;
import com.hp.domain.Department;
import com.hp.serviceInter.DepartmentServiceInter;

@Service
public class DepartmentServiceImpl extends BaseService implements
		DepartmentServiceInter {
	
	public List<String> getDepartmentByBuName(String bu){
		String hql = "select d.name from Department d where d.bu.name=?";
		Object[] parameters = {bu};
		List<String> list = getResult(hql, parameters);
		return list;
	}
	
	@Override
	public List<Department> getTopDepartment() {
		// TODO Auto-generated method stub
		String hql = "FROM Department WHERE department IS NULL";
		
		return getResult(hql, null);
	}
	
	@Override
	public List<Department> getDepartmentByParentId(Integer id) {
		// TODO Auto-generated method stub
		String hql = "FROM Department d WHERE d.department.id=?";
		Object[] parameters = {id};
		
		return getResult(hql, parameters);
	}
}
