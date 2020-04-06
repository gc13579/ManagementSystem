package com.gc_company.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.gc_company.dao.CountDao;
import com.gc_company.mapper.CountMapper;
import com.gc_company.util.JDBCTemplate;

public class CountDaoImpl implements CountDao {
	JDBCTemplate<Integer> temp = new JDBCTemplate<Integer>();
	@Override
	public Integer countTicketsByConditons(String startPlace, 
			String endPlace, String startTime) {
		List<String> params=new ArrayList<String>();
		StringBuffer sql = new StringBuffer()
			.append(" select ")
			.append(" 	count(id) num ")
			.append(" from ")
			.append(" 	t_ticket ")
			.append(" where 1 = 1 ")
			.append(" 	and state != ? ");
		params.add("已删除");
		if(startPlace!=null&&!"".equals(startPlace.trim())){
			sql.append(" and startplace = ? ");
			params.add(startPlace);
		}
		if(endPlace!=null&&!"".equals(endPlace.trim())){
			sql.append(" and endplace = ? ");
			params.add(endPlace);
		}
		if(startTime!=null&&!"".equals(startTime.trim())){
			sql.append(" and starttime = ? ");
			params.add(startTime);
		}
		
		return temp.selectOne(new CountMapper(), sql.toString(),params.toArray());
	}

}
