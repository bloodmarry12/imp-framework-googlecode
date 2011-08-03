/*
 * FileName: CacheSaveCommand.java
 * Author:   ahli
 * createDate: 2009-6-7
 * Description: 
 * 
 */
package com.huawei.imp.framework.cache.command.impl;

import java.io.Serializable;

import com.huawei.imp.framework.cache.command.CacheCommand;
import com.huawei.imp.framework.cache.container.CacheContainer;

/**
 * 保存操作命令
 * @author ahli
 * @version IMPV100R001DA0, Aug 10, 2009 
 * @see com.huawei.cms.utils.commcache.command.CacheCommand
 * @since CMS IMPV100R001DA0
 */
public class CacheSaveCommand implements CacheCommand {

	/**
	 * 
	 */
	private Object obj = null; 
	
	/**
	 * 
	 */
	private Serializable id = 1L;
	
	/**
	 * @param obj
	 * @param id
	 */
	public CacheSaveCommand(Object obj, Serializable id){
		this.obj = obj;
		this.id = id;
	}
	
	/* (non-Javadoc)
	 * @see ahli.ahcacheModel.command.CacheCommand#doCommand()
	 */
	@Override
	public void doCommand() {
		CacheContainer.save(obj, id);
	}
	
	/* (non-Javadoc)
	 * @see ahli.ahcacheModel.command.CacheCommand#undoCommand()
	 */
	@Override
	public void undoCommand() {
		CacheContainer.remove(obj.getClass(), id);
	}
}
