package com.ssm.service.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ssm.exception.fileuploadexception.UploadTypeException;
import com.ssm.exception.gametypeexception.GameTypeContainsGamesException;
import com.ssm.exception.gametypeexception.GameTypeExistedException;
import com.ssm.dao.GameDao;
import com.ssm.dao.GameTypeDao;
import com.ssm.pojo.Game;
import com.ssm.pojo.GameType;
import com.ssm.service.GameTypeService;
import com.ssm.util.FileUtil;

@Service
public class GameTypeServiceImpl implements GameTypeService {
	@Autowired
	private GameTypeDao gameTypeDao;
	@Autowired
	private GameDao gameDao;

	// 获取所有游戏类型
	@Transactional
	public List<GameType> getAllGameTypes(String name, String status) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", name);
		map.put("status", status);
		return gameTypeDao.selectAllGameTypes(map);
	}

	// 根据游戏数量排序，获取所有游戏类型
	@Transactional
	public List<GameType> getAllGameTypesByGameCount() {
		return gameTypeDao.selectAllGameTypesByGameCount();
	}

	// 修改游戏类型状态
	@Transactional
	public void modifyGameTypeStatus(Integer id, String name,
			String typeStatus, Date updateTime) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", name);
		map.put("id", id);
		map.put("typeStatus", typeStatus);
		map.put("updateTime", updateTime);
		gameTypeDao.updateGameTypeStatus(map);
	}

	// 删除游戏类型
	@Transactional
	public void removeGameType(String[] delId, String serverPicturePath)
			throws GameTypeContainsGamesException {
		// 根据类型id查询游戏类型
		List<GameType> list = gameTypeDao.selectGameByIds(delId);
		for (int i = 0; i < list.size(); i++) {
			String name = list.get(i).getName();
			List<Game> games = gameDao.selectGameByType(name);
			if (games.size() != 0) {
				throw new GameTypeContainsGamesException("要删除的游戏类型下，包含游戏，无法删除");
			}
		}
		for (GameType gameType : list) {
			// 根据游戏类型中，保存的图片路径，删除服务器的图片
			new File(serverPicturePath.concat(gameType.getPicture())).delete();
		}
		gameTypeDao.deleteGameType(delId);
	}

	// 添加游戏类型
	@Transactional
	public void addGameType(String name, String status, InputStream uploadFile,
			String imgPath, String fileName) throws UploadTypeException,
			GameTypeExistedException {
		String filePath = imgPath + "\\" + fileName;
		Date date = new Date();
		GameType gameType = gameTypeDao.selectGameTypeByName(name);
		if (gameType != null) {
			throw new GameTypeExistedException("该游戏类型名已存在");
		}
		// 文件上传
		FileUtil.uploadFile(uploadFile, filePath);
		// 数据库保存
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", name);
		map.put("typeStatus", status);
		map.put("filePath", "images\\type\\" + fileName);
		map.put("createTime", date);
		map.put("updateTime", date);
		gameTypeDao.insertGameType(map);
	}

	// 根据名字查询游戏类型
	@Transactional
	public GameType getGameTypeByName(String name) {
		return gameTypeDao.selectGameTypeByName(name);
	}

	// 获取所有商用状态的游戏类型
	@Transactional
	public List<GameType> getOnlineGameTypes() {
		return gameTypeDao.selectOnlineGameTypes();
	}

	// 根据id查游戏类型
	@Transactional
	public GameType getGameTypeById(Integer id) {
		return gameTypeDao.selectGameTypeById(id);
	}
}
