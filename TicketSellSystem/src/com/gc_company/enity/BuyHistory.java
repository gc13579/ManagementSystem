package com.gc_company.enity;

public class BuyHistory {
	private Integer buyhistory_id;
	private Integer t_id;
	private String trainNum;
	private String startPlace;
	private String endPlace;
	private String startTime;
	private Double tarinPrice;
	private String state;
	private String buyTime;
	private String buyState;

	public BuyHistory() {
	}

	public BuyHistory(Integer buyhistoryId, Integer tId, String trainNum, String startPlace, String endPlace, String startTime, Double tarinPrice, String state, String buyTime,
			String buyState) {
		super();
		this.buyhistory_id = buyhistoryId;
		this.t_id = tId;
		this.trainNum = trainNum;
		this.startPlace = startPlace;
		this.endPlace = endPlace;
		this.startTime = startTime;
		this.tarinPrice = tarinPrice;
		this.state = state;
		this.buyTime = buyTime;
		this.buyState = buyState;
	}

	@Override
	public String toString() {
		return "BuyHistory [buyState=" + buyState + ", buyTime=" + buyTime + ", buyhistory_id=" + buyhistory_id + ", endPlace=" + endPlace + ", startPlace=" + startPlace + ", startTime=" + startTime
				+ ", state=" + state + ", t_id=" + t_id + ", tarinPrice=" + tarinPrice + ", trainNum=" + trainNum ;
	}

	public Integer getT_id() {
		return t_id;
	}

	public void setT_id(Integer tId) {
		t_id = tId;
	}

	public String getTrainNum() {
		return trainNum;
	}

	public void setTrainNum(String trainNum) {
		this.trainNum = trainNum;
	}

	public String getStartPlace() {
		return startPlace;
	}

	public void setStartPlace(String startPlace) {
		this.startPlace = startPlace;
	}

	public String getEndPlace() {
		return endPlace;
	}

	public void setEndPlace(String endPlace) {
		this.endPlace = endPlace;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
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

	public void setBuyhistory_id(Integer buyhistory_id) {
		this.buyhistory_id = buyhistory_id;
	}

	public Integer getBuyhistory_id() {
		return buyhistory_id;
	}


	public void setTarinPrice(Double tarinPrice) {
		this.tarinPrice = tarinPrice;
	}

	public Double getTarinPrice() {
		return tarinPrice;
	}

}
