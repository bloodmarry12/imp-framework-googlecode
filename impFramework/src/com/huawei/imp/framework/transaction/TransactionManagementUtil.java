/**
 * 
 */
package com.huawei.imp.framework.transaction;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * <p>
 * 事务管理工具类。在IOC容器中注册该类，可以通过该类的execute方法直接使用
 * 事务。
 * </p>
 * @see 
 * @author aohai.li
 * @version CMSV100R001DB0SP0, 2010-8-5
 * @since CMSV100R001DB0SP0
 */
public class TransactionManagementUtil {

	/**
	 * 事务模板
	 */
	private final TransactionTemplate transactionTemplate;
	
	/**
	 * 构造函数
	 * @param transactionManager
	 * @param timeout
	 */
	public TransactionManagementUtil(PlatformTransactionManager transactionManager, int timeout)
	{
		// 创建事务模板对象
		this.transactionTemplate = new TransactionTemplate(transactionManager);

		// 设置最高事务隔离级别
		this.transactionTemplate.setIsolationLevel(TransactionDefinition.ISOLATION_READ_COMMITTED);

		// 设置超时120秒
		this.transactionTemplate.setTimeout(timeout); // 90 seconds
	}
	
	/**
	 * 执行事务内的脚本
	 * @param tc
	 * @return
	 */
	public Object execute(TransactionCallback tc){
		return transactionTemplate.execute(tc);
	}
}
