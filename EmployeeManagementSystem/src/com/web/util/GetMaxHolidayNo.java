package com.web.util;

import com.web.dao.HolidayDao;
import com.web.dao.impl.HolidayDaoImpl;

public class GetMaxHolidayNo {
	public static String createHolidayNo(){
		String newHolidayNo = "";
		HolidayDao holidayDao = new HolidayDaoImpl();
		String holidayNo = holidayDao.selectMaxNo();
		String holidayNoPrefix = holidayNo.substring(0,2);
		String holidayNoSuffix = holidayNo.substring(2);
		Integer integer = Integer.parseInt(holidayNoSuffix);
		newHolidayNo = holidayNoPrefix +(integer + 1);
		return newHolidayNo;
	}
}
