package com.ssm.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.ssm.pojo.GameType;
import com.ssm.pojo.Province;
import com.ssm.service.ProvinceService;

@Controller
@RequestMapping("province")
public class ProvinceController {

	@Autowired
	private ProvinceService provinceService;

	// 获取所有省份
	@RequestMapping("getAllProvinces")
	public void getAllProvinces(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		List<Province> list = provinceService.getAllProvinces();
		PrintWriter out = response.getWriter();
		out.write(JSONArray.fromObject(list).toString());
		out.flush();
		out.close();
	}

	// 获取尚无兑换比例的省份
	@RequestMapping("getSomeProvinces")
	public void getSomeProvinces(String str, HttpServletResponse response)
			throws IOException {
		String[] existedProvincesIds = str.split(",");
		List<Province> list = provinceService
				.getSomeProvinces(existedProvincesIds);
		PrintWriter out = response.getWriter();
		out.write(JSONArray.fromObject(list).toString());
		out.flush();
		out.close();
	}
}
