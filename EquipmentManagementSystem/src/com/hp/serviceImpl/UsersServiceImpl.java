package com.hp.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hp.base.service.BaseService;
import com.hp.domain.Users;
import com.hp.serviceInter.UsersServiceInter;

@Service
public class UsersServiceImpl extends BaseService implements UsersServiceInter {
	@Override
	public Users checkLogin(Users user) {
		// TODO Auto-generated method stub
		String hql = "from Users where jobId=? and password=?";
		String[] parameters = {user.getJobId(),user.getPassword()};
		List list = getResult(hql, parameters);
		
		if(list!=null && list.size()==1){
			Users u = (Users) list.get(0);
				return u;
		}
		return null;
	}
	
	@Override
	public List getAllUser() {
		// TODO Auto-generated method stub
		String hql = "FROM Users";
		List list = getResult(hql, null);
		return list;
	}
	
}
