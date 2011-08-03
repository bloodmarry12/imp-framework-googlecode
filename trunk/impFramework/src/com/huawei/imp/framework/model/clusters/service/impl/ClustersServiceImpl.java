package com.huawei.imp.framework.model.clusters.service.impl;

import java.net.MalformedURLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.caucho.hessian.client.HessianProxyFactory;
import com.huawei.imp.framework.config.ConfigUtil;
import com.huawei.imp.framework.config.FileConfigUtil;
import com.huawei.imp.framework.jee.webserver.Environment;
import com.huawei.imp.framework.logger.LogFactory;
import com.huawei.imp.framework.logger.Logger;
import com.huawei.imp.framework.model.clusters.dao.ClustersDao;
import com.huawei.imp.framework.model.clusters.domain.SlaveDevice;
import com.huawei.imp.framework.model.clusters.intface.MasterRemoteService;
import com.huawei.imp.framework.model.clusters.intface.SlaveRemoteService;
import com.huawei.imp.framework.model.clusters.service.ClustersService;
import com.huawei.imp.framework.model.clusters.service.ClustersSupport;
import com.huawei.imp.framework.utils.BeanHolder;

@Service("clustersService")
public class ClustersServiceImpl implements ClustersService, MasterRemoteService, SlaveRemoteService {

	/**
	 * 日志对象
	 */
	private static final Logger log = LogFactory.getLogger(ClustersServiceImpl.class);
	
	/**
	 * hessian工厂
	 */
	private HessianProxyFactory factory = new HessianProxyFactory();
	
	/**
	 * 事务模板
	 */
	private TransactionTemplate transactionTemplate = null;
	
	@Autowired
	@Qualifier("transactionManager0")
	private PlatformTransactionManager transactionManager = null;
	
	@Autowired
	@Qualifier("environment")
	private Environment environment;
	
	/**
	 * 集群处理集合
	 */
	private Map<String, ClustersSupport> clustersSupportMap = new HashMap<String, ClustersSupport>();
	
	/**
	 * 集群数据访问层
	 */
	@Autowired
	@Qualifier("clustersDao")
	private ClustersDao clustersDao;
	
	@PostConstruct
	public void init(){
		this.transactionTemplate = new TransactionTemplate(transactionManager);

		// 设置最高事务隔离级别
		this.transactionTemplate
				.setIsolationLevel(TransactionDefinition.ISOLATION_READ_COMMITTED);

		// 设置超时10秒
		this.transactionTemplate.setTimeout(60); // 10 seconds
	}
	
