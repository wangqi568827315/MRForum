//----------------------------------------------日期-------------------------------------------------------------

function YYYYMMDDstart(){
	MonHead = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];   
	
	//先给年下拉框赋内容   
	var y  = new Date().getFullYear();  
	for (var i = y; i > (y-140); i--) //以今年为准，前30年，后30年   
		   document.accountMsg_form.birth_year.options.add(new Option(i, i));   
	
	//赋月份的下拉框   
	for (var i = 1; i < 13; i++)   
		   document.accountMsg_form.birth_month.options.add(new Option(i, i));   
	
	
}  

	if(document.attachEvent)  window.attachEvent("onload", YYYYMMDDstart);   
	else   window.addEventListener('load', YYYYMMDDstart, false);  
	
function YYYYDD(str) //年发生变化时日期发生变化(主要是判断闰平年)   
{   
	var MMvalue = document.accountMsg_form.birth_month.options[document.accountMsg_form.birth_month.selectedIndex].value;   
	if (MMvalue == ""){ var e = document.accountMsg_form.birth_date; optionsClear(e); return;}   
	var n = MonHead[MMvalue - 1];   
	if (MMvalue ==2 && IsPinYear(str)) n++;   
	writeDay(n)   ;
}   
function MMDD(str)   //月发生变化时日期联动   
{   
	var YYYYvalue = document.accountMsg_form.birth_year.options[document.accountMsg_form.birth_year.selectedIndex].value;   
	if (YYYYvalue == ""){ var e = document.accountMsg_form.birth_date; optionsClear(e); return;}   
	var n = MonHead[str - 1];   
	if (str ==2 && IsPinYear(YYYYvalue)) n++;   
	writeDay(n);
}   
function writeDay(n)   //据条件写日期的下拉框   
{   
	var e = document.accountMsg_form.birth_date; optionsClear(e);   
	for (var i=1; i<(n+1); i++)   
	e.options.add(new Option(i, i));   
}   
function IsPinYear(year)//判断是否闰平年   
{
	return(0 == year%4 && (year%100 !=0 || year%400 == 0));
}   
function optionsClear(e)   
{   
	e.options.length = 1;   
}   

//----------------------------------------------日期-------------------------------------------------------------

//昵称不能有空格,是否已存在
$j(function()
{
	var flag = $j("#stateccnHide").val();
	if(flag == "false")
	{
		$j("#nicknameTip>td>label").css("display" , "none");
	}
	
	
	
	$j("#personal_nickname").blur(function(){
		
		if($j("#personal_nickname").val() != $j("#nicknameHide").val())
		{
			var nicknamezz = new RegExp(/^[\u4e00-\u9fa5A-Za-z0-9]*$/);
			if(!nicknamezz.test($j("#personal_nickname").val()))
			{
				$j("#nicknameTip>td>label").html("<font color='red'>× 昵称不合法!</font>");
				$j("#msgBtn_save").attr("disabled",true);
				$j("#msgBtn_save").css({"color":"gray","border":"1px solid gray"});
			}else {
				$j("#nicknameTip>td>label").html("<font color='green'>√ 昵称可用</font>");
				$j("#msgBtn_save").attr("disabled",false);
				$j("#msgBtn_save").css({"color":"#ff3300","border":"1px solid #ff3300"});
				
				$j.post("checkNicknameServlet",{"nickname":$j("#personal_nickname").val()},function(data){
					
					if(data.judgeSC[0] == false)
					{
						$j("#nicknameTip>td>label").html("<font color='red'>× 昵称已存在!</font>");
						$j("#msgBtn_save").attr("disabled",true);
						$j("#msgBtn_save").css({"color":"gray","border":"1px solid gray"});
					}else {
						$j("#nicknameTip>td>label").html("<font color='green'>√ 昵称可用</font>");
						$j("#msgBtn_save").attr("disabled",false);
						$j("#msgBtn_save").css({"color":"#ff3300","border":"1px solid #ff3300"});
					}
					
				});
				
			}
		}
		else {
			$j("#nicknameTip>td>label").html("(昵称只能修改一次,请三思!昵称不能有空格,特殊字符)");
			$j("#msgBtn_save").attr("disabled",false);
			$j("#msgBtn_save").css({"color":"#ff3300","border":"1px solid #ff3300"});
		}
	});
	
});

