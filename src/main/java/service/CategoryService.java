package service;

import org.apache.ibatis.session.SqlSession;

import entity.Category;
import entity.vo.MessageModel;
import mapper.CategoryMapper;
import util.GetSqlSession;

public class CategoryService {
	public MessageModel addCategory(Category category) {
		MessageModel messageModel = new MessageModel();
		messageModel.setObject(category);
		if(checkCategoryExist(category.getName(),category.getUserId())) {
			messageModel.setCode(0);
			messageModel.setMessage("This Category has already exist.");
			return messageModel;
		}else {
			SqlSession sqlSession = GetSqlSession.createSqlSession();
			CategoryMapper categoryMapper = sqlSession.getMapper(CategoryMapper.class);
			categoryMapper.insertCategory(category);
			sqlSession.commit();
			messageModel.setMessage("Catagory "+ category.getName()+" has been created successfully");
			return messageModel;
		}
		
	}
	
	public boolean checkCategoryExist(String name,int userid) {
		boolean exist = true;
		SqlSession sqlSession = GetSqlSession.createSqlSession();
		CategoryMapper categoryMapper = sqlSession.getMapper(CategoryMapper.class);
		Category category = categoryMapper.queryCategoryByName(name,userid);
		if(category==null) {
			return false;
		}
		return exist;
	}
}
