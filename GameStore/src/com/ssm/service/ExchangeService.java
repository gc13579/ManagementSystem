package com.ssm.service;

import java.util.List;

import com.ssm.util.Pager;
import com.ssm.vo.ExchangeVO;
import com.ssm.pojo.Exchange;

public interface ExchangeService {
	// 增加兑换比例
	public void addExchange(String provinceId, String charge,
			String exchangeStatus);

	// 分页获取所有兑换比例
	public Pager<ExchangeVO> getAllExchangesByPager(Integer pageNo,
			String provinceId);
	//获取所有兑换比例
	public List<Exchange> getAllExchanges();

	// 根据id获取兑换比例发
	public Exchange getExchangeById(Integer id);

	// 修改兑换比例
	public void modifyExchange(Integer id, String charge, String exchangeStatus);

	// 删除兑换比例
	public void removeExchangeByIds(String[] delId);
}
