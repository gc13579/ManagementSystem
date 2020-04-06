package com.gc_company.service;

import java.util.List;

import com.gc_company.enity.InvestApplyHistory;
import com.gc_company.enity.RechargeApply;

public interface InvestApplyHistoryService {
	public List<InvestApplyHistory> showAllRecords() throws Exception;
}
