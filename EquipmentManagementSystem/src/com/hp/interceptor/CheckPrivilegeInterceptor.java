package com.hp.interceptor;

import com.hp.domain.Users;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class CheckPrivilegeInterceptor extends AbstractInterceptor{

	@Override
	public String intercept(ActionInvocation arg0) throws Exception {
		// TODO Auto-generated method stub
		//获取用户信息
		Users user = (Users) ActionContext.getContext().getSession().get("user");
		
		String namespace = arg0.getProxy().getNamespace();
		String actionName = arg0.getProxy().getActionName();
		String privUrl = namespace+actionName;
		
		//假如未登陆
		if(user == null){
			if(privUrl.startsWith("/log_")){
				return arg0.invoke();
			}else
				return "goLogin";
		}else{//已登录 判断权限
			if(user.hasPrivilegeByUrl(privUrl)){
				return arg0.invoke();
			}else{
				return "noPrivilegeError";
			}
		}

	}
	
	
}