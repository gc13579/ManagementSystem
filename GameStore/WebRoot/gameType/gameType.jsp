<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	
%>
<% 
	if(session.getAttribute("manager")==null){
		request.setAttribute("isError",true);
		request.setAttribute("errorMessage","您尚未登录，请登录");
		request.getRequestDispatcher("/login.jsp").forward(request,response);
	}
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta name="description" content="">
		<meta name="author" content="">
		<title>Bootstrap后台管理系统</title>
		<script src="../js/jquery.js"></script>
		<link rel="stylesheet" href="../css/bootstrap.min.css">
		<script src="../js/bootstrap.min.js"></script>
		<style type="text/css">
#container {
	margin-top: -20px;
	margin-left: 10px;
}
</style>
		<script type="text/javascript">
	function clickAll(){
		var all=document.getElementById("all");
		var flag=all.checked;
		var checkboxs=document.getElementsByName("opt");
		var flag=all.checked;
		if(flag==true){
			for(i=0;i<checkboxs.length;i++){
				checkboxs[i].checked=true;
			}	
		}else{
			for(i=0;i<checkboxs.length;i++){
				checkboxs[i].checked=false;
			}
		}
	}
	function clickOne(){
		var all=document.getElementById("all");
		var checkboxs=document.getElementsByName("opt");
		var count=0;
		for(i=0;i<checkboxs.length;i++){
			if(checkboxs[i].checked==true){
				count++;
			}
		}
		if(checkboxs.length==count){
			all.checked=true;
		}else{
			all.checked=false;
		}
	}
	
	function del(){
		var checkboxs=document.getElementsByName("opt");
		var str="";
		for(i=0;i<checkboxs.length;i++){
			if(checkboxs[i].checked==true){
				str+=checkboxs[i].value+" ";
			}
		}
		if(str.length==0){
			alert("还没有选择要删除的项");
			return false;
		}
		var result = window.confirm("确认要删除吗?");
		if(true==result){
			$.ajax({
				type:"POST",
				url:"<%=basePath%>gameType/removeGameType.do",
				data:{
					"str":str,
				},
				success:function(msg){
					var data= $.parseJSON(msg);
					document.getElementById("all").checked=false;
					if(data == ""){
						alert("删除成功");
						search();
					}else{
						alert("删除失败,要删除的类型下包含游戏");
						search();
					}
				}
			});
		}
	}
	
	
   $(document).ready(function(){
		search();
	});
	function search(){
		var name = $("#name").val();
		var status = $("#status").val();
		$.ajax({
			type:"POST",
			url:"<%=basePath%>gameType/getAllGameTypes.do",
			data:{
				"name":name,
				"status":status,
			},
			success:function(msg){
				var datas = $.parseJSON(msg);
				var searchTable = $("#searchTable");
				searchTable.html($("table tr")[0]);
					for(var i = 0; i < datas.length;i++){
						var tr = $("<tr></tr>");
						var td1=$("<td></td>");
						var checkbox=$("<input type='checkbox' value="+datas[i].id+" name='opt' onclick="+"clickOne() />");
						td1.append(checkbox);
						var id = $("<td>"+datas[i].id+"</td>");
						var td2=$("<td></td>");
						var pic_div=$("<div class="+"col-xs-6"+"  col-md-offset-4"+"></div>");
						var img=$("<img class="+"img-responsive"+" width=33"+ "height=30"+ " src=<%=basePath%>"+datas[i].picture+"></img>");
						pic_div.append(img);
						td2.append(pic_div);
						var time=datas[i].createTime;
						var createTime=$("<td>"+(time.year+1900)+"-"+((time.month+1)<10?"0"+(time.month+1):(time.month+1))+"-"+((time.date)<10?"0"+(time.date):(time.date))
						+" "+(time.hours<10?"0"+time.hours:time.hours)+":"+(time.minutes<10?"0"+time.minutes:time.minutes)+":"+(time.seconds<10?"0"+time.seconds:time.seconds)+"</td>");
						var time2 =datas[i].updateTime;
						var updateTime=$("<td>"+(time2.year+1900)+"-"+((time2.month+1)<10?"0"+(time2.month+1):(time2.month+1))+"-"+((time2.date)<10?"0"+(time2.date):(time2.date))
						+" "+(time2.hours<10?"0"+time2.hours:time2.hours)+":"+(time2.minutes<10?"0"+time2.minutes:time2.minutes)+":"+(time2.seconds<10?"0"+time2.seconds:time2.seconds)+"</td>");
						var name = $("<td>"+datas[i].name+"</td>"); 
						var status = $("<td>"+datas[i].status+"</td>"); 
						var td3=$("<td></td>");
						var href=$("<a class='btn btn-info' href=updateGameType.jsp?id="+datas[i].id+"></a>");
						var span=$("<span class='glyphicon glyphicon-pencil'>修改</span>");
						href.append(span);
						td3.append(href);
						tr.append(td1).append(id).append(td2).append(name).append(status).append(createTime).append(updateTime).append(td3);
						searchTable.append(tr);
						}
			}
		});
	}
	
	
</script>
	</head>
	<body>
		<div id="container" class="col-sm-12">
			<div class="page-header">
				<h2>游戏类型 
				</h2>
			</div>
			<form action="<%=basePath%>gameType/getAllGameTypes.do" method="post">
				<div class="row">
					<div class="col-xs-6 col-sm-3">
						<div class="row">
							<div class="col-xs-8 col-sm-3">
								<span class="btn btn-default">类型名称</span>
							</div>
							<div class="col-xs-3 col-sm-8">
								<input type="text" name="name" id="name" class="form-control" />
							</div>
						</div>
					</div>
					<div class="col-xs-6 col-sm-2">
						<div class="row">
							<div class="col-xs-8 col-sm-3">
								<span class="btn btn-default">状态</span>
							</div>
							<div class="col-xs-3 col-sm-8">
								<select id="status" class="form-control">
									<option value="请选择">
										请选择
									</option>
									<option value="商用">
										商用
									</option>
									<option value="下线">
										下线
									</option>
								</select>
							</div>
						</div>

					</div>
					<div class="col-xs-6 col-sm-4">
						<input type="button" class="btn btn-primary" value="查询"
							onclick="search()" />
					</div>
				</div>
			</form>
			<div class="row">
				&nbsp;
			</div>
			<div class="row">
				<div class="col-xs-6 col-sm-4">
					<a class="btn btn-primary" href="addGameType.jsp"> <span
						class="glyphicon glyphicon-plus"></span> 新增</a>
					<a class="btn btn-danger" onclick="del()"> <span
						class="glyphicon glyphicon-trash"></span> 删除</a>
				</div>
			</div>
			<div class="row">
				&nbsp;
			</div>
			<div class="row">
				<div class="col-xs-6 col-sm-12">
					<div class="table-responsive "
						style="vertical-align: middle; text-align: center;">
						<table id="searchTable"
							class="table table-bordered table-condensed ">
							<tr>
								<td>
									<input type="checkbox" id="all" value="全选" onclick="clickAll()" />
									全选
								</td>
								<td>
									ID
								</td>
								<td>
									类型图片
								</td>
								<td>
									类型名称
								</td>
								<td>
									状态
								</td>
								<td>
									创建时间
								</td>
								<td>
									修改时间
								</td>
								<td>
									操作
								</td>
							</tr>
						</table>
					</div>
				</div>
			</div>
		</div>
		<c:if test="${isSuccess}">
			<script type="text/javascript">
				alert("${success}");
			</script>
		</c:if>

	</body>
	<style>
td {
	vertical-align: middle !important;
}
</style>

</html>
