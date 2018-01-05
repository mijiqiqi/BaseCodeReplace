package com.http;

public enum CMRequestType {
	//获取验证码
	util_getphonecode("util/getphonecode","util/getphonecode",null),
	//普通用户登录
	user_login("user/login","user/login",null);

	private String url;//request URL 
	private String tag;
	private Class<?> cls; //entity class for parse
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Class<?> getEntityCls() {
		return cls;
	}
	public void setCls(Class<?> cls) {
		this.cls = cls;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	private CMRequestType(String url, String tag, Class<?> cls) {
		this.url = url;
		this.cls = cls;
		this.tag = tag;
	}
}
