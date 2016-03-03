<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/public/commos.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
  <head>
    <title>My JSP 'machineList.jsp' starting page</title> 
  </head>
  
  <body>
    <table border="1">
    	<tr>
    		<td width="150px">机器名</td>
    		<td width="150px">机器类型</td>
    		<td width="150px">机器版本</td>
    		<td width="200px">相关操作</td>
    	</tr>
    	<s:iterator value="#machine">
    	<tr>
    		<td>${name }</td>
    		<td>${type }</td>
    		<td>${version }</td>
    		<td>
    			<s:a action="machine_delete?id=%{id}" onclick="return window.confirm('确定删除？')">删除</s:a>
    			<s:a action="machine_goEditUI?id=%{id}">修改</s:a>
    			<s:a action="maintainItems_goMaintainItemsUI?machineId=%{id}">保养项目管理</s:a>
    		</td>
    	</tr>
    	</s:iterator>
    </table>
    <s:a action="machine_goAddUI">增加</s:a>
  </body>
</html>
