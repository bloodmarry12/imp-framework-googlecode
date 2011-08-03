/*
 * 文件名：    TokenAlgorithm.java
 * 版权:      Copyright 2000-2008 Huawei Tech. Co. Ltd. All Rights Reserved.
 * 创建人:    申卿
 * 文件描述:  
 * 修改时间:   Jun 2, 2010 11:35:57 AM
 * 修改内容：  新增
 */
package com.huawei.imp.framework.console.script.service.algorithm;

import com.huawei.imp.framework.exception.BusinessException;
import com.huawei.imp.framework.console.script.domain.Script;


/**
 * <br>类描述： 算法接口</br>
 * @author  申卿
 * @version Jun 2, 2010 11:35:57 AM
 * @see
 * @since
 */
public interface Algorithm
{
	/**
	 * 方法描述：获取算法名称
	 * @return
	 * @author  申卿
	 * @version Jun 2, 2010 1:38:21 PM
	 * @修改方式：新增
	 */
	String getName();
	/**
	 * 方法描述：获取算法的标记
	 * @return
	 * @author  申卿
	 * @version Jun 2, 2010 1:39:55 PM
	 * @修改方式：新增
	 */
	String getTag();
	/**
	 * 方法描述：获取算法的实际处理类
	 * @return
	 * @author  申卿
	 * @version Jun 2, 2010 1:38:46 PM
	 * @修改方式：新增
	 */
	Class getAlgorithmClass();
	/**
	 * 方法描述：根据执行的脚本，生成token
	 * @param script
	 * @param keyTag token的密钥标志，通常为密钥文件的文件名
	 * @return
	 * @author  申卿
	 * @version Jun 2, 2010 11:28:52 AM
	 * @修改方式：新增
	 */
	Object createToken(Script script, String keyTag) throws BusinessException;
}
