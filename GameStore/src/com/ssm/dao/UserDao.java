package com.ssm.dao;

import java.util.List;
import java.util.Map;

import com.ssm.pojo.User;

public interface UserDao {
	// 根据账号密码查询用户
	public User selectUserByAccountAndPwd(Map<String, Object> map);

	// 用户注册
	public void insertUser(User user);

	// 根据账号查用户（用于注册时，账号的排重）
	public User selectUserByAccount(String account);

	// 获取所有用户
	public List<User> selectAllUsersByPager(Map<String, Object> map);

	// 禁用用户
	public void updateUsersStatusToForbid(String[] forbidId);

	// 解封用户
	public void updateUsersStatusToNormal(String[] unsealId);

	// 根据条件统计用户数量
	public Integer countUser(Map<String, Object> map);

	// 更改用户游币数量
	public void updateUserCurrencyById(Map<String, Object> map);

	// 更改用户话费数量
	public void updateUserTariffeById(Map<String, Object> map);

	// 根据id获取用户
	public User selectUserById(Integer id);

	// 修改密码
	public void updateUserPwd(Map<String, Object> map);
}
