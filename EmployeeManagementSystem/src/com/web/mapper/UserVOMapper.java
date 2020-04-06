package com.web.mapper;

import java.sql.Date;
import java.sql.ResultSet;

import com.web.VO.UserVO;
import com.web.pojo.User;

public class UserVOMapper implements RowMapper<UserVO>{

	public UserVO mapperObject(ResultSet rs) throws Exception {
		UserVO userVO = new UserVO();
		userVO.setT_user_id(rs.getInt("t_user_id"));
		userVO.setT_user_account(rs.getString("t_user_account"));
		userVO.setT_user_pwd(rs.getString("t_user_pwd"));
		userVO.setT_role_id(rs.getInt("t_role_id"));
		userVO.setT_user_name(rs.getString("t_user_name"));
		userVO.setT_user_state(rs.getString("t_user_state"));
		userVO.setT_role_name(rs.getString("t_role_name"));
		userVO.setT_create_time(new java.util.Date(rs.getDate("t_create_time").getTime()));
		return userVO;
	}

}
