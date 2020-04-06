package com.gc_company.service.impl;

import java.util.List;

import com.gc_company.dao.InvestApplyHistoryDao;
import com.gc_company.enity.InvestApplyHistory;
import com.gc_company.enity.RechargeApply;
import com.gc_company.factory.ObjectFactory;
import com.gc_company.service.InvestApplyHistoryService;

public class InvestApplyHistoryServiceImpl implements InvestApplyHistoryService {
	InvestApplyHistoryDao investApplyHistoryDao = (InvestApplyHistoryDao) ObjectFactory.getObject("investApplyHistoryDao");

	@Override
	public List<InvestApplyHistory> showAllRecords() throws Exception {
		return investApplyHistoryDao.selectAllRecords();
	}

}
