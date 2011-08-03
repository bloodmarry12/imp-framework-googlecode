/*
 * 文件名：ConstantQueryForm.java
 * 版权：  Copyright 2000-2009 Huawei Tech. Co. Ltd. All Rights Reserved.
 * 描述：〈描述〉
 * 修改人：ahli
 * 修改时间：Aug 27, 2009
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.huawei.imp.framework.constant.web.form;

import java.util.Map;

import com.huawei.imp.framework.common.web.form.BaseQueryForm;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author ahli
 * @version IMPV100R001DA0, Aug 27, 2009 
 * @see [相关类/方法]
 * @since CMS IMPV100R001DA0
 */

public class ConstantQueryForm extends BaseQueryForm
{

	/**
	 * 常量含义
	 */
	private String name;
	
	/**
	 * 常量值
	 */
	private String value;
	
	/**
	 * 常量序号
	 */
	private Integer order;
	
	/**
	 * 查询标识
	 */
	private String flag;
	
	/**
	 * 常量名称
	 */
	private Integer modelID;
	
	public final static ConstantQueryForm NULL = new ConstantQueryForm();

	public Integer getModelID()
	{
		return modelID;
	}

	public void setModelID(Integer modelID)
	{
		this.modelID = modelID;
	}

	@Override
	public Map<String, Object> getParams()
	{
		Map<String, Object> params = super.getParams();
		params.put("modelID", modelID);
		params.put("name", name);
		params.put("value", value);
		params.put("order", order);
		return params;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getValue()
	{
		return value;
	}

	public void setValue(String value)
	{
		this.value = value;
	}

	public Integer getOrder()
	{
		return order;
	}

	public void setOrder(Integer order)
	{
		this.order = order;
	}

	public String getFlag()
	{
		return flag;
	}

	public void setFlag(String flag)
	{
		this.flag = flag;
	}
}
