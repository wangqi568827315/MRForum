
			$j(function(){
				$j("#changepwd_account").focus();
				
				$j("input[type='text'],input[type='password']").on("keyup",function(){
				
				if(event.keyCode == 32)
				{
					debugger;
					$j(this).val($j(this).val().replace(" ",""));
					
				}
				});
			});


			function checkchangepwd(){
				
//				正则表达式
				var reg_pwd = new RegExp(/^[a-z]{1}\S{5,15}$/i);
				var reg_repwd = RegExp(/^\S{6,16}$/);
				 
				
//				判断条件变量
				var pwd_judge = 0;
				var repwd_judge = 0;

				
//				验证新密码
				if(reg_pwd.test(changepwd_newpwd.value)){
					pwd_judge = 1;
				}else{
					pwd_judge = 0;
				}
				
//				确认密码
				if((changepwd_newpwd.value != changepwd_renewpwd.value) || (!reg_repwd.test(changepwd_renewpwd.value))){
					repwd_judge = 0;
				}else{
					repwd_judge = 1;
				}
				

			
				
				//判断是否跳转
				if((pwd_judge == 1)&&(repwd_judge == 1)&&($j("#changepwd_oldpwd").val()!="" || $j("#changepwd_safepwd").val()!="")){
				    return true;
				}else{
					return false;
				}
			}
			
			//检查账号
			function checkAccount(){
				var changepwd_account = document.getElementById("changepwd_account");
				var cpspan_account = document.getElementById("cpspan_account");
				
				$j.post("registCheckAccountServlet",{"account":changepwd_account.value},function(data)
						{
							if(data.exist[0] == true)
							{
								cpspan_account.innerText = "√";
								cpspan_account.style.color = "green";
								$j("#main_submit").css("background-color","#ff3300");
								$j("#main_submit").attr("disabled",false);
								
							}else
							{
								$j("#main_submit").css("background-color","gray");
								$j("#main_submit").attr("disabled",true);
								cpspan_account.innerText = "× 账号不存在!!";
								cpspan_account.style.color = "red";
								
							}
							
							
						});
				
			
			}

			//检查原来的密码
			function checkOldPwd()
			{
				var changepwd_oldpwd = $j("#changepwd_oldpwd");
				var changepwd_account = $j("#changepwd_account");
				if(changepwd_oldpwd.val() != "")
				{
					$j.post("checkOldPwdServlet",{"oldpwd":changepwd_oldpwd.val(),"account":changepwd_account.val()},function(data)
					{
						if(data.judgeAU[0] == true)
						{
							$j("#main_submit").css("background-color","#ff3300");
							$j("#main_submit").attr("disabled",false);
							$j("#cpspan_oldpwd").text("√");
							$j("#cpspan_oldpwd").css("color","green");
						}else
						{
							$j("#main_submit").css("background-color","gray");
							$j("#main_submit").attr("disabled",true);
							$j("#cpspan_oldpwd").text("× 密码不正确!");
							$j("#cpspan_oldpwd").css("color","red");
						}
					});
				}
			}
			
			//检查安全码
			function checkSafeCode(){
				var changepwd_oldpwd = $j("#changepwd_oldpwd");
				var changepwd_safepwd = $j("#changepwd_safepwd");
				var changepwd_account = $j("#changepwd_account");
				
				if(changepwd_oldpwd.val() == "")
				{
					$j.post("checkSafeCodeServlet",{"account":changepwd_account.val(),"safecode":changepwd_safepwd.val()},function(data){
						debugger;
						if(data.judgeSC[0] == true)
						{
							$j("#main_submit").css("background-color","#ff3300");
							$j("#main_submit").attr("disabled",false);
							$j("#cpspan_safepwd").text("√");
							$j("#cpspan_safepwd").css("color","green");
						}else
						{
							$j("#main_submit").css("background-color","gray");
							$j("#main_submit").attr("disabled",true);
							$j("#cpspan_safepwd").text("× 安全码不正确!");
							$j("#cpspan_safepwd").css("color","red");
						}
						
					});
				}
				
			}
			
			
			//检查密码
			function checkPwd(){
				var reg_pwd = new RegExp(/^[a-z]{1}\S{5,15}$/i);
				var changepwd_newpwd = document.getElementById("changepwd_newpwd");
				var cpspan_newpwd = document.getElementById("cpspan_newpwd");
				
				
				if(reg_pwd.test(changepwd_newpwd.value)){
					cpspan_newpwd.innerText = "√";
					cpspan_newpwd.style.color = "green";
				}else{
					cpspan_newpwd.innerText = "× 6-16位，首字母必须是字母,不能包含空格!";
					cpspan_newpwd.style.color = "red";
				}
			}
			
			//检查确认密码
			function checkRePwd(){
				var reg_repwd = RegExp(/^\S{6,16}$/);
				var changepwd_newpwd = document.getElementById("changepwd_newpwd");
				var changepwd_renewpwd = document.getElementById("changepwd_renewpwd");
				var cpspan_renewpwd = document.getElementById("cpspan_renewpwd");
			
				if((changepwd_newpwd.value != changepwd_renewpwd.value) || (!reg_repwd.test(changepwd_renewpwd.value))){
					cpspan_renewpwd.innerText = "× 两次输入的密码不一样！";
					cpspan_renewpwd.style.color = "red";
				}else{
					cpspan_renewpwd.innerText = "√";
					cpspan_renewpwd.style.color = "green";
				}
			}
			
			
			
			function showSuccess()
			{	
				debugger;
				if(checkchangepwd()){
					var tip = $j("#tipDiv");
					tip.fadeIn(500);
					tip.css({"top":"380px","left":"800px"});
					tip.fadeOut(5000);
					
					tip.html("<font color='green'>√修改成功!五秒后跳转到登录页面...</font>");
				}
				
				
			}
			
			
			
			
			
			
			
			
			
			
		

