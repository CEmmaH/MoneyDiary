package service;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import entity.Income;
import entity.vo.MessageModel;
import mapper.IncomeMapper;
import util.GetSqlSession;

public class IncomeService {
	/**
	 * insert an income transaction.
	 * @param income
	 * @return
	 */
	public MessageModel addIncome(Income income) {
		SqlSession sqlSession = GetSqlSession.createSqlSession();
		MessageModel messageModel = new MessageModel();
		try {
			IncomeMapper incomeMapper = sqlSession.getMapper(IncomeMapper.class);
			incomeMapper.insertIncome(income);
			sqlSession.commit();
			messageModel.setCode(1);
			messageModel.setObject(income);
			messageModel.setMessage("Income transaction inserted successfully.");			
		}finally {
			sqlSession.close();
		}
		return messageModel;
	}
	/**
	 * Get Income transactions order by the request
	 * @param userId
	 * @param orderby
	 * @return
	 */
	public List<Income> getIncometrans(int userId, String orderby){
		List<Income> incomeTrans = new ArrayList<>();
		SqlSession sqlSession = GetSqlSession.createSqlSession();
		try {
			IncomeMapper incomeMapper = sqlSession.getMapper(IncomeMapper.class);
			incomeTrans = incomeMapper.queryIncomeOrderBy(orderby, userId);
		}finally {
			sqlSession.close();
		}
		return incomeTrans;
	}
}
