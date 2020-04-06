<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<head>
<meta charset="utf-8">
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<meta name="description" content="注册界面" />
<title>注册界面</title>
<link rel="stylesheet" href="../css/jq22.css">
<link rel="stylesheet" href="../css/bootstrap.min.css">
<link rel="stylesheet" href="../css/bootstrap.css">
<script src="../js/jquery-2.2.3.min.js"></script>
<script src="../js/My97DatePicker/WdatePicker.js"></script>
<link href="../css/bootstrap.min.css" rel="stylesheet">
<script src="../js/bootstrap.min.js"></script>


<style type="text/css">
	.red{
		color:red;
	}
	.green{
		color:green;
	}
</style>
<script type="text/javascript">
	$(document).ready(function(){
		search();
	})
	
	function search(){
		var province = $("#province");
		$.ajax({
			type:"POST",
			url:"<%=basePath%>province/getAllProvinces.do",
			success:function(msg){
				var datas= $.parseJSON(msg);
				province.append($("<option>"+"省份"+"</option>"));
				for(var i=0;i<datas.length;i++){
					var noTd =$("<option name='select' value="+datas[i].name+" >"+datas[i].name+"</option>");
					province.append(noTd);
				}
			}
		})
	}
	var flag=false;
	function checkBeforeSubmit(){
		var username=$("#userName").val();
		var password=$("#userPwd").val();
		var surePassword=$("#userPwd2").val();
		var iphone=$("#userIphone").val();
		var birthday=$("#birthday").val();
		var province=$("#province").val();
		var errorInfo="";
		if(username.trim()==""||password.trim()==""){
   			errorInfo+="用户名或密码不能为空\r\n";
   		}
		if(password!=surePassword){
			errorInfo+="两次输入的密码不一致，请重新输入\r\n";
		}
		if(!/^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{3,16}$/.test(username)){
			errorInfo+="用户名必须是3~16位的数字和英文\r\n";
		}
		if (password.length < 6 || password.length > 16) {
			errorInfo += "密码长度在6~16位之间\r\n";
		}
		if(!/^\d{11}$/.test(iphone)){
			errorInfo += "电话格式必须为11位数字\r\n";
		}
		if(iphone.trim()==''){
			errorInfo+="电话不能为空\r\n";
		}
		if(birthday.trim()==''){
			errorInfo+="生日不能为空\r\n";
		}
		if(province.trim()=="省份"){
			errorInfo+="还没有选择省份\r\n";
		}
		birthday=birthday.replace(/-/g,"/");
		var birthdayDate=new Date(birthday).getTime();
		var currentDate=new Date().getTime();
		if(birthdayDate>currentDate){
			errorInfo+="不能选择未来的时间作为生日\r\n";
		}
		if(errorInfo.length>0){
			alert(errorInfo);
			return false;
   		}
   		$.ajax({
   			type:"POST",
   			url:"<%=basePath%>user/getUserByAccount.do",
   			async:false,
   			data:{
				"account":username,   			
   			},
   			success:function(msg){
				var data = $.parseJSON(msg);
				if(data[0] != null){
					alert("该用户名已被注册");
					flag=true;
				}
			}
   		})
   		if(flag == true){
   			flag = false;
   			return false;
		}
		return true;
	}
	
</script>


