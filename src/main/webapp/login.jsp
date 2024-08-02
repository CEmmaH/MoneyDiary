<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>MoneyDiary Login</title>
<link rel="stylesheet" href="style.css">
</head>
<body>
<h2>Log In</h2>
<div style="text-align: center">
	<!-- action="/login" 为绝对路径，action="login"为相对路径-->
	<form action="login" method="post" id="loginForm">
		<table class="table">
			<tr>
				<td class="td">User Name: </td>
				<!--${messageModel.object.userName} 中的userName 需要与User对象中一致 -->
				<td class="td"><input type="text" name="username" id="username" value="${messageModel.object.userName}"></td>
			</tr>
			<tr>
				<td class="td">Password : </td>
				<td class="td"><input type="password" name="password" id="password" value=""></td>
			</tr>
			<tr>
				<td colspan="2" class="td-button">
					<!-- ${messageModel.message} 需要与getter对应，变量为msg，getMessage（） 需要用message-->
					<span id="msg" style="font-size: 12px; color:red">${messageModel.message}</span><br>
					<button type="button" id="loginBtn">Login</button>
					<button type="button" id="register">Sign Up</button>
				</td>
			</tr>
		</table>		
	</form>
</div>
</body>
<%--import jQuery --%>
<script type="text/javascript" src="js/jquery-3.7.1.js"></script>
<script src="Script.js"></script>
<script type="text/javascript">
	/**
	 *  1. 给登录按钮绑定点击事件（通过id选择器绑定）
	 	2. 获取用户用户名和密码的值
	 	3. 判断姓名是否为空： 如果为空提示用户（span标签赋值）， 并return	 		
	 	4. 判断密码是否为空：
	 	5. 不为空，手动提交表单
	*/
	$("#loginBtn").click(function(){
		//through id to get the values
		var username = $("#username").val();
		var password = $("#password").val();
		if(isEmpty(username)){
			$("#msg").html("Please enter the Username!");
			return;
		}
		if(isEmpty(password)){
			$("#msg").html("Please enter the Password!");
			return;
		}
		$("#loginForm").submit();
	});


	
	$("#register").click(function(){
		register();
	});	
/**	
 * 	Method 2:
	document.getElementById("register").addEventListener("click", function() {
	    register();
	});
*/
	function register(){
		window.location.href = "Register.jsp";
	}

</script>
</html>