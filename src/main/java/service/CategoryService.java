package service;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import entity.Category;
import entity.vo.MessageModel;
import mapper.CategoryMapper;
import util.GetSqlSession;

public class CategoryService {
	/**
	 * This method is used to add a expense category.
	 * @param category
	 * @return
	 */
	public MessageModel addCategory(Category category) {
		MessageModel messageModel = new MessageModel();
		messageModel.setObject(category);
		if(checkECategoryExist(category.getName(),category.getUserId())) {
			messageModel.setCode(0);
			messageModel.setMessage("This Category has already exist.");
			return messageModel;
		}else {
			SqlSession sqlSession = GetSqlSession.createSqlSession();
			try {
				CategoryMapper categoryMapper = sqlSession.getMapper(CategoryMapper.class);
				categoryMapper.insertECategory(category);
				sqlSession.commit();
				messageModel.setMessage("Catagory "+ category.getName()+" has been created successfully");
				return messageModel;
			}finally {
				sqlSession.close();
			}
		}
		
	}
	/**
	 * This method check if the expense category exist.
	 * @param name
	 * @param userid
	 * @return
	 */
	public boolean checkECategoryExist(String name,int userid) {
		boolean exist = true;
		SqlSession sqlSession = GetSqlSession.createSqlSession();
		try {
			CategoryMapper categoryMapper = sqlSession.getMapper(CategoryMapper.class);
			Category category = categoryMapper.queryECategoryByName(name,userid);
			if(category==null) {
				return false;
			}
			return exist;
		}finally {
			sqlSession.close();
		}		
	}
	/**
	 * This method gets expense categories by userId.
	 * @param userid
	 * @return
	 */
	public List<Category> getECategoriesByUserId(int userid){
		List<Category> categories = new ArrayList<>();
		SqlSession sqlSession = GetSqlSession.createSqlSession(); 
		try {
			CategoryMapper categoryMapper = sqlSession.getMapper(CategoryMapper.class);
			categories = categoryMapper.queryECategoryByUserId(userid);
		}finally {
			sqlSession.close();
		}
		
		return categories;
	}
	
	public void deleteECategoryById(int id) {
		SqlSession sqlSession = GetSqlSession.createSqlSession();
		try {
			CategoryMapper catergoryMapper = sqlSession.getMapper(CategoryMapper.class);
			catergoryMapper.deleteECategoryById(id);
			sqlSession.commit();
		}finally {
			sqlSession.close();
		}
	}
}
