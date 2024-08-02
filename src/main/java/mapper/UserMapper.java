package mapper;

import org.apache.ibatis.annotations.Param;

import entity.User;

public interface UserMapper {
	//queryUserName，String，User information needs to be consistent with UserMapper.xml

	public User queryUserByName(@Param("userName")String userName);
	
	public void insertUser(User ueer);
	//
	public User checkUserExist(@Param("userName")String userName, @Param("email")String email);
}
