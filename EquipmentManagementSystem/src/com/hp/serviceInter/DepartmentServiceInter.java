package com.hp.serviceInter;

import java.util.List;

import com.hp.base.service.BaseServiceInter;
import com.hp.domain.Department;

public interface DepartmentServiceInter extends BaseServiceInter {

	public List<String> getDepartmentByBuName(String bu);

	public List<Department> getTopDepartment();

	public List<Department> getDepartmentByParentId(Integer id);
}
