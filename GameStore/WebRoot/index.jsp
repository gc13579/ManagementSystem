<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
    <title>游币后台管理系统</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
     <script src="<%=basePath%>js/jquery-2.2.3.min.js"></script>
    <link href="<%=basePath%>css/ie10-viewport-bug-workaround.css" rel="stylesheet">
    <link href="<%=basePath%>css/dashboard.css" rel="stylesheet">
    <script src="<%=basePath%>js/ie-emulation-modes-warning.js"></script>
    <link href="<%=basePath%>css/bootstrap.min.css" rel="stylesheet">
    <script src="<%=basePath%>js/bootstrap.min.js"></script>
   
  </head>
   <frameset rows="5%,*">
		<frame src="<%=basePath%>top.jsp" noresize="no">
		</frame>
		<frameset cols="10%,*">
			<frame src="<%=basePath%>left.jsp" noresize="no"></frame>
			<frame src="<%=basePath%>user/user.jsp" name="right"></frame>
		</frameset>	
   </frameset>
   
	<body>
		
	</body>
</html>