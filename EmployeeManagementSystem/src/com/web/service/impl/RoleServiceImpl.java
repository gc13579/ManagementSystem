package com.web.service.impl;

import java.util.List;

import com.web.dao.RoleDao;
import com.web.dao.UserDao;
import com.web.pojo.Role;
import com.web.pojo.User;
import com.web.service.RoleService;

public class RoleServiceImpl implements RoleService {
	private RoleDao roleDao;
	private UserDao userDao;

	public RoleDao getRoleDao() {
		return roleDao;
	}

	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}

	// 查询所有角色
	public List<Role> getAllRoles() throws Exception {
		return roleDao.selectAllRoles();
	}

	// 添加一条角色
	public void addRole(String roleName) throws Exception {
		List<Role> list = roleDao.selectAllRoles();
		for (int i = 0; i < list.size(); i++) {
			if (roleName.equals(list.get(i).getT_role_name())) {
				throw new Exception("要添加的角色已存在");
			}
		}
		roleDao.insertRole(roleName);
	}

	// 删除一条角色
	public void removeRoleById(String roleId) throws Exception {
		List<User> list=userDao.selectUserByRid(Integer.parseInt(roleId));
		System.out.println("list:"+list);
		for (User user : list) {
			System.out.println(user);
		}
		if(list.size()!=0){
			throw new Exception("不能删除有帐户的角色");
		}
		roleDao.removeRoleById(roleId);
	}

	// 获取一条角色
	public Role getRoleById(String roleId) throws Exception {
		return roleDao.selectRoleById(roleId);
	}

	// 修改一条角色名称
	public void modifyRoleName(String roleId, String roleName) throws Exception {
		roleDao.updateRoleNameById(roleId, roleName);

	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public UserDao getUserDao() {
		return userDao;
	}

	// 获取除了指定id之外的所有角色
//	public List<Role> getRolesExpectedId(String roleId) throws Exception {
//		return roleDao.selectRolesExpectedId(roleId);
//	}

}
