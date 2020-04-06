package com.ssm.pojo;

import java.util.Date;

public class Card {
	private Integer id;
	private String account;
	private String password;
	private String province;
	private Date createTime;
	private Date startTime;
	private Date endTime;
	private String status;
	private Double money;

	public Card() {
	}

	public Card(Integer id, String account, String password, String province,
			Date createTime, Date startTime, Date endTime, String status,
			Double money) {
		super();
		this.id = id;
		this.account = account;
		this.password = password;
		this.province = province;
		this.createTime = createTime;
		this.startTime = startTime;
		this.endTime = endTime;
		this.status = status;
		this.money = money;
	}

	@Override
	public String toString() {
		return "Card [id=" + id + ", number=" + account + ", password="
				+ password + ", province=" + province + ", createTime="
				+ createTime + ", startTime=" + startTime + ", endTime="
				+ endTime + ", status=" + status + ", money=" + money + "]";
	}

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
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

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
