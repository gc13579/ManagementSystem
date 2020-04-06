package com.gc_company.enity;

import java.sql.Date;

public class Ticket {
	private Integer id;
	private String startPlace;
	private String endPlace;
	private String startTime;
	private Integer count;
	private String state;
	private Double price;
	private String trainnum;

	public Ticket() {
		super();
	}

	 

	public Ticket(Integer id, String startPlace, String endPlace, String startTime, Integer count, String state, Double price, String trainnum) {
		super();
		this.id = id;
		this.startPlace = startPlace;
		this.endPlace = endPlace;
		this.startTime = startTime;
		this.count = count;
		this.state = state;
		this.price = price;
		this.trainnum = trainnum;
	}



	

	@Override
	public String toString() {
		return "Ticket [count=" + count + ", endPlace=" + endPlace + ", id=" + id + ", price=" + price + ", startPlace=" + startPlace + ", startTime=" + startTime + ", state=" + state + ", trainnum="
				+ trainnum + "]";
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	
	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getStartTime() {
		return startTime;
	}



	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}



	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getPrice() {
		return price;
	}

	public void setTrainnum(String trainnum) {
		this.trainnum = trainnum;
	}

	public String getTrainnum() {
		return trainnum;
	}




}
