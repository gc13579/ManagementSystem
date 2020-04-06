package com.gc_company.service.impl;

import java.util.List;

import com.gc_company.dao.RechargeApplyDao;
import com.gc_company.dao.UserDao;
import com.gc_company.enity.RechargeApply;
import com.gc_company.enity.User;
import com.gc_company.factory.ObjectFactory;
import com.gc_company.service.RechargeApplyService;


public class RechargeApplyServiceImpl implements RechargeApplyService{
	RechargeApplyDao rechargeApplyDao = (RechargeApplyDao) ObjectFactory.getObject("rechargeApplyDao");
	UserDao userDao=(UserDao) ObjectFactory.getObject("userDao");
	@Override
	public void addOneRecord(Integer userId,  Double applyMoney, String applyTime, String applyState) throws Exception {
		rechargeApplyDao.insertOneRecord(userId,  applyMoney, applyTime, applyState);
	}
	@Override
	public List<RechargeApply> showAllRecords() throws Exception {
		
		return rechargeApplyDao.selectAllRecords();
		
	}
	@Override
	public void updateOneRecord(RechargeApply rechargeApply,String userName,Double money) throws Exception {
		rechargeApplyDao.updateOneRecordState(rechargeApply);
		User user=userDao.selectUserByUsername(userName);
		userDao.updateUserMoneyById(user.getId(), money);
	}

}
