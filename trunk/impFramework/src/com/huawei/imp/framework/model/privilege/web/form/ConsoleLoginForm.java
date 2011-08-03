package com.huawei.imp.framework.model.privilege.web.form;

public class ConsoleLoginForm {

	public static final ConsoleLoginForm NULL = new ConsoleLoginForm();
	
	private String name;
	private String password;
	private String code;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
}
