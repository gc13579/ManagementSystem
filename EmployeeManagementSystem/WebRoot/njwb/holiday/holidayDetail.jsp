<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>请假详情</title>
    
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
			var holidayNo = $("#holidayNo").val();
			$.ajax({
				type:"POST",
				url:"<%=basePath%>holiday/selfHoliday.do",
				data:{"holidayNo":holidayNo},
				success:function(msg){
					var holiday = $.parseJSON(msg);
					var holidayNoSpan = $("#holidayNoSpan");
					var holidayUserSpan = $("#holidayUserSpan");
					var holidayTypeSpan = $("#holidayTypeSpan");
					var holidayBzSpan = $("#holidayBzSpan");
					var startTimeSpan = $("#startTimeSpan");
					var endTimeSpan = $("#endTimeSpan");
					var applyStatesSpan = $("#applyStatesSpan");
					var createTimeSpan = $("#createTimeSpan");
					var holidayNo = $("<span>"+holiday.holidayNo+"</span><br><br>");
					var holidayUser = $("<span>"+holiday.holidayUser+"</span><br><br>");
					var holidayType = $("<span>"+holiday.holidayType+"</span><br><br>");
					var holidayBz = $("<span>"+holiday.holidayBz+"</span><br><br>");
					var startTime = $("<span>"+holiday.startTime+"</span><br><br>");
					var endTime = $("<span>"+holiday.endTime+"</span><br><br>");
					var applyStates = $("<span>"+holiday.holidayStates+"</span><br><br>");
					var createTime = $("<span>"+holiday.createTime+"</span><br><br>");
					var time = holiday.createTime;
					var createTime = $("<span>"+(time.year+1900)+"年"+(time.month+1)+"月"+(time.date)+"日"+"&nbsp;"+(time.hours)+":"+(time.minutes)+":"+(time.seconds)+"</span><br><br>");
					holidayNoSpan.append(holidayNo);
					holidayUserSpan.append(holidayUser);
					holidayTypeSpan.append(holidayType);
					holidayBzSpan.append(holidayBz);
					startTimeSpan.append(startTime);
					endTimeSpan.append(endTime);
					applyStatesSpan.append(applyStates);
					applyStatesSpan.append(createTime);
					createTimeSpan.append(createTime);
				}
			});
		});
	</script>
  </head>
  
  <body>
  <%
  	String holidayNo = request.getParameter("holidayNo");
  	request.setAttribute("holidayNo",holidayNo);
  %>
	
	<div>
		<span id="holidayNoSpan">请假编号:</span>
		<span id="holidayUserSpan">申请人:</span>
		<span id="holidayTypeSpan">申请类型:</span>
		<span id="holidayBzSpan">请假事由:</span>
		<span id="startTimeSpan">开始时间:</span>
		<span id="endTimeSpan">结束时间:</span>
		<span id="applyStatesSpan">申请状态:</span>
		<span id="createTimeSpan">提交时间:</span>
	</div>

	<a style="text-decoration: none" href="<%=basePath %>njwb/holiday/holiday.jsp" target="contentPage"><input type="button" value="返回"></a>
  	<input type="hidden" value="${holidayNo}" id="holidayNo">
  </body>
</html>
