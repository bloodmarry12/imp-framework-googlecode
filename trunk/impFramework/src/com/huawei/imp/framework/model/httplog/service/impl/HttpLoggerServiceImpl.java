/*
 * 文件名：HttpLoggerServiceImpl.java
 * 版权：  Copyright 2000-2009 Huawei Tech. Co. Ltd. All Rights Reserved.
 * 描述：〈描述〉
 * 修改人：ahli
 * 修改时间：Oct 28, 2009
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.huawei.imp.framework.model.httplog.service.impl;

import java.util.Collection;
import java.util.LinkedList;

import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.huawei.imp.framework.jee.webserver.Environment;
import com.huawei.imp.framework.logger.LogFactory;
import com.huawei.imp.framework.logger.Logger;
import com.huawei.imp.framework.model.httplog.dao.HttpLoggerDAO;
import com.huawei.imp.framework.model.httplog.domain.HttpLoggerEntity;
import com.huawei.imp.framework.model.httplog.service.HttpLoggerService;

/**
 * HTTP请求日志记录
 * 〈功能详细描述〉
 * @author ahli
 * @version IMPV100R001DA0, Oct 28, 2009 
 * @see [相关类/方法]
 * @since CMS IMPV100R001DA0
 */
@Service("httpLoggerService")
public class HttpLoggerServiceImpl implements HttpLoggerService
{

	/**
	 * 日志对象
	 */
	private static final Logger log = LogFactory.getLogger(HttpLoggerServiceImpl.class);
	
	/**
	 * 批量操作一次的记录数
	 */
	private static final int DEFAULT_BATCH_NUM = 2000;

	@Autowired
	@Qualifier("httpLoggerOracleDAO")
	private HttpLoggerDAO httpLoggerOracleDAO;
	
	/**
	 * 事务模板
	 */
	private TransactionTemplate transactionTemplate;
	
	@Autowired
	@Qualifier("environment")
	private Environment environment;
	
	/**
	 * 缓存容器
	 */
	private final HTTPLogerEntitySafeCache cache = new HTTPLogerEntitySafeCache();
	
	/**
	 * 默认构造函数
	 * @param platformTransactionManager    平台事务管理
	 */
	@Autowired
	public HttpLoggerServiceImpl(@Qualifier("transactionManager0")PlatformTransactionManager platformTransactionManager){
		super();
		this.transactionTemplate =  new TransactionTemplate(platformTransactionManager);
	}
	
	@PreDestroy
	public void destroy(){
		batchLogerPersistenceOperator();
	}
	
	/* (non-Javadoc)
	 * @see com.huawei.imp.framework.model.httplog.service.HttpLoggerService#log(com.huawei.imp.framework.model.httplog.domain.HttpLoggerEntity)
	 */
	@Override
	public void log(HttpLoggerEntity entity)
	{
		// 设置服务器名称，当多服务器并发处理时，可以区分日志来自哪太服务器；
		entity.setServer(environment.getLocalWebURL());
		cache.save(entity);
	}

	/* (non-Javadoc)
	 * @see com.huawei.imp.framework.model.httplog.service.HttpLoggerService#persistenceHttpLoggerEntity()
	 */
	@Override
	public void persistenceHttpLoggerEntity(int number){
		batchLogerPersistenceOperator();
	}
	
	void batchLogerPersistenceOperator()
	{
		LinkedList<HttpLoggerEntity> tempList = cache.get();
		
		if(tempList.size() == 0)
		{
			return;
		}else{
			Collection<HttpLoggerEntity> batchList = new LinkedList<HttpLoggerEntity>();
			for(HttpLoggerEntity entity : tempList){
				batchList.add(entity);
				// 如果批量容器的大小达到了上线，则进行一次入库操作
				if(batchList.size() > DEFAULT_BATCH_NUM){
					saveAll(batchList);
					batchList.clear();
				}
			}
			
			if(!batchList.isEmpty()){
				saveAll(batchList);
			}
		}
	}
	
	/**
	 * 批量保存日志对象
	 * @param batchList    日志集合
	 */
	private void saveAll(final Collection<HttpLoggerEntity> batchList)
	{
		transactionTemplate.execute(new TransactionCallback(){
			@Override
			public Object doInTransaction(TransactionStatus arg0)
			{
				try{
					httpLoggerOracleDAO.batchInsert(batchList);
				}catch(Exception e){
					log.error(e.toString());
				}
				return null;
			}
		});
	}
}
/**
 * 线程安全的Ibus数据交互日志缓存容器
 * 
 * @author ahli
 */
class HTTPLogerEntitySafeCache
{

	/**
	 * 日志对象
	 */
	private static final Logger log = LogFactory
			.getLogger(HTTPLogerEntitySafeCache.class);

	/**
	 * 缓存最大容量
	 */
	private static final int MAX_SIZE = 50000;

	/**
	 * 缓存容器
	 */
	private final LinkedList<HttpLoggerEntity> list = new LinkedList<HttpLoggerEntity>();

	/**
	 * NULL容器
	 */
	private final LinkedList<HttpLoggerEntity> NULL_LIST = new LinkedList<HttpLoggerEntity>();

	/**
	 * 保存日志对象
	 * 
	 * @param entity
	 *            日志记录对象
	 */
	public synchronized void save(HttpLoggerEntity entity)
	{
		if (list.size() > MAX_SIZE)
		{
			log.error("can not save ibus logger into cache , list.size() > "
					+ MAX_SIZE);
			return;
		}
		list.add(entity);
	}

	/**
	 * 获取当前日志的对象；如果当前没有日志记录，返回的是一个空的LinkedList对象；
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public synchronized LinkedList<HttpLoggerEntity> get()
	{
		if (list.size() > 0)
		{
			LinkedList<HttpLoggerEntity> tmp = (LinkedList<HttpLoggerEntity>) list.clone();
			list.clear();
			return tmp;
		}
		return NULL_LIST;
	}
}
