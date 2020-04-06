package com.gc_company.service.impl;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

import com.gc_company.Constant.Constant;
import com.gc_company.dao.CountDao;
import com.gc_company.dao.PurchaseRecordDao;
import com.gc_company.dao.TicketDao;
import com.gc_company.dao.UserDao;
import com.gc_company.enity.PurchaseRecord;
import com.gc_company.enity.Ticket;
import com.gc_company.enity.User;
import com.gc_company.exception.InsufficientMoneyException;
import com.gc_company.exception.SameTrainnnumExistedException;
import com.gc_company.exception.YouBoughtTheTicketException;
import com.gc_company.factory.ObjectFactory;
import com.gc_company.service.TicketService;
import com.gc_company.util.Pager;

public class TicketServiceImpl implements TicketService {
	TicketDao ticketDao = (TicketDao) ObjectFactory.getObject("ticketDao");
	UserDao userDao = (UserDao) ObjectFactory.getObject("userDao");
	CountDao countDao = (CountDao) ObjectFactory.getObject("countDao");
	PurchaseRecordDao purchaseRecordDao = (PurchaseRecordDao) ObjectFactory.getObject("purchaseRecordDao");

	@Override
	public Pager<Ticket> getTicketByPageAndCond(String startPlace, String endPlace, String startTime, Integer pageNum) throws Exception {
		Pager<Ticket> page = new Pager<Ticket>();
		page.setPageNum(pageNum);
		// 根据条件查询车次，把所有满足的车次存在集合
		List<Ticket> list = ticketDao.selectTicketsByPageAndCond(startPlace, endPlace, startTime, pageNum, Constant.PAGE_SIZE_3);
		// 用页存放查到的数据
		page.setData(list);
		// 根据条件查询满足的车次有多少趟
		Integer totalCount = countDao.countTicketsByConditons(startPlace, endPlace, startTime);
		page.setTotalPage(totalCount, Constant.PAGE_SIZE_3);
		return page;
	}

	@Override
	public void buyOneTicket(User user, String trainNum) throws  InsufficientMoneyException, YouBoughtTheTicketException, Exception {
		// 查购买记录是否有这趟车次
		Ticket ticket = ticketDao.selectOneTicketByTrainnum(trainNum);
		//查询包含倒序显示记录
		Integer ticketCount=ticket.getCount();
		PurchaseRecord purchaseRecord=purchaseRecordDao.selectOneRecord(ticket.getId());
		if (ticket.getPrice() > user.getMoney()) {
			throw new InsufficientMoneyException("很抱歉，您的余额不足，请充值后再试");
		}
		if (purchaseRecord != null && "已购买".equals(purchaseRecord.getBuyState())) {
			throw new YouBoughtTheTicketException("很抱歉，您已购买本车票,不能重复购买");
		}
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		purchaseRecordDao.insertOneRecord(user.getId(), ticket.getId(),simpleDateFormat.format(new java.util.Date()), "已购买");
		user.setMoney(user.getMoney() - ticket.getPrice());
		userDao.updateUserMoneyById(user.getId(), user.getMoney());
		// 车票减一
		ticketDao.updateOneTicketCountByTrainnum(trainNum, --ticketCount);
	}

	@Override
	public void deleteOneTicket(String trainNum) throws Exception {
		ticketDao.deleteOneTicketByTrainnum(trainNum);
	}

	@Override
	public void addOneTicket(Ticket ticket) throws SameTrainnnumExistedException, Exception {
		// 拿到所有车，比对车次
		List<Ticket> list = ticketDao.selectAllTickets();
		for (Ticket ticket2 : list) {
			if (ticket2.getTrainnum().equals(ticket.getTrainnum())) {
				throw new SameTrainnnumExistedException("该车次已存在，请重新输入车次");
			}
		}
		ticketDao.insertOneTicket(ticket);
	}

	@Override
	public void updateOneTicket(Ticket ticket) throws Exception {
		ticketDao.updateOneTicket(ticket);
	}

	@Override
	public void updateOneTicketStateByTrainnum(String trainNum, String state) throws SQLException {
		ticketDao.updateOneTicketStateByTrainnum(trainNum, state);
	}

	@Override
	public Ticket selectOneTicketByTrainnum(String trainNum) throws SQLException, Exception {
		Ticket ticket=ticketDao.selectOneTicketByTrainnum(trainNum);
		return ticket;
	}

}
