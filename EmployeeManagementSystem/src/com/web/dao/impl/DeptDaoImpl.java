package com.web.dao.impl;

import java.util.List;

import com.web.dao.DeptDao;
import com.web.mapper.DeptMapper;
import com.web.pojo.Dept;
import com.web.util.JDBCTemplate;

public class DeptDaoImpl implements DeptDao {

	JDBCTemplate<Dept> temp = new JDBCTemplate<Dept>();
	public List<Dept> selectDepts() {
		String sql = new StringBuffer()
			.append(" select ")
			.append(" 	t_dept_no,t_dept_name,t_dept_loc,t_dept_manager,t_create_time,t_description ")
			.append(" from ")
			.append(" 	t_dept ")
			.toString();
		return temp.selectAll(new DeptMapper(), sql);
	}
	public void insertDept(Dept dept) {
		String sql = new StringBuffer()
			.append(" insert into ")
			.append(" 	t_dept(t_dept_no,t_dept_name,t_dept_loc,t_dept_manager,t_create_time,t_description) ")
			.append(" values ")
			.append(" 	(?,?,?,?,sysdate(),?) ")
			.toString();
		temp.insert(sql, dept.getDeptNo(),dept.getDeptName(),dept.getDeptLoc(),dept.getDeptManager(),
					dept.getDescrption());
	}
	public Dept selectDeptByNo(String deptNo) {
		String sql = new StringBuffer()
			.append(" select ")
			.append(" 	t_dept_no,t_dept_name,t_dept_loc,t_dept_manager,t_create_time,t_description ")
			.append(" from ")
			.append(" 	t_dept ")
			.append(" where ")
			.append(" 	t_dept_no = ? ")
			.toString();
		return temp.selectOne(new DeptMapper(), sql, deptNo);
	}
	public void updateDept(Dept dept) {
		String sql = new StringBuffer()
			.append(" update ")
			.append(" 	t_dept ")
			.append(" set ")
			.append(" 	t_dept_name=?,t_dept_loc=?,t_dept_manager=? ")
			.append(" where ")
			.append(" 	t_dept_no = ? ")
			.toString();
		temp.update(sql, dept.getDeptName(),dept.getDeptLoc(),dept.getDeptManager(),
					dept.getDeptNo());
	}
	public void deleteDeptByNo(String deptNo) {
		String sql = new StringBuffer()
			.append(" delete from ")
			.append(" 	t_dept ")
			.append(" where ")
			.append(" 	t_dept_no = ? ")
			.toString();
		temp.delete(sql, deptNo);
	}
	public Dept selectDeptByEmpName(String name) {
		String sql = new StringBuffer()
		.append(" select ")
		.append(" 	t_dept_no,t_dept_name,t_dept_loc,t_dept_manager,d.t_create_time,t_description ")
		.append(" from ")
		.append(" 	t_dept d,t_employee e ")
		.append(" where ")
		.append(" 	d.t_dept_name = e.t_emp_dept and ")
		.append(" 	e.t_emp_name = ? ")
		.toString();
		return temp.selectOne(new DeptMapper(), sql, name);
	}
	public Dept selectDeptByDeptName(String name) {
		String sql=new StringBuffer()
			.append(" select ")
			.append(" 	t_dept_no,t_dept_name,t_dept_loc,t_dept_manager,t_create_time,t_description ")
			.append(" from ")
			.append(" 	t_dept ")
			.append(" where ")
			.append(" 	t_dept_name = ? ")
			.toString();
		return temp.selectOne(new DeptMapper(), sql, name);
	}

}
