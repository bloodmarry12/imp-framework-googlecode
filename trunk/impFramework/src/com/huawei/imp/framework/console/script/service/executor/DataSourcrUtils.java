/*
 * description: 
 * date:        下午06:45:10
 * author:      ahli
 */
package com.huawei.imp.framework.console.script.service.executor;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.NoSuchBeanDefinitionException;

import com.huawei.imp.framework.exception.BusinessException;
import com.huawei.imp.framework.logger.LogFactory;
import com.huawei.imp.framework.logger.Logger;
import com.huawei.imp.framework.utils.BeanHolder;

/**
 * <p><strong></strong></p>
 * <p></p>
 * @see
 * @version v1.0
 * @since v2.0
 * @author ahli (edit in 2010-5-29)
 */
public class DataSourcrUtils {
	
	private Map<String, DataSource> dataSources = new HashMap<String, DataSource>();
	
	
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
	 * 默认数据源：dataSource
	 */
	public static final String DS_DEFAULT = "dataSource0";
	/**
	 * 日志对象
	 */
	private static final Logger log = LogFactory.getLogger(DataSourcrUtils.class);
	
	/**
	 * 私有构造函数，避免实例化
	 */
	private DataSourcrUtils (){}
	
	/**
	 * 根据脚本，加载数据源
	 * 截取数据源配置
	 * @param script
	 * @return
	 */
	public static DataSource getDataSource(StringBuilder script){
//		String dsName = DS_DEFAULT;
		String dsName = null;
		if(log.isDebugEnabled()){
			log.debug("开始获取数据源");
		}
		if(script.indexOf(DS_CONFIG_HEADER) == 0 && 
				script.indexOf(DS_CONFIG_FOOTER) > DS_CONFIG_HEADER.length()){
			// 删除数据源头
			script.delete(0, 13);
			
			// 加载数据源名称
			dsName = script.substring(0, script.indexOf(DS_CONFIG_FOOTER));
			if(log.isDebugEnabled()){
				log.debug("解析脚本，指定数据源：" + dsName);
			}
			
			script.delete(0, script.indexOf(DS_CONFIG_FOOTER) + 3);
			log.debug("解析后的script：" + script.toString());
		}else{
			log.debug("script：" + script.toString());
		}
		DataSource ds = null;
		try{
			ds = (DataSource)BeanHolder.getBean(dsName);
		}catch (NoSuchBeanDefinitionException e) {
			throw new BusinessException("数据源[" + dsName + "]不存在!");
		}
		
		// 判断数据源是否存在
		if(null == ds){
			throw new BusinessException("数据源[" + dsName + "]不存在!");
		}
		return ds;
	}

	public Map<String, DataSource> getDataSources()
	{
		return dataSources;
	}

	public void setDataSources(Map<String, DataSource> dataSources)
	{
		this.dataSources = dataSources;
	}
}
