<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/public/commos.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
  <head>
    <title>My JSP 'departmentList.jsp' starting page</title> 
  </head>
  
  <body>
    <table border="1">
    	<tr>
    		<td width="150px">部门名</td>
    		<td width="150px">上级部门</td>
    		<td width="150px">位置</td>
    		<td width="250px">相关操作</td>
    	</tr>
    	<s:iterator value="#list">
    	<tr>
    		<td><s:a action="department_goDepartmentList?id=%{id}">${name }</s:a></td>
    		<td>${department.name }</td>
    		<td>${sit }</td>
    		<td>
    			<s:a action="department_delete?id=%{id}&parentId=%{parentId}" onclick="return window.confirm('确定删除？')">删除</s:a>
    			<s:a action="department_goEditUI?id=%{id}&parentId=%{parentId}">修改</s:a>
    		</td>
    	</tr>
    	</s:iterator>
    </table>
    <s:a action="department_goAddUI?parentId=%{parentId}">增加部门</s:a>
  </body>
</html>
