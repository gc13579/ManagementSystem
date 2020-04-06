<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="com.web.pojo.User"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>密码重置</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="css/reset.css">
	<link rel="stylesheet" type="text/css" href="css/login.css">
	
	<script type="text/javascript" src="<%=basePath%>js/jquery-1.8.3.min.js"></script>
	
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
   <style type="text/css">
   body{
   	 background-color: honeydew;
   }
   </style>
   <script type="text/javascript">
   		
   		function checkOldPwd(){
   			var password = $("#password").val();
   			var message = $("#message");
   			if(password != "${user.t_user_pwd}"){
   				console.log("不相同");
   				message.text("错误");
   				message.attr("style","color:red");
   				return false;
   			}else{
   				console.log("相同");
   				message.text("正确");
   				message.attr("style","color:green");
   				return true;
   			}
   			
   		}
   		
   		function checkBeforeReset(){
   			var password = $("#password").val();
   			var newPwd = $("#newPwd").val();
   			console.log(newPwd);
   			var confPwd = $("#confPwd").val();
   			console.log(confPwd);
   			if(password == newPwd){
   				alert("新密码不能与原密码相同");
   				return false;
   			}
   			if(checkOldPwd){
	   			if(newPwd==" "||confPwd==" "){
	   				alert("输入值不能为空");
	   				return false;
	   			}else if(newPwd != confPwd){
	   				alert("新密码与确认密码不一致");
	   				return false;
	   			}else{
	   				alert("更新密码成功！请重新登录");
	   				return true;
	   			}
   			}else{
   				return false;
   			}
   		}
   </script>
  </head>
  <body>
  <center>
     <div id = "reset">
     	  <div id = "title">
     	  		密码重置
     	  </div>
     	  <form action="user/changePassword.do" method="post" target="_parent" onsubmit="return checkBeforeReset()">
	     	  <table id="resetTable">
	     	  		<tr>
	     	  			<td>原密码:&nbsp;</td>
	     	  			<td>
	     	  				<input type= "password" name = "password" id = "password" onblur="checkOldPwd()">
	     	  			</td>
	     	  			<td>
	     	  				<span id="message"></span>
	     	  			</td>
	     	  		</tr>
	     	  		
	     	  		<tr>
	     	  			<td>新密码&nbsp;</td>
	     	  			<td>
	     	  				<input type= "password" name = "newPwd" id = "newPwd"/>
	     	  			</td>
	     	  			<td>&nbsp;</td>
	     	  		</tr>
	      	  		<tr>
	     	  			<td>确认密码:&nbsp;</td>
	     	  			<td>
	     	  				<input type= "password" name = "confPwd" id = "confPwd"/>
	     	  			</td>
	     	  			<td>
	     	  				
	     	  			</td>
	     	  		</tr>
	     	  		
	     	  		<tr>
	     	  			<td>&nbsp;</td>
	     	  			<td colspan="2">
		     	  			<input type= "submit" value="修&nbsp;改" class="btn"/>
		     	  			
	     	  			</td>
	     	  		</tr>
	     	  </table>
	     </form>
     </div>
     </center>
     <c:if test="${isError}">
		<script type="text/javascript">
			alert("${errorMessage}");
		</script>
	</c:if>
	
  </body>
</html>
