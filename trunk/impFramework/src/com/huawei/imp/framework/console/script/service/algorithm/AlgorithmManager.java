/*
 * 文件名：    AlgorithmManager.java
 * 版权:      Copyright 2000-2008 Huawei Tech. Co. Ltd. All Rights Reserved.
 * 创建人:    申卿
 * 文件描述:  
 * 修改时间:   Jun 2, 2010 1:35:48 PM
 * 修改内容：  新增
 */
package com.huawei.imp.framework.console.script.service.algorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * <br>类描述： 算法管理器</br>
 * @author  申卿
 * @version Jun 2, 2010 1:35:48 PM
 * @see
 * @since
 */
public class AlgorithmManager
{
	/**
	 * 默认的token算法名称
	 */
	private static final String DEFAULT_ALGORITHM_NAME = "Token:AES_MD5";
	
	private AlgorithmManager(){};
	
	//保存算法列表，key为算法标记，通过getTag方法获取，value为一个算法实现对象
	private static Map<String, Algorithm> algorithms = new HashMap<String, Algorithm>();
	/**
	 * 方法描述：获取注册到系统中的算法列表
	 * @return
	 * @author  申卿
	 * @version Jun 2, 2010 1:44:25 PM
	 * @修改方式：新增
	 */
	public static Map<String, Algorithm> getAlgorithms()
	{
		return algorithms;
	}
	
	/**
	 * 方法描述：注册一个算法
	 * @param algorithm
	 * @author  申卿
	 * @version Jun 2, 2010 1:56:53 PM
	 * @修改方式：新增/修改
	 */
	public static void regist(Algorithm algorithm)
	{
		algorithms.put(algorithm.getTag(), algorithm);
	}
	
	/**
	 * 方法描述：获取一个算法
	 * @param tag
	 * @return
	 * @author  申卿
	 * @version Jun 2, 2010 1:57:36 PM
	 * @修改方式：新增/修改
	 */
	public static Algorithm getAlgorithm(String tag)
	{
		if(null == tag)
		{
			return algorithms.get(DEFAULT_ALGORITHM_NAME);
		}
		return algorithms.get(tag);
	}
}
