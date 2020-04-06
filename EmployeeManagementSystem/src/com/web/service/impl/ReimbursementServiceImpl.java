package com.web.service.impl;

import java.util.Date;
import java.util.List;

import com.web.dao.ReimbursementDao;
import com.web.pojo.Holiday;
import com.web.pojo.Reimbursement;
import com.web.service.ReimbursementService;

public class ReimbursementServiceImpl implements ReimbursementService{
	private ReimbursementDao reimbursementDao;

	public ReimbursementDao getReimbursementDao() {
		return reimbursementDao;
	}

	public void setReimbursementDao(ReimbursementDao reimbursementDao) {
		this.reimbursementDao = reimbursementDao;
	}

	public List<Reimbursement> getReimbursement(String userName, String reimbursementType, String applyType) throws Exception {
		List<Reimbursement> reimbursements = reimbursementDao.selectReimbursementByUserName(userName,reimbursementType,applyType);
		return reimbursements;
	}

	public void addReimbursement(String reimNo, String userName, String reimType, String reimAbstract, String reimMoney, String reimStates) throws Exception {
		Reimbursement reimbursement = new Reimbursement();
		reimbursement.setReimNo(reimNo);
		reimbursement.setReimPerson(userName);
		reimbursement.setReimType(reimType);
		reimbursement.setReimMoney(Double.parseDouble(reimMoney));
		reimbursement.setReimTime(new Date());
		reimbursement.setReimStates(reimStates);
		reimbursement.setReimAbstract(reimAbstract);
		reimbursementDao.insertReimbursement(reimbursement);
		
	}

	public Reimbursement getReimByNo(String reimNo) throws Exception {
		return reimbursementDao.selectReimByNo(reimNo);
	}

	public void removeReimbursement(String reimNo) throws Exception {
		reimbursementDao.deleteReimbursement(reimNo);
		
	}

	public void changeReimbursement(String reimNo, String reimType, String reimAbstract, String reimMoney, String reimStates) throws Exception {
		Reimbursement reimbursement = new Reimbursement(); 
        reimbursement.setReimNo(reimNo);
		reimbursement.setReimType(reimType);
		reimbursement.setReimAbstract(reimAbstract);
		reimbursement.setReimMoney(Double.parseDouble(reimMoney));
		reimbursement.setReimStates(reimStates);
		reimbursementDao.updateReimbursement(reimbursement);
	}
	
}
