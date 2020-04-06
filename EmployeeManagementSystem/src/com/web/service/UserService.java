package com.web.service;

import java.util.List;

import com.web.pojo.Menu;
import com.web.pojo.User;

public interface UserService {
	public User login(String username,String password) throws Exception;

	public List<Menu> getAllSubMenus( Integer userId) throws Exception;
	
	public void resetPwd(Integer id, String newPwd) throws Exception;
}
