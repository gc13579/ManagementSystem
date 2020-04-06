package com.web.dao;

import java.util.List;

import com.web.VO.UserVO;

public interface UserVODao {
	public List<UserVO> selectAllUserVos(String userAccount, String userState, String roleName) throws Exception;

	public void deleteUserVoById(String id) throws Exception;

	public UserVO selectUserVoById(String userId);

	public void updateUserInfo(String account, String employeeName, String state, String roleId) throws Exception;

	public void insertOneUser(String account, String employeeName, String state, String roleId) throws Exception;
}
