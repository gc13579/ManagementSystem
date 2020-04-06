<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>报销编辑</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="<%=basePath%>js/jquery-1.8.3.min.js"></script>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style type="text/css">
		body,div,table,tr,td{
			margin: 0px;
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
		$(document).ready(function(){
			search();
		});
		function search(){
			var reimbursementNo = $("#reimbursementNo").val();
			var reimbursementType = $("#reimbursementType");
			var reimbursementAbstract = $("#reimbursementAbstract");
			var reimbursementMoney = $("#reimbursementMoney");
			$.ajax({
				type:"POST",
				url:"<%=basePath%>reimbursement/selfReimbursement.do",
				data:{"reimbursementNo":reimbursementNo},
				success:function(msg){
					var reimbursement = $.parseJSON(msg);
					reimbursementType.val(reimbursement.reimType);
					reimbursementAbstract.val(reimbursement.reimAbstract);
					reimbursementMoney.val(reimbursement.reimMoney);
				}
			});
		}
		function change(value){
			if(value == "1"){
				var reimbursementStates = "已提交";
			}else{
				var reimbursementStates = "草稿";
			}
			var reimbursementType = $("#reimbursementType").val();
			var reimbursementAbstract = $("#reimbursementAbstract").val();
			var reimbursementMoney = $("#reimbursementMoney").val();
			var reimbursementNo = $("#reimbursementNo").val();
			console.log("reimbursementMoney:"+reimbursementMoney);
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
			
			
			
			$.ajax({
				type:"POST",
				url:"<%=basePath%>reimbursement/change.do",
				data:{
					"reimbursementNo":reimbursementNo,
					"reimbursementStates":reimbursementStates,
					"reimbursementType":reimbursementType,
					"reimbursementAbstract":reimbursementAbstract,
					"reimbursementMoney":reimbursementMoney,
				},
				success:function(){
					alert("修改成功，请返回主界面查看");
				}
			});
		}
	</script>
  </head>
  
  <body>
  <%
  	String reimbursementNo = request.getParameter("reimbursementNo");
  	request.setAttribute("reimbursementNo",reimbursementNo);
  %>
	<form action="" >
   	<center>
   	<table id = "reimbursementAddTable">
   		<tr>
   			<td>
   			报销类型:
   			</td>
   			<td>
   				<select id="reimbursementType">
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
   				<input type = "text" name="reimbursementMoney" id="reimbursementMoney"/>
   			</td>
   		</tr>  
   		<tr>
   			<td colspan="2">
   				<input type = "button" value="草稿" onclick="change('0')"/>
   				<input type = "button" value="提交" onclick="change('1')"/>
   				<input type = "button" value="重置" onclick="sreach()"/>
				<a style="text-decoration: none" href="<%=basePath %>njwb/reimbursement/reimbursement.jsp" target="contentPage"><input type="button" value="返回"></a>
   			</td>
   		</tr>  	
   	</table>
   	</center>
   	<input type="hidden" value="${reimbursementNo}" id="reimbursementNo">
   	</form>
  </body>
</html>

