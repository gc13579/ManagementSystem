package com.gc_company.mapper;

import java.sql.ResultSet;

import com.gc_company.enity.PurchaseRecord;

public class PurchaseRecordMapper implements RowMapper<PurchaseRecord>{

	@Override
	public PurchaseRecord mapperObject(ResultSet rs) throws Exception {
		PurchaseRecord purchaseRecord=new PurchaseRecord();
		purchaseRecord.setId(rs.getInt("p_id"));
		purchaseRecord.setU_id(rs.getInt("u_id"));
		purchaseRecord.setT_id(rs.getInt("t_id"));
		purchaseRecord.setBuyTime(rs.getString("buytime"));
		purchaseRecord.setBuyState(rs.getString("buystate"));
//		purchaseRecord.setTrainNum(rs.getString("trainnum"));
//		purchaseRecord.setStartTime(rs.getString("starttime"));
//		purchaseRecord.setTrainPrice(rs.getDouble("trainprice"));
		return purchaseRecord;
	}

}
