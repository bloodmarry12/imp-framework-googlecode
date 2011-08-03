package com.huawei.imp.framework.config.domain;

import com.huawei.imp.framework.common.domain.Namingspace;


/**
 * Description:
 * 
 * @author ahli
 * Apr 21, 2009
 * 
 */
@Namingspace("_framework_config")
public class SysConfig implements Cloneable {

	/**
	 * 配置参数关键字
	 */
	private String key;
	/**
	 * 配置参数名称
	 */
	private String name;
	/**
	 * 配置参数值
	 */
	private String value;
	/**
	 * 配置参数描述
	 */
	private String description;
	
	/**
	 * 平台编码
	 */
	private String scope;
	
	/**
	 * ip地址
	 */
	private String address;
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Override
	public String toString()
	{
		StringBuffer sb = new StringBuffer(50);
		sb.append("key:" + key);
		sb.append(", name:" + name);
		sb.append(", value:" + value);
		sb.append(", description:" + description);
		sb.append(", scope:" + scope);
		sb.append(", address:" + address);
		return sb.toString();
	}
	
	@Override
	public SysConfig clone() throws CloneNotSupportedException
	{
		SysConfig sysConfigClone = (SysConfig)super.clone();
		sysConfigClone.setAddress(this.address);
		sysConfigClone.setDescription(this.description);
		sysConfigClone.setKey(this.key);
		sysConfigClone.setName(this.name);
		sysConfigClone.setScope(this.scope);
		sysConfigClone.setValue(this.value);
		return sysConfigClone;
	}	
	
	
}
