package com.web.dao;

import java.util.List;

import com.web.pojo.Role;

public interface RoleDao {

	public List<Role> selectAllRoles() throws Exception;

	public void insertRole(String roleName) throws Exception;

	public void removeRoleById(String roleId) throws Exception;

	public Role selectRoleById(String roleId) throws Exception;

	public void updateRoleNameById(String roleId, String roleName) throws Exception;

	//public List<Role> selectRolesExpectedId(String roleId) throws Exception;

}
