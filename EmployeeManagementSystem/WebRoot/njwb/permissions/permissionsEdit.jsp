<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>修改菜单</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
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
			search();
		});
		//给修改页面 传 角色和菜单的id
		function refresh(){
			var t_role_id = $("#hiddenInput1").val();
			var t_menu_id = $("#hiddenInput2").val();
			var t_current_pageNo = $("#hiddenInput4").val();
			console.log("t_current_pageNo:"+t_current_pageNo);
			$.ajax({
				type:"POST",
				url:"<%=basePath%>permissions/getPermissionsByRidAndMid.do",
				data:{
					"roleId":t_role_id,
					"menuId":t_menu_id,
					"pageNo":t_current_pageNo,
				},
				success:function(msg){
					var datas = $.parseJSON(msg);
						$("#roleNo").val(datas[0].t_role_id);
						$("#roleName").val(datas[0].t_role_name);
						$("#oldMenuId").val(datas[0].t_menu_id);
				}
			});
		}
		//查所有存在的菜单
		function search(){
			var t_menu_name = $("#hiddenInput3").val();
			$.ajax({
				type:"POST",
				url:"<%=basePath%>menu/getAllMenuNames.do",
				data:{
					"t_menu_name":t_menu_name,
				},
				success:function(msg){
					var datas = $.parseJSON(msg);
					var sel1=$("#sel1");
					for(var i=0;i<datas.length;i++){
						var noTd =$("<option value="+datas[i].t_menu_id+" >"+datas[i].t_menu_name+"</option>");
						sel1.append(noTd);
					}
				}
			});
		}
		function resetMethod(){
			$("#sel1").find("option").each(function(){
  				var $this=$(this);
  				$(this).remove();
  			});
			search();
		}
	</script>
  </head>
  <body>
    <%
  		String t_role_id = request.getParameter("t_role_id");
  		String t_menu_id = request.getParameter("t_menu_id");
  		String t_menu_name = request.getParameter("t_menu_name");
  		String t_current_pageNo=request.getParameter("t_current_pageNo");
  		request.setAttribute("t_role_id",t_role_id);
  		request.setAttribute("t_menu_id",t_menu_id);
  		request.setAttribute("t_menu_name",t_menu_name);
  		request.setAttribute("t_current_pageNo",t_current_pageNo);
  	%>
  	<form action="<%=basePath %>permissions/edit.do" method="post">
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
   				<input type = "text" name="roleName" id="roleName" onclick="blur()"/>
   			</td>
   		</tr>    

   		<tr>
   			<td>
   			菜单名称:
   			</td>
   			<td id="d1" name="d1">
   				<input type="hidden" name="oldMenuId" id="oldMenuId">
   				<select id="sel1" name="sel1">
   				</select>
   			</td>
   		</tr>  
   		
   		<tr>
   			<td colspan="2">
   				<input type = "submit" value="修改" />
   				<input type = "button" value="重置" onclick="resetMethod()"/>
				<a style="text-decoration: none" href="<%=basePath %>njwb/permissions/permissions.jsp" target="contentPage"><input type="button" value="返回"></a>
   			</td>
   		</tr>  	
   	</table>
   	</form>
	<input type="hidden" value="${t_role_id}" id="hiddenInput1">
	<input type="hidden" value="${t_menu_id}" id="hiddenInput2" name="hiddenInput2">
	<input type="hidden" value="${t_menu_name}" id="hiddenInput3">
	<input type="hidden" value="${t_current_pageNo}" id="hiddenInput4">
	<c:if test='${isError}'>
		<script type="text/javascript">
			alert("${errorMessage}");
		</script>  
	</c:if>
  </body>
</html>
