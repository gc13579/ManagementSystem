package com.ssm.service;

import java.io.InputStream;
import java.util.List;

import com.ssm.exception.fileuploadexception.UploadTypeException;
import com.ssm.exception.gameexception.GameExistedException;
import com.ssm.pojo.Game;
import com.ssm.pojo.GameType;
import com.ssm.util.Pager;

public interface GameService {
	// 添加游戏
	public void addGame(Game game, String mycoverPath, String myscreenPath,
			String downloadFiles, InputStream[] uploadFiles, String[] fileNames)
			throws GameExistedException, UploadTypeException;

	// 根据条件获取所有游戏
	public Pager<Game> getAllGamesByPager(String pageNo, String name,
			String type, String status, String pageSizeFlag);

	// 根据id查游戏
	public Game getGameById(Integer id);

	// 根据id修改游戏
	public void modifyGameById(Game game, InputStream[] uploadFiles,
			String mycoverPath, String myscreenPath, String[] fileNames)
			throws UploadTypeException;

	// 根据名字查游戏
	public Game getGameByName(String name, String oldName)
			throws GameExistedException;

	// 删除n个游戏
	public void removeGameByIds(String[] delId, String serverPicturePath);


}
