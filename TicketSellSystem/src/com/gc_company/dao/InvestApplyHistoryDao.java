package com.gc_company.dao;

import java.util.List;

import com.gc_company.enity.InvestApplyHistory;

public interface InvestApplyHistoryDao {
	public List<InvestApplyHistory> selectAllRecords() throws Exception;
}
