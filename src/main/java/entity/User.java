package entity;

public class User {
	private Integer id;
	private String userName;
	private String firstName;
	private String lastName;
	private String email;
	private String hashed_password;
	
	public User() {
		
	}
	public User(String userName,String firstName, String lastName, String email, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.hashed_password = password;
		this.userName = userName;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getId() {
		return id;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}	
	public String getUserName() {
		return userName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getFirstName() {
		return firstName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getLastName() {
		return lastName;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEmail() {
		return email;
	}
	
	public void setHashed_Password(String password) {
		this.hashed_password = password;
	}	
	public String getHashedPassword() {
		return hashed_password;
	}

}
