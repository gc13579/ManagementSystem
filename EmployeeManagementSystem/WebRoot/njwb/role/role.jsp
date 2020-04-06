<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>角色管理</title>
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
			search();
		});
		function search(){
			$.ajax({
  				type:"POST",
  				url:"<%=basePath%>role/getRole.do",
  				success:function(msg){
  					var datas = $.parseJSON(msg);
  					var resultTable = $(".deptInfo");
  					resultTable.html($("table tr")[0]);
  					for(var i = 0; i < datas.length;i++){
  						var tr = $("<tr></tr>");
  						var roleId = $("<td>"+datas[i].t_role_id+"</td>");
  						var roleName = $("<td>"+datas[i].t_role_name+"</td>"); 
						var operationTd = $("<td></td>");
						var deleteImg = $('<img id="'+datas[i].t_role_id+'"src="img/delete.png" class="operateImg" onclick="del(id)">');
						var editImg = $('<img src="img/edit.png" class="operateImg" >');
						var deptEditA = $('<a href="<%=basePath%>njwb/role/roleEdit.jsp?t_role_id='+datas[i].t_role_id+'" target="contentPage"></a>');
						deptEditA.append(editImg);
						operationTd.append(deleteImg);
						operationTd.append(deptEditA);
  						tr.append(roleId).append(roleName).append(operationTd);
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
					data:{"t_role_id":id},
					url:"<%=basePath%>role/remove.do",
					success:function(msg){
							search();
							alert(msg);
<%--		                    alert("删除成功");--%>
                    }
				});
			}
  		}
	</script>
  </head>
  
  <body>
    <h1 class="title">首页  &gt;&gt;角色管理 </h1>
    <br>
    <div class="add">
         <a href="<%=basePath%>njwb/role/roleAdd.jsp" target="contentPage"><img alt="" src="<%=basePath%>img/add.png" width="18px" height="18px">添加角色</a>
    </div>
  	<br>
    <table class="deptInfo">
   		<tr class="titleRow">
   			<td>角色ID</td>
   			<td>角色名称</td>
   			<td>操作列表</td>
   		</tr>
   </table>
  </body>
</html>
