/*
 * FileName: CacheCommand.java
 * Author:   ahli
 * createDate: 2009-6-7
 * Description: 
 * 
 */
package com.huawei.imp.framework.cache.command;

/**
 *  缓存操作命令
 *  该命令包括两个函数，一个是执行，一个是回退函数。当出现异常后，调用回退函数
 *  将被操作的值恢复到操作前的状态。
 * @author ahli
 * @version IMPV100R001DA0, Aug 10, 2009 
 * @since CMS IMPV100R001DA0
 */
public interface CacheCommand {

	/**
	 * 执行命令 
	 */
	void doCommand();
	
	/**
	 * 回退命令
	 */
	void undoCommand();
}
