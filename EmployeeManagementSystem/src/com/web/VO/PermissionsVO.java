package com.web.VO;

import com.web.pojo.Permissions;

public class PermissionsVO extends Permissions {
	private String t_role_name;
	private String t_menu_name;

	@Override
	public String toString() {
		return "PermissionsVO [t_menu_name=" + t_menu_name + ", t_role_name=" + t_role_name + "]";
	}

	public String getT_role_name() {
		return t_role_name;
	}

	public void setT_role_name(String tRoleName) {
		t_role_name = tRoleName;
	}

	public String getT_menu_name() {
		return t_menu_name;
	}

	public void setT_menu_name(String tMenuName) {
		t_menu_name = tMenuName;
	}

}
