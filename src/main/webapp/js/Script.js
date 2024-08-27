
/**
 * for index.jsp: achieve tab change in jquery
 */
$(document).ready(function(){
     $('.tab').click(function(){
         // Remove active class from all tabs and tab contents
         $('.tab, .tab-content').removeClass('active');
		 var tabname = $(this).data('tab-name');
		 if(tabname == "tab_SignOut"){
			logout();
			return;
		 }
         // Add active class to the clicked tab and corresponding tab content
         $(this).addClass('active');
         $('#' + $(this).data('tab')).addClass('active');
     });
 });
 
function logout(){
	$.ajax({
	  url: '/MoneyDiary/logout',  // 服务器上处理登出的端点
	  type: 'POST',
	  contentType: 'application/json',
	  success: function(response) {
	      // 成功退出后的操作，例如重定向到登录页面
	   	window.location.href = '/MoneyDiary/login.jsp';
	  },
	  error: function(xhr, status, error) {
	      // 处理错误
	    console.error('Error logging out:', error);
	  }
	});	
}
 
/**
 * For index.jsp: Expenses Transaction
 * automatically set current date
 */
 $(document).ready(function() {
     var today = new Date();
     // Format the date: yyyy-mm-dd
     var formattedDate = today.toISOString().split('T')[0];
     // set default input[type="date"] 
     $('.dateInput').val(formattedDate);
 });
/**
 * For index.jsp: Manage Category Tab
 * delete Expense category image action
 */
 $(document).ready(function(){
  $('img').on('click', function(){
 	var imageId = $(this).attr('id');
 	var dataValue = $(this).attr('data-value');
 	var actionUrl = $(this).data('action');
 	if (confirm('Are you sure you want to perform this action?')) {
           $.ajax({				
               url: actionUrl, // use data-action 
               type: 'POST',
               contentType: 'application/json',
               data: JSON.stringify({ id: dataValue }), // send the data to server           
               success: function(response) {
                   alert(response.message); 
               },
               error: function(xhr, status, error) {
                   alert('Failed to perform action.');
                   console.error('Error:', error);
               }
           });
       }
  });
 });
/**
 * index.jsp: update password tab
 * Update password submit
 */
$(document).ready(function(){
    $("#updatePasswd").click(function(){
        // Get the value of the input field with the ID "oldPassword"
        var oldPassword = $("#oldPassword").val();
		var newPassword = $("#newPassword").val();
		var confirmPassword = $("#confirmPassword").val();
		if(isEmpty(oldPassword)){
			alert("Old password can not be empty.");
			return;
		}
		if(isEmpty(newPassword)){
			alert("New passwords can not be empty.");
			return;
		}
		if(isEmpty(confirmPassword)){
			alert("Confirm passwords can not be empty.");
			return;
		}
		if(!checkPassword(newPassword,confirmPassword)){
			alert("The new password and confirm password must match.");
			return;
		} 
		if(checkPasswordLength(newPassword)){
			// submit the form with the ID "addForm"
			$("#updatePasswordForm").submit();
		}    
        
    });
});
/**
 * index.jsp - expenses transaction: submit check funciont.
 */
$(document).ready(function(){
	$('#addExpenseSubmit').click(function(){
		var name = $("#name").val();
		var amount = $("#amount").val();
		if(isEmpty(name)){
			alert("Please enter name.");
			return;
		}
		if(isEmpty(amount)){
			alert("Pleae enter amount.");
			return;
		}
		//check if amount is a number
		if(isNaN(amount) || isNaN(parseFloat(amount))){
			alert("Please enter a number for amount.");
			return;
		}
		var category = $("#category_select").val();
		var account = $("#account_select").val();
		if(isEmpty(category)){
			alert("Please choose a category.");
			return;
		}
		if(isEmpty(account)){
			alert("Please choose a account type.");
			return;
		}
		$("#addExpenseForm").submit();
	});
});
/**
 * index.jsp - income transaction: submit check funciont.
 */
