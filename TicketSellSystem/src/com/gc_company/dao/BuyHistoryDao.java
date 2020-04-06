package com.gc_company.dao;

import java.util.List;

import com.gc_company.enity.BuyHistory;

public interface BuyHistoryDao {
	public List<BuyHistory> selectAllRecords(Integer u_id) throws Exception;
}
