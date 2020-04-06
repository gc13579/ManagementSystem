package com.ssm.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ssm.pojo.Exchange;
import com.ssm.pojo.User;
import com.ssm.service.ExchangeService;
import com.ssm.service.ProvinceService;
import com.ssm.util.Pager;
import com.ssm.vo.ExchangeVO;

@Controller
@RequestMapping("exchange")
public class ExchangeController {
	@Autowired
	private ExchangeService exchangeService;
	@Autowired
	private ProvinceService provinceService;

	@RequestMapping("addExchange")
	// 增加兑换比例
	public ModelAndView addExchange(String provinceId, String charge,
			String exchangeStatus) {
		ModelAndView mav = new ModelAndView();
		exchangeService.addExchange(provinceId, charge, exchangeStatus);
		mav.addObject("isSuccess", true);
		mav.addObject("success", "添加成功");
		mav.setViewName("/exchange/exchange.jsp");
		return mav;
	}

	@RequestMapping("getAllExchangesByPager")
	// 分页获取所有兑换比例
	public void getAllExchangesByPager(String pageNo, String provinceId,
			HttpServletResponse response) throws IOException {
		Pager<ExchangeVO> pager = exchangeService.getAllExchangesByPager(
				Integer.parseInt(pageNo), provinceId.equals("请选择") ? ""
						: provinceId);
		// Pager<Exchange> pager =
		// exchangeService.getAllExchangesByPager(Integer
		// .parseInt(pageNo), province.equals("请选择") ? "" : province);
		// System.out.println(pager);
		// List<Object> list = new ArrayList<Object>();
		// list.add(pager);
		// List<Exchange> list2 = exchangeService.getAllExchanges();
		// List<Integer> allExistedProvincesIds = new ArrayList<Integer>();
		// for (int i = 0; i < list2.size(); i++) {
		// allExistedProvincesIds
		// .add(new Integer(list2.get(i).getProvinceId()));
		// }
		// list.add(allExistedProvincesIds);
		PrintWriter out = response.getWriter();
		out.write(JSONObject.fromObject(pager).toString());
		// out.write(JSONArray.fromObject(list).toString());
		out.flush();
		out.close();
	}

	@RequestMapping("getAllExchanges")
	// 获取所有兑换比例
	public void getAllExchanges(HttpServletResponse response)
			throws IOException {
		List<Exchange> list = exchangeService.getAllExchanges();
		List<Integer> provincesIds = new ArrayList<Integer>();
		for (int i = 0; i < list.size(); i++) {
			provincesIds.add(new Integer(list.get(i).getProvinceId()));
		}
		PrintWriter out = response.getWriter();
		out.write(JSONArray.fromObject(provincesIds).toString());
		out.flush();
		out.close();
	}

	@RequestMapping("getExchangeById")
	// 根据id获取兑换比例
	public void getExchangeById(String provinceId, HttpServletResponse response)
			throws IOException {
		Exchange exchange = exchangeService.getExchangeById(Integer
				.parseInt(provinceId));
		ExchangeVO exchangeVO = new ExchangeVO();
		exchangeVO.setExchange(exchange);
		exchangeVO.setProvince(provinceService.getProvinceById(
				exchange.getProvinceId()).getName());
		PrintWriter out = response.getWriter();
		out.write(JSONObject.fromObject(exchangeVO).toString());
		out.flush();
		out.close();
	}

	@RequestMapping("modifyExchange")
	// 修改兑换比例
	public ModelAndView modifyExchange(String id, String charge,
			String exchangeStatus) {
		exchangeService.modifyExchange(Integer.parseInt(id), charge,
				exchangeStatus);
		ModelAndView mav = new ModelAndView();
		mav.addObject("isSuccess", true);
		mav.addObject("success", "修改成功");
		mav.setViewName("/exchange/exchange.jsp");
		return mav;
	}

	@RequestMapping("removeExchange")
	// 删除兑换比例
	public ModelAndView removeExchange(String str) {
		ModelAndView mav = new ModelAndView();
		String[] delId = str.split(" ");
		exchangeService.removeExchangeByIds(delId);
		mav.setViewName("/exchange/exchange.jsp");
		return mav;
	}

}
