
function pubReply(){
	$j("#pubReplyDiv").fadeIn(500);
	$j("#zz").fadeIn(500);
	$j("#replyContentta").focus();
}

function closepubReply(){
	$j("#pubReplyDiv").fadeOut(500);
	$j("#zz").fadeOut(500);
}

/*返回按钮*/
function backtoIndex(){
	window.location.href="indexRefreshServlet";
}

//删除确定
function delMyNote(noteid)
{
	$j("#delconfirmDiv").fadeIn(300);
	$j("#zz").fadeIn(300);
}
//取消删除
function delCancel()
{
	$j("#delconfirmDiv").fadeOut(300);
	$j("#zz").fadeOut(300);
}

//删除帖子操作
function delConfirm(noteid)
{
	var tip = $j("#tipDiv");
	tip.fadeIn(500);
	tip.css({"top":"380px","left":"800px"});
	tip.fadeOut(5000);
	
	$j.post("delNoteServlet",{"noteid":noteid},function(data){
		if(data.deled[0] == true)
		{
			tip.html("<font color='green'>√删除成功!五秒后跳转到主页...</font>");
			setTimeout(function(){window.location.href = "index.jsp";}, 5000);
			$j("#delconfirmDiv").fadeOut(300);
			$j("#zz").fadeOut(300);
		}else {
			tip.html("<font color='red'>×系统出错,请重试!</font>");
		}
		
	});
}


//去登录
function tologin(){
	window.open("login.jsp?msg=请先登录再操作!");
}



//分页显示回复等等---------------------------------------------------------------------------------------------------------
$j(function(){
	//replyPageServlet
	var pIndex = $j("#pIndexHide").val();
	showReply(pIndex);
	
});
//分页显示
function showReply(pIndex)
{
	var nid = $j("#nidHide").val();
	$j.post("replyPageServlet",{"pIndex":pIndex,"nid":nid},function(data)
	{
		var cdiv = $j("#replyDiv");
		$j("#pIndexHide").val(data.pIndex);
		$j("#pTotalHide").val(data.pTotal);
		$j("#currentIndexFont").text(data.pIndex+1);
		
		var replyHtml = Mustache.render($j("#tmpl").html(),data);
		cdiv.html(replyHtml);
		
		//翻页下拉框
		var selected = document.getElementById("pageSelect");
        //获取select下拉框下所有的选项
        var optionArr = selected.getElementsByTagName("option");
        var len = optionArr.length;
        for(var i=0;i<len;i++){
            //将其对应序号的option删除
        	selected.remove(optionArr[i]);
        }
		for(var i = 1 ; i <= data.pTotal ; i++)
		{
			selected.options.add(new Option(i+"",i));
		}
	});
}
function showRepPage(index)
{
	debugger;
	var pIndex = parseInt($j("#pIndexHide").val());
	var pTotal = parseInt($j("#pTotalHide").val());
	
	var target = 0;
	
	if(index == -1 && pIndex > 0){
		target = pIndex - 1;
	}
	else if(index == 1 && pIndex < pTotal - 1){
		target = pIndex + 1;
	}
	
	else{
		return;
	}
	showReply(target);
}
//跳页
function jumpPage(){
	var pageSelect = $j("#pageSelect").val();
	var pageText = $j("#pageText").val();
	var pageTotal = $j("#pTotalHide").val();
	var targetPage = 1 ;
	
	
	if(((parseInt(pageSelect) <= parseInt(pageTotal)&&parseInt(pageSelect)>0) 
			&& (parseInt(pageText) <= parseInt(pageTotal)&&parseInt(pageText)>0))
			|| (pageSelect == "" && (parseInt(pageText) <= parseInt(pageTotal))&&parseInt(pageText)>0)
			|| (pageText == "" && parseInt(pageSelect) <= parseInt(pageTotal))&&parseInt(pageSelect)>0)
	{
		if(pageSelect == "" || pageText != "")
		{
			targetPage = parseInt(pageText);
		}else if(pageSelect != "" || pageText == "")
		{
			targetPage = parseInt(pageSelect);
		}else if(pageSelect != "" || pageText != "")
		{
			targetPage = parseInt(pageText);
		}else{
			return;
		}
	}else{
		return;
	}
	
	showReply(targetPage-1);
	
}
//---------------------------------------------------------------------------------------------------------------------



