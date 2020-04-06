package com.web.pojo;

import java.util.Date;

public class Holiday {
	private Integer id;
	private String holidayNo;
	private String holidayUser;
	private String holidayType;
	private String holidayBz;
	private String startTime;
	private String endTime;
	private String holidayStates;
	private Date createTime;
	
	
	
	public Holiday() {
	}
	public Holiday(Integer id, String holidayNo, String holidayUser, String holidayType, String holidayBz, String startTime, String endTime, String holidayStates, Date createTime) {
		this.id = id;
		this.holidayNo = holidayNo;
		this.holidayUser = holidayUser;
		this.holidayType = holidayType;
		this.holidayBz = holidayBz;
		this.startTime = startTime;
		this.endTime = endTime;
		this.holidayStates = holidayStates;
		this.createTime = createTime;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getHolidayNo() {
		return holidayNo;
	}
	public void setHolidayNo(String holidayNo) {
		this.holidayNo = holidayNo;
	}
	public String getHolidayUser() {
		return holidayUser;
	}
	public void setHolidayUser(String holidayUser) {
		this.holidayUser = holidayUser;
	}
	public String getHolidayType() {
		return holidayType;
	}
	public void setHolidayType(String holidayType) {
		this.holidayType = holidayType;
	}
	public String getHolidayBz() {
		return holidayBz;
	}
	public void setHolidayBz(String holidayBz) {
		this.holidayBz = holidayBz;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getHolidayStates() {
		return holidayStates;
	}
	public void setHolidayStates(String holidayStates) {
		this.holidayStates = holidayStates;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	
	
}
