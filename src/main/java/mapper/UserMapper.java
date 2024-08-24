package mapper;

import org.apache.ibatis.annotations.Param;

import entity.User;

public interface UserMapper {
	//queryUserName，String，User information needs to be consistent with UserMapper.xml

	public User queryUserByName(@Param("userName")String userName);
	//get user by user id
	public User queryUserById(@Param("id")int id);
	// insert a new user
	public void insertUser(User ueer);
	//check if the user name exist
	public User checkUserExist(@Param("userName")String userName, @Param("email")String email);
	//update password for current user
	public void updatePasswordByUserId(@Param("id")int userId, @Param("newPassword")String newPassword);
}
