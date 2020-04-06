package com.ssm.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Repository;

import com.ssm.dao.ExchangeDao;
import com.ssm.pojo.Exchange;
import com.ssm.util.Pager;

@Repository
public class ExchangeDaoImpl implements ExchangeDao {
	private ExchangeDao exchangeDao;

	// 增加兑换比例
	public void insertExchange(Map<String, Object> map) {
		exchangeDao.insertExchange(map);
	}

	// 获取所有兑换比例的数量
	public Integer countExchanges(Map<String, Object> map) {
		return exchangeDao.countExchanges(map);
	}

	// 分页获取所有兑换比例
	public List<Exchange> selectAllExchangesByPager(Map<String, Object> map) {
		return exchangeDao.selectAllExchangesByPager(map);
	}
	// 获取所有兑换比例
	public List<Exchange> selectAllExchange(){
		return exchangeDao.selectAllExchange();
	}
	// 根据id获取兑换比例
	public Exchange selectExchangeById(Integer id) {
		return exchangeDao.selectExchangeById(id);
	}

	// 修改兑换比例
	public void updateExchange(Map<String, Object> map) {
		exchangeDao.updateExchange(map);
	}

	// 删除兑换比例
	public void deleteExchangeByIds(String[] delId) {
		exchangeDao.deleteExchangeByIds(delId);
	}

	public void setFactory(SqlSessionFactory factory) {
		this.exchangeDao = factory.openSession().getMapper(ExchangeDao.class);
	}
}
