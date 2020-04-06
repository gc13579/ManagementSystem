package com.ssm.service;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

import com.ssm.exception.fileuploadexception.UploadTypeException;
import com.ssm.exception.gametypeexception.GameTypeContainsGamesException;
import com.ssm.exception.gametypeexception.GameTypeExistedException;
import com.ssm.pojo.GameType;

public interface GameTypeService {
	// 获取所有游戏类型
	public List<GameType> getAllGameTypes(String name, String status);

	// 修改游戏类型的状态和"修改时间"
	public void modifyGameTypeStatus(Integer id,String name, String typeStatus,
			Date updateTime);

	// 删除n个游戏类型
	public void removeGameType(String[] delId, String serverPicturePath)
			throws GameTypeContainsGamesException;

	// 添加游戏类型
	public void addGameType(String name, String typeStatus,
			InputStream uploadFile, String imgPath, String fileName)
			throws UploadTypeException, GameTypeExistedException;

	// 根据游戏数量排序，获取所有游戏类型
	public List<GameType> getAllGameTypesByGameCount();

	// 根据名字查询游戏类型
	public GameType getGameTypeByName(String gameTypeName);

	// 获取所有商用状态的游戏类型
	public List<GameType> getOnlineGameTypes();

	// 根据id查游戏类型
	public GameType getGameTypeById(Integer id);
}
