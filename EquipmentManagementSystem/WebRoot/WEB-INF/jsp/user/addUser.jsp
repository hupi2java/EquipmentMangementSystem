<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/public/commos.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
  <head>
    <title>My JSP 'addUser.jsp' starting page</title> 
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.3.1.js"></script>
  	<script type="text/javascript" src="${pageContext.request.contextPath}/js/myAJAX.js"></script>
  </head>
  
  <body>
    <s:form action="user_%{id==null?'add':'edit'}">
    	<table>
    		<s:hidden name="id"></s:hidden>
    		<tr>
				<td class="td1">部　门:</td>
				<td class="td2">
					<s:select value="department.id" name="departmentId" list="#departmentList" listKey="id" listValue="name" headerKey="" headerValue=""></s:select>
				</td>
			</tr>
    		
    		<tr>
    			<td>姓名</td>
    			<td><s:textfield name="name"></s:textfield></td>
    		</tr>
    		
    		<tr>
    			<td>工号</td>
    			<td><s:textfield name="jobId"></s:textfield></td>
    		</tr>
    		
    		<tr>
    			<td>职等</td>
    			<td><s:textfield name="post"></s:textfield></td>
    		</tr>
    		
    			
    	</table>
    	<s:submit></s:submit>
    </s:form>
  </body>
</html>
