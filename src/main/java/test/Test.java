package test;

import org.apache.ibatis.session.SqlSession;

import entity.User;
import mapper.UserMapper;
import util.GetSqlSession;

public class Test {

	public static void main(String[] args) {
		// Obtain sqlSession
		System.out.println("===================111");
		SqlSession session = GetSqlSession.createSqlSession();
		//get Mapper
		String userName = "user1";
		UserMapper userMapper = session.getMapper(UserMapper.class);
		//调用方法，返回用户对象
//		User user = userMapper.queryUserByName(userName);
		User user = new User("emma","aaa","bb","aa@yahoo.ca","123");
		userMapper.insertUser(user);
		User user1 = userMapper.queryUserByName("emma");
		System.out.println(user1.getEmail());
		User user2 = userMapper.checkUserExist("emma", "emma@yahoo.ca");
		System.out.println(user2);


	}

}
