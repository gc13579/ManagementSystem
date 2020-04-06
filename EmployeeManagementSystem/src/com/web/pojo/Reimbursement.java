package com.web.pojo;

import java.util.Date;

public class Reimbursement {
	private Integer id;
	private String reimNo;
	private String reimPerson;
	private String reimType;
	private Double reimMoney;
	private Date reimTime;
	private String reimStates;
	private String reimAbstract;

	public Reimbursement(Integer id, String reimNo, String reimPerson, String reimType, Double reimMoney, Date reimTime, String reimStates, String reimAbstract) {
		super();
		this.id = id;
		this.reimNo = reimNo;
		this.reimPerson = reimPerson;
		this.reimType = reimType;
		this.reimMoney = reimMoney;
		this.reimTime = reimTime;
		this.reimStates = reimStates;
		this.reimAbstract = reimAbstract;
	}

	@Override
	public String toString() {
		return "Reimbursement [id=" + id + ", reimAbstract=" + reimAbstract + ", reimMoney=" + reimMoney + ", reimNo=" + reimNo + ", reimPerson=" + reimPerson + ", reimStates=" + reimStates
				+ ", reimTime=" + reimTime + ", reimType=" + reimType + "]";
	}

	public Reimbursement() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getReimNo() {
		return reimNo;
	}

	public void setReimNo(String reimNo) {
		this.reimNo = reimNo;
	}

	public String getReimPerson() {
		return reimPerson;
	}

	public void setReimPerson(String reimPerson) {
		this.reimPerson = reimPerson;
	}

	public String getReimType() {
		return reimType;
	}

	public void setReimType(String reimType) {
		this.reimType = reimType;
	}

	public Double getReimMoney() {
		return reimMoney;
	}

	public void setReimMoney(Double reimMoney) {
		this.reimMoney = reimMoney;
	}

	public Date getReimTime() {
		return reimTime;
	}

	public void setReimTime(Date reimTime) {
		this.reimTime = reimTime;
	}

	public String getReimStates() {
		return reimStates;
	}

	public void setReimStates(String reimStates) {
		this.reimStates = reimStates;
	}

	public String getReimAbstract() {
		return reimAbstract;
	}

	public void setReimAbstract(String reimAbstract) {
		this.reimAbstract = reimAbstract;
	}

}
