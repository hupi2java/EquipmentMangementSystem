<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/public/commos.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
  <head>
    <title>select</title> 
   
  </head>
  
  <body>
   	<jsp:include page="head.jsp" />
   	<div class="common">
   		<div style="margin-left: 40px;margin-top: 20px;">
   		<form action="${pageContext.request.contextPath}/maintain_goMaintainUI.do" method="post">
	   		请选择线别：
	   		<select style="width: 180px;" name="lineName" id="lineNameOfSelect">
	   			<option></option>
	   			<s:iterator value="%{#request.LineName}" id="lineName" >
	   			<option value="${lineName }">${lineName }</option>
				</s:iterator>
	   		</select>
	   		<input type="submit" value="保养"/>
	   	</form>
   		</div>
   	</div>
  </body>
</html>
