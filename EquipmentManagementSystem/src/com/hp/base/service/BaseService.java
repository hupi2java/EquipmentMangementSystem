package com.hp.base.service;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

@Transactional//添加事物
public abstract class BaseService implements BaseServiceInter {
	@Resource//添加注释
	private  SessionFactory sessionFactory;
	
	public List getResultBySQL(String sql, Object[] parameters){
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(sql);
		if(parameters!=null && parameters.length>0){
			for(int i=0;i<parameters.length;i++){
				query.setParameter(i, parameters[i]);
			}
		}
		return query.list();
	}
	
	@Override
	public List getResult(String hql, Object[] parameters) {
		// TODO Auto-generated method stub
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		if(parameters!=null && parameters.length>0){
			for(int i=0;i<parameters.length;i++){
				query.setParameter(i, parameters[i]);
			}
		}
		
		return query.list();
	}
	
	@Override
	public void save(Object obj) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().save(obj);
	}
	
	@Override
	public void delete(Object obj) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().delete(obj);
	}
	
	@Override
	public void update(Object obj) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().update(obj);
	}
	
	@Override
	public Object getById(Serializable id, Class clazz) {
		// TODO Auto-generated method stub
		return sessionFactory.getCurrentSession().get(clazz, id);	
	}
	

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}
