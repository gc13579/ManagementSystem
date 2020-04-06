package com.gc_company.dao.impl;

import java.sql.SQLException;
import java.util.List;

import com.gc_company.dao.PurchaseRecordDao;
import com.gc_company.enity.PurchaseRecord;
import com.gc_company.mapper.PurchaseRecordMapper;
import com.gc_company.util.JDBCTemplate;

public class PurchaseRecordDaoImpl implements PurchaseRecordDao{
	JDBCTemplate<PurchaseRecord> temp=new JDBCTemplate<PurchaseRecord>();
	//在购买记录表，加一条记录
	
	@Override
	public void insertOneRecord(Integer u_id, Integer t_id, String buyTime, String buystate) throws SQLException {
		String sql=new StringBuffer()
			.append(" insert into ")
			.append(" 	t_purchase_record ")
			.append(" values ")
			.append(" 	(?,?,?,?,?) ")
			.toString();
		temp.insert(sql,null, u_id,t_id,buyTime,buystate);
	}
//	@Override
//	public void insertOneRecord(String user_id, String ticket_id, String buyTime, String state, String trainNum, String startTime,Double trainPrice) throws SQLException {
//		String sql=new StringBuffer()
//			.append(" insert into ")
//			.append(" 	t_purchase_record ")
//			.append(" values ")
//			.append(" 	(?,?,?,?,?,?,?,?) ")
//			.toString();
//		temp.insert(sql,null,user_id,ticket_id,buyTime,state,trainNum,startTime,trainPrice);
//	}
	//查询所有购买记录
	@Override
	public List<PurchaseRecord> selectAllRecords() {
//		String sql=new StringBuffer()
//			.append(" select ")
//			.append(" 	id,user_id,ticket_id,buytime,buystate,trainnum,starttime,trainprice")
//			.append(" from ")
//			.append(" 	t_purchase_record ")
//			.toString();				
		String sql=new StringBuffer()
		.append(" select ")
		.append(" 	buyhistory_id,t_id,trainnum,startplace,endplace,starttime,price,state,buytime,buystate ")
		.append(" from ")
		.append(" 	t_purchase_record ")
		.append(" right outer join ")
		.append(" (select ")
		.append(" 	id,trainnum,startplace,endplace,starttime,price,state from t_ticket) ")
		.append(" as temp on ")
		.append("  t_purchase_record.t_id = temp.id ")
		.toString();				
		return temp.selectAll(new PurchaseRecordMapper(), sql);
	}
	
	//找某条记录
	//按照倒序找，在买车票方法中，掉此方法查询是否已经被买过
	//如果退票了，应该还能买，倒序查找，可防止出现，只要退过一次，就能反复买的情况
	@Override
	public PurchaseRecord selectOneRecord(Integer t_id) {
		String sql=new StringBuffer()
			.append(" select ")
			.append(" 	p_id,u_id,t_id,buytime,buystate ")
			.append(" from ")
			.append(" 	t_purchase_record ")
			.append(" where ")
			.append(" 	t_id = ? ")
			.append(" order by p_id desc ")
			.toString();
		return temp.selectOne(new PurchaseRecordMapper(), sql, t_id);
	}
	//根据上面找到的记录，跟新购买状态
	@Override
	public void updateOneRecordState(PurchaseRecord purchaseRecord) throws SQLException {
		String sql=new StringBuffer()
			.append(" update ")
			.append(" 	t_purchase_record ")
			.append(" set ")
			.append(" 	buystate = ? ")
			.append(" where ")
			.append(" 	p_id = ? ")
			.toString();
		temp.update(sql, purchaseRecord.getBuyState(),purchaseRecord.getId());
	}

	

}
