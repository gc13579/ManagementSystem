package com.gc_company.dao;

import java.sql.SQLException;
import java.util.List;

import com.gc_company.enity.Ticket;

public interface TicketDao {
	public List<Ticket> selectTicketsByPageAndCond(String startPlace, String endPlace, String startTime, Integer pageNum, Integer pageSize);

	public List<Ticket> selectAllTickets();
	
	public Ticket selectOneTicketByTrainnum(String tarinNum);
 
	
	public void updateOneTicketCountByTrainnum(String tarinNum, Integer ticketCount) throws SQLException;

	public void updateOneTicketStateByTrainnum(String tarinNum,String ticektState) throws SQLException;
	
	public void deleteOneTicketByTrainnum(String trainNum) throws Exception;

	public void insertOneTicket(Ticket ticket) throws SQLException;

	public void updateOneTicket(Ticket ticket) throws SQLException;
}
