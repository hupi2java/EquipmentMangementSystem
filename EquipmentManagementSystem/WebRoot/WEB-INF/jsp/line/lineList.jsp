<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/public/commos.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
  <head>
    <title>My JSP 'lineList.jsp' starting page</title> 
  </head>
  
  <body>
    <table border="1">
    	<tr>
    		<td width="150px">线别名称</td>
    		<td width="150px">线别所在部门</td>
    		<td width="250px">相关操作</td>
    	</tr>
    	<s:iterator value="#list">
    		<tr>
    			<td>${name }</td>
    			<td>${department.name}</td>
    			<td>
    				<s:a action="line_delete?id=%{id}" onclick="return window.confirm('确定删除？')">删除</s:a>
    				<s:a action="line_goEditUI?id=%{id}">修改</s:a>
    			</td>
    		</tr>
    	</s:iterator>
    </table>
    <s:a action="line_goAddUI">增加线别</s:a>
  </body>
</html>
