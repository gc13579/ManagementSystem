package com.ssm.pojo;

import java.util.Date;

public class Expense {
	private Integer id;
	private Integer userId;
	private String gameName;
	private Double money;
	private String way;
	private Date buyTime;

	public Expense() {
	}

	protected Expense(Integer id, Integer userId, String gameName,
			Double money, String way, Date buyTime) {
		this.id = id;
		this.userId = userId;
		this.gameName = gameName;
		this.money = money;
		this.way = way;
		this.buyTime = buyTime;
	}

	@Override
	public String toString() {
		return "Expense [id=" + id + ", userId=" + userId + ", gameName="
				+ gameName + ", money=" + money + ", way=" + way + ", buyTime="
				+ buyTime + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getGameName() {
		return gameName;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
	}

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public String getWay() {
		return way;
	}

	public void setWay(String way) {
		this.way = way;
	}

	public Date getBuyTime() {
		return buyTime;
	}

	public void setBuyTime(Date buyTime) {
		this.buyTime = buyTime;
	}

}
