package com.web.VO;

import com.web.pojo.Holiday;

public class HolidayVO extends Holiday {

	private String deptManagerName;

	private Integer totalDays;

	private String deptName;

	public void setHoliday(Holiday holiday) {
		this.setId(holiday.getId());
		this.setHolidayNo(holiday.getHolidayNo());
		this.setHolidayUser(holiday.getHolidayUser());
		this.setHolidayType(holiday.getHolidayType());
		this.setHolidayBz(holiday.getHolidayBz());
		this.setStartTime(holiday.getStartTime());
		this.setEndTime(holiday.getEndTime());
		this.setHolidayStates(holiday.getHolidayStates());
		this.setCreateTime(holiday.getCreateTime());
	}

	public String getDeptManagerName() {
		return deptManagerName;
	}

	public void setDeptManagerName(String deptManagerName) {
		this.deptManagerName = deptManagerName;
	}

	public Integer getTotalDays() {
		return totalDays;
	}

	public void setTotalDays(Integer totalDays) {
		this.totalDays = totalDays;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
}
