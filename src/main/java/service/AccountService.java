package service;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import entity.Account;
import entity.vo.MessageModel;
import mapper.AccountMapper;
import util.GetSqlSession;

public class AccountService {
	/**
	 * Create a new account if the name not exist.
	 * @param account
	 * @return
	 */
	public MessageModel addAccount(Account account) {
		MessageModel messageModel = new MessageModel();
		messageModel.setObject(account);
		SqlSession sqlSession = GetSqlSession.createSqlSession();
		
		try {
			if(!this.checkAccountByName(account.getName(), account.getUserId())) {
				AccountMapper accountMapper = sqlSession.getMapper(AccountMapper.class);
				accountMapper.insertAccount(account);
				sqlSession.commit();
				messageModel.setCode(1);
				messageModel.setMessage("Account " + account.getName()+" has been created successful.");
			}else {
				messageModel.setCode(0);
				messageModel.setMessage("Account "+account.getName()+" has already exist.");
			}
		}finally {
			sqlSession.close();
		}
		return messageModel;
	}
	/**
	 * This method check if the account name exist.
	 * @param name
	 * @param userId
	 * @return
	 */
	public boolean checkAccountByName(String name, int userId) {
		boolean exist = true;
		SqlSession sqlSession = GetSqlSession.createSqlSession();
		try {
			AccountMapper accountMapper = sqlSession.getMapper(AccountMapper.class);
			Account account = accountMapper.queryAccountByName(name, userId);
			if(account == null) {
				return false;
			}
		}finally {
			sqlSession.close();
		}
		return exist;
	}
	/**
	 * Get Account info by userId.
	 * @param userId
	 * @return
	 */
	public List<Account> getAccountsByUserId(int userId){
		List<Account> accountlist = new ArrayList<>();
		SqlSession sqlSession = GetSqlSession.createSqlSession();
		try {
			AccountMapper accountMapper = sqlSession.getMapper(AccountMapper.class);
			accountlist = accountMapper.queryAccountByUserid(userId);			
		}finally {
			sqlSession.close();
		}
		return accountlist;
	}
	/**
	 * Delete account by name.
	 * @param name
	 * @param userId
	 */
	public void deleteAccountByName(String name, int userId) {
		SqlSession sqlSession = GetSqlSession.createSqlSession();
		try {
			AccountMapper accountMapper = sqlSession.getMapper(AccountMapper.class);
			accountMapper.deleteAccountByName(name, userId);
			sqlSession.commit();
		}finally {
			sqlSession.close();
		}
	}
	/**
	 * delete account by id.
	 * @param id
	 */
	public void deleteAccountById(int id) {
		SqlSession sqlSession = GetSqlSession.createSqlSession();
		try {
			AccountMapper accountMapper = sqlSession.getMapper(AccountMapper.class);
			accountMapper.deleteAccountById(id);
			sqlSession.commit();
		}finally {
			sqlSession.close();
		}
	}
}
