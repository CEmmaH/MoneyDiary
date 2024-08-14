
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

function openAddCategoryWindow() {
    const newWindow = window.open('AddCategory.jsp', '_blank', 'width=600,height=400');
    // Set up a listener to refresh the main window when the new window is closed
    const checkWindowClosed = setInterval(function() {
        if (newWindow.closed) {
            clearInterval(checkWindowClosed);
            location.reload(); // Refresh the main window
        }
    }, 1000);
}

function hashPassword(password){
	$salt = bin2hex(random_bytes(16)); // Generate a salt
	$hashedPassword = password_hash($password . $salt, PASSWORD_BCRYPT); // Hash the password with the salt

}