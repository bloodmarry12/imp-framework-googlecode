/*
 * FileName: CacheRemoveCommand.java
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
 * 缓存对象删除操作
 * @author ahli
 * @version IMPV100R001DA0, Aug 10, 2009 
 * @see com.huawei.cms.utils.commcache.command.CacheCommand
 * @since CMS IMPV100R001DA0
 */
public class CacheRemoveCommand implements CacheCommand {

	/**
	 * 缓存对象类型
	 */
	private Class<? extends Object> clazz = null;
	/**
	 * 默认缓存对象ID
	 */
	private Serializable id = 1L;
	/**
	 * 被删除的缓存对象
	 */
	private Object removedObject = null;
	
	public CacheRemoveCommand(Class<? extends Object> clazz, Serializable id){
		this.clazz = clazz;
		this.id = id;
	}
	
	/* (non-Javadoc)
	 * @see ahli.ahcacheModel.command.CacheCommand#doCommand()
	 */
	@Override
	public void doCommand() {
		removedObject = CacheContainer.load(clazz, id);
		CacheContainer.remove(clazz, id);
	}

	/* (non-Javadoc)
	 * @see ahli.ahcacheModel.command.CacheCommand#undoCommand()
	 */
	@Override
	public void undoCommand() {
		CacheContainer.save(removedObject, id);
	}
}
