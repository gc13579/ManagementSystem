package com.ssm.service;

import java.util.Date;
import java.util.List;

import com.ssm.pojo.Prepaid;

public interface PrepaidService {
	// 添加充值记录
	public void addPrepaid(String account, String password, Integer userId,
			String userAccount, String userProvince,Double userCurrency)
			throws Exception;

	// 查询用户所有充值记录
	public List<Prepaid> getAllPrepaidsByUserId(Integer userId);
}
