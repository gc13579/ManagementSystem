package com.web.dao.impl;

import java.util.List;
import com.web.dao.MenuDao;
import com.web.mapper.MenuMapper;
import com.web.pojo.Menu;
import com.web.pojo.User;
import com.web.util.JDBCTemplate;

public class MenuDaoImpl implements MenuDao {
	JDBCTemplate<Menu> temp = new JDBCTemplate<Menu>();

	public List<Menu> selectAllMenuNames(User user) throws Exception {
//select t_menu_id,t_menu_name,t_href_url,t_parent_id,t_create_time 
//from t_menu where t_menu_id in 
//(select x.t_menu_id from 
//(select t_id,t_menu_id,p.t_role_id,p.t_create_time 
//from t_user u,t_permissions p where u.t_role_id=p.t_role_id and u.t_user_id = ?) as x);
		String sql=new StringBuffer()
			.append(" select ")
			.append(" 	t_menu_id,t_menu_name,t_href_url,t_parent_id,t_create_time ")
			.append(" from ")
			.append(" 	t_menu_id ")
			.append(" where ")
			.append(" 	t_menu_id ")
			.append(" in ")
			.append(" 	(select ")
			.append(" 		x.t_menu_id ")
			.append(" 	from ")
			.append(" 		(select ")
			.append(" 			 t_id,t_menu_id,p.t_role_id,p.t_create_time ")
			.append(" 		from ")
			.append(" 			t_user u,t_permissions p ")
			.append(" 		where ")
			.append(" 			u.t_role_id = p.t_role_id ")
			.append(" 		and ")
			.append(" 			u.t_user_id = ? ) ")
			.append(" 	as x); ")
			.toString();
		return temp.selectAll(new MenuMapper(), sql, user.getT_user_id());
	}

	public List<Menu> selectAllMenus() throws Exception {
		String sql=new StringBuffer()
			.append(" select ")
			.append(" 	t_menu_id,t_menu_name,t_href_url,t_parent_id,t_create_time ")
			.append(" from ")
			.append(" 	t_menu ")
			.toString();
		return temp.selectAll(new MenuMapper(), sql);
	}

	public List<Menu> selectMenuNames(String menuName) throws Exception {
		String sql=new StringBuffer()
			.append(" select ")
			.append(" 	t_menu_id,t_menu_name,t_href_url,t_parent_id,t_create_time ")
			.append(" from ")
			.append(" 	t_menu ")
			.append(" where ")
			.append(" 	t_menu_name != ? ")
			.toString();
		return temp.selectAll(new MenuMapper(), sql, menuName);
	}

}
