package com.gc_company.service.proxy;

import java.util.List;

import com.gc_company.enity.PurchaseRecord;
import com.gc_company.enity.User;
import com.gc_company.factory.ObjectFactory;
import com.gc_company.service.PurchaseRecordService;
import com.gc_company.trans.Transation;

public class PurchaseRecordServiceProxy implements PurchaseRecordService {
	Transation trans = (Transation) ObjectFactory.getObject("trans");
	PurchaseRecordService purchaseRecordService = (PurchaseRecordService) ObjectFactory
			.getObject("purchaseRecordServiceImpl");

	// @Override
	// public void addOneBuyRecord(String trainNum, String user_id, String
	// buyTime, String state) throws Exception {
	// trans.begin();
	// try {
	// purchaseRecordService.addOneBuyRecord(trainNum, user_id, buyTime, state);
	// trans.commit();
	// } catch (Exception e) {
	// trans.rollback();
	// e = new Exception("购票记录添加未成功，请稍后再试");
	// throw e;
	// }
	// }
	// @Override
	// public void addOneBuyRecord(Integer u_id, Integer t_id, String buytime,
	// String buystate) throws Exception {
	// // TODO Auto-generated method stub
	// trans.begin();
	// try {
	// purchaseRecordService.addOneBuyRecord(u_id, t_id, buytime, buystate);
	// trans.commit();
	// } catch (Exception e) {
	// trans.rollback();
	// e = new Exception("购票记录未能成功显示，请稍后再试");
	// throw e;
	// }
	// }
	@Override
	public List<PurchaseRecord> showAllRecords() throws Exception {
		trans.begin();
		List<PurchaseRecord> list;
		try {
			list = purchaseRecordService.showAllRecords();
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
			e = new Exception("购票记录未能成功显示，请稍后再试");
			throw e;
		}
		return list;
	}

	@Override
	public void returnOneTicket(String trainnum, User user) throws Exception {
		trans.begin();
		try {
			purchaseRecordService.returnOneTicket(trainnum, user);
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
			e = new Exception("购票记录未能修改显示，请稍后再试");
			throw e;
		}
	}
	// @Override
	// public void returnOneTicket(String trainNum) throws Exception {
	// trans.begin();
	// try {
	// purchaseRecordService.returnOneTicket(trainNum);
	// trans.commit();
	// } catch (Exception e) {
	// trans.rollback();
	// e = new Exception("购票记录未能修改显示，请稍后再试");
	// throw e;
	// }
	// }

}
