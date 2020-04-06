package com.ssm.pojo;

import java.util.Date;

public class GameType {

	private Integer id;
	private String name;
	private String status;
	private String picture;
	private Date createTime;
	private Date updateTime;

	public GameType() {
	}

	public GameType(Integer id, String name, String status, String picture, Date createTime, Date updateTime) {
		super();
		this.id = id;
		this.name = name;
		this.status = status;
		this.picture = picture;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}

	@Override
	public String toString() {
		return "GameType [createTime=" + createTime + ", id=" + id + ", name=" + name + ", picture=" + picture + ", status=" + status + ", updateTime=" + updateTime + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}
