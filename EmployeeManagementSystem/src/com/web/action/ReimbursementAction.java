package com.web.action;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import com.web.pojo.Employee;
import com.web.pojo.Reimbursement;
import com.web.pojo.User;
import com.web.service.DeptService;
import com.web.service.EmployeeService;
import com.web.service.ReimbursementService;
import com.web.util.GetMaxReimNo;

public class ReimbursementAction {
	private ReimbursementService reimbursementService;
	private EmployeeService employeeService;
	private DeptService deptService;

	// 获取所有的请假
	public String getReimbursement(HttpServletRequest request, HttpServletResponse resp) {
		User user = (User) request.getSession().getAttribute("user");
		String reimbursementType = request.getParameter("reimbursementType");
		String applyType = request.getParameter("applyType");
		// 将“请选择”设为null符合数据库的查询逻辑
		if (applyType.equals("请选择")) {
			applyType = null;
		}
		if (reimbursementType.equals("请选择")) {
			reimbursementType = null;
		}
		List<Reimbursement> reimbursements;
		if (user.getT_role_id() != 2) {

			try {
				reimbursements = reimbursementService.getReimbursement(null, reimbursementType, applyType);
				PrintWriter out = resp.getWriter();
				out.write(JSONArray.fromObject(reimbursements).toString());
				out.flush();
				out.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			try {
				reimbursements = reimbursementService.getReimbursement(user.getT_user_name(), reimbursementType, applyType);
				PrintWriter out = resp.getWriter();
				out.write(JSONArray.fromObject(reimbursements).toString());
				out.flush();
				out.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		return "success";
	}

	public String addReimbursement(HttpServletRequest request, HttpServletResponse resp) {
		String reimType = request.getParameter("reimbursementType");
		String reimAbstract = request.getParameter("reimbursementAbstract");
		String reimMoney = request.getParameter("reimbursementMoney");
		String reimStates = request.getParameter("reimbursementStates");
		User user = (User) request.getSession().getAttribute("user");
		// 生成最新的请假编号（util类）：先查在加“1”
		String reimNo = GetMaxReimNo.createReimNo();
		try {
			reimbursementService.addReimbursement(reimNo, user.getT_user_name(), reimType, reimAbstract, reimMoney, reimStates);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}

	// 通过编号查请假
	public String selfReimbursement(HttpServletRequest request, HttpServletResponse resp) {
		String reimNo = request.getParameter("reimbursementNo");
		try {
			Reimbursement reimbursement = reimbursementService.getReimByNo(reimNo);
			request.setAttribute("reimbursement", reimbursement);
			Employee employee = employeeService.getEmployeeNoByName(reimbursement.getReimPerson());
			request.setAttribute("employee", employee);
			request.setAttribute("dept",deptService.selectDeptByDeptName(employee.getEmpDept()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}

	public String delReimbursement(HttpServletRequest request, HttpServletResponse resp) {
		String reimNo = request.getParameter("reimNo");
		try {
			reimbursementService.removeReimbursement(reimNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}

	// 修改请假
	public String change(HttpServletRequest request, HttpServletResponse resp) {
		String reimNo = request.getParameter("reimbursementNo");
		String reimType = request.getParameter("reimbursementType");
		String reimAbstract = request.getParameter("reimbursementAbstract");
		String reimMoney = request.getParameter("reimbursementMoney");
		String reimStates = request.getParameter("reimbursementStates");
		try {
			reimbursementService.changeReimbursement(reimNo, reimType, reimAbstract, reimMoney, reimStates);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";

	}

	public ReimbursementService getReimbursementService() {
		return reimbursementService;
	}

	public void setReimbursementService(ReimbursementService reimbursementService) {
		this.reimbursementService = reimbursementService;
	}

	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	public EmployeeService getEmployeeService() {
		return employeeService;
	}

	public void setDeptService(DeptService deptService) {
		this.deptService = deptService;
	}

	public DeptService getDeptService() {
		return deptService;
	}

}
