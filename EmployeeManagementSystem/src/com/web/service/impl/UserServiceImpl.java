package com.web.service.impl;

import java.util.ArrayList;
import java.util.List;
import com.web.dao.UserDao;
import com.web.pojo.Menu;
import com.web.pojo.User;
import com.web.service.UserService;

public class UserServiceImpl implements UserService {
	private UserDao userDao;
	public User login(String username, String password) throws Exception {
		User user = null;
		try {
			user = userDao.selectUserByName(username);
			if (user == null) {
				throw new Exception("登录失败：用户名不存在");
			} else {
				if (!user.getT_user_pwd().equals(password)) {
					throw new Exception("登录失败：用户名密码不匹配，请重新输入");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (e.getMessage() != null && !e.getMessage().equals("")) {
				throw e;
			}
			throw new Exception("服务器正忙：" + e.getMessage());
		}// 阻止原生报错直接出现在页面上
		return user;
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public List<Menu> getAllSubMenus( Integer userId) throws Exception {
		return userDao.selectUserSubMenus(userId);
	}
	public void resetPwd(Integer id, String newPwd) throws Exception {
		User user = null;
		try {
			user = userDao.selectUserByUid(id);
			if(user.getT_user_pwd().equals(newPwd)){
				throw new Exception("新密码与旧密码不能相同");
			}else{
				userDao.resetPwdByUid(id, newPwd);
			}
		} catch (Exception e) {
			e.printStackTrace();
			if(e.getMessage()!=null&&!e.getMessage().equals("")) {
				throw e;
			}
			throw new Exception("系统正忙"+e.getMessage());
		}
		
	}

}
