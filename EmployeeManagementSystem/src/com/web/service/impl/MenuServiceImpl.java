package com.web.service.impl;

import java.util.List;

import com.web.dao.MenuDao;
import com.web.pojo.Menu;
import com.web.pojo.User;
import com.web.service.MenuService;

public class MenuServiceImpl implements MenuService {
	private MenuDao menuDao;

	public void setMenuDao(MenuDao menuDao) {
		this.menuDao = menuDao;
	}

	public MenuDao getMenuDao() {
		return menuDao;
	}

	public List<Menu> selectAllMenuNames(User user) throws Exception {
		List<Menu> list=menuDao.selectAllMenuNames(user);
		return list;
	}

	public List<Menu> getAllMenu() throws Exception {
		List<Menu> list=menuDao.selectAllMenus();
		return list;
	}

	public List<Menu> getMenuNames(String menuName) throws Exception {
		List<Menu> list=menuDao.selectMenuNames(menuName);
		return list;
	}
}
