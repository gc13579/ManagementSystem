package com.web.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.web.VO.HolidayVO;
import com.web.dao.DeptDao;
import com.web.dao.HolidayDao;
import com.web.pojo.Dept;
import com.web.pojo.Holiday;
import com.web.service.HolidayVOService;
import com.web.util.Constants;

public class HolidayVOServiceImpl implements HolidayVOService {
	private HolidayDao holidayDao;
	private DeptDao deptDao;

	public HolidayVO getHolidayVOByNo(String holidayNo) {
		HolidayVO holidayVO = new HolidayVO();
		try {

			Holiday holiday = holidayDao.selectHolidayByNo(holidayNo);
			holidayVO.setHoliday(holiday);

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date startDate = sdf.parse(holidayVO.getStartTime());
			Date endDate = sdf.parse(holidayVO.getEndTime());
			long timeDiff = endDate.getTime() - startDate.getTime();
			int dateDiff = (int) (timeDiff / Constants.TIME_SWTICH) + 1;
			holidayVO.setTotalDays(dateDiff);

			// 1通过名字找到员工 select * from t_employee where name = ？;
			// 2通过员工找到部门 ;
			// 3通过部门找到部门领导 select t_dept_manager from t_dept d,t_employee e
			// where d.t_dept_name = e.t_emp_dept and e.t_emp_name = ?;
			Dept dept = deptDao.selectDeptByEmpName(holidayVO.getHolidayUser());
			String managerName = dept.getDeptManager();
			holidayVO.setDeptManagerName(managerName);

			String deptName = dept.getDeptName();
			holidayVO.setDeptName(deptName);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return holidayVO;
	}

	public HolidayDao getHolidayDao() {
		return holidayDao;
	}

	public void setHolidayDao(HolidayDao holidayDao) {
		this.holidayDao = holidayDao;
	}

	public DeptDao getDeptDao() {
		return deptDao;
	}

	public void setDeptDao(DeptDao deptDao) {
		this.deptDao = deptDao;
	}

}
