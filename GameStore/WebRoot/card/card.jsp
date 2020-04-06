<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
		<script src="../js/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript" src="../js/bootstrap-select.js">
	
</script>
		<link rel="stylesheet" type="text/css"
			href="../css/bootstrap-select.css">
		<!-- 3.0 -->
		<link href="../css/bootstrap.min.css" rel="stylesheet">
		<script src="../js/bootstrap.min.js"></script>
		<style type="text/css">
#container {
	margin-top: -20px;
	margin-left: 10px;
}
</style>
		<script type="text/javascript">
	
	function search2(){
		var province=$("#province");
		$.ajax({
			type:"POST",
			url:"<%=basePath%>province/getAllProvinces.do",
			success:function(msg){
				var datas= $.parseJSON(msg);
				province.append($("<option>"+"请选择"+"</option>"));
				for(var i=0;i<datas.length;i++){
					var noTd =$("<option value="+datas[i].name+" >"+datas[i].name+"</option>");
					province.append(noTd);
				}
			}
		})
	}
	
	
	var oldAccount="";
	var oldStartTime="";
	var oldEndTime="";
	var oldProvince="";
	function search(pageNo){
		var account = $("#account").val();
		var startTime = $("#startTime").val();
		var endTime = $("#endTime").val();
		var province = $("#province").val();
		var startDate=new Date(startTime.replace(/-/g,"/")).getTime();
		var endDate=new Date(endTime.replace(/-/g,"/")).getTime();
		if(startDate>=endDate){
			alert("生效时间不能晚于结束时间");
			return;
		}
		if(province==null){
			province="请选择";
		}
		if(oldAccount!=account||oldStartTime!=startTime||oldEndTime!=endTime||oldProvince!=province){
			pageNo=1;
		}
		oldAccount=account;
		oldStartTime=startTime;
		oldEndTime=endTime;
		oldProvince=province;
		$.ajax({
			type:"POST",
			url:"<%=basePath%>card/getAllCards.do",
			data:{
				"pageNo":pageNo,
				"account":account,
				"startTime":startTime,
				"endTime":endTime,
				"province":province,
			},
			success:function(msg){
				var pager = $.parseJSON(msg);
				var datas = pager.list;
				var searchTable = $("#searchTable");
				searchTable.html($("table tr")[0]);
					for(var i = 0; i < datas.length;i++){
						var tr = $("<tr></tr>");
						var td1=$("<td>"+datas[i].id+"</td>");
						var td2=$("<td>"+datas[i].account+"</td>");
						var td3=$("<td>"+datas[i].password+"</td>");
						var td4=$("<td>"+datas[i].money+"</td>");
						var td5=$("<td>"+datas[i].province+"</td>");
						var startTime=datas[i].startTime;
						var td6=$("<td>"+(startTime.year+1900)+"-"+((startTime.month+1)<10?"0"+(startTime.month+1):(startTime.month+1))+"-"+((startTime.date)<10?"0"+(startTime.date):(startTime.date))
						+" "+(startTime.hours<10?"0"+startTime.hours:startTime.hours)+":"+(startTime.minutes<10?"0"+startTime.minutes:startTime.minutes)+":"+(startTime.seconds<10?"0"+startTime.seconds:startTime.seconds)+"</td>");
						var endTime=datas[i].endTime;
						var td7=$("<td>"+(endTime.year+1900)+"-"+((endTime.month+1)<10?"0"+(endTime.month+1):(endTime.month+1))+"-"+((endTime.date)<10?"0"+(endTime.date):(endTime.date))
						+" "+(endTime.hours<10?"0"+endTime.hours:endTime.hours)+":"+(endTime.minutes<10?"0"+endTime.minutes:endTime.minutes)+":"+(endTime.seconds<10?"0"+endTime.seconds:endTime.seconds)+"</td>");
						var td8=$("<td>"+datas[i].status+"</td>");
						var createTime=datas[i].createTime;
						var td9=$("<td>"+(createTime.year+1900)+"-"+((createTime.month+1)<10?"0"+(createTime.month+1):(createTime.month+1))+"-"+((createTime.date)<10?"0"+(createTime.date):(createTime.date))
						+" "+(createTime.hours<10?"0"+createTime.hours:createTime.hours)+":"+(createTime.minutes<10?"0"+createTime.minutes:createTime.minutes)+":"+(createTime.seconds<10?"0"+createTime.seconds:createTime.seconds)+"</td>");
						tr.append(td1).append(td2).append(td3).append(td4).append(td5).append(td6).append(td7).append(td8).append(td9);
						searchTable.append(tr);
					}
					for(;i<5;i++){
						var tr = $("<tr><td>&nbsp;</td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>");
						searchTable.append(tr);
					}
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
		});
	}
	
	$(document).ready(function(){
		search2();
		search(1);
	})
</script>
	</head>
	<body>
		<div>
			<div id="container">
				<div class="page-header ">
					<h2>
						密保卡管理
					</h2>
				</div>
				<form action="">
					<div class="row">
						<div class="col-xs-4 col-sm-12 ">
							<div class="row">
								<div class="col-xs-4 col-sm-2">
									<span class="btn btn-default">卡号
										 <input id="account" name="account" type="text" style="width: 100px" />
									 </span>
								</div>
								<div class="col-xs-4 col-sm-3 ">
									<div class="row">
										<div class="col-xs-6 col-sm-4 ">
											<span class="btn btn-default">有效期开始时间 <input
													id="startTime" name="startTime" type="text"
													autocomplete="off" onclick="WdatePicker()" style="width: 100px"/> <img
													src="../js/My97DatePicker/skin/datePicker.gif" width="16"
													height="22" /> </span>
										</div>
									</div>
								</div>

								<div class="col-xs-4 col-sm-3 ">
									<div class="row">
										<div class="col-xs-8 col-sm-4">
											<span class="btn btn-default">有效期结束时间 <input
													id="endTime" name="endTime" type="text" autocomplete="off"
													onclick="WdatePicker()" style="width: 100px" /> <img
													src="../js/My97DatePicker/skin/datePicker.gif" width="16"
													height="22" /> </span>
										</div>
									</div>
								</div>

								<div class="col-xs-4 col-sm-2 ">
									<div class="row">
										<div class="col-xs-8 col-sm-3">
											<span class="btn btn-default"> 省份 <select
													id="province" name="province">
												</select> </span>
										</div>
									</div>
								</div>
								<div class="col-xs-4 col-sm-1 ">
									<div class="row">
										<div class="col-xs-6 col-sm-4 ">
											<input type="button" class="btn btn-primary"
												onclick="search(1)" value="查询" />
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</form>
				<div class="row">
					&nbsp;
				</div>
				<div class="row">
					<div class="col-xs-6 col-sm-4">
						<a href="addCard.jsp" class="btn btn-primary">批量生成密保卡</a>

					</div>
				</div>
				<div class="row">
					&nbsp;
				</div>
				<div class="row">
					<div class="col-xs-6 col-sm-11">
						<div class="table-responsive"
							style="vertical-align: middle; text-align: center;">
							<table id="searchTable"
								class="table table-bordered table-hover table-condensed  ">
								<tr>
									<td>
										ID
									</td>
									<td>
										卡号
									</td>
									<td>
										密码
									</td>
									<td>
										金额
									</td>
									<td>
										省份
									</td>
									<td>
										有效期开始时间
									</td>
									<td>
										有效期结束时间
									</td>
									<td>
										使用状态
									</td>
									<td>
										创建时间
									</td>
								</tr>
								<!--  
								<tr>
									<td><input type='checkbox' 
										name='opt' value='' onclick="clickOne()" /></td>
									<td>1</td>
									<td>MSD1WUYBJJNG</td>
									<td>SZ48b8</td>
									<td>100</td>
									<td>江苏</td>
									<td>2017-05-01 12:16:19</td>
									<td>2017-06-01 12:16:19</td>
									<td>
										未生效
									</td>
									<td>
										2017-05-13 12:16:19
									</td>
								</tr>
								-->
							</table>
							<input id="firstPage" type="button" value="首页">
							<input id="lastPage" type="button" value="上一页">
							<input id="nextPage" type="button" value="下一页">
							<input id="endPage" type="button" value="末页">
						</div>
					</div>
				</div>
			</div>
		</div>
		<c:if test="${isSuccess }">
			<script type="text/javascript">
				alert("${success}");
			</script>
		</c:if>
	</body>

	<style>
td {
	vertical-align: middle !important;
}
</style>
</html>
