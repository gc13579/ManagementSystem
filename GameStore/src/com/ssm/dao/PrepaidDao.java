package com.ssm.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.ssm.pojo.Prepaid;

public interface PrepaidDao {
	// 添加充值记录
	public void insertPrepaid(Map<String, Object> map);

	// 获取用户所有充值记录
	public List<Prepaid> selectAllPrepaidsByUserId(Integer userId);
}
