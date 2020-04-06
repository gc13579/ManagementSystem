package com.gc_company.service.impl;

import java.sql.SQLException;
import java.util.List;
import com.gc_company.dao.UserDao;
import com.gc_company.enity.User;
import com.gc_company.exception.LoginFailException;
import com.gc_company.exception.SomeFieldsAreSameException;
import com.gc_company.factory.ObjectFactory;
import com.gc_company.service.UserService;

public class UserServiceImpl implements UserService {
	UserDao userDao = (UserDao) ObjectFactory.getObject("userDao");

	@Override
	public User login(String username, String password) throws LoginFailException, Exception {
		User user = userDao.selectUserByUsernameAndPwd(username, password);
		// 业务逻辑判断
		if (user == null) {
			throw new LoginFailException("用户名或密码错误");
		}
		return user;
	}

	@Override
	public void regist(String username, String password, String phone, String idCard) throws SomeFieldsAreSameException, SQLException {
		// 业务逻辑判断，查询用户名等各个字段是否重复，抛出不同异常
		List<User> allUsers = userDao.selectAllUsers();
		if (allUsers.size() == 0) {
			userDao.insertUser(username, password, phone, idCard);
		} else {
			StringBuffer stringBuffer = new StringBuffer();
			for (int i = 0; i < allUsers.size(); i++) {
				if (allUsers.get(i).getUsername().equals(username)) {
					stringBuffer.append("用户名已存在").append("\n");
				}
				if (allUsers.get(i).getIdCard().equals(idCard)) {
					stringBuffer.append("此身份证已被使用").append("\n");
				}
				if (allUsers.get(i).getPhoneNum().equals(phone)) {
					stringBuffer.append("此电话号码已被使用").append("\n");
				}
			}
			if (stringBuffer.length() != 0) {
				throw new SomeFieldsAreSameException(stringBuffer.toString());
			}
			userDao.insertUser(username, password, phone, idCard);
		}

	}

	@Override
	public List<User> showAllUsers() {
		return userDao.selectAllUsers();
	}

	@Override
	public void updateUserState(String userName, String userState) throws Exception {
		// 找到要改变状态的用户
		User user = userDao.selectUserByUsername(userName);
		// 进行状态改变操作
		userDao.updateUserState(user, userState);
	}

	

	@Override
	public void updateUserMonney(String userName, Double userMoney) throws Exception {
		// 找到要改变余额的用户
		User user = userDao.selectUserByUsername(userName);
		// 进行余额变动操作
		// userDao.updateUserMoneyById(user.getId(), userMoney);
		userDao.updateUserMoneyById(user.getId(), userMoney);
	}

	@Override
	public User selectUserByUsername(String userName) {
		User user = userDao.selectUserByUsername(userName);
		return user;
	}
}