	@Override
	public void flushSlaveDevice(final String type) {
		if(TYPE_VAL_MASTER.equals(ConfigUtil.getSysConfigValue(TYPE))){
			List<SlaveDevice>  list = clustersDao.queryForList(environment.getLocalWebURL());
			for (final SlaveDevice device : list)
			{
				// 针对每个客户端服务，打开新的线程，进行刷新操作
				new Thread(new FlushSlaveDevice(device.getUrl(), type, null)).start();
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see com.huawei.imp.framework.model.clusters.service.ClustersService#flushSlaveDevice(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void flushSlaveDevice(String url, String deviceID, String type)
	{
		if(TYPE_VAL_MASTER.equals(ConfigUtil.getSysConfigValue(TYPE))){
			List<SlaveDevice>  list = clustersDao.queryForList(environment.getLocalWebURL());
			for (final SlaveDevice device : list)
			{
				// 针对每个客户端服务，打开新的线程，进行刷新操作
				new Thread(new FlushSlaveDevice(device.getUrl(), type, deviceID)).start();
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see com.huawei.imp.framework.model.clusters.service.ClustersService#flushSlaveDevice(java.lang.String, java.lang.String)
	 */
	@Override
	public void flushSlaveDevice(final String url, final String type) {
		if(TYPE_VAL_MASTER.equals(ConfigUtil.getSysConfigValue(TYPE))){
			new Thread(new FlushSlaveDevice(url, type, null)).start();
		}
	}

	/* (non-Javadoc)
	 * @see com.huawei.imp.framework.model.clusters.service.ClustersService#registRemote()
	 */
	@Override
	public void registRemote() {
		final String masterUrl = ConfigUtil.getSysConfigValue(MASTER_URL);
			final String url = environment.getLocalWebURL(); // 本地项目发布地址
			final String[] masterUrlArrays = masterUrl.split(";");
			for (String str : masterUrlArrays)
			{
				final String _masterUrl = str + PATH_CLUSTERS_MASTER;
				MasterRemoteService masterRemoteService;
				try
				{
					masterRemoteService = (MasterRemoteService) factory.create(
							MasterRemoteService.class, _masterUrl);
					masterRemoteService.registSlaveDevice(url);
				}
				catch (MalformedURLException e)
				{
					log.error("regist error: " + e.toString());
				}
			}
	}

	@Override
	public void registClustersSupport(String type,
			ClustersSupport clustersSupport) {
		
		if(null == clustersSupport){
			return ;
		}
		// 同步注册方法
		synchronized (clustersSupportMap) {
			clustersSupportMap.put(type, clustersSupport);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.huawei.imp.framework.model.clusters.service.ClustersService#addSlaveDevice(com.huawei.imp.framework.model.clusters.domain.SlaveDevice)
	 */
	@Override
	@Transactional
	public void addSlaveDevice(SlaveDevice device) {
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("url", device.getUrl());
		parameter.put("localhost", environment.getLocalWebURL());
		SlaveDevice deviceIndb = clustersDao.select(SlaveDevice.class, parameter);
		if(null == deviceIndb){
			SlaveRemoteService slaveRemoteService;
			try {
				slaveRemoteService = (SlaveRemoteService) factory.create(SlaveRemoteService.class, device.getUrl() + PATH_CLUSTERS_SLAVE);
				slaveRemoteService.check();
				device.setLocalhost(environment.getLocalWebURL());
				device.setLastStatus("1");
				device.setLastActiveTime(new Date());
				clustersDao.insert(device);
			} catch (MalformedURLException e) {
				log.error("flash error: " + e.toString());
			}
		}else{
			checkSlaveDevice(device.getUrl());
		}
	}

	@Override
	@Transactional
	public void checkSlaveDevice(String url) {
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("url", url);
		parameter.put("localhost", environment.getLocalWebURL());
		SlaveDevice device = clustersDao.select(SlaveDevice.class, parameter);
		if(null != device){
			SlaveRemoteService slaveRemoteService;
			try {
				slaveRemoteService = (SlaveRemoteService) factory.create(SlaveRemoteService.class, device.getUrl() + PATH_CLUSTERS_SLAVE);
				slaveRemoteService.check();
				device.setLastActiveTime(new Date());
				clustersDao.update(device);
			} catch (MalformedURLException e) {
				log.error("flash error: " + e.toString());
			}
		}
	}

	@Override
	@Transactional
	public void deleteSlaveDevice(String url) {
		SlaveDevice sd = new SlaveDevice();
		sd.setUrl(url);
		sd.setLocalhost(environment.getLocalWebURL());
		clustersDao.delete(SlaveDevice.class, sd);
	}

	@Override
	public List<SlaveDevice> queryForAllSlaveDevice() {
		return clustersDao.queryForList(environment.getLocalWebURL());
	}

	class FlushSlaveDevice implements Runnable{

		/**
		 * 当前服务器地址
		 */
		private String url;
		
		/**
		 * 刷新的业务关键字
		 */
		private String key;
		
		/**
		 * 目标服务器设备ID
		 */
		private String deviceID;
		
		/**
		 * @param url
		 * @param key
		 * @param targetUrl
		 * @param deviceID
		 */
		public FlushSlaveDevice(String url, String key, String deviceID)
		{
			super();
			this.url = url;
			this.key = key;
			this.deviceID = deviceID;
		}

		/* (non-Javadoc)
		 * @see java.lang.Runnable#run()
		 */
		@Override
		public void run()
		{
			try {
				// 睡眠2秒后，对远程服务器发起调用。
				Thread.sleep(2000);
			} catch (InterruptedException e1) {
				// 忽略
			}
			
			final Map<String, Object> parameter = new HashMap<String, Object>();
			parameter.put("url", url);
			parameter.put("localhost", environment.getLocalWebURL());
			
			final ClustersDao _clusterDao = clustersDao;
			transactionTemplate.execute(new TransactionCallback(){
				@Override
				public Object doInTransaction(TransactionStatus arg0)
				{
					SlaveDevice device = _clusterDao.select(SlaveDevice.class, parameter);
					if(null != device){
						try{
							SlaveRemoteService slaveRemoteService = (SlaveRemoteService) factory.create(SlaveRemoteService.class, device.getUrl() + PATH_CLUSTERS_SLAVE);
							slaveRemoteService.flush(url, deviceID, key);
							device.setLastStatus(SlaveDevice.LAST_STATUS_SUCCESS);
							device.setLastActiveTime(new Date());
						}
						catch (Exception e)
						{
							log.error(BeanHolder.getMessage("framework.model.cluster.exception.flusherror", 
									new Object[]{url, key, e.toString()}));
							device.setLastStatus(SlaveDevice.LAST_STATUS_FAILS);
						}
						try{
							_clusterDao.update(device);
						}catch (Exception e) {
							log.error(BeanHolder.getMessage("framework.model.cluster.exception.flusherrorupdate", 
									new Object[]{url, key, e.toString()}));
							arg0.setRollbackOnly();
						}
					}
					return null;
				}
			});
		}
	}
	
	/*========================================
	 * MasterRemoteService接口实现方法
	 * =======================================
	 */
	
	/* (non-Javadoc)
	 * @see com.huawei.imp.framework.model.clusters.intface.MasterRemoteService#register(java.lang.String, java.lang.String)
	 */
	@Override
	@Transactional
	public void registSlaveDevice(String url) {
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("url", url);
		parameter.put("localhost", environment.getLocalWebURL());
		
		SlaveDevice device = clustersDao.select(SlaveDevice.class, parameter);
		if(null == device){
			device = new SlaveDevice();
			device.setUrl(url);
			device.setLastActiveTime(new Date());
			device.setLastStatus("1");
			device.setLocalhost(environment.getLocalWebURL());
			clustersDao.insert(device);
		}else{
			device.setLastActiveTime(new Date());
			clustersDao.update(device);
		}
	}
	
	
	/*========================================
	 * SlaveRemoteService接口实现方法
	 * =======================================
	 */
	/* (non-Javadoc)
	 * @see com.huawei.imp.framework.model.clusters.intface.SlaveRemoteService#flush(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void flush(String url, String deviceID, String actionKey)
	{
		// 如果请求过来，url地址不符，则丢弃
		if (null != url && !"".equals(url.trim())
				&& !url.equals(this.environment.getLocalWebURL()))
		{
			return;
		}
		else if(log.isDebugEnabled())
		{
			log.debug("url check -> ok");
		}

		// 如果请求过来，设备类型不符，则丢弃
		if (null != deviceID && !"".equals(deviceID.trim())
				&& !deviceID.equals(FileConfigUtil.getString("configuration.plateform.code")))
		{
			return;
		}

		ClustersSupport clustersSupport = clustersSupportMap.get(actionKey);
		if (null != clustersSupport)
		{
			clustersSupport.invokeByMaster();
		}
	}
	
	/* (non-Javadoc)
	 * @see com.huawei.imp.framework.model.clusters.intface.SlaveRemoteService#check()
	 */
	@Override
	public void check() {
		return;
	}
}
