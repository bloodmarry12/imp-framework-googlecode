package com.huawei.imp.framework.model.ibus.log.service.impl;

import java.util.Collection;
import java.util.LinkedList;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.huawei.imp.framework.jee.webserver.Environment;
import com.huawei.imp.framework.logger.LogFactory;
import com.huawei.imp.framework.logger.Logger;
import com.huawei.imp.framework.model.ibus.impl.IbusRemoteInvokerImpl;
import com.huawei.imp.framework.model.ibus.log.dao.IbusLogDAO;
import com.huawei.imp.framework.model.ibus.log.domain.IbusLogerEntity;
import com.huawei.imp.framework.model.ibus.log.service.IbusLogService;

/**
 * Ibus日志服务实现类
 * @author ahli
 * @version IMPV100R001DA0, Oct 12, 2009 
 * @see com.huawei.imp.framework.model.ibus.log.service.IbusLogService
 * @since CMS IMPV100R001DA0
 */
@Service("ibusLogService")
public class IbusLogServiceImpl implements IbusLogService
{
	
	/**
	 * 日志对象
	 */
	private static final Logger log = LogFactory.getLogger(IbusLogServiceImpl.class);

	/**
	 * 批量操作一次的记录数
	 */
	private static final int DEFAULT_BATCH_NUM = 2000;
	
	/**
	 * 缓存容器
	 */
	private final IbusLogerEntitySafeCache cache = new IbusLogerEntitySafeCache();
	
	/**
	 * 当前时间
	 */
	long lastTime = System.currentTimeMillis();
	
	@Autowired
	@Qualifier("ibusLogDAOImpl")
	private IbusLogDAO ibusLogDAO;
	
	@Autowired
	@Qualifier("environment")
	private Environment environment;
	
	/**
	 * 事务模板
	 */
	private TransactionTemplate transactionTemplate;
	
	/**
	 * 构造函数
	 * @param platformTransactionManager
	 */
	@Autowired
	public IbusLogServiceImpl(@Qualifier("transactionManager0")PlatformTransactionManager platformTransactionManager){
		super();
		this.transactionTemplate =  new TransactionTemplate(platformTransactionManager);
		this.transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		this.transactionTemplate.setIsolationLevel(TransactionDefinition.ISOLATION_READ_COMMITTED);
		this.transactionTemplate.setTimeout(30);
	}
	
	@PostConstruct
	public void init(){
		// 将自己注入到远程调用
		IbusRemoteInvokerImpl iri = (IbusRemoteInvokerImpl)IbusRemoteInvokerImpl.getIbusRemoteInvoker();
		iri.setIbusLogService(this);
	}
	
	@PreDestroy
	public void destroy(){
		dataToDatabase();
	}
	
	@Override
	public void addLog(IbusLogerEntity logEntity)
	{
		// 设置服务器名称，当多服务器并发处理时，可以区分日志来自哪太服务器；
		logEntity.setServer(environment.getLocalWebURL());
		cache.save(logEntity);
	}

	void batchLogerPersistenceOperator()
	{
		LinkedList<IbusLogerEntity> tempList = cache.get();
		
		if(tempList.size() == 0)
		{
			return;
		}else{
			Collection<IbusLogerEntity> batchList = new LinkedList<IbusLogerEntity>();
			for(IbusLogerEntity entity : tempList){
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
	 * @param batchList
	 */
	private void saveAll(final Collection<IbusLogerEntity> batchList)
	{
		transactionTemplate.execute(new TransactionCallback(){
			@Override
			public Object doInTransaction(TransactionStatus arg0)
			{
				try{
					ibusLogDAO.batchInsert(batchList);
				}catch(Exception e){
					log.error(e.toString());
				}
				return null;
			}
		});
	}
	
	public void dataToDatabase(){
		dataToDatabase(0);
	}
	
	@Override
	public void dataToDatabase(int number){
		batchLogerPersistenceOperator();
	}
}

/**
 * 线程安全的Ibus数据交互日志缓存容器
 * 
 * @author ahli
 */
class IbusLogerEntitySafeCache
{

	/**
	 * 日志对象
	 */
	private static final Logger log = LogFactory
			.getLogger(IbusLogerEntitySafeCache.class);

	/**
	 * 缓存最大容量
	 */
	private static final int MAX_SIZE = 50000;

	/**
	 * 缓存容器
	 */
	private final LinkedList<IbusLogerEntity> list = new LinkedList<IbusLogerEntity>();

	/**
	 * NULL容器
	 */
	private final LinkedList<IbusLogerEntity> NULL_LIST = new LinkedList<IbusLogerEntity>();

	/**
	 * 保存日志对象
	 * 
	 * @param entity
	 *            日志记录对象
	 */
	public synchronized void save(IbusLogerEntity entity)
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
	public synchronized LinkedList<IbusLogerEntity> get()
	{
		if (list.size() > 0)
		{
			LinkedList<IbusLogerEntity> tmp = (LinkedList<IbusLogerEntity>) list.clone();
			list.clear();
			return tmp;
		}
		return NULL_LIST;
	}
}
