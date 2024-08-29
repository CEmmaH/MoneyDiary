package service;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import entity.Expense;
import entity.vo.MessageModel;
import mapper.ExpenseMapper;
import util.GetSqlSession;

public class ExpenseService {
	public MessageModel addExpenseTransaction(Expense expense) {
		SqlSession sqlSession = GetSqlSession.createSqlSession();
		MessageModel messageModel = new MessageModel();
		try {
			ExpenseMapper expenseMapper = sqlSession.getMapper(ExpenseMapper.class);
			expenseMapper.insertExpense(expense);
			messageModel.setCode(1);
			messageModel.setObject(expense);
			messageModel.setMessage("Expense transaction inserted successfully.");
			sqlSession.commit();
		}finally {
			sqlSession.close();
		}
		return messageModel;
	}
	/**
	 * Get all expense transaction for current user.
	 * @param userId
	 * @return
	 */
	public List<Expense> getExpenseTrans(int userId, String orderby){
		List<Expense> expenseList = new ArrayList<>();
		SqlSession sqlSession = GetSqlSession.createSqlSession();
		try {
			ExpenseMapper expenseMapper = sqlSession.getMapper(ExpenseMapper.class);
			expenseList = expenseMapper.queryExpenseOrderBy(userId,orderby);
		}finally {
			sqlSession.close();
		}
		return expenseList;
	}
	
	
}
