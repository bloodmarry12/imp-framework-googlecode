package com.huawei.imp.framework.model.privilege.cacheModel;

import java.util.List;
import java.util.Map;

import com.huawei.imp.framework.cache.CacheDAO;
import com.huawei.imp.framework.model.privilege.domain.Account;
import com.huawei.imp.framework.model.privilege.domain.ExplorerIndex;
import com.huawei.imp.framework.model.privilege.domain.Right;
import com.huawei.imp.framework.model.privilege.domain.Role;

public interface CacheModel extends CacheDAO
{

	/**
	 * 初始化缓存模型
	 * 
	 * @param accounts
	 * @param roles
	 * @param rights
	 * @param role2Rights
	 * @param account2Role
	 * @param role2Right
	 */
	public void rebuildCacheModel(List<Account> accounts, 
			List<Role> roles,
			List<Right> rights, 
			List<Map<String, Long>> role2Rights,
			List<Map<String, Long>> account2Rights,
			List<ExplorerIndex> explorerIndexs);

	/**
	 * 加载账户对象
	 * @param name    账户名称
	 * @return
	 */
	Account loadAccount(String name);

	/**
	 * 加载权限对象
	 * @param code    权限编码
	 * @return
	 */
	Right loadRight(String code);
	
	/**
	 * 刷新缓存内容
	 */
	public void flushCacheModel();

}