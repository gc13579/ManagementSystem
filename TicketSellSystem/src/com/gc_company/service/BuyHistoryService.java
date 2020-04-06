package com.gc_company.service;

import java.util.List;

import com.gc_company.enity.BuyHistory;

public interface BuyHistoryService {
	public List<BuyHistory> showAllBuyHistory(Integer u_id) throws Exception;

}
