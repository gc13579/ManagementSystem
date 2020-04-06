package com.web.service.impl;

import java.util.List;

import com.web.dao.HolidayDao;
import com.web.pojo.Holiday;
import com.web.service.HolidayService;

public class HolidayServiceImpl implements HolidayService {
	private HolidayDao holidayDao;
	public List<Holiday> getHolidayByEmpName(String empName, String holidayType, String holidayStates) throws Exception {
		List<Holiday> holidays = holidayDao.selectHolidayByEmpName(empName,holidayType, holidayStates);
		return holidays;

	}
	public void setHolidayDao(HolidayDao holidayDao) {
		this.holidayDao = holidayDao;
	}
	public HolidayDao getHolidayDao() {
		return holidayDao;
	}
	public void removeHoliday(String holidayNo) throws Exception {
		holidayDao.deleteHoliday(holidayNo);
	}
	public Holiday getHolidayByNo(String holidayNo) throws Exception {
		return holidayDao.selectHolidayByNo(holidayNo);
	}
	public void addHoliday(String holidayNo, String holidayUser, String holidayType, String holidayBz,
			String startTime, String endTime, String holidayStates) throws Exception {
		Holiday holiday = new Holiday();
		holiday.setHolidayNo(holidayNo);
		holiday.setHolidayUser(holidayUser);
		holiday.setHolidayType(holidayType);
		holiday.setHolidayBz(holidayBz);
		holiday.setStartTime(startTime);
		holiday.setEndTime(endTime);
		holiday.setHolidayStates(holidayStates);
		holidayDao.insertHoliday(holiday);
		
	}
	public void changeHoliday(String holidayNo,String holidayType, String holidayBz, String startTime, 
			String endTime, String holidayStates) throws Exception {
		Holiday holiday = new Holiday();
		holiday.setHolidayNo(holidayNo);
		holiday.setHolidayType(holidayType);
		holiday.setHolidayBz(holidayBz);
		holiday.setStartTime(startTime);
		holiday.setEndTime(endTime);
		holiday.setHolidayStates(holidayStates);
		holidayDao.updateHoliday(holiday);
	}
}
