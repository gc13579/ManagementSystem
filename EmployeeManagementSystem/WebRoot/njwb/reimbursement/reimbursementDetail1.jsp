<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>My JSP 'reimbursementDetail1.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<style type="text/css">
		tr{
			height: 25px;
		}
		table,td,th{
			border: 1px black solid;
		}
		td{
			width: 11em;
		}
		th{
			width: 5em;
		}
	</style>
  </head>
  
  <body>
    <table>
    	<tr><th colspan="6" >费用报销审批单</th></tr>
    	<tr>
    		<th>报销部门</th><td>${employee.empDept}</td>
    		<th>部门领导</th><td>${dept.deptManager}</td>
    	</tr>
    	<tr>
    		<th>申请人</th><td>${reimbursement.reimPerson}</td>
    		<th rowspan="3" valign="top">详情</th><td colspan="2" rowspan="3">${reimbursement.reimAbstract}</td>
    	</tr>
    	<tr>
    		<th>用途</th><td>${reimbursement.reimAbstract}</td>
    	</tr>
    	<tr>
    		<th>金额</th><td>${reimbursement.reimMoney}</td>
    	</tr>
    </table>
  </body>
</html>
