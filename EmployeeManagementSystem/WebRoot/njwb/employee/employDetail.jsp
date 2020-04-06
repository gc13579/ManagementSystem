<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>部门明细</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
    <script type="text/javascript" src="<%=basePath%>js/jquery-1.8.3.min.js"></script>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript">
		$(document).ready(function(){
			var empId = $("#hiddenInput").val();
			$.ajax({
				type:"POST",
				url:"<%=basePath%>employee/getemployeeById.do",
				data:{"empId":empId},
				success:function(msg){
					var employee = $.parseJSON(msg);
					var employeeNoSpan = $("#employeeNoSpan");
					var employeeNameSpan = $("#employeeNameSpan");
					var employeeSexSpan = $("#employeeSexSpan");
					var employeeDeptSpan = $("#employeeDeptSpan");
					var entryTimeSpan = $("#entryTimeSpan");
					var employeeNo = $("<span>"+employee.empNo+"</span><br><br>");
					var employeeName = $("<span>"+employee.empName+"</span><br><br>");
					var employeeSex = $("<span>"+employee.sex+"</span><br><br>");
					var employeeDept = $("<span>"+employee.empDept+"</span><br><br>");
					var time = employee.entryTime;
					var entryTime = $("<span>"+(time.year+1900)+"年"+(time.month+1)+"月"+(time.date)+"日"+"</span><br><br>");
					employeeNoSpan.append(employeeNo);
					employeeNameSpan.append(employeeName);
					employeeSexSpan.append(employeeSex);
					employeeDeptSpan.append(employeeDept);
					entryTimeSpan.append(entryTime);
				}
			});
		});
	</script>
  </head>
  
  <body>
  <%
  	String empId = request.getParameter("empId");
  	request.setAttribute("empId",empId);
  %>
	<div>
		<span id="employeeNoSpan">员工编号:</span>
		<span id="employeeNameSpan">员工性名:</span>
		<span id="employeeSexSpan">员工性别:</span>
		<span id="employeeDeptSpan">所属部门:</span>
		<span id="entryTimeSpan">入职时间:</span>
	</div>

	<a style="text-decoration: none" href="<%=basePath %>njwb/employee/employee.jsp" target="contentPage"><input type="button" value="返回"></a>
  	<input type="hidden" value="${empId}" id="hiddenInput">
  </body>
</html>

