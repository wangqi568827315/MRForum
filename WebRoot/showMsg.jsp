<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath%>">
    
		<title>MR-Msg</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="css/showMsgCSS/showMsgStyle.css"/>
	<link rel="stylesheet" type="text/css" href="css/indexCSS/headStyle.css"/>
		
	<script src="js/jquery-3.1.0.min.js" type="text/javascript" charset="utf-8"></script>
	<script src="js/mustache.min.js" type="text/javascript" charset="utf-8"></script>
	<script src="js/cropbox.js" type="text/javascript" charset="utf-8"></script>
	<script src="js/showMsgJS.js" type="text/javascript" charset="utf-8"></script>
	<script src="js/locationJS.js" type="text/javascript" charset="utf-8"></script>
	
	<script type="text/html" id="tmpl">
	
		{{#datalist}}
		<table id="noteMsgTable" cellpadding="0" cellspacing="0" style="background-color:white">
		<tr>
			<td><ul class="NoteUl">
				<li><a href="showNoteInfoServlet?noteid={{n_id}}">{{n_title}}</a></li>
				<li><a href="showNoteInfoServlet?noteid={{n_id}}">{{{n_article}}}</a></li></ul></td>
			<td>{{n_repcount}}</td>
		</tr>
		</table>
		{{/datalist}}		
	
	</script>
	
	
	

  </head>
  
 <body>
		<div id="headBackDiv">
			<div id="headDiv">
				<a href="indexRefreshServlet" title="MuscleRipper"><div id="headLogo">
					<label id="headLogoSpan">MR</label>
					<label id="headUrlSpan">muscleripper.cn</label>
				</div></a>
				
				<ul id="headGuideUL">
					<!--登录后替换的div-->
						<li class="${ sessionScope.loginUser == null ? 'showclass' : 'hideclass'}">
							<ul id="loginDiv"   >
								<a href="login.jsp" style="text-decoration: none;"><label class="headGuideSpan">登录</label></a>
								</ul>
							
						</li>
						<li  class="${sessionScope.loginUser == null ? 'hideclass' : 'showclass'}">
							<ul id="loginDiv1"  >
									<li>
										<div id="logindiv_cell1" >
										<!-- 设置cell的ul -->
										<ul id="logindiv_cell1_ul"  class="logindiv_cell_ul">
												<a href="logoutServlet?logoutindex=1"><li>切换账号</li></a>
												<a href="logoutServlet?logoutindex=2"><li>注销</li></a>
										</ul>
										</div>
									</li>
									
							</ul>
							
							
						</li>
						<li>
							
							<div id="myIconDiv" style="background-image: url(${sessionScope.loginUser == null ? 'img/DEFAULTICON/defaultIcon.png' : loginUser.getM_icon()});">
										<!-- 设置登录头像的ul -->
										<ul id="loginiconUL"  class="logindiv_cell_ul">
												<c:if test="${sessionScope.loginUser == null}">
													<a href="login.jsp?msg=请先登录再操作!" target="blank">
												</c:if>
												<c:if test="${sessionScope.loginUser != null}">
													<a href="showMsgServlet?mid=${sessionScope.loginUser.getM_id()}" target="blank">
												</c:if>
												<li>我的信息</li></a>
										</ul>
							
							</div>
						</li>
				</ul>
				
				<!-- <div id="headSelDiv">
						<input type="search" name="AllSelText" id="AllSelText" placeholder="全站搜索"/>
						<input type="button" id="AllSelBtn"/>
				</div> -->
				
				</div>
				
			</div><!---->
		
		<!--
        	主体
        -->
        <div id="mainDiv" style="background-color:white">
        	<!--上面头像Div-->
        	<div id="mainDiv_1">
        		<!--头像div-->
        		<div id="mainDiv_1_icon" style="background-image: url(${requestScope.showMem.getM_icon()})"></div>
        		
        		<!--上传头像-->
        		<div class="upfilebox">
        		
          			<label onclick="touploadIcon()" style="display:${requestScope.showMem.getM_id()==sessionScope.loginUser.getM_id()? '' : 'none'}">上传头像</label>
          		
          			<div id="personal_QMD" style="margin-top:${requestScope.showMem.getM_id()==sessionScope.loginUser.getM_id()? '20px' : '60px'}">${requestScope.showMeminfo.getMI_QMD()}</div> 
       			</div>
       			
        	</div>
        	
        	<div id="accountMsg_main">
			<form action="login.jsp" method="get" name="accountMsg_form" onsubmit="return checkMsg()" enctype="multipart/form-data">
			
				<table id="msg_table" >
					
					<tr>
						<td><span class="msgspan">昵称：</span></td>
						<td><input type="text" id="personal_nickname" class="textStyle"  value="${requestScope.showMem.getM_nickname()}"
									name="personal_account" readonly="readonly"/></td>
						<td colspan="2" class="${requestScope.showMem.getM_id()==sessionScope.loginUser.getM_id()? '' : 'toggleHide'}">
								<input type="button" id="msgBtn_save"   class="toggleHide"    value="保&nbsp;存"  onclick="saveEdit()"/>
								<input type="button" id="msgBtn_cancel"  class="toggleHide"   value="取&nbsp;消"  onclick="cancelEdit()"/>
								<input type="button" id="msgBtn_edit"   value="编辑资料"  onclick="editmsg()"/></td>
					</tr>
					
					<tr id="nicknameTip">
						<td colspan="4"><label class="${requestScope.showMem.getM_id()==sessionScope.loginUser.getM_id()? '' : 'toggleHide'}">(昵称只能修改一次,请三思!昵称不能有空格,特殊字符)</label></td>
						
					</tr>
					
					<tr>
						<td><span class="msgspan">签名档：</span></td>
						<td colspan="3">
							<textarea id="personal_QMD_ta" maxlength="50" readonly="readonly" >${requestScope.showMeminfo.getMI_QMD()}</textarea>
						</td>
					</tr>
					
					<tr>
						<td><span class="msgspan">性别：</span></td>
						<td><select id="msg_sexselect" class="toggleHide"></select>
							
							<label class="msgspan" id="personal_sex">${requestScope.showMeminfo.getMI_sex()}</label></td>
							
						<td><span class="msgspan">年龄：</span></td>
						<td><label class="msgspan" id="personal_age">${requestScope.showMeminfo.getMI_age()}</label>
								<select id="msg_ageselect" class="toggleHide"></select>
						</td>
					</tr>
					
					
					<tr>
						<td><span class="msgspan">生日：</span></td>
						
						<td colspan="3">
							<select class="toggleHide" name="birth_year" id="birth_year" onChange="YYYYDD(this.value)">
								<option value=""></option></select>
							<select class="toggleHide" name="birth_month" id="birth_month" onChange="MMDD(this.value)">
								<option value=""></option></select>
							<select class="toggleHide" name="birth_date" id="birth_date" >
								<option value=""></option></select>
								
							<label class="msgspan" id="personal_birthday" >${requestScope.showMeminfo.getMI_birthday()}</label></td>
						</td>
					</tr>
					
					
					<tr>
						<td><span class="msgspan">地址：</span></td>
						<td colspan="3">
							<select class="toggleHide" name="personal_loc_sheng" id="personal_loc_sheng"><option></option></select>
							<select class="toggleHide" name="personal_loc_shi" id="personal_loc_shi"><option></option></select>
							<select class="toggleHide" name="personal_loc_qu" id="personal_loc_qu" ><option></option></select>
							
							<label class="msgspan" id="personal_location">
							${requestScope.showMeminfo.getMI_location_sheng()}-${requestScope.showMeminfo.getMI_location_shi()}-${requestScope.showMeminfo.getMI_location_qu()}
							</label></td>
						</td>
					</tr>
					
					
					<tr>
						<td><span class="msgspan">个人说明：</span></td>
						<td colspan="3">
							<textarea id="personal_explain" maxlength="250" readonly="readonly">${requestScope.showMeminfo.getMI_explain()}</textarea>
						</td>
					</tr>
					
				</table>
				
			</form>
		</div>
        	
        </div>
        
        <!--发过的帖子div-->
        <div id="noteMsgBanner">发起的主题</div>
		<div id="noteMsgDiv"></div>
		
		<!--翻页-->
			<div id="pageChangeDiv" >
			<br><font color="dimgray">当前第<font  id="currentIndexFont" color="royalblue"></font>页</font>
				<table id="pageChangeTable"><tr>
					<td><a href="javascript:void(0);" onclick="showRepPage(-1)">&lt;上一页</a></td>
					<td><a href="javascript:void(0);" onclick="showRepPage(1)">下一页&gt;</a></td>
					<td><select style="width: 40px;" id="pageSelect"></select></td>
					<td><input type="text" size="3" id="pageText"/></td>
					<td><input type="button" value="跳页" id="pageBtn" onclick="jumpPage()"/></td>
				</tr></table>
			</div>
		
		
		<!--遮罩层div-->
		<div id="zz"></div>
		<!-- 修改信息确定 -->
		<div id="editconfirmDiv">
			<div id="editconfirmDiv1">确认修改信息?</div>
			<div id="editconfirmDiv2">
				<table><tr><td><input type="button"  value="确&nbsp;定" class="confirmbtn" onclick="editConfirm()"></td>
						<td><input type="button"  value="取&nbsp;消" class="confirmbtn"  onclick="editCancel()"></td></tr></table>
			</div>
		</div>
		<!-- 提示div -->
		 <div id="tipDiv" ></div>
		 
		<!---------------------------------------------- 上传头像div-------------------------------------------------------- -->
		 <div class="container">
			<div class="imageBox">
				<div class="thumbBox"></div>
				<div class="spinner" style="display: none">Loading...</div>
			</div>
			<div class="action">
				<!-- <input type="file" id="file" style=" width: 200px">-->
				<div class="new-contentarea tc">
					<a href="javascript:void(0)" class="upload-img">
						<label for="upload-file" style="text-align: center">选择图片</label>
					</a>
					<input type="file" class="" name="upload-file" id="upload-file" />
				</div>
				<input type="button" id="btnUploadImg" class="Btnsty_peyton"  style="width: 60px" value="上传">
				<input type="button" id="btnCrop" class="Btnsty_peyton"  style="width: 60px" value="裁切">
				<input type="button" id="btnZoomIn" class="Btnsty_peyton" value="+">
				<input type="button" id="btnZoomOut" class="Btnsty_peyton" value="-">
				<input type="button"  class="Btnsty_peyton" style="width: 60px"  value="取消" onclick="canceluploadIcon()">
				
			</div>
			<div class="cropped"></div>
		</div>
		 <!---------------------------------------------------------------------------------------------------------------------->
		 
		<input type="hidden" value="0" id="pIndexHide">
       <input type="hidden" value="0" id="pTotalHide">
		
		<input type="hidden" value="${sessionScope.loginUser.getM_id()==null?0:sessionScope.loginUser.getM_id()}" id="loginmidHide"/>
		<input type="hidden" value="${requestScope.showMeminfo.getMI_stateccn()}" id="stateccnHide"/>
		<input type="hidden" value="${requestScope.showMeminfo.getMI_age()}" id="ageHide"/>
		<input type="hidden" value="${requestScope.showMeminfo.getMI_sex()}" id="sexHide"/>
		<input type="hidden" value="${requestScope.showMeminfo.getMI_birthday()}" id="birthdayHide"/>
		<input type="hidden" value="${requestScope.showMem.getM_nickname()}" id="nicknameHide"/>
		<input type="hidden" value="${requestScope.showMeminfo.getMI_QMD()}" id="QMDHide"/>
		<input type="hidden" value="${requestScope.showMeminfo.getMI_explain()}" id="explainHide"/>
		<input type="hidden" value="${requestScope.showMem.getM_id()}" id="showmsgmidHide"/>
		<input type="hidden" 
				value="${requestScope.showMeminfo.getMI_location_sheng()}-${requestScope.showMeminfo.getMI_location_shi()}-${requestScope.showMeminfo.getMI_location_qu()}" id="locationHide"/>
	</body>
</html>