//回复回复
function repReply(floor)
{
	var loginuser = $j("#loginMidHide").val();
	if(loginuser != 0)
	{
		$j("table[repReplyTableAttr]").eq(floor-2).removeClass("hideclass");
	}else
	{
		window.open("login.jsp?msg=请登录后再操作!");
	}
	
}
//隐藏回复回复框
function hiderepReply(floor)
{
	$j("table[repReplyTableAttr]").eq(floor-2).addClass("hideclass");
}






//回复删除确定
function repdelConfirm()
{
	$j("#repdelconfirmDiv").fadeIn(300);
	$j("#zz").fadeIn(300);
}
//取消删除
function repdelCancel()
{
	$j("#repdelconfirmDiv").fadeOut(300);
	$j("#zz").fadeOut(300);
}
//删除回复
function repdelete()
{
	var repno = $j('#repdelHide').val();
	var tip = $j("#tipDiv");
	tip.fadeIn(500);
	tip.css({"top":"380px","left":"800px"});
	tip.fadeOut(5000);
	
	$j.post("delReplyServlet",{"repno":repno},function(data){
		if(data.delrepjudge[0] == true)
		{
			debugger;
			tip.html("<font color='green'>√删除回复成功!</font>");
			showReply($j("#pIndexHide").val()); 
			$j("#repdelconfirmDiv").fadeOut(300);
			$j("#zz").fadeOut(300);
		}else {
			tip.html("<font color='red'>×系统出错,请重试!</font>");
		}
		
	});
	
}

//发表一级回复
function toPubReply()
{
	var content = UE.getEditor('editor').getContent()>5000? UE.getEditor('editor').getContent().substring(0,4999):UE.getEditor('editor').getContent();
	var nid = parseInt($j("#nidHide").val());
	var lastfloor = parseInt($j("#lastfloorHide").val());
	var pfloor = 1;
	
	var tip = $j("#tipDiv");
	tip.fadeIn(500);
	tip.css({"top":"380px","left":"800px"});
	tip.fadeOut(5000);
	
	$j.post("replyNoteServlet",{"content":content,"noteid":nid,"pfloor":pfloor,"lastfloor":lastfloor},function(data)
	{
		if(data.canaddexp == "canadd")
		{
			tip.html("<font color='green'>√回复成功!经验+2</font>");
		}else
		{
			tip.html("<font color='green'>√回复成功!</font>");
		}
		UE.getEditor('editor').setContent("");
		window.location.href = "showNoteInfoServlet?noteid="+$j("#nidHide").val()+"#";
		
		if(data.repjudge[0] == true)
		{
			
			showReply($j("#pIndexHide").val());
			$j("#replyContentta").val("");
		}else {
			tip.html("<font color='red'>×系统出错,请重试!</font>");
		}
		$j("#pubReplyDiv").fadeOut(500);
		$j("#zz").fadeOut(500);
		
	});
	
}

//发表楼中楼
function toRepReply(pfloor)
{
	debugger;
	var content = $j("textarea[repReplyTAAttr]").eq(pfloor-2).val();
	var nid = parseInt($j("#nidHide").val());
	var lastfloor = -1;
	
	var tip = $j("#tipDiv");
	tip.fadeIn(500);
	tip.css({"top":"380px","left":"800px"});
	tip.fadeOut(5000);
	
	$j.post("replyNoteServlet",{"content":content,"noteid":nid,"pfloor":pfloor,"lastfloor":lastfloor},function(data){
		if(data.repjudge[0] == true)
		{
			tip.html("<font color='green'>√回复成功!</font>");
			showReply($j("#pIndexHide").val());
			$j("#pubReplyDiv").fadeOut(500);
			$j("#zz").fadeOut(500);
			$j("textarea[repReplyTAAttr]").eq(pfloor-2).val("");
		}else {
			tip.html("<font color='red'>×系统出错,请重试!</font>");
		}
		
	});
}

