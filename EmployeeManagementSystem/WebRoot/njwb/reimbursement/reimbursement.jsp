<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>报销管理</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/reimbursement.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/main.css">
    <script type="text/javascript" src="<%=basePath%>js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/layer/layer.js"></script>
	<style type="text/css">
	.titleRow{
		font-size: 14px;
	
	}
	
	</style>
	
	<script type="text/javascript">
		$(document).ready(function(){
			sreach();
		});
		function sreach(){
			var reimbursementType = $("#reimbursementType").val();
			var applyType = $("#applyType").val();
			$.ajax({
				type:"POST",
				url:"<%=basePath%>reimbursement/getReimbursement.do",
				data:{
					"reimbursementType":reimbursementType,
					"applyType":applyType,
				},
				success:function(msg){
					var datas = $.parseJSON(msg);
  					var resultTable = $(".deptInfo");
  					resultTable.html($("table tr")[0]);
  					for(var i = 0; i < datas.length;i++){
  						var tr = $("<tr></tr>");
  						var reimNoTd = $("<td>"+datas[i].reimNo+"</td>");
  						var reimPersonTd = $("<td>"+datas[i].reimPerson+"</td>"); 
  						var reimTypeTd = $("<td>"+datas[i].reimType+"</td>"); 
  						var reimMoneyTd = $("<td>"+datas[i].reimMoney+"</td>"); 
  						//var reimTimeTd = $("<td>"+datas[i].reimTime+"</td>"); 
  						var time = datas[i].reimTime;
  						var reimTimeTd = $("<td>"+(time.year+1900)+"-"+(time.month+1)+"-"+(time.date)+"<br>"+(time.hours)+":"+(time.minutes)+":"+(time.seconds)+"</td>"); 
  						var reimStatesTd = $("<td>"+datas[i].reimStates+"</td>"); 
						var operationTd = $("<td></td>");
						var deleteImg = $('<img id="'+datas[i].reimStates+'" name="'+datas[i].reimNo+'"src="img/delete.png" class="operateImg" onclick="del(id,name)">');
						//var deleteImg = $('<img src="img/delete.png" class="operateImg" onclick="del('+deptNo+')">');
						var editImg = $('<img src="img/edit.png" class="operateImg" >');
						var deptEditA = $('<a href="<%=basePath%>njwb/reimbursement/reimbursementEdit.jsp?reimbursementNo='+datas[i].reimNo+'" id="'+datas[i].reimStates+'" onclick="return edit(id)" target="contentPage"></a>');
						var detailImg = $('<img src="img/detail.png"  class="operateImg">');
						var detailA = $('<a href="<%=basePath%>reimbursement/selfReimbursement.do?reimbursementNo='+datas[i].reimNo+'" target="contentPage"></a>');
<%--						var detailA = $('<a href="<%=basePath%>njwb/reimbursement/reimbursementDetail.jsp?reimbursementNo='+datas[i].reimNo+'" target="contentPage"></a>');--%>
						deptEditA.append(editImg);
						detailA.append(detailImg);
						operationTd.append(deleteImg);
						operationTd.append(deptEditA);
						operationTd.append(detailA);
  						tr.append(reimNoTd).append(reimPersonTd).append(reimTypeTd).append(reimMoneyTd).append(reimTimeTd)
  						.append(reimStatesTd).append(operationTd);
  						resultTable.append(tr);
  					}
				}
			});
		}
		function del(reimStates,reimNo){
			var result = window.confirm("确认要删除吗?");
			if(true == result){
				if(reimStates == "草稿"){
					$.ajax({
						type:"POST",
						url:"<%=basePath%>reimbursement/delReimbursement.do",
						data:{"reimNo":reimNo},
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
		function edit(reimStates){
			if(reimStates == "已提交"){
				alert("已提交不能修改");
				return false;
			}
			return true;
		}
	</script>
  </head>
  <body>
<h1 class="title">首页  &gt;&gt;报销管理 </h1>
		<center>
         	<div class="search">
        		报销类型：
         		<select id="reimbursementType" name="reimbursementType">
         			<option value="请选择">请选择</option>
         			<option value="差旅费">事假</option>
         			<option value="招待费">婚假</option>
         			<option value="办公费">年假</option>
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
         		<a href="<%=basePath %>njwb/reimbursement/reimbursementAdd.jsp" target="contentPage"><img alt="" src="<%=basePath%>img/add.png" width="18px" height="18px">添加报销</a>
         	</div>
         	<table class="deptInfo">
         		<tr class="titleRow">
         			<td>报销编号</td>
         			<td>申请人</td>
         			<td>报销类型</td>
         			<td>金额</td>
         			<td>申请时间</td>
         			<td>申请状态</td>
         			<td>操作列表</td>
         		</tr>
         	</table>
  </body>
</html>