package com.web.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.web.pojo.Role;
import com.web.service.RoleService;

public class RoleAction {
	private RoleService roleService;

	// 获取所有角色
	public String getRole(HttpServletRequest request, HttpServletResponse resp) {
		List<Role> roles = null;
		try {
			roles = roleService.getAllRoles();
			PrintWriter out = resp.getWriter();
			out.write(JSONArray.fromObject(roles).toString());
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}

	// 根据id获取一条角色
	public String getRoleById(HttpServletRequest request, HttpServletResponse resp) {
		String roleId = request.getParameter("roleId");
		Role role = null;
		try {
			role = roleService.getRoleById(roleId);
			PrintWriter out = resp.getWriter();
			out.write(JSONObject.fromObject(role).toString());
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}

	// 获取除了指定id外，其他的角色
//	public String getRolesExpectedId(HttpServletRequest request, HttpServletResponse resp) {
//		String roleId = request.getParameter("roleId");
//		List<Role> list=null;
//		try {
////			list = roleService.getRolesExpectedId(roleId);
//			list = roleService.getAllRoles();
//			//System.out.println("rolelist:"+list);
////			for (Role role : list) {
////				System.out.println("role!!!:"+role);
////			}
//			PrintWriter out = resp.getWriter();
//			out.write(JSONArray.fromObject(list).toString());
//			out.flush();
//			out.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return "success";
//	}

	// 添加角色
	public String addRole(HttpServletRequest request, HttpServletResponse resp) {
		String roleName = request.getParameter("roleName");
		try {
			roleService.addRole(roleName);
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("isError", true);
			request.setAttribute("errorMessage", e.getMessage());
			return "fail";
		}
		
	}

	// 修改角色
	public String edit(HttpServletRequest request, HttpServletResponse resp) throws Exception {
		String roleId = request.getParameter("roleNo");
		String roleName = request.getParameter("roleName");
		try {
			
			roleService.modifyRoleName(roleId, roleName);
		} catch (Exception e) {
			e.printStackTrace();
//			PrintWriter out = resp.getWriter();
//			out.write(JSONArray.fromObject(list).toString());
//			out.flush();
//			out.close();
		}
		return "success";
	}

	// 删除角色
	public String remove(HttpServletRequest request, HttpServletResponse resp) throws Exception {
		String roleId = request.getParameter("t_role_id");
		PrintWriter out = resp.getWriter();
		try {
			roleService.removeRoleById(roleId);
			out.write("删除成功");
//			out.flush();
//			out.close();
			return "success";
		} catch (Exception e) {
//			request.setAttribute("isError", true);
//			request.setAttribute("errorMessage", e.getMessage());
			out.write(e.getMessage());
			out.flush();
			out.close();
			//e.printStackTrace();
			return "fail";
		}
		
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	public RoleService getRoleService() {
		return roleService;
	}
}
