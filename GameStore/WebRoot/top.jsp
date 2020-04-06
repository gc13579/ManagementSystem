<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
 <script src="<%=basePath%>js/jquery-2.2.3.min.js"></script>
<link rel="stylesheet" href="css/bootstrap.min.css">
<script src="js/bootstrap.min.js"></script>
<style type="text/css">
</style>
<script type="text/javascript">
	function getCurrentTime(){
		var currentTime=document.getElementById("currentTime");
		var date=new Date();
		var year=date.getFullYear();
		var month=date.getMonth();
		var day =date.getDate();
		var week=date.getDay();
		var hour=date.getHours();
		var minute=date.getMinutes();
		var second=date.getSeconds();
		var weekArray=new Array("星期日","星期一","星期二","星期三","星期四","星期五","星期六");
			currentTime.innerHTML=(year+"/"+((month+1)<10?"0"+(month+1):(month+1))+"/"+(day<10?"0"+day:day)+" "+((hour)>=12?"下午":"上午")+" "+(hour)+":"+(minute<10?"0"+minute:minute)+":"+(second<10?"0"+second:second)+" "+weekArray[week]);
	
	}
	 $(document).ready(function() {
		window.setInterval("getCurrentTime()",1000);
	});
</script>
</head>

<body>
	<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navbar" >
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand">游币后台管理系统</a>
			</div>
			<div id="navbar" class="collapse navbar-collapse ">
				<ul class="nav navbar-nav ">
					<li><a>${manager.account}管理员登录 &nbsp;&nbsp;&nbsp;<span id="currentTime"></span></a>
					</li>
					<span></span>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="<%=basePath%>manager/exit.do" target="_parent">退出</a></li>
				</ul>
			</div>
		</div>
		
	</nav>
		
		
</body>
</html>
