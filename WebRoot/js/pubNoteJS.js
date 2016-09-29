//窗口大小改变  改变fixed位置
$j(window).resize(function(){
	$j("#fixedDiv").css("left",$j("#bodyRightDiv").offset().left+270);
});


//分页以及....
$j(function(){
	//bodyRightDiv
	/*fix框的位置*/
	$j("#fixedDiv").css("left",$j("#bodyRightDiv").offset().left+270);
	
	
	//
	
	$j.post("showIndexServlet",function(data)
	{
		var btn = $j("#signin_btn");
		
		if(data == "cansignin")
		{
			
			btn.css("background","rgba(65,150,240,0.7)");
			btn.val("签  到");
			btn.attr("disabled",false);
		}else if(data == "cannontsign")
		{
			btn.css("background","rgba(60,60,60,1)");
			btn.val("已签到");
			btn.attr("disabled",true);
		}
		
	});
	
	var pIndex = $j("#pIndexHide").val();
	AjaxData(pIndex,0);
	showManagers();
	showTime();
	
	$j("#pubnotetitle").on("keyup",function(){
		if(event.keyCode == 32)
		{
			$j(this).val($j(this).val().replace(" ",""));
		}
	});
	
});

function showTime(){
	var myDate = new Date();
	var myYear = myDate.getFullYear();
	var myMonth = myDate.getMonth() + 1;
	var myDay = myDate.getDate();
	$j("#datelabel > label").html("&nbsp;"+myMonth+"-"+myDay+"<br>"+myYear+"年");
}	


function showManagers()
{
	$j.post("showManagerServlet",function(data)
	{
		for(var i = 0 ; i < data.micon.length ; i++)
		{
			$j("#micon"+(i+1)).css("background-image","url("+data.micon[i]+")");
			var url = "showMsgServlet?mid="+data.mid[i];
			$j("#mname"+(i+1)).text(data.mname[i]);
			$j("a.manageraclass").eq(i).attr({"href":url,"target":"blank"});
			
			$j("#micon"+(i+1)).click(function(){
				var url = $j(this).siblings(".manageraclass").attr("href");
				window.open(url);
			});
		}
	});
}


