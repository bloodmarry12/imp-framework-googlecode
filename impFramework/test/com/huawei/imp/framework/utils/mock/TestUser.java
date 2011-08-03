/*
 * 文件名：User.java
 * 版权：  Copyright 2000-2009 Huawei Tech. Co. Ltd. All Rights Reserved.
 * 描述：〈描述〉
 * 修改人：ahli
 * 修改时间：Sep 14, 2009
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.huawei.imp.framework.utils.mock;

import com.huawei.imp.framework.constant.ConstantValue;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author ahli
 * @version IMPV100R001DA0, Sep 14, 2009 
 * @see [相关类/方法]
 * @since CMS IMPV100R001DA0
 */

public class TestUser
{
	private String name;
	private Integer age;
	private ConstantValue language = new ConstantValue(0);
	
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public Integer getAge()
	{
		return age;
	}
	public void setAge(Integer age)
	{
		this.age = age;
	}
	
	public ConstantValue getLanguage()
	{
		return language;
	}
	public void setLanguage(ConstantValue language)
	{
		this.language = language;
	}
	
	@Override
	public String toString()
	{
		StringBuffer sb = new StringBuffer();
		sb.append("name:" + name);
		sb.append(", age:" + age);
		sb.append(", language:" + language);
		return sb.toString();
	}
}
