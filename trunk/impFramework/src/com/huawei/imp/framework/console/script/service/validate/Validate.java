/*
 * 文件名：    Validate.java
 * 版权:      Copyright 2000-2008 Huawei Tech. Co. Ltd. All Rights Reserved.
 * 创建人:    申卿
 * 文件描述:  
 * 修改时间:   Jun 3, 2010 12:49:03 PM
 * 修改内容：  新增
 */
package com.huawei.imp.framework.console.script.service.validate;

import java.util.Map;

import com.huawei.imp.framework.exception.BusinessException;

/**
 * <br>类描述： </br>
 * @author  申卿
 * @version Jun 3, 2010 12:49:03 PM
 * @see
 * @since
 */
public interface Validate
{
	/**
	 * 验证成功
	 */
	String RESULT_SUCCESS = "success";
	
	String PARAM_SCRIPT = "script";
	String PARAM_TOKEN = "token";
	String PARAM_SESSION = "session";
	
	/**
	 * 方法描述：验证
	 * @param params
	 * @return
	 * @author  申卿
	 * @version Jun 3, 2010 12:59:45 PM
	 * @修改方式：新增
	 */
	String validate(Map<String, Object> params) throws BusinessException;
}
