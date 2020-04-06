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
    
    <title>My JSP 'permissionsAdd.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	 <link rel="stylesheet" type="text/css" href="<%=basePath%>css/reset.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/main.css">
	<script type="text/javascript" src="<%=basePath%>js/laydate/laydate.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/layer/layer.js"></script>
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
		function search1(){
			$.ajax({
				type:"POST",
				url:"<%=basePath%>menu/getMenu.do",
				success:function(msg){
				var pager = $.parseJSON(msg);	
				var datas = pager;
				var resultTable = $("#d2");
				var sel2 = $("#sel2");
				sel2.append($("<option>"+"请选择"+"</option>"));
				for(var i=0;i<datas.length;i++){
					var noTd =$("<option value="+datas[i].t_menu_id+" >"+datas[i].t_menu_name+"</option>");
					sel2.append(noTd);
  					resultTable.append(sel2);
				}
			}
		});
	}
		
		function search2(){
			$.ajax({
				type:"POST",
				url:"<%=basePath%>role/getRole.do",
				success:function(msg){
				var pager = $.parseJSON(msg);	
				var datas = pager;
				var resultTable = $("#d1");
				var sel1 = $("#sel1");
				sel1.append($("<option>"+"请选择"+"</option>"));
				for(var i=0;i<datas.length;i++){
					var noTd =$("<option value="+datas[i].t_role_id+" >"+datas[i].t_role_name+"</option>");
					sel1.append(noTd);
  					resultTable.append(sel1);
				}
			}
		});
	}
		$(document).ready(function(){
  			search1();
  			search2();
  		});
	function check(){
		if(($("#sel1").val()=="请选择")||($("#sel2").val()=="请选择")){
			alert("角色或菜单还未选择");
			return false;
		}
		return true;
	}
	</script>
  </head>
  
  <body>
    <form action="permissions/addPermissions.do"   method="post" onsubmit="return check()">   	
   		<table id = "deptEditTable">
   		<tr>
   			<td>
   			角色:
   			</td>
   			<td id = "d1" name = "d1">
   				<select id="sel1" name="sel1">
   				</select>
   			</td>
   		</tr>  
   		<tr>
   			<td>
   			菜单:
   			</td>
   			<td id = "d2" name = "d2">
   				<select id="sel2" name="sel2">
   				</select>
   			</td>
   		</tr>  


   		<tr>
   			<td colspan="2">
   				<input type= "submit" value="添加" class="btn"/>
   				<input type ="reset" value="重置" class="btn"/>
				<a href="<%=basePath%>njwb/permissions/permissions.jsp" target="contentPage"><input type="button" value="返回"></a>
   			</td>
   		</tr>  	
   		</table>
   	</form>
   	<c:if test='${isError}'>
		<script type="text/javascript">
			alert("${errorMessage}");
		</script>  
	</c:if>
  </body>
</html>
