<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/public/commos.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
  <head>
    <title>My JSP 'addDepartment.jsp' starting page</title> 
  </head>
  
  <body>
    <s:form action="department_%{id==null?'add':'edit'}">
    	<table>
    	<s:hidden name="id"></s:hidden>
    		<tr>
    			<td>上级部门</td>
    			<td>
    				<s:select value="parentId" name="parentId" list="#departmentList" listKey="id" listValue="name" headerKey="" headerValue="选择上级部门"></s:select>
    			</td>
    		</tr>
    		<tr>
    			<td>部门名称</td>
    			<td>
    				<s:textfield name="name"></s:textfield>
    			</td>
    		</tr>
    		<tr>
    			<td>部门位置</td>
    			<td>
    				<s:textfield name="sit"></s:textfield>
    			</td>
    		</tr>
    	</table>
    	<s:submit></s:submit>
    </s:form>
  </body>
</html>
