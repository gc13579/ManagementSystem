package com.ssm.dao;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import com.ssm.exception.gametypeexception.GameTypeExistedException;
import com.ssm.pojo.Game;
import com.ssm.pojo.GameType;

public interface GameTypeDao {
	// 获取所有游戏类型
	public List<GameType> selectAllGameTypes(Map<String, Object> map);

	// 根据游戏数量排序，获取所有游戏类型
	public List<GameType> selectAllGameTypesByGameCount();

	// 更新游戏状态及更新时间
	public void updateGameTypeStatus(Map<String, Object> map);

	// 删除n个游戏类型
	public void deleteGameType(String[] arrays);

	// 添加游戏类型
	public void insertGameType(Map<String, Object> map);

	// 根据名字查询游戏类型
	public GameType selectGameTypeByName(String name);

	// 根据id查game(用于查图片路径并删除，以及查询该类型名下是否存在游戏)
	public List<GameType> selectGameByIds(String[] delId);

	// 获取所有商用状态的游戏类型
	public List<GameType> selectOnlineGameTypes();

	// 根据id获取游戏类型
	public GameType selectGameTypeById(Integer id);
}
