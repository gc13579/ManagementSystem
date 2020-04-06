package com.ssm.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssm.dao.UserDao;
import com.ssm.pojo.User;
import com.ssm.service.UserService;
import com.ssm.util.Constans;
import com.ssm.util.Pager;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;

	// 用户登录
	@Transactional
	public User login(String name, String userPwd) throws Exception {
		User user = null;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("account", name);
		map.put("password", userPwd);
		user = userDao.selectUserByAccountAndPwd(map);
		if (user == null) {
			throw new Exception("用户名或密码错误，请重新输入");
		} else if (user.getStatus().equals("封禁")) {
			throw new Exception("用户已被封禁！请联系管理员");
		}
		return user;
	}

	// 用户注册
	@Transactional
	public void userRegist(User user, String bir) {
		try {
			user.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse(bir));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		userDao.insertUser(user);
	}

	// 获取所有用户
	@Transactional
	public Pager<User> getAllUsersByPager(String pageNo, String account,
			String iphone) {
		Pager<User> pager = new Pager<User>();
		pager.setPageNo(Integer.parseInt(pageNo));

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("account", account);
		map.put("iphone", iphone);

		Integer totalCount = userDao.countUser(map);
		System.out.println("ServiceImpl:totalCount:" + totalCount);
		if (totalCount == null) {
			totalCount = 0;
		}
		pager.setTotalPage(totalCount, Constans.PAGE_SIZE_3);

		map.put("max", Integer.parseInt(pageNo) * Constans.PAGE_SIZE_3 + 1);
		map.put("min", (Integer.parseInt(pageNo) - 1) * Constans.PAGE_SIZE_3);
		List<User> list = userDao.selectAllUsersByPager(map);
		pager.setList(list);
		return pager;
	}

	// 封禁用户
	@Transactional
	public void modifyUsersStatusToForbid(String[] forbidId) {
		userDao.updateUsersStatusToForbid(forbidId);
	}

	// 解封用户
	@Transactional
	public void modifyUsersStatusToNormal(String[] unsealId) {
		userDao.updateUsersStatusToNormal(unsealId);
	}

	// 根据id获取用户
	@Transactional
	public User getUserById(Integer id) {
		return userDao.selectUserById(id);
	}

	// 修改密码
	@Transactional
	public void modifyUserPwd(Integer userId, String newPwd) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", userId);
		map.put("password", newPwd);
		userDao.updateUserPwd(map);
	}

	// 根据账号查询用户
	public User getUserByAccount(String account) {
		return userDao.selectUserByAccount(account);
	}
}
