package com.ssm.vo;

import com.ssm.pojo.Exchange;

public class ExchangeVO extends Exchange {
	private String province;

	public void setExchange(Exchange exchange) {
		setId(exchange.getId());
		setCharge(exchange.getCharge());
		setCreateTime(exchange.getCreateTime());
		setUpdateTime(exchange.getUpdateTime());
		setProvinceId(exchange.getProvinceId());
		setStatus(exchange.getStatus());
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

}
