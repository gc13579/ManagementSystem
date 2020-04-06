package com.ssm.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Repository;

import com.ssm.dao.CardDao;
import com.ssm.pojo.Card;
import com.ssm.pojo.Game;

@Repository
public class CardDaoImpl implements CardDao {
	private CardDao cardDao;

	// 生成密保卡
	public void insertCard(Card card) {
		cardDao.insertCard(card);
	}

	// 根据条件统计密保卡数量
	public Integer countCards(Map<String, Object> map) {
		return cardDao.countCards(map);
	}

	// 根据条件查询所有密保卡
	public List<Card> selectAllCardsByPager(Map<String, Object> map) {
		return cardDao.selectAllCardsByPager(map);
	}

	// 根据id修改密保卡状态
	public void updateCardStatusById(Map<String, Object> map) {
		cardDao.updateCardStatusById(map);
	}

	// 根据卡号查询密保卡
	public Card selectCardByAccount(String account) {
		return cardDao.selectCardByAccount(account);
	}

	public void setFactory(SqlSessionFactory factory) {
		this.cardDao = factory.openSession().getMapper(CardDao.class);
	}
}
