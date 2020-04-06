package com.web.dao;

import java.util.List;

import com.web.pojo.Menu;
import com.web.pojo.Permissions;
import com.web.pojo.User;

public interface MenuDao {
	public List<Menu> selectAllMenuNames(User user) throws Exception;

	public List<Menu> selectAllMenus() throws Exception;

	public List<Menu> selectMenuNames(String menuName) throws Exception;
}
