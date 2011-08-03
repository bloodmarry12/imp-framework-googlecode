/*
 * 文件名：    DataSourceManager.java
 * 版权:      Copyright 2000-2008 Huawei Tech. Co. Ltd. All Rights Reserved.
 * 创建人:    申卿
 * 文件描述:  
 * 修改时间:   Jun 7, 2010 6:12:08 PM
 * 修改内容：  新增
 */
package com.huawei.imp.framework.console.script.service.executor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import com.huawei.imp.framework.exception.BusinessException;
import com.huawei.imp.framework.logger.LogFactory;
import com.huawei.imp.framework.logger.Logger;
import com.huawei.imp.framework.utils.BeanHolder;

/**
 * <br>类描述： </br>
 * @author  申卿
 * @version Jun 7, 2010 6:12:08 PM
 * @see
 * @since
 */
public class DataSourceManager
{
	private static Map<String, DataSource> dataSources = new HashMap<String, DataSource>();
	static
	{
		dataSources = BeanHolder.getBeans(DataSource.class);
	}
	/**
	 * 日志对象
	 */
	private static final Logger log = LogFactory.getLogger(DataSourceManager.class);
	
	/**
	 * 数据源配置头 "/*DATASOURCE="
	 */
	public static final String DS_CONFIG_HEADER =  "/*DATASOURCE=";
	
	/**
	 * 数据源配置尾 "
	 * 
	 */
	public static final String DS_CONFIG_FOOTER = "*/";
	
	/**
	 * 方法描述：首先通过从script中解析数据源，如果无法解析到，就通过传入的dataSourceName获取
	 * @param script
	 * @param dataSourceName
	 * @return
	 * @throws BusinessException
	 * @author  申卿
	 * @version Jun 8, 2010 11:29:10 AM
	 * @修改方式：新增
	 */
	public static DataSource getDataSource(String dataSourceName) throws BusinessException
	{		
		if(log.isDebugEnabled()){
			log.debug("开始获取数据源");
		}
		DataSource d = (DataSource)dataSources.get(dataSourceName);
		if(null == d)
		{
			throw new BusinessException(BeanHolder.getMessage("exception.console.script.parsedatasource.failed", new String[]{dataSourceName}));
		}
		
		return d;
	}
	
	/**
	 * 方法描述：首先通过从script中解析数据源，如果无法解析到，就通过传入的dataSourceName获取
	 * @param script
	 * @param dataSourceName
	 * @return
	 * @author  申卿
	 * @version Jun 8, 2010 1:58:36 PM
	 * @修改方式：新增
	 */
	public static String getDataSourceName(StringBuilder script, String dataSourceName)
	{
		String dsName = parseDataSourceNameFromScript(script);
		
		if(null == dsName)
		{
			//首先通过从script中解析数据源，如果无法解析到，就通过传入的dataSourceName获取
			dsName = dataSourceName;
		}
		
		return dsName;
	}
	
	/**
	 * 方法描述：从script中解析数据源名称
	 * @param script
	 * @return
	 * @author  申卿
	 * @version Jun 8, 2010 11:42:52 AM
	 * @修改方式：新增
	 */
	private static String parseDataSourceNameFromScript(StringBuilder script)
	{
		String dsName = null;
		if(script.indexOf(DS_CONFIG_HEADER) == 0 && 
				script.indexOf(DS_CONFIG_FOOTER) > DS_CONFIG_HEADER.length()){
			// 删除数据源头
			script.delete(0, 13);
			
			// 加载数据源名称
			dsName = script.substring(0, script.indexOf(DS_CONFIG_FOOTER)).trim();
			
			script.delete(0, script.indexOf(DS_CONFIG_FOOTER) + 3);
		}
		
		return dsName;
	}
	
	/**
	 * 获取所有支持的数据源
	 * @return
	 */
	public static List<String> getSupportDataSource()
	{
		List<String> dataSourceName = new ArrayList<String>();
		for(String name : dataSources.keySet())
		{
			dataSourceName.add(name);
		}
		return dataSourceName;
	}
}
