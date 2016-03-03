<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/public/commos.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
  <head>
    <title>My JSP 'userList.jsp' starting page</title> 
  </head>
  
  <body>
    	<table border="1">
		<tr>
			<th width="150px">工号</th>
			<th width="150px">姓名</th>
			<th width="150px">所属部门</th>
			<th width="200px">相关操作</th>
		</tr>
	
		<s:iterator value="#user" id="user">
			<tr>
				<td align="center">${jobId }</td>
				<td align="center">${name }</td>
				<td align="center">${department.name }</td>
				<td align="center">
					<s:a action="user_delete?id=%{id}" onclick="return window.confirm('确定删除？')">删除</s:a>
					<s:a action="user_goEditUI?id=%{id}">修改</s:a>
					<s:a action="user_initPassword?id=%{id}" onclick="return window.confirm('确定初始化密码？')">初始化密码</s:a>
					<s:a action="user_setPrivilegeUI?id=%{id}">权限设置</s:a>
				</td>
			</tr>
		</s:iterator>
	</table>
	<s:a action="user_goAddUI">添加用户</s:a>
  </body>
</html>
