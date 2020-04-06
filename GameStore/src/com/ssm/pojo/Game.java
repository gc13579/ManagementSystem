package com.ssm.pojo;

import java.util.Date;

public class Game {
	private Integer id;
	private String name;
	private String type;
	private String status;
	private String cover;
	private String screenImgs1;
	private String screenImgs2;
	private String screenImgs3;
	private Double currency;
	private Double tariffe;
	private String developer;
	private String filing;
	private String detail;
	private String introduction;
	private Date createTime;
	private Date updateTime;

	public Game() {

	}

	public Game(Integer id, String name, String type, String status,
			String cover, String screenImgs1, String screenImgs2,
			String screenImgs3, Double currency, Double tariffe,
			String developer, String filing, String detail,
			String introduction, Date createTime, Date updateTime) {
		this.id = id;
		this.name = name;
		this.type = type;
		this.status = status;
		this.cover = cover;
		this.screenImgs1 = screenImgs1;
		this.screenImgs2 = screenImgs2;
		this.screenImgs3 = screenImgs3;
		this.currency = currency;
		this.tariffe = tariffe;
		this.developer = developer;
		this.filing = filing;
		this.detail = detail;
		this.introduction = introduction;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}

	@Override
	public String toString() {
		return "Game [id=" + id + ", name=" + name + ", type=" + type
				+ ", status=" + status + ", cover=" + cover + ", screenImgs1="
				+ screenImgs1 + ", screenImgs2=" + screenImgs2
				+ ", screenImgs3=" + screenImgs3 + ", currency=" + currency
				+ ", tariffe=" + tariffe + ", developer=" + developer
				+ ", filing=" + filing + ", detail=" + detail
				+ ", introduction=" + introduction + ", createTime="
				+ createTime + ", updateTime=" + updateTime + "]";
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCover() {
		return cover;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}

	public String getScreenImgs1() {
		return screenImgs1;
	}

	public void setScreenImgs1(String screenImgs1) {
		this.screenImgs1 = screenImgs1;
	}

	public String getScreenImgs2() {
		return screenImgs2;
	}

	public void setScreenImgs2(String screenImgs2) {
		this.screenImgs2 = screenImgs2;
	}

	public String getScreenImgs3() {
		return screenImgs3;
	}

	public void setScreenImgs3(String screenImgs3) {
		this.screenImgs3 = screenImgs3;
	}

	public double getCurrency() {
		return currency;
	}

	public void setCurrency(double currency) {
		this.currency = currency;
	}

	public double getTariffe() {
		return tariffe;
	}

	public void setTariffe(double tariffe) {
		this.tariffe = tariffe;
	}

	public String getDeveloper() {
		return developer;
	}

	public void setDeveloper(String developer) {
		this.developer = developer;
	}

	public String getFiling() {
		return filing;
	}

	public void setFiling(String filing) {
		this.filing = filing;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
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
