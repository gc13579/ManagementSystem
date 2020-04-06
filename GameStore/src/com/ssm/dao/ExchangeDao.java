package com.ssm.dao;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.ssm.pojo.Exchange;

public interface ExchangeDao {
	// 增加兑换比例
	public void insertExchange(Map<String, Object> map);

	// 获取所有兑换比例的数量
	public Integer countExchanges(Map<String, Object> map);

	// 分页获取所有兑换比例
	public List<Exchange> selectAllExchangesByPager(Map<String, Object> map);

	// 获取所有兑换比例
	public List<Exchange> selectAllExchange();

	// 根据id获取兑换比例
	public Exchange selectExchangeById(Integer id);

	// 修改兑换比例
	public void updateExchange(Map<String, Object> map);

	// 删除兑换比例
	public void deleteExchangeByIds(String[] delId);
}
