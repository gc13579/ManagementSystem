package com.web.service;

import java.util.List;

import com.web.VO.HolidayVO;
import com.web.pojo.Holiday;

public interface HolidayService {
    //通过姓名获取请假
	public List<Holiday> getHolidayByEmpName(String empName,String holidayType,String holidayStates)throws Exception;
   //删除请假
	public void removeHoliday(String holidayNo) throws Exception;
   //通过编号获取假期
	public Holiday getHolidayByNo(String holidayNo) throws Exception;
   //添加请假
	public void addHoliday(String holidayNo,String holidayUser, String holidayInfo,
			String startTime, String endTime, String holidayType,String holidayStates) throws Exception;
    //修改请假
	public void changeHoliday(String holidayNo,String holidayType, String holidayBz, 
			String startTime, String endTime, String holidayStates) throws Exception;
	

}
