/*
 * 文件名：ConstantTyepUtil.java
 * 版权：  Copyright 2000-2009 Huawei Tech. Co. Ltd. All Rights Reserved.
 * 描述：  全局值对象类
 * 修改人：liuguoping
 * 修改时间：Aug 14, 2009
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.huawei.imp.framework.constant.util;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.huawei.imp.framework.constant.domain.ConstantType;

/**
 * 用于缓存常量类型数据
 * @author liuguoping
 * @version IMPV100R001DA0, Aug 14, 2009 
 * @since CMS IMPV100R001DA0
 */
public class ConstantTypeUtil
{

	/**
	 * 缓存容器
	 */
	private static final Map<Integer, String> map = new ConcurrentHashMap<Integer, String>(500);
	
	public static void initConstantType(List<ConstantType> arg0)
	{
		map.clear();
		for(ConstantType ct : arg0)
		{
			Integer modelId = ct.getModelID();
			String modelName = ct.getModelName();
			map.put(modelId, modelName);			
		}
	}
	
	public static String getContantTypeName(Integer modelId)
	{
		return (String)map.get(modelId);
	}
	
}
