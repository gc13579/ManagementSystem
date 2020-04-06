package com.gc_company.service;

import java.util.List;

import com.gc_company.enity.PurchaseRecord;
import com.gc_company.enity.User;

public interface PurchaseRecordService {
	// 添加购买记录
	// public void addOneBuyRecord(String trainNum, String user_id, String
	// buyTime, String state) throws Exception;
	// public void addOneBuyRecord(Integer u_id,Integer t_id,String
	// buytime,String buystate) throws Exception;

	// 显示所有购买记录
	public List<PurchaseRecord> showAllRecords() throws Exception;

	// 退票
	public void returnOneTicket(String trainnum, User user)
			throws Exception;
//	public void returnOneTicket(Integer t_id)
//			throws Exception;

}
