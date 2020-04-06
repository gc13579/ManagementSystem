package com.ssm.pojo;

import java.util.Date;

public class Prepaid {
	private Integer id;
	private String account;
	private String password;
	private Integer userId;
	private String userAccount;
	private Date prepaidTime;
	private Double money;

	public Prepaid() {
	}

	public Prepaid(Integer id, String account, String password, Integer userId,
			String userAccount, Date prepaidTime, Double money) {
		super();
		this.id = id;
		this.account = account;
		this.password = password;
		this.userId = userId;
		this.userAccount = userAccount;
		this.prepaidTime = prepaidTime;
		this.money = money;
	}

	@Override
	public String toString() {
		return "Prepaid [id=" + id + ", account=" + account + ", password="
				+ password + ", userId=" + userId + ", userAccount="
				+ userAccount + ", prepaidTime=" + prepaidTime + ",money="
				+ money + "]";
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

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	public Date getPrepaidTime() {
		return prepaidTime;
	}

	public void setPrepaidTime(Date prepaidTime) {
		this.prepaidTime = prepaidTime;
	}

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

}