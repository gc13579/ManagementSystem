<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>添加报销</title>
    
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
			var reimbursementType = $("#reimbursementType").val();
			var reimbursementAbstract = $("#reimbursementAbstract").val();
			var reimbursementMoney = $("#reimbursementMoney").val();
		    var errorMessage = "";
		    if(reimbursementType == "请选择"){
		    	errorMessage +="请选择报销类型";
		    }
			if(reimbursementAbstract == ""){
				errorMessage += "摘要不能为空"+"\r\n";
			}
			if(reimbursementMoney == ""){
				errorMessage +="金额不能为空"+"\r\n";
			}
			
			if(!/^(([1-9]\d*)|\d)(\.\d{1,2})?$/.test(reimbursementMoney)){
				errorMessage +="金额格式错误：精确到小数点后两位或整数";
			}
			if(errorMessage.length!=0){
				alert(errorMessage);
				return;
			}
			
			if(value == "1"){
				var reimbursementStates = "已提交";
			}else{
				var reimbursementStates = "草稿";
			}
			$.ajax({
				type:"POST",
				url:"<%=basePath%>reimbursement/addReimbursement.do",
				data:{
					"reimbursementType":reimbursementType ,
					"reimbursementAbstract": reimbursementAbstract,
					"reimbursementMoney": reimbursementMoney,
					"reimbursementStates": reimbursementStates,
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
   	<center>
   	<table id = "reimbursementAddTable">
   		<tr>
   			<td>
   			报销类型:
   			</td>
   			<td>
   				<select name="reimbursementType" id="reimbursementType">
   					<option value="请选择">请选择</option>
         			<option value="差旅费">差旅费</option>
         			<option value="招待费">招待费</option>
         			<option value="办公费">办公费</option>
   				</select>
   			</td>
   		</tr>
   		<tr>
   			<td>
   			摘要:
   			</td>
   			<td>
   				<textarea id="reimbursementAbstract" name="reimbursementAbstract" rows="3" cols="25"></textarea>
   			</td>
   		</tr>  
   		<tr>
   			<td>
   			金额:
   			</td>
   			<td>
   				<input type = "text" name="reimbursementMoney" id="reimbursementMoney" />
   			</td>
   		</tr>
   		
   		<tr>
   			<td colspan="2">
   				<input type = "button" value="草稿" onclick="add('0')"/>
   				<input type = "button" value="提交" onclick="add('1')"/>
   				<input type = "reset" value="重置"/>
				<a style="text-decoration: none" href="<%=basePath %>njwb/reimbursement/reimbursement.jsp" target="contentPage"><input type="button" value="返回"></a>
   			</td>
   		</tr>  	
   	</table>
   	</center>
   	
   	</form>
  </body>
</html>
