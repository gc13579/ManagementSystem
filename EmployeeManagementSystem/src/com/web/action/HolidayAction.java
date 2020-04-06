package com.web.action;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.web.VO.HolidayVO;
import com.web.pojo.Holiday;
import com.web.pojo.User;
import com.web.service.HolidayService;
import com.web.util.GetMaxHolidayNo;

public class HolidayAction {
	private HolidayService holidayService;


	// 获取所有请假
	public String getHoliday(HttpServletRequest request, HttpServletResponse resp) {
		User user = (User) request.getSession().getAttribute("user");
		String holidayType = request.getParameter("holidayType");
		String applyType = request.getParameter("applyType");
		String holidayUser = request.getParameter("holidayUser");
		List<Holiday> holidays = null;
		// 将“请选择”设为null为了符合数据库的查询逻辑
		if (applyType.equals("请选择")) {
			applyType = null;
		}
		if (holidayType.equals("请选择")) {
			holidayType = null;
		}
		if (user.getT_role_id() != 2) {

			if (holidayUser.equals("")) {
				try {
					holidays = holidayService.getHolidayByEmpName(null, holidayType, applyType);
					PrintWriter out = resp.getWriter();
					out.write(JSONArray.fromObject(holidays).toString());
					out.flush();
					out.close();

				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				try {
					holidays = holidayService.getHolidayByEmpName(holidayUser, holidayType, applyType);
					PrintWriter out = resp.getWriter();
					out.write(JSONArray.fromObject(holidays).toString());
					out.flush();
					out.close();

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} else {
			if (holidayUser.equals("")) {
				try {
					holidays = holidayService.getHolidayByEmpName(user.getT_user_name(), holidayType, applyType);
					PrintWriter out = resp.getWriter();
					out.write(JSONArray.fromObject(holidays).toString());
					out.flush();
					out.close();

				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				try {
					holidays = holidayService.getHolidayByEmpName(holidayUser, holidayType, applyType);
					PrintWriter out = resp.getWriter();
					out.write(JSONArray.fromObject(holidays).toString());
					out.flush();
					out.close();

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return "success";
	}

	// 删除假期
	public String delHoliday(HttpServletRequest request, HttpServletResponse resp) {
		String holidayNo = request.getParameter("holidayNo");
		try {
			holidayService.removeHoliday(holidayNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}

	// 根据假期编号查询假期
	public String selfHoliday(HttpServletRequest request, HttpServletResponse resp) {
		String holidayNo = request.getParameter("holidayNo");
		try {
			Holiday holiday = holidayService.getHolidayByNo(holidayNo);
			PrintWriter out = resp.getWriter();
			out.write(JSONObject.fromObject(holiday).toString());
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}

	// 添加请假
	public String addHoliday(HttpServletRequest request, HttpServletResponse resp) {
		String holidayType = request.getParameter("holidayType");
		String holidayBz = request.getParameter("holidayInfo");
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		String holidayStates = request.getParameter("holidayStates");
		User user = (User) request.getSession().getAttribute("user");
		String holidayNo = GetMaxHolidayNo.createHolidayNo();
		try {
			holidayService.addHoliday(holidayNo, user.getT_user_name(), holidayType, holidayBz, startTime, endTime, holidayStates);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}

	// 修改请假
	public String change(HttpServletRequest request, HttpServletResponse resp) {
		String holidayNo = request.getParameter("holidayNo");
		String holidayType = request.getParameter("holidayType");
		String holidayBz = request.getParameter("holidayInfo");
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		String holidayStates = request.getParameter("holidayStates");
		try {
			holidayService.changeHoliday(holidayNo, holidayType, holidayBz, startTime, endTime, holidayStates);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";

	}

	public void setHolidayService(HolidayService holidayService) {
		this.holidayService = holidayService;
	}

	public HolidayService getHolidayService() {
		return holidayService;
	}
}
