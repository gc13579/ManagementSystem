<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.web.factory.ObjectFactory"%>
<%@page import="com.web.service.MenuService"%>
<%@page import="com.web.service.impl.MenuServiceImpl"%>
<%@page import="com.web.pojo.User"%>
<%@page import="com.web.pojo.Menu"%>
<%@page import="java.sql.Date"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%--<%--%>
<%--if(session.getAttribute("user") == null){--%>
<%--	request.setAttribute("isError",true);--%>
<%--	request.setAttribute("errorMessage","您尚未登录，请登录");--%>
<%--	request.getRequestDispatcher("login.jsp").forward(request,response);--%>
<%--}--%>
<%--%>--%>
<html>
		<head>
				<base href="<%=basePath%>">
				<title>My JSP 'main.jsp' starting page</title>
				<meta http-equiv="pragma" content="no-cache">
				<meta http-equiv="cache-control" content="no-cache">
				<meta http-equiv="expires" content="0">
				<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
				<meta http-equiv="description" content="This is my page">
				<link rel="stylesheet" type="text/css" href="<%=basePath%>css/reset.css">
				<link rel="stylesheet" type="text/css" href="<%=basePath%>css/main.css">
				<script type="text/javascript" src="<%=basePath%>js/jquery-1.8.3.min.js">
</script>
				<style type="text/css">
.hide {
	display: none;
}
</style>
				<script type="text/javascript">
$(document).ready(function() {
	f();
	search();
});
function f() {
	$("li[class='menu'] span").each(function() {
		$(this).click(function() {
			//this代表的是span
				$(this).siblings(".hide").slideToggle();
			});
	});
}
function ccc() {
	$("#666").siblings(".hide").slideToggle();
}
function ccc1() {
	$("#777").siblings(".hide").slideToggle();
}
function ccc2() {
	$("#888").siblings(".hide").slideToggle();
}
function search() {
	$.ajax( {
		type : "POST",
		url : "<%=basePath%>user/getAllSubMenu.do",
		success : function(msg) {
			var menuList = $.parseJSON(msg);
			console.log("menuList:" + menuList);
			var mainUl = $("#mainUl");
			for ( var i = 0; i < menuList.length; i++) {
				if (menuList[i].t_parent_id == 0 && menuList[i].t_menu_id == 1) {
					var rootMenu = $("<li class='menu'></li>");
					var rootUl = $('<ul id=' + menuList[i].t_menu_id + ' class="hide"></ul>');
					var span = $('<span id=666 onclick="ccc()">' + menuList[i].t_menu_name + '</span>');
					rootMenu.append(span);
					mainUl.append(rootMenu);
					rootMenu.append(rootUl);
				}
				if (menuList[i].t_parent_id == 0 && menuList[i].t_menu_id == 2) {
					var rootMenu = $("<li class='menu'></li>");
					var rootUl = $('<ul id=' + menuList[i].t_menu_id + ' class="hide"></ul>');
					var span = $('<span id=777 onclick="ccc1()">' + menuList[i].t_menu_name + '</span>');
					rootMenu.append(span);
					mainUl.append(rootMenu);
					rootMenu.append(rootUl);
				}
				if (menuList[i].t_parent_id == 0 && menuList[i].t_menu_id == 3) {
					var rootMenu = $("<li class='menu'></li>");
					var rootUl = $('<ul id=' + menuList[i].t_menu_id + ' class="hide"></ul>');
					var span = $('<span id=888 onclick="ccc2()">' + menuList[i].t_menu_name + '</span>');
					rootMenu.append(span);
					mainUl.append(rootMenu);
					rootMenu.append(rootUl);
				}
			}
			for ( var i = 0; i < menuList.length; i++) {
				if (menuList[i].t_parent_id != 0 && menuList[i].t_menu_id !=12) {
					var fatherUl = $("#" + menuList[i].t_parent_id);
					var li = $("<li class='menu-sub'><a href='<%=basePath%>"+menuList[i].t_href_url+"'target='contentPage'>" + menuList[i].t_menu_name + "</a></li>");
					fatherUl.append(li);
				}
				if(menuList[i].t_menu_id ==12){
					var fatherUl = $("#" + menuList[i].t_parent_id);
					var li = $("<li class='menu-sub'><a href='<%=basePath%>"+menuList[i].t_href_url+"'>" + menuList[i].t_menu_name + "</a></li>");
					fatherUl.append(li);
				}
			}
		}
	});
}
</script>
		</head>
		<body>
				<div id="mainDiv">
						<div id="header">
								<div id="logoDiv" class="lft">
										南京网博教育集团
								</div>
								<div id="userDiv" class="rft">
										${user.t_user_name}
								</div>
						</div>
						<div id="welcomeDiv">
								欢迎使用网博管理系统
						</div>
						<div id="contentDiv">
								<div id="content-left" class="lft">
										<ul id="mainUl">
										</ul>
								</div>
								<div id="content-right" class="rft">
										<iframe src="" name="contentPage" scrolling="yes" frameborder="0" width="788px" height="470px">
										</iframe>
								</div>
						</div>
						<div id="footer">
								<span>&copy;版权归属南京网博江北总部</span>
						</div>
				</div>
		</body>
</html>