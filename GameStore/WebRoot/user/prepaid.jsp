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

		<title>游币充值记录</title>
		<link rel="stylesheet" href="../css/bootstrap.min.css">
		<link rel="stylesheet" href="../css/index.css" />
		<script src="../js/jquery.js"></script>
		<script src="../js/bootstrap.min.js"></script>

		<style type="text/css">
strong,p {
	display: inline-block;
}

table {
	display: inline-block;
	width: 33%;
}
</style>
		<script type="text/javascript">
	$(document).ready(function (){
		search();
	})
	
	function search(){
		$.ajax({
			type:"POST",
			url:"<%=basePath%>/prepaid/getAllPrepaidsByUserId.do",
			data:{
				"userId":"${user.id}",
			},
			success:function(msg){
				var datas = $.parseJSON(msg);
				for(var i=0;i<datas.length;i++){
					var table=$("<table class='table table-striped table-hover'  style='width: 260px'></table>");
					var tbody=$("<tbody></tbody>");
				
					var tr1=$("<tr></tr>");
					var td1=$("<td></td>");
					var strong1=$("<strong>用户名:</strong>");
					var p1=$("<p>"+datas[i].userAccount+"</p>");
					td1.append(strong1).append(p1);
					tr1.append(td1);
					
					var tr2=$("<tr></tr>");
					var td2=$("<td></td>");
					var strong2=$("<strong>卡号:</strong>");
					var p2=$("<p>"+datas[i].account+"</p>");
					td2.append(strong2).append(p2);
					tr2.append(td2);
					
					var tr3=$("<tr></tr>");
					var td3=$("<td></td>");
					var strong3=$("<strong>密码:</strong>");
					var p3=$("<p>"+datas[i].password+"</p>");
					td3.append(strong3).append(p3);
					tr3.append(td3);
					
					var tr4=$("<tr></tr>");
					var td4=$("<td></td>");
					var strong4=$("<strong>充值游币数量:</strong>");
					var p4=$("<p>"+datas[i].money+"</p>");
					td4.append(strong4).append(p4);
					tr4.append(td4);
					
					var tr5=$("<tr></tr>");
					var td5=$("<td></td>");
					var strong5=$("<strong>充值时间:</strong>");
					var prepaidTime=datas[i].prepaidTime;
					var p5=$("<p>"+(prepaidTime.year+1900)+"-"+((prepaidTime.month+1)<10?"0"+(prepaidTime.month+1):(prepaidTime.month+1))+"-"+((prepaidTime.date)<10?"0"+(prepaidTime.date):(prepaidTime.date))
						+" "+(prepaidTime.hours<10?"0"+prepaidTime.hours:prepaidTime.hours)+":"+(prepaidTime.minutes<10?"0"+prepaidTime.minutes:prepaidTime.minutes)+":"+(prepaidTime.seconds<10?"0"+prepaidTime.seconds:prepaidTime.seconds)+"</p>");
					td5.append(strong5).append(p5);
					tr5.append(td5);
					
					tbody.append(tr1).append(tr2).append(tr3).append(tr4).append(tr5);
					if(i%5==0){
						$("#tableDiv").append("<hr></hr>");
					}
					table.append(tbody);
					$("#tableDiv").append(table);
				}
				if(datas.length==0){
					$("#caption").text("您尚未充值");
				}else{
					$("#caption").text("充值记录");
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
						<br>
					</li>

					<li class="dropdown">
						<a href="#" class="dropdown-toggle " data-toggle="dropdown">个人中心<b
							class="caret"> </b> </a>
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
								<br>
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
					<p id="caption"></p>
					<div id="tableDiv" class="table-responsive" style="width: 1300px">

					</div>
					<a href="portal.jsp" class="btn btn-warning">返回主页</a>
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
