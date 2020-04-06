package com.ssm.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Repository;

import com.ssm.dao.UserDao;
import com.ssm.pojo.User;

@Repository
public class UserDaoImpl implements UserDao {

	private UserDao userDao;

	// 根据帐号密码查询用户
	public User selectUserByAccountAndPwd(Map<String, Object> map) {
		return userDao.selectUserByAccountAndPwd(map);
	}

	// 新增一个用户
	public void insertUser(User user) {
		userDao.insertUser(user);
	}

	// 根据用户名查用户
	public User selectUserByAccount(String account) {
		return userDao.selectUserByAccount(account);
	}

	// 查询所有用户
	public List<User> selectAllUsersByPager(Map<String, Object> map) {
		return userDao.selectAllUsersByPager(map);
	}

	// 封禁用户
	public void updateUsersStatusToForbid(String[] forbidId) {
		userDao.updateUsersStatusToForbid(forbidId);
	}

	// 解封用户
	public void updateUsersStatusToNormal(String[] unsealId) {
		userDao.updateUsersStatusToNormal(unsealId);
	}

	// 根据条件统计用户数量
	public Integer countUser(Map<String, Object> map) {
		return userDao.countUser(map);
	}

	// 更改用户游币数量
	public void updateUserCurrencyById(Map<String, Object> map) {
		userDao.updateUserCurrencyById(map);
	}

	// 更改用户话费数量
	public void updateUserTariffeById(Map<String, Object> map) {
		userDao.updateUserTariffeById(map);
	}

	// 根据id获取用户
	public User selectUserById(Integer id) {
		return userDao.selectUserById(id);
	}

	// 修改密码
	public void updateUserPwd(Map<String, Object> map) {
		userDao.updateUserPwd(map);
	}

	public void setFactory(SqlSessionFactory factory) {
		this.userDao = factory.openSession().getMapper(UserDao.class);
	}
}
