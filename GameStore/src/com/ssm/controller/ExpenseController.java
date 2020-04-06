package com.ssm.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.ssm.pojo.Expense;
import com.ssm.pojo.User;
import com.ssm.service.ExpenseService;
import com.ssm.service.UserService;
import com.ssm.util.Pager;
import com.ssm.vo.ExpenseVO;

@Controller
@RequestMapping("expense")
public class ExpenseController {
	@Autowired
	private ExpenseService expenseService;
	@Autowired
	private UserService userService;

	@RequestMapping("addExpense")
	// 添加购买记录
	public void addExpense(String gameName, Integer userId, String select,
			String currency, String tariffe, String hiddenTariffe,
			String hiddenCurrency, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		select = select.equals("1") ? "话费支付" : "游币支付";
		Double payMoney = select.equals("话费支付") ? Double.parseDouble(tariffe)
				: Double.parseDouble(currency);
		Double userMoney = select.equals("话费支付") ? Double
				.parseDouble(hiddenTariffe) : Double
				.parseDouble(hiddenCurrency);
		expenseService.addExpense(gameName, userId, select, userMoney, payMoney,
				new Date());
		PrintWriter out = response.getWriter();
		ArrayList<String> list = new ArrayList<String>();
		list.add("购买成功");
		out.write(JSONArray.fromObject(list).toString());
		out.flush();
		out.close();
		// 将更新游币数后的用户，再返回给Jsp页面，避免购买游戏后，再次购买游戏时，判断游币还是之前的数额
		User user = userService.getUserById(userId);
		request.getSession().setAttribute("user", user);
	}

	@RequestMapping("getAllExpenses")
	// 查询所有购买记录
	public void getAllExpenses(Integer pageNo, Integer id,
			HttpServletResponse response) throws IOException {
		Pager<ExpenseVO> list = expenseService.getAllExpenses(pageNo, id);
		PrintWriter out = response.getWriter();
		out.write(JSONObject.fromObject(list).toString());
		out.flush();
		out.close();
	}

	@RequestMapping("getExpenseByUserIdAndGameName")
	// 查询用户是否购买过某游戏
	public void getExpenseByUserIdAndGameName(String gameName, Integer userId,
			HttpServletResponse response) throws IOException {
		Expense expense = expenseService.getExpenseByUserIdAndGameName(gameName,
				userId);
		PrintWriter out = response.getWriter();
		out.write(JSONArray.fromObject(expense).toString());
		out.flush();
		out.close();
	}
}
