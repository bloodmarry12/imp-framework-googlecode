/*
 * 文件名：CacheRemoveAllCommand.java
 * 版权：  Copyright 2000-2001 Huawei Tech. Co. Ltd. All Rights Reserved.
 * 描述：〈描述〉
 * 修改人：ahli
 * 修改时间：Jun 7, 2009
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.huawei.imp.framework.cache.command;

import java.io.Serializable;
import java.util.Map;

import com.huawei.imp.framework.cache.container.CacheContainer;

/**
 * 删除命令
 * 负责从缓存中删除缓存对象
 * @author ahli
 * @version IMPV100R001DA0, Jun 7, 2009 
 * @see com.huawei.cms.utils.commcache.command.CacheCommand
 * @since CMS IMPV100R001DA0
 */

public class CacheRemoveAllCommand<T extends Object> implements CacheCommand
{

	private Class<T> clazz = null;
	private Map<Serializable, T> deletedMap = null;
	
	public CacheRemoveAllCommand(Class<T> clazz){
		this.clazz = clazz;
	}
	/* (non-Javadoc)
	 * @see com.huawei.cms.utils.commcache.command.CacheCommand#doCommand()
	 */
	@Override
	public void doCommand()
	{
		deletedMap = CacheContainer.loadMap(clazz);
		CacheContainer.removeAll(clazz);
	}

	/* (non-Javadoc)
	 * @see com.huawei.cms.utils.commcache.command.CacheCommand#undoCommand()
	 */
	@Override
	public void undoCommand()
	{
		CacheContainer.saveMap(clazz, deletedMap);
	}
}
