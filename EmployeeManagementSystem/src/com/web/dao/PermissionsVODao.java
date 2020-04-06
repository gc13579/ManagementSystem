package com.web.dao;

import java.util.List;

import com.web.VO.PermissionsVO;

public interface PermissionsVODao {
	public List<PermissionsVO> selectAllPermissionsVos(String roleId,String menuId) throws Exception;

	public Integer countPermissions(String menuId, String roleId) throws Exception;

	public List<PermissionsVO> selectPermissionsByPage(String menuId, String roleId, Integer pageNo, Integer pageSize4) throws Exception;


}
