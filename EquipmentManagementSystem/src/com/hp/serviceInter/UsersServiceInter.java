package com.hp.serviceInter;

import java.util.List;

import com.hp.base.service.BaseServiceInter;
import com.hp.domain.Users;

public interface UsersServiceInter extends BaseServiceInter {

	public Users checkLogin(Users user);

	public List getAllUser();
}
