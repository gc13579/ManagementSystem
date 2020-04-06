package com.ssm.service;

import com.ssm.pojo.Manager;

 
public interface ManagerService {
	//管理员登录
   public Manager login(String username,String password) throws Exception;
}
