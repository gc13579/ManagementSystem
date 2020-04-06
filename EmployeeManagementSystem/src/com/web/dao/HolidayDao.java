package com.web.dao;

import java.util.List;

import com.web.pojo.Holiday;

public interface HolidayDao {
    //通过姓名查询请假信息
	public List<Holiday> selectHolidayByEmpName(String empName, String holidayType, String holidayStates);
    //通过请假id删除请假
	public void deleteHoliday(String holidayNo);
    //通过请假编号查询请假
	public Holiday selectHolidayByNo(String holidayNo);
    //添加请假
	public void insertHoliday(Holiday holiday);
	//查询当前最新的请假编号
	public String selectMaxNo();
    //修改请假
	public void updateHoliday(Holiday holiday);
	
}
