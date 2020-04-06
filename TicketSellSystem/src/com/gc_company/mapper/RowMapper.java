package com.gc_company.mapper;

import java.sql.ResultSet;

public interface RowMapper<T> {
	public T mapperObject(ResultSet rs) throws Exception;
}
