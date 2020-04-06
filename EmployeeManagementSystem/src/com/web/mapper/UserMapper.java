package com.web.mapper;

import java.sql.Date;
import java.sql.ResultSet;

import com.web.pojo.User;

public class UserMapper implements RowMapper<User> {

	public User mapperObject(ResultSet rs) throws Exception {
		User user = new User();
		user.setT_user_id(rs.getInt("t_user_id"));
		user.setT_user_account(rs.getString("t_user_account"));
		user.setT_user_pwd(rs.getString("t_user_pwd"));
		user.setT_role_id(rs.getInt("t_role_id"));
		user.setT_user_name(rs.getString("t_user_name"));
		user.setT_user_state(rs.getString("t_user_state"));
		user.setT_create_time(new Date(System.currentTimeMillis()));
		return user;
	}

}
