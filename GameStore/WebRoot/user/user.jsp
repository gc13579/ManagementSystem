<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	if(session.getAttribute("manager") == null){
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
<title>用户管理</title>
<script src="<%=basePath%>js/jquery-2.2.3.min.js"></script>
<link rel="stylesheet" href="../css/bootstrap.min.css">
<script src="../js/jquery.js"></script>
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
	
	 $(document).ready(function(){
			search(1);
		});
	var oldAccount="";
	var oldIphone="";
	function search(pageNo){
		var all=document.getElementById("all");
		all.checked=false;
		var account = $("#account").val();
		var iphone = $("#iphone").val();
		if(oldAccount != account || oldIphone!=iphone){
			pageNo=1;
		}
		oldAccount=account;
		oldIphone=iphone;
		$.ajax({
			type:"POST",
			url:"<%=basePath%>user/getAllusers.do",
			data:{
				"pageNo":pageNo,
				"account":account,
				"iphone":iphone,
			},
			success:function(msg){
				var pager = $.parseJSON(msg);
				var datas = pager.list;
				$("#hiddenInput").val(pager.pageNo);
					var searchTable = $("#searchTable");
					searchTable.html($("table tr")[0]);
 					for(var i = 0; i < datas.length;i++){
 						var tr = $("<tr></tr>");
 						var td1=$("<td></td>");
 						var checkbox=$("<input type='checkbox' value="+datas[i].id+" name='opt' onclick="+"clickOne() />");
 						td1.append(checkbox);
 						var id = $("<td>"+datas[i].id+"</td>");
						var time=datas[i].createTime;
						var createTime=$("<td>"+(time.year+1900)+"-"+((time.month+1)<10?"0"+(time.month+1):(time.month+1))+"-"+((time.date)<10?"0"+(time.date):(time.date))
						+" "+(time.hours<10?"0"+time.hours:time.hours)+":"+(time.minutes<10?"0"+time.minutes:time.minutes)+":"+(time.seconds<10?"0"+time.seconds:time.seconds)+"</td>");
 						var name = $("<td>"+datas[i].account+"</td>"); 
 						var status = $("<td>"+datas[i].status+"</td>"); 
 						var iphone=$("<td>"+datas[i].iphone+"</td>");
 						tr.append(td1).append(id).append(name).append(iphone).append(status).append(createTime);
 						searchTable.append(tr);
 					}
 					for(;i<3;i++){
 						var tr = $("<tr><td>&nbsp;</td><td></td><td></td><td></td><td></td><td></td></tr>");
 						searchTable.append(tr);
 					}
 					var firstPage=$("#firstPage");
 					var lastPage=$("#lastPage");
 					var nextPage=$("#nextPage");
 					var endPage=$("#endPage");
 					firstPage.attr("onclick","search("+(1)+")");
  					lastPage.attr("onclick","search("+(pager.pageNo-1)+")");
 					nextPage.attr("onclick","search("+(pager.pageNo+1)+")");
 					endPage.attr("onclick","search("+(pager.totalPage)+")");
 					if(pager.pageNo==1){
 						firstPage.prop("disabled",true);
 						lastPage.prop("disabled",true);
 						nextPage.prop("disabled",false);
 						endPage.prop("disabled",false);
 					}
 					if(pager.pageNo==pager.totalPage){
  						firstPage.prop("disabled",false);
 						lastPage.prop("disabled",false);
 						nextPage.prop("disabled",true);
 						endPage.prop("disabled",true);
 					}
 					if(pager.totalPage==1){
 						firstPage.prop("disabled",true);
 						lastPage.prop("disabled",true);
 						nextPage.prop("disabled",true);
 						endPage.prop("disabled",true);
 					}
 					if(pager.pageNo!=1&&pager.pageNo!=pager.totalPage)
 					{
 						firstPage.prop("disabled",false);
 						lastPage.prop("disabled",false);
 						nextPage.prop("disabled",false);
 						endPage.prop("disabled",false);
 					}
 					if(datas.length==0){
						firstPage.prop("disabled",true);
						lastPage.prop("disabled",true);
						nextPage.prop("disabled",true);
						endPage.prop("disabled",true);
					}
			}
		});
	}
		
	function forbidUser(){
		var checkboxs=document.getElementsByName("opt");
		var str="";
		for(i=0;i<checkboxs.length;i++){
			if(checkboxs[i].checked==true){
				str+=checkboxs[i].value+" ";
			}
		}
		if(str.length==0){
			alert("还没有选择要禁用的用户");
			return false;
		}
		var result = window.confirm("确认要禁用吗?");
		if(true==result){
			$.ajax({
				type:"POST",
				url:"<%=basePath%>user/modifyUsersStatusToForbid.do",
				data:{"str":str},
				success:function(){
					document.getElementById("all").checked=false;
					search($("#hiddenInput").val());
				}
			});
		}
	}
	
	function unsealUser(){
		var checkboxs=document.getElementsByName("opt");
		var str="";
		for(i=0;i<checkboxs.length;i++){
			if(checkboxs[i].checked==true){
				str+=checkboxs[i].value+" ";
			}
		}
		if(str.length==0){
			alert("还没有选择要解封的用户");
			return false;
		}
		var result = window.confirm("确认要解封吗?");
		if(true==result){
			$.ajax({
				type:"POST",
				url:"<%=basePath%>user/modifyUsersStatusToNormal.do",
				data:{"str":str},
				success:function(){
					document.getElementById("all").checked=false;
					search($("#hiddenInput").val());
				}
			});
		}
	}
</script>
</head>
<body>
	<div id="container"  >
		<div class="page-header">
			<h2>用户列表</h2>
		</div>
		<form action="" >
			<div class="row">
				<div class="col-xs-6 col-sm-3">
					<div class="row">
						<div class="col-xs-8 col-sm-3">
							<span class="btn btn-default">用户名</span>
						</div>
						<div class="col-xs-3 col-sm-8">
							<input id="account" type="text" class="form-control" />
						</div>
					</div>
				</div>
				<div class="col-xs-6 col-sm-2">
					<div class="row">
						<div class="col-xs-8 col-sm-4">
							<span class="btn btn-default" >手机号码</span>
						</div>
						<div class="col-xs-3 col-sm-8">
							<input id="iphone" id="status" type="text" class="form-control" />
						</div>
					</div>

				</div>
				<div class="col-xs-6 col-sm-4">
					<input type="button" class="btn btn-primary" onclick="search(1)" value="查询" />
				</div>
			</div>
		</form>
		<div class="row">&nbsp;</div>
		<div class="row">
			<div class="col-xs-6 col-sm-4">
				<a class="btn btn-success" onclick="unsealUser()">
					<span class="glyphicon glyphicon-ok-sign"></span>恢复使用
				</a>
				<a class="btn btn-danger" onclick="forbidUser()">
					<span class="glyphicon glyphicon-trash"></span> 禁止使用
				</a>
			</div>
		</div>

		<div class="row">&nbsp;</div>
		<div class="row">
			<div class="col-xs-6 col-sm-11">
				<div class="table-responsive"
					style="vertical-align: middle;text-align: center;">
					<table id="searchTable"
						class="table table-bordered table-hover table-condensed  ">
						<tr>
							<td><input type="checkbox" id="all" value="全选" onclick="clickAll()"
								/> 全选</td>
							<td>ID</td>
							<td>用户名</td>
							<td>手机账号</td>
							<td>状态</td>
							<td>创建时间</td>
					</table>
					<input id="firstPage" type="button" value="首页">
					<input id="lastPage" type="button" value="上一页">
					<input id="nextPage" type="button" value="下一页">
					<input id="endPage" type="button" value="末页">
					<input id="hiddenInput" type="hidden">
				</div>
			</div>
		</div>
	</div>
</body>
<style>
td {
	vertical-align: middle !important;
}
</style>

</html>
