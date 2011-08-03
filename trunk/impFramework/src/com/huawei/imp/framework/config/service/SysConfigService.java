package com.huawei.imp.framework.config.service;

import java.util.List;

import com.huawei.imp.framework.config.domain.SysConfig;

/**
 * Description:
 * 系统配置服务接口
 * @author ahli
 * Apr 21, 2009
 * 
 */
public interface SysConfigService {

	/**
	 * 查询配置列表
	 * @return
	 */
	List<SysConfig> findSysConfig();
	
	/**
	 * 读取配置参数信息
	 * @param key
	 * @return
	 */
	SysConfig loadSysConfig(String key);
	
	/**
	 * 更新配置参数
	 * @param config
	 */
	void updateSysConfig(SysConfig config);
	
	/**
	 * 刷新缓存
	 */
	void flushConfigCache();
	
//	/**
//	 * 新增配置参数
//	 * @param config
//	 */
//	void saveSysConfig(SysConfig config);
}
