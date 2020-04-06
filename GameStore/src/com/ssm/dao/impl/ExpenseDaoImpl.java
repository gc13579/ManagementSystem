package com.ssm.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Repository;

import com.ssm.dao.ExpenseDao;
import com.ssm.pojo.Expense;
import com.ssm.util.Pager;

@Repository
public class ExpenseDaoImpl implements ExpenseDao {
	private ExpenseDao expenseDao;

	// 添加购买记录
	public void insertExpense(Map<String, Object> map) {
		expenseDao.insertExpense(map);
	}

	// 根据用户id,统计用户购买记录数量
	public Integer countExpenses(Integer id) {

		return expenseDao.countExpenses(id);
	}

	// 获取所有购买记录
	public List<Expense> selectAllExpensesByPager(Map<String, Object> map) {
		return expenseDao.selectAllExpensesByPager(map);
	}

	// 查询用户是否购买过某游戏
	public Expense selectExpenseByUserIdAndGameName(Map<String, Object> map) {
		return expenseDao.selectExpenseByUserIdAndGameName(map);
	}

	public void setFactory(SqlSessionFactory factory) {
		this.expenseDao = factory.openSession().getMapper(ExpenseDao.class);
	}
}
