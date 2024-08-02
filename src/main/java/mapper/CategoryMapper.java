package mapper;

import org.apache.ibatis.annotations.Param;

import entity.Category;

public interface CategoryMapper {
	//insert a record to table category
	public void insertCategory(Category category);
	//get query according the category name
	public Category queryCategoryByName(@Param("name")String name,@Param("userid")int userid);
	//get all query from category
	public Category[] queryCategory();
	//according category name update category
	public void updateCategory(String oldName, String newName);
	//delete category bye name
	public void deleteCategoryByName(String name);
}
