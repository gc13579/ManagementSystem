<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
      <title>员工编辑</title>
    
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
			width: 500px;
			margin: 20px auto;
			
			
		}
		
		#deptEditTable td{
			height: 40px;
		}
	
	</style>
	
	<script type="text/javascript">
	$(document).ready(function(){
			search();
			refresh();
		});
		function refresh(){
			var empId = $("#hiddenInput").val();
			$.ajax({
				type:"POST",
				url:"<%=basePath%>employee/getemployeeById.do",
				data:{"empId":empId},
				success:function(msg){
				var employee = $.parseJSON(msg);	
		        var employeeNo = $("#employeeNo");
		        var employeeName = $("#employeeName");
		        var entryTime = $("#entryTime");
		        var employeeSex = $("#employeeSex");
		        employeeNo.val(employee.empNo);
				employeeName.val(employee.empName);
			
				var time = employee.entryTime;
				entryTime.val((time.year+1900)+"-"+(time.month+1)+"-"+(time.date));
                if(employee.sex=="男"){
                	var c =$("<option value="+employee.sex+" >"+employee.sex+"</option>");
					var e =$("<option value='女'>女</option>");
					employeeSex.append(c).append(e);
                }else if(employee.sex=="女"){
                	var c =$("<option value="+employee.sex+" >"+employee.sex+"</option>");
					var e =$("<option value='男'>男</option>");
					employeeSex.append(c).append(e);
                }
				}
			});
		}
		function search(){
			$.ajax({
				type:"POST",
				url:"<%=basePath%>dept/getDept.do",
				success:function(msg){
				var pager = $.parseJSON(msg);	
				currentPage =pager;
				var datas = pager;
				var sel2 = $("#dept");
				for(var i=0;i<datas.length;i++){
					var noTd =$("<option value="+datas[i].deptName+" >"+datas[i].deptName+"</option>");
					sel2.append(noTd);
				}
			}
		});
	}
	function editEmployee(){
		var No = $("#employeeNo").val();
		var name = $("#employeeName").val();
		var time = $("#entryTime").val();
		var errorMessage = "";
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
  </head>
  
 <body>
   <%
  		String empId = request.getParameter("empId");
  		request.setAttribute("empId",empId);
  %>
 
   	<form action="<%=basePath %>employee/change.do" method="post" onsubmit="return editEmployee()">
   	
   	<table id = "deptEditTable">
   		<tr>
   			<td>
   			员工编号:
   			</td>
   			<td>
   				<input type = "text" name="employeeNo" id="employeeNo" onclick="blur()"/>
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
   			性别:
   			</td>
   			<td>
   				<select id ="employeeSex" name = "employeeSex">
   				</select>
   			</td>
   		</tr>  

   		<tr>
   			<td>
   			所属部门:
   			</td>
   			<td>
   				<select id="dept" name = "dept">
   					
   				</select>
   			</td>
   		</tr>  
   		
   		<tr>
   			<td>
   			入职时间:
   			</td>
   			<td>
   				<input type = "text" name="entryTime" id="entryTime"/>
   			</td>
   		</tr>  
   		
   		<tr>
   			<td colspan="2">
   				<input type = "submit" value="修改"/>
   				<input type = "reset" value="重置"/>
				<a href="<%=basePath%>njwb/employee/employee.jsp" target="contentPage"><input type="button" value="返回"></a>
   			</td>
   		</tr>  	
   	</table>
   	
   	
   	</form>
   	<input type="hidden" value="${empId}" id="hiddenInput">
  </body>
</html>
