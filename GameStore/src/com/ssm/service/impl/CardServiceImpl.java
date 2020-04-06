package com.ssm.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ssm.dao.CardDao;
import com.ssm.exception.cardexception.CardExpiredException;
import com.ssm.exception.cardexception.CardInffectivedException;
import com.ssm.exception.cardexception.CardNotExistedException;
import com.ssm.exception.cardexception.CardPwdMismatchException;
import com.ssm.exception.cardexception.CardUsedException;
import com.ssm.pojo.Card;
import com.ssm.service.CardService;
import com.ssm.util.CardUtil;
import com.ssm.util.Constans;
import com.ssm.util.Pager;

@Service
public class CardServiceImpl implements CardService {
	@Autowired
	private CardDao cardDao;

	// 批量添加密保卡
	@Transactional
	public void addCards(String count, String province, String money,
			String startTime, String endTime) throws ParseException {
		System.out.println("业务层:" + count + " " + province + " " + money + " "
				+ startTime + " " + endTime);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date startTimeDate = sdf.parse(startTime);
		Date endTimeDate = sdf.parse(endTime);
		Date nowDate = new Date();
		String[] allProvinces = province.split(",");
		// 每个省
		for (int i = 0; i < allProvinces.length; i++) {
			// 生成count张密保卡
			for (int j = 0; j < Integer.parseInt(count); j++) {
				Card card = new Card();
				// 每张密保卡，随机生成卡号和密码
				String[] numAndPwd = CardUtil.createNumAndPwd();
				card.setAccount(numAndPwd[0]);
				card.setPassword(numAndPwd[1]);
				card.setProvince(allProvinces[i]);
				card.setCreateTime(nowDate);
				card.setStartTime(startTimeDate);
				card.setEndTime(endTimeDate);
				// 如果生效时间<当前时间<过期时间,状态为：正常
				if (startTimeDate.getTime() < nowDate.getTime()
						&& endTimeDate.getTime() > nowDate.getTime()) {
					card.setStatus("正常");
				}
				// 如果生效时间>当前时间,状态为：未生效
				if (startTimeDate.getTime() > nowDate.getTime()) {
					card.setStatus("未生效");
				}
				// 过期密保卡已经在表单验证拦截
				card.setMoney(Double.parseDouble(money));
				cardDao.insertCard(card);
			}
		}
	}

	// 根据条件查询密保卡
	@Transactional
	public Pager<Card> getAllCardsByPager(String pageNo, String account,
			String startTime, String endTime, String province) {
		Pager<Card> pager = new Pager<Card>();
		pager.setPageNo(Integer.parseInt(pageNo));
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("account", account);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("province", province);

		Integer totalCount = cardDao.countCards(map);
		if (totalCount == null) {
			totalCount = 0;
		}
		pager.setTotalPage(totalCount, Constans.PAGE_SIZE_5);

		map.put("max", Integer.parseInt(pageNo) * Constans.PAGE_SIZE_5 + 1);
		map.put("min", (Integer.parseInt(pageNo) - 1) * Constans.PAGE_SIZE_5);
		List<Card> list = cardDao.selectAllCardsByPager(map);
		Date nowDate = new Date();
		for (int i = 0; i < list.size(); i++) {
			Card card = list.get(i);
			// 如果密保卡状态不是"已使用"，进行下列更新状态操作
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
		}
		pager.setList(list);
		return pager;
	}

	// 根据卡号密码查询密保卡
//	@Transactional
//	public Card getCardByAccountAndPwd(String account, String password,
//			String userProvince) throws Exception {
//		Card card = cardDao.selectCardByAccount(account);
//		if (card == null) {
//			throw new CardNotExistedException("密保卡不存在");
//		}
//		if (!card.getPassword().equals(password)) {
//			throw new CardPwdMismatchException("密码错误");
//		}
//		if (!card.getProvince().equals(userProvince)) {
//			throw new CardPwdMismatchException("该密保卡仅支持 " + card.getProvince()
//					+ " 的用户充值");
//		}
//		if (card.getStatus().equals("已使用")) {
//			throw new CardUsedException("该密保卡已使用");
//		}
//		if (card.getStatus().equals("过期")) {
//			throw new CardExpiredException("该密保卡已过期");
//		}
//		if (card.getStatus().equals("未生效")) {
//			throw new CardInffectivedException("该密保卡还未生效");
//		}
//		return card;
//	}

	// 根据id更改密保卡状态（用于用户充值后）
	@Transactional
	public void modifyCardStatusById(Integer cardId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", cardId);
		map.put("status", "已使用");
		cardDao.updateCardStatusById(map);
	}
}
