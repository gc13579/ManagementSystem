package com.web.mapper;

import java.sql.Date;
import java.sql.ResultSet;

import com.web.VO.PermissionsVO;

public class PermissionsVOMapper implements RowMapper<PermissionsVO>{

	public PermissionsVO mapperObject(ResultSet rs) throws Exception {
		PermissionsVO permissionsVO=new PermissionsVO();
		permissionsVO.setT_id(rs.getInt("t_id"));
		permissionsVO.setT_role_id(rs.getInt("t_role_id"));
		permissionsVO.setT_menu_id(rs.getInt("t_menu_id"));
		permissionsVO.setT_create_time(new java.util.Date(rs.getDate("t_create_time").getTime()));
//		permissionsVO.setT_create_time(rs.getDate("t_create_time"));
		permissionsVO.setT_menu_name(rs.getString("t_menu_name"));
		permissionsVO.setT_role_name(rs.getString("t_role_name"));
		return permissionsVO;
	}

}
