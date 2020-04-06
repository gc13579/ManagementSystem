<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	request.setAttribute("name", request.getAttribute("name"));
	request.setAttribute("status", request.getAttribute("status"));
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

		<title>添加游戏类型</title>


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
	var flag=false;
	function checkBeforeSubmit(){
		var name = $("#name").val();
		var imgFile = $("#imgFile").val();
		var errorInfo = "";
		if (name.trim() == "") {
			errorInfo += "游戏类型名不能为空\r\n";
		}
		if(/^[,.<>\\\/\*\^]{1,}$/.test(name)){
			errorInfo+="游戏类型不能包含非法字符\r\n";
		}
		if (imgFile.trim() == "") {
			errorInfo += "还没有选择文件\r\n";
		}
		if (errorInfo.length > 0) {
			alert(errorInfo);
			return false;
		}
		$.ajax({
			type:"POST",
			url:"<%=basePath%>gameType/getGameTypeByName.do",
			async:false,
			data:{
				"gameTypeName":name,
			},
			success:function(msg){
				var data=$.parseJSON(msg);
				console.log(data[0]);
				if(data[0] != null){
					alert("该游戏类型已存在");
					flag = true;
				}
			}
		})
		if(flag == true){
   			flag = false;
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
					添加游戏类型
				</h2>
			</div>
			<div class="row">
				<div class="col-xs-6 col-sm-4 col-md-offset-4">
					<form action="<%=basePath%>gameType/addGameType.do" method="post"
						class="form-horizontal" enctype="multipart/form-data"
						onsubmit="return checkBeforeSubmit()">
						<table class="table table-bordered table-condensed ">
							<thead></thead>
							<tbody>
								<tr class="text-center">
									<td>
										类型名称
									</td>
									<td>
										<div class="col-sm-10">
											<input type="text" name="name" class="form-control" id="name">
										</div>
									</td>
								</tr>
								<tr class="text-center">
									<td>
										图片
									</td>
									<td>
										<div class="col-sm-10">
											<input type="file" name="imgFile" class="form-control"
												id="imgFile" accept="image/*">
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
										<button type="submit" class="btn btn-primary">
											确认添加
										</button>
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
					</form>

				</div>
			</div>
		</div>
		<c:if test="${isError }">
			<script type="text/javascript">
				alert("${errorMessage}");
			</script>
		</c:if>
	</body>
</html>
