<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>添加请假</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="<%=basePath %>js/laydate/laydate.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/jquery-1.8.3.min.js"></script>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style type="text/css">
		body,div,table,tr,td{
			margin: 80px 0px;
			padding: 0px;
		}
	
		#holidayAddTable{
			font-size: 15px;
			border-collapse: collapse;
			width: 350px;
			margin: 20px auto;
			
			
		}
		
		#holidayAddTable td{
			height: 40px;
		}
	
	</style>
	<script type="text/javascript">
		function add(value){
			var holidayType = $("#holidayType").val();
			var holidayInfo = $("#holidayInfo").val();
			var startTime = $("#startTime").val();
			var endTime = $("#endTime").val();
			var errorMessage = "";
            if(holidayInfo==""){
				errorMessage += "请假信息不能为空"+"\r\n";
			}
			if(startTime==""){
				errorMessage += "开始时间不能为空"+"\r\n";
			}
			if(!/^\d{4}[-]\d{2}[-]\d{2}$/.test(startTime)){
				errorMessage+="开始时间格式错误，例：2012-11-11"+"\r\n";
			}
			if(endTime==""){
				errorMessage += "结束时间不能为空"+"\r\n";
			}
			if(!/^\d{4}[-]\d{2}[-]\d{2}$/.test(endTime)){
				errorMessage+="结束时间格式错误，例：2012-11-11";
			}
			if(errorMessage.length!=0){
				alert(errorMessage);
				return;
			}
			
			if(value == "1"){
				var holidayStates = "已提交";
			}else{
				var holidayStates = "草稿";
			}
			$.ajax({
				type:"POST",
				url:"<%=basePath%>holiday/addHoliday.do",
				data:{
					"holidayType":holidayType ,
					"holidayInfo": holidayInfo,
					"startTime": startTime,
					"endTime": endTime,
					"holidayStates": holidayStates,
				},
				success:function(){
					alert("添加成功,请返回主界面查看");
				}
				
			});
		}
	</script>
  </head>
  
  <body>
	<form action="">
   	
   	<table id = "holidayAddTable">
   		<tr>
   			<td>
   			请假类型:
   			</td>
   			<td>
   				<select name="holidayType" id="holidayType">
   					<option value="请选择">请选择</option>
         			<option value="事假">事假</option>
         			<option value="婚假">婚假</option>
         			<option value="年假">年假</option>
         			<option value="调休">调休</option>
         			<option value="病假">病假</option>
         			<option value="丧假">丧假</option>
   				</select>
   			</td>
   		</tr>
   		<tr>
   			<td>
   			请假事由:
   			</td>
   			<td>
   				<textarea id="holidayInfo" name="holidayInfo" rows="3" cols="25"></textarea>
   			</td>
   		</tr>  
   		<tr>
   			<td>
   			开始时间:
   			</td>
   			<td>
   				<input type = "text" name="startTime" id="startTime" onclick="laydate()"/>
   			</td>
   		</tr>
   		<tr>
   			<td>
   			结束时间:
   			</td>
   			<td>
   				<input type = "text" name="endTime" id="endTime" onclick="laydate()"/>
   			</td>
   		</tr>  
   		<tr>
   			<td colspan="2">
   				<input type = "button" value="草稿" onclick="add('0')"/>
   				<input type = "button" value="提交" onclick="add('1')"/>
   				<input type = "reset" value="重置"/>
				<a style="text-decoration: none" href="<%=basePath %>njwb/holiday/holiday.jsp" target="contentPage"><input type="button" value="返回"></a>
   			</td>
   		</tr>  	
   	</table>
   	
   	
   	</form>
  </body>
</html>
