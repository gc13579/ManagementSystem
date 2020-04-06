package com.web.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.web.dao.HolidayDao;
import com.web.mapper.HolidayMapper;
import com.web.pojo.Holiday;
import com.web.util.JDBCTemplate;

public class HolidayDaoImpl implements HolidayDao{
	JDBCTemplate<Holiday> temp = new JDBCTemplate<Holiday>();
	public List<Holiday> selectHolidayByEmpName(String empName, String holidayType, String holidayStates) {
		StringBuffer sql = new StringBuffer()
			.append(" select ")
			.append(" 	id,t_holiday_no,t_holiday_user,t_holiday_type,t_holiday_bz,t_start_time,t_end_time,t_holiday_states,t_create_time ")
			.append(" from ")
			.append(" 	t_holiday ")
			.append(" where ")
			.append(" 	1 = 1")
			;
		List<Object> params = new ArrayList<Object>();
		if(holidayType != null && !holidayType.equals("")){
			sql.append(" and t_holiday_type like ? ");
			params.add("%"+holidayType+"%") ;
		}
		if(empName != null && !empName.equals("")){
			sql.append(" and t_holiday_user like ? ");
			params.add("%"+empName+"%") ;
		}
		if(holidayStates != null && !holidayStates.equals("")){
			sql.append(" and t_holiday_states like ? ");
			params.add("%"+holidayStates+"%") ;
		}
		return temp.selectAll(new HolidayMapper(), sql.toString(),params.toArray());
	}
	public void deleteHoliday(String holidayNo) {
		String sql = new StringBuffer()
			.append(" delete from ")
			.append(" 	t_holiday ")
			.append(" where ")
			.append(" 	t_holiday_no = ? ")
			.toString();
		temp.delete(sql, holidayNo);
	}
	public Holiday selectHolidayByNo(String holidayNo) {
		String sql = new StringBuffer()
			.append(" select ")
			.append(" 	id,t_holiday_no,t_holiday_user,t_holiday_type,t_holiday_bz,t_start_time,t_end_time,t_holiday_states,t_create_time ")
			.append(" from ")
			.append(" 	t_holiday ")
			.append(" where ")
			.append(" 	t_holiday_no = ? ")
			.toString();
		return temp.selectOne(new HolidayMapper(), sql, holidayNo);
	}
	public void insertHoliday(Holiday holiday) {
		String sql = new StringBuffer()
			.append(" insert into ")
			.append(" 	t_holiday(id,t_holiday_no,t_holiday_user,t_holiday_type,t_holiday_bz,t_start_time,t_end_time,t_holiday_states,t_create_time) ")
			.append(" values ")
			.append(" 	(null,?,?,?,?,?,?,?,sysdate()) ")
			.toString();
		temp.insert(sql, holiday.getHolidayNo(),holiday.getHolidayUser(),holiday.getHolidayType(),
				holiday.getHolidayBz(),holiday.getStartTime(),holiday.getEndTime(),holiday.getHolidayStates());
		
	}
	public String selectMaxNo(){
		String sql = new StringBuffer()
		.append(" select ")
		.append(" 	id,t_holiday_no,t_holiday_user,t_holiday_type,t_holiday_bz,t_start_time,t_end_time,t_holiday_states,t_create_time ")
		.append(" from ")
		.append(" 	t_holiday ")
		.append(" where ")
		.append(" 	t_holiday_no = (select max(t_holiday_no) from t_holiday) ")
		.toString();
		return temp.selectOne(new HolidayMapper(), sql).getHolidayNo();
	}
	public void updateHoliday(Holiday holiday) {
		String sql = new StringBuffer()
			.append(" update ")
			.append(" 	t_holiday ")
			.append(" set ")
			.append(" 	t_holiday_type=?,t_holiday_bz=?,t_start_time=?,t_end_time=?,t_holiday_states=?,t_create_time=sysdate() ")
			.append(" where ")
			.append(" 	t_holiday_no = ? ")
			.toString();
		temp.update(sql, holiday.getHolidayType(),holiday.getHolidayBz(),holiday.getStartTime(),
				holiday.getEndTime(),holiday.getHolidayStates(),holiday.getHolidayNo());
	}

}
