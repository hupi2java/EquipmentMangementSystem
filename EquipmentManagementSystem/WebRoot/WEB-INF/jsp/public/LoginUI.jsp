<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/public/commos.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
  <head>
    <title>login</title> 
    
  	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css" type="text/css"></link>
  	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css" type="text/css"></link>
  	
  	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.3.1.js"></script>
  	<script type="text/javascript" src="${pageContext.request.contextPath}/js/myAJAX.js"></script>
  	
  	<script language="JavaScript"> 
		if (window != top) 
		top.location.href = location.href; 
	</script>
  	
  	</head>
  
  <body>
  	<div class="login">
  		<!-- 标图栏 -->
  		<div class="title">
  			<img class="img1" src="${pageContext.request.contextPath}/images/white.gif"/>
  			<img class="img2" src="${pageContext.request.contextPath}/images/BigTitle.gif"/>
  		</div>
  		
  		<!-- 登录表单 -->
	  	<div class="form">
	  		<img class="img3" src="${pageContext.request.contextPath}/images/white.gif"/>
			<form action="${pageContext.request.contextPath}/log_login.do" method="post">
				<table >
					
					<tr>
						<td class="td1">用户名:</td>
						<td class="td2"><input type="text" name="jobId"/></td>
					</tr>
					
					<tr>
						<td class="td1">密　码:</td>
						<td class="td2"><input type="password" name="password"/></td>
					</tr>
					
					<tr>
						<td class="td1"></td>
						<td class="td2"><input type="submit" value="登录"/></td>
					</tr>
				</table>
			</form>    
	    </div>
	    
  	</div>
  </body>
</html>
