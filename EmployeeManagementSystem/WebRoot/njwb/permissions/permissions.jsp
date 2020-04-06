<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>权限管理</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/reset.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/main.css">
    <script type="text/javascript" src="<%=basePath%>js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/layer/layer.js"></script>
	
	<script type="text/javascript">
		$(document).ready(function(){
			loadMenu();
			loadRole();
			search(1);
		});
		var currentPage;
		var oldMenuId = null;
		var oldRoleId = null;
		function search(pageNo){
			var menuId=$("#menu").val();
			var roleId=$("#role").val();
			console.log(roleId+ " "+menuId);
			if(oldMenuId != menuId||oldRoleId != roleId){
  				pageNo = 1;
  			}
  			oldMenuId = menuId;
  			oldRoleId = roleId;
			$.ajax({
  				type:"POST",
  				url:"<%=basePath%>permissions/getPermissionsByRidAndMidAndPageNo.do",
  				data:{
  					"pageNo":pageNo,
  					"menuId":menuId,
  					"roleId":roleId,
  				},
  				success:function(msg){
  					var pager = $.parseJSON(msg);
  					currentPage = pager;
  					var datas = pager.list;
  					var resultTable = $(".deptInfo");
  					resultTable.html($("table tr")[0]);
  					for(var i = 0; i < datas.length;i++){
  						var tr = $("<tr></tr>");
  						var permissionRoleId = $("<td>"+datas[i].t_role_id+"</td>");
  						var permissionRoleName = $("<td>"+datas[i].t_role_name+"</td>"); 
  						var permissionMenuId = $("<td>"+datas[i].t_menu_id+"</td>"); 
  						var permissionMenuName = $("<td>"+datas[i].t_menu_name+"</td>"); 
						var operationTd = $("<td></td>");
						var deleteImg = $('<img id="'+datas[i].t_id+'"src="img/delete.png" class="operateImg" onclick="del(id)">');
						var editImg = $('<img src="img/edit.png" class="operateImg" >');
						var deptEditA = $('<a href="<%=basePath%>njwb/permissions/permissionsEdit.jsp?t_role_id='+datas[i].t_role_id+'&t_menu_id='+datas[i].t_menu_id+'&t_current_pageNo='+pageNo+'" target="contentPage"></a>');
						deptEditA.append(editImg);
						operationTd.append(deleteImg);
						operationTd.append(deptEditA);
  						tr.append(permissionRoleId).append(permissionRoleName).append(permissionMenuId).append(permissionMenuName).append(operationTd);
  						resultTable.append(tr);
  					}
  					
  					for(;i<4;i++){
  						var tr = $("<tr><td>&nbsp;</td><td></td><td></td><td></td><td></td><td></td></tr>");
  						resultTable.append(tr);
  					}
  					var startPage = $("#startPage");
  					var lastPage = $("#lastPage");
  					lastPage.attr("onclick","search("+(pager.pageNo - 1)+")");
  					var nextPage = $("#nextPage");
  					nextPage.attr("onclick","search("+(pager.pageNo + 1)+")");
  					console.log("下一页："+(pager.pageNo + 1));
  					var endPage = $("#endPage");
  					endPage.attr("onclick","search("+pager.totalPage+")");
					var state = false;  					
  					if(pager.pageNo == 1){
  						state = true;
  					}
  					startPage.prop("disabled",state);
  					lastPage.prop("disabled",state);
  					state = false;
  					if(pager.pageNo == pager.totalPage){
  						state = true;
  					}
  					nextPage.prop("disabled",state);
  					endPage.prop("disabled",state);
  					
  				}
  			});
		}
		function del(id){
  			var result = window.confirm("确认要删除吗?");
  			if(true == result){
				$.ajax({
					type:"POST",
					data:{"id":id},
					url:"<%=basePath%>permissions/remove.do",
					success:function(msg){
						search(1);
                        alert("删除成功");
                       }
				});
			}
  		}
		function loadMenu(){
			$.ajax({
				type:"POST",
				url:"<%=basePath%>menu/getMenu.do",
				success:function(msg){
				var pager = $.parseJSON(msg);	
				var datas = pager;
				var menu = $("#menu");
				menu.append($("<option value>"+"请选择"+"</option>"));
				for(var i=0;i<datas.length;i++){
					var noTd =$("<option value="+datas[i].t_menu_id+" >"+datas[i].t_menu_name+"</option>");
					menu.append(noTd);
				}
			}
		});
		}
		function loadRole(){
			$.ajax({
				type:"POST",
				url:"<%=basePath%>role/getRole.do",
				success:function(msg){
				var pager = $.parseJSON(msg);	
				var datas = pager;
				var role = $("#role");
				role.append($("<option value>"+"请选择"+"</option>"));
				for(var i=0;i<datas.length;i++){
					var noTd =$("<option value="+datas[i].t_role_id+" >"+datas[i].t_role_name+"</option>");
					role.append(noTd);
				}
			}
		});
		}
	</script>
  </head>
  
  <body>
    <h1 class="title">首页  &gt;&gt;权限管理 </h1>
    <br>
   	<center>
    角色：<select id="role" name="role"></select>
	菜单：<select id="menu" name="menu"></select>
	 <input type="button" value="查询" onclick="search()">
   	</center>
	<br>
    <div class="add">
         <a href="<%=basePath%>njwb/permissions/permissionsAdd.jsp" target="contentPage"><img alt="" src="<%=basePath%>img/add.png" width="18px" height="18px">添加权限</a>
    </div>
  	<br>
    <table class="deptInfo">
   		<tr class="titleRow">
   			<td>角色ID</td>
   			<td>角色名称</td>
   			<td>菜单ID</td>
   			<td>菜单名称</td>
   			<td>操作列表</td>
   		</tr>
   </table>
   
   <div id = "d1" style="text-align: center;">
	  		<input id="startPage" type="button" value="首页" onclick="search(1)">
	  		<input id="lastPage" type="button" value="上一页">
	  		<input id="nextPage" type="button" value="下一页">
	  		<input id="endPage" type="button" value="末页">
  </div>
   
  </body>
</html>
