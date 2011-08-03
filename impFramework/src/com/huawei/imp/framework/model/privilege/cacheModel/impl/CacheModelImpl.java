package com.huawei.imp.framework.model.privilege.cacheModel.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.huawei.imp.framework.cache.CacheDAOImpl;
import com.huawei.imp.framework.model.privilege.cacheModel.CacheModel;
import com.huawei.imp.framework.model.privilege.domain.Account;
import com.huawei.imp.framework.model.privilege.domain.ExplorerIndex;
import com.huawei.imp.framework.model.privilege.domain.Right;
import com.huawei.imp.framework.model.privilege.domain.Role;
import com.huawei.imp.framework.model.privilege.domain.Status;

/**
 * Description: 缓存模型对象
 * 
 * @author ahli Apr 24, 2009
 */
@Component("cacheModel")
public class CacheModelImpl extends CacheDAOImpl implements CacheModel
{
	/**
	 * 默认构造器
	 */
	public CacheModelImpl()
	{
	}

	/**
	 * 初始化缓存模型
	 * 
	 * @param accounts
	 * @param roles
	 * @param rights
	 * @param account2Role
	 * @param role2Right
	 */
	public void rebuildCacheModel(List<Account> accounts, List<Role> roles,
			List<Right> rights, 
			List<Map<String, Long>> role2Rights,
			List<Map<String, Long>> account2Rights,
			List<ExplorerIndex> explorerIndexs)
	{

		// 刷新缓存模型
		flushCacheModel();

		// 将初始数据存入缓存
		for (Role role : roles)
		{
			getSession().save(role, role.getId());
		}

		for (Right right : rights)
		{
			getSession().save(right, right.getId());
		}

		// 维护权限对象树关系
		for (Right right : rights)
		{
			// 权限节点的父节点ID
			final long parentID = right.getParent().getId();

			// 判断父节点是否是固定的根节点，如果是根节点，则将该节点添加到根节点下
			if (parentID > -1)
			{
				Right parent = getSession().load(Right.class, parentID);
				parent.addChildRight(right);
			}
		}

		for (Account account : accounts)
		{
			long roleID = account.getRole().getId();
			Role role = getSession().load(Role.class, roleID);
			account.setRole(role);
			getSession().save(account, account.getId());
		}

		// 将根节点缓存，目的是为了统一对象存取
		for (ExplorerIndex explorer : explorerIndexs)
		{
			final Long rightID = explorer.getRight().getId();

			// 如果权限ID不为空，则从缓存中获取对象，并且建立对象关联
			if (null != rightID && rightID != Right.ID_DEFAULT)
			{
				Right right = getSession().load(Right.class, explorer.getRight().getId());
				explorer.setRight(right);
			}
			else
			{
				explorer.setRight(null);
			}
			getSession().save(explorer, explorer.getId());
		}

		for (ExplorerIndex explorer : explorerIndexs)
		{
			final Long parentID = explorer.getParent().getId();
			// 根目录的父节点配置为-1
			if (parentID > -1)
			{
				getSession().load(ExplorerIndex.class, parentID).addChildrenExplorerIndexB(
								explorer);
			}
		}

		// 维护账户角色关系
		for (Map<String, Long> account2Right : account2Rights)
		{
			Account account = getSession().load(Account.class, account2Right.get("accountID"));
			
			Right right = getSession().load(Right.class, account2Right.get("rightID"));
			account.getRights().add(right);
		}
		
		// 维护权限角色关系
		for (Map<String, Long> role2Right : role2Rights)
		{
			Right right = getSession().load(Right.class, role2Right.get("rightID"));
			Role role = getSession().load(Role.class, role2Right.get("roleID"));
			role.addRight(right);
		}
		
		getSession().flush();
		
	}

	/**
	 * 刷新缓存内容
	 */
	@SuppressWarnings("unchecked")
	public void flushCacheModel()
	{
		getSession().removeAll(Account.class, 
				Role.class, 
				ExplorerIndex.class, 
				Right.class);
	}

	@Override
	public Account loadAccount(String name)
	{
		List<Account> list = getSession().find(Account.class);
		Iterator<Account> it = list.listIterator();
		Account account;
		while (it.hasNext())
		{
			account = it.next();
			if (account.getName().equals(name) && !Status.Element.REMOVED.getValue().equals(account.getStatus().getValue()))
			{
				return account;
			}
		}
		return null;
	}

	@Override
	public Right loadRight(String code)
	{
		Iterator<Right> it = getSession().find(Right.class)
				.iterator();
		Right _right;
		while (it.hasNext())
		{
			_right = it.next();
			if (_right.getCode().equals(code))
			{
				return _right;
			}
		}
		return null;
	}
}
