package com.web.mapper;

import java.sql.ResultSet;
import java.util.Date;
import com.web.pojo.Role;

public class RoleMapper implements RowMapper<Role>{

	public Role mapperObject(ResultSet rs) throws Exception {
		Role role=new Role();
		role.setT_role_id(rs.getInt("t_role_id"));
		role.setT_role_name(rs.getString("t_role_name"));
		role.setT_create_time(new Date(rs.getDate("t_create_time").getTime()));
		return role;
	}

}