//信息编程可修改的
function editmsg()
{
	
	var flag = $j("#stateccnHide").val();

	//昵称
	if(flag == "true")
	{
		$j("#personal_nickname").removeAttr("readonly");
		$j("#personal_nickname").css("border","1px solid lightblue");
	}
	
	//签名档
	$j("#personal_QMD_ta").removeAttr("readonly");
	$j("#personal_QMD_ta").css("border","1px solid lightblue");
	
	//性别
	var msg_sexselect = document.getElementById("msg_sexselect");
	var optionArr = msg_sexselect.getElementsByTagName("option");
	var len = optionArr.length;
	for(var i=0;i<len;i++){
		msg_sexselect.remove(optionArr[i]);
	}
	msg_sexselect.options.add(new Option("男"));
	msg_sexselect.options.add(new Option("女"));
	msg_sexselect.options.add(new Option("保密"));
	msg_sexselect.value = document.getElementById("sexHide").value;
	$j("#msg_sexselect").removeClass("toggleHide");
	$j("#msg_sexselect").css("border","1px solid lightblue");
	$j("#personal_sex").addClass("toggleHide");
	
	
	//年龄
	var selected = document.getElementById("msg_ageselect");
	var optionArr = selected.getElementsByTagName("option");
	var len = optionArr.length;
	for(var i=0;i<len;i++){
		selected.remove(optionArr[i]);
	}
	for(var i = 0 ; i < 120 ; i++)
	{
		selected.options.add(new Option(i+"",i));
		selected.value = document.getElementById("ageHide").value;
	}
	$j("#msg_ageselect").removeClass("toggleHide");
	$j("#msg_ageselect").css("border","1px solid lightblue");
	$j("#personal_age").addClass("toggleHide");
	
	//生日
	$j("#birth_year").removeClass("toggleHide");
	$j("#birth_month").removeClass("toggleHide");
	$j("#birth_date").removeClass("toggleHide");
	$j("#birth_year").css("border","1px solid lightblue");
	$j("#birth_month").css("border","1px solid lightblue");
	$j("#birth_date").css("border","1px solid lightblue");
	$j("#personal_birthday").addClass("toggleHide");
	if($j("#birthdayHide").val() != "")
	{
		$j("#birth_year").val($j("#birthdayHide").val().split("-")[0]);
		$j("#birth_month").val($j("#birthdayHide").val().split("-")[1].replace("0",""));
		document.getElementById("birth_date").options.add(new Option($j("#birthdayHide").val().split("-")[2]));
		$j("#birth_date").val($j("#birthdayHide").val().split("-")[2]);
	}
	
	//地址
	$j("#personal_loc_sheng").removeClass("toggleHide");
	$j("#personal_loc_shi").removeClass("toggleHide");
	$j("#personal_loc_qu").removeClass("toggleHide");
	$j("#personal_loc_sheng").css("border","1px solid lightblue");
	$j("#personal_loc_shi").css("border","1px solid lightblue");
	$j("#personal_loc_qu").css("border","1px solid lightblue");
	$j("#personal_location").addClass("toggleHide");
	if($j("#locationHide").val() != "")
	{
		$j("#personal_loc_sheng").val($j("#locationHide").val().split("-")[0]);
		document.getElementById("personal_loc_shi").options.add(new Option($j("#locationHide").val().split("-")[1]));
		$j("#personal_loc_shi").val($j("#locationHide").val().split("-")[1]);
		document.getElementById("personal_loc_qu").options.add(new Option($j("#locationHide").val().split("-")[2]));
		$j("#personal_loc_qu").val($j("#locationHide").val().split("-")[2]);
	}
	
	//个人说明
	$j("#personal_explain").removeAttr("readonly");
	$j("#personal_explain").css("border","1px solid lightblue");
	
	//隐藏编辑按钮
	$j("#msgBtn_edit").addClass("toggleHide");
	//显示保存取消按钮
	$j("#msgBtn_save").removeClass("toggleHide");
	$j("#msgBtn_cancel").removeClass("toggleHide");
}

