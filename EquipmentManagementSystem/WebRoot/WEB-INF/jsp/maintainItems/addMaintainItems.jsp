<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/public/commos.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
  <head>
    <title>My JSP 'addMaintainItems.jsp' starting page</title> 
  </head>
  
  <body>
  	<s:form action="maintainItems_%{id==null?'add':'edit'}">
   	<table>
   		<s:hidden name="machineId"></s:hidden>
   		<s:hidden name="id"></s:hidden>
   		<tr>
   			<td>周期</td>
   			<td>
   				<s:select name="cycleId" value="datecycle.id" list="#list" listKey="id" listValue="type" headerKey="" headerValue="选择周期"></s:select>
   			</td>
   		</tr>
   		<tr>
   			<td>保养项目</td>
   			<td><s:textfield name="project"></s:textfield></td>
   		</tr>
   		<tr>
   			<td>确认标准</td>
   			<td><s:textfield name="norm"></s:textfield></td>
   		</tr>
   		<tr>
   			<td>保养类型</td>
   			<td><s:select value="selection" name="selection" list="#{0:'保养结果为选择类型',1:'保养结果为自述型'}" listKey="key" listValue="value" headerKey="" headerValue="选择保养类型"></s:select></td>
   		</tr>
   	</table>
   	<s:submit></s:submit>
   	</s:form>
  </body>
</html>
