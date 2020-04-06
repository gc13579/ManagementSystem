package com.web.dao;

import java.util.List;

import com.web.pojo.Dept;

public interface DeptDao {
	//查询所有部门
	public List<Dept> selectDepts();
    //添加部门
	public void insertDept(Dept dept);
    //通过部门编号查询部门
	public Dept selectDeptByNo(String deptNo);
    //修改部门
	public void updateDept(Dept dept);
   //通过编号删除部门
	public void deleteDeptByNo(String deptNo); 
	public Dept selectDeptByEmpName(String name);
	public Dept selectDeptByDeptName(String name);
}
