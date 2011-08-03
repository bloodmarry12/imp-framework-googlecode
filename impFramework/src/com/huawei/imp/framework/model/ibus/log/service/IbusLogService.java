package com.huawei.imp.framework.model.ibus.log.service;

import com.huawei.imp.framework.model.ibus.log.domain.IbusLogerEntity;

/**
 * IBUS数据交互日志接口
 * @author ahli
 * @version IMPV100R001DA0, Oct 12, 2009 
 * @since CMS IMPV100R001DA0
 */
public interface IbusLogService {

	/**
	 * 日志数据从Derby的嵌入式数据库，批量入库到系统
	 * 基础数据库中；
	 * @param number    批量操作的数据量
	 */
	@Deprecated
	void dataToDatabase(int number);
	
	/**
	 * 日志数据从Derby的嵌入式数据库，批量入库到系统
	 * 基础数据库中；
	 */
	void dataToDatabase();
	
	/**
	 * 记录交互日志到Derby嵌入式数据库中；
	 * @param logEntity
	 */
	void addLog(IbusLogerEntity logEntity);
}
