package com.gc_company.service;

import java.util.List;

import com.gc_company.enity.RechargeApply;

public interface RechargeApplyService {
	public void addOneRecord(Integer userId, Double applyMoney, String applyTime, String applyState) throws Exception;

	public List<RechargeApply> showAllRecords() throws Exception;

	//public void updateOneRecord(RechargeApply rechargeApply) throws Exception;
	public void updateOneRecord(RechargeApply rechargeApply,String userName,Double money) throws Exception;
}
