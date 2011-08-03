package com.huawei.imp.framework.common.dao.impl;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.oproject.framework.orm.ibatis.CustomizedSQLExecutor;
import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.orm.ibatis.SqlMapClientTemplate;

import com.huawei.imp.framework.common.dao.IbatisSupportDAO;
import com.huawei.imp.framework.common.domain.Namingspace;
import com.huawei.imp.framework.utils.PageResult;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapExecutor;

/**
 * 抽象了Ibatis基础数据访问层实现类，所有基于Ibatis事先的数据访问都应该继承自该类。
 * 实现了基于命名空间适配功能
 * @author ahli
 * @date 2009-8-15
 */
public abstract class MDSSupportIbatisCommonDAOImpl implements IbatisSupportDAO {

	/**
	 * 日志对象
	 */
	private static final Logger log = Logger.getLogger(MDSSupportIbatisCommonDAOImpl.class);
	
	/**
	 * 命名映射
	 */
	public static final Map<String, String> namingMap = Collections.synchronizedMap(new HashMap<String, String>());
	
	/**
	 * 命名空间与SQL语句分隔符号
	 */
	public static final String _SQL_SEP = ".";
	
	/**
	 * 通用INSERT语句
	 */
	public static final String _SQL_INSERT = "insert";
	
	/**
	 * 通用DELETE语句
	 */
	public static final String _SQL_DELETE = "delete";
	
	/**
	 * 通用UPDATE语句
	 */
	public static final String _SQL_UPDATE = "update";
	
	/**
	 * 通用SELECT语句
	 */
	public static final String _SQL_SELECT = "select";
	
	/**
	 * 通用全表查询 语句
	 */
	public static final String _SQL_SELECTALL = "selectAll";
	
	/**
	 * 
	 */
	public static final String _SQL_SELECTSEQUENCE = "sequence";
	
	/**
	 * 自动适配查询对象集合语句
	 */
	static String NAMINGSQL_QUERY_LIST = "queryForList";
	
	/**
	 * 自动适配查询分页对象集合语句
	 */
	static String NAMINGSQL_QUERY_PAGELIST = "queryForPageList";
	
	/**
	 * 自动适配查询分页对象集合长度语句
	 */
	static String NAMINGSQL_QUERY_PAGELIST_COUNT = "queryForPageListCount";
	
	
	/**
	 * Ibaits的sql客户端对象
	 */
	protected SqlMapClient sqlMapClient;

	/**
	 * Ibaits的sql客户端对象(spring包装)
	 */
	protected SqlMapClientTemplate sqlMapClientTemplate;
	
	/**
	 * 默认构造函数，支持多数据源。不同数据源对应了不同的sqlMapClient对象，
	 * 由子类负责具体注入的sqlMapClient\sqlMapClientTemplate实例；
	 * @param sqlMapClient
	 * @param sqlMapClientTemplate
	 */
	public MDSSupportIbatisCommonDAOImpl(SqlMapClientTemplate sqlMapClientTemplate){
		this.sqlMapClientTemplate = sqlMapClientTemplate;
		this.sqlMapClient = sqlMapClientTemplate.getSqlMapClient();
	}
	
	@Override
	public void delete(Class<? extends Object> clazz, Object parameter) {
		sqlMapClientTemplate.delete(warpSqlstatement(clazz, _SQL_DELETE), parameter, 1);
	}

