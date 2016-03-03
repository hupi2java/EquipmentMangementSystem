package com.hp.serviceImpl;

import java.util.Collection;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hp.base.service.BaseService;
import com.hp.domain.Privilege;
import com.hp.serviceInter.PrivilegeServiceInter;

@Controller
@Scope("prototype")
public class PrivilegeServiceImpl extends BaseService implements PrivilegeServiceInter{

	@Override
	public List<Privilege> findTopList() {
		// TODO Auto-generated method stub
		String hql = "FROM Privilege p WHERE p.privilege IS NULL";
		return  getResult(hql, null);
	}

	@Override
	public Collection<String> getAllPrivilegeUrls() {
		// TODO Auto-generated method stub
		String hql = "SELECT DISTINCT p.url FROM Privilege p WHERE p.url IS NOT NULL";
		return getResult(hql, null);
	}

	@Override
	public List<Privilege> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Privilege> getByIds(Integer[] privilegeIds) {
		// TODO Auto-generated method stub
		return this.getSessionFactory().getCurrentSession().
				createQuery("FROM Privilege WHERE id IN (:privilegeIds)").setParameterList("privilegeIds", privilegeIds).list();
		
	}
		
}
