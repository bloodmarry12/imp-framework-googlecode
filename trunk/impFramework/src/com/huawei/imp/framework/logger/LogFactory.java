package com.huawei.imp.framework.logger;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.log4j.Level;

import com.huawei.imp.framework.logger.impl.AppLogImpl;

/**
 * 日志工厂方法，用于缓存日志对象。
 * @author ahli
 * @date 2009-8-29
 */
public class LogFactory
{
	private final static Map<String, AppLogImpl> appLogMap = new ConcurrentHashMap<String, AppLogImpl>();
	
	private static Level level = org.apache.log4j.Logger.getLogger(LogFactory.class).getLevel();
	
	/**
	 * 读写锁
	 */
	private static ReadWriteLock lock = new ReentrantReadWriteLock();
	
	private LogFactory(){}
	
	public static com.huawei.imp.framework.logger.Logger getLogger(Class<? extends Object> clazz)
	{
		String moduleName = clazz.getName();
		Logger log = loadAppLog(moduleName);
		
		if(null != log){
			return log;
		}else{
			createAppLog(moduleName);
			return loadAppLog(moduleName);
		}
	}

	public static com.huawei.imp.framework.logger.Logger getLoggerByName(String moduleName)
	{
		Logger log = loadAppLog(moduleName);
		
		if(null != log){
			return log;
		}else{
			createAppLog(moduleName);
			return loadAppLog(moduleName);
		}
	}
	
	private static Logger loadAppLog(String arg0){
		Lock readLock = lock.readLock();
		readLock.lock();
		try{
			return appLogMap.get(arg0);
		}finally{
			readLock.unlock();
		}
	}
	
	private static void createAppLog(String arg0){
		Lock writeLock = lock.writeLock();
		// 加上写锁
		writeLock.lock();
		try{
			// 同步块内部再进行一次非空校验，避免初始化多个LogImpl实例
			if(appLogMap.get(arg0) == null){
				appLogMap.put(arg0, new AppLogImpl(arg0, level));
			}
		}finally{
			writeLock.unlock();
		}
	}
	
	public static Level getLevel() {
		return LogFactory.level;
	}

	public static void setLevel(Level level) {
		Lock writeLock = lock.writeLock();
		// 加上写锁
		writeLock.lock();
		try{
			LogFactory.level = level;
			Set<Entry<String, AppLogImpl>> set = appLogMap.entrySet();
			for (Entry<String, AppLogImpl> entry : set) {
				entry.getValue().setLevel(level);
			}
		}finally{
			writeLock.unlock();
		}
	}
}
