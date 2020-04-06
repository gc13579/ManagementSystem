package com.gc_company.mapper;

import java.sql.ResultSet;

import com.gc_company.enity.User;

public class UserMapper implements RowMapper<User>{

	@Override
	public User mapperObject(ResultSet rs) throws Exception {
		User user=new User();
		user.setId(rs.getInt("id"));
		user.setUsername(rs.getString("username"));
		user.setPassword(rs.getString("password"));
		user.setIdCard(rs.getString("idcard"));
		user.setMoney(rs.getDouble("money"));
		user.setPhoneNum(rs.getString("phonenum"));
		user.setState(rs.getString("state"));
		return user;
	}

}
