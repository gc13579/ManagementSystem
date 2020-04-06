package com.web.service.proxy;

import java.util.List;

import com.web.pojo.Permissions;
import com.web.pojo.User;
import com.web.service.PermissionsService;
import com.web.trans.Transaction;

public class PermissionsServiceProxy implements PermissionsService {
	private PermissionsService permissionsService;
	private Transaction trans;

	public void removePermissionsVOById(String id) throws Exception {
		trans.begin();
		try {
			permissionsService.removePermissionsVOById(id);
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		}
	}

	public void setTrans(Transaction trans) {
		this.trans = trans;
	}

	public Transaction getTrans() {
		return trans;
	}

	public void setPermissionsService(PermissionsService permissionsService) {
		this.permissionsService = permissionsService;
	}

	public PermissionsService getPermissionsService() {
		return permissionsService;
	}

	public void addOnePermission(Integer roleId, Integer menuId) throws Exception {
		trans.begin();
		try {
			permissionsService.addOnePermission(roleId, menuId);
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		}
	}

	public void modifyOneMenuIdInPermissions(String roleId, String oldMenuId, String newMenuId) throws Exception {
		trans.begin();
		try {
			permissionsService.modifyOneMenuIdInPermissions(roleId, oldMenuId, newMenuId);
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		}
	}

}
