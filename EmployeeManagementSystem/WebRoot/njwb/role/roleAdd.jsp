<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>My JSP 'roleAdd.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="<%=basePath%>js/jquery-1.8.3.min.js"></script>
  </head>
  <script type="text/javascript">
  	function check(){
  		if($("#roleName").val().trim()==""){
  			alert("添加的角色不能为空");
  			return false;
  		}
  		return true;
  	}
  </script>
  
  <body>
    <form action="<%=basePath %>role/addRole.do" method="post" onsubmit="return check()">
    <center>
	   	<table id = "deptEditTable">
	   		<tr>
	   			<td>
	   			角色名称:
	   			</td>
	   			<td>
	   				<input type = "text" name="roleName" id="roleName"/>
	   			</td>
	   		</tr>  
	   		<tr>
	   			<td colspan="2">
	   				<input type = "submit" value="添加"/>
	   				<input type = "reset" value="重置"/>
					<a style="text-decoration: none" href="<%=basePath %>njwb/role/role.jsp" target="contentPage"><input type="button" value="返回"></a>
	   			</td>
	   		</tr>  	
	   	</table>
   	</center>
   	</form>
 	<c:if test='${isError}'>
		<script type="text/javascript">
			alert("${errorMessage}");
		</script>  
	</c:if>
  </body>
</html>