//取消编辑
function cancelEdit()
{
	//昵称
	$j("#personal_nickname").attr("readonly","readonly");
	$j("#personal_nickname").val($j("#nicknameHide").val());
	$j("#personal_nickname").css("border","1px solid white");
	
	//签名档
	$j("#personal_QMD_ta").attr("readonly","readonly");
	$j("#personal_QMD_ta").val($j("#QMDHide").val());
	$j("#personal_QMD_ta").css("border","1px solid lightgray");
	
	//性别
	$j("#msg_sexselect").addClass("toggleHide");
	$j("#personal_sex").val($j("#sexHide").val());
	$j("#personal_sex").removeClass("toggleHide");
	
	//年龄
	$j("#msg_ageselect").addClass("toggleHide");
	$j("#personal_age").val($j("#ageHide").val());
	$j("#personal_age").removeClass("toggleHide");
	
	//生日
	$j("#birth_year").addClass("toggleHide");
	$j("#birth_month").addClass("toggleHide");
	$j("#birth_date").addClass("toggleHide");
	$j("#personal_birthday").removeClass("toggleHide");
	$j("#personal_birthday").val($j("#birthdayHide").val());
	
	
	//地址
	$j("#personal_loc_sheng").addClass("toggleHide");
	$j("#personal_loc_shi").addClass("toggleHide");
	$j("#personal_loc_qu").addClass("toggleHide");
	$j("#personal_location").removeClass("toggleHide");
	$j("#personal_location").val($j("#locationHide").val());
	
	
	//个人说明
	$j("#personal_explain").attr("readonly","readonly");
	$j("#personal_explain").val($j("#explainHide").val());
	$j("#personal_explain").css("border","1px solid lightgray");
	
	//显示编辑按钮
	$j("#msgBtn_edit").removeClass("toggleHide");
	//隐藏保存取消按钮
	$j("#msgBtn_save").addClass("toggleHide");
	$j("#msgBtn_cancel").addClass("toggleHide");
}


//保存编辑
function saveEdit()
{
	$j("#editconfirmDiv").fadeIn(300);
	$j("#zz").fadeIn(300);
}
//取消保存编辑
function editCancel()
{
	$j("#editconfirmDiv").fadeOut(300);
	$j("#zz").fadeOut(300);
}
//保存编辑确定
function editConfirm()
{
	var mid = $j("#loginmidHide").val();
	if(mid != 0)
	{
		var stateccn = $j("#personal_nickname").val()==$j("#nicknameHide").val()? 1 : 0;
		var nickname = $j("#personal_nickname").val();
		var QMD = $j("#personal_QMD_ta").val();
		var sex = $j("#msg_sexselect").val();
		var age = $j("#msg_ageselect").val();
		
		var birth_mm = (parseInt($j("#birth_month").val())&&($j("#birth_month").val().indexOf("0")>0))<10? ("0"+$j("#birth_month").val()) : $j("#birth_month").val();
		var birth_dd = (parseInt($j("#birth_date").val())<10&&($j("#birth_date").val().indexOf("0")>0))? ("0"+$j("#birth_date").val()) : $j("#birth_date").val();
		var birthday = $j("#birth_year").val()+"-"+birth_mm+"-"+birth_dd;
		
		var location = $j("#personal_loc_sheng").val()+"-"+$j("#personal_loc_shi").val()+"-"+$j("#personal_loc_qu").val();
		var explain = $j("#personal_explain").val();
		//editMsgServlet
		
		var tip = $j("#tipDiv");
		tip.fadeIn(300);
		tip.css({"top":"380px","left":"800px"});
		tip.fadeOut(2000);
		
		$j.post("editMsgServlet",
				{"nickname":nickname,"QMD":QMD,"sex":sex,"age":age,"stateccn":stateccn,
						"birthday":birthday,"location":location,"explain":explain,"mid":mid},
				function(data){
			if(data.flag[0] == true)
			{
				tip.html("<font color='green'>√ 修改信息成功!</font>");
				setTimeout("window.location.href = 'showMsgServlet?mid='+$j('#loginmidHide').val();", 2000);
			}else {
				tip.html("<font color='red'>× 系统出错,请重试!</font>");
			}
			$j("#editconfirmDiv").fadeOut(500);
			$j("#zz").fadeOut(500);
		});
	}else {
		window.open("login.jsp?msg=请登录后再进行操作!");
	}
	
	
}

