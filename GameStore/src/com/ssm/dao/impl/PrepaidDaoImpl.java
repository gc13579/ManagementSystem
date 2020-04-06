package com.ssm.dao.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Repository;
import com.ssm.dao.PrepaidDao;
import com.ssm.pojo.Prepaid;

@Repository
public class PrepaidDaoImpl implements PrepaidDao {
	private PrepaidDao prepaidDao;

	// 添加充值记录
	public void insertPrepaid(Map<String, Object> map) {
		prepaidDao.insertPrepaid(map);
	}

	// 获取用户所有充值记录
	public List<Prepaid> selectAllPrepaidsByUserId(Integer userId) {
		return prepaidDao.selectAllPrepaidsByUserId(userId);
	}

	public void setFactory(SqlSessionFactory factory) {
		this.prepaidDao = factory.openSession().getMapper(PrepaidDao.class);
	}
}
