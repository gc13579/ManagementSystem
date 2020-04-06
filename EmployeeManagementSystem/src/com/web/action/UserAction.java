package com.web.action;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.web.VO.UserVO;
import com.web.pojo.Employee;
import com.web.pojo.Menu;
import com.web.pojo.User;
import com.web.service.EmployeeService;
import com.web.service.MenuService;
import com.web.service.PermissionsService;
import com.web.service.UserService;
import com.web.service.UserVOService;

public class UserAction {
	private UserService userService;
	private UserVOService userVOService;
	private EmployeeService employeeService;

	// 登录
	public String login(HttpServletRequest request, HttpServletResponse resp) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		User user = null;
		try {
			user = userService.login(username, password);
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			System.out.println(user);
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("isError", true);
			request.setAttribute("errorMessage", e.getMessage());
			return "fail";
		}
	}
//获取所有的菜单
	public String getAllSubMenu(HttpServletRequest request, HttpServletResponse resp) {
		User user = (User) request.getSession().getAttribute("user");
		Integer userId = user.getT_user_id();
		try {
			List<Menu> list = userService.getAllSubMenus(userId);
			PrintWriter out = resp.getWriter();
			out.write(JSONArray.fromObject(list).toString());
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}

	// 根据 帐号，状态，角色 获取所有uservo
	public String getUser(HttpServletRequest request, HttpServletResponse resp) {
		String userAccount = request.getParameter("account");
		String userState = request.getParameter("state");
		String roleName = request.getParameter("role");
		if (roleName.equals("请选择")) {
			roleName = "";
		}
		List<UserVO> list = null;
		try {
			list = userVOService.getAllUserVos(userAccount, userState, roleName);
			PrintWriter out = resp.getWriter();
			out.write(JSONArray.fromObject(list).toString());
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}

	// 根据id获取uservo
	public String getUserVoById(HttpServletRequest request, HttpServletResponse resp) {
		String t_user_id = request.getParameter("t_user_id");
		UserVO userVO = null;
		try {
			if (t_user_id.endsWith(" ")) {
				t_user_id = t_user_id.trim();
				userVO = userVOService.getOneUserVoById(t_user_id);
				Employee employee = employeeService.getEmployeeNoByName(userVO.getT_user_name());
				PrintWriter out = resp.getWriter();
				out.write(JSONObject.fromObject(employee).toString());
				out.flush();
				out.close();
			} else {
				userVO = userVOService.getOneUserVoById(t_user_id);
				PrintWriter out = resp.getWriter();
				out.write(JSONObject.fromObject(userVO).toString());
				out.flush();
				out.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}

	// 删除
	public String remove(HttpServletRequest request, HttpServletResponse resp) {
		String id = request.getParameter("t_user_id");
		try {
			userVOService.removeUserVoById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}

	// 修改
	public String edit(HttpServletRequest request, HttpServletResponse resp) {
		String account = request.getParameter("account");
		String employeeId = request.getParameter("employeeId");
		String employeeName = request.getParameter("employeeName");
		String state = request.getParameter("sel1");
		String roleId = request.getParameter("sel2");
		// System.out.println("帐号："+account);
		// System.out.println("员工id："+employeeId);
		// System.out.println("员工名字："+employeeName);
		// System.out.println("状态："+state);
		// System.out.println("角色id："+roleId);
		try {
			//employee = employeeService.getEmployeeNoByName(employeeName);
			userVOService.updateUserVo(account,employeeId, employeeName, state, roleId);
			
		} catch (Exception e) {
			//e.printStackTrace();
			request.setAttribute("isError", true);
			request.setAttribute("errorMessage", e.getMessage());
			return "fail";
		}
		return "success";
		
	}

	// 添加
	public String add(HttpServletRequest request, HttpServletResponse resp) {
		Employee employee=null;
		String account = request.getParameter("account");
		String employeeId = request.getParameter("employeeId");
		String employeeName = request.getParameter("employeeName");
		String state = request.getParameter("sel1");
		String roleId = request.getParameter("sel2");
		// System.out.println("帐号：" + account);
		// System.out.println("员工id：" + employeeId);
		// System.out.println("员工名字：" + employeeName);
		// System.out.println("状态：" + state);
		// System.out.println("角色id：" + roleId);
		try {
			userVOService.addUser(account,employeeId,employeeName, state, roleId);
			return "success";
		} catch (Exception e) {
			//e.printStackTrace();
			request.setAttribute("isError", true);
			request.setAttribute("errorMessage", e.getMessage());
			return "fail";
		}
		
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void setUserVOService(UserVOService userVOService) {
		this.userVOService = userVOService;
	}

	public UserVOService getUserVOService() {
		return userVOService;
	}

	public String changePassword(HttpServletRequest request, HttpServletResponse resp) {
		String newPwd = request.getParameter("newPwd");
		User user = (User) request.getSession().getAttribute("user");
		try {
			userService.resetPwd(user.getT_user_id(), newPwd);
			request.getSession().removeAttribute("user");
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("isError", true);
			request.setAttribute("errorMessage", e.getMessage());
			return "fail";
		}
	}

	public String quit(HttpServletRequest request, HttpServletResponse resp) {
		request.getSession().invalidate();
		return "success";
	}

	public EmployeeService getEmployeeService() {
		return employeeService;
	}

	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	
}
