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
		<title>MR-ChangePwd</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="css/changepwdCSS/changepwdStyle.css"/>
	<script src="js/jquery-3.1.0.min.js" type="text/javascript" charset="utf-8"></script>
	<script src="js/changepwdJS.js" type="text/javascript" charset="utf-8"></script>

  </head>
  
  <body>
		
	<div id="topDiv">
		<a href="indexRefreshServlet" title="MuscleRipper"><div id="headLogo">
			<label id="headLogoSpan">MR</label>
			<label id="headUrlSpan">muscleripper.cn</label>
		</div></a>
	</div>
	
	<div id="main_title"><span>修改密码</span></div><div id="main_tip">${param.msg==null? '请填入准确信息!' : msg}</div>
	
	<form action="changePwdServlet" method="post" id="main_form" onsubmit="return checkchangepwd()">
			
				<table id="changepwd_table" cellpadding="10px" cellspacing="10px">
					
					<tr>
						<td><span>账号：</span></td>
						<td><input type="text" id="changepwd_account" class="textStyle" onblur="checkAccount()" maxlength="16" name="changepwd_account"/></td>
						<td><span class="changepwdspan" id="cpspan_account">(论坛账号/手机号)</span></td>
					</tr>
					<tr>
						<td><span>原密码：</span></td>
						<td><input type="password" id="changepwd_oldpwd" class="textStyle" maxlength="16" name="changepwd_oldpwd" onblur="checkOldPwd()"/></td>
						<td><span class="changepwdspan" id="cpspan_oldpwd">(若忘记,输入安全码即可)</span></td>
					</tr>
					<tr>
						<td><span>安全码：</span></td>
						<td><input type="text" id="changepwd_safepwd" class="textStyle" maxlength="16" name="changepwd_safepwd" onblur="checkSafeCode()"/></td>
						<td><span class="changepwdspan" id="cpspan_safepwd"></span></td>
					</tr>
					<tr>
						<td><span>新密码：</span></td>
						<td><input type="password" id="changepwd_newpwd" class="textStyle" onblur="checkPwd()" maxlength="16" name="changepwd_newpwd"/></td>
						<td><span class="changepwdspan" id="cpspan_newpwd">(6-16位，首字母必须是字母,不能包含空格)</span></td>
					</tr>
					<tr>
						<td><span>确认密码：</span></td>
						<td><input type="password" id="changepwd_renewpwd" class="textStyle" onblur="checkRePwd()" maxlength="16" name="changepwd_renewpwd"/></td>
						<td><span class="changepwdspan" id="cpspan_renewpwd">(请确认您的密码)</span></td>
					</tr>
					
					
					<tr>
						<td></td>
						<td><input type="submit" value="确认修改" id="main_submit" name="main_submit"  onclick="showSuccess()"/></td>
						<td></td>
					</tr>
					
				</table>
				
			</form>
		</div>
		
		<div id="tipDiv" ></div>
	</body>
</html>
