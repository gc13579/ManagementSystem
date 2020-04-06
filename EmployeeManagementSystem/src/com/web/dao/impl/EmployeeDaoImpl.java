package com.web.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.web.dao.EmployeeDao;
import com.web.mapper.EmployeeMapper;
import com.web.mapper.RowMapper;
import com.web.pojo.Employee;
import com.web.util.JDBCTemplate;

public class EmployeeDaoImpl implements EmployeeDao{
	JDBCTemplate<Employee> temp = new JDBCTemplate<Employee>();
	JDBCTemplate<Integer> tempCount = new JDBCTemplate<Integer>();

	public Employee selectEmployeeNoByName(String employeeName) throws Exception {
		String sql=new StringBuffer()
			.append(" select ")
			.append(" 	id,t_emp_no,t_emp_name,t_emp_dept,t_sex,t_education,t_email,t_phone,t_entry_time,t_create_time ")
			.append(" from ")
			.append("  t_employee ")
			.append(" where ")
			.append(" 	t_emp_name = ? ")
			.toString();
		Employee e=temp.selectOne(new EmployeeMapper(), sql, employeeName);
		return e;
	}
	public List<Employee> selectEmpByDept(String deptName) {
		String sql = new StringBuffer()
			.append(" select ")
			.append(" 	id,t_emp_no,t_emp_name,t_emp_dept,t_sex,t_education,t_email,t_phone,t_entry_time,t_create_time ")
			.append(" from ")
			.append(" 	t_employee ")
			.append(" where ")
			.append(" 	t_emp_dept = ? ")
			.toString();
		return temp.selectAll(new EmployeeMapper(), sql, deptName);
	}
	public List<Employee> selectAllEmployee() {
		String sql = new StringBuffer()
		.append(" select ")
		.append(" 	id,t_emp_no,t_emp_name,t_emp_dept,t_sex,t_education,t_email,t_phone,t_entry_time,t_create_time ")
		.append(" from ")
		.append(" t_employee ")
		.toString();
		return temp.selectAll(new EmployeeMapper(), sql);
	}
	public Integer countUser(String name, String dept) {
		StringBuffer sb = new StringBuffer()
		.append(" select ")
		.append(" 	count(id) nums ")
		.append(" from ")
		.append(" 	t_employee ")
		.append(" where ")
		.append(" 	1 = 1 ");
		List<Object> param = new ArrayList<Object>();
		
		if(name != null && !name.equals("")) {
			sb.append(" and t_emp_name like ? ");
			param.add("%"+name+"%");
		}
		if(dept != null && !dept.equals("")) {
			sb.append(" and t_emp_dept = ? ");
			param.add(dept);
		}
		return tempCount.selectOne(new RowMapper<Integer>() {
	public Integer mapperObject(ResultSet rs) throws Exception {
		return rs.getInt("nums");
	}
}, sb.toString(), param.toArray());
	}
	public List<Employee> selectUserByPage(String name, String dept, Integer pageNo, Integer pageSize4) {
		
		StringBuffer sb = new StringBuffer()
		.append(" select ")
		.append(" 	id,t_emp_no,t_emp_name,t_emp_dept,t_sex,t_education,t_email,t_phone,t_entry_time,t_create_time ")
		.append(" from ")
		.append(" 	t_employee ")
		.append(" where ")
		.append(" 	1 = 1 ");
		List<Object> param = new ArrayList<Object>();
		
		if(name != null && !name.equals("")) {
			sb.append(" and t_emp_name like ? ");
			param.add("%"+name+"%");
		}
		if(dept != null && !dept.equals("")) {
			sb.append(" and t_emp_dept = ? ");
			param.add(dept);
		}
		sb.append(" limit ")
		  .append(" 	?,? ");
		param.add((pageNo - 1) * pageSize4);
		param.add(pageSize4);
	
	return temp.selectAll(new EmployeeMapper(), sb.toString(), param.toArray() );
	}
	public void deleteEmployeeById(String id) {
		String sql = new StringBuffer()
		.append(" delete from ")
		.append(" 	t_employee ")
		.append(" where ")
		.append(" 	id = ? ")
		.toString();
	   temp.delete(sql, id);
		
	}
	public void insertEmployee(Employee employee) {
        String sql = new StringBuffer()
        .append(" insert into ")
        .append(" 	t_employee ")
        .append(" values ")
        .append(" (?,?,?,?,?,?,?,?,?,?) ")
        .toString();
        temp.insert(sql,null,employee.getEmpNo(),employee.getEmpName(),employee.getEmpDept(),employee.getSex(),"本科","123","123",employee.getEntryTime(),new Date());
	}
	public Employee selectEmployeeById(Integer id) {
		String sql = new StringBuffer()
		.append(" select ")
		.append(" 	id,t_emp_no,t_emp_name,t_emp_dept,t_sex,t_education,t_email,t_phone,t_entry_time,t_create_time ")
		.append(" from ")
		.append(" 	t_employee ")
		.append(" where ")
		.append(" 	id = ? ")
		.toString();
		return temp.selectOne(new EmployeeMapper(), sql, id);
	}
	public void updateEmployee(String empNo, String empName, String sex, String empDept, Date entryTime) {
		String sql = new StringBuffer()
		.append(" update ")
		.append(" 	t_employee ")
		.append(" set ")
		.append(" 	t_emp_no=?,t_emp_name=?,t_sex=?,t_emp_dept=?,t_entry_time=? ")
		.append(" where ")
		.append(" 	t_emp_no = ?")
		.toString();
		temp.update(sql, empNo,empName,sex,empDept,entryTime,empNo);
	}
	public List<Employee> selectEmployeeByDeptNo(String deptName) {
		String sql = new StringBuffer()
		.append(" select ")
		.append(" 	id,t_emp_no,t_emp_name,t_emp_dept,t_sex,t_education,t_email,t_phone,t_entry_time,t_create_time ")
		.append(" from ")
		.append(" 	t_employee ")
		.append(" where ")
		.append(" 	t_emp_dept=? ")
		.toString();
		return temp.selectAll(new EmployeeMapper(), sql, deptName);
	}
	public List<Employee> selectEmployeeByName(String deptMaster) {
		String sql = new StringBuffer()
		.append(" select ")
		.append(" 	id,t_emp_no,t_emp_name,t_emp_dept,t_sex,t_education,t_email,t_phone,t_entry_time,t_create_time ")
		.append(" from ")
		.append(" 	t_employee ")
		.append(" where ")
		.append(" 	t_emp_name=? ")
		.toString();
		return temp.selectAll(new EmployeeMapper(), sql, deptMaster);
	}

	
}
