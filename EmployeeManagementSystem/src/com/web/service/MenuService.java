package com.web.service;

import java.util.List;

import com.web.pojo.Menu;
import com.web.pojo.User;

public interface MenuService {
	public List<Menu> selectAllMenuNames(User user) throws Exception;

	public List<Menu> getAllMenu() throws Exception;

	public List<Menu> getMenuNames(String menuName) throws Exception;
}
