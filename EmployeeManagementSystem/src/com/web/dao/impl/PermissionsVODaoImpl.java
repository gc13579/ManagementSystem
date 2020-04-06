package com.web.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.web.VO.PermissionsVO;
import com.web.dao.PermissionsVODao;
import com.web.mapper.EmployeeMapper;
import com.web.mapper.PermissionsVOMapper;
import com.web.mapper.RowMapper;
import com.web.util.JDBCTemplate;

public class PermissionsVODaoImpl implements PermissionsVODao {
	JDBCTemplate<PermissionsVO> temp = new JDBCTemplate<PermissionsVO>();
	JDBCTemplate<Integer> tempCount = new JDBCTemplate<Integer>();
	public List<PermissionsVO> selectAllPermissionsVos(String roleId,String menuId) throws Exception {
		StringBuffer sql = new StringBuffer()
			.append(" select ")
			//.append(" 	tmp.t_id,tmp.t_role_id,tmp.t_menu_id,tmp.t_create_time, ")
			.append(" tmp.*, ")
			.append(" 	m.t_menu_name ")
			.append(" from ")
			.append(" 	t_menu m, ")
			.append(" 		(select ")
			.append(" 			p.*, ")
//			.append(" 			p.t_id,p.t_role_id,p.t_menu_id,p.t_create_time, ")
			.append("           r.t_role_name ")
			.append(" 		from ")
			.append(" 			t_permissions p,t_role r ")
			.append(" 		where ")
			.append(" 			p.t_role_id=r.t_role_id) tmp ")
			.append(" where ")
			.append(" 	tmp.t_menu_id=m.t_menu_id ")
			.append(" and 1 = 1 ");
		List<Object> params=new ArrayList<Object>();
		if(roleId!=null&&!roleId.equals("")){
			sql.append(" and tmp.t_role_id = ? ");
			params.add(Integer.parseInt(roleId));
		}
		if(menuId!=null&&!menuId.equals("")){
			sql.append(" and tmp.t_menu_id = ? ");
			params.add(Integer.parseInt(menuId));
		}
		return temp.selectAll(new PermissionsVOMapper(), sql.toString(),params.toArray());
	}

	public Integer countPermissions(String menuId, String roleId) throws Exception {
		System.out.println("准备查数量: menuId:"+menuId+" roleId:"+roleId);
		StringBuffer sb = new StringBuffer()
		.append(" select ")
		.append(" 	count(t_id) nums ")
		.append(" from ")
		.append(" 	t_permissions ")
		.append(" where ")
		.append(" 	1 = 1 ");
		List<Object> param = new ArrayList<Object>();
		
		if(menuId != null && !menuId.equals("")) {
			sb.append(" and t_menu_id like ? ");
			param.add("%"+menuId+"%");
		}
		if(roleId != null && !roleId.equals("")) {
			sb.append(" and t_role_id = ? ");
			param.add(roleId);
		}
		return tempCount.selectOne(new RowMapper<Integer>() {
	public Integer mapperObject(ResultSet rs) throws Exception {
		return rs.getInt("nums");
	}
}, sb.toString(), param.toArray());
	}

	public List<PermissionsVO> selectPermissionsByPage(String menuId, String roleId, Integer pageNo, Integer pageSize4) throws Exception {
		StringBuffer sql = new StringBuffer()
		.append(" select ")
		//.append(" 	tmp.t_id,tmp.t_role_id,tmp.t_menu_id,tmp.t_create_time, ")
		.append(" tmp.*, ")
		.append(" 	m.t_menu_name ")
		.append(" from ")
		.append(" 	t_menu m, ")
		.append(" 		(select ")
		.append(" 			p.*, ")
//		.append(" 			p.t_id,p.t_role_id,p.t_menu_id,p.t_create_time, ")
		.append("           r.t_role_name ")
		.append(" 		from ")
		.append(" 			t_permissions p,t_role r ")
		.append(" 		where ")
		.append(" 			p.t_role_id=r.t_role_id) tmp ")
		.append(" where ")
		.append(" 	tmp.t_menu_id=m.t_menu_id ")
		.append(" and 1 = 1 ");
	List<Object> params=new ArrayList<Object>();
	if(roleId!=null&&!roleId.equals("")){
		sql.append(" and tmp.t_role_id = ? ");
		params.add(Integer.parseInt(roleId));
	}
	if(menuId!=null&&!menuId.equals("")){
		sql.append(" and tmp.t_menu_id = ? ");
		params.add(Integer.parseInt(menuId));
	}
	sql.append(" limit ")
		  .append(" 	?,? ");
		params.add((pageNo - 1) * pageSize4);
		params.add(pageSize4);
	List<PermissionsVO> list=temp.selectAll(new PermissionsVOMapper(), sql.toString(), params.toArray());
	for (PermissionsVO permissionsVO : list) {
		System.out.println("每个permissionsVO:"+permissionsVO);
	}
	return list;
}

	

}
