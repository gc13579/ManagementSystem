package com.web.service.proxy;

import java.util.Date;
import java.util.List;

import com.web.pojo.Employee;
import com.web.service.EmployeeService;
import com.web.trans.Transaction;
import com.web.util.Pager;

public class EmployeeServiceProxy implements EmployeeService {
	private EmployeeService employeeService;
	private Transaction trans;

	public Employee getEmployeeNoByName(String employeeName) throws Exception {
		Employee employee = null;
		trans.begin();
		try {
			employee = employeeService.getEmployeeNoByName(employeeName);
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
		}
		return employee;
	}

	public EmployeeService getEmployeeService() {
		return employeeService;
	}

	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	public Transaction getTrans() {
		return trans;
	}

	public void setTrans(Transaction trans) {
		this.trans = trans;
	}
	public List<Employee> getEmloyeeByDeptName(String deptName) throws Exception {
		trans.begin();
		List<Employee> employees;
		try {
			employees =employeeService.getEmloyeeByDeptName(deptName) ;
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
			throw e;
		}
		return employees;
		
	}
	public Pager<Employee> selectAll(Integer pageNo, String name, String dept) throws Exception {
		
		trans.begin();
		Pager<Employee> Pager;
		try {
			Pager = employeeService.selectAll(pageNo,name,dept);
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
			throw e;
		}
		return Pager;
	}
	public void removeEmployeeById(String id) throws Exception {
		trans.begin();
		try {
			employeeService.removeEmployeeById(id);
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
			throw e;
		}		
	}
	public void addEmployee(String empNo, String empName, String sex, String empDept, Date entryTime)throws Exception {
		trans.begin();
		try {
			employeeService.addEmployee(empNo, empName, sex, empDept, entryTime);
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
			throw e;
		}		
	}
	public Employee getEmployeeById(Integer id) throws Exception{
		trans.begin();
		Employee employee ;
		try {
			employee = employeeService.getEmployeeById(id);
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
			throw e;
		}	
		return employee;
	}
	public void changeEmployee(String empNo, String empName, String sex, String empDept, Date entryTime) throws Exception {
		trans.begin();
		try {
			 employeeService.changeEmployee(empNo, empName, sex, empDept, entryTime);
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
			throw e;
		}	
	}
}
