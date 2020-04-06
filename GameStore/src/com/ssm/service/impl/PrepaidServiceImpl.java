package com.ssm.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssm.dao.CardDao;
import com.ssm.dao.PrepaidDao;
import com.ssm.dao.UserDao;
import com.ssm.dao.impl.CardDaoImpl;
import com.ssm.exception.cardexception.CardExpiredException;
import com.ssm.exception.cardexception.CardInffectivedException;
import com.ssm.exception.cardexception.CardNotExistedException;
import com.ssm.exception.cardexception.CardPwdMismatchException;
import com.ssm.exception.cardexception.CardUsedException;
import com.ssm.pojo.Card;
import com.ssm.pojo.Prepaid;
import com.ssm.service.PrepaidService;

@Service
public class PrepaidServiceImpl implements PrepaidService {
	@Autowired
	private PrepaidDao prepaidDao;
	@Autowired
	private CardDao cardDao;
	@Autowired
	private UserDao userDao;

	// 添加充值记录
	@Transactional
	public void addPrepaid(String account, String password, Integer userId,
			String userAccount, String userProvince, Double userCurrency)
			throws Exception {
		Card card = cardDao.selectCardByAccount(account);
		Date nowDate = new Date();
		if (!card.getStatus().equals("已使用")) {
			// 如果密保卡的状态不是"正常"，且生效时间<当前时间<过期时间,状态改为:正常
			if (!card.getStatus().equals("正常")
					&& card.getStartTime().getTime() < nowDate.getTime()
					&& card.getEndTime().getTime() > nowDate.getTime()) {
				card.setStatus("正常");
				// 修改数据库
				Map<String, Object> map1 = new HashMap<String, Object>();
				map1.put("id", card.getId());
				map1.put("status", "正常");
				cardDao.updateCardStatusById(map1);
			}
			// 如果密保卡的状态不是"过期"，且过期时间<当前时间,状态改为:过期
			if (!card.getStatus().equals("过期")
					&& card.getEndTime().getTime() < nowDate.getTime()) {
				card.setStatus("过期");
				// 修改数据库
				Map<String, Object> map1 = new HashMap<String, Object>();
				map1.put("id", card.getId());
				map1.put("status", "过期");
				cardDao.updateCardStatusById(map1);
			}
		}
		if (card == null) {
			throw new CardNotExistedException("密保卡不存在");
		}
		if (!card.getPassword().equals(password)) {
			throw new CardPwdMismatchException("密码错误");
		}
		if (!card.getProvince().equals(userProvince)) {
			throw new CardPwdMismatchException("该密保卡仅支持 " + card.getProvince()
					+ " 的用户充值");
		}
		if (card.getStatus().equals("已使用")) {
			throw new CardUsedException("该密保卡已使用");
		}
		if (card.getStatus().equals("过期")) {
			throw new CardExpiredException("该密保卡已过期");
		}
		if (card.getStatus().equals("未生效")) {
			throw new CardInffectivedException("该密保卡还未生效");
		}
		Map<String, Object> map = new HashMap<String, Object>();
		// 更新用户游币
		map.put("userId", userId);
		map.put("currency", userCurrency + card.getMoney());
		userDao.updateUserCurrencyById(map);
		// 更改密保卡状态为:已使用
		map.put("id", card.getId());
		map.put("status", "已使用");
		cardDao.updateCardStatusById(map);
		// 添加充值记录
		map.put("account", account);
		map.put("password", password);
		map.put("userAccount", userAccount);
		map.put("prepaidTime", new Date());
		map.put("money", card.getMoney());
		prepaidDao.insertPrepaid(map);
	}

	// 查询用户所有充值记录
	@Transactional
	public List<Prepaid> getAllPrepaidsByUserId(Integer userId) {
		return prepaidDao.selectAllPrepaidsByUserId(userId);
	}
}
