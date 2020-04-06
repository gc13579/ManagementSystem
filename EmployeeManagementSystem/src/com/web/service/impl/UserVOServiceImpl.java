package com.web.service.impl;

import java.util.List;

import com.web.VO.UserVO;
import com.web.dao.EmployeeDao;
import com.web.dao.UserVODao;
import com.web.pojo.Employee;
import com.web.service.UserVOService;

public class UserVOServiceImpl implements UserVOService {
	private UserVODao userVODao;
	private EmployeeDao employeeDao;

	public UserVODao getUserVODao() {
		return userVODao;
	}

	public void setUserVODao(UserVODao userVODao) {
		this.userVODao = userVODao;
	}

	public List<UserVO> getAllUserVos(String userAccount, String userState, String roleName) throws Exception {
		List<UserVO> list = userVODao.selectAllUserVos(userAccount, userState, roleName);
		return list;
	}

	public void removeUserVoById(String id) throws Exception {
		userVODao.deleteUserVoById(id);
	}

	public UserVO getOneUserVoById(String UserId) throws Exception {
		return userVODao.selectUserVoById(UserId);
	}

	public void updateUserVo(String account, String employeeId, String employeeName, String state, String roleId) throws Exception {
		Employee employee = employeeDao.selectEmployeeNoByName(employeeName);
		if (employee == null) {
			throw new Exception("员工表无此人");
		}
		if (!employee.getEmpNo().equals(employeeId)) {
			throw new Exception("员工编号与员工不匹配");
		}
		userVODao.updateUserInfo(account, employeeName, state, roleId);
	}

	// 添加帐户
	public void addUser(String account, String employeeId, String employeeName, String state, String roleId) throws Exception {
		// 根据员工名查员工表，若无此员工或编号不匹配，无法添加
		Employee employee = employeeDao.selectEmployeeNoByName(employeeName);
		if (employee == null) {
			throw new Exception("员工表无此人");
		}
		if (!employee.getEmpNo().equals(employeeId)) {
			throw new Exception("员工编号与员工不匹配");
		}
		userVODao.insertOneUser(account, employeeName, state, roleId);
	}

	public void setEmployeeDao(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}

	public EmployeeDao getEmployeeDao() {
		return employeeDao;
	}

}
