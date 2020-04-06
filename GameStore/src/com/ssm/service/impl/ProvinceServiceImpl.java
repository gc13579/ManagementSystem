package com.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssm.dao.ProvinceDao;
import com.ssm.pojo.Province;
import com.ssm.service.ProvinceService;

@Service
public class ProvinceServiceImpl implements ProvinceService {
	@Autowired
	private ProvinceDao provinceDao;

	// 获取所有省份
	@Transactional
	public List<Province> getAllProvinces() {
		return provinceDao.selectAllProvinces();
	}

	// 获取尚无兑换比例的省份
	@Transactional
	public List<Province> getSomeProvinces(String[] existedProvincesIds) {
		// System.out.println("------------");
		// for (String string : existedProvincesIds) {
		// System.out.println(string);
		// }
		// System.out.println("------------");
		if (existedProvincesIds[0].trim().equals("")) {
			existedProvincesIds[0] = "0";
		}
		return provinceDao.selectSomeProvinces(existedProvincesIds);
	}

	// 根据id获取省份
	public Province getProvinceById(Integer provinceId) {
		return provinceDao.selectProvinceById(provinceId);
	}

}
