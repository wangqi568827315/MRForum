<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>

    <title>404error</title>
   
   <style>
	#mainDiv{
		width:1024px;
		height: 250px;
		margin:0px auto;
		margin-top: 200px;
		background: url("img/404error.jpg") -240px -80px;
	}
	#tipdiv{
		width: 500px;
		height: 250px;
		margin-left: 470px;
	}
	label{
		font-size: 18px;
		font-family: '微软雅黑';
		color:royalblue;
		line-height: 150px;
	}
	input{
		width: 130px;
		height: 35px;
		color: white;
		font-size:18px;
		display:block;
		border:none;
		border-radius:5px;
		box-shadow: 0px 0px 5px #90EE90;
		background-color: #87CEFA;
	}

</style>
   
   
  </head>
  
  <body>
   <div id="mainDiv">
   	<div id="tipdiv">
   		<label>哦哟,页面不小心丢了!可能是被删除了!</label>
   		<input type="button"  value="返回首页" onclick="window.location.href='indexRefreshServlet'"/>
   	</div>
   
   
   </div>
  
   
   
   
   
  </body>
</html>
