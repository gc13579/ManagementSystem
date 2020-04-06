package com.ssm.vo;

import com.ssm.pojo.Expense;

public class ExpenseVO extends Expense {
	private String userName;

	public void setExpense(Expense expense) {
		setId(expense.getId());
		setUserId(expense.getUserId());
		setGameName(expense.getGameName());
		setBuyTime(expense.getBuyTime());
		setMoney(expense.getMoney());
		setWay(expense.getWay());

	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
