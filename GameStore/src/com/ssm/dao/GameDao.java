package com.ssm.dao;

import java.util.List;
import java.util.Map;

import com.ssm.pojo.Game;

public interface GameDao {
	// 添加游戏
	public void insertGame(Game game);

	// 根据名字查游戏
	public Game selectGameByName(String gameName);

	// 根据条件统计游戏数量
	public Integer countGames(Map<String, Object> map);

	// 根据条件查询所有游戏
	public List<Game> selectAllGamesByPager(Map<String, Object> map);

	// 根据id查游戏
	public Game selectGameById(Integer id);

	// 根据id修改游戏
	public void updateGameById(Game game);

	// 删除n个游戏
	public void deleteGameByIds(String[] delId);

	// 根据n个id查图片路径
	public List<Game> selectPicturePathById(String[] delId);

	// 根据游戏类型查游戏(一个类型可能包含多个游戏)
	public List<Game> selectGameByType(String type);

}
