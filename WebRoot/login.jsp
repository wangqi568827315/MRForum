<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <meta charset="utf-8" />
		<title>MR-Login</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="css/loginCSS/login.css"/>
	<script src="js/jquery-3.1.0.min.js" type="text/javascript" charset="utf-8"></script>
	<script src="js/loginJS.js" type="text/javascript" charset="utf-8"></script>

  </head>
  
  <body>
	
	<div id="topDiv">
		<a href="indexRefreshServlet" title="MuscleRipper"><div id="headLogo">
			<label id="headLogoSpan">MR</label>
			<label id="headUrlSpan">muscleripper.cn</label>
		</div></a>
	</div>
	
	<div id="mainDiv">
		<div id="loginDiv">
			
			<!--登录表单-->
			<form action="loginServlet" method="post">
			<div id="iconDiv">
			</div>
			<div class="loginText1"><img src="img/loginIMG/login_user.png"/>
				<input type="text" name="username" id="username" value="论坛账号/手机号"/>
			</div>
			<div class="loginText1"><img src="img/loginIMG/login_pwd.png"/>
				<input type="password" name="password" id="password" />
			</div>
			<div class="loginText2">
				<div id="regDiv"><a href="regist.jsp">注册</a> </div>
				<div id="cpwdDiv"><a href="changepwd.jsp">修改密码</a></div>
			</div>
			<div class="loginText1" >
				<input type="submit" id="loginBtn" value="登&nbsp;&nbsp;&nbsp;&nbsp;录" />
			</div>
			
			</form>
			<div id="loginMsgDiv">${param.msg}</div>
		</div>
	</div>
	
	</body>
</html>
