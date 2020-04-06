package com.gc_company.service.impl;

import java.util.List;

import com.gc_company.dao.PurchaseRecordDao;
import com.gc_company.dao.TicketDao;
import com.gc_company.dao.UserDao;
import com.gc_company.enity.PurchaseRecord;
import com.gc_company.enity.Ticket;
import com.gc_company.enity.User;
import com.gc_company.factory.ObjectFactory;
import com.gc_company.service.PurchaseRecordService;

public class PurchaseRecordServiceImpl implements PurchaseRecordService {
	PurchaseRecordDao purchaseRecordDao = (PurchaseRecordDao) ObjectFactory
			.getObject("purchaseRecordDao");
	TicketDao ticketDao = (TicketDao) ObjectFactory.getObject("ticketDao");
	UserDao userDao = (UserDao) ObjectFactory.getObject("userDao");

	@Override
	public List<PurchaseRecord> showAllRecords() throws Exception {
		return purchaseRecordDao.selectAllRecords();
	}

	// @Override
	// public void addOneBuyRecord(Integer u_id, Integer t_id, String buytime,
	// String buystate) throws Exception {
	// purchaseRecordDao.insertOneRecord(u_id, t_id, buytime, buystate);
	// }

	@Override
	public void returnOneTicket(String trainnum, User user) throws Exception {
		Ticket ticket = ticketDao.selectOneTicketByTrainnum(trainnum);
		PurchaseRecord purchaseRecord = purchaseRecordDao
				.selectOneRecord(ticket.getId());
		purchaseRecord.setBuyState("已退票");
		userDao.updateUserMoneyById(user.getId(), user.getMoney()+ticket.getPrice()*0.8);
		purchaseRecordDao.updateOneRecordState(purchaseRecord);
		ticket.setCount(ticket.getCount()+1);
		ticketDao.updateOneTicketCountByTrainnum(ticket.getTrainnum(), ticket.getCount());
	}

}
