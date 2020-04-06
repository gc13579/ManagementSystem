package com.gc_company.service.proxy;

import java.util.List;

import com.gc_company.enity.RechargeApply;
import com.gc_company.factory.ObjectFactory;
import com.gc_company.service.RechargeApplyService;
import com.gc_company.trans.Transation;

public class RechargeApplyServiceProxy implements RechargeApplyService {
	Transation trans = (Transation) ObjectFactory.getObject("trans");
	RechargeApplyService rechargeApplyService = (RechargeApplyService) ObjectFactory.getObject("rechargeApplyServiceImpl");

	@Override
	public void addOneRecord(Integer userId, Double applyMoney, String applyTime, String applyState) throws Exception {
		trans.begin();
		try {
			rechargeApplyService.addOneRecord(userId, applyMoney, applyTime, applyState);
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
			e = new Exception("申请失败，请稍后重试");
			throw e;
		}
	}

	@Override
	public List<RechargeApply> showAllRecords() throws Exception {
		trans.begin();
		List<RechargeApply> list=null;
		try {
			 list=rechargeApplyService.showAllRecords();
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
			e = new Exception("显示用户申请失败，请稍后重试");
			throw e;
		}
		return list;
	}

	@Override
	public void updateOneRecord(RechargeApply rechargeApply,String userName,Double money) throws Exception {
		trans.begin();
		try {
			rechargeApplyService.updateOneRecord(rechargeApply,userName,money);
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			e = new Exception("处理申请失败，请稍后重试");
			throw e;
		}
	}

}
