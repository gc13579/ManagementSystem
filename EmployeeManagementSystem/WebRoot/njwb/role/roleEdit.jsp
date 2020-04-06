<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>修改角色</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="<%=basePath%>js/jquery-1.8.3.min.js"></script>
	<style type="text/css">
		body,div,table,tr,td{
			margin: 0px;
			padding: 0px;
		}
		#deptEditTable{
			font-size: 15px;
			border-collapse: collapse;
			width: 350px;
			margin: 20px auto;
		}
		#deptEditTable td{
			height: 40px;
		}
	</style>
	<script type="text/javascript">
		$(document).ready(function(){
			refresh();
		});
		
		
		//给修改页面 传 角色id,显示所有角色
		function refresh(){
			var t_role_id = $("#hiddenInput1").val();
			$.ajax({
				type:"POST",
				url:"<%=basePath%>role/getRole.do",
				data:{
					"roleId":t_role_id,
				},
				success:function(msg){
					var datas = $.parseJSON(msg);
					var roleName= $("#roleName");
					$("#roleNo").val(t_role_id);
					for(var i=0;i<datas.length;i++){
						var noTd =$("<option value="+datas[i].t_role_name+" >"+datas[i].t_role_name+"</option>");
						roleName.append(noTd);
					}
				}
			});
		}
	</script>
	
  </head>
  
  <body>
    <%
  		String t_role_id = request.getParameter("t_role_id");
  		request.setAttribute("t_role_id",t_role_id);
  	%>
  	<form action="<%=basePath %>role/edit.do" method="post">
   	<table id = "deptEditTable">
   		<tr>
   			<td>
   			角色ID:
   			</td>
   			<td>
   				<input type = "text" name="roleNo" id="roleNo" onclick="blur()"/>
   			</td>
   		</tr>
   		<tr>
   			<td>
   			角色名称:
   			</td>
   			<td>
   				<select id="roleName" name="roleName">
   				</select>
   			</td>
   		</tr>    
   		<tr>
   			<td colspan="2">
   				<input type = "submit" value="修改" />
				<a style="text-decoration: none" href="<%=basePath %>njwb/role/role.jsp" target="contentPage"><input type="button" value="返回"></a>
   			</td>
   		</tr>  	
   	</table>
   	</form>
   		<input type="hidden" value="${t_role_id}" id="hiddenInput1">
  </body>
</html>
