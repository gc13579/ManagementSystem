package com.gc_company.service;

import java.sql.SQLException;
import com.gc_company.enity.Ticket;
import com.gc_company.enity.User;
import com.gc_company.exception.InsufficientMoneyException;
import com.gc_company.exception.SameTrainnnumExistedException;
import com.gc_company.exception.YouBoughtTheTicketException;
import com.gc_company.util.Pager;

public interface TicketService {
	public Pager<Ticket> getTicketByPageAndCond(String startPlace, String endPlace, String startTime, Integer pageNum) throws Exception;

	public void buyOneTicket(User user, String trainNum) throws  InsufficientMoneyException,YouBoughtTheTicketException, Exception;

	public void deleteOneTicket(String trainNum) throws Exception;

	public void addOneTicket(Ticket ticket) throws SameTrainnnumExistedException,Exception;

	public void updateOneTicket(Ticket ticket) throws Exception;
	
	public void updateOneTicketStateByTrainnum(String trainNum,String state) throws SQLException, Exception;

	public Ticket selectOneTicketByTrainnum(String trainNum) throws SQLException, Exception;
}
