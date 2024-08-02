package util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class GetSqlSession {
	public static SqlSession createSqlSession() {
		SqlSessionFactory sqlSessionFactory = null;
		InputStream input = null;
		SqlSession session = null;
		
		try {
			//获得mybatis-config.xml环境配置文件
			// Obtain the mybatis-config.xml configuration file
			String resource = "mybatis-config.xml";
			//以流的方式获取resource（mybatis-config.xml环境配置文件）
			// Get the resource (mybatis-config.xml) as an input stream
			input = Resources.getResourceAsStream(resource);
			//创建会话工厂
			// Create the SqlSessionFactory
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(input);
			//通过工厂得到SqlSession
			// Obtain the SqlSession from the factory
			session = sqlSessionFactory.openSession();
			return session;
		}catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static void main(String[] arts) {
		System.out.println(createSqlSession());
	}
}
