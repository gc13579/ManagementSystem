package com.gc_company.mapper;

import java.sql.ResultSet;

import com.gc_company.enity.RechargeApply;

public class RechargeApplyMapper implements RowMapper<RechargeApply> {

	@Override
	public RechargeApply mapperObject(ResultSet rs) throws Exception {
		RechargeApply rechargeApply = new RechargeApply();
		rechargeApply.setId(rs.getInt("id"));
		rechargeApply.setU_id(rs.getInt("user_id"));
		rechargeApply.setApplyMoney(rs.getDouble("applymoney"));
		rechargeApply.setApplyTime(rs.getString("applytime"));
		rechargeApply.setApplyState(rs.getString("applystate"));
		return rechargeApply;
	}

}
