<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
request.setAttribute("name",request.getParameter("name"));
request.setAttribute("status",request.getParameter("status"));
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

<title>修改密码</title>


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
   		
   		function checkBeforeReset(){
   			var newPwd = $("#newPwd").val();
   			var confPwd = $("#confPwd").val();
   			if(${user.password} == newPwd){
   				alert("新密码不能与原密码相同");
   				return false;
   			}
   			if(newPwd.length<6||newPwd.length>16){
   				alert("新密码长度必须为6~16位");
   				return false;
   			}
   			if(newPwd.trim()==""||confPwd.trim()==""){
   				alert("输入值不能为空");
   				return false;
   			}else if(newPwd != confPwd){
   				alert("新密码与确认密码不一致");
   				return false;
   			}else{
   				alert("更新密码成功！");
   				$("#newPwd").val(newPwd);
   				return true;
   			}
   		}
</script>
</head>
<body>
	<div id="container">
		<div class="page-header text-center">
			<h2>修改密码</h2>
		</div>
		<div class="row">
			<div class="col-xs-6 col-sm-4 col-md-offset-4">
				<form action="<%=basePath%>user/modifyPwd.do" class="form-horizontal" onsubmit="return checkBeforeReset()">
					<table class="table table-bordered table-condensed ">
						<thead></thead>
						<tbody>
							<tr class="text-center">
								<td>新密码</td>
								<td>
									<div class="col-sm-10">
										<input id = "newPwd"  name = "newPwd" type="password" class="form-control">
									</div>
								</td>
							</tr>
							<tr class="text-center">
								<td>确认新密码</td>
								<td>
									<div class="col-sm-10">
										<input id = "confPwd" name = "confPwd" type="password" class="form-control">
									</div>
								</td>
							</tr>
						</tbody>
					</table>
					<div class="row">
						<div class="col-xs-6 col-sm-7">
							<div class="form-group">
								<div class="col-sm-offset-4 col-sm-4">
									<input type="submit" class="btn btn-primary" value="确认修改" />
								</div>
							</div>
						</div>
						<div class="col-xs-6 col-sm-4">
							<div class="form-group">
								<div class=" col-sm-11">
									<a href="userDetail.jsp" class="btn btn-warning">返回上一页</a>
								</div>
							</div>
						</div>
					</div>
					<input type="hidden" name="userId" value="${user.id}">
				</form>

			</div>
		</div>
	</div>
</body>
</html>
