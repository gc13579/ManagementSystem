package com.ssm.service.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ssm.dao.GameDao;
import com.ssm.exception.fileuploadexception.UploadTypeException;
import com.ssm.exception.gameexception.GameExistedException;
import com.ssm.pojo.Game;
import com.ssm.service.GameService;
import com.ssm.util.Constans;
import com.ssm.util.FileUtil;
import com.ssm.util.Pager;

@Service
public class GameServiceImpl implements GameService {
	@Autowired
	private GameDao gameDao;

	// 添加游戏
	@Transactional
	public void addGame(Game game, String mycoverPath, String myscreenPath,
			String downloadFiles, InputStream[] uploadFiles, String[] fileNames)
			throws GameExistedException, UploadTypeException {
		Game game1 = gameDao.selectGameByName(game.getName());
		if (game1 != null) {
			throw new GameExistedException("此游戏已经存在");
		}
		// 上传图片
		for (int i = 0; i < uploadFiles.length; i++) {

			if (i == 0) {
				FileUtil.uploadFile(uploadFiles[i], mycoverPath + "\\"
						+ fileNames[i]);
			} else {
				FileUtil.uploadFile(uploadFiles[i], myscreenPath + "\\"
						+ fileNames[i]);
			}

		}
		// 服务器准备txt文件
		try {
			File f = new File(downloadFiles + "\\" + game.getName() + ".txt");
			f.createNewFile();
			PrintWriter pw = new PrintWriter(f);
			pw.append("这是名为" + game.getName() + "的游戏");
			pw.flush();
			pw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 添加游戏
		gameDao.insertGame(game);
	};

	// 根据条件获取所有游戏
	@Transactional
	public Pager<Game> getAllGamesByPager(String pageNo, String name,
			String type, String status, String pageSizeFlag) {
		Pager<Game> pager = new Pager<Game>();
		pager.setPageNo(Integer.parseInt(pageNo));
		Integer pageSize = null;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", name);
		map.put("type", type);
		map.put("status", status);
		if (pageSizeFlag.equals("6")) {
			pageSize = Constans.PAGE_SIZE_6;
		}
		if (pageSizeFlag.equals("3")) {
			pageSize = Constans.PAGE_SIZE_3;
			map.put("invoker", "root");
		}
		Integer totalCount = gameDao.countGames(map);
		if (totalCount == null) {
			totalCount = 0;
		}
		pager.setTotalPage(totalCount, pageSize);

		map.put("max", Integer.parseInt(pageNo) * pageSize + 1);
		map.put("min", (Integer.parseInt(pageNo) - 1) * pageSize);
		List<Game> list = gameDao.selectAllGamesByPager(map);
		pager.setList(list);
		return pager;
	};

	// 根据id查游戏
	@Transactional
	public Game getGameById(Integer id) {
		return gameDao.selectGameById(id);
	}

	// 根据id修改游戏
	@Transactional
	public void modifyGameById(Game game, InputStream[] uploadFiles,
			String mycoverPath, String myscreenPath, String[] fileNames)
			throws UploadTypeException {
		// 上传文件
		for (int i = 0; i < uploadFiles.length; i++) {
			if (uploadFiles[i] != null) {
				if (i == 0) {
					FileUtil.uploadFile(uploadFiles[i], mycoverPath + "\\"
							+ fileNames[i]);
				} else {
					FileUtil.uploadFile(uploadFiles[i], myscreenPath + "\\"
							+ fileNames[i]);
				}
			}
		}
		gameDao.updateGameById(game);
	}

	// 根据名字查游戏
	@Transactional
	public Game getGameByName(String name, String oldName)
			throws GameExistedException {
		Game game = gameDao.selectGameByName(name);
		if (game != null && !game.getName().equals(oldName)) {
			throw new GameExistedException("该游戏已经存在");
		}
		return game;
	}

	// 删除n个游戏
	@Transactional
	public void removeGameByIds(String[] delId, String serverPicturePath) {
		List<Game> list = gameDao.selectPicturePathById(delId);
		for (Game g : list) {
			// 删除4个图片
			new File(serverPicturePath.concat(g.getCover())).delete();
			new File(serverPicturePath.concat(g.getScreenImgs1())).delete();
			new File(serverPicturePath.concat(g.getScreenImgs2())).delete();
			new File(serverPicturePath.concat(g.getScreenImgs3())).delete();
			// 服务器删除，新建游戏时，生成的txt
			new File(serverPicturePath.concat("\\downloadFiles\\").concat(
					g.getName()).concat(".txt")).delete();
		}
		gameDao.deleteGameByIds(delId);
	}
}
