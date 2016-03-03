package com.hp.interceptor;

import com.hp.domain.Users;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class CheckPrivilegeInterceptor extends AbstractInterceptor{

	@Override
	public String intercept(ActionInvocation arg0) throws Exception {
		// TODO Auto-generated method stub
		//��ȡ�û���Ϣ
		Users user = (Users) ActionContext.getContext().getSession().get("user");
		
		String namespace = arg0.getProxy().getNamespace();
		String actionName = arg0.getProxy().getActionName();
		String privUrl = namespace+actionName;
		
		//����δ��½
		if(user == null){
			if(privUrl.startsWith("/log_")){
				return arg0.invoke();
			}else
				return "goLogin";
		}else{//�ѵ�¼ �ж�Ȩ��
			if(user.hasPrivilegeByUrl(privUrl)){
				return arg0.invoke();
			}else{
				return "noPrivilegeError";
			}
		}

	}
	
	
}