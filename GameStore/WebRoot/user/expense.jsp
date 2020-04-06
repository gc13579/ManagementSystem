<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%
	if (session.getAttribute("user") == null) {
		request.setAttribute("isError", true);
		request.setAttribute("errorMessage", "您尚未登录，请登录");
		request.getRequestDispatcher("/user/login.jsp").forward(
				request, response);
	}
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta name="description" content="">
		<meta name="author" content="">

		<title>游币消费记录</title>
		<link rel="stylesheet" href="../css/bootstrap.min.css">
		<link rel="stylesheet" href="../css/index.css" />
		<script src="../js/jquery.js"></script>
		<script src="../js/bootstrap.min.js"></script>
		<script type="text/javascript">
	$(document).ready(function (){
		search(1);
		
	})
	
	function search(pageNo){
		$.ajax({
			type:"POST",
			url:"<%=basePath%>expense/getAllExpenses.do",
			data:{
				"pageNo":pageNo,
				"id":"${user.id}",
			},
			success:function(msg){
				var pager = $.parseJSON(msg);
				var datas = pager.list;
				$("#firstPage").remove();
				$("#lastPage").remove();
				$("#nextPage").remove();
				$("#endPage").remove();
				if(datas.length==0){
					$("#caption").text("您尚未购买游戏");
				}else{
					$("#caption").text("购买记录");
					var searchTable = $("#searchTable");
					var tr0 = $("<tr></tr>");
					tr0.append($("<td>"+"用户名"+"<td>")).append($("<td>游戏名<td>")).append($("<td>购买方式<td>")).append($("<td>购买价格<td>")).append($("<td>购买时间<td>"));
					searchTable.append(tr0);
					searchTable.html($("table tr")[0]);
						for(var i = 0; i < datas.length;i++){
							var tr1 = $("<tr></tr>");
							var td1=$("<td>"+datas[i].userName+"</td><td></td>");
							var td2=$("<td>"+datas[i].gameName+"</td><td></td>");
							var td3=$("<td>"+datas[i].way+"</td><td></td>");
							var td4=$("<td>"+datas[i].money+"</td><td></td>");
							var buyTime=datas[i].buyTime;
							var td5=$("<td>"+(buyTime.year+1900)+"-"+((buyTime.month+1)<10?"0"+(buyTime.month+1):(buyTime.month+1))+"-"+((buyTime.date)<10?"0"+(buyTime.date):(buyTime.date))
							+" "+(buyTime.hours<10?"0"+buyTime.hours:buyTime.hours)+":"+(buyTime.minutes<10?"0"+buyTime.minutes:buyTime.minutes)+":"+(buyTime.seconds<10?"0"+buyTime.seconds:buyTime.seconds)+"</td><td></td>");
							
							tr1.append(td1).append(td2).append(td3).append(td4).append(td5);
							searchTable.append(tr1);
						}
						for(;i<3;i++){
							var tr = $("<tr><td>&nbsp;</td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>");
							searchTable.append(tr);
						}
						$("#tableDiv").append("<input id='firstPage' type='button' value='首页'>");
						$("#tableDiv").append("<input id='lastPage' type='button' value='上一页'>");
						$("#tableDiv").append("<input id='nextPage' type='button' value='下一页'>");
						$("#tableDiv").append("<input id='endPage' type='button' value='末页'>");
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
				}
			});
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
						<a href="#" class="dropdown-toggle " data-toggle="dropdown">
							个人中心 <b class="caret"> </b> </a>
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
							<li class="divider"></li>
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
				<div class="col-md-12">
					<p id="caption"></p>
					<div id="tableDiv" class="table-responsive"
						style="text-align: center">
						<table id="searchTable" class="table table-striped table-hover">
							<!--  
							<thead>
								<tr>
									
								</tr>
							</thead>
							<tbody>
								<tr id="tr">
								</tr>
							</tbody>
							-->
						</table>
					</div>
					<a href="portal.jsp" class="btn btn-warning">返回上页</a>
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
