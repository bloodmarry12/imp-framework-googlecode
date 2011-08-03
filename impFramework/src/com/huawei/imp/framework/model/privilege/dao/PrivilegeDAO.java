package com.huawei.imp.framework.model.privilege.dao;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.huawei.imp.framework.common.dao.IbatisSupportDAO;

/**
 * Description:
 * 用户管理
 * @author ahli
 * Apr 24, 2009
 * 
 */
public interface PrivilegeDAO extends IbatisSupportDAO{

	/**
	 * @param id
	 */
	void deleteRole2RightByRole(long id);
	
	
	/**
	 * 查询所有的账户权限关系
	 * @return
	 */
	List<Map<String, Long>> findAllAccount2Right();
	
	/**
	 * 查询所有的角色权限关系
	 * @return
	 */
	List<Map<String, Long>> findAllRole2Right();
	
	/**
	 * @param list
	 */
	void insertRole2Right(long roleID, List<Long> rightIDs);
	
	/**
	 * 插入账户权限
	 * @param accountID
	 * @param rightIDs
	 */
	void insertAccount2Right(long accountID, List<Long> rightIDs);
	
	/**
	 * 删除账户
	 * @param accountIDs
	 */
	void deleteAccount2RightByAccount(long accountIDs);
	
	/**
	 * 批量使用默认的insert方法插入领域对象
	 * @param col
	 */
	void batchInsertDomainObject(Collection<? extends Object> col, Class<? extends Object> clazz);
	
	/**
	 * 批量插入角色权限关联数据
	 * @param col
	 */
	void batchInsertRole2Right(Collection<Map<String, Long>> col);
	
	/**
	 * 批量插入账户权限关联数据
	 * @param col
	 */
	void batchInsertAccount2Right(Collection<Map<String, Long>> col);
	
	/**
	 * 插入帐户登录时间
	 * @param accountID    帐户ID
	 * @param date         日期
	 */
	void insertLoginTime(long accountID, Date date);
	
	/**
	 * 根据登录时间，查询符合条件的帐户ID
	 * @param params    查询条件
	 * @return    符合条件的帐户ID集合
	 */
	List<Long> queryAccountIDsByLoginTime(Map<String, Object> params);
}
