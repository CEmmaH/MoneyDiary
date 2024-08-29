package mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import entity.Expense;

public interface ExpenseMapper {
	public void insertExpense(Expense expense);
	public Expense queryExpenseById(@Param("id")int id);
	public List<Expense> queryExpenseByDate(@Param("date")Date date, int userId);
	public List<Expense> queryExpenseByUserId(@Param("userId")int userId);
	public List<Expense> queryExpenseOrderBy(@Param("userId")int userId,@Param("orderby")String orderby);
 	public void deleteExpenseById(@Param("id")int id);
	
}
