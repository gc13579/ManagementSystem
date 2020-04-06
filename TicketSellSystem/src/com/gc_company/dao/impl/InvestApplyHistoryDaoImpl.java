package com.gc_company.dao.impl;

import java.util.List;

import com.gc_company.dao.InvestApplyHistoryDao;
import com.gc_company.enity.InvestApplyHistory;
import com.gc_company.mapper.InvestApplyHistoryMapper;
import com.gc_company.util.JDBCTemplate;

public class InvestApplyHistoryDaoImpl implements InvestApplyHistoryDao {
	JDBCTemplate<InvestApplyHistory> temp=new JDBCTemplate<InvestApplyHistory>();
	@Override
	public List<InvestApplyHistory> selectAllRecords() throws Exception {
		String sql = new StringBuffer()
			.append(" select ")
			.append(" 	r_id,u_id,applymoney,applytime,applystate,username ")
			.append(" from ")
			.append("   t_recharge_apply ")
			.append(" right outer join ")
			.append(" (select ")
			.append(" 	id,username ")
			.append(" from ")
			.append(" 	t_user) ")
			.append(" as temp on ")
			.append(" 	t_recharge_apply.u_id=temp.id ")
			.append(" where ")
			.append(" 	applystate = ? ")
			.append(" 	or applystate = ? ")
			.append(" order by ")
			.append("   r_id desc ")
			.toString();
		return temp.selectAll(new InvestApplyHistoryMapper(), sql ,"正在申请","已通过");
	}
}
