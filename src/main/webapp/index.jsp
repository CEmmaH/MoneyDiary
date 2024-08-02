<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="entity.User"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Insert title here</title>
	<link rel="stylesheet" href="style.css">
</head>
<%
	
    if (session == null) {
    	response.sendRedirect("login.jsp"); // Session无效，重定向到登录页面
        return; // 确保后续代码不执行
    }
    // 获取 userId 参数
    User user = (User)session.getAttribute("user");
    if (user == null || user.getId()==null) {
    	response.sendRedirect("login.jsp"); // Session无效，重定向到登录页面
        return; // 确保后续代码不执行
     }
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
        <div class="tab" data-tab="tab_Invest_Report">Investment Report</div>
        <div class="tab" data-tab="tab_Manage">Manage Account</div>
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
			        <option value="groceries">Groceries</option>
			        <option value="utilities">Utilities</option>
			        <option value="transportation">Transportation</option>
			        <option value="entertainment">Entertainment</option>
			        <option value="healthcare">Healthcare</option>
			        <option value="education">Education</option>
			        <option value="dining">Dining</option>
			        <option value="clothing">Clothing</option>
			        <option value="housing">Housing</option>
			        <option value="miscellaneous">Miscellaneous</option>
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
       	  	<td colspan="4" class="td-button"><button type="button" class="custom-button" id="tab1_submit">submit</button></td>
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
   <div id="tab_Invest_Report" class="tab-content">
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
   	  			<td class="td2">1</td>
   	  			<td class="td1">Diner</td>
   	  			<td class="td2 img"  style="text-align: center"><img src="image/edit.png" alt="Delete"></td>
   	  			<td class="td2 img" style="text-align: center"><img src="image/delete.png" alt="Delete"></td>
   	  		</tr>
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
   	  			<td class="td2 img"  style="text-align: center"><img src="image/edit.png" alt="Delete"></td>
   	  			<td class="td2 img" style="text-align: center"><img src="image/delete.png" alt="Delete"></td>
   	  		</tr>  	  		
   	  	</table>
   	   </form>
   	  </div>
   </div>        
</div>
</body>
<script type="text/javascript" src="js/jquery-3.7.1.js"></script>
<script>
/**
 *  // achieve tab change in javascript

    document.querySelectorAll('.tab').forEach(tab => {
        tab.addEventListener('click', () => {
            // Remove active class from all tabs and tab contents
            document.querySelectorAll('.tab, .tab-content').forEach(el => el.classList.remove('active'));

            // Add active class to the clicked tab and corresponding tab content
            tab.classList.add('active');
            document.getElementById(tab.getAttribute('data-tab')).classList.add('active');
        });
    });
  */
	// achieve tab change in jquery
    $(document).ready(function(){
        $('.tab').click(function(){
            // Remove active class from all tabs and tab contents
            $('.tab, .tab-content').removeClass('active');

            // Add active class to the clicked tab and corresponding tab content
            $(this).addClass('active');
            $('#' + $(this).data('tab')).addClass('active');
        });
    });
  $(document).ready(function() {
      // 获取当前日期
      var today = new Date();
      // 格式化日期为 yyyy-mm-dd
      var formattedDate = today.toISOString().split('T')[0];
      // 设置 input[type="date"] 的默认值
      $('.dateInput').val(formattedDate);
  });
  function openAddCategoryWindow(id) {
      const newWindow = window.open('AddCategory.jsp', '_blank', 'width=600,height=400');
      // Set up a listener to refresh the main window when the new window is closed
      const checkWindowClosed = setInterval(function() {
          if (newWindow.closed) {
              clearInterval(checkWindowClosed);
              location.reload(); // Refresh the main window
          }
      }, 1000);
  }
</script>
</html>