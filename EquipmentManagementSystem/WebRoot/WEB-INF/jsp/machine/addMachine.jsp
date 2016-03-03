<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/public/commos.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
  <head>
    <title>My JSP 'addMachine.jsp' starting page</title> 
  </head>
  
  <body>
  	<s:form action="machine_%{id==null?'add':'edit'}">
    <table>
    	<s:hidden name='id'></s:hidden>
    	<tr>
    		<td>机器名称</td>
    		<td>
    			<s:textfield name="name"></s:textfield>
    		</td>
    	</tr>
    	<tr>
    		<td>机器类型</td>
    		<td>
    			<s:textfield name="type"></s:textfield>
    		</td>
    	</tr>
    	<tr>
    		<td>机器型号</td>
    		<td>
    			<s:textfield name="version"></s:textfield>
    		</td>
    	</tr>
    </table>
    <s:submit></s:submit>
    </s:form>
  </body>
</html>
