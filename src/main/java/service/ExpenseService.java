package service;

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
}
