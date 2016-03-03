package com.hp.serviceInter;

import java.util.List;

import com.hp.base.service.BaseServiceInter;
import com.hp.domain.Line;
import com.hp.domain.Users;

public interface LineServiceInter extends BaseServiceInter {
	public List<String> getLineNameByUser(Users user);
	
	public List<Line> getLineByDepartmentId(Integer departmentId);

	public List<Line> getAll();
}
