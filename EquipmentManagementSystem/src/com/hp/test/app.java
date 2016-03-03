package com.hp.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class app {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
		user u = (user) ac.getBean("u");
		System.out.println(u.getName());
	}

}
