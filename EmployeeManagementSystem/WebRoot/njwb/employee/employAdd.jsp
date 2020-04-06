<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'employAdd.jsp' starting page</title>
    
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
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
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
		function addEmployee(){
		var No = $("#No").val();
		var name = $("#name").val();
		var time = $("#time").val();
		var errorMessage = "";
			if(No == ""){
				errorMessage+="员工编号不能为空"+"\r\n";
				
			}

			if(!/^[B]\d{4}$/.test(No)){
				errorMessage+="员工编号格式错误：B+四位数字"+"\r\n";
				
			}
			if(name == ""){
				errorMessage+="员工姓名不能为空"+"\r\n";
			}
			
			if(/^\w{1,4}$/.test(name)){
				errorMessage+="员工姓名格式错误：4位以下中文"+"\r\n";
			}
			
			if(time == "" || time == undefined){
				errorMessage+="入职时间不能为空";
			}
			if(!/^\d{4}[-]\d{2}[-]\d{2}$/.test(time)){
				errorMessage+="入职时间格式错误，例：2012-11-11";
			}
			if(errorMessage.length==0){
				return true;
			}else{
				alert(errorMessage);
				return false;
			}
			
		}
	</script>
	
	<script type="text/javascript">
		function search(){
			$.ajax({
				type:"POST",
				url:"<%=basePath%>dept/getDept.do",
				success:function(msg){
				var pager = $.parseJSON(msg);	
				currentPage =pager;
				var datas = pager;
				var resultTable = $("#d1");
				var sel2 = $("#sel2");
				for(var i=0;i<datas.length;i++){
					var noTd =$("<option value="+datas[i].deptName+" >"+datas[i].deptName+"</option>");
					sel2.append(noTd);
  					resultTable.append(sel2);
				}
			}
		});
	}
		$(document).ready(function(){
  			search();
  		});
	</script>
  </head>
  
  <body>
	<form action="employee/addEmployee.do"   method="post" onsubmit="return addEmployee()">   	
   		<table id = "deptEditTable">
   		<tr>
   			<td>
   			员工编号:
   			</td>
   			<td>
   				<input id="No" name="No" type ="text"/>
   			</td>
   		</tr>
   		<tr>
   			<td>
   			员工姓名:
   			</td>
   			<td>
   				<input id="name" name="name" type ="text"/>
   			</td>
   		</tr>  

   		<tr>
   			<td>
   			性别:
   			</td>
   			<td>
   				<select id="sel1" name="sel1">
   					<option value="男">男</option>
   					<option value="女">女</option>
   				</select>
   			</td>
   		</tr>  
   		<tr>
   			<td>
   			所在部门:
   			</td>
   			<td id = "d1" name = "d1">
   				<select id="sel2" name="sel2">
   				</select>
   			</td>
   		</tr>  

   		<tr>
   			<td>
   			入职时间:
   			</td>
   			<td>
   				<input id="time" name="time" type = "text" onclick="laydate()"/>
   			</td>
   		</tr>  
   		
   		<tr>
   			<td colspan="2">
   				<input type= "submit" value="添加" class="btn"/>
   				<input type ="reset" value="重置" class="btn"/>
				<a href="<%=basePath%>njwb/employee/employee.jsp" target="contentPage"><input type="button" value="返回"></a>
   			</td>
   		</tr>  	
   		</table>
   	</form>
  </body>
</html>