//跳页正则
$j(function(){
	
	$j("#pageText").on("keyup",function(){
		var zz = new RegExp(/^\d$/);
		if(!zz.test($j("#pageText").val()))
		{
			$j("#pageText").val("");
		}
		if(event.keyCode == 32)
		{
			debugger;
			$j(this).val($j(this).val().replace(" ",""));
			
		}
	});
	
	
	//回复空正文判断
	$j("#replyContentta").blur(function(){
		if($j("#replyContentta").val() == "")
		{
			$j("#replyContentta").val("内容不能为空!!");
			$j("#replyContentta").css("color","lightgray");
			$j("#pubReplyBtn").attr("disabled","true");
			$j("#pubReplyBtn").css("background-color","gray");
		}else{
			$j("#pubReplyBtn").removeAttr("disablsed");
			$j("#pubReplyBtn").css("background-color","cornflowerblue");
		}
		
	});
	$j("#replyContentta").focus(function(){
		debugger;
		if($j("#replyContentta").val() == "内容不能为空!!")
		{
			$j("#pubReplyBtn").removeAttr("disabled");
			$j("#pubReplyBtn").css("background-color","cornflowerblue");
			$j("#replyContentta").css("color","black");
			$j("#replyContentta").val("");
		}
		
	});
	
});

//加精
function superNote(nid,eindex)
{
	var tip = $j("#tipDiv");
	tip.fadeIn(500);
	tip.css({"top":"380px","left":"800px"});
	tip.fadeOut(2000);
	
	$j.post("editNoteSupServlet",{"noteid":nid,"eindex":eindex},function(data)
	{
		if(data == "ok" && eindex == 1)
		{
			tip.html("<font color='green'>√ 标记精品成功!</font>");
			setTimeout("window.location.reload(true);", 2000);
		}else if(data == "ok" && eindex == 0)
		{
			tip.html("<font color='green'>√ 取消精品成功!</font>");
			setTimeout("window.location.reload(true);", 2000);
		}else{
			tip.html("<font color='red'>×系统出错,请重试!</font>");
		}
		
	});
}

//置顶
function topNote(nid,eindex)
{
	var tip = $j("#tipDiv");
	tip.fadeIn(500);
	tip.css({"top":"380px","left":"800px"});
	tip.fadeOut(2000);
	
	$j.post("editNoteTopServlet",{"noteid":nid,"eindex":eindex},function(data)
	{
		if(data == "ok" && eindex == 1)
		{
			tip.html("<font color='green'>√ 主题置顶成功!</font>");
			setTimeout("window.location.reload(true);", 2000);
		}else if(data == "ok" && eindex == 0)
		{
			tip.html("<font color='green'>√ 取消置顶成功!</font>");
			setTimeout("window.location.reload(true);", 2000);
		}else if(data == "no")
		{
			tip.html("<font color='red'>×系统出错,请重试!</font>");
		}else{
			tip.html("<font color='red'>"+data+"</font>");
		}
		

	});
	
	
}



//获取删除字体的位置
$j(function(){
	$j(".saNoteEditLabelClass").mouseover(function(){
		/*alert($j(".saNoteEditLabelClass").offset().left);
		alert($j(".saNoteEditLabelClass").offset().top);*/
		$j(".saNotecellulClass").css({"top":($j(".saNoteEditLabelClass").offset().top-155),"left":($j(".saNoteEditLabelClass").offset().left-26)});
	});
	
	//fixed div
	$j("#fixedDiv").css("left",$j("#noteDiv").offset().left+270);
});






























