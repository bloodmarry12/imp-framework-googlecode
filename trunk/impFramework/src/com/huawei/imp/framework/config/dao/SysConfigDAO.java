package com.huawei.imp.framework.config.dao;

import java.util.List;

import com.huawei.imp.framework.common.dao.IbatisSupportDAO;
import com.huawei.imp.framework.config.domain.SysConfig;


/**
 * Description:
 * 系统配置DAO
 * @author ahli
 * Apr 21, 2009
 * 
 */
public interface SysConfigDAO extends IbatisSupportDAO
{
	/**
	 * 根据属性加载
	 * @param <T>
	 * @param clazz 
	 * @param ipAddress ip地址
	 * @return
	 */
	 
	List<SysConfig> selectServerConfigByAddress(Class<SysConfig> clazz,String ipAddress);
	
	/**
	 * 查询全局配置项
	 * @param clazz
	 * @return
	 */
	List<SysConfig> selectGlobalConfig(Class<SysConfig> clazz);
	
	/**
	 * 根据平台代码查询平台配置项
	 * @param clazz
	 * @param platformCode
	 * @return
	 */
	List<SysConfig> selectPlatConfigByCode(Class<SysConfig> clazz ,String platformCode);
}
