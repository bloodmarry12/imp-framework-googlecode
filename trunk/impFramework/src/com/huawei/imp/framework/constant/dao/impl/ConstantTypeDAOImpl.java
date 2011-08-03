/**
 * 
 */
package com.huawei.imp.framework.constant.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.huawei.imp.framework.common.dao.impl.IbatisCommonDAOImpl;
import com.huawei.imp.framework.constant.dao.ConstantTypeDAO;
import com.huawei.imp.framework.constant.domain.ConstantType;

/**
 * 常量数据访问层
 * @author liuguoping
 * @date 2009-8-16
 */
@Component("constantTypeDAO")
public class ConstantTypeDAOImpl extends IbatisCommonDAOImpl implements ConstantTypeDAO {

	
	/*
	 * (non-Javadoc)
	 * @see com.huawei.imp.framework.constant.dao.ConstantTypeDAO#deleteConstantType(java.lang.Integer[])
	 */
	@Override
	public void deleteConstantType(Map<String,Object> params)
	{		
		sqlMapClientTemplate.delete(warpSqlstatement(ConstantType.class, _SQL_DELETE), params);
	}

	/*
	 * (non-Javadoc)
	 * @see com.huawei.imp.framework.constant.dao.ConstantTypeDAO#insertConstantType(com.huawei.imp.framework.constant.domain.ConstantType)
	 */
	@Override
	public void insertConstantType(ConstantType ct)
	{
		this.insert(ct);
	}

	/*
	 * (non-Javadoc)
	 * @see com.huawei.imp.framework.constant.dao.ConstantTypeDAO#queryConstantType(java.util.Map, int, int)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<ConstantType> queryConstantType()
	{		
		final String sqlQuery = warpSqlstatement(ConstantType.class, "selectAll");
		return this.sqlMapClientTemplate.queryForList(sqlQuery);
	}

	/*
	 * (non-Javadoc)
	 * @see com.huawei.imp.framework.constant.dao.ConstantTypeDAO#updateConstantType(com.huawei.imp.framework.constant.domain.ConstantType)
	 */
	@Override
	public void updateConstantType(ConstantType ct)
	{
		this.update(ct);
	}
}
