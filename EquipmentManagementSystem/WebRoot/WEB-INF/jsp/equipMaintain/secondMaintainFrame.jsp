<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/public/commos.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
  <head>
    <title>secondMaintainFrame</title> 
    
  	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.3.1.js"></script>
  	<script type="text/javascript" src="${pageContext.request.contextPath}/js/publicJs.js"></script>

  </head>
  
  <body>

   	<jsp:include page="head.jsp" />
   	
   	<div class="common">
   		<div class="inputInfo">
   			<form action="#" method="post">
   			<table>
   				<tr>
   					<td>设备码:</td>
   					<td><input class="text1" type="text" value="${equipment.eid }"/></td>
   					<td>保养类型:</td>
   					<td><input class="text1" type="text" /></td>
   					<td>设备名称:</td>
   					<td><input class="text1" type="text" value="${equipment.machine.name }"/></td>
   				</tr>
   				<tr>
   					<td>设备型号:</td>
   					<td><input class="text1" type="text" value="${equipment.machine.version }"/></td>
   					<td>设备线别:</td>
   					<td><input class="text1" type="text" value="${equipment.line.name }"/></td>
   					<td>设备位置:</td>
   					<td><input class="text1" type="text" value="${equipment.sit }"/></td>
   				</tr>
   			</table>
   			</form>
   		</div>
   		
   		<div class="maintainTable">
   			<table border="1" cellspacing="0" style="border:#A9A9A9 1px solid;">
   				<tr>
   					<td>警告</td>
   					<td>保养周期</td>
   					<td>上次保养时间</td>
   					<td>下次保养时间</td>
   					<td>周期（天）</td>
   					<td>周期（小时）</td>
   					<td>运转时间（天）</td>
   					<td>运转时间（小时）</td>
   				</tr>
   				<tr>
   					<td id="colorOfDay"></td>
   					<td><a href="${pageContext.request.contextPath}/maintain_goMaintainItems.do?datetype=day&equipmentName=${equipment.machine.name }&lineName=${equipment.line.name }&equipmentId=${equipment.eid }">Day</a></td>
   					<td>${lastTimeOfType[0] }</td>
   					<td>${nextTimeOfType[0] }</td>
   					<td>1</td>
   					<td>24</td>
   					<td>${runTimeOfDay[0] }</td>
   					<td id="runTimeOfDay">${runTimeOfHour[0] }</td>
   				</tr>
   				<tr>
   					<td id="colorOfWeek"></td>
   					<td><a href="${pageContext.request.contextPath}/maintain_goMaintainItems.do?datetype=week&equipmentName=${equipment.machine.name }&lineName=${equipment.line.name }&equipmentId=${equipment.eid }">Week</a></td>
   					<td>${lastTimeOfType[1] }</td>
   					<td>${nextTimeOfType[1] }</td>
   					<td>7</td>
   					<td>168</td>
   					<td>${runTimeOfDay[1] }</td>
   					<td id="runTimeOfWeek">${runTimeOfHour[1] }</td>				
   				</tr>
   				<tr>
   					<td id="colorOfMonth"></td>
   					<td><a href="${pageContext.request.contextPath}/maintain_goMaintainItems.do?datetype=month&equipmentName=${equipment.machine.name }&lineName=${equipment.line.name }&equipmentId=${equipment.eid }">Month</a></td>
   					<td>${lastTimeOfType[2] }</td>
   					<td>${nextTimeOfType[2] }</td>
   					<td>30</td>
   					<td>720</td>
   					<td>${runTimeOfDay[2] }</td>
   					<td id="runTimeOfMonth">${runTimeOfHour[2] }</td>   					
   				</tr>
   				<tr>
   					<td id="colorOfQuarter"></td>
   					<td><a href="${pageContext.request.contextPath}/maintain_goMaintainItems.do?datetype=quarter&equipmentName=${equipment.machine.name }&lineName=${equipment.line.name }&equipmentId=${equipment.eid }">Quarter</a></td>
   					<td>${lastTimeOfType[3] }</td>
   					<td>${nextTimeOfType[3] }</td>
   					<td>92</td>
   					<td>2208</td>
   					<td>${runTimeOfDay[3] }</td>
   					<td id="runTimeOfQuarter">${runTimeOfHour[3] }</td>   					
   				</tr>
   				<tr>
   					<td id="colorOfHalfYear"></td>
   					<td><a href="${pageContext.request.contextPath}/maintain_goMaintainItems.do?datetype=halfyear&equipmentName=${equipment.machine.name }&lineName=${equipment.line.name }&equipmentId=${equipment.eid }">Half-year</a></td>
   					<td>${lastTimeOfType[4] }</td>
   					<td>${nextTimeOfType[4] }</td>
   					<td>182</td>
   					<td>4368</td>
   					<td>${runTimeOfDay[4] }</td>
   					<td id="runTimeOfHalfYear">${runTimeOfHour[4] }</td>   					
   				</tr>
   				<tr>
   					<td id="colorOfYear"></td>
   					<td><a href="${pageContext.request.contextPath}/maintain_goMaintainItems.do?datetype=year&equipmentName=${equipment.machine.name }&lineName=${equipment.line.name }&equipmentId=${equipment.eid }">Year</a></td>
   					<td>${lastTimeOfType[5] }</td>
   					<td>${nextTimeOfType[5] }</td>
   					<td>365</td>
   					<td>8760</td>
   					<td>${runTimeOfDay[5] }</td>
   					<td id="runTimeOfYear">${runTimeOfHour[5] }</td>   					
   				</tr>
   			</table>
   		</div>
   		
   	</div>
  </body>
</html>

