package com.huawei.imp.framework.config.service.impl;

import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huawei.imp.framework.cache.CacheSession;
import com.huawei.imp.framework.cache.CacheSessionFactory;
import com.huawei.imp.framework.cache.aspectj.annotation.CacheSessionTrans;
import com.huawei.imp.framework.common.observer.Observer;
import com.huawei.imp.framework.common.observer.ObserverAware;
import com.huawei.imp.framework.common.observer.ObserverUtil;
import com.huawei.imp.framework.config.FileConfigUtil;
import com.huawei.imp.framework.config.dao.SysConfigDAO;
import com.huawei.imp.framework.config.domain.SysConfig;
import com.huawei.imp.framework.config.service.SysConfigService;
import com.huawei.imp.framework.jee.webserver.Environment;
import com.huawei.imp.framework.utils.Constant;

/**
 * Description:
 * 系统配置服务接口实现
 * @author ahli
 * Apr 21, 2009
 * 
 */
@Service("sysConfigService")
public class SysConfigServiceImpl implements SysConfigService, ObserverAware<String> {

	/**
	 * 由于各个平台都需要读写配置信息:这里用到的读写锁
	 */
	private static final ReadWriteLock lock = new ReentrantReadWriteLock();
	
	private static final Lock readLock = lock.readLock();
	private static final Lock writeLock = lock.writeLock();
	
	/**
	 * 日志对象
	 */
	private static final Logger log = Logger.getLogger(SysConfigServiceImpl.class);
	
	/**
	 * 静态构造器
	 */
	private static final SysConfigComparator comp  = new SysConfigComparator();
	
	private ObserverUtil<String> observerUtil = new ObserverUtil<String>();
	
	@Autowired
	@Qualifier("environment")
	private Environment environment;
	
	@Autowired
	@Qualifier("sysCofinDAO")
	private SysConfigDAO sysConfigDAO;
	
	/**
	 * 初始化缓存
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	@PostConstruct
	public void initCache(){
		writeLock.lock();
		try
		{
			String platformCode = FileConfigUtil.getString("configuration.plateform.code");
			
			CacheSession session = CacheSessionFactory.getCacheSession(true);
			session.removeAll(SysConfig.class);
			Map<String,SysConfig> mapAllConfig = new HashMap<String,SysConfig>(0);
			
			List<SysConfig> listGlobalConfig = sysConfigDAO.selectGlobalConfig(SysConfig.class);//获得全局配置项
			List<SysConfig> listPlatConfig = sysConfigDAO.selectPlatConfigByCode(SysConfig.class, platformCode);//获得平台配置项
			List<SysConfig> listServerConfig = sysConfigDAO.selectServerConfigByAddress(SysConfig.class, environment.getLocalWebURL());//获得服务器配置项
			
			//加载全局配置项
			for (SysConfig globalConfig : listGlobalConfig)
			{
				mapAllConfig.put(globalConfig.getKey(), globalConfig);
			}
			//加载平台配置项
			for (SysConfig platConfig : listPlatConfig)
			{
				mapAllConfig.put(platConfig.getKey(), platConfig);
			}
			//加载服务器配置项
			for (SysConfig serverConfig : listServerConfig)
			{
				mapAllConfig.put(serverConfig.getKey(), serverConfig);
			}
			
			
			if(log.isDebugEnabled()){
				log.debug("加载系统配置项:" + mapAllConfig.size() +  "条配置项");
			}
			
			if(log.isDebugEnabled()){
				StringBuffer sb = new StringBuffer(2000);
				sb.append("加载配置信息：");
				sb.append(Constant.LINE_SEPARATOR);
				for (Iterator iterator = mapAllConfig.entrySet().iterator(); iterator.hasNext();)
				{
					Map.Entry<String,SysConfig> entry= (Entry<String, SysConfig>) iterator.next();
					SysConfig sysConfig = entry.getValue();
					sb.append("--" + sysConfig);
					sb.append(Constant.LINE_SEPARATOR);
				}
				log.debug(sb.toString());
			}
			
			for (Iterator iterator = mapAllConfig.entrySet().iterator(); iterator.hasNext();)
			{
				Map.Entry<String,SysConfig> entry= (Entry<String, SysConfig>) iterator.next();
				SysConfig sysConfig = entry.getValue();
				session.save(sysConfig, sysConfig.getKey());
			}
			
			if(log.isDebugEnabled()){
				StringBuffer sb = new StringBuffer(2000);
				List<SysConfig> sysList = session.find(SysConfig.class);
				sb.append("测试刷新后的缓存数据:");
				for (SysConfig config : sysList)
				{
					sb.append("--" + config);
					sb.append(Constant.LINE_SEPARATOR);
				}
				log.debug(sb);
			}
			
		}
		finally
		{
			writeLock.unlock();
		}
	}
	
	/* (non-Javadoc)
	 * @see com.huawei.cms.common.sysconfig.service.SysConfigService#findSysConfig()
	 */
	@Override
	@Transactional
	public List<SysConfig> findSysConfig() {
		readLock.lock();
		try{
			CacheSession session = CacheSessionFactory.getCacheSession(true);
			
			List<SysConfig> list = session.find(SysConfig.class);
			//如果缓存内容不存在，则直接加载数据库配置
			if(null == list || list.size() == 0){
				log.debug("sysConfig cache is emtpy... start reload");
//				initCache();
			}
			//重新加载缓存数据
			list = session.find(SysConfig.class);
			
			//对list进行排序
			Collections.sort(list, comp);
			return list;
			
		}finally{
			readLock.unlock();
		}
	}

