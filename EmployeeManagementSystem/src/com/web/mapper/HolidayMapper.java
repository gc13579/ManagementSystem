package com.web.mapper;

import java.sql.ResultSet;

import com.web.pojo.Holiday;

public class HolidayMapper implements RowMapper<Holiday>{

	public Holiday mapperObject(ResultSet rs) throws Exception {
		Holiday holiday = new Holiday();
		holiday.setId(rs.getInt("id"));
		holiday.setHolidayNo(rs.getString("t_holiday_no"));
		holiday.setHolidayUser(rs.getString("t_holiday_user"));
		holiday.setHolidayType(rs.getString("t_holiday_type"));
		holiday.setHolidayBz(rs.getString("t_holiday_bz"));
		holiday.setStartTime(rs.getString("t_start_time"));
		holiday.setEndTime(rs.getString("t_end_time"));
		holiday.setHolidayStates(rs.getString("t_holiday_states"));
		holiday.setCreateTime(rs.getTimestamp("t_create_time"));
		return holiday;
	}
	
}
