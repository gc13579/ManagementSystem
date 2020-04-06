package com.web.mapper;

import java.sql.ResultSet;
import java.util.Date;

import com.web.pojo.Reimbursement;

public class ReimbursementMapper implements RowMapper<Reimbursement>{

	public Reimbursement mapperObject(ResultSet rs) throws Exception {
		Reimbursement reimbursement = new Reimbursement();
		reimbursement.setId(rs.getInt("id"));
		reimbursement.setReimNo(rs.getString("t_reim_no"));
		reimbursement.setReimPerson(rs.getString("t_reim_person"));
		reimbursement.setReimType(rs.getString("t_reim_type"));
		reimbursement.setReimMoney(rs.getDouble("t_reim_money"));
		reimbursement.setReimTime(new Date(rs.getTimestamp("t_reim_time").getTime()));
		reimbursement.setReimStates(rs.getString("t_reim_state"));
		reimbursement.setReimAbstract(rs.getString("t_reim_abstract"));
		return reimbursement;
	}

}
