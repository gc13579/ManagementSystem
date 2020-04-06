package com.ssm.dao;

import java.util.Map;

import com.ssm.pojo.Manager;

public interface ManagerDao {
	//根据账号密码查询管理员
	public Manager selectManagerByAccountAndPwd(Map<String, Object> map);

}
