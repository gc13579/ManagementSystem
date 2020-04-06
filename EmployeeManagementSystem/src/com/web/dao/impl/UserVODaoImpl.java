package com.web.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.web.VO.PermissionsVO;
import com.web.VO.UserVO;
import com.web.dao.UserVODao;
import com.web.mapper.UserVOMapper;
import com.web.util.JDBCTemplate;

public class UserVODaoImpl implements UserVODao{
	JDBCTemplate<UserVO> temp = new JDBCTemplate<UserVO>();
	public List<UserVO> selectAllUserVos(String userAccount, String userState, String roleName) throws Exception {
		StringBuffer sql=new StringBuffer()
			.append(" select ")
			//.append(" 	u.t_user_account,u.t_user_name,u.t_user_state, ")
			//.append(" 	r.t_role_name ")
			.append(" 	u.*,r.t_role_name ")
			.append(" from  ")
			.append(" 	t_user u,t_role r ")
			.append(" where ")
			.append(" 	u.t_role_id=r.t_role_id ")
			.append(" and ")
			.append(" 1 = 1 ");
		List<Object> params=new ArrayList<Object>();
		if(userAccount!=null&&!userAccount.equals("")){
			sql.append(" and u.t_user_account = ? ");
			params.add(userAccount);
		}
		if(userState!=null&&!userState.equals("")){
			sql.append(" and u.t_user_state = ? ");
			params.add(userState);
		}
		if(roleName!=null&&!roleName.equals("")){
			sql.append(" and r.t_role_name = ? ");
			params.add(roleName);
		}
		List<UserVO> list=temp.selectAll(new UserVOMapper(), sql.toString(), params.toArray());
		return list;
	}
	public void deleteUserVoById(String id) throws Exception {
		String sql=new StringBuffer()
			.append(" delete ")	
			.append(" from ")	
			.append(" 	t_user ")	
			.append(" where ")	
			.append(" 	t_user_id = ? ")	
			.toString();
		temp.delete(sql, Integer.parseInt(id));
	}
	public UserVO selectUserVoById(String userId) {
		String sql=new StringBuffer()
			.append(" select ")
			.append(" 	u.*,r.t_role_name ")
			.append(" from  ")
			.append(" 	t_user u,t_role r ")
			.append(" where ")
			.append(" 	u.t_role_id=r.t_role_id ")
			.append(" and ")
			.append(" u.t_user_id = ? ")
			.toString();
		return temp.selectOne(new UserVOMapper(), sql, Integer.parseInt(userId));
	}
	public void updateUserInfo(String account, String employeeName, String state, String roleId) throws Exception {
		String sql=new StringBuffer()
			.append(" update ")
			.append(" 	t_user ")
			.append(" set ")
			.append("  t_user_name = ? , t_user_state = ? , t_role_id = ? ")
			.append(" where ")
			.append(" 	t_user_account = ? ")
			.toString();
		temp.update(sql, employeeName,state,roleId,account);
	}
	public void insertOneUser(String account, String employeeName, String state, String roleId) throws Exception {
		String sql=new StringBuffer()
			.append(" insert into ")
			.append(" 	t_user ")
			//.append(" (t_user_id,t_user_account,t_user_pwd,t_user_name,t_role_id,t_user_state,t_create_time) ")
			.append("  values ")
			.append(" (null,?,'123456',?,?,?,now()) ")
			.toString();
		temp.insert(sql, account,employeeName,roleId,state);
	}

}
