/*
 * 文件名：HttpLoggerDAOImpl.java
 * 版权：  Copyright 2000-2009 Huawei Tech. Co. Ltd. All Rights Reserved.
 * 描述：〈描述〉
 * 修改人：ahli
 * 修改时间：Oct 28, 2009
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.huawei.imp.framework.model.httplog.dao.impl;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.huawei.imp.framework.common.dao.impl.IbatisCommonDAOImpl;
import com.huawei.imp.framework.model.httplog.dao.HttpLoggerDAO;
import com.huawei.imp.framework.model.httplog.domain.HttpLoggerEntity;

/**
 * DB数据库实现类
 * @author ahli
 * @version IMPV100R001DA0, Oct 28, 2009 
 * @see com.huawei.imp.framework.common.dao.impl.IbatisCommonDAOImpl
 * @see com.huawei.imp.framework.model.httplog.dao.HttpLoggerDAO
 * @since CMS IMPV100R001DA0
 */
@Component("httpLoggerOracleDAO")
public class HttpLoggerOracleDAOImpl extends IbatisCommonDAOImpl implements
		HttpLoggerDAO
{
	/* (non-Javadoc)
	 * @see com.huawei.imp.framework.model.httplog.dao.HttpLoggerDAO#batchDelete(java.util.List)
	 */
	@Override
	public void batchDelete(List<HttpLoggerEntity> arg0)
	{
		final String sql = super.warpSqlstatement(HttpLoggerEntity.class, _SQL_DELETE);
		super.batchDelete(sql, arg0);
	}

	/* (non-Javadoc)
	 * @see com.huawei.imp.framework.model.httplog.dao.HttpLoggerDAO#batchInsert(java.util.List)
	 */
	@Override
	public void batchInsert(Collection<HttpLoggerEntity> arg0)
	{
		final String sql = super.warpSqlstatement(HttpLoggerEntity.class, _SQL_INSERT);
		super.batchInsert(sql, arg0);
	}

	/* (non-Javadoc)
	 * @see com.huawei.imp.framework.model.httplog.dao.HttpLoggerDAO#query(java.util.Map)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<HttpLoggerEntity> query(Map<String, Object> params)
	{
		return super.sqlMapClientTemplate.queryForList(_SQL_SELECT, params);
	}
}
