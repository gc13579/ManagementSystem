package com.web.pojo;

import java.util.Date;

public class User {
	private Integer userId;
	private String userAccount;
	private String userPwd;
	protected String userName;
	private Integer roleId;
	private String userState;
	private Date createTime;

	public User() {
		super();
	}

	@Override
	public String toString() {
		return "User [createTime=" + createTime + ", roleId=" + roleId + ", userAccount=" + userAccount + ", userId=" + userId + ", userName=" + userName + ", userPwd=" + userPwd + ", userState="
				+ userState + "]";
	}

	public User(Integer tUserId, String tUserAccount, String tUserPwd, String tUserName, Integer tRoleId, String tUserState, Date tCreateTime) {
		super();
		userId = tUserId;
		userAccount = tUserAccount;
		userPwd = tUserPwd;
		userName = tUserName;
		roleId = tRoleId;
		userState = tUserState;
		createTime = tCreateTime;
	}

	public Integer getT_user_id() {
		return userId;
	}

	public void setT_user_id(Integer tUserId) {
		userId = tUserId;
	}

	public String getT_user_account() {
		return userAccount;
	}

	public void setT_user_account(String tUserAccount) {
		userAccount = tUserAccount;
	}

	public String getT_user_pwd() {
		return userPwd;
	}

	public void setT_user_pwd(String tUserPwd) {
		userPwd = tUserPwd;
	}

	public String getT_user_name() {
		return userName;
	}

	public void setT_user_name(String tUserName) {
		userName = tUserName;
	}

	public Integer getT_role_id() {
		return roleId;
	}

	public void setT_role_id(Integer tRoleId) {
		roleId = tRoleId;
	}

	public String getT_user_state() {
		return userState;
	}

	public void setT_user_state(String tUserState) {
		userState = tUserState;
	}

	public Date getT_create_time() {
		return createTime;
	}

	public void setT_create_time(Date tCreateTime) {
		createTime = tCreateTime;
	}

}
