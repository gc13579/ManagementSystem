package com.web.dao.impl;

import java.util.List;
import com.web.dao.UserDao;
import com.web.mapper.MenuMapper;
import com.web.mapper.UserMapper;
import com.web.pojo.Menu;
import com.web.pojo.User;
import com.web.util.JDBCTemplate;

public class UserDaoImpl implements UserDao {
	JDBCTemplate<User> temp = new JDBCTemplate<User>();
	JDBCTemplate<Menu> temp2=new JDBCTemplate<Menu>();

	public User selectUserByName(String username) {
		String sql = new StringBuffer()
			.append(" select ")
			.append(" 	t_user_id,t_user_account,t_user_pwd,t_user_name,t_role_id,t_user_state,t_create_time")
			.append(" from ")
			.append(" 	t_user ")
			.append(" where ")
			.append(" 	t_user_account = ? ")
			.toString();
		return temp.selectOne(new UserMapper(), sql, username);
	}

	public List<Menu> selectUserSubMenus( Integer userId) throws Exception {
		String sql=new StringBuffer()
			.append(" select  ")
			.append(" 	t_menu_id,t_menu_name,t_href_url,t_parent_id,t_create_time ")
			.append(" from ")
			.append(" 	t_menu ")
			.append(" where ")
			.append(" 	t_menu_id in ")
			.append(" 	(select ")
			.append(" 		x.t_menu_id ")
			.append(" 	from ")
			.append(" 		(select ")
			.append(" 			t_id,t_menu_id,p.t_role_id,p.t_create_time ")
			.append(" 		from ")
			.append(" 			t_user u,t_permissions p ")
			.append(" 		where ")
			.append(" 			u.t_role_id=p.t_role_id ")
			.append(" 		and ")
			.append(" 			u.t_user_id = ?) ")
			.append(" as x) ")
			.toString();
		List<Menu> list=temp2.selectAll(new MenuMapper(), sql,userId);
		for (Menu menu : list) {
			System.out.println("userdaoimpl:"+menu);
		}
		return list;
	}
	public void resetPwdByUid(Integer id, String newPwd) {
		String sql = new StringBuffer()
		.append(" update ")
		.append(" 	t_user ")
		.append(" set ")
		.append(" 	t_user_pwd = ? ")
		.append(" where ")
		.append(" 	t_user_id = ? ")
		.toString();
        temp.update(sql,newPwd,id);
		
	}
	public User selectUserByUid(Integer id) {
		String sql = new StringBuffer()
		.append(" select ")
		.append(" 	t_user_id,t_user_account,t_user_pwd,t_user_name,t_user_state,t_role_id,t_create_time ")
		.append(" from ")
		.append(" 	t_user ")
		.append(" where ")
		.append(" 	t_user_id = ? ")
		.toString();
	return temp.selectOne(new UserMapper(), sql, id);	
		
	}

	public List<User> selectUserByRid(Integer roleId) {
		String sql=new StringBuffer()
			.append(" select  ")
			.append(" 	t_user_id,t_user_account,t_user_pwd,t_user_name,t_user_state,t_role_id,t_create_time ")
			.append(" from ")
			.append(" 	t_user ")
			.append(" where ")
			.append(" 	t_role_id = ? ")
			.toString();
		return temp.selectAll(new UserMapper(), sql, roleId);
	}

}
