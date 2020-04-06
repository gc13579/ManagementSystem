package com.gc_company.dao.impl;
import java.sql.SQLException;
import java.util.List;

import com.gc_company.dao.UserDao;
import com.gc_company.enity.User;
import com.gc_company.mapper.UserMapper;
import com.gc_company.util.JDBCTemplate;

public class UserDaoImpl implements UserDao{

	JDBCTemplate<User> temp=new JDBCTemplate<User>();
	@Override
	public User selectUserByUsernameAndPwd(String username,String password) {
		String sql=new StringBuffer()
			.append(" select ")
			.append(" 	id,username,password,idcard,money,phonenum,state ")
			.append(" from ")
			.append(" 	t_user ")
			.append(" where ")
			.append(" 	username = ? and ")
			.append(" 	password = ? ")
			.toString();
		User user=temp.selectOne(new UserMapper(), sql, username,password);
		return user;
	}

	@Override
	public void insertUser(String username, String password, String phone, String idCard) throws SQLException {
		String sql=new StringBuffer()
			.append(" insert into ")
			.append(" 	t_user ")
			.append(" values ")
			.append(" 	(?,?,?,?,?,?,?) ")
			.toString();
		 temp.insert(sql,null,username,password,idCard,0,phone,1);
	}

	@Override
	public List<User> selectAllUsers() {
		String sql=new StringBuffer()
			.append(" select ")
			.append(" 	id,username,password,idcard,money,phonenum,state ")
			.append(" from ")
			.append(" 	t_user ")
			.toString();
		List<User> allUsers=temp.selectAll(new UserMapper(), sql);
		return allUsers;
	}

	@Override
	public void updateUserMoneyById(Integer id,Double money) throws SQLException {
		String sql=new StringBuffer()
			.append(" update ")
			.append(" 	t_user ")
			.append(" set ")
			.append(" 	money = ? ")
			.append(" where ")
			.append(" 	id = ?  ")
			.toString();
		temp.update(sql, money, id);
	}

	@Override
	public User selectUserByUsername(String username) {
		String sql=new StringBuffer()
			.append(" select ")
			.append(" 	id,username,password,idcard,money,phonenum,state ")
			.append(" from ")
			.append(" 	t_user ")
			.append(" where ")
			.append(" 	username = ? ")
			.toString();
		return temp.selectOne(new UserMapper(), sql, username);
	}
	@Override
	public void updateUserState(User user,String userState) throws SQLException {
		String sql=new StringBuffer()
			.append(" update ")
			.append(" 	t_user ")
			.append(" set ")
			.append(" 	state = ? ")
			.append(" where ")
			.append(" 	id = ? ")
			.toString();
		temp.update(sql,userState,user.getId());
	}

}
