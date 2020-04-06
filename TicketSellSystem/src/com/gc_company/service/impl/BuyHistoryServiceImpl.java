package com.gc_company.service.impl;

import java.util.List;

import com.gc_company.dao.BuyHistoryDao;
import com.gc_company.enity.BuyHistory;
import com.gc_company.factory.ObjectFactory;
import com.gc_company.service.BuyHistoryService;

public class BuyHistoryServiceImpl implements BuyHistoryService{
BuyHistoryDao buyHistoryDao=(BuyHistoryDao) ObjectFactory.getObject("buyHistoryDao");
	@Override
	public List<BuyHistory> showAllBuyHistory(Integer u_id) throws Exception {
		return buyHistoryDao.selectAllRecords(u_id);
	}
	


}
