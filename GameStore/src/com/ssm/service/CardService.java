package com.ssm.service;

import java.text.ParseException;

import com.ssm.pojo.Card;
import com.ssm.util.Pager;

public interface CardService {
	// 批量添加密保卡
	public void addCards(String count, String province, String money,
			String startTime, String endTime) throws ParseException;

	// 根据条件查询密保卡
	public Pager<Card> getAllCardsByPager(String pageNo, String account,
			String startTime, String endTime, String province);

	// 根据卡号密码查询密保卡
	//public Card getCardByAccountAndPwd(String account, String password,
	//		String userProvince) throws Exception;

	// 根据id更改密保卡状态（用于用户充值后）
	public void modifyCardStatusById(Integer cardId);
}
