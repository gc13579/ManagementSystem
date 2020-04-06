package com.web.service;

import java.util.List;

import com.web.pojo.Reimbursement;

public interface ReimbursementService {
     //通过姓名查报销
	public List<Reimbursement> getReimbursement(String userName, String reimbursementType, String applyType) throws Exception;
    //添加报销
	public void addReimbursement(String reimNo, String userName, String reimType, String reimAbstract, String reimMoney, String reimStates) throws Exception;
    //通过编号获取报销
	public Reimbursement getReimByNo(String reimNo) throws Exception;
    //删除报销
	public void removeReimbursement(String reimNo) throws Exception;
    //修改报销
	public void changeReimbursement(String reimNo, String reimType, String reimAbstract, String reimMoney, String reimStates) throws Exception;

}
