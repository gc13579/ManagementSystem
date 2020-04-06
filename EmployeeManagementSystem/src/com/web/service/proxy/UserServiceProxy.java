package com.web.service.proxy;

import java.util.List;

import com.web.pojo.Menu;
import com.web.pojo.User;
import com.web.service.UserService;
import com.web.trans.Transaction;

public class UserServiceProxy implements UserService {
	private UserService userService;
	private Transaction trans;

	public User login(String username, String password) throws Exception {
		trans.begin();
		User user;
		try {
			user = userService.login(username, password);
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
			throw e;
		}
		return user;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public Transaction getTrans() {
		return trans;
	}

	public void setTrans(Transaction trans) {
		this.trans = trans;
	}

	public List<Menu> getAllSubMenus(Integer userId) throws Exception {
		trans.begin();
		List<Menu> list = null;
		try {
			list = userService.getAllSubMenus(userId);
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
		}
		return list;
	}
	public void resetPwd(Integer id, String newPwd) throws Exception {
        trans.begin();
        try {
			userService.resetPwd(id, newPwd);
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
		    throw e;
		}
	}

}
