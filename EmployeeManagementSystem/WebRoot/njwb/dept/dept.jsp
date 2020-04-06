<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>部门管理</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/reset.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/main.css">
    <script type="text/javascript" src="<%=basePath%>js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/layer/layer.js"></script>

	<style type="text/css">
	
	
	</style>
	
	<script type="text/javascript">
		$(document).ready(function(){
			search();
		});
		
		function search(){
			$.ajax({
  				type:"POST",
  				url:"<%=basePath%>dept/getDept.do",
  				success:function(msg){
  					var datas = $.parseJSON(msg);
  					var resultTable = $(".deptInfo");
  					resultTable.html($("table tr")[0]);
  					for(var i = 0; i < datas.length;i++){
  						var tr = $("<tr></tr>");
  						var deptNoTd = $("<td>"+datas[i].deptNo+"</td>");
  						var deptNameTd = $("<td>"+datas[i].deptName+"</td>"); 
  						var deptLocationTd = $("<td>"+datas[i].deptLoc+"</td>"); 
  						var deptManagerTd = $("<td>"+datas[i].deptManager+"</td>"); 
						var operationTd = $("<td></td>");
						var deleteImg = $('<img id="'+datas[i].deptNo+'"src="img/delete.png" class="operateImg" onclick="del(id)">');
						//var deleteImg = $('<img src="img/delete.png" class="operateImg" onclick="del('+deptNo+')">');
						var editImg = $('<img src="img/edit.png" class="operateImg" >');
						var deptEditA = $('<a href="<%=basePath%>njwb/dept/deptEdit.jsp?deptNo='+datas[i].deptNo+'" target="contentPage"></a>');
						var detailImg = $('<img src="img/detail.png" class="operateImg">');
						var detailA = $('<a href="<%=basePath%>njwb/dept/deptDetail.jsp?deptNo='+datas[i].deptNo+'" target="contentPage"></a>');
						deptEditA.append(editImg);
						detailA.append(detailImg);
						operationTd.append(deleteImg);
						operationTd.append(deptEditA);
						operationTd.append(detailA);
  						tr.append(deptNoTd).append(deptNameTd).append(deptLocationTd).append(deptManagerTd).append(operationTd);
  						resultTable.append(tr);
  					}
  				}
  			});
		}
		function del(deptNo){
			var result = window.confirm("确认要删除吗?");
			console.log(deptNo);
			if(true == result){
				$.ajax({
					type:"POST",
					data:{"deptNo":deptNo},
					url:"<%=basePath%>dept/remove.do",
					success:function(msg){
						alert(msg);
						search();
<%--						var datas = $.parseJSON(msg);--%>
<%--						console.log(datas);--%>
<%--						if(datas.length != 0){--%>
<%--							alert("该部门下还有员工关联，不允许删除！");--%>
<%--						}else{--%>
<%--							$.ajax({--%>
<%--								type:"POST",--%>
<%--								data:{"deptNo":deptNo},--%>
<%--								url:"<%=basePath%>dept/remove.do",--%>
<%--								success:function(msg){--%>
<%--									search();--%>
<%--									alert("删除成功");--%>
<%--								}--%>
<%--							});--%>
<%--						}--%>
					}
				});
			}
		}
	
	</script>
  </head>
  
  <body>
	<h1 class="title">首页  &gt;&gt;部门管理 </h1>
         	
         	<div class="add">
         		<a href="<%=basePath%>njwb/dept/deptAdd.jsp" target="contentPage"><img alt="" src="<%=basePath%>img/add.png" width="18px" height="18px">添加部门</a>
         	</div>
         	
         	<table class="deptInfo">
         		<tr class="titleRow">
         			<td>部门编号</td>
         			<td>部门名称</td>
         			<td>部门位置</td>
         			<td>部门负责人</td>
         			<td>操作列表</td>
         		</tr>
         	</table>
  </body>
</html>
