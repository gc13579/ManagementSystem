<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.web.pojo.User"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%--<%=session.getAttribute("user")%>--%>
<%--<%request.setAttribute("user",session.getAttribute("user")); %>--%>
<%--<%--%>
<%--if(session.getAttribute("user") == null){--%>
<%--	request.setAttribute("isError",true);--%>
<%--	request.setAttribute("errorMessage","您尚未登录，请登录");--%>
<%--	request.getRequestDispatcher("login.jsp").forward(request,response);--%>
<%--}--%>
<%--%>--%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>用户登录</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/reset.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/login.css">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style type="text/css">
	#td{
		width:45px;
		height:20px;
 		border: black solid 1px;
 		background-color: pink;
   	}	
    body{
   	 background-color: #0070A2;
   	}
   
   </style>
   <script type="text/javascript" src="<%=basePath%>js/jquery-1.8.3.min.js"></script>
   <script type="text/javascript">
   $(document).ready(function() {
		createCode();
	});
	   function createCode(){
		   code="";
		   var strs = new Array( 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X",
				"Y", "Z", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z");
	  	   for(var i=0;i< 4;i++){
	  		   code+=strs[Math.floor(Math.random()*52)];
	  	   }
	  	   var checkcode=document.getElementById("checkcode");
	  	   checkcode.innerHTML=code;
	   }
   		function checkBeforeSubmit(){
   			var username=$("#username").val();
   			var password=$("#password").val();
   			var code=$("#code").val();
   			var errorInfo="";
   			if(username==""||password==""){
   				errorInfo+="用户名或密码不能为空\r\n";
   			}
   			//\w : 0-9 a-z A-Z _
   			if(/^\w*[<>\\\/\*\^]+\w*$/.test(username)){
				errorInfo+="用户名不能包含非法字符\r\n";
			}
   			if($("#checkcode").html().toLowerCase()!=code.toLowerCase()){
   				errorInfo+="验证码错误\r\n";
   				createCode();
   				$("#code").val("");
   			}
   			if(errorInfo.length>0){
   				alert(errorInfo);
   				return false;
   			}
			return true;
   		}
   </script>
  </head>
  
  <body>
     <div id = "login">
     	  <div id = "title">
     	  		NJWB管理系统
     	  </div>
     	  <form action="user/login.do" method="post" onsubmit="return checkBeforeSubmit()">
	     	  <table id="loginTable">
	     	  		<tr>
	     	  			<td>用户名:&nbsp;</td>
	     	  			<td>
	     	  				<input type= "text" name = "username" id = "username"/>
	     	  			</td>
	     	  			<td>&nbsp;</td>
	     	  		</tr>
	     	  		<tr>
	     	  			<td>密&nbsp;&nbsp;&nbsp;码:&nbsp;</td>
	     	  			<td>
	     	  				<input type= "password" name = "password" id = "password"/>
	     	  			</td>
	     	  			<td>&nbsp;</td>
	     	  		</tr>
	      	  		<tr>
	     	  			<td>验证码:&nbsp;</td>
	     	  			<td>
	     	  				<input type= "text" name = "code" id = "code"/>
	     	  			</td>
	     	  			<td id="td" onclick="createCode()"><span id="checkcode"></span></td>
	     	  		</tr>
	     	  		
	     	  		<tr>
	     	  			<td>&nbsp;</td>
	     	  			<td colspan="2">
	     	  				<input type= "submit" value="登&nbsp;录" class="btn"/>
	     	  			</td>
	     	  		</tr>
	     	  </table>
	     </form>
     </div>
     	<c:if test='${isError}'>
			<script type="text/javascript">
				alert("${errorMessage}");
			</script>  
		</c:if>
  </body>
</html>
