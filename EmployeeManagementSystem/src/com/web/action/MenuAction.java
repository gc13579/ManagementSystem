package com.web.action;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.web.pojo.Menu;
import com.web.pojo.User;
import com.web.service.MenuService;

public class MenuAction {
	private MenuService menuService;

	// 获取所有菜单
	public String getMenu(HttpServletRequest request, HttpServletResponse resp) {
		List<Menu> depts = null;
		try {
			depts = menuService.getAllMenu();
			PrintWriter out = resp.getWriter();
			out.write(JSONArray.fromObject(depts).toString());
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}

	// 获取除了指定名的所有菜单
	public String getAllMenuNames(HttpServletRequest request, HttpServletResponse resp) {
		String t_menu_name = request.getParameter("t_menu_name");
		List<Menu> list = null;
		try {
			list = menuService.getMenuNames(t_menu_name);
			PrintWriter out = resp.getWriter();
			out.write(JSONArray.fromObject(list).toString());
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	};



	public void setMenuService(MenuService menuService) {
		this.menuService = menuService;
	}

	public MenuService getMenuService() {
		return menuService;
	}
}
