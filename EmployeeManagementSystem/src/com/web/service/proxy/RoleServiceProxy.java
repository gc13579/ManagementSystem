package com.web.service.proxy;

import java.util.List;

import com.web.pojo.Role;
import com.web.service.RoleService;
import com.web.trans.Transaction;

public class RoleServiceProxy implements RoleService {
	private RoleService roleService;
	private Transaction trans;

	public RoleService getRoleService() {
		return roleService;
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	public Transaction getTrans() {
		return trans;
	}

	public void setTrans(Transaction trans) {
		this.trans = trans;
	}

	public List<Role> getAllRoles() throws Exception {
		trans.begin();
		List<Role> list = null;
		try {
			list = roleService.getAllRoles();
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		}
		return list;
	}

	public void addRole(String roleName) throws Exception {
		trans.begin();
		try {
			roleService.addRole(roleName);
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		}
	}

	public void removeRoleById(String roleId) throws Exception {
		trans.begin();
		try {
			roleService.removeRoleById(roleId);
			trans.commit();
		} catch (Exception e) {
			//e.printStackTrace();
			trans.rollback();
			throw e;
		}
	}

	public Role getRoleById(String roleId) throws Exception {
		trans.begin();
		Role role = null;
		try {
			role = roleService.getRoleById(roleId);
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		}
		return role;
	}

	public void modifyRoleName(String roleId, String roleName) throws Exception {
		trans.begin();
		try {
			roleService.modifyRoleName(roleId, roleName);
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		}
	}

//	public List<Role> getRolesExpectedId(String roleId) throws Exception {
//		trans.begin();
//		List<Role> list = null;
//		try {
//			list = roleService.getRolesExpectedId(roleId);
//			trans.commit();
//		} catch (Exception e) {
//			e.printStackTrace();
//			trans.rollback();
//			throw e;
//		}
//		return list;
//	}

}
