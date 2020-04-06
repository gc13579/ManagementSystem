package com.ssm.dao;

import java.util.List;
import java.util.Map;

import com.ssm.pojo.Card;
import com.ssm.pojo.Game;

public interface CardDao {
	// 生成密保卡
	public void insertCard(Card card);

	// 根据条件统计密保卡数量
	public Integer countCards(Map<String, Object> map);

	// 根据条件查询所有密保卡
	public List<Card> selectAllCardsByPager(Map<String, Object> map);

	// 根据id修改密保卡状态
	public void updateCardStatusById(Map<String, Object> map);

	// 根据卡号查询密保卡
	public Card selectCardByAccount(String account);
}
