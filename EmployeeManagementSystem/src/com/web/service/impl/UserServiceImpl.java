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
				throw new Exception("��¼ʧ�ܣ��û���������");
			} else {
				if (!user.getT_user_pwd().equals(password)) {
					throw new Exception("��¼ʧ�ܣ��û������벻ƥ�䣬����������");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (e.getMessage() != null && !e.getMessage().equals("")) {
				throw e;
			}
			throw new Exception("��������æ��" + e.getMessage());
		}// ��ֹԭ������ֱ�ӳ�����ҳ����
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
				throw new Exception("������������벻����ͬ");
			}else{
				userDao.resetPwdByUid(id, newPwd);
			}
		} catch (Exception e) {
			e.printStackTrace();
			if(e.getMessage()!=null&&!e.getMessage().equals("")) {
				throw e;
			}
			throw new Exception("ϵͳ��æ"+e.getMessage());
		}
		
	}

}
