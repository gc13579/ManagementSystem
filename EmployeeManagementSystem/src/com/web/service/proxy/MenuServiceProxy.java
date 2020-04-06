package com.web.service.proxy;

import java.util.List;

import com.web.pojo.Menu;
import com.web.pojo.User;
import com.web.service.MenuService;
import com.web.trans.Transaction;

public class MenuServiceProxy implements MenuService{
	private MenuService menuService;
	private Transaction trans;

	public void setMenuService(MenuService menuService) {
		this.menuService = menuService;
	}

	public MenuService getMenuService() {
		return menuService;
	}

	public void setTrans(Transaction trans) {
		this.trans = trans;
	}

	public Transaction getTrans() {
		return trans;
	}

	public List<Menu> selectAllMenuNames(User user) throws Exception {
		trans.begin();
		List<Menu> list=null;
		try {
			list=menuService.selectAllMenuNames(user);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return list;
	}

	public List<Menu> getAllMenu() throws Exception {
		trans.begin();
		List<Menu> list=null;
		try {
			list=menuService.getAllMenu();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return list;
	}

	public List<Menu> getMenuNames(String menuName) throws Exception {
		trans.begin();
		List<Menu> list=null;
		try {
			list=menuService.getMenuNames(menuName);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return list;
	}
}
