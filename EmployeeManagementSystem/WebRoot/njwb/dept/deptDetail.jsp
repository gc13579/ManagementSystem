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
			var deptNo = $("#deptNo").val();
			$.ajax({
				type:"POST",
				url:"<%=basePath%>dept/getDeptByNo.do",
				data:{"deptNo":deptNo},
				success:function(msg){
					var dept = $.parseJSON(msg);
					console.log(dept.deptNo)
					var deptNoSpan = $("#deptNoSpan");
					var deptNameSpan = $("#deptNameSpan");
					var deptLocSpan = $("#deptLocSpan");
					var deptMasterSpan = $("#deptMasterSpan");
					var createTimeSpan = $("#createTimeSpan");
					var deptInfoSpan = $("#deptInfoSpan");
					var deptNo = $("<span>"+dept.deptNo+"</span><br><br>");
					var deptName = $("<span>"+dept.deptName+"</span><br><br>");
					var deptLoc = $("<span>"+dept.deptLoc+"</span><br><br>");
					var deptMaster = $("<span>"+dept.deptManager+"</span><br><br>");
					var time = dept.createTime;
					var createTime = $("<span>"+(time.year+1900)+"年"+(time.month+1)+"月"+(time.date)+"日"+"&nbsp;"+(time.hours)+":"+(time.minutes)+":"+(time.seconds)+"</span><br><br>");
					var deptInfo = $("<span>"+dept.descrption+"</span><br><br><br>");
					deptNoSpan.append(deptNo);
					deptNameSpan.append(deptName);
					deptLocSpan.append(deptLoc);
					deptMasterSpan.append(deptMaster);
					createTimeSpan.append(createTime);
					deptInfoSpan.append(deptInfo);
				}
			});
		});
	</script>
  </head>
  
  <body>
  <%
  	String deptNo = request.getParameter("deptNo");
  	request.setAttribute("deptNo",deptNo);
  %>
	
	<div>
		<span id="deptNoSpan">部门编号:</span>
		<span id="deptNameSpan">部门名称:</span>
		<span id="deptLocSpan">部门地址:</span>
		<span id="deptMasterSpan">部门负责人:</span>
		<span id="createTimeSpan">创建时间:</span>
		<span id="deptInfoSpan">部门介绍:</span>
	</div>

	<a style="text-decoration: none" href="<%=basePath %>njwb/dept/dept.jsp" target="contentPage"><input type="button" value="返回"></a>
  	<input type="hidden" value="${deptNo}" id="deptNo">
  </body>
</html>