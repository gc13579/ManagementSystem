package com.ssm.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ssm.pojo.GameType;
import com.ssm.pojo.Prepaid;
import com.ssm.pojo.User;
import com.ssm.service.CardService;
import com.ssm.service.PrepaidService;
import com.ssm.service.UserService;

@Controller
@RequestMapping("prepaid")
public class PrepaidController {
	@Autowired
	private PrepaidService prepaidService;
	@Autowired
	private UserService userService;

	@RequestMapping("addPrepaid")
	// 添加充值记录
	public void addPrepaid(String account, String password, Integer userId,
			String userAccount, String userProvince, String userCurrency,
			HttpServletRequest request,HttpServletResponse response) {
		List<String> list = new ArrayList<String>();
		PrintWriter out = null;
		try {
			out = response.getWriter();
			// 充值
			prepaidService.addPrepaid(account, password, userId, userAccount,
					userProvince, Double.parseDouble(userCurrency));
			list.add("充值成功,可前往个人中心-充值记录查看");
			out.write(JSONArray.fromObject(list).toString());
			//将更新游币数后的用户，再返回给Jsp页面，避免充值后，购买游戏时，判断游币还是之前的数额
			User user=userService.getUserById(userId);
			request.getSession().setAttribute("user", user);
		} catch (Exception e) {
			// 充值失败，将错误信息返回给用户
			e.printStackTrace();
			list.add(e.getMessage());
			out.write(JSONArray.fromObject(list).toString());
		}
		out.flush();
		out.close();
	}

	@RequestMapping("getAllPrepaidsByUserId")
	public void getAllPrepaidsByUserId(Integer userId,
			HttpServletResponse response) throws IOException {
		List<Prepaid> list = prepaidService.getAllPrepaidsByUserId(userId);
		PrintWriter out = response.getWriter();
		out.write(JSONArray.fromObject(list).toString());
		out.flush();
		out.close();
	}
}
