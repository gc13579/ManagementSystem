package com.web.mapper;

import java.sql.ResultSet;
import java.util.Date;

import com.web.pojo.Menu;

public class MenuMapper implements RowMapper<Menu>{
	public Menu mapperObject(ResultSet rs) throws Exception {
		Menu menu=new Menu();
		menu.setT_menu_id(rs.getInt("t_menu_id"));
		menu.setT_menu_name(rs.getString("t_menu_name"));
		menu.setT_href_url(rs.getString("t_href_url"));
		menu.setT_parent_id(rs.getInt("t_parent_id"));
		menu.setT_create_time(new Date(rs.getDate("t_create_time").getTime()));
		return menu;
	}

}
