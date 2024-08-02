package mapper;

import org.apache.ibatis.annotations.Param;

import entity.User;

public interface UserMapper {
	//queryUserName，String，User信息需要与UserMapper.xml一致

	public User queryUserByName(@Param("userName")String userName);
	
	public void insertUser(User ueer);
	//
	public User checkUserExist(@Param("userName")String userName, @Param("email")String email);
}
