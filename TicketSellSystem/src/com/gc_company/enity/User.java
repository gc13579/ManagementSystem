package com.gc_company.enity;

public class User {
	private Integer id;
	private String username;
	private String password;
	private String idCard;
	private Double money;
	private String phoneNum;
	private String state;

	public User() {
	}

	public User(Integer id, String username, String password, String idCard, Double money, String phoneNum, String state) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.idCard = idCard;
		this.money = money;
		this.phoneNum = phoneNum;
		this.state = state;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", idCard=" + idCard + ", money=" + money + ", password=" + password + ", phoneNum=" + phoneNum + ", state=" + state + ", username=" + username + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

}
