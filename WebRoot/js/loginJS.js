window.onload = function(){
			var usertext = document.getElementById("username");
			usertext.onfocus = function(){
				if(usertext.value == "论坛账号/手机号"){
					usertext.value = "";
					usertext.style.color = "black";
				}
			}
			usertext.onblur = function(){
				if(usertext.value == ""){
					usertext.value = "论坛账号/手机号";
					usertext.style.color = "lightgray";
				}
			}
			
			$j("input[type='text'],input[type='password']").on("keyup",function(){
				
				if(event.keyCode == 32)
				{
					debugger;
					$j(this).val($j(this).val().replace(" ",""));
					
				}
			});
		}
		
