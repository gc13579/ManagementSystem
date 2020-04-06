package com.web.service.proxy;

import java.util.List;

import com.web.pojo.Dept;
import com.web.pojo.User;
import com.web.service.DeptService;
import com.web.service.UserService;
import com.web.trans.Transaction;

public class DeptServiceProxy implements DeptService{
	private DeptService deptService;
	private Transaction trans;
	public List<Dept> getAllDept() throws Exception  {
		trans.begin();
		List<Dept> depts;
		try {
			depts = deptService.getAllDept();
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
			throw e;
		}
		return depts;
	}
	public void addDept(String deptNo, String deptName, String deptLoc, String deptMaster, String deptInfo) throws Exception {
		trans.begin();
		try {
			deptService.addDept(deptNo, deptName, deptLoc, deptMaster, deptInfo);
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
			throw e;
		}
	}
	public Dept getDept(String deptNo) throws Exception {
		trans.begin();
		Dept dept;
		try {
			dept = deptService.getDept(deptNo);
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
			throw e;
		}
		return dept;
	}
	public void changeDept(String dpetNo, String deptName, String deptLoc, String deptMaster) throws Exception {
		trans.begin();
		try {
			deptService.changeDept(dpetNo,deptName,deptLoc,deptMaster);
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
			throw e;
		}
		
	}
	public void removeDeptByNo(String deptName,String deptNo2) throws Exception {
		trans.begin();
		try {
			deptService.removeDeptByNo(deptName,deptNo2);
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
			throw e;
		}
	}
	public void setDeptService(DeptService deptService) {
		this.deptService = deptService;
	}
	public DeptService getDeptService() {
		return deptService;
	}
	public Transaction getTrans() {
		return trans;
	}
	public void setTrans(Transaction trans) {
		this.trans = trans;
	}
	public Dept selectDeptByDeptName(String empDept) throws Exception {
		trans.begin();
		Dept dept=null;
		try {
			dept=deptService.selectDeptByDeptName(empDept);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dept;
	}


}
