package com.web.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.web.dao.ReimbursementDao;
import com.web.mapper.HolidayMapper;
import com.web.mapper.ReimbursementMapper;
import com.web.pojo.Reimbursement;
import com.web.util.JDBCTemplate;

public class ReimbursementDaoImpl implements ReimbursementDao{
JDBCTemplate<Reimbursement> temp = new JDBCTemplate<Reimbursement>();

public List<Reimbursement> selectReimbursementByUserName(String userName, String reimbursementType, String applyType) {
	StringBuffer sql = new StringBuffer()
	.append(" select ")
	.append(" 	id,t_reim_no,t_reim_person,t_reim_type,t_reim_money,t_reim_time,t_reim_state,t_reim_abstract ")
	.append(" from ")
	.append(" 	t_reimbursement ")
	.append(" where ")
	.append(" 	1 = 1")
	;
List<Object> params = new ArrayList<Object>();
if(reimbursementType != null && !reimbursementType.equals("")){
	sql.append(" and t_reim_type like ? ");
	params.add("%"+reimbursementType+"%") ;
}
if(userName != null && !userName.equals("")){
	sql.append(" and t_reim_person like ? ");
	params.add("%"+userName+"%") ;
}
if(applyType != null && !applyType.equals("")){
	sql.append(" and t_reim_state like ? ");
	params.add("%"+applyType+"%") ;
}
return temp.selectAll(new ReimbursementMapper(), sql.toString(),params.toArray());
}

public String selectMaxNo() {
	String sql = new StringBuffer()
	.append(" select ")
	.append(" 	id,t_reim_no,t_reim_person,t_reim_type,t_reim_money,t_reim_time,t_reim_state,t_reim_abstract ")
	.append(" from ")
	.append(" t_reimbursement ")
	.append(" where ")
	.append(" 	t_reim_no = (select max(t_reim_no) from t_reimbursement) ")
	.toString();
	
	
	return temp.selectOne(new ReimbursementMapper(), sql).getReimNo();
}

public void insertReimbursement(Reimbursement reimbursement) {
	String sql = new StringBuffer()
	.append(" insert into ")
	.append(" 	t_reimbursement(id,t_reim_no,t_reim_person,t_reim_type,t_reim_money,t_reim_time,t_reim_state,t_reim_abstract) ")
	.append(" values ")
	.append(" 	(null,?,?,?,?,sysdate(),?,?) ")
	.toString();
temp.insert(sql,reimbursement.getReimNo(),reimbursement.getReimPerson(),reimbursement.getReimType(),reimbursement.getReimMoney(),reimbursement.getReimStates(),reimbursement.getReimAbstract());
	
}

public Reimbursement selectReimByNo(String reimNo) {
	String sql = new StringBuffer()
	.append(" select ")
	.append(" 	id,t_reim_no,t_reim_person,t_reim_type,t_reim_money,t_reim_time,t_reim_state,t_reim_abstract ")
	.append(" from ")
	.append(" 	t_reimbursement ")
	.append(" where ")
	.append(" 	t_reim_no = ? ")
	.toString();
return temp.selectOne(new ReimbursementMapper(), sql, reimNo);
}

public void deleteReimbursement(String reimNo) {
	String sql = new StringBuffer()
	.append(" delete from ")
	.append(" 	t_reimbursement ")
	.append(" where ")
	.append(" 	t_reim_no = ? ")
	.toString();
    temp.delete(sql, reimNo);
	
}

public void updateReimbursement(Reimbursement reimbursement) {
	String sql = new StringBuffer()
	.append(" update ")
	.append(" 	t_reimbursement ")
	.append(" set ")
	.append(" 	t_reim_type=?,t_reim_abstract=?,t_reim_money=?,t_reim_state=?,t_reim_time=sysdate() ")
	.append(" where ")
	.append(" 	t_reim_no = ? ")
	.toString();
    temp.update(sql,reimbursement.getReimType(),reimbursement.getReimAbstract(),reimbursement.getReimMoney(),reimbursement.getReimStates(),reimbursement.getReimNo() );
}


}
