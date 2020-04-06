package com.ssm.dao.impl;

import java.util.Map;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Repository;

import com.ssm.dao.ManagerDao;
import com.ssm.pojo.Manager;
@Repository
public class ManagerDaoImpl implements ManagerDao{

	private ManagerDao managerDao;
	//根据账号密码查管理员
	public Manager selectManagerByAccountAndPwd(Map<String, Object> map) {
		return managerDao.selectManagerByAccountAndPwd(map);
	}
	public void setFactory(SqlSessionFactory factory) {
		this.managerDao = factory.openSession().getMapper(ManagerDao.class);
	}
}
