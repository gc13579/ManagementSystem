package com.web.service.impl;

import java.util.List;

import com.web.dao.DeptDao;
import com.web.dao.EmployeeDao;
import com.web.pojo.Dept;
import com.web.pojo.Employee;
import com.web.service.DeptService;

public class DeptServiceImpl implements DeptService{
	private  DeptDao deptDao;
	private EmployeeDao employeeDao;
	public List<Dept> getAllDept() throws Exception {
		List<Dept> depts = deptDao.selectDepts();
		return depts;
	}
	public void addDept(String deptNo, String deptName, String deptLoc, String deptMaster, String deptInfo) throws Exception {
		Dept dept = new Dept();
		dept.setDeptNo(deptNo);//添加部门编号
		dept.setDeptName(deptName);//添加部门名称
		dept.setDeptLoc(deptLoc);//添加部门地址
		dept.setDeptManager(deptMaster);//添加部门负责人
		dept.setDescrption(deptInfo);//添加部门介绍
		if(deptDao.selectDeptByNo(deptNo)!=null){
			throw new Exception("不能添加重复的部门");
		}
		if(employeeDao.selectEmployeeByName(deptMaster).size()==0){
			throw new Exception("填写的经理不是员工");
		}
		deptDao.insertDept(dept);//添加部门
	}
	public void changeDept(String deptNo, String deptName, String deptLoc, String deptMaster) throws Exception {
		Dept dept = new Dept();
		dept.setDeptNo(deptNo);//添加部门编号
		dept.setDeptName(deptName);//添加部门名称
		dept.setDeptLoc(deptLoc);//添加部门位置
		dept.setDeptManager(deptMaster);//添加部门负责人
		deptDao.updateDept(dept);//修改部门
		if(employeeDao.selectEmployeeByName(deptMaster).size()==0){
			throw new Exception("部门经理不是员工");
		}
		
	}
	public void removeDeptByNo(String deptName,String deptNo2) throws Exception {
			List<Employee> employees=employeeDao.selectEmployeeByDeptNo(deptName);
			for (Employee employee : employees) {
				System.out.println(employee);
			}
			if(employees.size()!=0){
				throw new Exception("无法删除有员工的部门");
			}
			deptDao.deleteDeptByNo(deptNo2);//删除部门
	}
	public Dept getDept(String deptNo) throws Exception {
		Dept dept = deptDao.selectDeptByNo(deptNo);//查询部门
		return dept;//返回部门
	}
	public DeptDao getDeptDao() {
		return deptDao;
	}
	public void setDeptDao(DeptDao deptDao) {
		this.deptDao = deptDao;
	}
	public EmployeeDao getEmployeeDao() {
		return employeeDao;
	}
	public void setEmployeeDao(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}
	public Dept selectDeptByDeptName(String empDept) throws Exception {
		return deptDao.selectDeptByDeptName(empDept);
	}
	

}
