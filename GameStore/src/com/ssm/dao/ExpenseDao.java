package com.ssm.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.ssm.pojo.Expense;
import com.ssm.util.Pager;

public interface ExpenseDao {
	// 添加购买记录
	public void insertExpense(Map<String, Object> map);

	// 根据用户id,统计用户购买记录数量
	public Integer countExpenses(Integer id);

	// 获取所有购买记录
	List<Expense> selectAllExpensesByPager(Map<String, Object> map);

	// 查询用户是否购买过某游戏
	public Expense selectExpenseByUserIdAndGameName(Map<String, Object> map);
}
