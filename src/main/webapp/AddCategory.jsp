<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ page import="entity.User" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="style.css">
</head>

<body>
	<form action="addcategory" method="post" id="addForm">
		<table class="table">
			<tr>
				<td class="td">Category Name: </td>
				<td class="td"><input type="text" id="name" name="name" value=""></td>
			</tr>
			<tr>				
				<td colspan="2" class="td-button">
					<span id="msg" style="font-size: 12px; color:red">${messageModel.message}</span><br>
					<button type="button" class="custom-button" id="add">Add</button>
				</td>
			</tr>
		</table>
	</form>
</body>
<script type="text/javascript" src="js/jquery-3.7.1.js"></script>
<script src="Script.js"></script>
<script>
	$("#add").click(function(){
		var name = $("#name").val();
		if(isEmpty(name)){
			$("#msg").html("Please enter Category Name! ");
			return;
		}
		$("#addForm").submit();
	});
	
</script>
</html>