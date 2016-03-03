package com.hp.base.service;

import java.io.Serializable;
import java.util.List;

public interface BaseServiceInter {
	//��ѯ���
		//hql��ʽ
	public List getResult(String hql ,Object[] parameters);
		//sql��ʽ
	public List getResultBySQL(String sql, Object[] parameters);
	//���
	public void save(Object obj);
	//�޸�
	public void update(Object obj);
	//ɾ��
	public void delete(Object obj);
	//
	public Object getById(Serializable id,Class clazz);
}
