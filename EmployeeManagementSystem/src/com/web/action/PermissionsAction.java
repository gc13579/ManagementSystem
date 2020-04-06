package com.web.action;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.web.VO.PermissionsVO;
import com.web.pojo.Employee;
import com.web.pojo.Permissions;
import com.web.service.PermissionsService;
import com.web.service.PermissionsVOService;
import com.web.util.Pager;

public class PermissionsAction {
	private PermissionsVOService permissionsVOService;
	private PermissionsService permissionsService;
	//多表分页查询
	public String getPermissionsByRidAndMidAndPageNo(HttpServletRequest request, HttpServletResponse resp) {
		List<PermissionsVO> permissionsVos = null;
		Integer pageNo = Integer.parseInt(request.getParameter("pageNo"));
		String menuId = request.getParameter("menuId");
		String roleId = request.getParameter("roleId");
		if (menuId == null || menuId.equals("0")) {
			menuId = "";
		}
		if (roleId == null || roleId.equals("0")) {
			roleId = "";
		}
		System.out.println("permissionAction pageNo:"+pageNo+" menuId:"+menuId+" roleId:"+roleId);
		try {
			Pager<PermissionsVO> pager = permissionsVOService.selectAll(pageNo,roleId,menuId);
			PrintWriter out;
			out = resp.getWriter();
			String json = JSONObject.fromObject(pager).toString();
			out.write(json);
			out.flush();
			out.close();
//			permissionsVos = permissionsVOService.getAllPermissionsVos(roleId, menuId);
//			PrintWriter out = resp.getWriter();
//			out.write(JSONArray.fromObject(permissionsVos).toString());
//			out.flush();
//			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}
	//多表查询
	public String getPermissionsByRidAndMid(HttpServletRequest request, HttpServletResponse resp) {
		List<PermissionsVO> permissionsVos = null;
		Integer pageNo = Integer.parseInt(request.getParameter("pageNo"));
		String menuId = request.getParameter("menuId");
		String roleId = request.getParameter("roleId");
		if (menuId == null || menuId.equals("0")) {
			menuId = "";
		}
		if (roleId == null || roleId.equals("0")) {
			roleId = "";
		}
		try {
			permissionsVos = permissionsVOService.getAllPermissionsVos(roleId, menuId);
			PrintWriter out = resp.getWriter();
			out.write(JSONArray.fromObject(permissionsVos).toString());
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}
	//根据id删除权限
	public String remove(HttpServletRequest request, HttpServletResponse resp) {
		String id = request.getParameter("id");
		try {
			permissionsService.removePermissionsVOById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}
	//修改权限
	public String edit(HttpServletRequest request, HttpServletResponse resp) {
		String roleId=request.getParameter("roleNo");
		String oldMenuId=request.getParameter("oldMenuId");
		String newMenuId=request.getParameter("sel1");
		try {
			 permissionsService.modifyOneMenuIdInPermissions(roleId,oldMenuId,newMenuId);
			 return "success";
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("isError", true);
			request.setAttribute("errorMessage", e.getMessage());
			return "fail";
		}
	
	}
	//添加权限
	public String addPermissions(HttpServletRequest request, HttpServletResponse resp) {
		Integer roleId = Integer.parseInt(request.getParameter("sel1"));
		Integer menuId = Integer.parseInt(request.getParameter("sel2"));
		try {
			permissionsService.addOnePermission(roleId, menuId);
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("isError", true);
			request.setAttribute("errorMessage", e.getMessage());
			return "fail";
		}
	}

	public PermissionsService getPermissionsService() {
		return permissionsService;
	}

	public void setPermissionsService(PermissionsService permissionsService) {
		this.permissionsService = permissionsService;
	}

	public PermissionsVOService getPermissionsVOService() {
		return permissionsVOService;
	}

	public void setPermissionsVOService(PermissionsVOService permissionsVOService) {
		this.permissionsVOService = permissionsVOService;
	}

}
