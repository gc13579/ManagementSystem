package com.web.service;

import java.util.Date;
import java.util.List;

import com.web.pojo.Employee;
import com.web.util.Pager;

public interface EmployeeService {
	public Employee getEmployeeNoByName(String employeeName) throws Exception;

	// 通过部门名获取员工
	public List<Employee> getEmloyeeByDeptName(String deptName) throws Exception;

	// 获取所有员工
	public Pager<Employee> selectAll(Integer pageNo, String name, String dept) throws Exception;

	// 通过id删除员工
	public void removeEmployeeById(String id) throws Exception;

	// 添加员工
	public void addEmployee(String empNo, String empName, String sex, String empDept, Date entryTime) throws Exception;

	// 通过id获取员工
	public Employee getEmployeeById(Integer id) throws Exception;

	// 修改员工
	public void changeEmployee(String empNo, String empName, String sex, String empDept, Date entryTime) throws Exception;

}
