package com.web.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.web.pojo.Employee;
import com.web.service.EmployeeService;
import com.web.util.Pager;

public class EmployeeAction {
	private  EmployeeService employeeService;

	public  Employee getEmployeeNoByName(HttpServletRequest request, HttpServletResponse resp, String employeeName) {
		Employee employee = null;
		try {
			employee = employeeService.getEmployeeNoByName(employeeName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return employee;
	}

	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	public EmployeeService getEmployeeService() {
		return employeeService;
	}
	//通过部门名查员工
	public String getEmployeeByDept(HttpServletRequest request, HttpServletResponse resp){
		String deptName = request.getParameter("deptName");
		try {
			List<Employee> employees = employeeService.getEmloyeeByDeptName(deptName);
			PrintWriter out = resp.getWriter();
			out.write(JSONArray.fromObject(employees).toString());
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}
	//获取所有员工
	public String getEmployee(HttpServletRequest request, HttpServletResponse resp){
		Integer pageNo = Integer.parseInt(request.getParameter("pageNo"));
		String name = request.getParameter("name");
		String dept = request.getParameter("dept");
		try {
			Pager<Employee> pager = employeeService.selectAll(pageNo,name,dept);
			PrintWriter out;
			out = resp.getWriter();
			String json = JSONObject.fromObject(pager).toString();
			out.write(json);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}
	//添加员工
	public String addEmployee(HttpServletRequest request, HttpServletResponse resp){
		String empNo = request.getParameter("No");
		String empName = request.getParameter("name");
		String sex = request.getParameter("sel1");
		String empDept = request.getParameter("sel2");
		String time = request.getParameter("time");
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date entryTime =(Date)sd.parse(time);
			employeeService.addEmployee(empNo,empName,sex,empDept,entryTime);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}
	//删除员工
	public String remove(HttpServletRequest request, HttpServletResponse resp){
		String id = request.getParameter("id");

		try {
			employeeService.removeEmployeeById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}
	//通过id查找员工
	public String getemployeeById(HttpServletRequest request, HttpServletResponse resp){
		Integer id =  Integer.parseInt(request.getParameter("empId"));
		Employee employee = null;
		try {
		    employee = employeeService.getEmployeeById(id);
		    System.out.println(employee.toString());
			PrintWriter out = resp.getWriter();
			out.write(JSONObject.fromObject(employee).toString());
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "success";
	}
	//修改员工信息
	public String change(HttpServletRequest request, HttpServletResponse resp){
		String empNo = request.getParameter("employeeNo");
		String empName = request.getParameter("employeeName");
		String sex = request.getParameter("employeeSex");
		String empDept = request.getParameter("dept");
		String time = request.getParameter("entryTime");
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date entryTime =(Date)sd.parse(time);
			employeeService.changeEmployee(empNo,empName,sex,empDept,entryTime);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}
}
