package com.gc_company.dao;

import java.sql.SQLException;
import java.util.List;

import com.gc_company.enity.User;

public interface UserDao {
	public User selectUserByUsernameAndPwd(String username, String password);

	public void insertUser(String username,
			String password, String phone, String idCard) throws SQLException;

	public List<User> selectAllUsers();
	
	public void updateUserMoneyById(Integer id,Double money) throws SQLException;
	
	public User selectUserByUsername(String username);
	
	public void updateUserState(User user,String userState) throws SQLException;
	
}
