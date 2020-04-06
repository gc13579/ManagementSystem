package com.gc_company.mapper;

import java.sql.ResultSet;

import com.gc_company.enity.BuyHistory;

public class BuyHistoryMapper implements RowMapper<BuyHistory>{

	@Override
	public BuyHistory mapperObject(ResultSet rs) throws Exception {
		BuyHistory buyHistory=new BuyHistory();
		buyHistory.setBuyhistory_id(rs.getInt("p_id"));
		buyHistory.setT_id(rs.getInt("t_id"));
		buyHistory.setBuyTime(rs.getString("buytime"));
		buyHistory.setBuyState(rs.getString("buystate"));
		buyHistory.setTrainNum(rs.getString("trainnum"));
		buyHistory.setStartTime(rs.getString("starttime"));
		buyHistory.setTarinPrice(rs.getDouble("price"));
		buyHistory.setStartPlace(rs.getString("startplace"));
		buyHistory.setEndPlace(rs.getString("endplace"));
		buyHistory.setState(rs.getString("state"));
		return buyHistory;
	}

}
