package com.huawei.imp.framework.config.dao.impl;

import java.util.List;

import org.springframework.orm.ibatis.SqlMapClientTemplate;

import com.huawei.imp.framework.common.dao.impl.MDSSupportIbatisCommonDAOImpl;
import com.huawei.imp.framework.config.dao.SysConfigDAO;
import com.huawei.imp.framework.config.domain.SysConfig;

public class SysConfigDAOImpl extends MDSSupportIbatisCommonDAOImpl implements SysConfigDAO {

	/**
	 * @param sqlMapClientTemplate
	 */
	public SysConfigDAOImpl(SqlMapClientTemplate sqlMapClientTemplate)
	{
		super(sqlMapClientTemplate);
	}

	/**
	 * 根据属性查询
	 */
	private static final String _SQL_SELECTBYPROPERITES = "selectByProperties";
	
	/**
	 * 查询全局配置项
	 */
	private static final String _SQL_SELECTGLOABL = "selectGlobalConfig";
	
	/**
	 * 查询平台配置项
	 */
	private static final String _SQL_SELECTPLAT = "selectPlatConfig";
	
	@SuppressWarnings("unchecked")
	@Override
	public List<SysConfig> selectServerConfigByAddress(Class<SysConfig> clazz,String ipAddress) 
	{
		return sqlMapClientTemplate.queryForList(warpSqlstatement(clazz, _SQL_SELECTBYPROPERITES),ipAddress);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SysConfig> selectGlobalConfig(Class<SysConfig> clazz)
	{
		return sqlMapClientTemplate.queryForList(warpSqlstatement(clazz, _SQL_SELECTGLOABL));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SysConfig> selectPlatConfigByCode(Class<SysConfig> clazz,
			String platformCode)
	{
		return sqlMapClientTemplate.queryForList(warpSqlstatement(clazz, _SQL_SELECTPLAT), platformCode);
	}
}
