package mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import entity.Category;

public interface CategoryMapper {
	//insert a record to table category
	public void insertECategory(Category category);
	//get query according the category name
	public Category queryECategoryByName(@Param("name")String name,@Param("userid")int userid);
	//get all query from category
	public List<Category> queryECategoryByUserId(@Param("userid")int userid);
	//according category name update category
	public void updateECategory(String oldName, String newName);
	//delete category bye name
	public void deleteECategoryByName(String name);
	//delete expense category by Id
	public void deleteECategoryById(@Param("id")int id);
}
