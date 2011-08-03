/*
 * 文件名：HttpLoggerDAO.java
 * 版权：  Copyright 2000-2009 Huawei Tech. Co. Ltd. All Rights Reserved.
 * 描述：〈描述〉
 * 修改人：ahli
 * 修改时间：Oct 28, 2009
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.huawei.imp.framework.model.httplog.dao;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.huawei.imp.framework.common.dao.IbatisSupportDAO;
import com.huawei.imp.framework.model.httplog.domain.HttpLoggerEntity;

/**
 * Http日志访问接口
 * 〈功能详细描述〉
 * @author ahli
 * @version IMPV100R001DA0, Oct 28, 2009 
 * @see [相关类/方法]
 * @since CMS IMPV100R001DA0
 */

public interface HttpLoggerDAO extends IbatisSupportDAO
{
	/**
	 * 数据查询，按照升序排列；
	 * @return
	 */
	List<HttpLoggerEntity> query(Map<String, Object> params);
	
	/**
	 * 批量插入
	 * @param arg0
	 */
	void batchInsert(Collection<HttpLoggerEntity> arg0);
	
	/**
	 * 批量删除
	 * @param arg0
	 */
	void batchDelete(List<HttpLoggerEntity> arg0);
}
