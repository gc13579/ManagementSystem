package com.web.service.proxy;

import java.util.List;

import com.web.pojo.Holiday;
import com.web.pojo.Reimbursement;
import com.web.service.ReimbursementService;
import com.web.trans.Transaction;

public class ReimbursementServiceProxy implements ReimbursementService{
	private ReimbursementService reimbursementService;
	private Transaction trans;
	
	
	
	
	public ReimbursementService getReimbursementService() {
		return reimbursementService;
	}
	public void setReimbursementService(ReimbursementService reimbursementService) {
		this.reimbursementService = reimbursementService;
	}
	public Transaction getTrans() {
		return trans;
	}
	public void setTrans(Transaction trans) {
		this.trans = trans;
	}
	public List<Reimbursement> getReimbursement(String userName, String reimbursementType, String applyType) throws Exception {
		trans.begin();
		List<Reimbursement> reimbursements;
		try {
			reimbursements = reimbursementService.getReimbursement(userName, reimbursementType, applyType);
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
			throw e;
		}
		return reimbursements;
	}
	public void addReimbursement(String reimNo, String userName, String reimType, String reimAbstract, String reimMoney, String reimStates) throws Exception{
        trans.begin();
        try {
			reimbursementService.addReimbursement(reimNo, userName, reimType, reimAbstract, reimMoney, reimStates);
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
			throw e;
		}
	}
	public Reimbursement getReimByNo(String reimNo) throws Exception {
		trans.begin();
		Reimbursement reimbursement;
		try {
			reimbursement = reimbursementService.getReimByNo(reimNo);
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
			throw e;
		}
		return reimbursement;

	}
	public void removeReimbursement(String reimNo) throws Exception {
		trans.begin();
		try {
			reimbursementService.removeReimbursement(reimNo);
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
			throw e;
		}
		
	}
	public void changeReimbursement(String reimNo, String reimType, String reimAbstract, String reimMoney, String reimStates) throws Exception {
		trans.begin();
		try {
			reimbursementService.changeReimbursement(reimNo, reimType, reimAbstract, reimMoney, reimStates);
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
			throw e;
		}
	}
	
}
