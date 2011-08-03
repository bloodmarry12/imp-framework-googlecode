/*
 * 文件名：HttpLoggerService.java
 * 版权：  Copyright 2000-2009 Huawei Tech. Co. Ltd. All Rights Reserved.
 * 描述：〈描述〉
 * 修改人：ahli
 * 修改时间：Oct 27, 2009
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.huawei.imp.framework.model.httplog.service;

import com.huawei.imp.framework.model.httplog.domain.HttpLoggerEntity;

/**
 * Http日志业务类
 * @author ahli
 * @version IMPV100R001DA0, Oct 27, 2009 
 * @since CMS IMPV100R001DA0
 */

public interface HttpLoggerService
{
	/**
	 * 记录日志对象
	 * @param entity
	 */
	void log(HttpLoggerEntity entity);
	
	/**
	 * 持久化日志记录到基础数据库
	 */
	void persistenceHttpLoggerEntity(int num);
}
