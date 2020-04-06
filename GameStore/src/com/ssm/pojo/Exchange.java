package com.ssm.pojo;

import java.util.Date;

public class Exchange {
	private Integer id;
	private Integer charge;
	private Date createTime;
	private Date updateTime;
	private String status;
	private Integer provinceId;

	public Exchange() {
	}

	public Exchange(Integer id, Integer charge, Date createTime,
			Date updateTime, String status, Integer provinceId) {
		super();
		this.id = id;
		this.charge = charge;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.status = status;
		this.provinceId = provinceId;
	}

	@Override
	public String toString() {
		return "Exchange [id=" + id + ", charge=" + charge + ", createTime="
				+ createTime + ", updateTime=" + updateTime + ", status="
				+ status + ", provinceId=" + provinceId + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCharge() {
		return charge;
	}

	public void setCharge(Integer charge) {
		this.charge = charge;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}

}