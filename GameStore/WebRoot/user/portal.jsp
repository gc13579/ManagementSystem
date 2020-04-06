<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta name="description" content="">
		<meta name="author" content="">

		<title>游币门户网站</title>
		<link rel="stylesheet" href="<%=basePath%>css/bootstrap.min.css">
		<link href="<%=basePath%>/css/jquery-accordion-menu.css"
			rel="stylesheet" type="text/css" />
		<link href="<%=basePath%>css/font-awesome.css" rel="stylesheet"
			type="text/css" />
		<script src="<%=basePath%>js/jquery.js"></script>
		<script src="<%=basePath%>js/bootstrap.min.js"></script>
		<script src="<%=basePath%>js/particles.js"></script>
		<script src="<%=basePath%>js/app.js"></script>
		<style type="text/css">
* {
	box-sizing: border-box;
	-moz-box-sizing: border-box;
	-webkit-box-sizing: border-box;
}

.content {
	width: 260px;
	margin: 100px auto;
}

.filterinput {
	background-color: rgba(249, 244, 244, 0);
	border-radius: 15px;
	width: 90%;
	height: 30px;
	border: thin solid #FFF;
	text-indent: 0.5em;
	font-weight: bold;
	color: #FFF;
}

#demo-list a {
	overflow: hidden;
	text-overflow: ellipsis;
	-o-text-overflow: ellipsis;
	white-space: nowrap;
	width: 100%;
}
</style>
		<style type="text/css">
* {
	padding: 0;
	margin: 0;
}

ul {
	list-style: none;
}

.out {
	width: 350px;
	height: 245px;
	margin: 25px auto;
	position: relative;
}

.img li {
	position: absolute;
	top: 0px;
	left: 0px;
	display: none
}

.out .num {
	position: absolute;
	bottom: 0px;
	left: 0px;
	font-size: 0px;
	text-align: center;
	width: 100%;
}

.num li {
	width: 20px;
	height: 20px;
	background: #666666;
	color: #FFFFFF;
	text-align: center;
	line-height: 20px;
	display: inline-block;
	font-size: 16px;
	border-radius: 50%;
	margin-right: 10px;
	cursor: pointer;
}

.out .btn {
	position: absolute;
	top: 52%;
	margin-top: -30px;
	width: 45px;
	height: 60px;
	background: rgba(0, 0, 0, 0.5);
	color: #FFFFFF;
	text-align: center;
	line-height: 60px;
	font-size: 40px;
	display: none;
	cursor: pointer;
}

.out .num li.active-1 {
	background: #AA0000;
	margin-left: 150px;
}

.out:hover .btn {
	display: block
}

.out .left {
	left: 0px;
}

.out .right {
	right: -150px;
}

#leftNav {
	margin-left: 87px;
}

#gameShow {
	width: 700px;
}

