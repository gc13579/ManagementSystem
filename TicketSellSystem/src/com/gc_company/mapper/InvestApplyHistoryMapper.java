package com.gc_company.mapper;

import java.sql.ResultSet;

import com.gc_company.enity.InvestApplyHistory;

public class InvestApplyHistoryMapper implements RowMapper<InvestApplyHistory> {

	@Override
	public InvestApplyHistory mapperObject(ResultSet rs) throws Exception {
		InvestApplyHistory rechargeApplyHistory = new InvestApplyHistory();
		rechargeApplyHistory.setRechargeApplyHistoryId(rs.getInt("r_id"));
		rechargeApplyHistory.setU_id(rs.getInt("u_id"));
		rechargeApplyHistory.setUserName(rs.getString("userName"));
		rechargeApplyHistory.setApplyMoney(rs.getDouble("applyMoney"));
		rechargeApplyHistory.setApplyTime(rs.getString("applyTime"));
		rechargeApplyHistory.setApplyState(rs.getString("applyState"));
		return rechargeApplyHistory;
	}

}
