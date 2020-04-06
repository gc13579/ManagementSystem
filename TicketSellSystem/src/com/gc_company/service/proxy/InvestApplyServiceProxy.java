package com.gc_company.service.proxy;

import java.util.List;

import com.gc_company.enity.InvestApplyHistory;
import com.gc_company.enity.RechargeApply;
import com.gc_company.factory.ObjectFactory;
import com.gc_company.service.InvestApplyHistoryService;
import com.gc_company.service.impl.InvestApplyHistoryServiceImpl;
import com.gc_company.trans.Transation;

public class InvestApplyServiceProxy implements InvestApplyHistoryService {
	Transation trans = (Transation) ObjectFactory.getObject("trans");
	InvestApplyHistoryServiceImpl investApplyServiceImpl = (InvestApplyHistoryServiceImpl) ObjectFactory.getObject("investApplyServiceImpl");

	@Override
	public List<InvestApplyHistory> showAllRecords() throws Exception {
		List<InvestApplyHistory> list = null;
		trans.begin();
		try {
			list=investApplyServiceImpl.showAllRecords();
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
			e = new Exception("查看记录失败，请稍后重试");
			throw e;
		}
		return list;
	}

}
