/**
 * 
 */
package com.huawei.imp.framework.constant.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.huawei.imp.framework.common.dao.impl.IbatisCommonDAOImpl;
import com.huawei.imp.framework.constant.dao.ConstantDAO;
import com.huawei.imp.framework.constant.domain.Constant;
import com.huawei.imp.framework.utils.PageResult;

/**
 * 常量数据访问层
 * @author ahli
 * @date 2009-8-16
 */
@Component("constantDAO")
public class ConstantDAOImpl extends IbatisCommonDAOImpl implements ConstantDAO {

	/*
	 * (non-Javadoc)
	 * @see com.huawei.imp.framework.constant.dao.ConstantDAO#query(java.util.Map, int, int)
	 */
	@Override
	public PageResult query(Map<String, Object> params, int pageNum,
			int pageSize)
	{		
		final String sqlCount = warpSqlstatement(Constant.class, "countForPage");
		final String sqlQuery = warpSqlstatement(Constant.class, "queryForPage");
		return queryForPageResult(sqlCount, sqlQuery, params, pageSize, pageNum);
	}

	/*
	 * (non-Javadoc)
	 * @see com.huawei.imp.framework.constant.dao.ConstantDAO#delete(java.util.Map)
	 */
	@Override
	public void delete(Map<String, Object> params)
	{
		super.sqlMapClientTemplate.delete(warpSqlstatement(Constant.class, _SQL_DELETE), params);
	}

	/*
	 * (non-Javadoc)
	 * @see com.huawei.imp.framework.constant.dao.ConstantDAO#insert(com.huawei.imp.framework.constant.domain.Constant)
	 */
	@Override
	public void insert(Constant c)
	{
		super.insert(c);
	}

	/*
	 * (non-Javadoc)
	 * @see com.huawei.imp.framework.constant.dao.ConstantDAO#update(com.huawei.imp.framework.constant.domain.Constant)
	 */
	@Override
	public void update(Constant c)
	{
		super.update(c);
	}

	/*
	 * (non-Javadoc)
	 * @see com.huawei.imp.framework.constant.dao.ConstantDAO#selectByModel(java.util.Map)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Constant> selectByModel(Map<String, Object> params)
	{
		return this.sqlMapClientTemplate.queryForList(warpSqlstatement(Constant.class, "queryForlist"), params);
	}

	
}
