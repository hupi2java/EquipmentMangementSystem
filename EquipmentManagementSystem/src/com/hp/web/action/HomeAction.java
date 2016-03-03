package com.hp.web.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;


@Controller
@Scope("prototype")
public class HomeAction extends ActionSupport {

	public String head(){
		
		return "head";
	}
	
	public String left(){
		
		return "left";
	}
	
	public String right(){
		
		return "right";
	}
}
