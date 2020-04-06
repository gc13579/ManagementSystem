package com.web.service;

import java.util.List;

import com.web.pojo.Dept;

public interface DeptService {
	// 获取所有部门
	public List<Dept> getAllDept() throws Exception;

	// 添加部门
	public void addDept(String deptNo, String deptName, String deptLoc, String deptMaster, String deptInfo) throws Exception;

	// 通过部门编号获取部门
	public Dept getDept(String deptNo) throws Exception;

	// 修改部门
	public void changeDept(String deptNo, String deptName, String deptLoc, String deptMaster) throws Exception;

	// 删除部门
	public void removeDeptByNo(String deptName, String deptNo2) throws Exception;

	public Dept selectDeptByDeptName(String empDept) throws Exception;

}
