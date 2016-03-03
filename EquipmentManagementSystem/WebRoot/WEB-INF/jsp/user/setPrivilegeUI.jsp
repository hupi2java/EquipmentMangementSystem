<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/public/commos.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
  <head>
    <title>My JSP 'setPrivilegeUI.jsp' starting page</title> 
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.3.1.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery_treeview/jquery.treeview.js"></script>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/js/jquery_treeview/jquery.treeview.css" />
    <script type="text/javascript">
		$(function(){
			// 指定事件处理函数
			$("[name=privilegeIds]").click(function(){
				
				// 当选中或取消一个权限时，也同时选中或取消所有的下级权限
				$(this).siblings("ul").find("input").attr("checked", this.checked);
				
				// 当选中一个权限时，也要选中所有的直接上级权限
				if(this.checked == true){
					$(this).parents("li").children("input").attr("checked", true);
				}
				
			});
		});
		
	</script>

  
  </head>
  <s:debug></s:debug>
  <body>
  	<s:form action="user_setPrivilege">
  	<s:hidden name="id"></s:hidden>
  	<ul id="tree">
    <s:iterator value="#topPrivilegeList">
    	<li>
    		<input type="checkbox" name="privilegeIds"  value="${id }" <s:property value="%{id in privilegeIds ? 'checked' : ''}"/> />${name }
    			<ul>
    			<s:iterator value="privileges">
    				<li>
    				<input type="checkbox" name="privilegeIds" value="${id }" <s:property value="%{id in privilegeIds ? 'checked' : ''}"/> />${name }
    				</li>
    			</s:iterator>
    			</ul>
    	</li>
    </s:iterator>
    </ul>
     <script language="javascript">
        	$("#tree").treeview();
        </script>
    <s:submit></s:submit>
    </s:form>
  </body>
</html>
