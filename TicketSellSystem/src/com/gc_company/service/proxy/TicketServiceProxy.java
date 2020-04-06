package com.gc_company.service.proxy;

import java.sql.SQLException;
import com.gc_company.enity.Ticket;
import com.gc_company.enity.User;
import com.gc_company.exception.InsufficientMoneyException;
import com.gc_company.exception.SameTrainnnumExistedException;
import com.gc_company.exception.YouBoughtTheTicketException;
import com.gc_company.factory.ObjectFactory;
import com.gc_company.service.TicketService;
import com.gc_company.trans.Transation;
import com.gc_company.util.Pager;

public class TicketServiceProxy implements TicketService {
	Transation trans = (Transation) ObjectFactory.getObject("trans");
	TicketService ticketService = (TicketService) ObjectFactory
			.getObject("ticketServiceImpl");

	@Override
	public Pager<Ticket> getTicketByPageAndCond(String startPlace,
			String endPlace, String startTime, Integer pageNum)
			throws Exception {
		Pager<Ticket> pager = null;
		trans.begin();
		try {
			pager = ticketService.getTicketByPageAndCond(startPlace, endPlace,
					startTime, pageNum);
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
			e = new Exception("查询意外终止，请稍后重试");
			throw e;
		}
		return pager;
	}

	@Override
	public void buyOneTicket(User user, String trainNum)
			throws InsufficientMoneyException, YouBoughtTheTicketException,
			Exception {
		trans.begin();
		try {
			ticketService.buyOneTicket(user, trainNum);
			trans.commit();
		} catch (InsufficientMoneyException e) {
			throw e;
		} catch (YouBoughtTheTicketException e) {
			throw e;
		} catch (Exception e) {
			trans.rollback();
			e = new Exception("购买出现异常，请稍后再试");
			throw e;
		}
	}

	@Override
	public void deleteOneTicket(String trainNum) throws SQLException, Exception {
		trans.begin();
		try {
			ticketService.deleteOneTicket(trainNum);
			trans.commit();
		} catch (SQLException e) {
			throw e;
		} catch (Exception e) {
			trans.rollback();
			e = new Exception("删除出现异常，请稍后再试");
			throw e;
		}
	}

	@Override
	public void addOneTicket(Ticket ticket)
			throws SameTrainnnumExistedException, Exception {
		trans.begin();
		try {
			ticketService.addOneTicket(ticket);
			trans.commit();
		} catch (SameTrainnnumExistedException e) {
			throw e;
		} catch (Exception e) {
			trans.rollback();
			e = new Exception("添加出现异常，请稍后再试");
			throw e;
		}
	}

	@Override
	public void updateOneTicket(Ticket ticket) throws Exception {
		trans.begin();
		try {
			ticketService.updateOneTicket(ticket);
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
			e = new Exception("修改出现异常，请稍后再试");
			throw e;
		}
	}

	@Override
	public void updateOneTicketStateByTrainnum(String trainNum, String state)
			throws Exception {
		trans.begin();
		try {
			ticketService.updateOneTicketStateByTrainnum(trainNum, state);
			trans.commit();
		} catch (SQLException e) {
			throw e;
		} catch (Exception e) {
			trans.rollback();
			e = new Exception("修改出现异常，请稍后再试");
			throw e;
		}
	}

	@Override
	public Ticket selectOneTicketByTrainnum(String trainNum)
			throws SQLException, Exception {
		trans.begin();
		Ticket ticket;
		try {
			ticket = ticketService.selectOneTicketByTrainnum(trainNum);
			trans.commit();
		} catch (SQLException e) {
			throw e;
		} catch (Exception e) {
			trans.rollback();
			e = new Exception("查找出现异常，请稍后再试");
			throw e;
		}
		return ticket;
	}

}
