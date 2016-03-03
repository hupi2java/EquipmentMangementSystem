package com.hp.interceptor;

import java.util.Collection;
import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.hp.domain.Privilege;
import com.hp.serviceInter.PrivilegeServiceInter;

public class InitListener implements ServletContextListener{
	
	public void contextInitialized(ServletContextEvent arg0){
		
		ApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(arg0.getServletContext());
		PrivilegeServiceInter privilegeServiceInter = (PrivilegeServiceInter) ac.getBean("privilegeServiceImpl");
		//准备数据
//		List<Privilege> topPrivilegeList = privilegeServiceInter.findTopList();
//		arg0.getServletContext().setAttribute("topPrivilegeList", topPrivilegeList);
//	
		Collection<String> allPrivilegeUrlList = privilegeServiceInter.getAllPrivilegeUrls();
		arg0.getServletContext().setAttribute("allPrivilegeUrlList", allPrivilegeUrlList);
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
