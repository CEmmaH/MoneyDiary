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

function checkPassword(password1, password2){
	return password1 === password2;
}

function hashPassword(password){
	$salt = bin2hex(random_bytes(16)); // Generate a salt
	$hashedPassword = password_hash($password . $salt, PASSWORD_BCRYPT); // Hash the password with the salt

}