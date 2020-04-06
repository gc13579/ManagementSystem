package com.ssm.dao;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.ssm.pojo.Province;

public interface ProvinceDao {
	// 获取所有省份
	public List<Province> selectAllProvinces();

	// 根据id获取省份名字
	public Province selectProvinceById(Integer provinceId);

	// 获取尚无兑换比例的省份
	public List<Province> selectSomeProvinces(String[] existedProvincesIds);

}
