<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
			
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

		<title>游戏列表</title>


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
			search2();
			search(1);
	});
	
	function search2(){
		var type=$("#type");
		var status=$("#status");
		$.ajax({
			type:"POST",
			url:"<%=basePath%>/gameType/getAllGameTypes.do",
			success:function(msg){
				var datas= $.parseJSON(msg);
				for(var i=0;i<datas.length;i++){
					var noTd =$("<option name='type' value="+datas[i].name+" >"+datas[i].name+"</option>");
					type.append(noTd);
				}
			}
		})
	}
	
	
	
	var oldName="";
	var oldType="";
	var oldStatus="";
	function search(pageNo){
		var all=document.getElementById("all");
		all.checked=false;
		var name = $("#name").val();
		var type = $("#type").val();
		var status=$("#status").val();
		if(type==null){
			type="请选择";
		}
		if(status==null){
			status="请选择";
		}
		if(oldName != name || oldType!=type||oldStatus!=status){
			pageNo=1;
		}
		oldName=name;
		oldType=type;
		oldStatus=status;
		$.ajax({
			type:"POST",
			url:"<%=basePath%>game/getAllgames.do",
			data:{
				"pageNo":pageNo,
				"name":name,
				"type":type,
				"status":status,
				"pageSizeFlag":"3",
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
						var name = $("<td>"+datas[i].name+"</td>"); 
						var type = $("<td>"+datas[i].type+"</td>"); 
						var td2=$("<td></td>");
						var pic_div=$("<div class="+"'col-xs-6 col-md-offset-3'"+"></div>");
						var img=$("<img class="+"img-responsive"+" width=70"+ " height=70"+ " src=<%=basePath%>"+datas[i].cover+"></img>");
						pic_div.append(img);
						td2.append(pic_div);
						var status = $("<td>"+datas[i].status+"</td>"); 
						var currency=$("<td>"+datas[i].currency+"</td>");
						var tariffe=$("<td>"+datas[i].tariffe+"</td>");
						var time=datas[i].createTime;
						var createTime=$("<td>"+(time.year+1900)+"-"+((time.month+1)<10?"0"+(time.month+1):(time.month+1))+"-"+((time.date)<10?"0"+(time.date):(time.date))
						+" "+(time.hours<10?"0"+time.hours:time.hours)+":"+(time.minutes<10?"0"+time.minutes:time.minutes)+":"+(time.seconds<10?"0"+time.seconds:time.seconds)+"</td>");
						var time2=datas[i].updateTime;
						var updateTime=$("<td>"+(time2.year+1900)+"-"+((time2.month+1)<10?"0"+(time2.month+1):(time2.month+1))+"-"+((time2.date)<10?"0"+(time2.date):(time2.date))
						+" "+(time2.hours<10?"0"+time2.hours:time2.hours)+":"+(time2.minutes<10?"0"+time2.minutes:time2.minutes)+":"+(time2.seconds<10?"0"+time2.seconds:time2.seconds)+"</td>");
						var td3=$("<td></td>");
						var div1=$("<div class='col-xs-6 col-sm-4 col-md-offest-1'></div>");
						var href1=$("<a class='btn btn-info btn-sm' href=updateGame.jsp?id="+datas[i].id+"></a>");
						var span=$("<span class='glyphicon glyphicon-pencil'>修改</span>");
						div1.append(href1);
						href1.append(span);
						
						var div2=$("<div class='col-xs-6 col-sm-4 col-md-offest-1'></div>");
						var href2=$("<a class='btn btn-warning btn-sm' href=gameDetails.jsp?id="+datas[i].id+"></a>");
						var span=$("<span class='glyphicon glyphicon-pencil'>详情</span>");
						div2.append(href2);
						href2.append(span);
						
						td3.append(div1);
						td3.append(div2);
						tr.append(td1).append(id).append(name).append(type).append(td2).append(status).append(currency).append(tariffe).append(createTime).append(updateTime).append(td3);
						searchTable.append(tr);
					}
					for(;i<3;i++){
						var tr = $("<tr><td>&nbsp;</td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>");
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
					if(pager.totalPage==1||datas.length==0){
						firstPage.prop("disabled",true);
						lastPage.prop("disabled",true);
						nextPage.prop("disabled",true);
						endPage.prop("disabled",true);
					}
					if(pager.pageNo!=1&&pager.pageNo!=pager.totalPage){
						firstPage.prop("disabled",false);
						lastPage.prop("disabled",false);
						nextPage.prop("disabled",false);
						endPage.prop("disabled",false);
					}
			}
		});
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
				url:"<%=basePath%>game/removeGame.do",
				data:{"str":str},
				success:function(){
					document.getElementById("all").checked=false;
					alert("删除成功");
					search(1);
				}
			});
		}
	}	
</script>
	</head>
	<body>
		<div id="container">
			<div class="page-header">
				<h2>
					游戏列表
				</h2>
			</div>
			<div class="row">
				<div class="col-xs-6 col-sm-3">
					<div class="row">
						<div class="col-xs-8 col-sm-3">
							<span class="btn btn-default">游戏名称</span>
						</div>
						<div class="col-xs-3 col-sm-8">
							<input id="name" type="text" class="form-control" />
						</div>
					</div>
				</div>
				<div class="col-xs-6 col-sm-2">
					<div class="row">
						<div class="col-xs-8 col-sm-4">
							<span class="btn btn-default">游戏类型</span>
						</div>
						<div class="col-xs-3 col-sm-8">
							<select id="type" name="type" class="form-control">
								<option value="请选择">
									请选择
								</option>
							</select>
						</div>
					</div>
				</div>
				<div class="col-xs-6 col-sm-2">
					<div class="row">
						<div class="col-xs-8 col-sm-4">
							<span class="btn btn-default">游戏状态</span>
						</div>
						<div class="col-xs-3 col-sm-8">
							<select id="status" name="status" class="form-control">
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
					<input type="button" class="btn btn-primary" onclick="search(1)"
						value="查询" />
				</div>
			</div>
			<div class="row">
				&nbsp;
			</div>
			<div class="row">
				<div class="col-xs-6 col-sm-4">
					<a class="btn btn-primary" href="addGame.jsp"> <span
						class="glyphicon glyphicon-plus"></span>新增 </a>

					<a class="btn btn-danger" onclick="del()"> <span
						class="glyphicon glyphicon-trash"></span> 删除 </a>
				</div>
			</div>

			<div class="row">
				&nbsp;
			</div>
			<div class="row">
				<div class="col-xs-6 col-sm-12">
					<div class="table-responsive"
						style="vertical-align: middle; text-align: center;">
						<table id="searchTable"
							class="table table-bordered table-hover table-condensed  ">
							<tr>
								<td>
									<input type="checkbox" id="all" value="全选" onclick="clickAll()" />
									全选
								</td>
								<td>
									ID
								</td>
								<td>
									游戏名称
								</td>
								<td>
									游戏类别
								</td>
								<td>
									游戏封面
								</td>
								<td>
									状态
								</td>
								<td>
									游币价格
								</td>
								<td>
									话费价格
								</td>
								<td>
									创建时间
								</td>
								<td>
									更新时间
								</td>
								<td>
									操作
								</td>
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
		<c:if test="${isSuccess }">
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
