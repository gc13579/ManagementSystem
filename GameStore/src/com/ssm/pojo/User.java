package com.ssm.pojo;

import java.util.Date;

public class User {
	private Integer id;
	private String account;
	private String password;
	private Date birthday;
	private String iphone;
	private Double tariffe;
	private Double currency;
	private String status;
	private String sex;
	private Date createTime;
	private String province;
	public User() {
	}

	public User(Integer id, String account, String password, Date birthday,
			String iphone, Double tariffe, Double currency, String status,
			String sex, Date createTime, String province) {
		super();
		this.id = id;
		this.account = account;
		this.password = password;
		this.birthday = birthday;
		this.iphone = iphone;
		this.tariffe = tariffe;
		this.currency = currency;
		this.status = status;
		this.sex = sex;
		this.createTime = createTime;
		this.province = province;
	}

	@Override
	public String toString() {
		return "User [account=" + account + ", birthday=" + birthday
				+ ", createTime=" + createTime + ", currency=" + currency
				+ ", id=" + id + ", iphone=" + iphone + ", password="
				+ password + ", sex=" + sex + ", status=" + status
				+ ", tariffe=" + tariffe + ",province=" + province + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getIphone() {
		return iphone;
	}

	public void setIphone(String iphone) {
		this.iphone = iphone;
	}

	public Double getTariffe() {
		return tariffe;
	}

	public void setTariffe(Double tariffe) {
		this.tariffe = tariffe;
	}

	public Double getCurrency() {
		return currency;
	}

	public void setCurrency(Double currency) {
		this.currency = currency;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}
	
	

}