function AjaxData(pIndex,typeIndex)
{
	$j("#pTypeHide").val(typeIndex);

	$j.post("notePageServlet",{"pIndex":pIndex,"tIndex":typeIndex},function(data)
	{
		var Contentdiv = $j("div.bodyNote");
		
		$j("#pIndexHide").val(data.pIndex);
		$j("#pTotalHide").val(data.pTotal);
		$j("#currentIndexFont").text(data.pIndex+1);
		
		$j("ul.NoteUl > li").css("color",data.pubNameColor);
		
		var tableHtml = Mustache.render($j("#tmpl").html(), data);
		Contentdiv.html(tableHtml);
		
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


function showNotePage(index)
{
	var pIndex = parseInt($j("#pIndexHide").val());
	var pTotal = parseInt($j("#pTotalHide").val());
	var pType = $j("#pTypeHide").val();
	
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
	AjaxData(target,pType);
}

function tologin(){
	window.open("login.jsp?msg=请登录后再操作!");
}

//签到加经验
function signin(mid){
	if(mid == "0"){
		tologin();
	}else{
		
		var div = $j("#tipDiv");
		div.fadeIn(500);
		div.css({"top":"230px","left":"510px","width":"150px"});
		div.fadeOut(4000);
		
		$j.post("signinServlet",{"mid":parseInt(mid)},function(data){
			if(data == "ok")
				{
					var btn = $j("#signin_btn");
					btn.css("background","rgba(60,60,60,1)");
					btn.val("已签到");
					div.html("<font color='green'>√签到成功!经验+5</font>");
					$j("#expcountLabel").text(parseInt($j("#expcountLabel").text())+5);
					btn.attr("disabled",true);
				}else {
					div.html("<font color='red'>×系统出错,请重试!</font>");
				}
			
		});
	}
}



	//发帖
	function memPubNote()
	{
		//UE.getEditor('editor').getContent()
		if($j("#pubnotetitle").val() == "")
		{
			$j("#pubNoteDiv4>label").html("<font color='red'>×标题不能为空!!!</font>");
			
			return;
		}
		else {
			var title = $j("#pubnotetitle").val();
			var article = UE.getEditor('editor').getContent().length>5000?UE.getEditor('editor').getContent().substring(0,4999):UE.getEditor('editor').getContent();
			
			var div = $j("#tipDiv");
			div.fadeIn(500);
			div.css({"top":"450px","left":"870px"});
			div.fadeOut(4000);
			
			$j.post("pubNoteServlet",{"title":title,"article":article},function(data)
			{
				if(data.canaddexp == "canadd")
				{
					div.html("<font color='green'>√发表成功!经验+5</font>");
				}else {
					div.html("<font color='green'>√发表成功!</font>");
				}
				UE.getEditor('editor').setContent("");
				$j("#pubNoteDiv4>label").html("");
				window.location.href = "#";
				
				if(data.pubed[0] == true)
				{
					$j("#pubNoteDiv").fadeOut(700);
					$j("#zz").fadeOut(700);
					AjaxData(0,0);
					$j("#pubnotetitle").val("");
					$j("#pubnotearticle").val("");
				}else {
					div.html("<font color='red'>×系统出错,请重试!</font>");
				}
				
				
			});
		}
		
		
		
	}


	//跳页
	function jumpPage(){

		var pageSelect = $j("#pageSelect").val();
		var pageText = $j("#pageText").val();
		var pageTotal = $j("#pTotalHide").val();
		var pageType = $j("#pTypeHide").val();
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
		
		
		AjaxData(targetPage-1,pageType);
	}




function pubNote(){
	$j("#pubNoteDiv").fadeIn(700);
	$j("#zz").fadeIn(700);
	$j("#pubnotetitle").focus();
}

function closepubNote(){
	$j("#pubNoteDiv").fadeOut(700);
	$j("#zz").fadeOut(700);
	$j("#pubnotetitle").val("");
	$j("#pubnotearticle").val("");
}



//图片自动切换
var g=2;
var interId;
function hoverLi(idx)
{
	if(idx !=undefined)  g = idx;
	if(g==6)  g=1;
	
	for(var i=1;i<6;i++)
	{
		if(i == g)
		{
			var src1 = "url(img/indexIMG/CouDivImg/couDivImg"+i+".jpg)";
 			$j("#mainCouImgDivImg").css("backgroundImage",src1);
			
			$j("#couImgli_"+i).attr("class","mainCouImgScrollUlhover");
		}
		else
		{
			$j("#couImgli_"+i).removeAttr("class","mainCouImgScrollUlhover");
		}
	}
	g++;
}


$j(function()
{
	interId = setInterval(hoverLi,2000);
});

function UserhoverLi(idex)
{
	clearInterval(interId);
	hoverLi(idex);
}

function UserOut()
{
	interId = setInterval(hoverLi,2000);
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
			$j(this).val($j(this).val().replace(" ",""));
			
		}
	});
	
	//发帖空标题正文判断
	$j("#pubnotetitle").blur(function(){
		debugger;
		$j("#pubnotetitle").val($j("#pubnotetitle").val().replace(" ",""));
		if($j("#pubnotetitle").val().replace(" ","") == "")
		{
			$j("#pubnotetitle").val("标题不能为空!!");
			$j("#pubnotetitle").css("color","lightgray");
			$j("#pubNoteBtn").attr("disabled","true");
			$j("#pubNoteBtn").css("background-color","gray");
		}else{
			$j("#pubNoteBtn").removeAttr("disabled");
			$j("#pubNoteBtn").css("background-color","cornflowerblue");
		}
		
	});
	$j("#pubnotetitle").focus(function(){
		if($j(this).val() == "标题不能为空!!")
		{
			$j("#pubNoteBtn").removeAttr("disabled");
			$j("#pubNoteBtn").css("background-color","cornflowerblue");
			$j("#pubnotetitle").css("color","black");
			$j("#pubnotetitle").val("");
		}
		
	});
	
	//发帖空标题正文判断
	$j("#pubnotearticle").blur(function(){
		if($j("#pubnotearticle") == "")
		{
			$j("#pubnotearticle").val("正文不能为空!!");
			$j("#pubnotearticle").css("color","lightgray");
			$j("#pubNoteBtn").attr("disabled","true");
			$j("#pubNoteBtn").css("background-color","gray");
		}else{
			$j("#pubNoteBtn").removeAttr("disabled");
			$j("#pubNoteBtn").css("background-color","cornflowerblue");
		}
		
	});
	$j("#pubnotearticle").focus(function(){
		if($j(this).val() == "正文不能为空!!")
		{
			$j("#pubNoteBtn").removeAttr("disabled");
			$j("#pubNoteBtn").css("background-color","cornflowerblue");
			$j("#pubnotearticle").css("color","black");
			$j("#pubnotearticle").val("");
		}
		
	});
	
	
	
});
	
	
	
	
	
	
	
	
	
	





			