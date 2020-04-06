package com.web.dao;

import java.util.List;

import com.web.pojo.Reimbursement;

public interface ReimbursementDao {
    //通过登录进来的用户名查询报销
	public List<Reimbursement> selectReimbursementByUserName(String userName, String reimbursementType, String applyType);
    //获取最新的报销编号
	public String selectMaxNo();
    //添加报销
	public void insertReimbursement(Reimbursement reimbursement);
    //通过报销编号查询报销
	public Reimbursement selectReimByNo(String reimNo);
   //删除报销
	public void deleteReimbursement(String reimNo); 
   //修改报销
	public void updateReimbursement(Reimbursement reimbursement);

}
