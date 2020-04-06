package com.ssm.service;

import java.util.Date;

import com.ssm.pojo.Exchange;
import com.ssm.pojo.Expense;
import com.ssm.util.Pager;
import com.ssm.vo.ExpenseVO;

public interface ExpenseService {
	// 添加购买记录
	public void addExpense(String gameName, Integer userId, String select,
			Double userMoney, Double payMoney, Date buyTime);

	// 获取所有购买记录
	public Pager<ExpenseVO> getAllExpenses(Integer pageNo, Integer id);

	// 查询用户是否购买过某游戏
	public Expense getExpenseByUserIdAndGameName(String gameName, Integer userId);

}
