<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>账户维护</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/reset.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/main.css">
    <script type="text/javascript" src="<%=basePath%>js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/layer/layer.js"></script>
 	<script type="text/javascript">
		$(document).ready(function(){
			loadRole();
			search();
		});
		function search(){
			var account=$("#account").val();
			var state=$("#state").val();
			var role=$("#role").val();
			console.log(account+" "+state+" "+role);
			$.ajax({
  				type:"POST",
  				url:"<%=basePath%>user/getUser.do",
  				data:{
  					"account":account,
  					"state":state,
  					"role":role,
  				},
  				success:function(msg){
  					var datas = $.parseJSON(msg);
  					var resultTable = $(".deptInfo");
  					resultTable.html($("table tr")[0]);
  					for(var i = 0; i < datas.length;i++){
  						var tr = $("<tr></tr>");
  						var account = $("<td>"+datas[i].t_user_account+"</td>");
  						var name = $("<td>"+datas[i].t_user_name+"</td>"); 
  						var state = $("<td>"+datas[i].t_user_state+"</td>"); 
  						var role = $("<td>"+datas[i].t_role_name+"</td>"); 
						var operationTd = $("<td></td>");
						var deleteImg = $('<img id="'+datas[i].t_user_id+'"src="img/delete.png" class="operateImg" onclick="del(id)">');
						var editImg = $('<img src="img/edit.png" class="operateImg" >');
						var deptEditA = $('<a href="<%=basePath%>njwb/user/userEdit.jsp?t_user_id='+datas[i].t_user_id+'&t_role_id='+datas[i].t_role_id+'" target="contentPage"></a>');
						deptEditA.append(editImg);
						operationTd.append(deleteImg);
						operationTd.append(deptEditA);
  						tr.append(account).append(name).append(state).append(role).append(operationTd);
  						resultTable.append(tr);
  					}
  				}
  			});
		}
		function del(id){
  			var result = window.confirm("确认要删除吗?");
  			if(true == result){
				$.ajax({
					type:"POST",
					data:{"t_user_id":id},
					url:"<%=basePath%>user/remove.do",
					success:function(msg){
						search();
                        alert("删除成功");
                        }
				});
			}
  		}
		function loadRole(){
				$.ajax({
				type:"POST",
				url:"<%=basePath%>role/getRole.do",
				success:function(msg){
				var pager = $.parseJSON(msg);	
				var datas = pager;
				var role = $("#role");
				role.append($("<option value="+"请选择"+">"+"请选择"+"</option>"));
				for(var i=0;i<datas.length;i++){
					var noTd =$("<option value="+datas[i].t_role_name+">"+datas[i].t_role_name+"</option>");
					role.append(noTd);
				}
			}
		});
		}
	</script>
  </head>
  
  <body>
    <h1 class="title">首页  &gt;&gt;账户管理 </h1>
    <br>
   	<center>
   	帐号：<input type="text" id="account" name="account"">
	帐号状态：<select id="state" name="stete">
		<option value="正常">正常</option>
		<option value="注销">注销</option>
	</select>
    角色：<select id="role" name="role"></select>
	<input type="button" value="查询" onclick="search()">
   	</center>
	<br>
    <div class="add">
         <a href="<%=basePath%>njwb/user/userAdd.jsp" target="contentPage"><img alt="" src="<%=basePath%>img/add.png" width="18px" height="18px">添加账户</a>
    </div>
  	<br>
    <table class="deptInfo">
   		<tr class="titleRow">
   			<td>帐号</td>
   			<td>员工姓名</td>
   			<td>状态</td>
   			<td>角色</td>
   			<td>操作列表</td>
   		</tr>
   </table>
  </body>
</html>

