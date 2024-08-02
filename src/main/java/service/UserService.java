package service;

import org.apache.ibatis.session.SqlSession;

import entity.User;
import entity.vo.MessageModel;
import mapper.UserMapper;
import util.GetSqlSession;
import util.PasswordUtil;
import util.StringUtil;

/**
 * 业务逻辑
 * @version 1.0
 * @author CEmmH
 */
public class UserService {
	/**
	 * 1. Check for null parameters
	 *    If the parameter is null: Set the status code, message, and echo data in the message model object, and return the message model object.
	 * 2. Call the Mapper layer's query method to retrieve the user object by username.
	 * 3. Check if the user object is null
	 *    If the user object is null: Set the status code, message, and echo data in the message model object, and return the message model object.
	 * 4. Check if the password matches
	 *    If it does not match, set the status code, message, and echo data in the message model object, and return the message model object.
	 * 5. Set the success status, message, and user object in the message model object, and return it.
	 * @param userName
	 * @param password
	 * @return
	 */

	public MessageModel userLogin(String userName, String password) {
		MessageModel messageModel  = new MessageModel();
		//回显数据
		User user = new User();
		user.setUserName(userName);
		user.setHashed_Password(password);
		messageModel.setObject(user);
		
		// Parameter null check
		if(StringUtil.isEmpty(userName) || StringUtil.isEmpty(password)) {
			messageModel.setCode(0);
			messageModel.setMessage("User Name and Password can't be empty! ");
			return messageModel;
		}
		//调用Mapper层查询方法
		SqlSession sqlSession = GetSqlSession.createSqlSession();
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		user = userMapper.queryUserByName(userName);
		if(user == null) {
			messageModel.setCode(0);
			messageModel.setMessage("The UserName is not excist.");
			return messageModel;
		}
		
		if(!PasswordUtil.verifyPassword(password, user.getHashedPassword())) {
			messageModel.setCode(0);
			messageModel.setMessage("The password is not correct.");
			return messageModel;	
		}
		//success，
		messageModel.setCode(1);
		messageModel.setObject(user);
		return messageModel;
		
	}
	public boolean checkUserExist(String userName, String email) {
		boolean userExist = true;
		SqlSession sqlSession = GetSqlSession.createSqlSession();
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		User user = userMapper.checkUserExist(userName,email);
		if(user == null ) {
			userExist = false;
		}
		return userExist;
		
	}
	public MessageModel userRegister(User user) {
		MessageModel messageModel = new MessageModel();
		messageModel.setObject(user);
		if(checkUserExist(user.getUserName(),user.getEmail())) {
			messageModel.setCode(0);
			messageModel.setMessage("This user name or email has already been registered!");
			return messageModel;
		}else {
			SqlSession sqlSession = GetSqlSession.createSqlSession();
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			userMapper.insertUser(user);
			sqlSession.commit();
			messageModel.setMessage("Register Successed, please log in!");
			return messageModel;
		}
		
	}
}
