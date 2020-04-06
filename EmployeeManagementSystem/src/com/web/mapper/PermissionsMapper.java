package com.web.mapper;

import java.sql.ResultSet;
import java.util.Date;

import com.web.pojo.Permissions;

public class PermissionsMapper implements RowMapper<Permissions>{

	public Permissions mapperObject(ResultSet rs) throws Exception {
		Permissions permissions=new Permissions();
		permissions.setT_id(rs.getInt("t_id"));
		permissions.setT_role_id(rs.getInt("t_role_id"));
		permissions.setT_menu_id(rs.getInt("t_menu_id"));
//		permissions.setT_create_time(rs.getDate("t_create_time"));
		permissions.setT_create_time(new Date(rs.getDate("t_create_time").getTime()));
		return permissions;
	}

}
