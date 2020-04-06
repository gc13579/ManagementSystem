package com.ssm.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ssm.pojo.Manager;
import com.ssm.pojo.User;
import com.ssm.service.UserService;
import com.ssm.util.Pager;
import com.sun.org.apache.xpath.internal.operations.Mod;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;

	// 用户登录
	@RequestMapping("/userLogin")
	public ModelAndView userLogin(String name, String userPwd,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		User user = null;
		ModelAndView mav = new ModelAndView();
		try {
			user = userService.login(name, userPwd);
			request.getSession().setAttribute("user", user);
			mav.setViewName("/user/portal.jsp");
		} catch (Exception e) {
			e.printStackTrace();
			mav.addObject("isError", true);
			mav.addObject("name", name);
			mav.addObject("errorMessage", e.getMessage());
			mav.setViewName("/user/login.jsp");
		}
		return mav;
	}

	// 用户注册
	@RequestMapping("/userRegist")
	public ModelAndView userRegist(User user, String bir) {
		ModelAndView mav = new ModelAndView();
		userService.userRegist(user, bir);
		mav.addObject("isSuccess", true);
		mav.addObject("success", "注册成功");
		mav.setViewName("/user/login.jsp");
		return mav;
	}

	// 获取所有用户
	@RequestMapping("getAllusers")
	public void getAllusers(String pageNo, String account, String iphone,
			HttpServletResponse response) throws IOException {
		Pager<User> pager = userService.getAllUsersByPager(pageNo, account,
				iphone);
		PrintWriter out = response.getWriter();
		out.write(JSONObject.fromObject(pager).toString());
		out.flush();
		out.close();
	}

	// 禁用用户
	@RequestMapping("modifyUsersStatusToForbid")
	public void modifyUsersStatusToForbid(HttpServletRequest request,
			HttpServletResponse response) {
		String[] forbidId = request.getParameter("str").split(" ");
		userService.modifyUsersStatusToForbid(forbidId);
	}

	// 解封用户
	@RequestMapping("modifyUsersStatusToNormal")
	public void modifyUsersStatusToNormal(HttpServletRequest request,
			HttpServletResponse response) {
		String[] unsealId = request.getParameter("str").split(" ");
		userService.modifyUsersStatusToNormal(unsealId);
	}

	// 用户退出
	@RequestMapping("exit")
	public ModelAndView exit(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		request.getSession().removeAttribute("user");
		mav.setViewName("/user/login.jsp");
		return mav;
	}

	// 根据id获取用户
	@RequestMapping("getUserById")
	public void getUserById(Integer id, HttpServletResponse response)
			throws IOException {
		User user = userService.getUserById(id);
		PrintWriter out = response.getWriter();
		out.write(JSONArray.fromObject(user).toString());
		out.flush();
		out.close();
	}

	// 修改密码
	@RequestMapping("modifyPwd")
	public ModelAndView modifyPwd(Integer userId, String newPwd,
			HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		userService.modifyUserPwd(userId, newPwd);
		// 查询一次用户，避免多次修改密码时，判断原密码仍是第一次登录的密码
		request.getSession().setAttribute("user",
				userService.getUserById(userId));
		mav.setViewName("/user/userDetail.jsp");
		return mav;
	}

	// 根据账号查询用户
	@RequestMapping("getUserByAccount")
	public void getUserByAccount(String account, HttpServletResponse response)
			throws IOException {
		User user = userService.getUserByAccount(account);
		PrintWriter out = response.getWriter();
		out.write(JSONArray.fromObject(user).toString());
		out.flush();
		out.close();
	}

}
