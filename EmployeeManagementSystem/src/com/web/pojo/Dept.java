package com.web.pojo;

import java.util.Date;

public class Dept {
	private String deptNo;
	private String deptName;
	private String deptLoc;
	private String deptManager;
	private Date createTime;
	private String descrption;

	public Dept() {
	}

	public Dept(String deptNo, String deptName, String deptLoc, String deptManager, Date createTime, String descrption) {
		this.deptNo = deptNo;
		this.deptName = deptName;
		this.deptLoc = deptLoc;
		this.deptManager = deptManager;
		this.createTime = createTime;
		this.descrption = descrption;
	}

	@Override
	public String toString() {
		return "Dept [createTime=" + createTime + ", deptLoc=" + deptLoc + ", deptManager=" + deptManager + ", deptName=" + deptName + ", deptNo=" + deptNo + ", descrption=" + descrption + "]";
	}

	public String getDeptNo() {
		return deptNo;
	}

	public void setDeptNo(String deptNo) {
		this.deptNo = deptNo;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getDeptLoc() {
		return deptLoc;
	}

	public void setDeptLoc(String deptLoc) {
		this.deptLoc = deptLoc;
	}

	public String getDeptManager() {
		return deptManager;
	}

	public void setDeptManager(String deptManager) {
		this.deptManager = deptManager;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getDescrption() {
		return descrption;
	}

	public void setDescrption(String descrption) {
		this.descrption = descrption;
	}

}
