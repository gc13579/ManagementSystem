package com.web.service;

import java.util.List;

import com.web.VO.UserVO;

public interface UserVOService {
	public List<UserVO> getAllUserVos(String userAccount, String userState, String roleName) throws Exception;

	public void removeUserVoById(String id) throws Exception;

	public UserVO getOneUserVoById(String tUserId) throws Exception;

	public void updateUserVo(String account, String employeeId, String employeeName, String state, String roleId) throws Exception;

	public void addUser(String account, String employeeId, String employeeName, String state, String roleId) throws Exception;
}
