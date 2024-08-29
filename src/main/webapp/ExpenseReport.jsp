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
    List<Expense> expenseList = (List<Expense>)session.getAttribute("expenseList");
%>
<body>
    <div id="tab_Expense_Report" class="tab-content <c:if test="${activeTab == 'tab_Expense_Report'}">active</c:if>">
	  <form id="expenseReportForm" action="expenseReportServlet" method="post">
	    <table class="table">
	      <tr>
	        <th colspan="4">Expenses Transaction Report</th>
	        <th colspan="3"> order by: 
	            <select id="orderby" name="orderby">
			    	<option value="date">Date</option>
			    	<option value="name">Name</option>
			    	<option value="amount">Amount</option>
			    	<option value="category">Category</option>
			    	<option value="account">Account</option>
			    </select>
			    <button class="medium-button" type="submit">Submit</button>
	        </th>
	      </tr>
	      <tr>
	        <td class="td_report" width="5%"> </td>
	        <td class="td_report" width="15%">Name</td>
	        <td class="td_report" width="10%">Amount</td>
	        <td class="td_report" width="15%">Date</td>
	        <td class="td_report" width="15%">category</td>
	        <td class="td_report" width="15%">Account</td>
	        <td class="td_report">Note</td>
	      </tr>

	      <%
	      	 if(expenseList != null && expenseList.size()>0){
	      		 for(int i = 0; i< expenseList.size(); i++){
	      %>
	         	<tr>
			        <td class="td_report" width="5%"> <%=i+1 %></td>
			        <td class="td_report" width="15%"><%=expenseList.get(i).getName() %></td>
			        <td class="td_report" width="10%"><%=expenseList.get(i).getAmount() %></td>
			        <td class="td_report" width="15%"><%=expenseList.get(i).getDate()%></td>
			        <td class="td_report" width="15%"><%=expenseList.get(i).getCategoryName() %></td>
			        <td class="td_report" width="15%"><%=expenseList.get(i).getAccountName() %></td>
			        <td class="td_report"><%=expenseList.get(i).getDescription() %></td>	              
	            </tr>
	      <%	
	      		 }
	      	 }
	      %>    	      
	    </table>
	  </form>
    </div>
</body>
</html>