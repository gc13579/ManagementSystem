package com.web.mvc;
//	<result name="success">/qqqZoneIndex.jsp</result>
public class Result {
	//action通过返回name来决定方法执行完后要跳转哪个result中配置的地址
	private String name;
	//连接有两种方式，重定向和转发，可以通过type属性自定义
	private String type;
	//当前result中配置的目标地址
	private String targetUrl;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTargetUrl() {
		return targetUrl;
	}
	public void setTargetUrl(String targetUrl) {
		this.targetUrl = targetUrl;
	}
	
}
