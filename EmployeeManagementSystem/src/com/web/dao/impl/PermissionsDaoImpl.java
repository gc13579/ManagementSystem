package com.web.dao.impl;

import java.util.List;

import com.web.dao.PermissionsDao;
import com.web.mapper.PermissionsMapper;
import com.web.pojo.Permissions;
import com.web.pojo.User;
import com.web.util.JDBCTemplate;

public class PermissionsDaoImpl implements PermissionsDao{
	JDBCTemplate<Permissions> temp=new JDBCTemplate<Permissions>();
	//删除权限
	public void deletePermissionById(String id) throws Exception {
		String sql=new StringBuffer()
			.append(" delete ")
			.append(" from ")
			.append(" 	t_permissions ")
			.append(" where ")
			.append(" 	t_id = ? ")
			.toString();
		temp.delete(sql, Integer.parseInt(id));
	}
	//新增权限
	public void insertOnePermission(Integer roleId, Integer menuId) throws Exception {
		String sql=new StringBuffer()
			.append(" insert into ")
			.append(" 	t_permissions ")
			.append(" 	(t_id,t_role_id,t_menu_id,t_create_time) ")
			.append(" values ")
			.append(" 	(null,?,?,now()) ")
			.toString();
		temp.insert(sql, roleId,menuId);
	}
	//修改权限
	public void updateOneMenuIdInPermissions(String roleId, String oldMenuId, String newMenuId) throws Exception {
		String sql=new StringBuffer()
			.append(" update ")
			.append(" 	t_permissions ")
			.append(" set ")
			.append(" 	t_menu_id = ? ")
			.append(" where ")
			.append(" 	t_menu_id = ? ")
			.append(" and ")
			.append(" 	t_role_id = ? ")
			.toString();
		temp.update(sql, Integer.parseInt(newMenuId) , Integer.parseInt(oldMenuId) , Integer.parseInt(roleId));
	}
	//根据角色id,菜单id获取权限
	public Permissions getPermissionsByRidAndMid(Integer roleId,Integer menuId) throws Exception{
		System.out.println("roleId:"+roleId+" menuId:"+menuId);
		String sql=new StringBuffer()
			.append(" select ")
			.append(" 	t_id,t_role_id,t_menu_id,t_create_time ")
			.append(" from ")
			.append(" 	t_permissions ")
			.append(" where ")
			.append(" 	t_role_id = ? ")
			.append(" and ")
			.append(" 	t_menu_id = ? ")
			.toString();
//		List<Permissions> list=temp.selectAll(new PermissionsMapper(), sql, roleId ,menuId);
//		for (Permissions permissions : list) {
//			System.out.println("%%:"+permissions);
//		}
		return temp.selectOne(new PermissionsMapper(), sql, roleId ,menuId);
	}

}
