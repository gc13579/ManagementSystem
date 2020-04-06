<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>报销详情</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
    <script type="text/javascript" src="<%=basePath%>js/jquery-1.8.3.min.js"></script>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript">
		$(document).ready(function(){
			var reimbursementNo = $("#reimbursementNo").val();
			$.ajax({
				type:"POST",
				url:"<%=basePath%>reimbursement/selfReimbursement.do",
				data:{"reimbursementNo":reimbursementNo},
				success:function(msg){
					var reimbursement = $.parseJSON(msg);
					var reimbursementNoSpan = $("#reimbursementNoSpan");
					var reimbursementPersonSpan = $("#reimbursementPersonSpan");
					var reimbursementTypeSpan = $("#reimbursementTypeSpan");
					var reimbursementAbstractSpan = $("#reimbursementAbstractSpan");
					var reimbursementMoneySpan = $("#reimbursementMoneySpan");
					var reimbursementTimeSpan = $("#reimbursementTimeSpan");
					var reimbursementStatesSpan = $("#reimbursementStatesSpan");
					var reimNo = $("<span>"+reimbursement.reimNo+"</span><br><br>");
					var reimPerson = $("<span>"+reimbursement.reimPerson+"</span><br><br>");
					var reimType = $("<span>"+reimbursement.reimType+"</span><br><br>");
					var reimAbstract = $("<span>"+reimbursement.reimAbstract+"</span><br><br>");
					var reimMoney = $("<span>"+reimbursement.reimMoney+"</span><br><br>");
					var reimStates = $("<span>"+reimbursement.reimStates+"</span><br><br>");
					var time = reimbursement.reimTime;
					var reimTime = $("<span>"+(time.year+1900)+"年"+(time.month+1)+"月"+(time.date)+"日"+"&nbsp;"+(time.hours)+":"+(time.minutes)+":"+(time.seconds)+"</span><br><br>");
					reimbursementNoSpan.append(reimNo);
					reimbursementPersonSpan.append(reimPerson);
					reimbursementTypeSpan.append(reimType);
					reimbursementAbstractSpan.append(reimAbstract);
					reimbursementMoneySpan.append(reimMoney);
					reimbursementTimeSpan.append(reimTime);
					reimbursementStatesSpan.append(reimStates);
				}
			});
		});
	</script>
  </head>
  
  <body>
  <%
  	String reimbursementNo = request.getParameter("reimbursementNo");
  	request.setAttribute("reimbursementNo",reimbursementNo);
  %>
	
	<div>
		<span id="reimbursementNoSpan">报销编号:</span>
		<span id="reimbursementPersonSpan">申请人:</span>
		<span id="reimbursementTypeSpan">报销类型:</span>
		<span id="reimbursementAbstractSpan">摘要:</span>
		<span id="reimbursementMoneySpan">金额:</span>
		<span id="reimbursementTimeSpan">申请时间:</span>
		<span id="reimbursementStatesSpan">申请状态:</span>
	</div>

	<a style="text-decoration: none" href="<%=basePath %>njwb/reimbursement/reimbursement.jsp" target="contentPage"><input type="button" value="返回"></a>
  	<input type="hidden" value="${reimbursementNo}" id="reimbursementNo">
  </body>
</html>
