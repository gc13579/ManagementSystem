package com.ssm.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Repository;

import com.ssm.dao.GameDao;
import com.ssm.dao.UserDao;
import com.ssm.pojo.Game;

@Repository
public class GameDaoImpl implements GameDao {
	private GameDao gameDao;

	// 添加游戏
	public void insertGame(Game game) {
		gameDao.insertGame(game);
	};

	// 根据名字查游戏
	public Game selectGameByName(String gameName) {
		return gameDao.selectGameByName(gameName);
	};

	// 根据条件统计游戏数量
	public Integer countGames(Map<String, Object> map) {
		return gameDao.countGames(map);
	}

	// 根据条件查询所有游戏
	public List<Game> selectAllGamesByPager(Map<String, Object> map) {
		return gameDao.selectAllGamesByPager(map);
	}

	// 根据id查游戏
	public Game selectGameById(Integer id) {
		return gameDao.selectGameById(id);
	}

	// 根据id修改游戏
	public void updateGameById(Game game) {
		gameDao.updateGameById(game);
	}

	// 删除n个游戏
	public void deleteGameByIds(String[] delId) {
		gameDao.deleteGameByIds(delId);
	}

	// 根据n个id查图片路径
	public List<Game> selectPicturePathById(String[] delId) {
		return gameDao.selectPicturePathById(delId);
	}

	// 根据游戏类型查游戏(一个类型可能包含多个游戏)
	public List<Game> selectGameByType(String type) {
		return gameDao.selectGameByType(type);
	}

	public void setFactory(SqlSessionFactory factory) {
		this.gameDao = factory.openSession().getMapper(GameDao.class);
	}
}
