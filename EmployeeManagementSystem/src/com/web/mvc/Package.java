package com.web.mvc;

import java.util.HashMap;
import java.util.Map;

/**
 * 使用package对一类action做分包整合，
 * 使用相同的命名空间在用户访问的时候可以做区分
 * @author Administrator
 *
 */
public class Package {
	//不重要（主要是用来区分不同的package）
	private String name;
	//命名空间，也就是用户访问的链接中在项目名和实际操作名之间的部分 localhost:8080/项目名/命名空间/操作名
	private String namespace;
	//在此包下拥有的所有action
	private Map<String, Action> actions = new HashMap<String, Action>();
	//根据action的名字获取当前package中存在的action对象
	public Action getAction(String name) {
		return actions.get(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNamespace() {
		return namespace;
	}

	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}

	public Map<String, Action> getActions() {
		return actions;
	}

	public void setActions(Map<String, Action> actions) {
		this.actions = actions;
	}
	
	
}
