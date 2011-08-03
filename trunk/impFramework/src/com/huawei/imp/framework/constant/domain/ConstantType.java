/**
 * 
 */
package com.huawei.imp.framework.constant.domain;

import org.apache.log4j.Logger;

import com.huawei.imp.framework.common.domain.Namingspace;

/**
 * 系统常量类型领域对象
 * @author liuguoping
 * @date 2009-8-16
 */
@Namingspace("_framework_constantType")
public class ConstantType implements Cloneable
{
	
	Logger log = Logger.getLogger(ConstantType.class);
	
	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 1L;
	

	
	/**
	 * 类型代号
	 */
	private Integer modelID;
	
	/**
	 * 类型中文描述
	 */
	private String modelName;
	
	
	public Integer getModelID()
	{
		return modelID;
	}
	public void setModelID(Integer modelID)
	{
		this.modelID = modelID;
	}
	
	@Override
	public ConstantType clone()
	{
		ConstantType cloneObject = null;
		try {
			cloneObject = (ConstantType)super.clone();			
			cloneObject.setModelID(modelID);
			cloneObject.setModelName(modelName);
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
		sb.append(", modelID:" + modelID);
		sb.append(",modelName:"+modelName);
		return sb.toString();
	}
	
	public String getModelName()
	{
		return modelName;
	}
	
	public void setModelName(String modelName)
	{
		this.modelName = modelName;
	}
}
