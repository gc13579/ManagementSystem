package com.ssm.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ssm.pojo.Manager;
import com.ssm.service.ManagerService;

@Controller
@RequestMapping("manager")
public class ManagerController {
	@Autowired
	private ManagerService managerService;

	// 管理员登录
	@RequestMapping("managerLogin")
	public ModelAndView managerLogin(String username, String password,
			HttpServletRequest request) {
		Manager manager;
		ModelAndView mav = new ModelAndView();
		try {
			manager = managerService.login(username, password);
			//mav.addObject("manager", manager);
			 request.getSession().setAttribute("manager", manager);
			mav.setViewName("/index.jsp");
		} catch (Exception e) {
			e.printStackTrace();
			mav.addObject("isError", true);
			mav.addObject("errorMessage", e.getMessage());
			mav.setViewName("/login.jsp");
		}
		return mav;
	}

	// 管理员退出
	@RequestMapping("exit")
	public ModelAndView exit(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		request.getSession().removeAttribute("manager");
		mav.setViewName("/index.jsp");
		return mav;
	}
}
