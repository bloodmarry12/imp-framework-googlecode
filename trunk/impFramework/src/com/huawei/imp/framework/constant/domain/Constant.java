/**
 * 
 */
package com.huawei.imp.framework.constant.domain;

import org.apache.log4j.Logger;

import com.huawei.imp.framework.common.domain.Namingspace;

/**
 * 系统常量域对象
 * @author ahli
 * @date 2009-8-16
 */
@Namingspace("_framework_constant")
public class Constant implements Cloneable{
	
	Logger log = Logger.getLogger(Constant.class);
	
	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * ID
	 */
	private Long id;
	
	/**
	 * 常量值
	 */
	private String value;
	
	/**
	 * 常量含义
	 */
	private String name;
	
	/**
	 * 常量序号
	 */
	private Integer order;
	
	/**
	 * 常量名称
	 */
	private Integer modelID;
	
	/**
	 * 默认构造函数
	 */
	public Constant(){}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public Integer getOrder()
	{
		return order;
	}
	public void setOrder(Integer order)
	{
		this.order = order;
	}
	public Integer getModelID()
	{
		return modelID;
	}
	public void setModelID(Integer modelID)
	{
		this.modelID = modelID;
	}
	
	@Override
	public Constant clone()
	{
		Constant cloneObject = null;
		try {
			cloneObject = (Constant)super.clone();
			cloneObject.setId(id);
			cloneObject.setValue(value);
			cloneObject.setName(name);
			cloneObject.setModelID(modelID);
			cloneObject.setOrder(order);
		} catch (CloneNotSupportedException e) {
			log.error(e.toString() + ":" + e.getMessage());
		}
		return cloneObject;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		StringBuffer sb = new StringBuffer(50);
		sb.append("id:" + id);
		sb.append(", value:" + value);
		sb.append(", name:" + name);
		sb.append(", order:" + order);
		sb.append(", modelID:" + modelID);
		return sb.toString();
	}
}
