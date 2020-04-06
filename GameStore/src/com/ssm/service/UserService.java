package com.ssm.service;

import java.util.List;

import com.ssm.pojo.User;
import com.ssm.util.Pager;

public interface UserService {
	// 用户登录
	public User login(String account, String password) throws Exception;

	// 用户注册
	public void userRegist(User user, String bir);

	// 查寻所有用户
	public Pager<User> getAllUsersByPager(String pageNo, String account,
			String iphone);

	// 禁用用户
	public void modifyUsersStatusToForbid(String[] forbidId);

	// 解封用户
	public void modifyUsersStatusToNormal(String[] unsealId);

	// 根据id获取用户
	public User getUserById(Integer id);

	// 修改密码
	public void modifyUserPwd(Integer userId, String newPwd);

	// 根据账号查询用户
	public User getUserByAccount(String account);
}
