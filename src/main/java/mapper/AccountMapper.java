package mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import entity.Account;

public interface AccountMapper {
	public void insertAccount(Account account);
	public List<Account> queryAccountByUserid(@Param("userId")int useId);
	public Account queryAccountById(@Param("id")int id);
	public Account queryAccountByName(@Param("name")String name,@Param("userId")int userId);
	public void deleteAccountById(@Param("id")int id);
	public void updateAccountById(@Param("id")int id,@Param("newName")String newName);
	public void deleteAccountByName(@Param("name")String name, @Param("userId")int userId);
}
