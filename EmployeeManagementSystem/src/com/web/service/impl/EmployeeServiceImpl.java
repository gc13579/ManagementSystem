package com.web.service.impl;

import java.util.Date;
import java.util.List;

import com.web.dao.EmployeeDao;
import com.web.pojo.Employee;
import com.web.service.EmployeeService;
import com.web.util.Constants;
import com.web.util.Pager;

public class EmployeeServiceImpl implements EmployeeService {
	private EmployeeDao employeeDao;

	public Employee getEmployeeNoByName(String employeeName) throws Exception {
		return employeeDao.selectEmployeeNoByName(employeeName);
	}

	public void setEmployeeDao(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}

	public EmployeeDao getEmployeeDao() {
		return employeeDao;
	}

	public List<Employee> getEmloyeeByDeptName(String deptName) throws Exception {
		List<Employee> employees = null;
		employees = employeeDao.selectEmpByDept(deptName);
		return employees;
	}
	public Pager<Employee> selectAll(Integer pageNo, String name, String dept) throws Exception{
		Pager<Employee> pager = new Pager<Employee>();
		pager.setPageNo(pageNo);
		Integer totalCount = employeeDao.countUser( name, dept);
		if(totalCount == null) {
			totalCount = 0;
		}
		System.out.println("totalCount = "+totalCount);
		pager.setTotalPage(totalCount, Constants.PAGE_SIZE_4);
		List<Employee> users = employeeDao.selectUserByPage( name, dept,pageNo,Constants.PAGE_SIZE_4);
		pager.setList(users);
		return pager;
	}
	
	public void addEmployee(String empNo, String empName, String sex, String empDept, Date entryTime)throws Exception{
		//将所有信息封装在一个对象中
         Employee employee = new Employee();
         employee.setEmpNo(empNo);
         employee.setEmpName(empName);
         employee.setSex(sex);
         employee.setEmpDept(empDept);
         employee.setEntryTime(entryTime);
         employeeDao.insertEmployee(employee);
	}
	
	
	public void removeEmployeeById(String id) throws Exception {
		employeeDao.deleteEmployeeById(id);
	}
	public Employee getEmployeeById(Integer id) throws Exception {
		Employee employee = employeeDao.selectEmployeeById(id);
		return employee;
	}
	public void changeEmployee(String empNo, String empName, String sex, String empDept, Date entryTime) throws Exception {
        employeeDao.updateEmployee(empNo,empName,sex,empDept,entryTime);		
	}
}
