package com.ssm.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssm.dao.ExpenseDao;
import com.ssm.dao.GameDao;
import com.ssm.dao.UserDao;
import com.ssm.pojo.Expense;
import com.ssm.pojo.Game;
import com.ssm.service.ExpenseService;
import com.ssm.util.Constans;
import com.ssm.util.Pager;
import com.ssm.vo.ExpenseVO;

@Service
public class ExpenseServiceImpl implements ExpenseService {
	@Autowired
	private ExpenseDao expenseDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private GameDao gameDao;

	// 添加购买记录
	@Transactional
	public void addExpense(String gameName, Integer userId, String select,
			Double userMoney, Double payMoney, Date buyTime) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		if (select.equals("游币支付")) {
			map.put("currency", userMoney - payMoney);
			userDao.updateUserCurrencyById(map);
		} else {
			map.put("tariffe", userMoney - payMoney);
			userDao.updateUserTariffeById(map);
		}
		map.put("money", payMoney);
		map.put("gameName", gameName);
		map.put("way", select);
		map.put("buyTime", buyTime);
		expenseDao.insertExpense(map);
	}

	// 获取所有购买记录
	@Transactional
	public Pager<ExpenseVO> getAllExpenses(Integer pageNo, Integer id) {
		Pager<ExpenseVO> pager = new Pager<ExpenseVO>();
		pager.setPageNo(pageNo);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);

		Integer totalCount = expenseDao.countExpenses(id);
		if (totalCount == null) {
			totalCount = 0;
		}
		pager.setTotalPage(totalCount, Constans.PAGE_SIZE_3);

		map.put("max", pageNo * Constans.PAGE_SIZE_3 + 1);
		map.put("min", (pageNo - 1) * Constans.PAGE_SIZE_3);
		List<Expense> list = expenseDao.selectAllExpensesByPager(map);
		List<ExpenseVO> listVO = new ArrayList<ExpenseVO>();

		for (int i = 0; i < list.size(); i++) {
			listVO.add(new ExpenseVO());
			Expense expense = list.get(i);
			listVO.get(i).setExpense(expense);
			listVO.get(i).setUserName(
					userDao.selectUserById(expense.getUserId()).getAccount());
		}
		pager.setList(listVO);
		return pager;
	}

	// 查询用户是否购买过某游戏
	@Transactional
	public Expense getExpenseByUserIdAndGameName(String gameName, Integer userId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("gameName", gameName);
		return expenseDao.selectExpenseByUserIdAndGameName(map);
	}
}
