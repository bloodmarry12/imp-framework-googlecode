package com.huawei.imp.framework.model.ftp;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;

import org.apache.commons.pool.ObjectPool;
import org.apache.commons.pool.impl.GenericObjectPool;
import org.apache.commons.pool.impl.GenericObjectPoolFactory;

import com.huawei.imp.framework.logger.LogFactory;
import com.huawei.imp.framework.logger.Logger;
import com.huawei.imp.framework.model.ftp.domain.FtpConfig;
import com.huawei.imp.framework.model.ftp.exception.FtpException;
import com.huawei.imp.framework.model.ftp.impl.FtpConnectionImpl;

/**
 * FTP连接池对象
 * 维护一个以fpt别名为key值的映射容器，用于保存所有ftp的连接池工厂
 * @author ahli
 * @date 2009-8-18
 */
public class FtpConnectionPools {
	
	/**
	 * 日志对象
	 */
	private static final Logger log = LogFactory.getLogger(FtpConnectionPools.class);
	
	/**
	 * 对象池映射
	 */
	private static Map<String, ObjectPool> objectPoolMap = new ConcurrentHashMap<String, ObjectPool>();
	
	/**
	 * fpt链接对象工厂映射
	 */
	private static Map<String, FtpObjectFactory> ftpObjectFactoryMap = new ConcurrentHashMap<String, FtpObjectFactory>();
	
	/**
	 * 是否初始化标签
	 */
	private static boolean initialed = false;

	/**
	 * 私有构造函数，防止该类被实例化
	 */
	private FtpConnectionPools(){}
	
	private static final int DEFAULT_MAXACTIVE = 40;// 最大活动对象数
	private static final int DEFAULT_MAXWAIT = 1000 * 60; //  最大等待时间1分钟
	
	/**
	 * 初始化FTP连接池
	 */
	@PostConstruct
	public synchronized static void initFtpPool(List<FtpConfig> list)
	{
		log.info("initialize ftp connection pools -- start");
		GenericObjectPoolFactory factory;

		if(log.isDebugEnabled()){
			log.debug("ftp connection config size:" + list.size());
		}
		// 一次为每一个配置创建相应的ftp链接对象缓冲池
		for (FtpConfig config : list)
		{
			if(log.isDebugEnabled()){
				log.debug("ftp config:" + config.toString());
			}
			
			FtpObjectFactory ftpObjectFactory = new FtpObjectFactory(config);
			ftpObjectFactoryMap.put(config.getFtpAlias(), ftpObjectFactory);
			
			factory = new GenericObjectPoolFactory(ftpObjectFactory, DEFAULT_MAXACTIVE,
					GenericObjectPool.WHEN_EXHAUSTED_BLOCK, DEFAULT_MAXWAIT,
					true, false);
			objectPoolMap.put(config.getFtpAlias(), factory.createPool());
		}
		
		initialed = true;
		log.info("initialize ftp connection pools -- complete");
	}

	public static void resetFtpObjectFactory(FtpConfig config ){
		FtpObjectFactory ftpObjectFactory = ftpObjectFactoryMap.get(config.getFtpAlias());
		if(null != ftpObjectFactory){
			ftpObjectFactoryMap.get(config.getFtpAlias()).setConfig(config);
		}
	}
	
	/**
	 * 获取一个Ftp控制器对象
	 * @param Alias
	 * @return
	 * @throws Exception 
	 */
	public static FtpConnection getFtpController(String alias) throws Exception{
		if(!initialed){
			throw new FtpException("ftp 连接池尚未未初始化。");
		}
		ObjectPool pool = objectPoolMap.get(alias);
		
		if(null == pool){
			throw new FtpException("连接对象为被初始化，系统未初始化别名为:" + alias + "");
		}
		if(log.isDebugEnabled()){
			log.debug("getFtpController(" + alias + ")");
		}
		return (FtpConnection)pool.borrowObject();
	}
	
	/**
	 * 将对象归还给连接池
	 * @param fc
	 */
	public static void returnObject(FtpConnectionImpl fc){
		if(log.isDebugEnabled()){
			log.debug("returnObject(" + fc + ")");
		}
		
		try {
			objectPoolMap.get(fc.getFtpAlias()).returnObject(fc);
		} catch (Exception e) {
			if(log.isDebugEnabled()){
				e.printStackTrace();
			}
		}
	}

	public static Map<String, ObjectPool> getObjectPoolMap()
	{
		return objectPoolMap;
	}

	public static Map<String, FtpObjectFactory> getFtpObjectFactoryMap()
	{
		return ftpObjectFactoryMap;
	}
}