package com.gc_company.dao.impl;

import java.sql.SQLException;
import java.util.List;

import com.gc_company.dao.RechargeApplyDao;
import com.gc_company.enity.RechargeApply;
import com.gc_company.mapper.RechargeApplyMapper;
import com.gc_company.util.JDBCTemplate;

public class RechargeApplyDaoImpl implements RechargeApplyDao {
	JDBCTemplate<RechargeApply> temp = new JDBCTemplate<RechargeApply>();

	@Override
	public void insertOneRecord(Integer userId, Double applyMoney, String applyTime, String applyState) throws SQLException {
		String sql=new StringBuffer()
			.append(" insert into ")
			.append(" 	t_recharge_apply ")
			.append(" values ")
			.append(" 	(?,?,?,?,?) ")
			.toString();
		temp.insert(sql,null,userId,applyMoney,applyTime,applyState);
	}

	@Override
	public List<RechargeApply> selectAllRecords() {
		String sql=new StringBuffer()
			.append(" select ")
			.append(" 	id,user_id,user_name,applymoney,applytime,applystate ")
			.append(" from ")
			.append(" 	t_recharge_apply ")
			.toString();
		return temp.selectAll(new RechargeApplyMapper(), sql);
	}

	@Override
	public void updateOneRecordState(RechargeApply rechargeApply) throws SQLException {
		String sql=new StringBuffer()
			.append(" update ")
			.append(" 	t_recharge_apply ")
			.append(" set ")
			.append(" 	applystate = ? ")
			.append(" where ")
			.append(" 	applymoney = ? and ")
			.append(" 	applytime = ? and ")
			.append(" 	applystate = ? ")
			.toString();
		temp.update(sql,
				"已通过",rechargeApply.getApplyMoney(),
				rechargeApply.getApplyTime(),rechargeApply.getApplyState());
		
	}

}
