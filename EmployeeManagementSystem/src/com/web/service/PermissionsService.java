package com.web.service;

import java.util.List;

import com.web.pojo.Permissions;
import com.web.pojo.User;
import com.web.trans.Transaction;

public interface PermissionsService {

	public void removePermissionsVOById(String id) throws Exception;
	// public List<Permissions> selectAllPermissions(User user) throws
	// Exception;

	public void addOnePermission(Integer roleId, Integer menuId) throws Exception;

	public void modifyOneMenuIdInPermissions(String roleId, String oldMenuId, String newMenuId) throws Exception;

}
