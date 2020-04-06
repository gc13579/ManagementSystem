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
    
    <title>添加员工</title>
    
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
		//获取所有角色
		function refresh(){
			$.ajax({
				type:"POST",
				url:"<%=basePath%>role/getRole.do",
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
		function check(){
			var wrong="";
			if($("#account").val().trim()==""){
				wrong+=("帐号不能为空"+"\r\n");
			}
			if($("#employeeId").val().trim()==""){
				wrong+=("员工编码不能为空"+"\r\n");
			}
			if($("#employeeName").val().trim()==""){
				wrong+=("员工姓名不能为空"+"\r\n");
			}
			if(wrong!=""){
				alert(wrong);
				return false;
			}else{
				return true;
			}
		}
	</script>
  </head>
  
  <body>
  	<form action="<%=basePath %>user/add.do" method="post" onsubmit="return check()">
   	<table id = "deptEditTable">
   		<tr>
   			<td>
   			帐号:
   			</td>
   			<td>
   				<input type = "text" name="account" id="account" />
   			</td>
   		</tr>
   		<tr>
   			<td>
   			员工编码:
   			</td>
   			<td>
   				<input type = "text" name="employeeId" id="employeeId" on/>
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
   				<input type = "submit" value="保存" />
   				<input type = reset value="重置"/>
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
  </body>
</html>
