package com.gc_company.enity;

public class InvestApplyHistory {
	private Integer rechargeApplyHistoryId;
	private Integer u_id;
	private String userName;
	private Double applyMoney;
	private String applyTime;
	private String applyState;

	public InvestApplyHistory() {
		super();
	}

	public InvestApplyHistory(Integer rechargeApplyHistoryId, Integer uId, String userName, Double applyMoney, String applyTime, String applyState) {
		super();
		this.rechargeApplyHistoryId = rechargeApplyHistoryId;
		u_id = uId;
		this.userName = userName;
		this.applyMoney = applyMoney;
		this.applyTime = applyTime;
		this.applyState = applyState;
	}

	@Override
	public String toString() {
		return "RechargeApplyHistory [applyMoney=" + applyMoney + ", applyState=" + applyState + ", applyTime=" + applyTime + ", rechargeApplyHistoryId=" + rechargeApplyHistoryId + ", u_id=" + u_id
				+ ", userName=" + userName + "]";
	}

	public Integer getRechargeApplyHistoryId() {
		return rechargeApplyHistoryId;
	}

	public void setRechargeApplyHistoryId(Integer rechargeApplyHistoryId) {
		this.rechargeApplyHistoryId = rechargeApplyHistoryId;
	}

	public Integer getU_id() {
		return u_id;
	}

	public void setU_id(Integer uId) {
		u_id = uId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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
