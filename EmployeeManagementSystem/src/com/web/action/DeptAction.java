package com.web.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.web.pojo.Dept;
import com.web.service.DeptService;

public class DeptAction {
	private  DeptService deptService;
	//获取所有的部门
	public String getDept(HttpServletRequest request, HttpServletResponse resp) {
		List<Dept> depts = null;
			try {
				depts = deptService.getAllDept();
				PrintWriter out = resp.getWriter();
				out.write(JSONArray.fromObject(depts).toString());
				out.flush();
				out.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "success";
	}
	//添加部门
	public String addDept(HttpServletRequest request, HttpServletResponse resp){
		String deptNo = request.getParameter("deptNo");
		String deptName = request.getParameter("deptName");
		String deptLoc = request.getParameter("deptLoc");
		String deptMaster = request.getParameter("deptMaster");
		String deptInfo = request.getParameter("deptInfo");
		Dept dept = null;
		try {
			//通过部门编号查询部门
			deptService.addDept(deptNo,deptName,deptLoc,deptMaster,deptInfo);
			return "success";
		} catch (Exception e1) {
			//e1.printStackTrace();
			request.setAttribute("isError", true);
			request.setAttribute("errorMessage", e1.getMessage());
			return "fail";
		}
		//如果查不到部门说明没有重复
//		if(dept == null){
//			try {
//				deptService.addDept(deptNo,deptName,deptLoc,deptMaster,deptInfo);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			return "success";
//		}else{
//			request.setAttribute("isError", true);
//			request.setAttribute("errorMessage", "添加失败：该部门编号已存在");
//			return "fail";
//		}
	}
	//通过部门编号查询一个部门
	public String getDeptByNo(HttpServletRequest request, HttpServletResponse resp){
		String deptNo = request.getParameter("deptNo");
		Dept dept = null;
		try {
			dept = deptService.getDept(deptNo);
			PrintWriter out = resp.getWriter();
			out.write(JSONObject.fromObject(dept).toString());
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		 return "success";
	}
	//修改部门
	public String change(HttpServletRequest request, HttpServletResponse resp){
		String deptNo = request.getParameter("deptNo");
		String deptName = request.getParameter("deptName");
		String deptLoc = request.getParameter("deptLoc");
		String deptMaster = request.getParameter("deptMaster");
		try {
			deptService.changeDept(deptNo,deptName,deptLoc,deptMaster);
		} catch (Exception e) {
			//e.printStackTrace();
			request.setAttribute("isError", true);
			request.setAttribute("errorMessage", e.getMessage());
			return "fail";
		}
		return "success";
	}
	//删除部门
	public 	String remove(HttpServletRequest request, HttpServletResponse resp) throws Exception{
		String deptNo = request.getParameter("deptNo");
		Dept dept = deptService.getDept(deptNo);
		PrintWriter out=resp.getWriter();
		try {
			deptService.removeDeptByNo(dept.getDeptName(),deptNo);
			out.write("删除成功");
			return "success";
		} catch (Exception e) {
			out.write(e.getMessage());
			out.flush();
			out.close();
			return "fail";
		}
		
	}

	public void setDeptService(DeptService deptService) {
		this.deptService = deptService;
	}
	public DeptService getDeptService() {
		return deptService;
	}
}
