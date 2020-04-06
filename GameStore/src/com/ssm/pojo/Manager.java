package com.ssm.pojo;

import java.util.Date;

public class Manager {
	private Integer id;
	private String account;
	private String password;
	private String iphone;
	private Date createTime;
	public Manager(Integer id, String account, String password, String iphone,
			Date createTime) {
		this.id = id;
		this.account = account;
		this.password = password;
		this.iphone = iphone;
		this.createTime = createTime;
	}
	public Manager() {
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
	public String getIphone() {
		return iphone;
	}
	public void setIphone(String iphone) {
		this.iphone = iphone;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@Override
	public String toString() {
		return "Manager [account=" + account + ", createTime=" + createTime
				+ ", id=" + id + ", iphone=" + iphone + ", password="
				+ password + "]";
	}
	
	
	
	
}
