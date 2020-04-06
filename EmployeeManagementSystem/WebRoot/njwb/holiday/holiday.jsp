<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>请假管理</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/holiday.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/main.css">
    <script type="text/javascript" src="<%=basePath%>js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/layer/layer.js"></script>
	<style type="text/css">
	.titleRow{
		font-size: 12px;
	
	}
	.deptInfo tr{
		font-size: 12px;
	
	}
	</style>
	
	
	<script type="text/javascript">
		$(document).ready(function(){
			sreach();
		});
		function sreach(){
			var holidayType = $("#holidayType").val();
			var applyType = $("#applyType").val();
			var holidayUser = $("#holidayUser").val();
			$.ajax({
				type:"POST",
				url:"<%=basePath%>holiday/getHoliday.do",
				data:{
					"holidayType":holidayType,
					"applyType":applyType,
					"holidayUser":holidayUser,
				},
				success:function(msg){
					var datas = $.parseJSON(msg);
  					var resultTable = $(".deptInfo");
  					resultTable.html($("table tr")[0]);
  					for(var i = 0; i < datas.length;i++){
  						var tr = $("<tr></tr>");
  						var holidayNoTd = $("<td>"+datas[i].holidayNo+"</td>");
  						var holidayUserTd = $("<td>"+datas[i].holidayUser+"</td>"); 
  						var holidayTypeTd = $("<td>"+datas[i].holidayType+"</td>"); 
  						var holidayBzTd = $("<td>"+datas[i].holidayBz+"</td>"); 
  						var startTimeTd = $("<td>"+datas[i].startTime+"</td>"); 
  						var endTimeTd = $("<td>"+datas[i].endTime+"</td>"); 
  						var holidayStatesTd = $("<td>"+datas[i].holidayStates+"</td>"); 
  						var time = datas[i].createTime;
  						var createTimeTd = $("<td>"+(time.year+1900)+"-"+(time.month+1)+"-"+(time.date)+"<br>"+(time.hours)+":"+(time.minutes)+":"+(time.seconds)+"</td>"); 
						var operationTd = $("<td></td>");
						var deleteImg = $('<img id="'+datas[i].holidayStates+'" name="'+datas[i].holidayNo+'"src="img/delete.png" class="operateImg" onclick="del(id,name)">');
						var editImg = $('<img src="img/edit.png" class="operateImg" >');
						var deptEditA = $('<a href="<%=basePath%>njwb/holiday/holidayEdit.jsp?holidayNo='+datas[i].holidayNo+'" id="'+datas[i].holidayStates+'" onclick="return edit(id)" target="contentPage"></a>');
						var detailImg = $('<img src="img/detail.png"  class="operateImg">');
						var detailA = $('<a href="<%=basePath%>njwb/holiday/holidayDetail.jsp?holidayNo='+datas[i].holidayNo+'" target="contentPage"></a>');
						deptEditA.append(editImg);
						detailA.append(detailImg);
						operationTd.append(deleteImg);
						operationTd.append(deptEditA);
						operationTd.append(detailA);
  						tr.append(holidayNoTd).append(holidayUserTd).append(holidayTypeTd).append(holidayBzTd).append(startTimeTd)
  						.append(endTimeTd).append(holidayStatesTd).append(createTimeTd).append(operationTd);
  						resultTable.append(tr);
  					}
				}
			});
		}
		function del(holidayStates,holidayNo){
			var result = window.confirm("确认要删除吗?");
			if(true == result){
				if(holidayStates == "草稿"){
					$.ajax({
						type:"POST",
						url:"<%=basePath%>holiday/delHoliday.do",
						data:{"holidayNo":holidayNo},
						success:function(){
							alert("删除成功");
							sreach();
						}
					});
				}else{
					alert("删除失败:已提交不能删除");
				}
			}
		}
		function edit(holidayStates){
			if(holidayStates == "已提交"){
				alert("已提交不能修改");
				return false;
			}
			return true;
			
		}
	</script>
  </head>
  
  <body>
         	<h1 class="title">首页  &gt;&gt;请假管理 </h1>
         	<center>
         	<div class="search">
         		申请人：<input id="holidayUser"type="text" style="width: 100px;">&nbsp;&nbsp;
        		请假类型：
         		<select id="holidayType" name="holidayType">
         			<option value="请选择">请选择</option>
         			<option value="事假">事假</option>
         			<option value="婚假">婚假</option>
         			<option value="年假">年假</option>
         			<option value="调休">调休</option>
         			<option value="病假">病假</option>
         			<option value="丧假">丧假</option>
         		</select>&nbsp;&nbsp;&nbsp;
         		申请状态：
         		<select id="applyType" name="applyType">
         			<option value="请选择">请选择</option>
         			<option value="草稿">草稿</option>
         			<option value="已提交">已提交</option>
         		</select>&nbsp;&nbsp;&nbsp;
         		<input type="button" value="查询" onclick="sreach()">
         		<br/>
         	</div>
         	</center>
         	<div class="add">
         		<a href="<%=basePath %>njwb/holiday/holidayAdd.jsp" target="contentPage"><img alt="" src="<%=basePath%>img/add.png" width="18px" height="18px">添加请假</a>
         	</div>
         	
         	<table class="deptInfo">
         		<tr class="titleRow">
         			<td>请假编号</td>
         			<td>申请人</td>
         			<td>申请类型</td>
         			<td>请假事由</td>
         			<td>开始时间</td>
         			<td>结束时间</td>
         			<td>申请状态</td>
         			<td>提交时间</td>
         			<td>操作列表</td>
         		</tr>
         	</table>
  </body>
</html>