package com.gc_company.dao.impl;

import java.util.List;

import com.gc_company.dao.BuyHistoryDao;
import com.gc_company.enity.BuyHistory;
import com.gc_company.mapper.BuyHistoryMapper;
import com.gc_company.mapper.PurchaseRecordMapper;
import com.gc_company.util.JDBCTemplate;

public class BuyHistoryDaoImpl implements BuyHistoryDao {
	JDBCTemplate<BuyHistory> temp=new JDBCTemplate<BuyHistory>();
	@Override
	public List<BuyHistory> selectAllRecords(Integer u_id) throws Exception {
		String sql=new StringBuffer()
		.append(" select ")
		.append(" 	p_id,t_id,trainnum,startplace,endplace,starttime,price,state,buytime,buystate ")
		.append(" from ")
		.append(" 	t_purchase_record ")
		.append(" right outer join ")
		.append(" (select ")
		.append(" 	id,trainnum,startplace,endplace,starttime,price,state from t_ticket) ")
		.append(" as temp on ")
		.append("  t_purchase_record.t_id = temp.id ")
		.append(" where ")
		.append(" 	u_id = ? ")
		.append(" order by p_id desc ")
		.toString();				
		return temp.selectAll(new BuyHistoryMapper(), sql,u_id);
	}
}
