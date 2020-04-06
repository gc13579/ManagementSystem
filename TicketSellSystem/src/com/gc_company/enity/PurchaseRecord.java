package com.gc_company.enity;

public class PurchaseRecord {
	private Integer id;
	private Integer u_id;
	private Integer t_id;
	private String buyTime;
	private String buyState;


	public PurchaseRecord() {
		super();
	}


	public PurchaseRecord(Integer id, Integer uId, Integer tId, String buyTime, String buyState) {
		super();
		this.id = id;
		u_id = uId;
		t_id = tId;
		this.buyTime = buyTime;
		this.buyState = buyState;
	}


	@Override
	public String toString() {
		return "PurchaseRecord [buyState=" + buyState + ", buyTime=" + buyTime + ", id=" + id + ", t_id=" + t_id + ", u_id=" + u_id + "]";
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Integer getU_id() {
		return u_id;
	}


	public void setU_id(Integer uId) {
		u_id = uId;
	}


	public Integer getT_id() {
		return t_id;
	}


	public void setT_id(Integer tId) {
		t_id = tId;
	}


	public String getBuyTime() {
		return buyTime;
	}


	public void setBuyTime(String buyTime) {
		this.buyTime = buyTime;
	}


	public String getBuyState() {
		return buyState;
	}


	public void setBuyState(String buyState) {
		this.buyState = buyState;
	}


}
