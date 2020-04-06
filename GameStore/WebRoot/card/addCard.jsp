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
		<title>游币比例管理页面</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta name="description" content="">
		<meta name="author" content="">
		<script src="../js/jquery.js"></script>

		<link rel="stylesheet" type="text/css" href="../css/example-styles.css">
		<script src="../js/jquery-multi-select.min.js"></script>

		<script src="../js/My97DatePicker/WdatePicker.js"></script>
		<link href="../css/bootstrap.min.css" rel="stylesheet">
		<script src="../js/bootstrap.min.js"></script>

		<style type="text/css">
#container {
	margin-top: -20px;
	margin-left: 10px;
}

input,img {
	vertical-align: middle;
}
</style>
		<link href="../css/doubleDate.css" rel="stylesheet" type="text/css" />

		<style type="text/css">
* {
	margin: 0;
	padding: 0;
	list-style-type: none;
}

a,img {
	border: 0;
}

body {
	font: 12px/ 180% Arial, Helvetica, sans-serif, "新宋体";
}

.iptgroup {
	width: 620px;
	height: 60px;
	margin: 20px auto 0 auto;
}

.iptgroup li {
	float: left;
	height: 30px;
	line-height: 30px;
	padding: 5px;
}

.iptgroup li .ipticon {
	background: url(blue/date_icon.gif) 98% 50% no-repeat;
	border: 1px #CFCFCF solid;
	padding: 3px;
}
</style>
<script type="text/javascript">
	function checkBeforeSubmit(){
		var count=$("#count").val();
		var province=$("#province").val();
		var money=$("#money").val();
		var startTime=$("#startTime").val();
		var endTime=$("#endTime").val();
		var errorInfo="";
		if(!/^\d{1,3}$/.test(count)){
			errorInfo+="数量格式不正确"+"\r\n";
		}
		if(province==null){
			errorInfo+="还没有选择省份"+"\r\n";
		}
		if(!/^\d{1,3}$/.test(money)){
			errorInfo+="金额格式不正确"+"\r\n";
		}
		if(startTime==""){
			errorInfo+="还没有选择生效时间"+"\r\n";
		}
		if(endTime==""){
			errorInfo+="还没有选择过期时间"+"\r\n";
		}
		startTime=startTime.replace(/-/g,"/");
		var startTimeDate=new Date(startTime).getTime();
		endTime=endTime.replace(/-/g,"/");
		var endTimeDate=new Date(endTime).getTime();
		if(startTimeDate>endTimeDate){
			errorInfo+="有效开始时间不能大于有效结束时间"+"\r\n";
		}
		if(endTimeDate<new Date().getTime()){
			errorInfo+="不能添加已经过期的密保卡"+"\r\n";
		}
		if(errorInfo.length!=0){
			alert(errorInfo);
			return false;
		}
		return true;
	}
	
	function search2(){
		var province=$("#province");
		$.ajax({
			type:"POST",
			url:"<%=basePath%>province/getAllProvinces.do",
			success:function(msg){
				var datas= $.parseJSON(msg);
				for(var i=0;i<datas.length;i++){
					var noTd =$("<option value="+datas[i].name+">"+"&nbsp;&nbsp;&nbsp;&nbsp;"+datas[i].name+"</option>");
					province.append(noTd);
				}
				$('#province').multiSelect();
			}
		})
	}
	$(document).ready(function(){
		search2();
	})
</script>
	</head>
	<body>
		<div>
			<div id="container">
				<div class="page-header ">
					<h2>
						密保卡生成
					</h2>
				</div>
				<div class="row">
					<div class="col-xs-6 col-sm-4">
						<div class="modal-body">
							<form action="<%=basePath%>card/addCards.do" method="post"
								class="form-horizontal" onsubmit="return checkBeforeSubmit()">
								<table
									class="table table-bordered table-hover table-condensed  ">
									<tbody>
										<tr>
											<td>
												数量(张)
											</td>
											<td>
												<input id="count" name="count" type="text" placeholder="必须输入1~3位的整数" />
											</td>
										</tr>
										<tr>
											<td>
												选择需要生成卡的省份
											</td>
											<td>
												<select id="province" name="province"
													class="selectpicker bla bla bli" multiple
													data-live-search="true">
												</select>
											</td>
										</tr>
										<tr>
											<td>
												金额(游币数量)
											</td>
											<td>
												<input id="money" name="money" type="text" placeholder="必须输入1~3位的整数" />
											</td>
										</tr>
										<tr class="iptgroup">
											<td>
												有效期开始时间
												<input id="startTime" name="startTime" type="text" onclick="WdatePicker()"
													readonly="readonly" autocomplete="off" class="doubledate ipticon" />
											</td>
											<td>
												有效期结束时间
												<input id="endTime" name="endTime" type="text" onclick="WdatePicker()"
													readonly="readonly" autocomplete="off" class="doubledate ipticon" />
											</td>
										</tr>
									</tbody>
								</table>

								<div class="form-group">
									<div class="col-sm-offset-2 col-sm-10">
										<button type="submit" class="btn btn-primary">生成密保卡</button>
										<a href="card.jsp" class="btn btn-warning">返回上一页</a>
									</div>
								</div>
							</form>
						</div>
					</div>
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
