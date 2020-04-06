package com.gc_company.service.proxy;

import java.util.List;
import com.gc_company.enity.User;
import com.gc_company.exception.LoginFailException;
import com.gc_company.exception.SomeFieldsAreSameException;
import com.gc_company.factory.ObjectFactory;
import com.gc_company.service.UserService;
import com.gc_company.trans.Transation;

public class UserServiceProxy implements UserService {
	Transation trans = (Transation) ObjectFactory.getObject("trans");
	UserService userService = (UserService) ObjectFactory.getObject("userServiceImpl");

	@Override
	public User login(String username, String password) throws LoginFailException, Exception {
		User user = null;
		trans.begin();
		try {
			user = userService.login(username, password);
			trans.commit();
		} catch (LoginFailException e) {
			trans.rollback();
			throw e;
		} catch (Exception e) {
			trans.rollback();
			e = new Exception("连接出现异常，请稍后再试");
			throw e;
		}
		return user;
	}

	@Override
	public void regist(String username, String password, String phone, String idCard) throws SomeFieldsAreSameException, Exception {
		trans.begin();
		try {
			userService.regist(username, password, phone, idCard);
			trans.commit();
		} catch (SomeFieldsAreSameException e) {
			trans.rollback();
			throw e;
		} catch (Exception e) {
			trans.rollback();
			e = new Exception("注册出现异常，请稍后再试");
			throw e;
		}
	}

	@Override
	public List<User> showAllUsers() throws Exception {
		trans.begin();
		List<User> list;
		try {
			list=userService.showAllUsers();
			trans.commit();
		}   catch (Exception e) {
			trans.rollback();
			e = new Exception("显示出现异常，请稍后再试");
			throw e;
		}
		return list;
	}

	@Override
	public void updateUserState(String userName,String userState) throws Exception {
		trans.begin();
		try {
			userService.updateUserState(userName,userState);
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
			e = new Exception("修改出现异常，请稍后再试");
			throw e;
		}
	}

	@Override
	public void updateUserMonney(String userName, Double userMoney) throws Exception {
		trans.begin();
		try {
			userService.updateUserMonney(userName,userMoney);
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
			e = new Exception("修改出现异常，请稍后再试");
			throw e;
		}
	}

	@Override
	public User selectUserByUsername(String userName) throws Exception {
		trans.begin();
		User user;
		try {
			user=userService.selectUserByUsername(userName);
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
			e = new Exception("修改出现异常，请稍后再试");
			throw e;
		}
		return user;
	}

	
}
