<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.ssm.pojo.Game"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
request.setAttribute("id",request.getParameter("id"));
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

<title>游戏详情</title>


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
	$(document).ready(function(){
		search();
	});
	function search(){
		var id=$("#hiddenTextId").val();
		$.ajax({
			type:"POST",
			url:"<%=basePath%>game/getGameById.do",
			data:{
				"id":id,
			},
			async:false,
			success:function(msg){
				var data= $.parseJSON(msg);
				$("#id").text(data.id);
				$("#name").text(data.name);
				$("#status").text(data.status);
				$("#type").text(data.type);
				$("#developer").text(data.developer);
				$("#filing").text(data.filing);
				$("#introduction").text(data.introduction);
				$("#detail").text(data.detail);
				$("#currency").text(data.currency);
				$("#tariffe").text(data.tariffe);
				
				var cover="<%=basePath%>"+data.cover;
				$("#coverImg").attr("src",cover);
				var screenImgs1="<%=basePath%>"+data.screenImgs1;
				$("#screenImgs1").attr("src",screenImgs1);
				var screenImgs2="<%=basePath%>"+data.screenImgs2;
				$("#screenImgs2").attr("src",screenImgs2);
				var screenImgs3="<%=basePath%>"+data.screenImgs3;
				$("#screenImgs3").attr("src",screenImgs3);
				
				
				var time=data.createTime;
				var createTime=(time.year+1900)+"-"+((time.month+1)<10?"0"+(time.month+1):(time.month+1))+"-"+((time.date)<10?"0"+(time.date):(time.date))
						+" "+(time.hours<10?"0"+time.hours:time.hours)+":"+(time.minutes<10?"0"+time.minutes:time.minutes)+":"+(time.seconds<10?"0"+time.seconds:time.seconds);
				$("#createTime").text(createTime);
				var time2=data.updateTime;
				var updateTime=(time2.year+1900)+"-"+((time2.month+1)<10?"0"+(time2.month+1):(time2.month+1))+"-"+((time2.date)<10?"0"+(time2.date):(time2.date))
						+" "+(time2.hours<10?"0"+time2.hours:time2.hours)+":"+(time2.minutes<10?"0"+time2.minutes:time2.minutes)+":"+(time2.seconds<10?"0"+time2.seconds:time2.seconds);
				$("#updateTime").text(updateTime);
			}
		})
	}
</script>
</head>
<body>
	<div id="container">
		<div class="page-header text-center">
			<h2>游戏详情</h2>
		</div>
		<div class="row">
			<div class="col-xs-6 col-sm-6 col-md-offset-4">
				<form class="form-horizontal text-center" >
					<table class="table table-bordered table-condensed ">
						<thead></thead>
						<tbody>
							<tr>
								<td>游戏id</td>
								<td>
									<div id="id" class="col-sm-10">
									</div>
								</td>
							</tr>
							
							<tr >
								<td>游戏名称</td>
								<td>
									<div id="name" class="col-sm-10">
									</div>
								</td>
							</tr>
							
							<tr >
								<td>游戏类型</td>
								<td>
									<div id="type" class="col-sm-10">
									</div>
								</td>
							</tr>
							
							<tr >
								<td>游戏状态</td>
								<td>
									<div id="status" class="col-sm-10">
									</div>
								</td>
							</tr>
							
							<tr >
								<td>游戏开发商</td>
								<td>
									<div id="developer" class="col-sm-10">
									</div>
								</td>
							</tr>
							
							<tr >
								<td>游戏备案号</td>
								<td>
									<div id="filing" class="col-sm-10">
									</div>
									
								</td>
							</tr>
							
							<tr >
								<td>游戏简介</td>
								<td>
									<div id="introduction" class="col-sm-10">
									</div>
								</td>
							</tr>
							
							<tr >
								<td>游戏详情</td>
								<td>
									<div id="detail" class="col-sm-10">
									</div>
								</td>
							</tr>
							
							<tr >
								<td>游戏币价格</td>
								<td>
									<div id="currency" class="col-sm-10">
									</div>
								</td>
							</tr>
							
							<tr >
								<td>话费价格</td>
								<td>
									<div id="tariffe" class="col-sm-10">
									</div>
								</td>
							</tr>
							
							<tr >
								<td>游戏封面</td>
								<td>
									<div class="col-sm-10  col-md-offset-4">
										<img id="coverImg" class="img-responsive" width="130" />
									</div>
								</td>
							</tr>
							
							<tr >
								<td>游戏画面截图</td>
								<td>
									 <div class="col-sm-5  col-md-4">
     									<div class="thumbnail">
        									 <img id="screenImgs1" class="img-responsive" width="130"/>
      									</div>
 									 </div>
 									 <div class="col-sm-4  col-md-4">
     									<div class="thumbnail">
											<img id="screenImgs2" class="img-responsive" width="130" />
      									</div>
 									 </div>
 									 <div class="col-sm-4  col-md-4">
     									<div class="thumbnail">
        									 <img id="screenImgs3" class="img-responsive" width="130"/>
      										
      									</div>
 									 </div>
								</td>
							</tr>
							
							<tr >
								<td>创建时间</td>
								<td>
									<div id="createTime" class="col-sm-10">
									</div>
								</td>
							</tr>
							
							<tr >
								<td>修改时间</td>
								<td>
									<div id="updateTime" class="col-sm-10">
									</div>
								</td>
							</tr>
						</tbody>
					</table>
					<div class="row">
						<div class="col-xs-6 col-sm-4">
							<div class="form-group">
								<div class="col-sm-offset-10 col-sm-11">
									<a href="game.jsp" class="btn btn-warning" >返回上一页</a>
								</div>
							</div>
						</div>
					</div>
					<input id="hiddenTextId" name="id" type="hidden" value="${id}">
				</form>

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
