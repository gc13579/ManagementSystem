package com.gc_company.service.proxy;

import java.util.List;

import com.gc_company.enity.BuyHistory;
import com.gc_company.factory.ObjectFactory;
import com.gc_company.service.BuyHistoryService;
import com.gc_company.trans.Transation;


public class BuyHistoryServiceProxy implements BuyHistoryService{
	Transation trans = (Transation) ObjectFactory.getObject("trans");
	BuyHistoryService buyHistoryService=(BuyHistoryService) ObjectFactory.getObject("buyHistoryServiceImpl");
	@Override
	public List<BuyHistory> showAllBuyHistory(Integer u_id) throws Exception {
		trans.begin();
		List<BuyHistory> list = null; 
		try {
			list=buyHistoryService.showAllBuyHistory(u_id);
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
			e=new Exception("购买记录现实失败，请稍后重试");
			throw e;
		}
		return list;
	}
	
	
}
