package com.web.service;

import java.util.List;

import com.web.VO.PermissionsVO;
import com.web.pojo.Permissions;
import com.web.util.Pager;

public interface PermissionsVOService {
	//多表查询
	public List<PermissionsVO> getAllPermissionsVos(String roleId,String menuId) throws Exception;
	//分页多表查询
	public Pager<PermissionsVO> selectAll(Integer pageNo, String menuId, String roleId) throws Exception;


}
