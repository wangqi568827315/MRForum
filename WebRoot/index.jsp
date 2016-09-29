<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.chinasoft.model.entity.MEMBERS"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>

    
    
		<title>MR论坛</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="css/indexCSS/headStyle.css"/>
	<link rel="stylesheet" type="text/css" href="css/indexCSS/mainframestyle.css"/>
	<link rel="stylesheet" type="text/css" href="css/indexCSS/bodyStyle.css"/>
		
	<script type="text/javascript" charset="utf-8" src="ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="ueditor/ueditor.all.min.js"> </script>
	<script src="js/jquery-3.1.0.min.js" type="text/javascript" charset="utf-8"></script>
	<script src="js/mustache.min.js" type="text/javascript" charset="utf-8"></script>
	<script src="js/pubNoteJS.js" type="text/javascript" charset="utf-8"></script>
	
	 <meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
    
    <!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
    <!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
    <script type="text/javascript" charset="utf-8" src="ueditor/lang/zh-cn/zh-cn.js"></script>

    <style type="text/css">
        div{
            width:100%;
        }
    </style>


	
	<script type="text/html" id="tmpl">
		<table id="bodyNoteTable" cellpadding="0" cellspacing="0" style="background-color:white">
      		{{#notelist}}
        	<tr>
				<td><ul class="NoteUl">
						<li ><a href = "showNoteInfoServlet?noteid={{objdata.n_id}}" title="{{notetitle}}">{{{topornot}}}{{{supornot}}}{{{objdata.n_title}}}</a></li>
						<li ><a href = "showNoteInfoServlet?noteid={{objdata.n_id}}">{{{objdata.n_article}}}</li></a></ul></td>
					<td><label id="noteOwnerLabel"><a href="showMsgServlet?mid={{objdata.m_id}}" target="blank">{{{pname}}}</a></label></td>
					<td>{{objdata.n_repcount}}</td>
				</tr>
       		{{/notelist}}
          
       </table>
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
									<!-- ---------------------------------------------------------------------------------- -->
									<li>
										<div id="logindiv_cell1" >
										<!-- 设置cell的ul -->
										<ul id="logindiv_cell1_ul"  class="logindiv_cell_ul">
												<a href="changepwd.jsp"><li>修改密码</li></a>
												<a href="logoutServlet?logoutindex=1"><li>切换账号</li></a>
												<a href="logoutServlet?logoutindex=2"><li>注销</li></a>
										</ul>
										</div>
									</li>
									<!-- ---------------------------------------------------------------------------------- -->
									
							</ul>
							
							
						</li>
						<li>
							
							<div id="myIconDiv"  style="background-image: url(${sessionScope.loginUser == null ? 'img/DEFAULTICON/defaultIcon.png' : loginUser.getM_icon()});">
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
				
			</div>
		
		<div id="mainDiv">
			<div id="mainFrameDiv">
				<div id="mainMsgDiv">
					<div id="mainMsg_MyMsg">
					<c:if test="${sessionScope.loginUser == null}">
							<a href="javascript:void(0);" target="blank">
						</c:if>
						<c:if test="${sessionScope.loginUser != null}">
							<a href="showMsgServlet?mid=${sessionScope.loginUser.getM_id()}">
						</c:if>
					
						<div id="mainMsg_MyMsg_icon"  style="background-image: url(${sessionScope.loginUser == null ? 'img/DEFAULTICON/defaultIcon.png' : loginUser.getM_icon()});">
						</div></a>
						
						<div id="mainMsg_MyMsg_name">
							<label id="myNickName" 
							style="color: <c:if test="${sessionScope.loginUser.getP_no() == 0}">
  												  <c:out value=" white"/></c:if>
  												  <c:if test="${sessionScope.loginUser.getP_no() == 1}">
  												  <c:out value=" white"/></c:if>
  												  <c:if test="${sessionScope.loginUser.getP_no() == 2}">
  												  <c:out value=" red"/></c:if>
  												  <c:if test="${sessionScope.loginUser.getP_no() == 3}">
  												  <c:out value=" #FF7F24"/></c:if>
  												  <c:if test="${sessionScope.loginUser.getP_no() == 4}">
  												  <c:out value=" #FF00FF"/></c:if>">
  							
								${sessionScope.loginUser == null ?'游客':loginUser.getM_nickname()}</label>
						</div>
						<div id="mainMsg_MyMsg_exp">
							<div id="mainMsg_MyMsg_exp_count">
								<font>经验: </font>
								<label color="cornflowerblue"  id="expcountLabel">
								${sessionScope.loginUser == null ? '0' : loginUser.getM_exp()}</label>
							</div>
							<div id="mainMsg_MyMsg_exp_image">
							<c:choose>
								  <c:when test="${loginUser.getM_exp()>=0 && loginUser.getM_exp()<20}">
							      <img src="img/indexIMG/rankImage/rank1.png"  width="32px" height="32px"/></c:when>
  								  <c:when test="${loginUser.getM_exp()>=20 && loginUser.getM_exp()<50}">
  								  <img src="img/indexIMG/rankImage/rank2.png"  width="32px" height="32px"/></c:when>
  							  	  <c:when test="${loginUser.getM_exp()>=50 && loginUser.getM_exp()<100}">
  							      <img src="img/indexIMG/rankImage/rank3.png"  width="32px" height="32px"/></c:when>
  								  <c:when test="${loginUser.getM_exp()>=100 && loginUser.getM_exp()<200}">
  								  <img src="img/indexIMG/rankImage/rank4.png"  width="32px" height="32px"/></c:when>
  								  <c:when test="${loginUser.getM_exp()>=200 && loginUser.getM_exp()<500}">
  								  <img src="img/indexIMG/rankImage/rank5.png"  width="32px" height="32px"/></c:when>
  								  <c:when test="${loginUser.getM_exp()>=500 && loginUser.getM_exp()<1000}">
  								  <img src="img/indexIMG/rankImage/rank6.png"  width="32px" height="32px"/></c:when>
  								  <c:when test="${loginUser.getM_exp()>=1000 && loginUser.getM_exp()<2000}">
  								  <img src="img/indexIMG/rankImage/rank7.png"  width="32px" height="32px"/></c:when>
  								  <c:when test="${loginUser.getM_exp()>=2000 && loginUser.getM_exp()<5000}">
  								  <img src="img/indexIMG/rankImage/rank8.png"  width="32px" height="32px"/></c:when>
  								  <c:when test="${loginUser.getM_exp()>=5000 && loginUser.getM_exp()<10000}">
  								  <img src="img/indexIMG/rankImage/rank9.png"  width="32px" height="32px"/></c:when>
  								  <c:when test="${loginUser.getM_exp()>=10000 }">
  								  <img src="img/indexIMG/rankImage/rank10.png"  width="32px" height="32px"/></c:when>
								</c:choose>
							</div>
						</div>
						
						<div id="mainMsg_MyMsg_signin">
							<input type="button" value="签  到" id="signin_btn" title="签到加经验哦"
								onclick="signin(${sessionScope.loginUser==null ? '0' : sessionScope.loginUser.getM_id()})"/>
							<div id="datelabel"><label></label>
							</div>
						</div>
						
						
					</div>
					<div id="mainMsg_ThisMsg">
						<div id="msgtipDiv">论坛管理:</div>
						<table class="managerTable">
							<tr>
								<td><div class="managerIcon" id="micon1"></div>
								<a href="javascript:void(0);" class="manageraclass"><label class="managerNickname" id="mname1">
									<font color="white">-</font>
									</label></a>
								</td>
								<td><div class="managerIcon" id="micon2"></div>
								<a href="javascript:void(0);" class="manageraclass"><label class="managerNickname" id="mname2">
									<font color="white">-</font>
									</label></a></td>
							</tr>
							<tr>
								<td><div class="managerIcon" id="micon3"></div>
									<a href="javascript:void(0);" class="manageraclass"><label class="managerNickname" id="mname3">
									<font color="white">-</font>
									</label></a>
									</td>
								<td><div class="managerIcon" id="micon4"></div>
									<a href="javascript:void(0);" class="manageraclass">
									<label class="managerNickname" id="mname4">
									<font color="white">-</font>
									</label></a>
									</td>
							</tr>
						</table>
						
					</div>
				</div>
				<div id="mainCouDiv">
				<!--
					main右边Ul区域
                -->
					<div id="mainCouUlDiv">
						<div id="mainCouUlUpDiv">
							<ul id="mainCouUlUp">
								<li>减 脂 修 身</li>
								<li>增 肌 塑 形</li>
								<li>运 动 恢 复</li>
								<li>H I I T</li>
								<li>健 身 饮 食</li>
							</ul>
						</div>
						
						<div class="mainCouDownDiv">
							<ul class="mainCouUlDown">
								<a href="javascript:void(0)" target="blank"><li><span>慢跑</span></li></a>
								<a href="javascript:void(0)" target="blank"><li><span>动感单车</span></li></a>
								<a href="javascript:void(0)" target="blank"><li><span>瑜伽</span></li></a>
								<a href="javascript:void(0)" target="blank"><li><span>游泳</span></li></a>
								<a href="javascript:void(0)" target="blank"><li><span>舞蹈</span></li></a>
							</ul></div>
						<div class="mainCouDownDiv">
							<ul class="mainCouUlDown">
								<a href="javascript:void(0)" target="blank"><li><span>肩部锻炼</span></li></a>
								<a href="javascript:void(0)" target="blank"><li><span>胸部锻炼</span></li></a>
								<a href="javascript:void(0)" target="blank"><li><span>背部锻炼</span></li></a>
								<a href="javascript:void(0)" target="blank"><li><span>腿部锻炼</span></li></a>
								<a href="javascript:void(0)" target="blank"><li><span>腹部锻炼</span></li></a>
							</ul></div>
						<div class="mainCouDownDiv">
							<ul class="mainCouUlDown">
									<a href="javascript:void(0)" target="blank"><li><span>拉伸运动</span></li></a>
									<a href="javascript:void(0)" target="blank"><li><span>补剂</span></li></a>
									<a href="javascript:void(0)" target="blank"><li><span>按摩</span></li></a>
									<a href="javascript:void(0)" target="blank"><li><span>物理疗法</span></li></a>
							</ul></div>
						<div class="mainCouDownDiv">
							<ul class="mainCouUlDown">
									<a href="javascript:void(0)" target="blank"><li><span>有氧操</span></li></a>
									<a href="javascript:void(0)" target="blank"><li><span>MFT</span></li></a>
									<a href="javascript:void(0)" target="blank"><li><span>变速跑</span></li></a>
							</ul></div>
						<div class="mainCouDownDiv">
							<ul class="mainCouUlDown">
									<a href="javascript:void(0)" target="blank"><li><span>饮食搭配</span></li></a>
									<a href="javascript:void(0)" target="blank"><li><span>食谱</span></li></a>
									<a href="javascript:void(0)" target="blank"><li><span>菜品选择</span></li></a>
									<a href="javascript:void(0)" target="blank"><li><span>增肌选择</span></li></a>
									<a href="javascript:void(0)" target="blank"><li><span>减脂选择</span></li></a>
							</ul></div>
						
					</div>
				
				<!--
					main右边图片区域
                -->
					<div id="mainCouImgDiv">
						<div id="mainCouImgDivImg"></div>
						<div id="mainCouImgDivBanner">
							<ul id="mainCouImgScrollUl">
								<li id="couImgli_1" onmouseover="UserhoverLi(1)" onmouseout="UserOut()">拉扎尔</li>
								<li id="couImgli_2" onmouseover="UserhoverLi(2)" onmouseout="UserOut()">腹肌撕裂者</li>
								<li id="couImgli_3" onmouseover="UserhoverLi(3)" onmouseout="UserOut()">蛋白粉</li>
								<li id="couImgli_4" onmouseover="UserhoverLi(4)" onmouseout="UserOut()">普拉提</li>
								<li id="couImgli_5" onmouseover="UserhoverLi(5)" onmouseout="UserOut()">MATRIX</li>
							</ul>
							
						</div>
						
					</div><!---->
					
				</div>
				
				
			</div>
			
		</div>
		
		<!--
			body主体
                -->
        <!-- body导航-->
		<div id="bodyBannerDiv">
			<div id="bodyBannerDiv1">
			<ul>
				<li class="bannerLi"><input type="button" value="全 部" class="bannerLiBtn" onclick="AjaxData(0,0)"></li>
				<li class="bannerLi"><input type="button" value="精 品" class="bannerLiBtn" onclick="AjaxData(0,1)"></li>
			</ul>
			</div>
		</div>
		<div id="bodyLeftDiv">
			<!-- body左板块-->
			<div class="bodyNote">
					<!-- <td><ul class="NoteUl"><li><a href="javascript:void(0);">20字测试帖子的标标题测试帖子的标题</a></li>
						<li><a href="javascript:void(0);">30字测试帖子的正文测试帖子的试帖子的正文测试帖子的正文</a></li></ul></td> -->
				
		</div>
		<!--翻页-->
			<div id="pageChangeDiv">
			<br><font color="dimgray">当前第<font  id="currentIndexFont" color="royalblue"></font>页</font>
				<table id="pageChangeTable"><tr>
					<td><a href="javascript:void(0);" onclick="showNotePage(-1)">&lt;上一页</a></td>
					<td><a href="javascript:void(0);" onclick="${loginUser==null? 'tologin()' : 'showNotePage(1)'}">下一页&gt;</a></td>
					<td><select style="width: 40px;" id="pageSelect"></select></td>
					<td><input type="text" size="3" id="pageText"/></td>
					<td><input type="button" value="跳页" id="pageBtn" onclick="${loginUser==null? 'tologin()' :'jumpPage()'}"/></td>
				</tr></table>
			</div>
		<!-- body右板块-->
		<div id="bodyRightDiv" style="background-color:white">
			<div id="bodyRight_rule">
				<font style="color: cornflowerblue;font-weight: bold;font-size: 20px;line-height: 150%;">论坛规则</font><br>
				<font style="color: gray;font-size: 16px;line-height: 150%;">只要充钱你就能成为会员</font><br>
				<font style="color: gray;font-size: 16px;line-height: 150%;">联系我QQ:1335636311</font><br>
				<font style="color: gray;font-size: 16px;line-height: 150%;">只要充钱你就能变得更强</font><br>
				<font style="color: gray;font-size: 16px;line-height: 150%;">广告位强势招租</font>
			</div>
		</div><!---->
		
		<!-- 富文本-------------------------------------------------------------------------------------------- -->
		<div id="pubNoteDiv2"><input type="text" class="pubNoteTitle"  id="pubnotetitle" maxlength="30"/> </div>
		<div>
		<div>
 		   <script id="editor" type="text/plain" style="width:1024px;height:500px;"></script>
		</div>
		<!-- <input type="button" value="click" onclick="alert(UE.getEditor('editor').getContent())"/> -->
		<div id="pubNoteDiv4"><font class="${loginUser==null? '':'hideclass'}" style="color: red;">您还没有登录!</font>
		<label></label>
		<input type="button" id="pubNoteBtn" value="发  表"  
					onclick="${loginUser==null? 'tologin()':'memPubNote()'}"></div>
		
		<script type="text/javascript">
   		 //实例化编辑器
  		  var ue = UE.getEditor('editor',{
			 toolbars: [
   				 ['undo', 'emotion', 'simpleupload','scrawl','redo','date','time']], 
    			initialFrameWidth : 740,
      		  initialFrameHeight: 200,
			});
			</script>
		
		<!-- 富文本-------------------------------------------------------------------------------------------- -->
		
		
		
		
		
		
		
		<!--右边fixed板块-->
		<div id="fixedDiv">
			<a href="#headBackDiv"><div id="fixedDivCell1" ></div></a>
			<a href="#pubNoteDiv2"><div id="fixedDivCell2" ></div></a>
		</div>
		
		<!--遮罩层div-->
		<div id="zz"></div>
		
		
		
		
		
	</body>
	
	
	
	 <input type="hidden" value="0" id="pIndexHide">
       <input type="hidden" value="0" id="pTotalHide">
       <input type="hidden" value="0" id="pTypeHide">
       
       <div id="tipDiv" ></div>
       
</html>