package com.hp.base.service;

import java.io.Serializable;
import java.util.List;

public interface BaseServiceInter {
	//查询语句
		//hql方式
	public List getResult(String hql ,Object[] parameters);
		//sql方式
	public List getResultBySQL(String sql, Object[] parameters);
	//添加
	public void save(Object obj);
	//修改
	public void update(Object obj);
	//删除
	public void delete(Object obj);
	//
	public Object getById(Serializable id,Class clazz);
}
