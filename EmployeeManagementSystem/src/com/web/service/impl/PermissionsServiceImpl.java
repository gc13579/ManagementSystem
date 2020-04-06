package com.web.service.impl;

import java.util.List;

import com.web.dao.PermissionsDao;
import com.web.pojo.Permissions;
import com.web.pojo.User;
import com.web.service.PermissionsService;

public class PermissionsServiceImpl implements PermissionsService {
	private PermissionsDao permissionsDao;

	public void setPermissionsDao(PermissionsDao permissionsDao) {
		this.permissionsDao = permissionsDao;
	}

	public PermissionsDao getPermissionsDao() {
		return permissionsDao;
	}

	public void removePermissionsVOById(String id) throws Exception {
		permissionsDao.deletePermissionById(id);
	}

	// 添加权限
	public void addOnePermission(Integer roleId, Integer menuId) throws Exception {
		Permissions allPermissions = permissionsDao.getPermissionsByRidAndMid(roleId, menuId);
		if (allPermissions != null) {
			throw new Exception("不能为角色添加重复的权限");
		}
		permissionsDao.insertOnePermission(roleId, menuId);
	}

	// 修改权限
	public void modifyOneMenuIdInPermissions(String roleId, String oldMenuId, String newMenuId) throws Exception {
		Permissions allPermissions = permissionsDao.getPermissionsByRidAndMid(Integer.parseInt(roleId), Integer.parseInt(newMenuId));
		if (allPermissions != null) {
			throw new Exception("要添加的权限已存在");
		}
		permissionsDao.updateOneMenuIdInPermissions(roleId, oldMenuId, newMenuId);
	}

}
