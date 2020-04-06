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
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta name="description" content="">
		<meta name="author" content="">

		<title>添加游戏</title>

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
		var type=$("#type");
		$.ajax({
			type:"POST",
			url:"<%=basePath%>/gameType/getOnlineGameTypes.do",
			success:function(msg){
				var datas= $.parseJSON(msg);
				for(var i=0;i<datas.length;i++){
					var noTd =$("<option name='type' value="+datas[i].name+" >"+datas[i].name+"</option>");
					type.append(noTd);
				}
			}
		})
	}
	var flag=false;
	function checkBeforeSubmit(){
		var name=$("#name").val();
		var developer=$("#developer").val();
		var introduction=$("#introduction").val();
		var detail=$("#detail").val();
		var filing=$("#filing").val();
		var coverImg=$("#coverImg").val();
		var screenImg1=$("#screenImg1").val();
		var screenImg2=$("#screenImg2").val();
		var screenImg3=$("#screenImg3").val();
		var tariffe=$("#tariffe").val();
		var currency=$("#currency").val();
		var errorInfo = "";
		if(name.trim()==""){
			errorInfo += "游戏名不能为空\r\n";
		}
		if(/^[,.<>\\\/\*\^]{1,}$/.test(name)){
			errorInfo+="游戏名不能包含非法字符\r\n";
		}
		if(developer.trim()==""){
			errorInfo += "开发商不能为空\r\n";
		}
		if(introduction.trim()==""){
			errorInfo += "游戏简介不能为空\r\n";
		}
		if(filing.trim()==""){
			errorInfo += "备案号不能为空\r\n";
		}
		if(coverImg.trim()==""||screenImg1.trim()==""||screenImg2.trim()==""||screenImg3.trim()==""){
			errorInfo += "还有图片未上传\r\n";
		}
		if(tariffe.trim()==""){
			errorInfo += "话费价格不能为空\r\n";
		}else if(!/^(([1-9]\d*)|\d)(\.\d{1,2})?$/.test(tariffe)){
			errorInfo += "话费价格格式错误\r\n";
		}
		if(currency.trim()==""){
			errorInfo += "游戏币价格不能为空\r\n";
		}else if(!/^(([1-9]\d*)|\d)(\.\d{1,2})?$/.test(currency)){
			errorInfo += "游戏币价格格式错误\r\n";
		}
		if(errorInfo.length!=0){
			alert(errorInfo);	
			return false;	
		}
		$.ajax({
			type:"POST",
			url:"<%=basePath%>game/getGameByName.do",
			async:false,
			data:{
				"name":name,
				"oldName":"",
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
					添加游戏
				</h2>
			</div>
			<div class="row">
				<div class="col-xs-6 col-sm-10 col-md-offset-2">
					<form class="form-horizontal text-center"
						 method="post" action="<%=basePath%>game/addGame.do" enctype="multipart/form-data"
						onsubmit="return checkBeforeSubmit()">
						<table id="table" class="table table-bordered table-condensed ">
							<thead></thead>
							<tbody>
								<tr>
									<td>
										游戏名称
									</td>
									<td>
										<div class="col-sm-6 col-md-offset-3">
											<input id="name" type="text" name="name" class="col-sm-12" />
										</div>
									</td>
								</tr>

								<tr>
									<td>
										游戏类型
									</td>
									<td>
										<div class="col-sm-6 col-md-offset-3">
											<select id="type" name="type" class="col-sm-12">
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
											<input id="developer" value="${game.developer}"
												name="developer" type="text" class="col-sm-12"
												placeholder="例(腾讯开发商)" />
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
												class="form-control" rows="3">${game.introduction}</textarea>
										</div>
										<!--  
										<div class="col-sm-6 col-md-offset-3">
											<textarea  name="introduction" class="form-control" rows="3">欢乐斗地主有天地癞子多倍的模式，玩法多样</textarea>
										</div>
										-->
									</td>
								</tr>

								<tr>
									<td>
										游戏详情
									</td>
									<td>
										<!--  
										<div class="col-sm-6 col-md-offset-3">
											<textarea name="detail" class="form-control" rows="3">欢乐斗地主是最近流行的扑克牌新玩法</textarea>
										</div>
										-->
										<div class="col-sm-6 col-md-offset-3">
											<textarea id="detail" name="detail" class="form-control"
												rows="3">${game.detail}</textarea>
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
												class="col-sm-12" value="${game.filing}" />
										</div>
										<!-- 
										<div class=" col-sm-6 col-md-offset-3">
											<input name="filing" type="text" class="col-sm-12"
												value="文网游备字〔2016〕Ｍ-CBG 0011 号" />
										</div>
										 -->
									</td>
								</tr>

								<tr>
									<td>
										封面图片
									</td>
									<td>
										<div class="col-sm-6 col-md-offset-3">
											<div class="caption">
												<p>
													<input id="coverImg" name="coverImg" type="file" accept="image/*" />
												</p>
											</div>

										</div>
									</td>
								</tr>

								<tr>
									<td>
										内容图片
										<br>
										(三张)
									</td>
									<td>
										<div class="col-sm-4 col-md-2 col-md-offset-1">
											<div class="caption">
												<p>
													<input id="screenImg1" name="screenImg1" type="file" accept="image/*"/>
												</p>
											</div>
										</div>

										<div class="col-sm-4 col-md-2 col-md-offset-1">
											<div class="caption">
												<p>
													<input id="screenImg2" name="screenImg2" type="file" accept="image/*"/>
												</p>
											</div>
										</div>

										<div class="col-sm-4 col-md-2 col-md-offset-1">
											<div class="caption">
												<p>
													<input id="screenImg3" name="screenImg3" type="file" accept="image/*"/>
												</p>
											</div>
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
												class="col-sm-12" value="${game.tariffe}" placeholder="小数点后至多保留两位数字" />
										</div>
									</td>
								</tr>

								<tr>
									<td>
										游币价格
									</td>
									<td>
										<div class="col-sm-6 col-md-offset-3">
											<input id="currency" name="currency" type="text"
												class="col-sm-12" value="${game.currency}" placeholder="小数点后至多保留两位数字" />
										</div>
									</td>
								</tr>
							</tbody>
						</table>
						<div class="row">
							<div class="col-xs-6 col-sm-7">
								<div class="form-group">
									<div class="col-xs-6 col-sm-4 col-md-offset-3">
										<button id="addGame" type="submit" class="btn btn-info" ">
											添加游戏
										</button>
									</div>
									<div class="col-xs-6 col-sm-4">
										<a href="game.jsp" class="btn btn-warning">返回上一页</a>
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
	<style>
td {
	vertical-align: middle !important;
}
</style>