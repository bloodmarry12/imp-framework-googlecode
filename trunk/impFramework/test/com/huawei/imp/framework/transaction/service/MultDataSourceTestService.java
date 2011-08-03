package com.huawei.imp.framework.transaction.service;

/**
 * 由于SpringTest暂时不支持多数据源测试,所以这里只能将测试移到
 * 容器发布到web环境下面做 。
 * @author ahli
 *
 */
public interface MultDataSourceTestService {

	/**
	 * 测试向量个数据源写数据
	 */
	public void testAddMultDatasourceData();
	
	/**
	 * 测试向两个数据源写数据，后面一个异常；导致整个事务回滚
	 */
	public void testAddMultDatasourceDataWithExp();
}
