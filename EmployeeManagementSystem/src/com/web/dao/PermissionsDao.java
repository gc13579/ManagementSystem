package com.web.dao;

import java.util.List;

import com.web.pojo.Permissions;
import com.web.pojo.User;

public interface PermissionsDao {

	public void deletePermissionById(String id) throws Exception;

	public void insertOnePermission(Integer roleId, Integer menuId) throws Exception;

	public Permissions getPermissionsByRidAndMid(Integer roleId,Integer menuId) throws Exception;
	
	public void updateOneMenuIdInPermissions(String roleId, String oldMenuId, String newMenuId) throws Exception;
}
