package com.web.service.proxy;

import java.util.List;

import com.web.VO.UserVO;
import com.web.service.UserVOService;
import com.web.trans.Transaction;

public class UserVOServiceProxy implements UserVOService {
	private UserVOService userVOService;
	private Transaction trans;

	public List<UserVO> getAllUserVos(String userAccount, String userState, String roleName) throws Exception {
		trans.begin();
		List<UserVO> list = null;
		try {
			list = userVOService.getAllUserVos(userAccount, userState, roleName);
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
		}
		return list;
	}

	public void setUserVOService(UserVOService userVOService) {
		this.userVOService = userVOService;
	}

	public UserVOService getUserVOService() {
		return userVOService;
	}

	public void setTrans(Transaction trans) {
		this.trans = trans;
	}

	public Transaction getTrans() {
		return trans;
	}

	public void removeUserVoById(String id) throws Exception {
		trans.begin();
		try {
			userVOService.removeUserVoById(id);
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
		}
	}

	public UserVO getOneUserVoById(String tUserId) throws Exception {
		trans.begin();
		UserVO userVO = null;
		try {
			userVO = userVOService.getOneUserVoById(tUserId);
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
		}
		return userVO;
	}

	public void updateUserVo(String account, String employeeId, String employeeName, String state, String roleId) throws Exception {
		trans.begin();
		try {
			userVOService.updateUserVo(account, employeeId, employeeName, state, roleId);
			trans.commit();
		} catch (Exception e) {
			//e.printStackTrace();
			trans.rollback();
			throw e;
		}
	}

	public void addUser(String account, String employeeId, String employeeName, String state, String roleId) throws Exception {
		trans.begin();
		try {
			userVOService.addUser(account, employeeId, employeeName, state, roleId);
			trans.commit();
		} catch (Exception e) {
			// e.printStackTrace();
			trans.rollback();
			throw e;
		}
	}

}
