package com.huawei.imp.framework.console.script.dao.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.oproject.framework.orm.ibatis.CustomizedSQLExecutor;
import org.springframework.orm.ibatis.SqlMapClientTemplate;

import com.huawei.imp.framework.common.domain.Namingspace;
import com.huawei.imp.framework.console.script.dao.DaoSupport;
import com.huawei.imp.framework.utils.PageResult;
import com.ibatis.sqlmap.client.SqlMapClient;

/**
 * 
 */
public abstract class IbatisCommonDAOImpl implements DaoSupport {

	/**
	 * 日志对象
	 */
	private static final Logger log = Logger.getLogger(IbatisCommonDAOImpl.class);
	
	/**
	 * 命名映射
	 */
	public static final Map<String, String> namingMap = new HashMap<String, String>();
	
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
	public IbatisCommonDAOImpl(SqlMapClientTemplate sqlMapClientTemplate){
		this.sqlMapClientTemplate = sqlMapClientTemplate;
		this.sqlMapClient = sqlMapClientTemplate.getSqlMapClient();
	}

	@Override
	public <T> T get(Class<T> clazz, Serializable id)
	{
		return null;
	}
	@Override
	public <T> T load(Class<T> clazz, Serializable id)
	{
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public <T> PageResult queryForList(Map<String, Object> params,
			int startRow, int fetchSize, Class<? extends Object> clazz)
	{
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public <T> PageResult queryForPageResult(Map<String, Object> params,
			int pageSize, int pageNum, Class<T> clazz)
	{
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public <T> int remove(T t)
	{
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public <T> void save(T entity)
	{
		sqlMapClientTemplate.insert(warpSqlstatement(entity.getClass(), _SQL_INSERT), entity);;
	}
	@Override
	public <T> int update(T t)
	{
		// TODO Auto-generated method stub
		return 0;
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
}
