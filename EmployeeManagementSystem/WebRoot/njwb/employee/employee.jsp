<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>员工管理</title>
    
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
        var currentPage;
		var oldName = "";
		var oldDept = "";
  		function search(pageNo){
  			var name = $("#name").val();
  			var dept = $("#dept").val();
  			if(oldName != name||oldDept != dept){
  				pageNo = 1;
  			}
  			oldName = name;
  			oldDept = dept;
  			$.ajax({
  				type:"POST",
  				url:"<%=basePath%>employee/getEmployee.do",
  				data:{"pageNo":pageNo,
  					  "name": name,
  					  "dept": dept},
  				success:function(msg){
  					var pager = $.parseJSON(msg);
  					currentPage = pager;
  					var datas = pager.list;
  					var resultTable = $("#resultTable");
  					resultTable.html($("table tr")[0]);
  					for(var i = 0; i < datas.length;i++){
  						var tr = $("<tr></tr>");
  						var empNo = $("<td>"+datas[i].empNo+"</td>"); 
  						var empName = $("<td>"+datas[i].empName+"</td>"); 
  						var sex = $("<td>"+datas[i].sex+"</td>"); 
  						var empDept = $("<td>"+datas[i].empDept+"</td>");
  						var time = datas[i].entryTime;
  						var entryTime = $("<td>"+(time.year+1900)+"-"+(time.month+1)+"-"+(time.date)+"</td>"); 
  						
  						var operationTd = $("<td></td>");
						var deleteImg = $('<img id="'+datas[i].id+'"src="img/delete.png" class="operateImg" onclick="del(id)">');
						//var deleteImg = $('<img src="img/delete.png" class="operateImg" onclick="del('+deptNo+')">');
						var editImg = $('<img src="img/edit.png" class="operateImg" >');
						var employeeEditA = $('<a href="<%=basePath%>njwb/employee/employEdit.jsp?empId='+datas[i].id+'" target="contentPage"></a>');
						var detailImg = $('<img src="img/detail.png" class="operateImg">');
						var detailA = $('<a href="<%=basePath%>njwb/employee/employDetail.jsp?empId='+datas[i].id+'" target="contentPage"></a>');
						employeeEditA.append(editImg);
						detailA.append(detailImg);
						operationTd.append(deleteImg);
						operationTd.append(employeeEditA);
						operationTd.append(detailA);
  						tr.append(empNo).append(empName).append(sex).append(empDept).append(entryTime).append(operationTd);
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
  		$(document).ready(function(){
  			search(1);
  		});
  		function del(id){
  			var result = window.confirm("确认要删除吗?");
  			if(true == result){
				$.ajax({
					type:"POST",
					data:{"id":id},
					url:"<%=basePath%>employee/remove.do",
					success:function(msg){
						search(1);
                        alert("删除成功");
                        }
				});
			}
  		}
  	
  		
  
  </script>
  </head>
  
  <body>
     <h1 class="title">首页  &gt;&gt;员工管理 </h1>
         	
         	<div class="add">
         		<a href="<%=basePath%>njwb/employee/employAdd.jsp" target="contentPage"><img alt="" src="<%=basePath%>img/add.png" width="18px" height="18px">添加员工</a>
         	</div>
         	<br/>
         	<br/>
         	<br/>
         	<center>
	        姓名：<input id="name" type="text" style="width: 150px">
	  		部门：<input id="dept" type="text" style="width: 150px">
	  		     <input type="button" value="搜索" onclick="search(1)">
         	</center>
  		<br>
  		<table id="resultTable" class="deptInfo">
  			<tr class="titleRow">
  				<td>员工编号</td>
         		<td>员工姓名</td>
         		<td>性别</td>
         		<td>所属部门</td>
         		<td>入职时间</td>
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