$(document).ready(function(){
	$('#income_submit').click(function(){
		var name = $("#income_name").val();
		var amount = $("#income_amount").val();
		if(isEmpty(name)){
			alert("Please enter name.");
			return;
		}
		if(isEmpty(amount)){
			alert("Pleae enter amount.");
			return;
		}
		//check if amount is a number
		if(isNaN(amount) || isNaN(parseFloat(amount))){
			alert("Please enter a number for amount.");
			return;
		}		
		$("#addIncomeForm").submit();
	});
});
/**
 * Add Expense Category
 */
$(document).ready(function(){
    // Attach a click event handler to the element with the ID "add"
    $("#addECategory").click(function(){
        // Get the value of the input field with the ID "name"
        var name = $("#name").val();
        
        // Check if the "name" input is empty using the isEmpty function
        if(isEmpty(name)){
            // If empty, display an error message in the element with the ID "msg"
            $("#msg").html("Please enter Category Name! ");
            return; // Exit the function without submitting the form
        }
        
        // If the "name" input is not empty, submit the form with the ID "addForm"
        $("#addForm").submit();
    });
});
/**
 * Addcount.jsp: add a new account type.
 */
$(document).ready(function(){
	$("#addAccount").click(function(){
		debugger;
		var name = $("#name").val();
		if(isEmpty(name)){
			$("#msg").html("Please enter account name. ");
			return;
		}
		$("#addForm").submit();
	});
});


/**
 * check if the string is empty.
*/
function isEmpty(str){
	if(str == null || str.trim() ==""){
		return true;
	}
	return false;
}
/**
 * check email format.
 */
function checkEmail(email){	
	var emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
	return emailPattern.test(email);
}
/**
 * check if the two passwords match.
 */
function checkPassword(password1, password2){
	return password1 === password2;
}
/**
 * add expenses category page
 */
function openAddCategoryWindow() {
	const screenWidth = window.screen.availWidth;
	const screenHeight = window.screen.availHeight;
	const width = 600;
	const height = 400;
	const left = (screenWidth - width) / 2;
	const top = (screenHeight - height) / 2;
    const newWindow = window.open('AddCategory.jsp', '_blank', 
		`width=${width},height=${height},left=${left},top=${top},scrollbars=yes`);
    // Set up a listener to refresh the main window when the new window is closed
    const checkWindowClosed = setInterval(function() {
        if (newWindow.closed) {
            clearInterval(checkWindowClosed);
/*			const currentUrl = new URL(window.location.href);
			currentUrl.searchParams.set('activeTab', 'tab_Manage');
			window.location.href = currentUrl.toString(); */
			localStorage.setItem('activeTab', 'tab_Manage');
            location.reload(); // Refresh the main window
        }
    }, 1000);
}
/**
 * open add account page
 */
function openAddAccount(){
	const screenWidth = window.screen.availWidth;
	const screenHeight = window.screen.availHeight;
	const width = 600;
	const height = 400;
	const left = (screenWidth - width) / 2;
	const top = (screenHeight - height) / 2;	
	const newWindow = window.open("AddAccount.jsp",'_blank',`width=${width},height=${height},left=${left},top=${top},scrollbars=yes`);
	const checkWindowClosed = setInterval(function(){
		if(newWindow.closed){
			clearInterval(checkWindowClosed);
			ocalStorage.setItem('activeTab', 'tab_Manage');
			location.reload(); // Refresh the main window			
		}
	},1000);
}
/**
 * check password length
 */
function checkPasswordLength(password){
	const minLength = 3;
	if(password.length < minLength){
		alert("The password must be at least 8 characters long.")
		return false;
	}
	return true;
}
function hashPassword(password){
	$salt = bin2hex(random_bytes(16)); // Generate a salt
	$hashedPassword = password_hash($password . $salt, PASSWORD_BCRYPT); // Hash the password with the salt

}