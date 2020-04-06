<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<meta name="description" content="登录界面" />
<title>登录界面</title>
<link rel="stylesheet" href="../css/jq22.css">
<link rel="stylesheet" href="../css/bootstrap.min.css">
<link rel="stylesheet" href="../css/bootstrap.css">
<style type="text/css">
	#msg2{
		color:red;
	}
</style>
<!-- 控制粒子 -->
<script type="text/javascript">
	function checkBeforeSubmit(){
		var username=$("#name").val();
		var password=$("#userPwd").val();
		var errorInfo="";
		if(username.trim()==""||password.trim()==""){
   			errorInfo+="用户名或密码不能为空\r\n";
   		}
		if(/^\w*[<>\\\/\*\^]+\w*$/.test(username)){
			errorInfo+="用户名不能包含非法字符\r\n";
		}
		if (password.length < 6 && password.length > 16) {
			errorInfo += "密码长度在6~16位之间\r\n";
		}
		if(errorInfo.length>0){
			alert(errorInfo);
			return false;
   		}
   		//$.ajax({
   		//	type:"POST",
   		//	url:"<%=basePath%>",
   		
   		//})
		return true;
	}
</script>

</head>
<body>
<!-- begin -->

<div id="login">
    <div class="wrapper">
        <div class="login">
            <form action="<%=basePath%>user/userLogin.do" method="post" class="container offset1 loginform" onsubmit="return checkBeforeSubmit()">
                <div id="owl-login">
                    <div class="hand"></div>
                    <div class="hand hand-r"></div>
                    <div class="arms">
                        <div class="arm"></div>
                        <div class="arm arm-r"></div>
                    </div>
                </div>
                
                <div class="pad">
                    <div class="control-group">
                        <div class="controls text-center">
                            <h4>游币客户登录</h4>
                        </div>
                    </div>
                    <div class="control-group">
                        &nbsp;
                    </div>
                    <div class="control-group">
                        <div class="controls">
                            <label for="name" id="nameMsg" class="glyphicon glyphicon-user"></label>
                            <input id="name" type="text" name="name" placeholder="用户名" required="required"  tabindex="1" autofocus="autofocus" class="form-control input-medium" value="${name}">
                        </div>
                    </div>
                    <div class="control-group">
                        <div class="controls">
                            <label for="userPwd" id="userPwdMsg" class="glyphicon glyphicon-th"></label>
                            <input id="userPwd" type="password" name="userPwd" required="required" placeholder="密码(6-16个字符)" tabindex="2" class="form-control input-medium" >
                        </div>
                    </div>
                </div>
                <div class="form-actions"><a href="#" tabindex="5" class="btn pull-left btn-link text-muted"  style="color:red">忘记密码?</a><a href="register.jsp" tabindex="4" class="btn btn-link text-muted"  style="color:red">新用户注册</a>
                    <button type="commit" tabindex="3"  class="btn btn-primary" >登录</button>
                </div>
            </form>
        </div>
    </div>
</div>
<script type="text/javascript" src="../js/canvas-particle.js"></script>
	<script src="../js/jquery.min.js"></script>
<%--    <%--%>
<%--    	request.setAttribute("name",request.getAttribute("name"));--%>
<%--    %>--%>
    <script type="text/javascript">
		window.onload = function(){
			var config = {
				vx: 4,
				vy:  4,
				height: 2,
				width: 2,
				count: 300,
				color: "121, 162, 185",
				stroke: "100,200,180",
				dist: 6000,
				e_dist: 20000,
				max_conn: 10
			};
			CanvasParticle(config);
		};
		
	</script>
    <script>
    $(function() {
        $('#login #userPwd').focus(function() {
            $('#owl-login').addClass('password');
        }).blur(function() {
            $('#owl-login').removeClass('password');
        });
    });
    </script>
     <c:if test="${isError }">
     	<script type="text/javascript">
     		alert("${errorMessage}");
     	</script>
     </c:if>
     <c:if test="${isSuccess }">
     	<script type="text/javascript">
     		alert("${success}");
     	</script>
     </c:if>
</body>
</html>
