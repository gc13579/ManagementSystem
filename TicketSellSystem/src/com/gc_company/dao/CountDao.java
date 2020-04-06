package com.gc_company.dao;

public interface CountDao {
	public Integer countTicketsByConditons(String startPlace, 
			String endPlace, String startTime);
}
