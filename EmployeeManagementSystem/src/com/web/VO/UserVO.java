package com.web.VO;

import com.web.pojo.User;

public class UserVO extends User {
	private String t_role_name;

	public void setT_role_name(String t_role_name) {
		this.t_role_name = t_role_name;
	}

	public String getT_role_name() {
		return t_role_name;
	}

	@Override
	public String toString() {
		return "UserVO [t_role_name=" + t_role_name + "]";
	}

}
