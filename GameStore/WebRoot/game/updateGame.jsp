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

		<title>游戏详情</title>


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
			search2();
			
		});
	
		function search2(){
			var select=$("#select");
			$.ajax({
				type:"POST",
				url:"<%=basePath%>/gameType/getAllGameTypes.do",
				success:function(msg){
					var datas= $.parseJSON(msg);
					for(var i=0;i<datas.length;i++){
						var noTd =$("<option name='type' value="+datas[i].name+" >"+datas[i].name+"</option>");
						select.append(noTd);
					}
				}
			})
		}
	
	
		function search(){
			var id=$("#hiddenId").val();
			$.ajax({
				type:"POST",
				url:"<%=basePath%>game/getGameById.do",
				data:{
					"id":id,
				},
				success:function(msg){
					var data = $.parseJSON(msg);
					$("#name").val(data.name);
					$("#hiddenOldName").val($("#name").val());
					var radio=document.getElementsByName("status");
					if(data.status=="商用"){
						radio[0].checked="checked";
					}
					else{
						radio[1].checked="checked";
					}
					$("#developer").val(data.developer);
					$("#filing").val(data.filing);
					$("#introduction").val(data.introduction);
					$("#detail").val(data.detail);
					$("#currency").val(data.currency);
					$("#tariffe").val(data.tariffe);
					
					var cover="<%=basePath%>"+data.cover;
					$("#coverImg").attr("src",cover);
					$("#hiddenCoverImg").val(data.cover);
					
					var screenImgs1="<%=basePath%>"+data.screenImgs1;
					$("#screenImgs1").attr("src",screenImgs1);
					$("#hiddenScreenImgs1").val(data.screenImgs1);
					var screenImgs2="<%=basePath%>"+data.screenImgs2;
					$("#screenImgs2").attr("src",screenImgs2);
					$("#hiddenScreenImgs2").val(data.screenImgs2);
					var screenImgs3="<%=basePath%>"+data.screenImgs3;
					$("#screenImgs3").attr("src",screenImgs3);
					$("#hiddenScreenImgs3").val(data.screenImgs3);
					
					var time=data.createTime;
					var createTime=(time.year+1900)+"-"+((time.month+1)<10?"0"+(time.month+1):(time.month+1))+"-"+((time.date)<10?"0"+(time.date):(time.date))
						+" "+(time.hours<10?"0"+time.hours:time.hours)+":"+(time.minutes<10?"0"+time.minutes:time.minutes)+":"+(time.seconds<10?"0"+time.seconds:time.seconds);
					$("#createTime").val(createTime);
					
					var time2=data.updateTime;
					var updateTime=(time2.year+1900)+"-"+((time2.month+1)<10?"0"+(time2.month+1):(time2.month+1))+"-"+((time2.date)<10?"0"+(time2.date):(time2.date))
						+" "+(time2.hours<10?"0"+time2.hours:time2.hours)+":"+(time2.minutes<10?"0"+time2.minutes:time2.minutes)+":"+(time2.seconds<10?"0"+time2.seconds:time2.seconds);
					$("#updateTime").val(updateTime);
				}
			})
		}
		var flag=false;
		function checkBeforeSubmit(){
			var name=$("#name").val();
			var oldName=$("#hiddenOldName").val();
			if(/^[,.<>\\\/\*\^]{1,}$/.test(name)){
				alert("游戏名不能包含非法字符");
				return false;
			}
			$.ajax({
				type:"POST",
				url:"<%=basePath%>game/getGameByName.do",
				async:false,
				data:{
					"name":name,
					"oldName":oldName,
				},
				success:function(msg){
					var data = $.parseJSON(msg);
					if(data[0] == "error1"){
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
			<div class="page-header ">
				<h2>
					修改游戏
				</h2>
			</div>
			<div class="row">
				<div class="col-xs-6 col-sm-8 col-md-offset-2">
					<form class="form-horizontal text-center"
						action="<%=basePath%>game/modifyGame.do" method="post"
						onsubmit="return checkBeforeSubmit()"
						enctype="multipart/form-data">
						<table class="table table-bordered table-condensed ">
							<thead></thead>
							<tbody>
								<tr>
									<td>
										游戏名称
									</td>
									<td>
										<div class="col-sm-6 col-md-offset-3">
											<input id="name" name="name" type="text" class="col-sm-12" />
										</div>
									</td>
								</tr>

								<tr>
									<td>
										游戏类型
									</td>
									<td>
										<div class="col-sm-6 col-md-offset-3">
											<select id="select" name="select" class="col-sm-12">
											</select>
										</div>
									</td>
								</tr>

								<tr>
									<td>
										游戏状态
									</td>
									<td>
										<div class="col-sm-6 col-md-offset-3">
											<div class="radio">
												<label>
													<input type="radio" value="商用" name="status"
														checked="checked">
													商用
												</label>
												<label>
													<input type="radio" value="下线" name="status">
													下线
												</label>
											</div>
										</div>
									</td>
								</tr>

								<tr>
									<td>
										开发商
									</td>
									<td>
										<div class="col-sm-6 col-md-offset-3">
											<input id="developer" name="developer" type="text"
												class="col-sm-12" />
										</div>
									</td>
								</tr>

								<tr>
									<td>
										备案号
									</td>
									<td>
										<div class=" col-sm-6 col-md-offset-3">
											<input id="filing" name="filing" type="text"
												class="col-sm-12" />
										</div>
									</td>
								</tr>

								<tr>
									<td>
										游戏简介
									</td>
									<td>
										<div class="col-sm-6 col-md-offset-3">
											<textarea id="introduction" name="introduction"
												class="form-control" rows="3"></textarea>
										</div>
									</td>
								</tr>

								<tr>
									<td>
										游戏详情
									</td>
									<td>
										<div class="col-sm-6 col-md-offset-3">
											<textarea id="detail" name="detail" class="form-control"
												rows="3"></textarea>
										</div>
									</td>
								</tr>

								<tr>
									<td>
										游戏币价格
									</td>
									<td>
										<div class="col-sm-6 col-md-offset-3">
											<input id="currency" name="currency" type="text"
												class="col-sm-12" />
										</div>
									</td>
								</tr>

								<tr>
									<td>
										话费价格
									</td>
									<td>
										<div class="col-sm-6 col-md-offset-3">
											<input id="tariffe" name="tariffe" type="text"
												class="col-sm-12" />
										</div>
									</td>
								</tr>

								<tr>
									<td>
										游戏封面
									</td>
									<td>
										<div class="col-sm-6  col-md-offset-3">
											<div class="thumbnail">
												<img id="coverImg" class="img-responsive img-rounded "
													width="80" />
												<input id="hiddenCoverImg" type="hidden" name="hiddenCoverImg">
											</div>
											<div class="caption">
												<p>
													<input id="newCoverImg" name="newCoverImg" type="file" accept="image/*"/>
												</p>
											</div>

										</div>
									</td>
								</tr>

								<tr>
									<td>
										游戏截图
									</td>
									<td>
										<div class="col-sm-4 col-md-3 col-md-offset-1">
											<div class="thumbnail">
												<img id="screenImgs1" class="img-rounded" width="150">
												<input id="hiddenScreenImgs1" type="hidden"
													name="hiddenScreenImgs1">
											</div>
											<div class="caption">
												<p>
													<input id="newScreenImgs1" name="newScreenImgs1"
														type="file" accept="image/*"/>
												</p>
											</div>
										</div>

										<div class="col-sm-4 col-md-3 col-md-offset-1">
											<div class="thumbnail">
												<img id="screenImgs2" class="img-rounded " width="150">
												<input id="hiddenScreenImgs2" type="hidden"
													name="hiddenScreenImgs2">
											</div>
											<div class="caption">
												<p>
													<input id="newScreenImgs2" name="newScreenImgs2"
														type="file" accept="image/*"/>
												</p>
											</div>
										</div>

										<div class="col-sm-4 col-md-3 col-md-offset-1">
											<div class="thumbnail">
												<img id="screenImgs3" class="img-rounded " width="150">
												<input id="hiddenScreenImgs3" type="hidden"
													name="hiddenScreenImgs3">
											</div>
											<div class="caption">
												<p>
													<input id="newScreenImgs3" name="newScreenImgs3"
														type="file" accept="image/*"/>
												</p>
											</div>
										</div>
									</td>
								</tr>
								<tr>
									<td>
										创建时间
									</td>
									<td>
										<div class="col-sm-6 col-md-offset-3">
											<input id="createTime" name="createTime" type="text"
												class="col-sm-12" onfocus="blur()" />
										</div>
									</td>
								</tr>

								<tr>
									<td>
										修改时间
									</td>
									<td>
										<div class="col-sm-6 col-md-offset-3">
											<input id="updateTime" type="text" class="col-sm-12"
												onfocus="blur()" />
										</div>
									</td>
								</tr>
							</tbody>
						</table>
						<div class="row">
							<div class="col-xs-6 col-sm-10">
								<div class="form-group">
									<div class="col-xs-6 col-sm-4 col-md-offset-3">
										<input type="submit" class="btn btn-info" value="提交修改" />
									</div>
									<div class="col-xs-6 col-sm-4">
										<a href="game.jsp" class="btn btn-warning">返回上一页</a>
									</div>
								</div>
							</div>
						</div>
						<input id="hiddenId" name="id" type="hidden" value="${id}">
						<input id="hiddenOldName" type="hidden">
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

	<style>
td {
	vertical-align: middle !important;
}
</style>
</html>