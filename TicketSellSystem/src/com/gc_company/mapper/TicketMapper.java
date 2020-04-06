package com.gc_company.mapper;

import java.sql.ResultSet;

import com.gc_company.enity.Ticket;

public class TicketMapper implements RowMapper<Ticket> {

	@Override
	public Ticket mapperObject(ResultSet rs) throws Exception {
		Ticket ticket = new Ticket();
		ticket.setId(rs.getInt("id"));
		ticket.setTrainnum(rs.getString("trainnum"));
		ticket.setStartPlace(rs.getString("startplace"));
		ticket.setEndPlace(rs.getString("endplace"));
		ticket.setStartTime(rs.getString("starttime"));
		ticket.setCount(rs.getInt("count"));
		ticket.setPrice(rs.getDouble("price"));
		ticket.setState(rs.getString("state"));
		return ticket;
	}

}