	/* (non-Javadoc)
	 * @see com.huawei.cms.common.sysconfig.service.SysConfigService#flushConfigCache()
	 */
	@Override
	@Transactional
	public void flushConfigCache() {
		log.debug("flushConfigCache...");
		initCache();
	}

	/* (non-Javadoc)
	 * @see com.huawei.cms.common.sysconfig.service.SysConfigService#loadSysConfig(java.lang.String)
	 */
	@Override
	@Transactional
	public SysConfig loadSysConfig(String key) {
		readLock.lock();
		SysConfig none = null;
		try
		{
			CacheSession session = CacheSessionFactory.getCacheSession(true);
			List<SysConfig> list = session.find(SysConfig.class);
			if(null == list || list.size() == 0)
			{
				log.debug("sysConfig cache is emtpy... start reload");
//				initCache();
			}
			SysConfig sysConfig = session.load(SysConfig.class, key);
			if(null != sysConfig)
			{
				try
				{
					return sysConfig.clone();
				}
				catch (CloneNotSupportedException e)
				{
					log.debug("sysConfig cache is error : "+e.getMessage());
				}
			}
			else
			{
				none = new SysConfig();
				log.debug("sysConfig is emtpy where property key's value is null,return a null object");
			}
		}
		finally
		{
			readLock.unlock();
		}
		return none;
	}

	/* (non-Javadoc)
	 * @see com.huawei.cms.common.sysconfig.service.SysConfigService#updateSysConfig(com.huawei.cms.common.sysconfig.domain.SysConfig)
	 */
	@Transactional
	@CacheSessionTrans
	@Override
	public void updateSysConfig(SysConfig config) {
		writeLock.lock();
		try
		{
			CacheSession session = CacheSessionFactory.getCacheSession(true);
			log.debug("start update db config");
			//修改数据库配置
			sysConfigDAO.update(config);
			//修改缓存数据
			log.debug("start update cache config");
			SysConfig proxy = session.loadUpdateProxy(SysConfig.class, config.getKey());
			proxy.setDescription(config.getDescription());
			proxy.setName(config.getName());
			proxy.setValue(config.getValue());
			proxy.setScope(config.getScope());
			proxy.setAddress(config.getAddress());
			
		}
		finally
		{
			writeLock.unlock();
		}
		
		// 通知观察者
		observerUtil.notifyObserver(config.getKey());
	}
	
	/**
	 * 系统配置信息排序比较器
	 * @author ahli
	 * @version IMPV100R001DA0, Apr 22, 2009
	 * @see java.util.Comparator<SysConfig>
	 * @since CMS IMPV100R001DA0
	 */
	private static class SysConfigComparator implements Comparator<SysConfig>,Serializable {
		/**
		 * 序列号
		 */
		private static final long serialVersionUID = 6797597382448529789L;

		@Override
		public int compare(SysConfig o1, SysConfig o2) {
			return o1.getName().compareTo(o2.getName());
		}
	}

	/* (non-Javadoc)
	 * @see com.huawei.imp.framework.common.observer.ObserverAware#registBusinessObserver(com.huawei.imp.framework.common.observer.Observer)
	 */
	@Override
	public void registObserver(Observer<String> observer)
	{
		observerUtil.registObserver(observer);
	}

//	@Override
//	@Transactional
//	@CacheSessionTrans
//	public void saveSysConfig(SysConfig config) {
//		CacheSession session = CacheSessionFactory.getCacheSession();
//		log.debug("start add db config");
//		//新增数据库配置
//		sysConfigDAO.insert(config);
//		session.save(config, config.getKey());
//		
//	}
}
