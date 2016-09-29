<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <meta charset="UTF-8">
		<title>MR-Regist</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="css/registCSS/registStyle.css"/>
		
	<script src="js/jquery-3.1.0.min.js" type="text/javascript" charset="utf-8"></script>
	<script src="js/registJS.js" type="text/javascript" charset="utf-8"></script>

  </head>
  
  <body>
		
	<div id="topDiv">
		<a href="indexRefreshServlet" title="MuscleRipper"><div id="headLogo">
			<label id="headLogoSpan">MR</label>
			<label id="headUrlSpan">muscleripper.cn</label>
		</div></a>
	</div>
		
		<div id="regist_title"><span>注册信息</span></div><div id="regist_tip">所有信息请认真填写，否则无法完成注册！</div>
		<div id="regist_main">
			<form action="registServlet" method="post" id="regist_form" onsubmit="return checkMsg()">
			
				<table id="msg_table" cellpadding="10px" cellspacing="10px">
					
					<tr>
						<td><span>登录账号：</span></td>
						<td><input type="text" id="msg_account" class="textStyle" onblur="checkAccount()" maxlength="16" name="msg_account"/></td>
						<td><span class="msgspan" id="span_account">(6-16位,第一位必须是字母,只能由字母数字组成)</span></td>
					</tr>
					<tr>
						<td><span>登录密码：</span></td>
						<td><input type="password" id="msg_pwd" class="textStyle" onblur="checkPwd()" maxlength="16" name="msg_pwd"/></td>
						<td><span class="msgspan" id="span_pwd">(6-16位，首字符必须是字母,不能包含空格)</span></td>
					</tr>
					<tr>
						<td><span>确认密码：</span></td>
						<td><input type="password" id="msg_pwd_check" class="textStyle" onblur="checkRePwd()" maxlength="16" name="msg_pwd_check"/></td>
						<td><span class="msgspan" id="span_pwd_check">(请确认您的密码)</span></td>
					</tr>
					
					<tr>
						<td><span>手机号码：</span></td>
						<td><input type="text" id="msg_phone" class="textStyle" maxlength="20" name="msg_phone" onblur="checkPhone()"/></td>
						<td><span class="msgspan" id="span_phone">(用于登录,其他用户不可见)</span></td>
					</tr>
					
					<tr>
						<td><span>安全码：</span></td>
						<td><input type="text" id="msg_safepwd" class="textStyle" onblur="checkSafepwd()" maxlength="16" name="msg_safepwd"/></td>
						<td><span class="msgspan" id="span_safepwd">(6-16位,用于找回密码,以数字和字母组成)</span></td>
					</tr>
					
					<tr>
						<td></td>
						<td><input type="submit" value="确认并提交" id="regist_submit" name="regist_submit" onclick="showSuccess()"/></td>
						<td></td>
					</tr>
					
				</table>
				
			</form>
		</div>
		
		<div id="tipDiv" ></div>
		
		
	</body>
</html>
