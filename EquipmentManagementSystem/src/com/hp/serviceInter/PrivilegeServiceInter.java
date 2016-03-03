package com.hp.serviceInter;

import java.util.Collection;
import java.util.List;

import com.hp.base.service.BaseServiceInter;
import com.hp.domain.Privilege;

public interface PrivilegeServiceInter extends BaseServiceInter {

	public List<Privilege> findTopList();
	public Collection<String> getAllPrivilegeUrls();
	public List<Privilege> findAll();
	public List<Privilege> getByIds(Integer[] privilegeIds);
}
