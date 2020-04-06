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
    
    <title>修改员工</title>
    
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
  </head>
 <script type="text/javascript">
  	$(document).ready(function(){
			refresh();
			//refresh2();
			refresh3();
			refresh4();
	});
  	//显示帐号 员工姓名
    function refresh(){
			var t_user_id = $("#hiddenInput1").val();
			$.ajax({
				type:"POST",
				url:"<%=basePath%>user/getUserVoById.do",
				data:{
					"t_user_id":t_user_id,
				},
				success:function(msg){
					var data = $.parseJSON(msg);
						$("#account").val(data.t_user_account);
						$("#employeeName").val(data.t_user_name);
				}
			});
		}
<%--  	//获取自己的角色名(用蚂蚁传会乱码)--%>
<%--  	function refresh2(){--%>
<%--			var t_role_id = $("#hiddenInput2").val();--%>
<%--			$.ajax({--%>
<%--				type:"POST",--%>
<%--				url:"<%=basePath%>role/getRoleById.do",--%>
<%--				data:{--%>
<%--					"roleId":t_role_id,--%>
<%--				},--%>
<%--				success:function(msg){--%>
<%--					var data = $.parseJSON(msg);--%>
<%--					var sel2=$("#sel2");--%>
<%--					sel2.append($("<option value="+data.t_role_id+" >"+data.t_role_name+"</option>"));--%>
<%--						var noTd =$("<option value="+data.t_role_id+" >"+data.t_role_name+"</option>");--%>
<%--						sel2.append(noTd);--%>
<%--				}--%>
<%--			});--%>
<%--	}--%>
  	//获取所有的角色名
  	function refresh3(){
			var t_role_id = $("#hiddenInput2").val();
			$.ajax({
				type:"POST",
				url:"<%=basePath%>role/getRole.do",
				data:{
					"roleId":t_role_id,
				},
				success:function(msg){
					var datas = $.parseJSON(msg);
					var sel2=$("#sel2");
					for(var i=0;i<datas.length;i++){
						var noTd =$("<option value="+datas[i].t_role_id+" >"+datas[i].t_role_name+"</option>");
						sel2.append(noTd);
					}
				}
			});
	}
  	//获取员工编码
  	function refresh4(){
		var t_user_id = $("#hiddenInput1").val()+" ";
		$.ajax({
			type:"POST",
			url:"<%=basePath%>user/getUserVoById.do",
			data:{
				"t_user_id":t_user_id,
			},
			success:function(msg){
				var datas = $.parseJSON(msg);
				var employeeId=$("#employeeId");
				employeeId.val(datas.empNo);
			}
		});
	}
  	function resetMethod(){
  		refresh4();
  		$("#sel2").find("option").each(function(){
  			var $this=$(this);
  			$(this).remove();
  		});
  		
  		$("#sel1").find("option").each(function(){
  			var $this=$(this);
  			$(this).remove();
  		});
  		$("#sel1").append("<option value="+'正常'+" >"+'正常'+"</option>");
  		$("#sel1").append("<option value="+'注销'+" >"+'注销'+"</option>");
  		refresh3();
  	}
  </script>
  <body>
    <%
  		String t_user_id = request.getParameter("t_user_id");
  		String t_role_id = request.getParameter("t_user_id");
  		request.setAttribute("t_user_id",t_user_id);
  		request.setAttribute("t_role_id",t_role_id);
  	%>
  	<form action="<%=basePath %>user/edit.do" method="post">
   	<table id = "deptEditTable">
   		<tr>
   			<td>
   			帐号:
   			</td>
   			<td>
   				<input type = "text" name="account" id="account" onclick="blur()"/>
   			</td>
   		</tr>
   		<tr>
   			<td>
   			员工编码:
   			</td>
   			<td>
   				<input type = "text" name="employeeId" id="employeeId"/>
   				
   			</td>
   		</tr>    
   		<tr>
   			<td>
   			员工姓名:
   			</td>
   			<td>
   				<input type = "text" name="employeeName" id="employeeName"/>
   				
   			</td>
   		</tr>    

   		<tr>
   			<td>
   			状态:
   			</td>
   			<td id="d1">
   				<input type="hidden" name="state" id="state">
   				<select id="sel1" name="sel1">
   					<option value="正常">正常</option>
   					<option value="注销">注销</option>
   				</select>
   			</td>
   		</tr>  
   		<tr>
   			<td>
   			角色:
   			</td>
   			<td id="d1">
   				<input type="hidden" name="role" id="role">
   				<select id="sel2" name="sel2">
   				</select>
   			</td>
   		</tr>  
   		
   		<tr>
   			<td colspan="2">
   				<input type = "submit" value="修改" />
   				<input type = button value="重置" onclick="resetMethod()"/>
				<a style="text-decoration: none" href="<%=basePath %>njwb/user/user.jsp" target="contentPage"><input type="button" value="返回"></a>
   			</td>
   		</tr>  	
   	</table>
   	</form>
   	<c:if test='${isError}'>
		<script type="text/javascript">
			alert("${errorMessage}");
		</script>  
	</c:if>
	<input type="hidden" value="${t_user_id}" id="hiddenInput1">
	<input type="hidden" value="${t_role_id}" id="hiddenInput2">
  </body>
   
</html>
