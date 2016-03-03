package com.hp.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hp.base.service.BaseService;
import com.hp.domain.Line;
import com.hp.domain.Users;
import com.hp.serviceInter.LineServiceInter;

@Service
public class LineServiceImpl extends BaseService implements LineServiceInter {

	public List<String> getLineNameByUser(Users user){
		String hql = "select l.name from Line l where l.department.id=?";
		Object[] parameters = {user.getDepartment().getId()};
 		List<String> list= getResult(hql, parameters);

		return list;
	}
	
	@Override
	public List<Line> getLineByDepartmentId(Integer departmentId) {
		// TODO Auto-generated method stub
		String hql = "From Line l where l.department.id=?";
		Object[] parameters = {departmentId};
		return getResult(hql, parameters);
	}
	
	@Override
	public List<Line> getAll() {
		// TODO Auto-generated method stub
		String hql="FROM Line";
		
		return getResult(hql, null);
	}
}
