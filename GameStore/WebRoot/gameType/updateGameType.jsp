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
	if (session.getAttribute("manager") == null) {
		request.setAttribute("isError", true);
		request.setAttribute("errorMessage", "您尚未登录，请登录");
		request.getRequestDispatcher("/login.jsp").forward(request,
				response);
	}
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta name="description" content="">
		<meta name="author" content="">

		<title>修改游戏类型</title>


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
		var gameId=$("#hiddenId").val();
		$.ajax({
			type:"POST",
			url:"<%=basePath%>gameType/getGameTypeById.do",
			async:false,
			data:{
				"id":gameId,
			},
			success:function(msg){
				var data = $.parseJSON(msg);
				$("#name").val(data.name);
				var radio=document.getElementsByName("typeStatus");
				if(data.status=="商用"){
					radio[0].checked="checked";
				}
				else{
					radio[1].checked="checked";
				}
				$("#hiddenOldName").val(data.name);
			}
		})
	}
	var flag=false;
	function checkBeforeSubmit(){
		var name=$("#name").val();
		if(name.trim()==""){
			alert("类型名不能为空");
			return false;
		}
		if(/^[,.<>\\\/\*\^]{1,}$/.test(name)){
			alert("游戏类型不能包含非法字符");
			return false;
		}
		$.ajax({
				type:"POST",
				url:"<%=basePath%>gameType/getGameTypeByName.do",
				data:{
					"gameTypeName":name,
				},
				async:false,
				success:function(msg){
					var data = $.parseJSON(msg);
					if(data != null && data.name != $("#hiddenOldName").val()){
						alert("该游戏已存在");
						flag=true;
					}
				}
			})
			if(flag == true){
				flag=false;
				return false;
			}
			return true;
	}
</script>


	</head>
	<body>
		<div id="container">
			<div class="page-header text-center">
				<h2>
					修改游戏类型
				</h2>
			</div>
			<div class="row">
				<div class="col-xs-6 col-sm-4 col-md-offset-4">
					<form action="<%=basePath%>gameType/updateGameTypeStatus.do"
						class="form-horizontal" onsubmit="return checkBeforeSubmit()">
						<table class="table table-bordered table-condensed ">
							<thead></thead>
							<tbody>
								<tr class="text-center">
									<td>
										类型名称
									</td>
									<td>
										<div class="col-sm-10">
											<input id="name" type="text" name="name" class="form-control">
										</div>
									</td>
								</tr>
								<tr class="text-center">
									<td>
										状态
									</td>
									<td>
										<div class="col-sm-4">
											<div class="radio">
												<label>
													<input type="radio" name="typeStatus" value="商用"
														checked="checked">
													商用
												</label>
												<label>
													<input type="radio" name="typeStatus" value="下线">
													下线
												</label>
											</div>
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
										<a href="gameType.jsp" class="btn btn-warning">返回上一页</a>
									</div>
								</div>
							</div>
						</div>
						<input id="hiddenId" name="id" type="hidden" value="${id}">
					</form>
					<input id="hiddenOldName" type="hidden">
				</div>
			</div>
		</div>
	</body>
</html>
