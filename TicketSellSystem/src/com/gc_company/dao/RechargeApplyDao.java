package com.gc_company.dao;

import java.sql.SQLException;
import java.util.List;

import com.gc_company.enity.RechargeApply;

public interface RechargeApplyDao {
	public void insertOneRecord(Integer userId, Double applyMoney, String applyTime, String applyState) throws SQLException;

	public List<RechargeApply> selectAllRecords();

	public void updateOneRecordState(RechargeApply rechargeApply) throws SQLException;
}
