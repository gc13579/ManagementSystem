package com.gc_company.enity;

public class RechargeApply {
	private Integer id;
	private Integer u_id;
	private Double applyMoney;
	private String applyTime;
	private String applyState;

	public RechargeApply() {
	}

	public RechargeApply(Integer id, Integer u_id, Double applyMoney, String applyTime, String applyState) {
		super();
		this.id = id;
		this.u_id = u_id;
		this.applyMoney = applyMoney;
		this.applyTime = applyTime;
		this.applyState = applyState;
	}

	@Override
	public String toString() {
		return "RechargeApply [applyMoney=" + applyMoney + ", applyState=" + applyState + ", applyTime=" + applyTime + ", id=" + id + ", u_id=" + u_id + "]";
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

	public Double getApplyMoney() {
		return applyMoney;
	}

	public void setApplyMoney(Double applyMoney) {
		this.applyMoney = applyMoney;
	}

	public String getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(String applyTime) {
		this.applyTime = applyTime;
	}

	public String getApplyState() {
		return applyState;
	}

	public void setApplyState(String applyState) {
		this.applyState = applyState;
	}

}
