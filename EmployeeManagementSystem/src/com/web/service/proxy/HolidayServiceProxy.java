package com.web.service.proxy;

import java.util.List;

import com.web.VO.HolidayVO;
import com.web.pojo.Holiday;
import com.web.service.HolidayService;
import com.web.trans.Transaction;



public class HolidayServiceProxy implements HolidayService {
	private HolidayService holidayService;
	private Transaction trans;

	public List<Holiday> getHolidayByEmpName(String userName, String holidayType, String holidayStates) throws Exception {
			trans.begin();
			List<Holiday> holidays;
			try {
				holidays = holidayService.getHolidayByEmpName(userName,holidayType,holidayStates);
				trans.commit();
			} catch (Exception e) {
				trans.rollback();
				throw e;
			}
			return holidays;
		}

	public HolidayService getHolidayService() {
		return holidayService;
	}

	public void setHolidayService(HolidayService holidayService) {
		this.holidayService = holidayService;
	}

	public Transaction getTrans() {
		return trans;
	}

	public void setTrans(Transaction trans) {
		this.trans = trans;
	}

	public void removeHoliday(String holidayNo) throws Exception {
		trans.begin();
		try {
			holidayService.removeHoliday(holidayNo);
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
			throw e;
		}
	}

	public Holiday getHolidayByNo(String holidayNo) throws Exception {
		trans.begin();
		Holiday holiday;
		try {
			holiday = holidayService.getHolidayByNo(holidayNo);
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
			throw e;
		}
		return holiday;
	}

	public void addHoliday(String holidayNo,String holidayUser, String holidayInfo,
			String startTime, String endTime, String holidayType,String holidayStates)
			throws Exception {
		trans.begin();
		try {
			holidayService.addHoliday(holidayNo,holidayUser, holidayInfo, startTime, endTime, holidayType, holidayStates);
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
			throw e;
		}
		
	}

	public void changeHoliday(String holidayNo,String holidayType, String holidayBz, String startTime, String endTime, String holidayStates) throws Exception {
		trans.begin();
		try {
			holidayService.changeHoliday(holidayNo,holidayType, holidayBz, startTime, endTime, holidayStates);
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
			throw e;
		}
	}


	
}

	


