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
    
    <title>添加部门</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="<%=basePath%>js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript">
	function addDept(){
		var deptNo = $("#deptNo").val();
		var deptName = $("#deptName").val();
		var deptLoc = $("#deptLoc").val();
		var deptMaster = $("#deptMaster").val();
		var deptInfo  = $("#deptInfo").val();
		var errorMessage = "";
		if (deptNo == ""){
			errorMessage +="部门编号不能为空"+"\r\n";
		}
		if(!/^[A]\d{4}$/.test(deptNo)){
			errorMessage+="部门编号格式错误：A+四位数字"+"\r\n";
		}
		if(deptName==""){
			errorMessage += "部门名不能为空"+"\r\n";
		}
		if(deptMaster==""){
			errorMessage+="部门经理不能为空"+"\r\n";
		}
		if(!/^[\u4E00-\u9FA5]{2,4}$/.test(deptMaster)){
			errorMessage+="部门经理格式错误：四位以下中文"+"\r\n";
		}
		if(deptInfo == ""){
			errorMessage+="部门信息不能为空"+"\r\n";
		}
		if(errorMessage.length == ""){
			return true;
		}else{
			alert(errorMessage);
			return false;
		}
		
	}
	
	
	</script>
	<style type="text/css">
		body,div,table,tr,td{
			margin: 0px;
			padding: 0px;
		}
	
		#deptEditTable{
			font-size: 15px;
			border-collapse: collapse;
			width: 350px;
			margin: 20px auto;
			
			
		}
		
		#deptEditTable td{
			height: 40px;
		}
	
	</style>
  </head>
  
  <body>
	<form action="<%=basePath %>dept/addDept.do" method="post" onsubmit="return addDept()">
   	
   	<table id = "deptEditTable">
   		<tr>
   			<td>
   			部门编号:
   			</td>
   			<td>
   				<input type = "text" name="deptNo" id="deptNo"/>
   			</td>
   		</tr>
   		<tr>
   			<td>
   			部门名称:
   			</td>
   			<td>
   				<input type = "text" name="deptName" id="deptName"/>
   			</td>
   		</tr>  

   		<tr>
   			<td>
   			部门位置:
   			</td>
   			<td>
   				<input type = "text" name="deptLoc" id="deptLoc"/>
   			</td>
   		</tr>  

   		<tr>
   			<td>
   			部门负责人:
   			</td>
   			<td>
   				<input type = "text" name="deptMaster" id="deptMaster"/>
   			</td>
   		</tr>  
   		<tr>
   			<td>
   			部门介绍:
   			</td>
   			<td>
   				<textarea id="deptInfo" name="deptInfo" rows="3" cols="25"></textarea>
   			</td>
   		</tr>  
   		
   		<tr>
   			<td colspan="2">
   				<input type = "submit" value="添加"/>
   				<input type = "reset" value="重置"/>
				<a style="text-decoration: none" href="<%=basePath %>njwb/dept/dept.jsp" target="contentPage"><input type="button" value="返回"></a>
   			</td>
   		</tr>  	
   	</table>
   	
   	
   	</form>
   	<c:if test='${isError}'>
			<script type="text/javascript">
				alert("${errorMessage}");
			</script>  
		</c:if>
  </body>
</html>
