package com.ssm.dao.impl;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Repository;

import com.ssm.dao.GameDao;
import com.ssm.dao.GameTypeDao;
import com.ssm.dao.UserDao;
import com.ssm.exception.gametypeexception.GameTypeExistedException;
import com.ssm.pojo.Game;
import com.ssm.pojo.GameType;
import com.sun.org.apache.regexp.internal.recompile;

@Repository
public class GameTypeDaoImpl implements GameTypeDao {
	private GameTypeDao gameTypeDao;
	private GameDao gameDao;

	// 获取所有游戏类型
	public List<GameType> selectAllGameTypes(Map<String, Object> map) {
		return gameTypeDao.selectAllGameTypes(map);
	}

	// 根据游戏数量排序，获取所有游戏类型
	public List<GameType> selectAllGameTypesByGameCount() {
		return gameTypeDao.selectAllGameTypesByGameCount();
	}

	// 更新游戏类型的状态
	public void updateGameTypeStatus(Map<String, Object> map) {
		gameTypeDao.updateGameTypeStatus(map);
	}

	// 删除n个游戏类型
	public void deleteGameType(String[] arrays) {
		gameTypeDao.deleteGameType(arrays);
	}

	// 新建游戏类型
	public void insertGameType(Map<String, Object> map) {
		gameTypeDao.insertGameType(map);
	}

	// 根据名字查游戏类型
	public GameType selectGameTypeByName(String name) {
		return gameTypeDao.selectGameTypeByName(name);
	}

	// 根据id查game(用于查图片路径并删除，以及查询该类型名下是否存在游戏)
	public List<GameType> selectGameByIds(String[] delId) {
		return gameTypeDao.selectGameByIds(delId);
	}

	// 获取所有商用状态的游戏类型
	public List<GameType> selectOnlineGameTypes() {
		return gameTypeDao.selectOnlineGameTypes();
	}

	// 根据id获取游戏类型
	public GameType selectGameTypeById(Integer id) {
		return gameTypeDao.selectGameTypeById(id);
	}

	public void setFactory(SqlSessionFactory factory) {
		this.gameTypeDao = factory.openSession().getMapper(GameTypeDao.class);
		this.gameDao = factory.openSession().getMapper(GameDao.class);
	}
}
