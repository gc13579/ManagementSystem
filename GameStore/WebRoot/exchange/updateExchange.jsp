<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
request.setAttribute("provinceId",request.getParameter("provinceId"));
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

<title>修改兑换比例</title>


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
		var id=$("#hiddenInput").val();
		$.ajax({
			type:"POST",
			url:"<%=basePath%>exchange/getExchangeById.do",
			data:{
				"provinceId":id,
			},
			success:function(msg){
				var data= $.parseJSON(msg);
				$("#province").val(data.province);
				var radios=document.getElementsByName("exchangeStatus");
				if(data.status=="商用"){
					radios[0].checked="checked";
				}
				else{
					radios[1].checked="checked";
				}
				$("#charge").val(data.charge);
			}
		})
	}
	
	function checkBeforeSubmit(){
		var charge=$("#charge").val();
		if(!/^\d{1,2}$/.test(charge)){
			alert("兑换比例格式不正确，请输入1~2位正整数");
			return false;
		}
		return true;
	}
	
	$(document).ready(function(){
		search();
	})
</script>
</head>
<body>
	<div id="container">
		<div class="page-header text-center">
			<h2>&nbsp;修改对应省份游币兑换比例</h2>
		</div>
		<div class="row">
			<div class="col-xs-6 col-sm-4 col-md-offset-4">
				<form class="form-horizontal" action="<%=basePath%>exchange/modifyExchange.do" onsubmit="return checkBeforeSubmit()">
					<table class="table table-bordered table-condensed ">
						<thead></thead>
						<tbody>
							<tr class="text-center">
								<td>省份名</td>
								<td>
									<div class="col-sm-4">
										<div class="radio">
											<input id="province" type="text" onfocus="blur()">
										</div>
									</div>
								</td>
							</tr>
							<tr class="text-center">
								<td>兑换状态</td>
								<td>
									<div class="col-sm-4">
										<div id="radio" class="radio">
											<label> 
												<input type="radio" value="商用" name="exchangeStatus"> 商用 
											</label> 
											<label> 
												<input type="radio" value="下线" name="exchangeStatus"> 下线
											</label>
										</div>
									</div>
								</td>
							</tr>
							<tr class="text-center">
								<td>兑换比例</td>
								<td>
									<div class="col-sm-4">
										<div class="radio">
											<input id="charge" name="charge" type="text"/>
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
									<input type="submit" class="btn btn-primary" value="确认修改" />
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
					<input id="hiddenInput" name="provinceId" type="hidden" value="${provinceId}">
					<input id="hiddenInput2" name="id" type="hidden" value="${id}">
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
