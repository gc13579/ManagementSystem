package com.web.dao;

import java.util.List;

import com.web.pojo.Menu;
import com.web.pojo.User;

public interface UserDao {
	public User selectUserByName(String username);

	public List<Menu> selectUserSubMenus(Integer userId) throws Exception;
	
	public User selectUserByUid(Integer id);

	public List<User> selectUserByRid(Integer roleId);

	public void resetPwdByUid(Integer id, String newPwd);
}
