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
	 * 1. 参数的非空判断
			如果参数为空： 将状态码，提示信息，回显数据设置到消息模型对象中，返回消息模型对象；
		2. 调用Mapper层的查询方法，通过用户名查询用户对象
		3. 判断用户对象是否为空
			如果参数为空： 将状态码，提示信息，回显数据设置到消息模型对象中，返回消息模型对象；
		4. 判断密码是否一致
			如果不等，将状态码，提示信息，回显数据设置到消息模型对象中，返回消息模型对象；
		5. 将成功状态，提示信息，用户对象设置消息模型对象，并return
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
		//登录成功，
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
