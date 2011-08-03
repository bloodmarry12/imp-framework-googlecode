package com.huawei.imp.framework.model.privilege.dao.impl;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Component;

import com.huawei.imp.framework.common.dao.impl.MDSSupportIbatisCommonDAOImpl;
import com.huawei.imp.framework.model.privilege.dao.PrivilegeDAO;
import com.huawei.imp.framework.model.privilege.domain.Account;
import com.huawei.imp.framework.model.privilege.domain.Role;
import com.ibatis.sqlmap.client.SqlMapExecutor;

/**
 * 权限管理数据访问层
 * 权限管理数据访问层
 * @author ahli
 * @date 2009-8-19
 */
@Component("privilegeDAO")
public class PrivilegeDAOImpl extends MDSSupportIbatisCommonDAOImpl implements PrivilegeDAO {
	
	private static final String DELETE_ROLE2RIGHT_BY_ROLE = "deleteRole2RightByRole";
	
	private static final String DELETE_ACCOUNT2RIGHT_BY_ACCOUNT = "deleteAccount2RightByAccount";
	
	private static final String SELECT_ROLE2RIGHT = "selectRole2Right";
	
	private static final String SELECT_ACCOUNT2RIGHT = "selectAccount2Right";
	
	private static final String INSERT_ROLE2RIGHT = "insertRole2Right";
	
	private static final String INSERT_ACCOUNT2RIGHT = "insertAccount2Right";
	
	/**
	 * 默认构造函数，注入MDSSupportIbatisCommonDAOImpl构造需要的参数
	 * @param sqlMapClientTemplate
	 */
	public PrivilegeDAOImpl(SqlMapClientTemplate sqlMapClientTemplate) {
		super(sqlMapClientTemplate);
	}
	
	@Override
	public void insertRole2Right(final long roleID, final List<Long> rightIDs) {
		final String sql = super.warpSqlstatement(Role.class, INSERT_ROLE2RIGHT);
		sqlMapClientTemplate.execute(new SqlMapClientCallback()
		{
			
			public Object doInSqlMapClient(SqlMapExecutor executor)
					throws SQLException
			{
				executor.startBatch();
				Map<String, Long> record ;
				for(Long rightID : rightIDs){
					record = new HashMap<String, Long>(2);
					record.put("roleID", roleID);
					record.put("rightID", rightID);
					executor.insert(sql, record);
				}
				return executor.executeBatch();
			}
		});
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Long>> findAllAccount2Right()
	{
		final String sql = super.warpSqlstatement(Account.class, SELECT_ACCOUNT2RIGHT);
		return sqlMapClientTemplate.queryForList(sql);
	}

	@Override
	public void deleteAccount2RightByAccount(long accountID)
	{
		final String sql = super.warpSqlstatement(Account.class, DELETE_ACCOUNT2RIGHT_BY_ACCOUNT);
		sqlMapClientTemplate.delete(sql, accountID);
	}

	@Override
	public void insertAccount2Right(final long accountID, final List<Long> rightIDs)
	{
		final String sql = super.warpSqlstatement(Account.class, INSERT_ACCOUNT2RIGHT);
		sqlMapClientTemplate.execute(new SqlMapClientCallback()
		{
			public Object doInSqlMapClient(SqlMapExecutor executor)
					throws SQLException
			{
				executor.startBatch();
				Map<String, Long> record ;
				for(Long rightID : rightIDs){
					record = new HashMap<String, Long>(2);
					record.put("accountID", accountID);
					record.put("rightID", rightID);
					executor.insert(sql, record);
				}
				return executor.executeBatch();
			}
		});
	}

	@Override
	public void deleteRole2RightByRole(long id) {
		final String sql = super.warpSqlstatement(Role.class, DELETE_ROLE2RIGHT_BY_ROLE);
		sqlMapClientTemplate.delete(sql, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Long>> findAllRole2Right() {
		final String sql = super.warpSqlstatement(Role.class, SELECT_ROLE2RIGHT);
		return sqlMapClientTemplate.queryForList(sql);
	}

	@Override
	public void batchInsertDomainObject(Collection<? extends Object> col, Class<? extends Object> clazz)
	{
		final String sql = super.warpSqlstatement(clazz, _SQL_INSERT);
		super.batchInsert(sql, col);
	}

	@Override
	public void batchInsertAccount2Right(Collection<Map<String, Long>> col)
	{
		final String sql = super.warpSqlstatement(Account.class, INSERT_ACCOUNT2RIGHT);
		super.batchInsert(sql, col);
	}

	@Override
	public void batchInsertRole2Right(Collection<Map<String, Long>> col)
	{
		final String sql = super.warpSqlstatement(Role.class, INSERT_ROLE2RIGHT);
		super.batchInsert(sql, col);
	}

	/* (non-Javadoc)
	 * @see com.huawei.imp.framework.common.dao.IbatisSupportDAO#insertLoginTime(long, java.util.Date)
	 */
	@Override
	public void insertLoginTime(long accountID, Date date)
	{
		final String sql = super.warpSqlstatement(Account.class, "inserLoginTime");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("accountID", accountID);
		params.put("loginTime", date);
		super.sqlMapClientTemplate.insert(sql, params);
	}

	/* (non-Javadoc)
	 * @see com.huawei.imp.framework.model.privilege.dao.PrivilegeDAO#queryAccountIDsByLoginTime(java.util.Map)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Long> queryAccountIDsByLoginTime(Map<String, Object> params)
	{
		final String sql = super.warpSqlstatement(Account.class, "selectAccountIDByLoginTime");
		return super.sqlMapClientTemplate.queryForList(sql, params);
	}
}
