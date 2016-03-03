<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/public/commos.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
  <head>
    <title>My JSP 'addEquipment.jsp' starting page</title> 
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.3.1.js"></script>
  	<script type="text/javascript" src="${pageContext.request.contextPath}/js/myAJAX.js?ver=1.7"></script>
  </head>
  
  <body>
  	<s:form action="equipment_%{id==null?'add':'edit'}">
  	<s:hidden name="id"></s:hidden>
    <table>
    	<tr>
    		<td>部门</td>
    		<td>
    			<s:select value="line.department.id" name="departmentId" list="#departmentList" listKey="id" listValue="name" headerKey="" headerValue="选择部门"></s:select>
    		</td>
    	</tr>
    	<tr>
    		<td>线别</td>
    		<td>
    			<select id="lineInfo" name="lineId" value="${line.id }" >
    				
    			</select>
    		</td>
    	</tr>
    	<tr>
    		<td>机器</td>
    		<td>
    			<s:select value="machine.id" name="machineId" list="#machineList" listKey="id" listValue="name" headerKey="" headerValue="选择机器"></s:select>
    		</td>
    	</tr>
    	<tr>
    		<td>设备码</td>
    		<td><s:textfield name="eid"></s:textfield> </td>
    	</tr>
    	<tr>
    		<td>位置</td>
    		<td><s:textfield name="sit"></s:textfield></td>
    	</tr>
    </table>
    <s:submit></s:submit>
    </s:form>
  </body>
</html>
