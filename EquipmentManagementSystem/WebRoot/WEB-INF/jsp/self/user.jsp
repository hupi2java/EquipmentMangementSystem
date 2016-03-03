<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/public/commos.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
  <head>
    <title>main</title> 
  </head>
  
  <body>
  		<jsp:include page="/WEB-INF/jsp/equipMaintain/head.jsp" />
	    <div class="common">
    	<form action="${pageContext.request.contextPath}/repassword.do?flag=changePassword" method="post">
    	<table id="changePwd">
    		<tr>
    			<td>原密码:</td>
    			<td><input type="password" name="oldPassword"/></td>
    		</tr>
    		<tr>
    			<td>新密码:</td>
    			<td><input type="password" name="newPassword"/></td>
    		</tr>
    		<tr>
    			<td>请确认:</td>
    			<td><input type="password" name="secondPassword"/></td>
    		</tr>
    		<tr>
    			<td></td>
    			<td><input type="submit" value="提交"/></td>
    		</tr>
    	</table>
    	</form>
    </div>
  </body>
</html>



