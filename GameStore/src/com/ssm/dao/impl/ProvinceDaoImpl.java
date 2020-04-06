package com.ssm.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;

import com.ssm.dao.ManagerDao;
import com.ssm.dao.ProvinceDao;
import com.ssm.pojo.Province;

public class ProvinceDaoImpl implements ProvinceDao {
	private ProvinceDao provinceDao;

	// 获取所有省份
	public List<Province> selectAllProvinces() {
		return provinceDao.selectAllProvinces();
	}

	// 根据id获取省份名字
	public Province selectProvinceById(Integer provinceId) {
		return provinceDao.selectProvinceById(provinceId);
	}

	// 获取尚无兑换比例的省份
	public List<Province> selectSomeProvinces(String[] existedProvincesIds) {
		return provinceDao.selectSomeProvinces(existedProvincesIds);
	}

	public void setFactory(SqlSessionFactory factory) {
		this.provinceDao = factory.openSession().getMapper(ProvinceDao.class);
	}
}
