package com.gc_company.mapper;

import java.sql.ResultSet;

public class CountMapper implements RowMapper<Integer>{

	@Override
	public Integer mapperObject(ResultSet rs) throws Exception {
		return rs.getInt("num");
	}

}
