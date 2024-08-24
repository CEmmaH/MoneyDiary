<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="entity.User"%>
<%@ page import="java.util.List" %>
<%@ page import="entity.Category" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Insert title here</title>
	<link rel="stylesheet" href="css/style.css">
	<script type="text/javascript" src="js/jquery-3.7.1.js"></script>
	<script src="js/Script.js" defer></script>
</head>
<%
	
    if (session == null) {
    	response.sendRedirect("login.jsp"); // Session is invalid, redirecting to login page
        return; 
    }
    // get UserId
    User user = (User)session.getAttribute("user");
    if (user == null || user.getId()==null) {
    	response.sendRedirect("login.jsp"); // Session is invalid, redirecting to login page
        return;
     }
    
    List<Category> categories = (List<Category>)session.getAttribute("eCategory");

%>
<body>
	<header>
		<h2>Welcome ${user.firstName} ${user.lastName} </h2>
	</header>

	<div class="div-container">
    <div class="tabs">
        <div class="tab active" data-tab="tab_Expense_Trans">Expenses Transaction</div>
        <div class="tab" data-tab="tab_Income_Trans">Income Transaction</div>
        <div class="tab" data-tab="tab_Invest">Investment</div>
        <div class="tab" data-tab="tab_Expense_Report">Expenses Report</div>
        <div class="tab" data-tab="tab_Income_Report">Income Report</div>
        <div class="tab" data-tab="tab_Update_passwd">Update Password</div>
        <div class="tab" data-tab="tab_Manage">Manage Category</div>
    </div>

    <div id="tab_Expense_Trans" class="tab-content active">
       <table class="table">
       	  <tr>
       		<th colspan ="4">Expenses Transaction</th>
       	  </tr>
       	  <tr>
       	  	<td class="td">Name</td>
       	  	<td class="td"><input id="name" type="text"></td>
       	  	<td class="td">Amount</td>
       	  	<td class="td"><input id="" type="text"></td>
       	  </tr>
       	  <tr>
       	  	<td class="td">Category</td>
       	  	<td class="td">
       	  		<select id="category" name="category_select">
       	  			<option value="">Choose a Category</option>
       	  			<%
       	  				if(categories!=null && categories.size()>0){
       	  					for(int i = 0; i < categories.size(); i++){
       	  			%>
       	  				<option value="<%=categories.get(i).getId()%>"><%= categories.get(i).getName() %></option>
       	  			<% }}%>
			    </select>
       	  	</td>
       	  	<td class="td">Account</td>
       	  	<td class="td">
			    <select id="account_select" name="account_select">
			        <option value="cash">Cash</option>
			        <option value="debit">Debit</option>
			        <option value="creditcard">Credit Card</option>
			    </select>
       	  	</td>
       	  </tr>
		  <tr>
       	  	<td class="td">Date</td>
       	  	<td colspan="3" class="td"><input class="dateInput" type="date"></td>
       	  </tr>
       	  <tr>
       	  	<td class="td">Description</td>
       	  	<td colspan="3" class="td"><textarea id="description"></textarea></td>
       	  </tr>
       	  <tr>
       	  	<td colspan="4" class="td-button">
       	  	  <button type="button" class="custom-button" id="tab1_submit">submit</button>
       	  	</td>
       	  </tr>
       </table>
    </div>
    <div id="tab_Income_Trans" class="tab-content">
       <table class="table">
       	  <tr>
       		<th colspan ="4">Income Transaction</th>
       	  </tr>
       	  <tr>
       	  	<td class="td">Name</td>
       	  	<td class="td"><input id="name" type="text"></td>
       	  	<td class="td">Amount</td>
       	  	<td class="td"><input id="" type="text"></td>
       	  </tr>
		  <tr>
       	  	<td class="td">Date</td>
       	  	<td colspan="3" class="td"><input class="dateInput" type="date"></td>
       	  </tr>
       	  <tr>
       	  	<td class="td">Description</td>
       	  	<td colspan="3" class="td"><textarea id="description"></textarea></td>
       	  </tr>
       	  <tr>
       	  	<td colspan="4" class="td-button"><button type="button" class="custom-button" id="tab2_submit">submit</button></td>
       	  </tr>
       </table> 
    </div>
    <div id="tab_Invest" class="tab-content">

    </div>
    <div id="tab_Expense_Report" class="tab-content">

    </div>
   <div id="tab_Income_Report" class="tab-content">

    </div>
   <div id="tab_Update_passwd" class="tab-content">
      <form action="updatePasswordServlet" method="post" id="updatePasswordForm">
	   	  <table class="table-manage">
	   	  	 <tr>
	   	  	    <th colspan="2">Update Password</th>
	   	  	 </tr>
	   	  	  <tr>
	   	  	  	<td class="td2">Old Password: </td>
	   	  	  	<td class="td2"><input type="password" id="oldPassword" name="oldPassword"></td>
	   	  	  </tr>
	   	  	  <tr>
	   	  	    <td class="td2">New Password: </td>
	   	  	    <td class="td2"><input type="password" id="newPassword" name="newPassword"></td>
	   	  	  </tr>
	   	  	  <tr>
	   	  	    <td class="td2">Confirm New Password: </td>
	   	  	    <td class="td2"><input type="password" id="confirmPassword" name="confirmPassword"></td>
	   	  	  </tr>
	   	  	  <tr>
	   	  	   	 <td colspan="2" class="td-button">
	   	  	   	 	<span id="msg" style="font-size: 12px; color:red">${messageModel.message}</span><br>
	   	  	   	    <button class="custom-button" id="updatePasswd" type="button">Submit</button>
	   	  	   	 </td>
	   	  	  </tr>
	   	  </table>
   	  </form>
    </div>
   <div id="tab_Manage" class="tab-content" >
   	  <div class="table-container">

   	  	<table class="table-manage">
   	  		<tr>
   	  			<th colspan="3.5" style="border:none"> Expenses Category  </th>
   	  			<th style="border:none">
   	  				<button class="small-button" id="addECategory" onclick="openAddCategoryWindow()">Add</button> 
   	  			</th>
   	  		</tr>
   	  		<tr>
	   	  		<td class="td2"></td>
	   	  		<td class="td1" style="text-align: center">Name</td>
	   	  		<td class="td2 img"  style="text-align: center"></td>
	   	  		<td class="td2 img" style="text-align: center"></td>
   	  		</tr>
   	  		<%
   	  			if(categories!=null && categories.size()>0){
   	  				for (int i = 0; i < categories.size(); i++){
   	  		%>
   	  			<tr>
	   	  			<td class="td2"><%=i+1 %></td>
	   	  			<td class="td1"><%= categories.get(i).getName()%></td>
	   	  			<td class="td2 img"  style="text-align: center"><img src="image/edit.png" alt="edit"></td>
	   	  			<td class="td2 img" style="text-align: center">
	   	  				<img id="delECategory" data-value="<%=categories.get(i).getId()%>" src="image/delete.png" alt="Delete" data-action="deleteECategory">
	   	  			</td>
   	  			</tr>
   	  		<%
   	  				}
   	  			}
   	  		%>
   	  		
   	  	</table>

   	  </div>
   	  <div class="table-container">
   	   <form action="addAccount" method="post" id="addAccount">
   	  	<table  class="table-manage">
   	  		<tr>
   	  			<th colspan="3.5" style="border:none"> Account Management  </th>
   	  			<th style="border:none">
   	  				<button class="small-button" id="addAccount" type="submit">Add</button> 
   	  			</th>
   	  		</tr>
 			<tr>
   	  			<td class="td2">1</td>
   	  			<td class="td1">Diner</td>
   	  			<td class="td2 img"  style="text-align: center"><img src="image/edit.png" alt="edit"></td>
   	  			<td class="td2 img" style="text-align: center"><img src="image/delete.png" alt="Delete" data-action="" style="cursor:pointer;"></td>
   	  		</tr>  	  		
   	  	</table>
   	   </form>
   	  </div>
   </div>        
</div>
</body>
<script>
	$(document).ready(function() {		
	    // Get the active tab from the request attribute
	    var activeTab = '${activeTab}';
	    // Check if activeTab is set
	    if (!activeTab) {
			var activeTab = 'tab_Expense_Trans';
	    }
	    $('.tab, .tab-content').removeClass('active');

	    // Activate the corresponding tab link and content 
	    $('a[href="#' + activeTab + '"]').addClass('active'); // Activate tab link
	    $('#' + activeTab).addClass('active'); // Activate tab content
	});
</script>
</html>