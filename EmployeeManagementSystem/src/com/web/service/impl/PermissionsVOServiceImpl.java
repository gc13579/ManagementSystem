package com.web.service.impl;

import java.util.List;

import com.web.VO.PermissionsVO;
import com.web.dao.PermissionsVODao;
import com.web.pojo.Employee;
import com.web.service.PermissionsVOService;
import com.web.util.Constants;
import com.web.util.Pager;

public class PermissionsVOServiceImpl implements PermissionsVOService {
	private PermissionsVODao permissionsVODao;

	public List<PermissionsVO> getAllPermissionsVos(String roleId, String menuId) throws Exception {
		return permissionsVODao.selectAllPermissionsVos(roleId, menuId);
	}

	public void setPermissionsVODao(PermissionsVODao permissionsVODao) {
		this.permissionsVODao = permissionsVODao;
	}

	public PermissionsVODao getPermissionsVODao() {
		return permissionsVODao;
	}

	public Pager<PermissionsVO> selectAll(Integer pageNo, String menuId, String roleId) throws Exception {
		Pager<PermissionsVO> pager = new Pager<PermissionsVO>();
		pager.setPageNo(pageNo);
		System.out.println("permissionVOservce menuid:"+menuId+" roleid:"+roleId);
		Integer totalCount = permissionsVODao.countPermissions( menuId, roleId);
		if(totalCount == null) {
			totalCount = 0;
		}
		System.out.println("totalCount = "+totalCount);
		pager.setTotalPage(totalCount, Constants.PAGE_SIZE_4);
		List<PermissionsVO> users = permissionsVODao.selectPermissionsByPage( menuId, roleId,pageNo,Constants.PAGE_SIZE_4);
		pager.setList(users);
		return pager;
	}
}
