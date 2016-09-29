			window.onload = function(){
				var account = document.getElementById("msg_account");
				account.focus();
				
				$j("input[type='text'],input[type='password']").on("keyup",function(){
				
				if(event.keyCode == 32)
				{
					debugger;
					$j(this).val($j(this).val().replace(" ",""));
					
				}
				});
				
			}
			function checkMsg(){
				
//				正则表达式
				var reg_account = new RegExp(/^[a-z]{1}[a-z0-9]{5,15}$/i);
				var reg_pwd = new RegExp(/^[a-z]{1}\S{5,15}$/i);
				var reg_repwd = RegExp(/^\S{6,16}$/);
				var reg_phone = new RegExp(/^1[34578]\d{9}$/);
				var reg_safepwd = RegExp(/^[a-z0-9]{6,16}$/i);
				
				
//				判断条件变量
				var account_judge = 0;
				var pwd_judge = 0;
				var repwd_judge = 0;
				var phone_judge = 0;
				var safepwd_judge = 0;

				//检查账号
				if(reg_account.test(msg_account.value)){
					account_judge = 1;
				}else{
					account_judge = 0;
				}
//				验证密码
				if(reg_pwd.test(msg_pwd.value)){
					pwd_judge = 1;
				}else{
					pwd_judge = 0;
				}
				
//				确认密码
				if((msg_pwd_check.value != msg_pwd.value) || (!reg_repwd.test(msg_pwd_check.value))){
					repwd_judge = 0;
				}else{
					repwd_judge = 1;
				}
				

				//验证电话号码
				if(reg_phone.test(msg_phone.value)){
					phone_judge = 1;
				}else{
					phone_judge = 0;
				}

	
				//验证安全码
				if(reg_safepwd.test(msg_safepwd.value)){
					safepwd_judge = 1;
				}else{
					safepwd_judge = 0;
				}
				
				
				//判断是否跳转
				if((account_judge == 1)&&(pwd_judge == 1)&&(repwd_judge == 1)&&(phone_judge == 1)&&(safepwd_judge == 1)){
				    return true;
				}else{
					return false;
				}
			}
			
			//检查账号
			function checkAccount(){
				var reg_account = new RegExp(/^[a-z]{1}[a-z0-9]{5,15}$/i);
				var msg_account = document.getElementById("msg_account");
				var span_account = document.getElementById("span_account");
				
				$j.post("registCheckAccountServlet",{"account":msg_account.value},function(data)
				{
					if(data.exist[0] == true)
					{
						span_account.innerText = "× 账号已存在!!";
						span_account.style.color = "red";
						$j("#regist_submit").css("background-color","gray");
						$j("#regist_submit").attr("disabled",true);
					}else
					{
						$j("#regist_submit").css("background-color","#ff3300");
						$j("#regist_submit").attr("disabled",false);
						if(reg_account.test(msg_account.value)){
							span_account.innerText = "√";
							span_account.style.color = "green";
						}else{
							span_account.innerText = "× 6-16位,第一位必须是字母,只能由字母数字组成!";
							span_account.style.color = "red";
						}
					}
					
					
				});
			}
			
			
			
			//检查密码
			function checkPwd(){
				var reg_pwd = new RegExp(/^[a-z]{1}\S{5,15}$/i);
				var msg_pwd = document.getElementById("msg_pwd");
				var span_pwd = document.getElementById("span_pwd");
				
				if(reg_pwd.test(msg_pwd.value)){
					span_pwd.innerText = "√";
					span_pwd.style.color = "green";
				}else{
					span_pwd.innerText = "× 6-16位，首字符必须是字母,不能包含空格!";
					span_pwd.style.color = "red";
				}
			}
			
			//检查确认密码
			function checkRePwd(){
				var reg_repwd = RegExp(/^\S{6,16}$/);
				var msg_pwd = document.getElementById("msg_pwd");
				var msg_pwd_check = document.getElementById("msg_pwd_check");
				var span_pwd_check = document.getElementById("span_pwd_check");
			
				if((msg_pwd_check.value != msg_pwd.value) || (!reg_repwd.test(msg_pwd_check.value))){
					span_pwd_check.innerText = "× 两次输入的密码不一样！";
					span_pwd_check.style.color = "red";
				}else{
					span_pwd_check.innerText = "√";
					span_pwd_check.style.color = "green";
				}
			}
			
			//检查电话号码
			function checkPhone(){
				var msg_phone = document.getElementById("msg_phone");
				var span_phone= document.getElementById("span_phone");
				var reg_phone = new RegExp(/^1[34578]\d{9}$/);
				
				$j.post("registCheckPhoneServlet",{"phone":msg_phone.value},function(data)
						{
							if(data.exist[0] == true)
							{
								span_phone.innerText = "× 该号码已被注册!!";
								span_phone.style.color = "red";
								$j("#regist_submit").css("background-color","gray");
								$j("#regist_submit").attr("disabled",true);
							}else
							{
								$j("#regist_submit").css("background-color","#ff3300");
								$j("#regist_submit").attr("disabled",false);
								if(reg_phone.test(msg_phone.value)){
									span_phone.innerText = "√";
									span_phone.style.color = "green";
								}else{
									span_phone.innerText = "× 请填写正确的电话号码！";
									span_phone.style.color = "red";
								}
							}
					});
			}
			
			//检查安全码
			function checkSafepwd(){
				var msg_safepwd = document.getElementById("msg_safepwd");
				var span_safepwd = document.getElementById("span_safepwd");
				var reg_safepwd = RegExp(/^[a-z0-9]{6,16}$/i);
				
				if(reg_safepwd.test(msg_safepwd.value)){
					span_safepwd.innerText = "√";
					span_safepwd.style.color = "green";
				}else{
					span_safepwd.innerText = "× 6-16位,用于找回密码,以数字和字母组成";
					span_safepwd.style.color = "red";
				}
			}

		
function showSuccess()
{	
	debugger;
	if(checkMsg()){
		var tip = $j("#tipDiv");
		tip.fadeIn(500);
		tip.css({"top":"380px","left":"800px"});
		tip.fadeOut(5000);
		
		tip.html("<font color='green'>√注册成功!五秒后跳转到登录页面...</font>");
	}
	
	
}











