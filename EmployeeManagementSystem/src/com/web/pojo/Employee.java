package com.web.pojo;

import java.util.Date;

public class Employee {
	private Integer id;
	private String empNo;
	private String empName;
	private String empDept;
	private String sex;
	private String education;
	private String email;
	private String phone;
	private Date entryTime;
	private Date createTime;

	@Override
	public String toString() {
		return "Employee [createTime=" + createTime + ", education=" + education + ", email=" + email + ", empDept=" + empDept + ", empName=" + empName + ", empNo=" + empNo + ", entryTime="
				+ entryTime + ", id=" + id + ", phone=" + phone + ", sex=" + sex + "]";
	}

	public Employee() {
	}

	public Employee(Integer id, String empNo, String empName, String empDept, String sex, String education, String email, String phone, Date entryTime, Date createTime) {
		this.id = id;
		this.empNo = empNo;
		this.empName = empName;
		this.empDept = empDept;
		this.sex = sex;
		this.education = education;
		this.email = email;
		this.phone = phone;
		this.entryTime = entryTime;
		this.createTime = createTime;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmpNo() {
		return empNo;
	}

	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getEmpDept() {
		return empDept;
	}

	public void setEmpDept(String empDept) {
		this.empDept = empDept;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getEntryTime() {
		return entryTime;
	}

	public void setEntryTime(Date entryTime) {
		this.entryTime = entryTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