	@Override
	public Object insert(Object obj) {
		return sqlMapClientTemplate.insert(warpSqlstatement(obj.getClass(), _SQL_INSERT), obj);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T extends Object> T select(Class<T> clazz, Object parameter) {
		return (T)sqlMapClientTemplate.queryForObject(warpSqlstatement(clazz, _SQL_SELECT), parameter);
	}

	@Override
	public void update(Object obj) {
		sqlMapClientTemplate.update(warpSqlstatement(obj.getClass(), _SQL_UPDATE), obj, 1);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> selectAll(Class<T> clazz) {
		return (List<T>)sqlMapClientTemplate.queryForList(warpSqlstatement(clazz, _SQL_SELECTALL));
	}
	
	@Override
	public <T> Long selectSequence(Class<T> clazz) {
		return (Long)sqlMapClientTemplate.queryForObject(warpSqlstatement(clazz, _SQL_SELECTSEQUENCE));
	}

	protected String warpSqlstatement(Class<? extends Object> clazz, String stmt){
		final String namespace = this.getNamesapce(clazz);
		return namespace + _SQL_SEP + stmt;
	}
	
	/**
	 * 获取命名空间名称
	 * @param object    对象
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected String getNamesapce(Object object){
		final Class clazz = object.getClass();
		return getNamesapce(clazz);
	}
	
	/**
	 * 获取命名空间名称
	 * @param clazz    对象类型
	 * @return
	 */
	protected String getNamesapce(Class<? extends Object> clazz){
		String namespace = null;
		final String clazzName = clazz.getName();
		log.debug("NamingSQLAdapte.className:" + clazzName);
		
		// 从缓存中获取
		namespace = namingMap.get(clazzName);
		
		// 如果缓存中还没有保存对应的SQL命名空间名称，则初始化并且加载
		if(null == namespace){
			Namingspace _namespace = (Namingspace)clazz.getAnnotation(Namingspace.class);
			namespace = _namespace.value();
			if(null == namespace || "".equals(namespace.trim())){
				throw new RuntimeException ("namespace is empty.");
			}
			namingMap.put(clazzName, namespace);
		}
		return namespace;
	}
	
	/**
	 * 分页查询方法
	 * 
	 * @param countName
	 *            统计总记录数语句别名
	 * @param queryName
	 *            查询结果集合的语句别名
	 * @param obj
	 *            查询的参数
	 * @param pageSize
	 *            每页显示记录数
	 * @param pageNum
	 *            当前页码
	 * @return &lt;PageResult&gt;
	 */
	@SuppressWarnings("unchecked")
	protected PageResult queryForPageResult(String countName, String queryName,
			Object obj, int pageSize, int pageNum)
	{
		// 申明一个空返回集合对象，NULL Object模式，避免返回Null造成麻烦
		PageResult pr = PageResult.EMPTY_PAGE;
		// 通过一定个查询，获取总记录数
		final int totalRowNum = obj == null ? (Integer) sqlMapClientTemplate
				.queryForObject(countName) : (Integer) sqlMapClientTemplate
				.queryForObject(countName, obj);

		// 如果查询集合大于0，表示有查询结果
		if (totalRowNum > 0)
		{
			try
			{
				CustomizedSQLExecutor
						.setCurrentPaginType(CustomizedSQLExecutor.DBType.Native);
				// 构造分页对象
				pr = new PageResult(totalRowNum, pageSize, pageNum);
				List list = obj == null ? sqlMapClientTemplate.queryForList(
						queryName, pr.getStartRow(), pageSize)
						: sqlMapClientTemplate.queryForList(queryName, obj, pr
								.getStartRow(), pageSize);
				pr.setResultList(list);
			}
			finally
			{
				CustomizedSQLExecutor
						.setCurrentPaginType(CustomizedSQLExecutor.DBType.Default);
			}
		}
		return pr;
	}

	/**
	 * 根据SQL语句获取序列号
	 * 
	 * @param namingSql
	 *            SQL语句别名
	 * @return
	 */
	protected Long generateSequenceNextVal(String namingSql)
	{
		return (Long) sqlMapClientTemplate.queryForObject(namingSql);
	}

	/* (non-Javadoc)
	 * @see com.huawei.cms.common.dao.CommonDAO#batchInsert(java.lang.String, java.util.Collection)
	 */
	@Override
	public <T> void batchInsert(String sqlLabel, Collection<T> col)
	{
		final String _sql = sqlLabel;
		final Collection<T> _col = col;
		
		sqlMapClientTemplate.execute(new SqlMapClientCallback()
		{
			public Object doInSqlMapClient(SqlMapExecutor executor)
					throws SQLException
			{
				executor.startBatch();
				for(T t : _col){
					executor.insert(_sql, t);
				}
				return executor.executeBatch();
			}
		});
		
	}

	/* (non-Javadoc)
	 * @see com.huawei.cms.common.dao.CommonDAO#batchUpdate(java.lang.String, java.util.Collection)
	 */
	@Override
	public <T> void batchUpdate(String sqlLabel, Collection<T> col)
	{
		final String _sql = sqlLabel;
		final Collection<T> _col = col;
		
		sqlMapClientTemplate.execute(new SqlMapClientCallback()
		{
			public Object doInSqlMapClient(SqlMapExecutor executor)
					throws SQLException
			{
				executor.startBatch();
				for(T t : _col){
					executor.update(_sql, t);
				}
				return executor.executeBatch();
			}
		});
	}

	@Override
	public <T> void batchDelete(String sqlLabel, Collection<T> col)
	{
		final String _sql = sqlLabel;
		final Collection<T> _col = col;
		
		sqlMapClientTemplate.execute(new SqlMapClientCallback()
		{
			public Object doInSqlMapClient(SqlMapExecutor executor)
					throws SQLException
			{
				executor.startBatch();
				for(T t : _col){
					executor.delete(_sql, t);
				}
				return executor.executeBatch();
			}
		});
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<? extends Object> queryForList(Map<String, Object> params,
			Class<? extends Object> clazz)
	{
		final String sql_query = warpSqlstatement(clazz, NAMINGSQL_QUERY_LIST);
		return sqlMapClientTemplate.queryForList(sql_query,params);
	}
	
	public PageResult queryForPageResult(Map<String, Object> params, int pageSize,
			int pageNum,Class<? extends Object> clazz)
	{
		final String sql_query_count = warpSqlstatement(clazz, NAMINGSQL_QUERY_PAGELIST_COUNT);
		final String sql_query = warpSqlstatement(clazz, NAMINGSQL_QUERY_PAGELIST);
		return queryForPageResult(sql_query_count, sql_query, params, pageSize, pageNum);
	}

	/* (non-Javadoc)
	 * @see com.huawei.imp.framework.common.dao.IbatisSupportDAO#queryForList(java.util.Map, java.lang.String, int, int)
	 */
	@SuppressWarnings("unchecked")
//	@Override
	public List<? extends Object> queryForList(Map<String, Object> params,
			String sql, int startRow, int fetchSize)
	{
		try
		{
			CustomizedSQLExecutor
					.setCurrentPaginType(CustomizedSQLExecutor.DBType.Native);
			// 构造分页对象
			return sqlMapClientTemplate.queryForList(sql, params, startRow, fetchSize);
		}
		finally
		{
			CustomizedSQLExecutor
					.setCurrentPaginType(CustomizedSQLExecutor.DBType.Default);
		}
	}
}
