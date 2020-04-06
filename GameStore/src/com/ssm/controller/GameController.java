package com.ssm.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ssm.exception.fileuploadexception.UploadTypeException;
import com.ssm.exception.gameexception.GameExistedException;
import com.ssm.pojo.Game;
import com.ssm.service.GameService;
import com.ssm.util.Pager;

@Controller
@RequestMapping("game")
public class GameController {
	@Autowired
	private GameService gameService;

	@RequestMapping("getAllgames")
	// 获取所有游戏
	public void getAllgames(HttpServletResponse response, String pageNo,
			String name, String type, String status, String pageSizeFlag)
			throws IOException {
		// 此方法为管理员与客户共同调用，客户端查询，status为null，防止与type相同写法，直接调用equals报错
		if (status != null && status.equals("请选择")) {
			status = "";
		}
		Pager<Game> list = gameService.getAllGamesByPager(pageNo, name, type
				.equals("请选择") ? "" : type, status, pageSizeFlag);
		PrintWriter out = response.getWriter();
		out.write(JSONObject.fromObject(list).toString());
		out.flush();
		out.close();
	}

	@RequestMapping("getGameById")
	// 根据id获取游戏
	public void getGameById(Integer id, HttpServletResponse response)
			throws IOException {
		Game game = gameService.getGameById(id);
		PrintWriter out = response.getWriter();
		out.write(JSONObject.fromObject(game).toString());
		out.flush();
		out.close();
	}

	@RequestMapping("removeGame")
	// 删除若干游戏
	public ModelAndView removeGame(String str, HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		String[] delId = str.split(" ");
		String serverPicturePath = request.getSession().getServletContext()
				.getRealPath("\\");
		gameService.removeGameByIds(delId, serverPicturePath);
		mav.setViewName("/game/game.jsp");
		return mav;
	}

	@RequestMapping("addGame")
	// 添加游戏
	public ModelAndView addGame(Game game, @RequestParam("coverImg")
	CommonsMultipartFile coverImg, @RequestParam("screenImg1")
	CommonsMultipartFile screenImg1, @RequestParam("screenImg2")
	CommonsMultipartFile screenImg2, @RequestParam("screenImg3")
	CommonsMultipartFile screenImg3, HttpServletResponse response,
			HttpServletRequest request) throws IOException {
		ModelAndView mav = new ModelAndView();
		Date date = new Date();
		game.setCreateTime(date);
		game.setUpdateTime(date);
		game.setCover("images\\mycover\\" + coverImg.getFileItem().getName());
		game.setScreenImgs1("images\\myscreen\\"
				+ screenImg1.getFileItem().getName());
		game.setScreenImgs2("images\\myscreen\\"
				+ screenImg2.getFileItem().getName());
		game.setScreenImgs3("images\\myscreen\\"
				+ screenImg3.getFileItem().getName());
		// System.out.println(game);
		String mycoverPath = request.getSession().getServletContext()
				.getRealPath("/images/mycover");
		String myscreenPath = request.getSession().getServletContext()
				.getRealPath("/images/myscreen");
		String downloadFiles = request.getSession().getServletContext()
				.getRealPath("/downloadFiles");
		InputStream[] uploadFiles = new InputStream[] {
				coverImg.getInputStream(), screenImg1.getInputStream(),
				screenImg2.getInputStream(), screenImg3.getInputStream() };
		String[] fileNames = new String[] { coverImg.getFileItem().getName(),
				screenImg1.getFileItem().getName(),
				screenImg2.getFileItem().getName(),
				screenImg3.getFileItem().getName() };
		try {
			gameService.addGame(game, mycoverPath, myscreenPath, downloadFiles,
					uploadFiles, fileNames);
			mav.addObject("isSuccess", true);
			mav.addObject("success", "添加成功");
			mav.setViewName("/game/game.jsp");
		} catch (Exception e) {
			if (e instanceof GameExistedException
					|| e instanceof UploadTypeException) {
				mav.addObject("isError", true);
				mav.addObject("errorMessage", e.getMessage());
				mav.setViewName("/game/addGame.jsp");
			}
		}
		return mav;
	}

