<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registration</title>
<link rel="stylesheet" href="style.css">
<style>

    .toggle-icon {
    	width: 30px;
        position: absolute;
        right: 455px;
        top: 50%;
        transform: translateY(-50%);
        cursor: pointer;
    }
</style>
</head>
<body>
<h2>Sigh Up For Free</h2>
<div style="text-align: center">
	<form id="registerForm" method="post" action="register">
		<table class="table">
			<tr>
				<td class="td"><label for="userName">User Name:	</label></td>
				<td class="td"><input type="text" name="userName" id="userName" value="${messageModel.object.userName}"></td>
			</tr>
			<tr>
				<td class="td"><label for="firstName">First Name:	</label></td>
				<td class="td"><input type="text" name="firstname" id="firstName" value="${messageModel.object.firstName}"></td>
			</tr>
			<tr>
				<td class="td"><label for="lastName">Last Name:	</label></td>
				<td class="td"><input type="text" name="lastname" id="lastName" value="${messageModel.object.lastName}"></td>
			</tr>
			<tr>
				<td class="td"><label for="Email">Email:</label></td>
				<td class="td"><input type="text" name="email" id="Email" value="${messageModel.object.email}"></td>
			</tr>
			<tr>
				<td class="td"><label for="password">Password:	</label></td>
				<td class="td">
				  <input type="password" name="password" id="password" width="100px" value="${messageModel.object.hashedPassword}">
				  <img id="visibilityImg" alt="Toggle Visibility" src="image/visible.png" class="toggle-icon">
				  <button class="custom-button" type="submit" name="action" value="passwordGenerator">Generate Password</button>
				</td>
			</tr>
			<tr>
				<td class="td"><label for="password2">Confirm Password:</label></td>
				<td class="td"><input type="password" name="password2" id="password2" value="${messageModel.object.hashedPassword}"></td>
			</tr>
			<tr>
				<td colspan = "2" class="td-button">
					<span id="msg" style="font-size: 20px; color:red">${messageModel.message}</span><br>
					<button type="submit" id="submitBtn" name="action" value="submit">submit</button>
				</td>
			</tr>									
		</table>
			
		
	</form>
</div>
</body>
<script type="text/javascript" src="js/jquery-3.7.1.js"></script>
<!-- Include crypto-js from a CDN -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/crypto-js/4.0.0/crypto-js.min.js"></script>
<script src="Script.js"></script>
<script type="text/javascript">
	$("#submitBtn").click(function(){
		var username = $("#userName").val();
		var firstname = $("#firstName").val();
		var lastname = $("#lastName").val();
		var email = $("#Email").val();
		var password = $("#password").val();
		var password2 = $("#password2").val();
		if(isEmpty(username)){
			alert("Please enter your User Name! ");
			return;
		}
		if(isEmpty(firstname)){
//			$("#msg").html("Please enter your First Name!");
			alert("Please enter your First Name!");
			return;
		}
		if(isEmpty(lastname)){
//			$("#msg").html("Please enter your Last Name! ");
			alert("Please enter your Last Name!");
			return;
		}
		if(isEmpty(email)){
			alert("Please enter your Email!");
			return;
		}
		if(!checkEmail(email)){
			alert("Invalid Email format!");
			return;
		}
		if(isEmpty(password)){
			alert("Please enter your password!");
			return;
		}
		if(isEmpty(password2)){
			alert("Please enter your confirm password!");
			return;
		}
		if(!checkPassword(password,password2)){
			alert("The two passwords much match!");
			return;
		}
//		var hashedPassword = CryptoJS.SHA256(password).toString();

        // Create a new form data object to send the hashed password
/*        
        var formData = {
            password: hashedPassword;
            alert(hashedPassword);
        };
*/
		$("#registerForm").submit();
	});
	
	$(document).ready(function() {
        $('#visibilityImg').click(function() {
            var passwordField = $('#password');
            var visibilityImg = $('#visibilityImg');
            if (passwordField.attr('type') === 'password') {
                passwordField.attr('type', 'text');
                visibilityImg.attr('src', 'image/invisible.png'); // 图标切换为不可见
            } else {
                passwordField.attr('type', 'password');
                visibilityImg.attr('src', 'image/visible.png'); // 图标切换为可见
            }
        });
    });
</script>
</html>