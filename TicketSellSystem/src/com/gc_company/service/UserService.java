package com.gc_company.service;

import java.util.List;
import com.gc_company.enity.User;
import com.gc_company.exception.LoginFailException;
import com.gc_company.exception.SomeFieldsAreSameException;

public interface UserService {
	public User login(String username, String password) throws LoginFailException, Exception;

	public void regist(String username, String password, String phone, String idCard) throws SomeFieldsAreSameException, Exception;

	public List<User> showAllUsers() throws Exception;

	public void updateUserState(String userName, String userState) throws Exception;

	public void updateUserMonney(String userName, Double userMoney) throws Exception;

	public User selectUserByUsername(String userName) throws Exception;
}
