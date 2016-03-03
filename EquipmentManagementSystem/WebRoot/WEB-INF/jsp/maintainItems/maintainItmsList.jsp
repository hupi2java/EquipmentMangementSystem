<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/public/commos.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
  <head>
    <title>My JSP 'maintainItmsList.jsp' starting page</title> 
  </head>
  
  <body>
  	<s:a action="maintainItems_goAddUI?machineId=%{machineId}">增加保养项目</s:a>
  	<br/>
  	<s:iterator value="#list" id="list">
    <table border="1">
    	<tr>
    		<td width="200px">保养项目</td>
    		<td width="200px">保养标准</td>
    		<td width="200px">保养周期</td>
    		<td width="200px">相关操作</td>
    	</tr>
    	<s:iterator value="top" >
    	<tr>
    		<td>${project }</td>
    		<td>${norm }</td>
    		<td>${datecycle.type }</td>
    		<td>
				<s:a action="maintainItems_delete?id=%{id}&machineId=%{machineId}" onclick="return window.confirm('确定删除？')">删除</s:a>
				<s:a action="maintainItems_goEditUI?id=%{id}&machineId=%{machineId}">修改</s:a>
			</td>
    	</tr>
    	</s:iterator>
    </table>
    <br/>
    </s:iterator>
    <br/><br/>
  </body>
</html>
