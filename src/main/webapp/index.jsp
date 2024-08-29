<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="entity.User"%>
<%@ page import="java.util.List" %>
<%@ page import="entity.Category" %>
<%@ page import="entity.Account" %>
<%@ page import="entity.Expense" %>
<%@ page import="entity.vo.MessageModel" %>
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
	List<Account> accountList = (List<Account>)session.getAttribute("accountList");
%>
<body>
	<header>
		<h2>Welcome ${user.firstName} ${user.lastName} </h2>
	</header>

	<div class="div-container">
    <div class="tabs">
        <div class="tab active" data-tab="tab_Expense_Trans">Expenses Transaction</div>
        <div class="tab" data-tab="tab_Income_Trans">Income Transaction</div>

        <div class="tab" data-tab="tab_Expense_Report" data-tab-name="tab_Expense_Report">
        	Expenses Report
        </div>

        <div class="tab" data-tab="tab_Income_Report" data-tab-name="tab_Income_Report">Income Report</div>
        <div class="tab" data-tab="tab_Update_passwd">Update Password</div>
        <div class="tab" data-tab="tab_Manage" >Manage Category</div>
        <div class="tab" data-tab="tab_SignOut" data-tab-name="tab_SignOut">Sign Out</div>
    </div>
   <div id="tab_Expense_Trans" class="tab-content active">
    <form action="addExpenseServlet" method="post" id="addExpenseForm">
       <table class="table">
       	  <tr>
       		<th colspan ="4">Expenses Transaction</th>
       	  </tr>
       	  <tr>
       	  	<td class="td">Name</td>
       	  	<td class="td"><input id="name" name="name" type="text"></td>
       	  	<td class="td">Amount</td>
       	  	<td class="td"><input id="amount" name="amount" type="text"></td>
       	  </tr>
       	  <tr>
       	  	<td class="td">Category</td>
       	  	<td class="td">
       	  		<select id="category_select" name="category_select">
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
			    	<option value="">Choose an account</option>
			    	<%
			    		if(accountList != null && accountList.size()>0){
			    			for(int i = 0; i < accountList.size(); i++){
			    	%>
			    		<option value="<%=accountList.get(i).getId()%>"><%=accountList.get(i).getName() %></option>
			    	<%			
			    			}
			    		}
			    	%>
			    </select>
       	  	</td>
       	  </tr>
		  <tr>
       	  	<td class="td">Date</td>
       	  	<td colspan="3" class="td"><input class="dateInput" name="date" type="date"></td>
       	  </tr>
       	  <tr>
       	  	<td class="td">Description</td>
       	  	<td colspan="3" class="td"><textarea id="description" name="description"></textarea></td>
       	  </tr>
       	  <tr>
       	  	<td colspan="4" class="td-button">
       	  	  	<c:if test="${not empty messageModel}">
   				  <span id="msg" style="font-size: 12px; color:red">${messageModel.message}</span><br>
    
                  <c:remove var="messageModel" scope="session" />
                </c:if>
       	  	  <button type="button" class="custom-button" id="addExpenseSubmit">submit</button>
       	  	</td>
       	  </tr>
       </table>
      </form>
    </div>
    <div id="tab_Income_Trans" class="tab-content">
      <form action="addIncomeServlet" method="post" id="addIncomeForm">
       <table class="table">
       	  <tr>
       		<th colspan ="4">Income Transaction</th>
       	  </tr>
       	  <tr>
       	  	<td class="td">Name</td>
       	  	<td class="td"><input id="income_name" name ="income_name" type="text"></td>
       	  	<td class="td">Amount</td>
       	  	<td class="td"><input id="income_amount" name="income_amount" type="text"></td>
       	  </tr>
		  <tr>
       	  	<td class="td">Date</td>
       	  	<td colspan="3" class="td"><input class="dateInput" id="income_date" name="income_date" type="date"></td>
       	  </tr>
       	  <tr>
       	  	<td class="td">Description</td>
       	  	<td colspan="3" class="td"><textarea id="income_description" name="income_description"></textarea></td>
       	  </tr>
       	  <tr>
       	  	<td colspan="4" class="td-button">
       	  	    <c:if test="${not empty messageModel}">
   				  <span id="msg" style="font-size: 12px; color:red">${messageModel.message}</span><br>
    
                  <c:remove var="messageModel" scope="session" />
                </c:if>
       	  	  <button type="button" class="custom-button" id="income_submit">submit</button>
       	  	</td>
       	  </tr>
       </table> 
      </form>
    </div>

    <div id="tab_Expense_Report" class="tab-content <c:if test="${activeTab == 'tab_Expense_Report'}">active</c:if>">

    </div>
   <div id="tab_Income_Report" class="tab-content">
	  <form id="incomeReportForm" action="" method="post">
	    <table class="table">
	      <tr>
	        <th colspan="7">Income Transaction Report</th>
	      </tr>
	      <tr>
	        <td class="td_report" width="10%"> </td>
	        <td class="td_report" width="15%">Name</td>
	        <td class="td_report" width="15%">Amount</td>
	        <td class="td_report" width="15%">Date</td>
	        <td class="td_report">Note</td>
	      </tr>
	    </table>
	  </form>
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
	   	  	   	 <c:if test="${not empty messageModel}">
   				  <span id="msg" style="font-size: 12px; color:red">${messageModel.message}</span><br>
    
                  <c:remove var="messageModel" scope="session" />
                </c:if>
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
   	  	<table  class="table-manage">
   	  		<tr>
   	  			<th colspan="3.5" style="border:none"> Account Management  </th>
   	  			<th style="border:none">
   	  				<button class="small-button" id="addAccount" onclick="openAddAccount()">Add</button> 
   	  			</th>
   	  		</tr>
   	  		<tr>
	   	  		<td class="td2"></td>
	   	  		<td class="td1" style="text-align: center">Name</td>
	   	  		<td class="td2 img"  style="text-align: center"></td>
	   	  		<td class="td2 img" style="text-align: center"></td>
   	  		</tr>   	  		
   	  		<%
   	  			if(accountList!=null && accountList.size()>0){
   	  				for(int i = 0; i < accountList.size(); i++){  	  				
   	  		%>
 			<tr>
   	  			<td class="td2"><%=i+1 %></td>
   	  			<td class="td1"><%=accountList.get(i).getName() %></td>
   	  			<td class="td2 img"  style="text-align: center"><img src="image/edit.png" alt="edit"></td>
   	  			<td class="td2 img" style="text-align: center">
   	  			<img id="delAccount" src="image/delete.png" alt="Delete" data-value="<%=accountList.get(i).getId() %>" data-action="deleteAccount" style="cursor:pointer;"></td>
   	  		</tr>  	
   	  		<%
   	  				}
   	  			}
   	  		%>  		
   	  	</table>
   	  </div>
   </div>  
   <div id="tab_SignOut" class="tab-content">
		
   </div>      
</div>
</body>

</html>