<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>游币管理员后台登录页面</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta name="description" content="">
		<meta name="author" content="">
		<!-- Le styles -->
		<link rel="stylesheet" href="<%=basePath%>css/style.css">
		<link rel="stylesheet" href="<%=basePath%>css/bootstrap.css">
		<script type="text/javascript" src="<%=basePath%>js/jquery.js">
</script>
		<!-- MAIN EFFECT -->
		<script type="text/javascript" src="<%=basePath%>js/bootstrap.js">
</script>
		<link rel="shortcut icon" href="<%=basePath%>assets/ico/minus.png">
		<script src="<%=basePath%>js/jquery-1.8.3.min.js">
</script>
		<style type="text/css">
#zhong {
	margin-top: 200px;
}

#yzmsj {
	display: none;
}

#1 {
	color: green;
}
</style>
		<script type="text/javascript">
function createCode() {
	code = "";
	var strs = new Array(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "a", "b", "c",
			"d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z");
	for ( var i = 0; i < 4; i++) {
		code += strs[Math.floor(Math.random() * 62)];
	}
	var getCode = document.getElementById("getCode");
	getCode.innerHTML = code;
}

function checkBeforeSubmit() {
	var username = $("#username").val();
	var password = $("#password").val();
	var inputCode = $("#inputCode").val();
	var errorInfo = "";
	if (username.trim() == "" || password.trim() == "") {
		errorInfo += "用户名或密码不能为空\r\n";
	}
	if (/^\w*[<>\\\/\*\^]+\w*$/.test(username)) {
		errorInfo += "用户名不能包含非法字符\r\n";
	}
	if (password.length < 6 && password.length > 16) {
		errorInfo += "密码长度在6~16位之间\r\n";
	}
	if ($("#getCode").html().toLowerCase() != inputCode.toLowerCase()) {
		errorInfo += "验证码错误\r\n";
		createCode();
		$("#inputCode").val("");
	}
	if (errorInfo.length > 0) {
		alert(errorInfo);
		return false;
	}
	return true;
}
</script>
	</head>
	<body>
		<div id="preloader"></div>
		<div class="container">
			<div id="zhong">
				<div class="" id="login-wrapper">
					<div class="row">
						<div class="col-md-4 col-md-offset-4">
							<div id="logo-login">
								<h1>
									游币后台登录页面
								</h1>
							</div>
						</div>
					</div>
					

					
					<div class="row">
						<div class="col-md-4 col-md-offset-4">
							<div class="account-box">
								<form action="<%=basePath%>manager/managerLogin.do"
									method="post" onsubmit="return checkBeforeSubmit()">
									<div class="row">
										<div class="col-sm-9">
											<label for="inputUsername">
												用户名
											</label>
											<div class="row">
												<div class="col-xs-8 col-sm-12">
													<input id="username" name="username" type="text"
														required="required" class="form-control">
												</div>
												<div class="col-xs-3 col-sm-5">
													<span id="1"></span>
												</div>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-sm-9">
											<label for="pwd">
												密码
											</label>
											<input id="password" name="password" type="password"
												required="required" class="form-control">
										</div>
									</div>
									<div class="row">
										<div class="col-sm-9">
											<label for="inputverificationcode">
												验证码
											</label>
											<div class="row">
												<div class="col-xs-8 col-sm-8">
													<input id="inputCode" type="text" required="required"
														class="form-control">
												</div>
												<div id="getCode" class="col-xs-2 col-sm-2">
													<input class="btn btn-warning" type="button" id="btn"
														value="获取验证码" onclick="return createCode()" />
												</div>
											</div>
										</div>
									</div>
									<div></div>
									<div class="row">
										&nbsp;
									</div>
									<button id="start" class="btn btn btn-primary " type="submit">
										登 录
									</button>
								</form>
								<a class="forgotLnk" href="index.jsp"></a>
								<div class="row-block">
									<div class="row"></div>
								</div>
							</div>
						</div>
					</div>
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
