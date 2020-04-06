package com.gc_company.dao;

import java.sql.SQLException;
import java.util.List;

import com.gc_company.enity.PurchaseRecord;

public interface PurchaseRecordDao {
//	public void insertOneRecord(String user_id, String ticket_id, String buyTime, String state, String trainNum, String startTime,Double trainPrice) throws SQLException;
	public void insertOneRecord(Integer u_id, Integer t_id, String buyTime, String buystate) throws SQLException;

	public List<PurchaseRecord> selectAllRecords();
	
//	public PurchaseRecord selectOneRecord(String trainNum);
	public PurchaseRecord selectOneRecord(Integer t_id);
	
	public void updateOneRecordState(PurchaseRecord purchaseRecord) throws SQLException;
	
}
