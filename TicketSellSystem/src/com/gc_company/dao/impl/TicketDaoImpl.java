package com.gc_company.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.gc_company.dao.TicketDao;
import com.gc_company.enity.Ticket;
import com.gc_company.mapper.TicketMapper;
import com.gc_company.util.JDBCTemplate;

public class TicketDaoImpl implements TicketDao{
	JDBCTemplate<Ticket> temp = new JDBCTemplate<Ticket>();
	@Override
	public List<Ticket> selectTicketsByPageAndCond(String startPlace,
			String endPlace, String date, Integer pageNum, Integer pageSize) {
		List params=new ArrayList();
		StringBuffer sql=new StringBuffer()
			.append(" select ")	
			.append(" 	id,trainnum,startplace,endplace,starttime,count,price,state ")	
			.append(" from ")	
			.append(" 	t_ticket ")	
			.append(" where ")	
			.append(" 	1 = 1 ")
			.append(" and state != ? ");	
		params.add("已删除");
		if(startPlace!=null&&!"".equals(startPlace.trim())){
			sql.append(" and startplace = ? ");
			params.add(startPlace);
		}
		if(endPlace!=null&&!"".equals(endPlace.trim())){
			sql.append(" and endPlace = ? ");
			params.add(endPlace);
		}
		if(date!=null&&!"".equals(date.trim())){
			sql.append(" and starttime = ? ");
			params.add(date);
		}
		sql.append(" limit ")
			.append(" 	?,? ")
			.toString();
		params.add((pageNum-1)*pageSize);
		params.add(pageSize);
		return temp.selectAll(new TicketMapper(),
				sql.toString(),params.toArray());
		 
	}
	
	
	@Override
	public Ticket selectOneTicketByTrainnum(String tarinNum) {
		String sql=new StringBuffer()
			.append(" select ")
			.append(" 	id,trainnum,startplace,endplace,starttime,count,price,state ")
			.append(" from ")
			.append(" 	t_ticket ")
			.append(" where ")
			.append(" 	trainnum = ? ")
			.toString();
		return temp.selectOne(new TicketMapper(), sql, tarinNum);
		
	}



	@Override
	public void deleteOneTicketByTrainnum(String trainNum)  throws Exception{
		String sql=new StringBuffer()
			.append(" delete ")
			.append(" from ")
			.append(" 	t_ticket ")
			.append(" where ")
			.append(" 	trainnum = ? ")
			.toString();
		temp.delete(sql, trainNum);
	}


	@Override
	public void insertOneTicket(Ticket ticket) throws SQLException {
		String sql=new StringBuffer()
			.append(" insert into ")
			.append(" 	t_ticket ")
			.append(" values ")
			.append(" 	(?,?,?,?,?,?,?,?) ")
			.toString();
		temp.insert(sql,
				null,ticket.getStartPlace(),ticket.getEndPlace(),
				ticket.getStartTime(),ticket.getCount(),"可购买",
				ticket.getPrice(),ticket.getTrainnum());
		}
	//只查名字的话，要重写一个mapper
	@Override
	public List<Ticket> selectAllTickets() {
		String sql=new StringBuffer()
			.append(" select ")
			.append(" 	trainnum,startplace,endplace,starttime,count,price,state ")
			.append(" from ")
			.append(" 	t_ticket ")
			.toString();
		return temp.selectAll(new TicketMapper(), sql);
	}
	@Override
	public void updateOneTicketCountByTrainnum(String tarinNum,Integer ticketCount) throws SQLException {	
		String sql=new StringBuffer()
			.append(" update ")
			.append(" 	t_ticket ")
			.append(" set ")
			.append(" 	count = ? ")
			.append(" where ")
			.append(" 	trainnum = ? ")
			.toString();
		temp.update(sql,ticketCount,tarinNum);
	}
	@Override
	public void updateOneTicket(Ticket ticket) throws SQLException {
		String sql=new StringBuffer()
			.append(" update ")
			.append(" 	t_ticket ")
			.append(" set ")
			.append(" 	trainnum = ? , ")
			.append(" 	startplace = ? , ")
			.append(" 	endplace = ? , ")
			.append(" 	starttime = ? , ")
			.append(" 	count = ? , ")
			.append(" 	state = ? , ")
			.append(" 	price = ? ")
			.append(" where ")
			.append("   trainnum = ? ")
			.toString();
		temp.update(sql,
			ticket.getTrainnum(),ticket.getStartPlace(),ticket.getEndPlace(),
			ticket.getStartTime(),ticket.getCount(),ticket.getState(),
			ticket.getPrice(),ticket.getTrainnum());
	}

	
	@Override
	public void updateOneTicketStateByTrainnum(String tarinNum, String ticektState) throws SQLException {
		String sql=new StringBuffer()
		.append(" update ")
		.append(" 	t_ticket ")
		.append(" set ")
		.append(" 	state = ? ")
		.append(" where ")
		.append(" 	trainnum = ? ")
		.toString();
	temp.update(sql,ticektState,tarinNum);
	}


	

}