#gameShow .thumbnail {
	vertical-align: top;
	display: inline-block;
	width: 30%;
	height: 300px;
}
</style>
		<script src="../js/jquery-accordion-menu.js" type="text/javascript"></script>

		<script type="text/javascript">
		$(document).ready(function(){
			searchGameTypes();
			window.setInterval("getCurrentTime()",1000);
		});
		
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
		
		var oldName="";
		//type来自导航按钮，换页或搜索按钮
		//来自导航，换页时，type的值为按钮的值（非空）
		//来自搜索时，type的值暂时为空，在该函数中，type再被赋值为搜索时，下拉框选中的游戏类型
		function searchGames(pageNo,type){
			var gameShow=$("#gameShow");
			gameShow.empty();
			$("#firstPage").remove();
			$("#lastPage").remove();
			$("#nextPage").remove();
			$("#endPage").remove();
			var searchType=$("#type").val();
			var searchInput=$("#input").val();
			//当是搜索按钮触发此函数，type为下拉框选中的游戏类型
			if(type==""){
				type=searchType;
			}
			//搜索框的值发生改变时，换页不受影响，换页时仍旧采用之前的搜索条件
			//只有点击搜索按钮，新条件才生效!!!
			if(type!=""&& oldName != searchInput){
				searchInput=oldName;
			}
			oldName=searchInput;
			$.ajax({
				type:"POST",
				url:"<%=basePath%>game/getAllgames.do",
				data:{
					"pageNo":pageNo,
					"type":type,
					"name":searchInput,
					"pageSizeFlag":"6",
				},
				success:function(msg){
					var pager = $.parseJSON(msg);
					var datas = pager.list;
					for(var i=0;i<datas.length;i++){
						var div=$("<div class='thumbnail'></div>");
						var img=$("<img src=<%=basePath%>"+datas[i].cover+" width=150px height=150px></img>");
						var subDiv=$("<div class='caption'></div>");
						var h3=$("<h3>"+datas[i].name+"</h3>");
						var p=$("<p></p>");
						var a=$("<a class='btn btn-primary' href=gameDetail.jsp?id="+datas[i].id+">游戏详情</a>");
						p.append(a);
						subDiv.append(h3).append(p);
						div.append(img).append(subDiv);
						gameShow.append(div);
					}
					for(;i<6;i++){
						gameShow.append($("<div class='thumbnail'></div>"));
					}
					var firstPage=$("<input id='firstPage' type='button' value='首页'"+"onclick='searchGames(1,\""+ type  +"\")' "+">");
					var lastPage=$("<input id='lastPage' type='button' value='上一页'"+"onclick='searchGames("+(pager.pageNo-1)+",\""+ type  +"\")' "+">");
					var nextPage=$("<input id='nextPage' type='button' value='下一页'"+"onclick='searchGames("+(pager.pageNo+1)+",\""+ type  +"\")' "+">");
					var endPage=$("<input id='endPage' type='button' value='末页'"+"onclick='searchGames("+(pager.totalPage)+",\""+ type  +"\")' "+">");
					
					$("#nail2").append(firstPage).append(lastPage).append(nextPage).append(endPage);
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
					//清空的目的，防止按钮查询后，条件仍然存在，妨碍其他两处的点击查询
					$("#type").val("请选择");
					$("#input").val("");
				}
			})
		}
	
		
		function searchGameTypes(){
			$.ajax({
				type:"POST",
				url:"<%=basePath%>gameType/getAllGameTypesByGameCount.do",
				success:function(msg){
					var datas = $.parseJSON(msg);
					var ul=$("#demo-list");
					
					var ul2=$("#ul");
					var li0=$("<li></li>");
					var a0=$("<a href='portal.jsp' class='btn btn-primary'>"+"首页"+"</a>");
					li0.append(a0);
					ul2.append(li0);
					
					for(var i = 0; i < datas.length;i++){
						var li1=$("<li></li>");
						var a1=$("<a onclick=searchGames(1,\""+datas[i].name+"\")></a>");
						var img1=$("<img width="+50+"px src=<%=basePath%>"+datas[i].picture+">"+datas[i].name+"</img>");
						a1.append(img1);
						li1.append(a1);
						ul.append(li1);
						
						var li2=$("<li></li>");
						var a2=$("<a class='btn btn-primary' onclick=searchGames(1,\""+datas[i].name+"\")"+">"+datas[i].name+"</a>");
						li2.append(a2);
						ul2.append(li2);
					}
					var li3=$("<li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</li>");
					ul2.append(li3);
					
					var li4=$("<li></li>");
					var select=$("<select id='type' class='form-control'></select>");
					var option=$("<option name='type' value="+"请选择"+" >"+"请选择"+"</option>");
					select.append(option);
					for(var i=0;i<datas.length;i++){
						var noTd =$("<option name='type' value="+datas[i].name+">"+datas[i].name+"</option>");
						select.append(noTd);
					}
					li4.append(select);
					ul2.append(li4);
					
					var li5=$("<li></li>");
					var input=$("<input id='input' name='type' type='text' class='form-control'/>");
					li5.append(input);
					ul2.append(li5);
					
					var li6=$("<li></li>");
					var a2=$("<a class='btn btn-primary' onclick=searchGames(1,\"\")"+">查询</a>");
					li6.append(a2);
					ul2.append(li6);
				//	var li2=$("<li></li>");
				//	var a2=$("<a href='#'></a>");
				//	var i1=$("<i class='fa fa-suitcase'></i>");
				//	var span=$("<span class='submenu-indicator'>+</span>");
				//	a2.append(i1);
				//	a2.append(span);
				//	li2.append(a2);
				//	var ul2=$("<ul class='submenu'></ul>");
				//	var lii=$("<li>xxx</li>");
				//	ul2.append(lii);
				//	li2.append(ul2);
				//	ul.append(li2);
				}
			})
		}
		var flag=false;
		function addPortal(){
			var account=$("#account").val();
			var password=$("#password").val();
			if(account.trim()==""){
				alert("密保卡号不能为空");
				return false;
			}
			if(password.trim()==""){
				alert("密保卡密码不能为空");
				return false;
			}
			$.ajax({
				type:"POST",
				url:"<%=basePath%>prepaid/addPrepaid.do",
				data:{
					"account":account,
					"password":password,
					"userProvince":"${user.province}",
					"userId":"${user.id}",
					"userAccount":"${user.account}",
					"userCurrency":"${user.currency}",
				},
				success:function(msg){
					var data= $.parseJSON(msg);
					alert(data[0]);
					if(data[0]!="充值成功,可前往个人中心-充值记录查看"){
						flag=true;
					}else{
						$("#closeButton").click();
					}
				}
			})
			if(flag==true){
				flag=false;
				return false;
			}
			return false;
		}
	</script>

	</head>
	<body>
		<div class="container">
			<nav class="navbar navbar-default">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#example-navbar-collapse">
					<span class="sr-only">切换导航</span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="portal.jsp">游币手机下载门户网站&nbsp;&nbsp;&nbsp;&nbsp;<span id="currentTime"></span></a>
			</div>
			<div class="collapse navbar-collapse" id="example-navbar-collapse">
				<ul class="nav navbar-nav">
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<c:choose>
						<c:when test="${user eq null}">
							<li class="dropdown">
								<a href="#">欢迎游客</a>
							</li>
							<li class="dropdown">
								<a href="login.jsp">登录</a>
							</li>
							<li class="dropdown">
								<a href="register.jsp">注册</a>
							</li>
						</c:when>
						<c:otherwise>
							<li class="dropdown">
								<a href="#">欢迎${user.account}</a>
							</li>
							<li class="dropdown">
								<a href="#" class="dropdown-toggle" data-toggle="dropdown">个人中心<b
									class="caret"></b> </a>
								<ul class="dropdown-menu">
									<li>
										<a data-toggle="modal" data-target="#myModal">密保卡充值</a>
									</li>
									<li>
										<a href="prepaid.jsp">充值记录</a>
									</li>
									<li>
										<a href="userDetail.jsp">账户信息</a>
									</li>
									<li>
										<a href="expense.jsp">消费记录</a>
									</li>
									<li class="divider"></li>
									<li>
										<a href="<%=basePath%>user/exit.do">退出</a>
									</li>
								</ul>
							</li>
						</c:otherwise>
					</c:choose>
				</ul>
			</div>
			</nav>
		</div>
		<div class="modal fade" id="myModal" tabindex="-1">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button id="closeButton" type="button" class="close" data-dismiss="modal"
							aria-hidden="true">
							&times;
						</button>
						<h4 class="modal-title" id="myModalLabel">
							密保卡充值
						</h4>
					</div>
					<div class="modal-body" style="height: 160px">
							<div class="form-group">
								<label for="firstname" class="col-sm-2 control-label">
									卡号
								</label>
								<div class="col-sm-10">
									<input id="account" type="text" class="form-control"
										name="account" placeholder="请输入卡号">
								</div>
							</div>
							<div class="form-group">
								<label for="lastname"  class="col-sm-2 control-label">
									密码
								</label>
								<div class="col-sm-10" style="padding-top: 10px">
									<input id="password" type="text" class="form-control"
										placeholder="请输入卡密码" name="password" />
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-offset-2 col-sm-10" style="padding-top: 10px">
									<input type="button" onclick="addPortal()" class="btn btn-default" value="确认充值">
								</div>
							</div>
					</div>
				</div>
			</div>
		</div>


		<div class="row" style="background-color: #CCC; overflow: hidden;"
			onLoad="init()">
			<div class="col-md-12">
				<ul id="ul" class="nav nav-pills col-md-offset-1">

				</ul>
			</div>
		</div>


		<div id="nail1" class="container">
			<div class="row">
				<div class="col-md-6 ">
					<div class="out">
						<!--上面-->
						<ul class="img">
							<li>
								<img
									src="../images/myscreen/doudizhu_content1.jpg" width="500"
									height="300" alt="" />
							</li>
							<li>
								<img
									src="../images/myscreen/viceCity_content2.jpg" width="500"
									height="300" alt="" />
							</li>
							<li>
								<img
									src="../images/myscreen/tomAndJery_content1.jpg" width="500"
									height="300" alt="" />
							</li>
							<li>
								<img
									src="../images/myscreen/tribalConflict_content1.png"
									width="500" height="300" alt="" />
							</li>
						</ul>
						<!--下面-->
						<ul class="num">
						</ul>
						<div class="left btn">
							&lt;
						</div>
						<div class="right btn">
							&gt;
						</div>
					</div>
				</div>
				<div class="col-md-5 col-md-offset-1">

					<div class="row">
						&nbsp;
					</div>
					<table class="table">
						<thead>
							<tr>
								<th>
									新闻公告
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>
									<a href="#">劳动节优惠先知</a>
								</td>
							</tr>
							<tr>
								<td>
									<a href="#">情人节优惠放送</a>
								</td>
							</tr>
							<tr>
								<td>
									<a href="#">定时充值返现活动</a>
								</td>
							</tr>
							<tr>
								<td>
									<a href="#">体育竞技类游戏更新</a>
								</td>
							</tr>
							<tr>
								<td>
									<a href="#">棋牌类游戏折扣活动</a>
								</td>
							</tr>
							<tr>
								<td>
									<a href="#">直辖市优惠活动</a>
								</td>
							</tr>
							<tr>
								<td>
									&nbsp;
								</td>
								<td>
									<a href="#">更多</a>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			<div class="row">
				&nbsp;
			</div>
			<div class="row">
				&nbsp;
			</div>
			<div class="row">
				<div class="col-md-12 ">
					<div id="leftNav" class="col-md-2">
						<div id="jquery-accordion-menu" class="jquery-accordion-menu red">
							<div class="jquery-accordion-menu-header" id="form"></div>
							<ul id="demo-list">

							</ul>
							<div class="jquery-accordion-menu-footer">
								Footer
							</div>
						</div>
					</div>
					<div id="nail2" style="text-align: center"
						class="col-md-8 col-md-offset-1">
						<div class="row">
							<div class="col-md-6">
							</div>
						</div>

						<div id="gameShow" class="col-md-4 col-sm-6 ">

							<!-- <div class="col-md-4 col-sm-6 ">该游戏类型下没有提供可下载游戏</div> -->
						</div>
					</div>
				</div>

			</div>

			<div class="row">
				<div class="container">
					<hr />
					<div class="col-md-4 col-md-offset-5">
						&copy;2016-2017 portal.com 版权所有
					</div>
				</div>
			</div>
			<script type="text/javascript">
			$(function(){
				$("#123").click(function(){
					$("#myModal").modal('hide');
				});
			});
			
			(function($) {
			$.expr[":"].Contains = function(a, i, m) {
				return (a.textContent || a.innerText || "").toUpperCase().indexOf(m[3].toUpperCase()) >= 0;
			};
			function filterList(header, list) {
				//@header 头部元素
				//@list 无需列表
				//创建一个搜素表单
				var form = $("<form>").attr({
					"class":"filterform",
					action:"#"
				}), input = $("<input>").attr({
					"class":"filterinput",
					type:"text"
				});
				$(form).append(input).appendTo(header);
				$(input).change(function() {
					var filter = $(this).val();
					if (filter) {
						$matches = $(list).find("a:Contains(" + filter + ")").parent();
						$("li", list).not($matches).slideUp();
						$matches.slideDown();
					} else {
						$(list).find("li").slideDown();
					}
					return false;
				}).keyup(function() {
					$(this).change();
				});
			}
			$(function() {
				filterList($("#form"), $("#demo-list"));
			});
			})(jQuery);	
			jQuery(document).ready(function () {
				jQuery("#jquery-accordion-menu").jqueryAccordionMenu();
				
			});
			$(function(){	
				//顶部导航切换
				$("#demo-list li").click(function(){
					$("#demo-list li.active").removeClass("active");
					$(this).addClass("active");
				});	
			})	;
			$(".classname img").addClass("carousel-inner img-responsive img-rounded");
	</script>
	</body>

</html>
