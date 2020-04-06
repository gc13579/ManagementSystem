package com.ssm.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssm.dao.ExchangeDao;
import com.ssm.dao.ProvinceDao;
import com.ssm.pojo.Exchange;
import com.ssm.pojo.Expense;
import com.ssm.service.ExchangeService;
import com.ssm.util.Constans;
import com.ssm.util.Pager;
import com.ssm.vo.ExchangeVO;
import com.ssm.vo.ExpenseVO;

@Service
public class ExchangeServiceImpl implements ExchangeService {
	@Autowired
	private ExchangeDao exchangeDao;
	@Autowired
	private ProvinceDao provinceDao;

	// 增加兑换比例
	@Transactional
	public void addExchange(String provinceId, String charge,
			String exchangeStatus) {
		Date date = new Date();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("provinceId", provinceId);
		map.put("charge", charge);
		map.put("createTime", date);
		map.put("updateTime", date);
		map.put("status", exchangeStatus);
		exchangeDao.insertExchange(map);
	}

	// 获取所有兑换比例
	@Transactional
	public Pager<ExchangeVO> getAllExchangesByPager(Integer pageNo,
			String provinceId) {
		Pager<ExchangeVO> pager = new Pager<ExchangeVO>();
		// Pager<Exchange> pager = new Pager<Exchange>();
		pager.setPageNo(pageNo);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("provinceId", provinceId);
		Integer totalCount = exchangeDao.countExchanges(map);
		if (totalCount == null) {
			totalCount = 0;
		}
		pager.setTotalPage(totalCount, Constans.PAGE_SIZE_5);
		map.put("max", pageNo * Constans.PAGE_SIZE_5 + 1);
		map.put("min", (pageNo - 1) * Constans.PAGE_SIZE_5);
		List<Exchange> list = exchangeDao.selectAllExchangesByPager(map);
		List<ExchangeVO> listVO = new ArrayList<ExchangeVO>();
		for (int i = 0; i < list.size(); i++) {
			listVO.add(new ExchangeVO());
			Exchange exchange = list.get(i);
			listVO.get(i).setExchange(exchange);
			listVO.get(i).setProvince(
					provinceDao.selectProvinceById(exchange.getProvinceId())
							.getName());
		}
		// pager.setList(list);
		pager.setList(listVO);
		return pager;
	}

	// 获取所有兑换比例
	@Transactional
	public List<Exchange> getAllExchanges() {
		return exchangeDao.selectAllExchange();
	}

	// 根据id获取兑换比例
	@Transactional
	public Exchange getExchangeById(Integer id) {
		return exchangeDao.selectExchangeById(id);
	}

	// 修改兑换比例
	@Transactional
	public void modifyExchange(Integer id, String charge, String exchangeStatus) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("charge", charge);
		map.put("status", exchangeStatus);
		map.put("updateTime", new Date());
		exchangeDao.updateExchange(map);
	}

	// 删除兑换比例
	@Transactional
	public void removeExchangeByIds(String[] delId) {
		exchangeDao.deleteExchangeByIds(delId);
	}
}
