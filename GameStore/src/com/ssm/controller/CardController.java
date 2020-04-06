package com.ssm.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.ssm.pojo.Card;
import com.ssm.service.CardService;
import com.ssm.util.Pager;

@Controller
@RequestMapping("card")
public class CardController {
	@Autowired
	private CardService cardService;

	// 批量添加密保卡
	@RequestMapping("addCards")
	public ModelAndView addCards(String count, String province, String money,
			String startTime, String endTime) throws ParseException {
		System.out.println(province);
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++");
		ModelAndView mav = new ModelAndView();
		cardService.addCards(count, province, money, startTime, endTime);
		mav.addObject("isSuccess", true);
		mav.addObject("success", "添加成功");
		mav.setViewName("/card/card.jsp");
		return mav;
	}

	// 根据条件获取所有密保卡
	@RequestMapping("getAllCards")
	public void getAllCards(String pageNo, String account, String startTime,
			String endTime, String province, HttpServletResponse response)
			throws IOException {
		Pager<Card> pager = cardService.getAllCardsByPager(pageNo, account,
				startTime, endTime, province.equals("请选择") ? "" : province);
		PrintWriter out = response.getWriter();
		out.write(JSONObject.fromObject(pager).toString());
		out.flush();
		out.close();
	}


}