	@RequestMapping("modifyGame")
	// 修改游戏
	public ModelAndView modifyGame(Integer id, String name, String select,
			String status, String developer, String filing,
			String introduction, String detail, String currency,
			String tariffe, @RequestParam("newCoverImg")
			CommonsMultipartFile newCoverImg, @RequestParam("newScreenImgs1")
			CommonsMultipartFile newScreenImgs1,
			@RequestParam("newScreenImgs2")
			CommonsMultipartFile newScreenImgs2,
			@RequestParam("newScreenImgs3")
			CommonsMultipartFile newScreenImgs3, HttpServletRequest request,
			HttpServletResponse response, String hiddenCoverImg,
			String hiddenScreenImgs1, String hiddenScreenImgs2,
			String hiddenScreenImgs3, String createTime) throws IOException,
			ParseException {
		ModelAndView mav = new ModelAndView();
		Game game = new Game();
		game.setId(id);
		game.setName(name);
		game.setType(select);
		game.setStatus(status);
		game.setDeveloper(developer);
		game.setFiling(filing);
		game.setIntroduction(introduction);
		game.setDetail(detail);
		game.setCurrency(Double.parseDouble(currency));
		game.setTariffe(Double.parseDouble(tariffe));
		game.setCreateTime(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
				.parse(createTime));
		game.setUpdateTime(new Date());
		String mycoverPath = request.getSession().getServletContext()
				.getRealPath("/images/mycover");
		String myscreenPath = request.getSession().getServletContext()
				.getRealPath("/images/myscreen");
		game
				.setCover(newCoverImg.getFileItem().getName().equals("") ? hiddenCoverImg
						: "images\\mycover\\"
								+ newCoverImg.getFileItem().getName());
		game
				.setScreenImgs1(newScreenImgs1.getFileItem().getName().equals(
						"") ? hiddenScreenImgs1 : "images\\myscreen\\"
						+ newScreenImgs1.getFileItem().getName());
		game
				.setScreenImgs2(newScreenImgs2.getFileItem().getName().equals(
						"") ? hiddenScreenImgs2 : "images\\myscreen\\"
						+ newScreenImgs2.getFileItem().getName());
		game
				.setScreenImgs3(newScreenImgs3.getFileItem().getName().equals(
						"") ? hiddenScreenImgs3 : "images\\myscreen\\"
						+ newScreenImgs3.getFileItem().getName());
		InputStream[] uploadFiles = new InputStream[] {
				newCoverImg.getFileItem().getName().equals("") ? null
						: newCoverImg.getInputStream(),
				newScreenImgs1.getFileItem().getName().equals("") ? null
						: newScreenImgs1.getInputStream(),
				newScreenImgs2.getFileItem().getName().equals("") ? null
						: newScreenImgs2.getInputStream(),
				newScreenImgs3.getFileItem().getName().equals("") ? null
						: newScreenImgs3.getInputStream() };
		String[] fileNames = new String[] {
				newCoverImg.getFileItem().getName().equals("") ? hiddenCoverImg
						: newCoverImg.getFileItem().getName(),
				newScreenImgs1.getFileItem().getName().equals("") ? hiddenScreenImgs1
						: newScreenImgs1.getFileItem().getName(),
				newScreenImgs2.getFileItem().getName().equals("") ? hiddenScreenImgs2
						: newScreenImgs2.getFileItem().getName(),
				newScreenImgs3.getFileItem().getName().equals("") ? hiddenScreenImgs3
						: newScreenImgs3.getFileItem().getName() };
		try {
			gameService.modifyGameById(game, uploadFiles, mycoverPath,
					myscreenPath, fileNames);
			mav.addObject("isSuccess", true);
			mav.addObject("success", "修改成功");
			mav.setViewName("/game/game.jsp");
		} catch (Exception e) {
			if (e instanceof UploadTypeException) {
				mav.addObject("isError", true);
				mav.addObject("errorMessage", e.getMessage());
				mav.setViewName("/game/addGame.jsp");
			}
		}
		return mav;
	}

	// 根据名字查找游戏
	@RequestMapping("getGameByName")
	public void getGameByName(String name, String oldName,
			HttpServletResponse response) throws IOException {
		List<String> list = new ArrayList<String>();
		PrintWriter out = response.getWriter();
		try {
			Game game = gameService.getGameByName(name, oldName);
		} catch (GameExistedException e) {
			e.printStackTrace();
			list.add("error1");
			out.write(JSONArray.fromObject(list).toString());
		}
		out.flush();
		out.close();
	}

}
