package com.web.dao;

import java.util.Date;
import java.util.List;

import com.web.pojo.Employee;

public interface EmployeeDao {

	public Employee selectEmployeeNoByName(String employeeName) throws Exception;
	  //通过部门查员工
	public List<Employee> selectEmpByDept(String deptName);
	//查询所有员工
	public List<Employee> selectAllEmployee();
    //员工数目（用于分页）
	public Integer countUser(String name, String dept);
    //分页查询
	public List<Employee> selectUserByPage(String name, String dept, Integer pageNo, Integer pageSize4);
    //通过id删除员工
	public void deleteEmployeeById(String id);
    //添加员工
	public void insertEmployee(Employee employee);
    //通过id查员工
	public Employee selectEmployeeById(Integer id);
    //修改员工
	public void updateEmployee(String empNo, String empName, String sex, String empDept, Date entryTime);
	public List<Employee> selectEmployeeByDeptNo(String deptName);
	public List<Employee> selectEmployeeByName(String deptMaster);


}
