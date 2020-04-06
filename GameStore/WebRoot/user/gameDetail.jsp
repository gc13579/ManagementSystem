<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	request.setAttribute("id", request.getParameter("id"));
%>
<%
	if(session.getAttribute("user") == null){
		request.setAttribute("isError",true);
		request.setAttribute("errorMessage","您尚未登录，请登录");
		request.getRequestDispatcher("/user/login.jsp").forward(request,response);
	}
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta name="description" content="">
		<meta name="author" content="">
		<title>游币游戏详情</title>
		<link rel="stylesheet" href="../css/bootstrap.min.css">
		<link href="../css/jquery-accordion-menu.css" rel="stylesheet" type="text/css" />
		<script src="../js/jquery.js"></script>
		<script src="../js/bootstrap.min.js"></script>
		<script src="../js/particles.js"></script>
		<script src="../js/jquery-accordion-menu.js" type="text/javascript"></script>
		<style type="text/css">
			.p,.strong {
				display: inline-block;
			}
			
			.img li {
				position: absolute;
				text-align:center;
				display: none;
			}
			
			.out .num {
				position: absolute;
				bottom: 0px;
				left: 0px;
				font-size: 0px;
				text-align: center;
				width: 100%;
			}
			
			.num li {
				width: 20px;
				height: 20px;
				background: #666666;
				color: #FFFFFF;
				text-align: center;
				line-height: 20px;
				display: inline-block;
				font-size: 16px;
				border-radius: 50%;
				margin-right: 10px;
				cursor: pointer;
			}
			
			.out .btn {
				position: absolute;
				top: 52%;
				margin-top: -30px;
				width: 45px;
				height: 60px;
				background: rgba(0, 0, 0, 0.5);
				color: #FFFFFF;
				text-align: center;
				line-height: 60px;
				font-size: 40px;
				display: none;
				cursor: pointer;
			}
			
			.out .num li.active-1 {
				background: #AA0000;
				margin-left: 150px;
			}
			
			.out:hover .btn {
				display: block
			}
			
			.out .left {
				left: 23px;
			}
			
			.out .right {
				right: 20px;
			}
		</style>


		<script type="text/javascript">
			$(document).ready(function (){
				search();
				var user='<%=session.getAttribute("user")%>';
				//防止游客登录，user的id为空，造成空指针异常
				if(user!='null'){
					search2();
				}
			})
			
			function buyOrDownLoadGame(){
				var user='<%=session.getAttribute("user")%>';
				var status="";
				var gameTypeStatus="";
				if(user == 'null'){
					alert("您还未登录，无法购买游戏");
					return;
				}
				$.ajax({
					type:"POST",
					url:"<%=basePath%>game/getGameById.do",
					async:false,
					data:{
						"id":$("#hiddenInput").val(),
					},
					success:function(msg){
						var data= $.parseJSON(msg);
						if(data==null){
							alert("该游戏已不存在，无法购买或下载");
							return;
						}
						status=data.status;
						type=data.type;
					}
				})
				$.ajax({
					type:"POST",
					url:"<%=basePath%>gameType/getGameTypeByName.do",
					async:false,
					data:{
						"gameTypeName":type,
					},
					success:function(msg){
						var data= $.parseJSON(msg);
						gameTypeStatus=data.status;
					}
				})
				alert(status);
				if(status != "下线"&&gameTypeStatus!= "下线"){
					//如果按钮为  购买
					if($("#buyOrDownLoadButton").text()=="确认购买"){
						var buyWay=$("#select").val();
						if(buyWay=="2" && parseInt($("#currency").html()) > parseInt($("#hiddenCurrency").val())){
							alert("游币不足，请充值后再试");
							return;
						}
						if(buyWay=="1" && parseInt($("#tariffe").html()) > parseInt($("#hiddenTariffe").val())){
							alert("话费不足，请充值后再试");
							return;
						}
						$.ajax({
							type:"POST",
							url:"<%=basePath%>expense/addExpense.do",
							data:{
								"gameName":$("#hiddenGameName").val(),
								"userId":"${user.id}",
								"select":$("#select").val(),
								"currency":$("#currency").text(),
								"tariffe":$("#tariffe").text(),
								"hiddenTariffe":$("#hiddenTariffe").val(),
								"hiddenCurrency":$("#hiddenCurrency").val(),
							},
							success:function(msg){
								var data = $.parseJSON(msg);
								alert(data[0]);
								$("#buyOrDownLoadButton").text("下载游戏");
							}
						})
					}
					//如果按钮为  下载游戏
					else{
						if(status!="下线"&&gameTypeStatus!= "下线"){
							$("#buyOrDownLoadButton").attr("href","<%=basePath%>downloadFiles/"+$("#hiddenGameName").val()+".txt");
							$("#buyOrDownLoadButton").attr("download",$("#hiddenGameName").val()+"_download.txt");
						}
					}
				}
				else{
					alert("游戏已下线，请联系管理员");
					return;
				}
			}
			
			function search(){
				var id=$("#hiddenInput").val();
				$.ajax({
				type:"POST",
				url:"<%=basePath%>/game/getGameById.do",
				data:{
					"id":id,
				},
				async:false,
				success:function(msg){
					var data= $.parseJSON(msg);
					$("#name").text(data.name);
					$("#hiddenGameName").val(data.name);
					$("#type").text(data.type);
					$("#currency").text(data.currency);
					$("#hiddenCurrency").val(${user.currency});
					$("#tariffe").text(data.tariffe);
					$("#hiddenTariffe").val(${user.tariffe});
					$("#developer").text(data.developer);
					$("#filing").text(data.filing);
					$("#introduction").text(data.introduction);
					$("#status").text(data.status);
					var time=data.createTime;
					var createTime=((time.year+1900)+"-"+(time.month+1)+"-"+(time.date)
								+" "+time.hours+":"+time.minutes+":"+time.seconds);
					$("#createTime").text(createTime);
					$("#gameIntroduction").text(data.introduction);
					var cover="<%=basePath%>"+data.cover;
					$("#coverImg").attr("src",cover);
					var screenImgs1="<%=basePath%>"+data.screenImgs1;
					$("#screenImgs1").attr("src",screenImgs1);
					var screenImgs2="<%=basePath%>"+data.screenImgs2;
					$("#screenImgs2").attr("src",screenImgs2);
					var screenImgs3="<%=basePath%>"+data.screenImgs3;
					$("#screenImgs3").attr("src",screenImgs3);
					
				}
			})
		}
		
		function search2(){
			var gameName=$("#hiddenGameName").val();
			$.ajax({
				type:"POST",
				url:"<%=basePath%>expense/getExpenseByUserIdAndGameName.do",
				data:{
					"gameName":gameName,
					"userId":"${user.id}",
				},
				success:function(msg){
					var data= $.parseJSON(msg);
					if(data != ""){
						$("#buyOrDownLoadButton").text("下载游戏");
					}
				}
			})
		}
		
		var flag=false;
		function addPortal(){
			var account=$("#account").val();
			var password=$("#password").val();
			if(account.trim()==""){
				alert("密保卡号不能为空");
				return false;
			}
			if(password.trim()==""){
				alert("密保卡密码不能为空");
				return false;
			}
			$.ajax({
				type:"POST",
				url:"<%=basePath%>prepaid/addPrepaid.do",
				data:{
					"account":account,
					"password":password,
					"userProvince":"${user.province}",
					"userId":"${user.id}",
					"userAccount":"${user.account}",
					"userCurrency":"${user.currency}",
				},
				success:function(msg){
					var data= $.parseJSON(msg);
					alert(data[0]);
					if(data[0]!="充值成功,可前往个人中心-充值记录查看"){
						flag=true;
					}else{
						$("#closeButton").click();
					}
				}
			})
			if(flag==true){
				flag=false;
				return false;
			}
			return false;
		}
		</script>

	</head>
	<body>
		<div class="container">
			<nav class="navbar navbar-default">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#example-navbar-collapse">
					<span class="sr-only">切换导航</span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="portal.html">游币手机下载门户网站</a>
			</div>
			<div class="collapse navbar-collapse" id="example-navbar-collapse">
				<ul class="nav navbar-nav">

				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li class="dropdown">
						<c:choose>
							<c:when test="${user eq null}">
								<a href="#">欢迎游客</a>
								<br>
							</c:when>
							<c:otherwise>
								<a href="#">欢迎${user.account}</a>
								<br>
							</c:otherwise>
						</c:choose>
					</li>
					
					<c:if test="${not empty user}">
						<li class="dropdown">
							<a href="#" class="dropdown-toggle " data-toggle="dropdown">个人中心
								<b class="caret"> </b>
							</a>
							<ul class="dropdown-menu">
								<li>
									<a data-toggle="modal" data-target="#myModal">密保卡充值</a>
									<br>
								</li>
								<li>
									<a href="prepaid.jsp">充值记录</a>
									<br>
								</li>
								<li>
									<a href="userDetail.jsp">账户信息</a>
									<br>
								</li>
								<li>
									<a href="expense.jsp">消费记录</a>
									<br>
								</li>
								<li class="divider">
									<br>
								</li>
								<li>
									<a href="<%=basePath%>user/exit.do">退出</a>
								</li>
							</ul>
						</li>
					</c:if>
				</ul>
			</div>
			</nav>
		</div>
		<div class="modal fade" id="myModal" tabindex="-1">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							&times;
						</button>
						<h4 class="modal-title" id="myModalLabel">
							密保卡充值
						</h4>
					</div>
				</div>
				<div class="modal-body" style="height: 160px;background-color: white">
					<div class="form-group">
						<label for="firstname" class="col-sm-2 control-label">
							卡号
						</label>
						<div class="col-sm-10">
							<input id="account" type="text" class="form-control"
								name="account" placeholder="请输入卡号">
						</div>
					</div>
					<div class="form-group">
						<label for="lastname" class="col-sm-2 control-label">
							密码
						</label>
						<div class="col-sm-10" style="padding-top: 10px">
							<input id="password" type="text" class="form-control"
								placeholder="请输入卡密码" name="password" />
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10" style="padding-top: 10px">
							<input type="button" onclick="addPortal()"
								class="btn btn-default" value="确认充值">
						</div>
					</div>
				</div>
			</div>
		</div>

		<div class="row" style="background-color: #CCC; overflow: hidden;"
			onLoad="init()">
			<div class="col-md-12">
				<ul class="nav nav-pills col-md-offset-3">
				</ul>
			</div>
		</div>


		<div class="row">
			&nbsp;
		</div>
		<div class="container">
			<div class="row">
				<div class="col-md-4 col-sm-4">
					<img id="coverImg" width="450px;" alt="" />
				</div>
				<div class="col-md-6 col-md-offset-1">
					<strong class="strong">游戏名称:</strong>
					<p id="name" class="p"></p>
					<br>
					<strong class="strong">游戏类型:</strong>
					<p id="type" class="p"></p>
					<br>
					<strong class="strong">话费价格:</strong>
					<p id="tariffe" class="p"></p>
					元
					<br>
					<strong class="string">游币价格:</strong>
					<p id="currency" class="p"></p>
					游币
					<br>
					<strong class="strong">开发商:</strong>
					<p id="developer" class="p"></p>
					<br>
					<strong class="strong">备案号:</strong>
					<p id="filing" class="p"></p>
					<br>
					<strong class="strong">游戏简介:</strong>
					<p id="introduction" class="p"></p>
					<br>
					<strong class="strong">游戏状态:</strong>
					<p id="status" class="p"></p>
					<br>
					<strong>最新发布时间:</strong>
					<p id="createTime" class="p"></p>
					<br>
					<p>
						<strong>支付方式:</strong>
						<select id="select" name="select">
							<option value="1" selected="selected">
								话费支付
							</option>
							<option value="2">
								游币支付
							</option>
						</select>
					</p>
					<p>
						<a id="buyOrDownLoadButton" onclick="buyOrDownLoadGame()"
							class="btn btn-success">确认购买</a>
						<a class="btn btn-warning" href="portal.jsp">返回首页</a>
					</p>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12">
					<div class="panel panel-info">
						<div class="panel-heading">
							<h3 class="panel-title">
								游戏简介
							</h3>
						</div>
						<div id="gameIntroduction" class="panel-body">
						</div>
					</div>
				</div>

			</div>
			<div class="row col-md-12">
				<div class="panel panel-warning ">
					<div class="panel-heading">
						<h3 class="panel-title">
							游戏画面截图
						</h3>
					</div>
					<div class="out panel-body" style="height:500px ">
						<div class="content-main">
							<ul class="img">
								<li>
									<img id="screenImgs1" style="width: 1000px;height:450px "/>
								</li>
								<li>
									<img id="screenImgs2" style="width: 1000px;height:450px "/>
								</li>
								<li>
									<img id="screenImgs3" style="width: 1000px;height:450px "/>
								</li>
							</ul>
							<ul class="num">
							</ul> 
							<div class="left btn">&lt;</div>
							<div class="right btn">&gt;</div>
						</div>
					</div>
					<input id="hiddenInput" name="gameId" type="hidden" value="${id}">
					<!--  
					<input id="hiddenInput" name="userId" type="hidden"
						value="${user.id}">
						-->
					<input id="hiddenTariffe" name="hiddenTariffe" type="hidden">
					<input id="hiddenCurrency" name="hiddenCurrency" type="hidden">
					<input id="hiddenGameName" name="hiddenGameName" type="hidden">
				</div>
		
			</div>
		</div>
		<div class="row">
			<div class="container">
				<hr />
				<div class="col-md-4 col-md-offset-5">
					&copy;2016-2017 portal.com 版权所有
				</div>
			</div>
		</div>
		<c:if test="${isSuccess }">
			<script type="text/javascript">
				alert("${success}");
			</script>
		</c:if>
	</body>

</html>
