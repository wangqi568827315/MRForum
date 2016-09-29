<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.chinasoft.model.entity.MEMBERS"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <meta charset="UTF-8">
		<title>MR论坛</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="css/showNoteCSS/showNoteStyle.css"/>
	<link rel="stylesheet" type="text/css" href="css/indexCSS/headStyle.css"/>
		 
	<script type="text/javascript" charset="utf-8" src="ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="ueditor/ueditor.all.min.js"> </script>
	<script src="js/jquery-3.1.0.min.js" type="text/javascript" charset="utf-8"></script>
	<script src="js/mustache.min.js" type="text/javascript" charset="utf-8"></script>
	<script src="js/replyJS.js" type="text/javascript" charset="utf-8"></script>
	
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
	<input type="hidden" value="{{lastfloor}}" id="lastfloorHide">
	{{#list}}
			<div style="background-color:white">
		<div id="replyMainDiv" class="{{showorhide}}">
			
			<div id="replyPublisherDiv" style="background-image:url({{lzbgi}});background-repeat: no-repeat;">
				<a href="showMsgServlet?mid={{objdata.m_id}}" target="blank" style="text-decoration:none;">
								<div id="replyPubIcon" style="background-image:url({{pubIcon}});"></div></a>
			
				<div id="replyPubName">
				<a href="showMsgServlet?mid={{objdata.m_id}}" target="blank" style="text-decoration:none;">{{{pubName}}}</a></div>
					<img src="img/indexIMG/rankImage/rank{{pubRank}}.png" style="position: relative;top: -5px;left:82px;width: 32px;height: 32px;"/>
				
			</div>
			<!--回复正文-->
			<div id="replyArticalDiv">
				{{{objdata.r_content}}}
			</div>

			<!--楼中楼-->
			<table id="childReplyTable">
			{{#childrepdata}}
				<tr><td></td><td><a href="showMsgServlet?mid={{childpubmid}}" target="blank">{{childpubname}}</a>&nbsp;:
				<font>{{childrep.r_content}}</font></td>
				<td onclick="pubReply()"></td>
				</tr>
			{{/childrepdata}}
		</table>


		<!--回复回复框-->
		<table id="repReplyTable" class="hideclass" repReplyTableAttr="">
			 <tr><td></td>
					<td>
						<textarea class="repReplyTa" repReplyTAAttr="" ></textarea>
						<input type="button" id="repReplyBtn" value="发&nbsp;表" onclick="toRepReply({{objdata.r_floor}})"/>
					</td>
					<td><a href="javascript:void(0);" onclick="hiderepReply({{objdata.r_floor}})">收起↑</a></td>
					</tr>
		</table>

		<!--回复状态-->
		<div id="replyStateDiv"><label>{{objdata.r_floor}}楼</label>&nbsp;&nbsp;
			<label>{{objdata.r_reptime}}</label>&nbsp;&nbsp;
			<a href="javascript:void(0);" onclick="repReply({{objdata.r_floor}})">回复</a>&nbsp;&nbsp;
			<a href="javascript:void(0);" class="{{delClass}}" onclick="repdelConfirm();$j('#repdelHide').val({{objdata.r_no}});">删除</a>


		</div>

		</div>
		{{/list}}

		
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
												<a href="logoutServlet?logoutindex=1"><li>切换账号</li></a>
												<a href="logoutServlet?logoutindex=2"><li>注销</li></a>
										</ul>
										</div>
									</li>
									<!-- ---------------------------------------------------------------------------------- -->
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
				
			</div>
	
	<div id="noteDiv" style="background-color:white">
		<div id="noteMainDiv">
			<!--帖子标题-->
			<div id="noteTitleDiv">
				<label id="noteTitleLable">${requestScope.pubtitle}</label>
				<input type="button" name="noteBackBtn" id="noteBackBtn" value="返&nbsp;回" 
											onclick="backtoIndex()" />
			</div>
		
			<!--楼主-->
			<div id="notePublisherDiv">
				<a href="showMsgServlet?mid=${requestScope.showNote.getM_id()}" target="blank">
				<div id="notePubIcon" style="background-image: url(${requestScope.pubMem.getM_icon()})"></div></a>
				<div id="notePubName">
					<a href="showMsgServlet?mid=${requestScope.showNote.getM_id()}" target="blank" style="text-decoration: none">${requestScope.pubname }</a>
				</div>
					<c:choose>
						<c:when
							test="${requestScope.pubMem.getM_exp()>=0 && requestScope.pubMem.getM_exp()<20}">
							<img src="img/indexIMG/rankImage/rank1.png"  
							style="position: relative;top: -5px;left:82px;width: 32px;height: 32px;" />
						</c:when>
						<c:when
							test="${requestScope.pubMem.getM_exp()>=20 && requestScope.pubMem.getM_exp()<50}">
							<img src="img/indexIMG/rankImage/rank2.png" 
							style="position: relative;top: -5px;left:82px;width: 32px;height: 32px;" />
						</c:when>
						<c:when
							test="${requestScope.pubMem.getM_exp()>=50 && requestScope.pubMem.getM_exp()<100}">
							<img src="img/indexIMG/rankImage/rank3.png" 
							style="position: relative;top: -5px;left:82px;width: 32px;height: 32px;" />
						</c:when>
						<c:when
							test="${requestScope.pubMem.getM_exp()>=100 && requestScope.pubMem.getM_exp()<200}">
							<img src="img/indexIMG/rankImage/rank4.png" 
							style="position: relative;top: -5px;left:82px;width: 32px;height: 32px;" />
						</c:when>
						<c:when
							test="${requestScope.pubMem.getM_exp()>=200 && requestScope.pubMem.getM_exp()<500}">
							<img src="img/indexIMG/rankImage/rank5.png" 
							style="position: relative;top: -5px;left:82px;width: 32px;height: 32px;" />
						</c:when>
						<c:when
							test="${requestScope.pubMem.getM_exp()>=500 && requestScope.pubMem.getM_exp()<1000}">
							<img src="img/indexIMG/rankImage/rank6.png" 
							style="position: relative;top: -5px;left:82px;width: 32px;height: 32px;" />
						</c:when>
						<c:when
							test="${requestScope.pubMem.getM_exp()>=1000 && requestScope.pubMem.getM_exp()<2000}">
							<img src="img/indexIMG/rankImage/rank7.png" 
							style="position: relative;top: -5px;left:82px;width: 32px;height: 32px;" />
						</c:when>
						<c:when
							test="${requestScope.pubMem.getM_exp()>=2000 && requestScope.pubMem.getM_exp()<5000}">
							<img src="img/indexIMG/rankImage/rank8.png" 
							style="position: relative;top: -5px;left:82px;width: 32px;height: 32px;" />
						</c:when>
						<c:when
							test="${requestScope.pubMem.getM_exp()>=5000 && requestScope.pubMem.getM_exp()<10000}">
							<img src="img/indexIMG/rankImage/rank9.png" 
							style="position: relative;top: -5px;left:82px;width: 32px;height: 32px;" />
						</c:when>
						<c:when 
							test="${requestScope.pubMem.getM_exp()>=10000 }">
							<img src="img/indexIMG/rankImage/rank10.png" 
							style="position: relative;top: -5px;left:82px;width: 32px;height: 32px;" />
						</c:when>
						
					</c:choose>
	
				
			</div>
			<!--帖子正文-->
			<div id="noteArticalDiv">${requestScope.showNote.getN_article() }</div>
			
		</div>
		<!--帖子状态-->
		<div id="noteStateDiv"><label>1楼</label>&nbsp;&nbsp;
			<label>${requestScope.showNote.getN_pubtime() }</label>&nbsp;&nbsp;
			<a href="javascript:void(0);" onclick="${sessionScope.loginUser==null? 'tologin()':'pubReply()'}">回复</a>&nbsp;&nbsp;
			<a href="javascript:void(0);" onclick="delMyNote()" 
				class="${requestScope.showNote.getM_id()==sessionScope.loginUser.getM_id()? '':'hideclass' }">删除</a>
				<ul class="saNoteulClass" style="display: ${sessionScope.loginUser.getP_no()>=3? '' : 'none'}">
					<!-- ------------------------------------------------------------------------------------------------ -->
					<li><label  class="saNoteEditLabelClass" >&Delta;操作
							<ul class="saNotecellulClass">
							<a href="javascript:void(0)" onclick="superNote(${requestScope.showNote.getN_id()},1)"><li>标记精品</li></a>
							<a href="javascript:void(0)" onclick="superNote(${requestScope.showNote.getN_id()},0)"><li>取消精品</li></a>
							<a href="javascript:void(0)" onclick="topNote(${requestScope.showNote.getN_id()},1)"><li>标记置顶</li></a>
							<a href="javascript:void(0)" onclick="topNote(${requestScope.showNote.getN_id()},0)"><li>取消置顶</li></a>
							<a href="javascript:void(0)" onclick="delMyNote()"><li>删除</li></a>
							</label>
						    </ul>
					</li>
					<!-- ------------------------------------------------------------------------------------------------ -->
				</ul>
		</div>
	
	</div>
	
	
	
	<div id="replyDiv"></div>
	
	
	<!--翻页-->
			<div id="pageChangeDiv" >
			<br><font color="dimgray">当前第<font  id="currentIndexFont" color="royalblue"></font>页</font>
				<table id="pageChangeTable"><tr>
					<td><a href="javascript:void(0);" onclick="showRepPage(-1)">&lt;上一页</a></td>
					<td><a href="javascript:void(0);" onclick="${ loginUser==null? 'tologin()' : 'showRepPage(1)' }">下一页&gt;</a></td>
					<td><select style="width: 40px;" id="pageSelect"></select></td>
					<td><input type="text" size="3" id="pageText"/></td>
					<td><input type="button" value="跳页" id="pageBtn" onclick="jumpPage()"/></td>
				</tr></table>
			</div>
	
	<!-- 富文本-------------------------------------------------------------------------------------------- -->

		<div style="margin-left: 450px">
 		   <script id="editor" type="text/plain" style="width:1024px;height:500px;"></script>
		</div>
		<!-- <input type="button" value="click" onclick="alert(UE.getEditor('editor').getContent())"/> -->
		
		<div id="pubReplyDiv4">
		<font class="${loginUser==null? '':'hideclass'}" style="color: red;">您还没有登录!</font>
		<input type="button" id="pubReplyBtn" value="发  表" onclick="${loginUser==null? 'tologin()':'toPubReply()'}"></div>
		
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
	
	
	
		<!--遮罩层div-->
		<div id="zz"></div>
	<!-- 提示div -->
	 <div id="tipDiv" ></div>
	 
	 <!--右边fixed板块-->
		<div id="repfixedDiv">
			<a href="showNoteInfoServlet?noteid=${requestScope.showNote.getN_id()}#headBackDiv"><div id="repfixedDivCell1" ></div></a>
			<a href="showNoteInfoServlet?noteid=${requestScope.showNote.getN_id()}#pubReplyDiv4"><div id="repfixedDivCell2" ></div></a>
		</div>
	
	
	<!-- 帖子删除确定 -->
		<div id="delconfirmDiv">
			<div id="delconfirmDiv1">确认删除主题?</div>
			<div id="delconfirmDiv2">
				<table><tr><td><input type="button"  value="确&nbsp;定" class="confirmbtn" onclick="delConfirm(${requestScope.showNote.getN_id()})"></td>
						<td><input type="button"  value="取&nbsp;消" class="confirmbtn"  onclick="delCancel()"></td></tr></table>
			</div>
		</div>
		<!-- 回复删除确定 -->
		<div id="repdelconfirmDiv">
			<div id="repdelconfirmDiv1">确认删除回复?</div>
			<div id="repdelconfirmDiv2">
				<table><tr><td><input type="button"  value="确&nbsp;定" class="confirmbtn" onclick="repdelete()"></td>
						<td><input type="button"  value="取&nbsp;消" class="confirmbtn"  onclick="repdelCancel()"></td></tr></table>
			</div>
		</div>
		
		
		
		<input type="hidden" value="0" id="pIndexHide">
       <input type="hidden" value="0" id="pTotalHide">
       <input type="hidden" value="${requestScope.showNote.getM_id()}" id="noteMidHide">
       <input type="hidden" value="${sessionScope.loginUser==null? 0 : sessionScope.loginUser.getM_id()}" id="loginMidHide">
        <input type="hidden" value="${requestScope.showNote.getN_id()}" id="nidHide">
         <input type="hidden" value="0" id="repdelHide">
	</body>
</html>










