package com.web.pojo;

import java.util.Date;



public class Permissions {
	private Integer t_id;
	private Integer t_role_id;
	private Integer t_menu_id;
	private Date t_create_time;

	public Permissions() {
		super();
	}

	public Permissions(Integer tId, Integer tRoleId, Integer tMenuId, Date tCreateTime) {
		super();
		t_id = tId;
		t_role_id = tRoleId;
		t_menu_id = tMenuId;
		t_create_time = tCreateTime;
	}

	@Override
	public String toString() {
		return "Permissions [t_create_time=" + t_create_time + ", t_id=" + t_id + ", t_menu_id=" + t_menu_id + ", t_role_id=" + t_role_id + "]";
	}

	public Integer getT_id() {
		return t_id;
	}

	public void setT_id(Integer tId) {
		t_id = tId;
	}

	public Integer getT_role_id() {
		return t_role_id;
	}

	public void setT_role_id(Integer tRoleId) {
		t_role_id = tRoleId;
	}

	public Integer getT_menu_id() {
		return t_menu_id;
	}

	public void setT_menu_id(Integer tMenuId) {
		t_menu_id = tMenuId;
	}

	public Date getT_create_time() {
		return t_create_time;
	}

	public void setT_create_time(Date tCreateTime) {
		t_create_time = tCreateTime;
	}

}
