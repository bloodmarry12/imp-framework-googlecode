/**
 * 
 */
package com.huawei.imp.framework.model.fileupload.service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author ahli
 * @version IMPV100R001DA0, 2009-12-10 
 * @see [相关类/方法]
 * @since CMS IMPV100R001DA0
 */

public class ProgressMap
{

	/**
	 * 由于各个平台都需要读写配置信息:这里用到的读写锁
	 */
	private static final ReadWriteLock lock = new ReentrantReadWriteLock();
	
	private static final Lock readLock = lock.readLock();
	private static final Lock writeLock = lock.writeLock();
	
	private final Map<String, Integer> map = new HashMap<String, Integer>();
	
	
	public Integer get(String uuid){
		readLock.lock();
		try{
			return map.get(uuid);
		}finally{
			readLock.unlock();
		}
	}
	
	/**
	 * @param uuid
	 * @param progress
	 * @return
	 */
	public boolean set(String uuid, Integer progress){
		writeLock.lock();
		try{
			Integer cur = map.get(uuid);
			if (null != cur && cur < 0)
			{
				return false;
			}
			else
			{
				map.put(uuid, progress);
				return true;
			}
		}finally{
			writeLock.unlock();
		}
	}
	
	public Integer remove(String uuid)
	{
		writeLock.lock();
		try
		{
			return map.remove(uuid);
		}
		finally
		{
			writeLock.unlock();
		}
	}
	
	public void cancel(String uuid){
		writeLock.lock();
		try
		{
			map.put(uuid, -1);
		}
		finally
		{
			writeLock.unlock();
		}
	}
}
