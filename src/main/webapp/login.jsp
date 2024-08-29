<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>MoneyDiary Login</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
<h2>Log In</h2>

<div style="text-align: center">
	<!-- action="/login" is a absolute path，action="login" is a relative path-->
	<form action="login" method="post" id="loginForm">
		<table class="table-flex">
			<tr>
				<td class="td">User Name: </td>			
				<!--${messageModel.object.userName} userName need to mathc User Object -->
				<td class="td"><input type="text" name="username" id="username" value="${messageModel.object.userName}"></td>
				
			</tr>
			<tr>
				<td class="td">Password : </td>
				<td class="td"><input type="password" name="password" id="password" value=""></td>
			</tr>
			<tr>
				<td colspan="2" class="td-button">
					<!-- ${messageModel.message} match getter，msg，getMessage（） here use message-->
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
<script src="js/Script.js"></script>
<script type="text/javascript">

	/*
		1. Bind a click event to the login button (using the ID selector).
		2. Get the values of the username and password.
		3. Check if the username is empty: If it is, prompt the user (set the value of a span element) and return.
		4. Check if the password is empty.
		5. If neither is empty, manually submit the form.
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