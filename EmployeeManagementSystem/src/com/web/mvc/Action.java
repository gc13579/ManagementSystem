package com.web.mvc;
//<action name="login" class="com.web.action.UserAction" method="doLogin">
//	<result name="success">/qqqZoneIndex.jsp</result>
//	<result name="fail">/qqqZoneLogin.jsp</result>
//</action>

import java.util.HashMap;
import java.util.Map;

public class Action {
	//很重要，是紧跟在命名空间后出现在地址中的部分，直接参与对链接的分配
	private String name;
	//当前链接应该交由哪个类来处理
//	private String className;
	private String id;
	//当前链接应该交由类中的哪个方法来处理
	private String method;
	//当请求处理完毕后需要跳转到哪个页面
	//String 配置文件中result的name属性，与目标地址一一对应
	//Result 和name对应的result对象
	private Map<String, Result> results = new HashMap<String, Result>();

	public Result getResult(String name) {
		return results.get(name);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

//	public String getClassName() {
//		return className;
//	}
//
//	public void setClassName(String className) {
//		this.className = className;
//	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public Map<String, Result> getResults() {
		return results;
	}

	public void setResults(Map<String, Result> results) {
		this.results = results;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	
}
