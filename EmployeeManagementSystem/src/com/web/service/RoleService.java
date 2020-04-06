package com.web.service;

import java.util.List;

import com.web.pojo.Role;

public interface RoleService {
	public List<Role> getAllRoles() throws Exception;

	public void addRole(String roleName) throws Exception;

	public void removeRoleById(String roleId) throws Exception;

	public Role getRoleById(String roleId) throws Exception;

	public void modifyRoleName(String roleId, String roleName) throws Exception;

	//public List<Role> getRolesExpectedId(String roleId) throws Exception;
}
