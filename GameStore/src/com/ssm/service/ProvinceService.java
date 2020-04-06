package com.ssm.service;

import java.util.List;

import com.ssm.pojo.Province;

public interface ProvinceService {
	// 获取所有省份
	public List<Province> getAllProvinces();

	//获取尚无兑换比例的省份
	public List<Province> getSomeProvinces(String[] existedProvincesIds);
	//根据id获取省份
	public Province getProvinceById(Integer provinceId);
}