</head>
<body>
<!-- begin -->
<div id="login">
    <div class="wrapper">
        <div class="login">
            <form action="<%=basePath%>user/userRegist.do" method="post" class="container offset1 loginform" onsubmit="return checkBeforeSubmit()">
                <div id="owl-login">
                    <div class="hand"></div>
                    <div class="hand hand-r"></div>
                    <div class="arms">
                        <div class="arm"></div>
                        <div class="arm arm-r"></div>
                    </div>
                </div>
                <div class="pad">
                    <div class="control-group">
                        <div class="controls text-center">
                            <h4>游币用户注册</h4>
                        </div>
                    </div>
                    <div class="control-group">
                        &nbsp;
                    </div>
                    
                    <div class="control-group">
                        <div class="controls">
                            <label for="userName" class="glyphicon glyphicon-user"></label>
                            <input id="userName" type="text" name="account"  placeholder="用户名(3-16个字符)" autofocus="autofocus" required="required" tabindex="1"  class="form-control input-medium">
                        </div>
                    </div>
                    <div class="control-group">
                        <div class="controls">
                            <label for="password" class="glyphicon glyphicon-th"></label>
                            <input id="userPwd" type="password" name="password" placeholder="密码(6-16个字符)" required="required" tabindex="2" class="form-control input-medium" value="${user.password}">
                        </div>
                    </div>
                    <div class="control-group">
                        <div class="controls">
                            <label for="password" class="glyphicon glyphicon-th"></label>
                            <input id="userPwd2" type="password"  placeholder="确认密码" required="required" tabindex="3" class="form-control input-medium" value="${user.password}">
                        </div>
                    </div>
                     
                    <div class="control-group">
                        <div class="controls">
                            <label for="Number"  class="glyphicon glyphicon-user"></label>
                            <input id="userIphone" type="text" name="iphone"  placeholder="手机号码 " required="required"  tabindex="4"  class="form-control input-medium" value="${user.iphone}">
                        </div>
                    </div>
		    <div class="control-group">
                        <div class="controls">
                        <c:choose>
	                        <c:when test="${user eq null}">
	                        	<input id="radio" type="radio" name="sex" value="男"  tabindex="5" checked="checked"/>男
	                            <input id="radio" type="radio" name="sex" value="女"  tabindex="6"/>女
	      			 		</c:when>  
	      			 		<c:otherwise>
	      			 			<c:choose>
		      			 			<c:when test="${user.sex eq '女'}">
		                            	<input id="radio" type="radio" name="sex" value="男"  tabindex="5" />男
		                            	<input id="radio" type="radio" name="sex" value="女"  tabindex="6" checked="checked"/>女
		                        	</c:when>
		                        	<c:otherwise>
			                            <input id="radio" type="radio" name="sex" value="男"  tabindex="5" checked="checked"/>男
			                            <input id="radio" type="radio" name="sex" value="女"  tabindex="6" />女
		                        	</c:otherwise>
		                        </c:choose>
	      			 		</c:otherwise>   
      			 		</c:choose>           
			</div>
                    </div>
		    		<div class="control-group">
                        <div class="controls">
                            <label for="birthday" id="birthdayMsg" class="glyphicon glyphicon-user"></label>
                            <input id="birthday" name="bir" type="text" autocomplete="off" onclick="WdatePicker()" placeholder="生日" class="doubledate ipticon form-control input-medium"  value="${bir}">
                        </div>
                    </div>
                    <div class="control-group">
                        <div class="controls">
                            <select id="province" name="province" class="form-control input-medium">
                            </select>
                        </div>
                    </div>
                    
                </div>
                <div class="form-actions">
                    <a href="login.jsp" tabindex="9" class="btn btn-link text-muted " style="color:red">返回登录</a>
                    <button type="submit" tabindex="8" class="btn btn-primary">点击注册</button>
                </duser.birthdayiuser.birthdayv>
            </form>
        </div>
    </div>
    </div>
	<script type="text/javascript" src="../js/canvas-particle.js"></script>
	<script src="../js/jquery.min.js"></script>
    <script type="text/javascript" >
    $(function() {

        $('#login #userPwd,#login #userPwd2').focus(function() {
            $('#owl-login').addClass('password');
        }).blur(function() {
            $('#owl-login').removeClass('password');
        });
        var config = {
				vx: 4,
				vy:  4,
				height: 2,
				width: 2,
				count: 300,
				color: "121, 162, 185",
				stroke: "100,200,180",
				dist: 8000,
				e_dist: 60000,
				max_conn: 10
			};
			CanvasParticle(config);
         
    });
    </script>
    <c:if test="${isError}">
     	<script type="text/javascript">
     		alert("${errorMessage}");
     	</script>
     </c:if>
<!-- end -->
</body>
</html>

