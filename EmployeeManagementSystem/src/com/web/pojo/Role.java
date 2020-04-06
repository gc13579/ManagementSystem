package com.web.pojo;

import java.util.Date;

public class Role {
	private Integer t_role_id;
	private String t_role_name;
	private Date t_create_time;

	@Override
	public String toString() {
		return "Role [t_create_time=" + t_create_time + ", t_role_id=" + t_role_id + ", t_role_name=" + t_role_name + "]";
	}

	public Integer getT_role_id() {
		return t_role_id;
	}

	public void setT_role_id(Integer tRoleId) {
		t_role_id = tRoleId;
	}

	public String getT_role_name() {
		return t_role_name;
	}

	public void setT_role_name(String tRoleName) {
		t_role_name = tRoleName;
	}

	public Date getT_create_time() {
		return t_create_time;
	}

	public void setT_create_time(Date tCreateTime) {
		t_create_time = tCreateTime;
	}
}
