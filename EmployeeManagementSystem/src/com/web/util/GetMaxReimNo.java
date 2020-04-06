package com.web.util;

import com.web.dao.ReimbursementDao;
import com.web.dao.impl.ReimbursementDaoImpl;

public class GetMaxReimNo {	
	public static String  createReimNo(){
		String newReimNo = "";
		ReimbursementDao reimbursementDao = new ReimbursementDaoImpl();
		String reimNo = reimbursementDao.selectMaxNo();
		String reimNoPrefix = reimNo.substring(0,2);
		String reimNoSuffix = reimNo.substring(2);
		Integer integer = Integer.parseInt(reimNoSuffix);
		newReimNo = reimNoPrefix +(integer + 1);
		return newReimNo;
	}
}
