<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
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
		<link rel="stylesheet" href="../css/index.css" />
		<script src="../js/jquery.js"></script>
		<script src="../js/bootstrap.min.js"></script>
		<style type="text/css">
strong,p {
	display: inline-block;
}
</style>
		<script type="text/javascript">
	$(document).ready(function (){
		search();
	})
	
	function search(){
		$.ajax({
			type:"POST",
			url:"<%=basePath%>user/getUserById.do",
			data:{
				"id":"${user.id}",
			},
			success:function(msg){
				var datas=$.parseJSON(msg);
				for(var i=0;i<datas.length;i++){
					var table=$("<table class='table table-striped table-hover'></table>");
					var tbody=$("<tbody></tbody>");
					
					var tr1=$("<tr></tr>");
					var td1=$("<td></td>");
					var strong1=$("<strong>用户名:</strong>");
					var p1=$("<p>"+datas[i].account+"</p>");
					td1.append(strong1).append(p1);
					tr1.append(td1)
					
					var tr2=$("<tr></tr>");
					var td2=$("<td></td>");
					var strong2=$("<strong>密码:</strong>");
					var p2=$("<p>"+datas[i].password+"</p>");
					td2.append(strong2).append(p2);
					tr2.append(td2);
					
					var tr3=$("<tr></tr>");
					var td3=$("<td></td>");
					var strong3=$("<strong>性别:</strong>");
					var p3=$("<p>"+datas[i].sex+"</p>");
					td3.append(strong3).append(p3);
					tr3.append(td3);
					
					var tr4=$("<tr></tr>");
					var td4=$("<td></td>");
					var strong4=$("<strong>手机号码:</strong>");
					var p4=$("<p>"+datas[i].iphone+"</p>");
					td4.append(strong4).append(p4);
					tr4.append(td4);
					
					var tr5=$("<tr></tr>");
					var td5=$("<td></td>");
					var strong5=$("<strong>生日:</strong>");
					var birthday=datas[i].birthday;
					var p5=$("<p>"+(birthday.year+1900)+"-"+((birthday.month+1)<10?"0"+(birthday.month+1):(birthday.month+1))+"-"+((birthday.date)<10?"0"+(birthday.date):(birthday.date))+"</p>");
					td5.append(strong5).append(p5);
					tr5.append(td5);
					
					var tr6=$("<tr></tr>");
					var td6=$("<td></td>");
					var strong6=$("<strong>话费余额:</strong>");
					var p6=$("<p>"+datas[i].tariffe+"</p>");
					td6.append(strong6).append(p6);
					tr6.append(td6);
					
					var tr7=$("<tr></tr>");
					var td7=$("<td></td>");
					var strong7=$("<strong>游币余额:</strong>");
					var p7=$("<p>"+datas[i].currency+"</p>");
					td7.append(strong7).append(p7);
					tr7.append(td7);
					
					tbody.append(tr1).append(tr2).append(tr3).append(tr5).append(tr4).append(tr6).append(tr7);
					table.append(tbody);
					$("#tableDiv").append(table);
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
						<a href="#">欢迎${user.account}</a>
					</li>

					<li class="dropdown">
						<a href="#" class="dropdown-toggle " data-toggle="dropdown">个人中心<b
							class="caret"></b> </a>
						<ul class="dropdown-menu">
							<li>
								<a data-toggle="modal" data-target="#myModal">密保卡充值</a>
							</li>
							<li>
								<a href="prepaid.jsp">充值记录</a>
							</li>
							<li>
								<a href="userDetail.jsp">账户信息</a>
							</li>
							<li>
								<a href="expense.jsp">消费记录</a>
							</li>
							<li>
								<a href="<%=basePath%>user/exit.do">退出</a>
							</li>
						</ul>
					</li>

				</ul>
			</div>
			</nav>
		</div>
		<div class="modal fade" id="myModal" tabindex="-1">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button id="closeButton" type="button" class="close" data-dismiss="modal"
							aria-hidden="true">
							&times;
						</button>
						<h4 class="modal-title" id="myModalLabel">
							密保卡充值
						</h4>
					</div>
					<div class="modal-body" style="height: 160px">
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
								<label for="lastname"  class="col-sm-2 control-label">
									密码
								</label>
								<div class="col-sm-10" style="padding-top: 10px">
									<input id="password" type="text" class="form-control"
										placeholder="请输入卡密码" name="password" />
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-offset-2 col-sm-10" style="padding-top: 10px">
									<input type="button" onclick="addPortal()" class="btn btn-default" value="确认充值">
								</div>
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
				<div class="col-md-6">
					<caption>
						账户详情
					</caption>
					<div id="tableDiv" class="table-responsive">

					</div>
					<a href="portal.jsp" class="btn btn-warning">返回首页</a>
					<a href="modifyPwd.jsp" class="btn btn-primary">修改密码</a>
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
	</body>
</html>
