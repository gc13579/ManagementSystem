package com.ssm.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssm.dao.ManagerDao;
import com.ssm.pojo.Manager;
import com.ssm.service.ManagerService;

@Service
public class ManagerServiceImpl implements ManagerService {
	@Autowired
	private ManagerDao managerDao;
	//管理员登录
	@Transactional
	public Manager login(String username, String password) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("username", username);
		map.put("password", password);
		Manager manager = managerDao.selectManagerByAccountAndPwd(map);
		if (manager == null) {
			throw new Exception("用户名或密码错误，请重新输入");
		}
		return manager;
	}

}
