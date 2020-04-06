package com.ssm.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ssm.exception.fileuploadexception.UploadTypeException;
import com.ssm.exception.gametypeexception.GameTypeContainsGamesException;
import com.ssm.pojo.GameType;
import com.ssm.service.GameTypeService;

@Controller
@RequestMapping("gameType")
public class GameTypeController {
	@Autowired
	private GameTypeService gameTypeService;

	// 获取所有游戏类型
	@RequestMapping("getAllGameTypes")
	public void getAllGameTypes(String name, String status,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		if (status != null && status.equals("请选择")) {
			status = "";
		}
		List<GameType> list = gameTypeService.getAllGameTypes(name, status);
		PrintWriter out = response.getWriter();
		out.write(JSONArray.fromObject(list).toString());
		out.flush();
		out.close();
	}

	// 根据游戏数量排序，获取所有游戏类型（客户端显示）
	@RequestMapping("getAllGameTypesByGameCount")
	public void getAllGameTypesByGameCount(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		List<GameType> list = gameTypeService.getAllGameTypesByGameCount();
		PrintWriter out = response.getWriter();
		out.write(JSONArray.fromObject(list).toString());
		out.flush();
		out.close();
	}

	// 更新游戏类型
	@RequestMapping("updateGameTypeStatus")
	public ModelAndView updateGameTypeStatus(String id, String name,
			String typeStatus) {
		ModelAndView mav = new ModelAndView();
		gameTypeService.modifyGameTypeStatus(Integer.parseInt(id), name,
				typeStatus, new Date());
		mav.setViewName("/gameType/gameType.jsp");
		return mav;
	}

	// 删除n个游戏类型
	@RequestMapping("removeGameType")
	public void removeGameType(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		List list = new ArrayList();
		PrintWriter out = response.getWriter();
		String[] delId = request.getParameter("str").split(" ");
		String serverPicturePath = request.getSession().getServletContext()
				.getRealPath("\\");
		try {
			gameTypeService.removeGameType(delId, serverPicturePath);
			out.write(JSONArray.fromObject(list).toString());
		} catch (GameTypeContainsGamesException e) {
			e.printStackTrace();
			list.add("error");
			out.write(JSONArray.fromObject(list).toString());
		}
		out.flush();
		out.close();
	}

	// 添加游戏类型
	@RequestMapping("addGameType")
	public ModelAndView addGameType(String name, String typeStatus,
			@RequestParam("imgFile")
			CommonsMultipartFile imgFile, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		ModelAndView mav = new ModelAndView();
		String imgPath = request.getSession().getServletContext().getRealPath(
				"/images/type");
		String fileName = imgFile.getFileItem().getName();
		try {
			gameTypeService.addGameType(name, typeStatus, imgFile
					.getInputStream(), imgPath, fileName);
			mav.addObject("isSuccess", true);
			mav.addObject("success", "添加成功");
			mav.setViewName("/gameType/gameType.jsp");
		} catch (Exception e) {
			e.printStackTrace();
			mav.addObject("isError", true);
			mav.addObject("errorMessage", e.getMessage());
			// 如果上传类型异常，添加页面的游戏类型名称保留
			if (e instanceof UploadTypeException) {
				mav.addObject("name", name);
			} else {
				mav.addObject("name", "");
			}
			mav.addObject("typeStatus", typeStatus);
			mav.setViewName("/gameType/addGameType.jsp");
		}
		return mav;
	}

	// 根据名字查询游戏类型
	@RequestMapping("getGameTypeByName")
	public void getGameTypeByName(String gameTypeName,
			HttpServletResponse response) throws IOException {
		GameType gameType = gameTypeService.getGameTypeByName(gameTypeName);
		PrintWriter out = response.getWriter();
		out.write(JSONObject.fromObject(gameType).toString());
		out.flush();
		out.close();
	}

	// 获取商用状态的所有游戏类型
	@RequestMapping("getOnlineGameTypes")
	public void getOnlineGameTypes(HttpServletResponse response)
			throws IOException {
		List<GameType> list = gameTypeService.getOnlineGameTypes();
		PrintWriter out = response.getWriter();
		out.write(JSONArray.fromObject(list).toString());
		out.flush();
		out.close();
	}

	// 根据id查游戏类型
	@RequestMapping("getGameTypeById")
	public void getGameTypeById(String id, HttpServletResponse response)
			throws IOException {
		GameType gameType = gameTypeService.getGameTypeById(Integer
				.parseInt(id));
		PrintWriter out = response.getWriter();
		out.write(JSONObject.fromObject(gameType).toString());
		out.flush();
		out.close();
	}
}
