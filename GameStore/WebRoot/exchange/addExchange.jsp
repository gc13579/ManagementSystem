<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
		<title>添加换算比例</title>
		<script src="../js/jquery.js"></script>
		<link rel="stylesheet" href="../css/bootstrap.min.css">
		<script src="../js/bootstrap.min.js"></script>
		<style type="text/css">
#container {
	margin-top: -20px;
	margin-left: 10px;
}
</style>
<script type="text/javascript">
	function search(){
		$.ajax({
			type:"POST",
			url:"<%=basePath%>exchange/getAllExchanges.do",
			async:false,
			success:function(msg){
				var datas= $.parseJSON(msg);
				$("#hiddenExistedProvincesIds").val(datas);
			}
		})
	}
	
	function search2(){
		var provinceId=$("#provinceId");
		var existedProvincesIds=$("#hiddenExistedProvincesIds").val();
		$.ajax({
			type:"POST",
			url:"<%=basePath%>province/getSomeProvinces.do",
			data:{
				"str":existedProvincesIds,
			},
			success:function(msg){
				var datas= $.parseJSON(msg);
				provinceId.append($("<option>"+"请选择"+"</option>"));
				for(var i=0;i<datas.length;i++){
					var noTd =$("<option value="+datas[i].id+" >"+datas[i].name+"</option>");
					provinceId.append(noTd);
				}
			}
		})
	}
	$(document).ready(function(){
		search();
		search2();
	})
	
	function checkBeforeSubmit(){
		var provinceId=$("#provinceId").val();
		var charge=$("#charge").val();
		var errorInfo="";
		if(provinceId=="请选择"){
			errorInfo+="还没有选择省份"+"\r\n";
		}
		if(charge.trim()==""){
			errorInfo+="兑换比例不能为空"+"\r\n";
		}else if(!/^\d{1,2}$/.test(charge)){
			errorInfo+="兑换比例格式错误"+"\r\n";
		}
		if(errorInfo.length!=0){
			alert(errorInfo);
			return false;
		}
		existedChargeProvnceId+=provinceId+",";
		return true;
	}
	
</script>
	</head>
	<body>
		<div id="container">
			<div class="page-header text-center">
				<h2> 
					添加对应省份游币兑换比例 
				</h2>
			</div>
			<div class="row">
				<div class="col-xs-6 col-sm-4 col-md-offset-4">
					<form class="form-horizontal"
						action="<%=basePath%>exchange/addExchange.do"
						onsubmit="return checkBeforeSubmit()">
						<table class="table table-bordered table-condensed ">
							<thead></thead>
							<tbody>
								<tr class="text-center">
									<td>
										省份名
									</td>
									<td>
										<div class="col-sm-4">
											<div class="radio">
												<select id="provinceId" name="provinceId">
												</select>
											</div>
										</div>
									</td>
								</tr>
								<tr class="text-center">
									<td>
										兑换比例
									</td>
									<td>
										<div class="col-sm-4">
											<div class="radio">
												<input id="charge" name="charge" placeholder="请输入1~2位正整数" type="text" />
											</div>
										</div>
									</td>
								</tr>
								<tr class="text-center">
									<td>
										兑换状态
									</td>
									<td>
										<div class="col-sm-4">
											<div class="radio">
												<label>
													<input type="radio" name="exchangeStatus" value="商用"
														checked="checked">
													商用
												</label>
												<label>
													<input type="radio" name="exchangeStatus" value="下线">
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
									<div class="col-sm-offset-2 col-sm-4">
										<input type="submit" class="btn btn-primary" value="确认添加" />
									</div>
								</div>
							</div>
							<div class="col-xs-6 col-sm-4">
								<div class="form-group">
									<div class=" col-sm-11">
										<a class="btn btn-warning" href="exchange.jsp">返回上一页</a>
									</div>
								</div>
							</div>
						</div>
					</form>
					<input id="hiddenExistedProvincesIds" type="hidden">
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
