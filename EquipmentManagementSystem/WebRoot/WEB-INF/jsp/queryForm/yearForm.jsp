<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
  <head>
    <title>年保养记录表</title> 
    

  </head>
  
  <body>
  <s:debug></s:debug>
  	<table border="1" cellspacing="0" style="border:#A9A9A9 1px solid;">
  		<tr>

  			<td colspan="4" align="center">
  			<span>${machineVersion }年保养记录表　</span>
  			<span>${year }年　${equipmentEid}机台   ${lineName }线别</span>
  			</td>

  		</tr>
  		<tr>

  			<td colspan="3" width="500px" align="center">保养项目</td>
  			<td colspan="1" align="center">周期</td>

  		</tr>
  		<tr>
  			<td align="center">NO</td>
  			<td align="center">执行动作</td>
  			<td align="center">确认方法及标准</td>
  			<td width="600px" align="center">全年 </td>

  		</tr>
  		<s:iterator value="%{#request.maintainItems}" id="maintainItems" status="i">
  		<tr>
  			<td align="center"><s:property value="#i.index+1"/></td>
  			<td align="center"><s:property value="project"/></td>
  			<td align="center"><s:property value="norm" /> </td>
  			
  			<s:if test="#i.index==0">
	  			<s:iterator value="%{#request.yearRecord0}" id="yearRecord0">
	  				<td align="center">
	  				<s:property value="yearRecord0"/>
	  				</td>
	  			</s:iterator>
  			</s:if>
  			<s:elseif test="#i.index==1">
  				<s:iterator value="%{#request.yearRecord1}" id="yearRecord1">
	  				<td align="center">
	  				<s:property value="yearRecord1"/>
	  				</td>
	  			</s:iterator>
  			</s:elseif>
  			<s:elseif test="#i.index==2">
  				<s:iterator value="%{#request.yearRecord2}" id="yearRecord2">
	  				<td align="center">
	  				<s:property value="yearRecord2"/>
	  				</td>
	  			</s:iterator>
  			</s:elseif>
  			<s:elseif test="#i.index==3">
  				<s:iterator value="%{#request.yearRecord3}" id="yearRecord3">
	  				<td align="center">
	  				<s:property value="yearRecord3"/>
	  				</td>
	  			</s:iterator>
  			</s:elseif>
  			<s:elseif test="#i.index==4">
  				<s:iterator value="%{#request.yearRecord4}" id="yearRecord4">
	  				<td align="center">
	  				<s:property value="yearRecord4"/>
	  				</td>
	  			</s:iterator>
  			</s:elseif>
  			<s:elseif test="#i.index==5">
  				<s:iterator value="%{#request.yearRecord5}" id="yearRecord5">
	  				<td align="center">
	  				<s:property value="yearRecord5"/>
	  				</td>
	  			</s:iterator>
  			</s:elseif>

  		</tr>
  		</s:iterator>
  		<tr>
  			<td rowspan="6" align="center">注意事项</td>
  			<td align="center">1.xxxxxxx</td>
  			<td rowspan="3" align="center">保养者签名</td>
  			<s:iterator value="#request.maintancePerson" id="maintancePerson">
  			<td rowspan="3" align="center"><s:property value="#maintancePerson"/></td>
  			</s:iterator>
  		</tr>
  		<tr>
  			<td align="center">2.xxxxxxxx</td>
  		</tr>
  		<tr>
  			<td align="center">3.xxxxxxxx</td>
  		</tr>
  		<tr>
  			<td align="center">1.xxxxxxx</td>
  			<td rowspan="3" align="center">工程师签名</td>
  			<s:iterator value="#request.confirmPerson" id="confirmPerson">
  			<td rowspan="3" align="center"><s:property value="#confirmPerson"/></td>
  			</s:iterator>
  		</tr>
  		<tr>
  			<td align="center">2.xxxxxxxx</td>
  		</tr>
  		<tr>
  			<td align="center">3.xxxxxxxx</td>
  		</tr>
  	</table>
  </body>
</html>