//上传头像
//-------------------------------------------------------------------------------------------------------------
//显示上传div
function touploadIcon()
{
	$j(".container").fadeIn(300);
	$j("#zz").fadeIn(300);
}
//取消上传头像
function canceluploadIcon()
{
	$j(".container").fadeOut(300);
	$j("#zz").fadeOut(300);
}

$j(function() {

	var options = {
		thumbBox: '.thumbBox',
		spinner: '.spinner',
		imgSrc: 'img/DEFAULTICON/defaultIcon.png'  //初始图片 
	};
	//选择图片
	var cropper = $j('.imageBox').cropbox(options);
	$j('#upload-file').on('change', function() {
		var reader = new FileReader();
		reader.onload = function(e) {
			options.imgSrc = e.target.result;
			cropper = $j('.imageBox').cropbox(options);
		}
		reader.readAsDataURL(this.files[0]);
		this.files = [];
	});
	//点击裁切按钮
	$j('#btnCrop').on('click', function() {
		var img = cropper.getDataURL();
		$j('.cropped').html('');
		$j('.cropped').append('<img src="' + img + '" align="absmiddle" style="width:64px;margin-top:4px;border-radius:64px;box-shadow:0px 0px 12px #7E7E7E;" ><p>64px*64px</p>');
		$j('.cropped').append('<img src="' + img + '" align="absmiddle" style="width:128px;margin-top:4px;border-radius:128px;box-shadow:0px 0px 12px #7E7E7E;"><p>128px*128px</p>');
		$j('.cropped').append('<img src="' + img + '" align="absmiddle" style="width:180px;margin-top:4px;border-radius:180px;box-shadow:0px 0px 12px #7E7E7E;"><p>180px*180px</p>');
	});
	//点击上传按钮
	$j('#btnUploadImg').on('click', function() {
		var img = cropper.getDataURL();
		
		var tip = $j("#tipDiv");
		tip.fadeIn(300);
		tip.css({"top":"380px","left":"800px"});
		tip.fadeOut(2000);
		//ajax上传开始
		$j.post("uploadIconServlet",{"imgdata":img},function(data)
		{
			
			if(data == "ok")
			{
				tip.html("<font color='green'>√ 头像上传成功!</font>");
				setTimeout("window.location.reload(true)", 2000);
			}else {
				tip.html("<font color='red'>× 系统出错,请重试!</font>");
			}
			
			$j(".container").fadeOut(300);
			$j("#zz").fadeOut(300);
		});
		
	});
	
	
	
	
	$j('#btnZoomIn').on('click', function() {
		cropper.zoomIn();
	});
	$j('#btnZoomOut').on('click', function() {
		cropper.zoomOut();
	});

});

//-----------------------------------------------------------------------------------------------------------------------


//分页显示自己的帖子----------------------------------------------------------------------------------------------------
$j(function()
{
	var pIndex = parseInt($j("#pIndexHide").val());
	showMyNote(pIndex);
	
});
function showMyNote(pIndex)
{
	var mid = $j("#showmsgmidHide").val();
	$j.post("msgNotePageServlet",{"pIndex":pIndex,"mid":mid},function(data)
	{
		var cdiv = $j("#noteMsgDiv");
		$j("#pIndexHide").val(data.pIndex);
		$j("#pTotalHide").val(data.pTotal);
		$j("#currentIndexFont").text(data.pIndex+1);
		
		var notemsgHtml = Mustache.render($j("#tmpl").html(),data);
		cdiv.html(notemsgHtml);
		
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
	showMyNote(target);
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
	
	showMyNote(targetPage-1);
	
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
	
});
	
//-----------------------------------------------------------------------------------------------------------------------














