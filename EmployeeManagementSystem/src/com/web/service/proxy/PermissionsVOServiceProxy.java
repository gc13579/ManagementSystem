package com.web.service.proxy;

import java.util.List;

import com.web.VO.PermissionsVO;
import com.web.service.PermissionsVOService;
import com.web.trans.Transaction;
import com.web.util.Pager;

public class PermissionsVOServiceProxy implements PermissionsVOService {
	private PermissionsVOService permissionsVOService;
	private Transaction trans;

	public PermissionsVOService getPermissionsVOService() {
		return permissionsVOService;
	}

	public void setPermissionsVOService(PermissionsVOService permissionsVOService) {
		this.permissionsVOService = permissionsVOService;
	}

	public Transaction getTrans() {
		return trans;
	}

	public void setTrans(Transaction trans) {
		this.trans = trans;
	}

	public List<PermissionsVO> getAllPermissionsVos(String roleId, String menuId) throws Exception {
		trans.begin();
		List<PermissionsVO> list = null;
		try {
			list = permissionsVOService.getAllPermissionsVos(roleId, menuId);
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		}
		return list;
	}

	public Pager<PermissionsVO> selectAll(Integer pageNo, String menuId, String roleId) throws Exception {
		trans.begin();
		Pager<PermissionsVO> list = null;
		try {
			list = permissionsVOService.selectAll(pageNo,roleId, menuId);
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		}
		return list;
	}

}
