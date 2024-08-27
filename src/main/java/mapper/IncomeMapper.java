package mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import entity.Income;

public interface IncomeMapper {
	public void insertIncome(Income income);
	public Income queryIncomeById(@Param("id")int id);
	public List<Income> queryIncomeByDate(@Param("date")Date date, int userId);
	public List<Income> queryIncomeByUserId(@Param("userId")int userId);
	public void deleteIncomeById(@Param("id")int id);
}
