package com.web.dao.impl;

import java.util.List;

import com.web.dao.RoleDao;
import com.web.mapper.RoleMapper;
import com.web.pojo.Role;
import com.web.util.JDBCTemplate;

public class RoleDaoImpl implements RoleDao{
	JDBCTemplate<Role> temp=new JDBCTemplate<Role>();
	//查询所有角色
	public List<Role> selectAllRoles() throws Exception {
		String sql=new StringBuffer()
			.append(" select ")
			.append("  	t_role_id,t_role_name,t_create_time ")
			.append(" from ")
			.append(" 	t_role ")
			.toString();
		List<Role> list=temp.selectAll(new RoleMapper(), sql);
		return list;
	}
	//添加一条角色
	public void insertRole(String roleName) throws Exception {
		String sql=new StringBuffer()
			.append(" insert into ")
			.append(" 	t_role ")
			.append(" 	(t_role_id,t_role_name,t_create_time) ")
			.append(" values ")
			.append(" 	(null,?,now()) ")
			.toString();
		temp.insert(sql, roleName);
	}
	//删除一条角色
	public void removeRoleById(String roleId) throws Exception {
		String sql=new StringBuffer()
			.append(" delete from ")
			.append(" 	t_role ")
			.append(" where ")
			.append(" 	t_role_id = ? ")
			.toString();
		temp.delete(sql, roleId);
	}
	//查询一条角色
	public Role selectRoleById(String roleId) throws Exception {
		String sql=new StringBuffer()
			.append(" select ")
			.append(" 	t_role_id,t_role_name,t_create_time ")
			.append(" from ")
			.append(" 	t_role ")
			.append(" where ")
			.append(" 	t_role_id = ? ")
			.toString();
		return temp.selectOne(new RoleMapper(), sql, Integer.parseInt(roleId));
	}
	//修改一条角色的名称
	public void updateRoleNameById(String roleId, String roleName) throws Exception {
		String sql=new StringBuffer()
			.append(" update ")
			.append(" 	t_role ")
			.append(" set ")
			.append(" 	t_role_name = ? ")
			.append(" where ")
			.append(" 	t_role_id = ? ")
			.toString();
		temp.update(sql, roleName , Integer.parseInt(roleId));
	}
	//获取除指定id以外的所有角色
	public List<Role> selectRolesExpectedId(String roleId) throws Exception {
		String sql=new StringBuffer()
		.append(" select ")
		.append(" 	t_role_id,t_role_name,t_create_time ")
		.append(" from ")
		.append(" 	t_role ")
//		.append(" where ")
//		.append(" 	t_role_id != ? ")
		.toString();
		return temp.selectAll(new RoleMapper(), sql);
//		return temp.selectAll(new RoleMapper(), sql, Integer.parseInt(roleId));
	}

}
