package com.web.pojo;

import java.util.Date;


public class Menu {
	private Integer t_menu_id;
	private String t_menu_name;
	private String t_href_url;
	private Integer t_parent_id;
	private Date t_create_time;

	public Menu() {
		super();
	}

	public Menu(Integer tMenuId, String tMenuName, String tHrefUrl, Integer tParentId, Date tCreateTime) {
		super();
		t_menu_id = tMenuId;
		t_menu_name = tMenuName;
		t_href_url = tHrefUrl;
		t_parent_id = tParentId;
		t_create_time = tCreateTime;
	}

	@Override
	public String toString() {
		return "Menu [t_create_time=" + t_create_time + ", t_href_url=" + t_href_url + ", t_menu_id=" + t_menu_id + ", t_menu_name=" + t_menu_name + ", t_parent_id=" + t_parent_id + "]";
	}

	public Integer getT_menu_id() {
		return t_menu_id;
	}

	public void setT_menu_id(Integer tMenuId) {
		t_menu_id = tMenuId;
	}

	public String getT_menu_name() {
		return t_menu_name;
	}

	public void setT_menu_name(String tMenuName) {
		t_menu_name = tMenuName;
	}

	public String getT_href_url() {
		return t_href_url;
	}

	public void setT_href_url(String tHrefUrl) {
		t_href_url = tHrefUrl;
	}

	public Integer getT_parent_id() {
		return t_parent_id;
	}

	public void setT_parent_id(Integer tParentId) {
		t_parent_id = tParentId;
	}

	public Date getT_create_time() {
		return t_create_time;
	}

	public void setT_create_time(Date tCreateTime) {
		t_create_time = tCreateTime;
	}

}
