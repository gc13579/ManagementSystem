package com.web.mapper;


import java.sql.ResultSet;
import java.util.Date;

import com.web.pojo.Employee;

public class EmployeeMapper implements RowMapper<Employee>{

	public Employee mapperObject(ResultSet rs) throws Exception {
		Employee employee = new Employee();
		employee.setId(rs.getInt("id"));
		employee.setEmpNo(rs.getString("t_emp_no"));
		employee.setEmpName(rs.getString("t_emp_name"));
		employee.setEmpDept(rs.getString("t_emp_dept"));
		employee.setSex(rs.getString("t_sex"));
		employee.setEducation(rs.getString("t_education"));
		employee.setEmail(rs.getString("t_email"));
		employee.setPhone(rs.getString("t_phone"));
		employee.setEntryTime(new Date(rs.getDate("t_entry_time").getTime()));
		employee.setCreateTime(new Date(rs.getTimestamp("t_create_time").getTime()));
		return employee;
	}

}