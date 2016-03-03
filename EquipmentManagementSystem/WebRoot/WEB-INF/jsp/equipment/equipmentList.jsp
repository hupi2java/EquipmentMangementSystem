<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/public/commos.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
  <head>
    <title>My JSP 'equipmentList.jsp' starting page</title> 
  </head>
  
  <body>
    <table border="1">
    	<tr>
    		<td width="250px">设备码</td>
    		<td width="150px">设备类型</td>
    		<td width="150px">设备信息</td>
    		<td width="200px">相关操作</td>
    	</tr>
    	<s:iterator value="#equipment">
    	<tr>
    		<td>${eid}</td>
    		<td>${machine.version }</td>
    		<td>${line.department.name}　${line.name }</td>
    		<td>
    			<s:a action="equipment_delete?id=%{id}" onclick="return window.confirm('确定删除')">删除</s:a>
    			<s:a action="equipment_goEditUI?id=%{id}">修改</s:a>
    		</td>
    	</tr>
    	</s:iterator>
    </table>
    <s:a action="equipment_goAddUI">增加</s:a>
  </body>
</html>
