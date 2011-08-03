package com.huawei.imp.framework.model.privilege.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.huawei.imp.framework.common.observer.Observer;
import com.huawei.imp.framework.common.observer.ObserverAware;
import com.huawei.imp.framework.common.observer.ObserverUtil;
import com.huawei.imp.framework.exception.FrameworkBusinessException;
import com.huawei.imp.framework.jee.session.ServletSessionUtil;
import com.huawei.imp.framework.logger.LogFactory;
import com.huawei.imp.framework.logger.Logger;
import com.huawei.imp.framework.model.privilege.PrivilegeUtil;
import com.huawei.imp.framework.model.privilege.cacheModel.ReadOnlyCacheModel;
import com.huawei.imp.framework.model.privilege.cacheModel.ReadOnlyCacheModel.ExecuteHandler;
import com.huawei.imp.framework.model.privilege.cacheModel.ReadOnlyCacheModel.QueryHandler;
import com.huawei.imp.framework.model.privilege.cacheModel.impl.PrivilegeCacheModelImpl;
import com.huawei.imp.framework.model.privilege.dao.PrivilegeDAO;
import com.huawei.imp.framework.model.privilege.domain.Account;
import com.huawei.imp.framework.model.privilege.domain.ExplorerIndex;
import com.huawei.imp.framework.model.privilege.domain.Right;
import com.huawei.imp.framework.model.privilege.domain.Role;
import com.huawei.imp.framework.model.privilege.domain.Status;
import com.huawei.imp.framework.model.privilege.service.PrivilegeService;
import com.huawei.imp.framework.reload.ReloadSupport;
import com.huawei.imp.framework.transaction.aspectj.annotation.MultTransactional;
import com.huawei.imp.framework.utils.MD5Util;
import com.huawei.imp.framework.utils.PageResult;

/**
 * <p>
 * 用户权限管理服务,系统用户权限管理
 * </p>
 * @see 
 * @author aohai.li
 * @version CMSV100R001DB0, 2009-8-19
 * @since CMSV100R001DB0
 */
@Service("privilegeService")
public class PrivilegeServiceImpl implements PrivilegeService, ObserverAware<String>, ReloadSupport{

	/**
	 * 日志对象
	 */
	private static final Logger log = LogFactory.getLogger(PrivilegeServiceImpl.class);
	
	@Autowired
	@Qualifier("privilegeDAO")
	private PrivilegeDAO privilegeDAO;
	
	/**
	 * 只读缓存模型
	 */
	private ReadOnlyCacheModel readOnlyCacheModel;
	
	/**
	 * 观察者对象
	 */
	private ObserverUtil<String> observerUtil = new ObserverUtil<String>();
	
	@PostConstruct
	public void init(){
		PrivilegeUtil.init(this);
		reload();
	}
	
	@MultTransactional
	@Override
	public void activeAccount(long[] ids) {
		resetAccountStatus(ids, Status.Element.ACTIVE.getValue());
		// 通知观察者
		observerUtil.notifyObserver(null);
	}

	@Override
	@MultTransactional
	public void applyAccount2Active(long[] ids) {
		resetAccountStatus(ids, Status.Element.WAITE2ACTION.getValue());
		// 通知观察者
		observerUtil.notifyObserver(null);
	}
	
	private void resetAccountStatus(long[] ids, final int status) {
		// 如果传入的ID为空，直接返回；
		if (ids == null) {
			return;
		}

		// 申明账户对象
		Account account = null;

		// 申明账户数据库操作TO
		Account accountTO = null;

		// 执行数据库操作，将数据持久化
		for (long id : ids) {
			// 数据库操作
			accountTO = new Account();
			accountTO.setId(id);
			accountTO.getStatus().setValue(status);
			privilegeDAO.update(accountTO);
		}

		// 更新缓存内容
		for (long id : ids) {
			account = readOnlyCacheModel.load(Account.class, id);
			account.getStatus().setValue(status);
		}
	}
	
	@Override
	@MultTransactional
	public void approveAccount2Active(long[] ids) {
		resetAccountStatus(ids, Status.Element.ACTIVE.getValue());
		// 通知观察者
		observerUtil.notifyObserver(null);
	}

	@Override
	@MultTransactional
	public void changePassword(long accountID, String opsw, String npsw) {
		// 加写锁
		Account account = readOnlyCacheModel.load(Account.class, Long
				.valueOf(accountID));
		if (!account.getPassword().equals(MD5Util.getMd5Str(opsw))) {
			throw new FrameworkBusinessException(
					"common.um.exception.userNameIsNotAvailable");
		}
		String password = null;
		if (StringUtils.isNotBlank(npsw)) {
			// 将密码进行MD5加密
			password = MD5Util.getMd5Str(npsw);
		} else {
			password = MD5Util.getMd5Str(Account.PASSWOR_DAFALUT);
		}

		account.setPassword(password);
		account.setLastPwdModTime(new Date());
		privilegeDAO.update(account);

		// 通知观察者
		observerUtil.notifyObserver(null);
	}

	@Override
	@MultTransactional
	public void changePassword(long accountID, String npsw) {
		Account _proxy = readOnlyCacheModel.load(Account.class, accountID);
		String password = null;
		if (StringUtils.isNotBlank(npsw)) {
			// 将密码进行MD5加密
			password = MD5Util.getMd5Str(npsw);
		} else {
			password = MD5Util.getMd5Str(Account.PASSWOR_DAFALUT);
		}

		_proxy.setPassword(password);
		privilegeDAO.update(_proxy);

		// 通知观察者
		observerUtil.notifyObserver(null);
	}
	
	@Override
	@MultTransactional
	public List<Role> findAllRole() {
		return readOnlyCacheModel.query(Role.class, null);
	}

	@Override
	@MultTransactional
	public List<Account> listAccountByRoleID(final long roleID) {
		return readOnlyCacheModel.query(Account.class, new QueryHandler<Account>(){
			@Override
			public boolean filter(Account obj) {
				return roleID == obj.getRole().getId();
			}
		});
	}

	@Override
	@MultTransactional
	public Account loadAccount(String accountName, String accountPSW) {
			// 将密码进行MD5加密
			final String password = MD5Util.getMd5Str(accountPSW);
			return loadAccountEncodePsw(accountName, password);
	}

	@Override
	@MultTransactional
	public Account loadAccount(long id) {
			Account account = readOnlyCacheModel.load(Account.class, id);
			// 如果账户对象不存在，则抛出异常
			if (null == account)
			{
				throw new FrameworkBusinessException("common.um.role.exception.IsNotExists", new Object[]{id});
			}
			return account;
	}

	@Override
	@MultTransactional
	public Account loadAccountEncodePsw(final String accountName, String accountPSW) {
			Account account = readOnlyCacheModel.load(Account.class, new QueryHandler<Account>(){

				@Override
				public boolean filter(Account obj) {
					// 排除删除帐户
					if(!Status.Element.REMOVED.getValue().equals(obj.getStatus().getValue()) &&
							obj.getName().equals(accountName)){
						return true;
					}else{
						return false;
					}
				}});
			
			if (null == account)
			{
				// 用户名不存在
				throw new FrameworkBusinessException(
						"common.user.exception.userNameIsNotExist", new Object[]
						{
							accountName
						});
			}
			if (!account.getPassword().equals(accountPSW))
			{
				// 用户密码验证失败
				throw new FrameworkBusinessException(
						"common.user.exception.userNameIsNotAvailable");
			}

			// 账户状态如果是停用或者逻辑删除，将无法登陆系统
			if (Status.Element.STOPED.getValue().equals(account.getStatus().getValue())
					||Status.Element.REMOVED.getValue().equals(account.getStatus().getValue())
					)
			{
				throw new FrameworkBusinessException(
						"common.user.exception.userStatusIsNotAvailable",
						new Object[]
						{
							account.getStatus()
						});
			}
			return account;
	}

	@Override
	@MultTransactional
	public ExplorerIndex loadExplorerIndex() {
		return readOnlyCacheModel.load(ExplorerIndex.class, ExplorerIndex.ROOT_NODE_ID).deepClone();
	}

	@Override
	@MultTransactional
	public ExplorerIndex loadExplorerIndexByID(long id) {
		ExplorerIndex explorerIndex = readOnlyCacheModel.load(ExplorerIndex.class, id);
		return explorerIndex.deepClone();
	}

	@Override
	@MultTransactional
	public ExplorerIndex loadExplorerIndexWithPermission(long id) {
		// 从线程中获取用户ID
		final long accountID = ServletSessionUtil.getLoginID();
		Account account = readOnlyCacheModel.load(Account.class, accountID);
		ExplorerIndex explorerIndex = readOnlyCacheModel.load(ExplorerIndex.class, id);
		return explorerIndex.deepCloneWithRight(account.getRights());
	}

	@Override
	@MultTransactional
	public Right loadRight(long id) {
		return readOnlyCacheModel.load(Right.class, id);
	}

	@Override
	@MultTransactional
	public Right loadRightWidthRoleID(long id, long roleID) {
		Right right = readOnlyCacheModel.load(Right.class, id);
		Role role = readOnlyCacheModel.load(Role.class, roleID);
		return right.deepCloneWithoutRole(role.getRights());
	}

	@Override
	@MultTransactional
	public Role loadRole(long id) {
		return this.readOnlyCacheModel.load(Role.class, id);
	}

	@Override
	@MultTransactional
	public PageResult queryAccount(int pageSize, int pageNum) {
		List<Account> accountArray = readOnlyCacheModel.query(Account.class, new QueryHandler<Account>(){
			@Override
			public boolean filter(Account obj) {
				if (!Status.Element.REMOVED.getValue().equals(obj.getStatus().getValue()))
				{
					return true;
				}else{
					return false;
				}
			}});
		return queryAccount(pageSize, pageNum, accountArray);
	}

	@Override
	@MultTransactional
	public PageResult queryAccount(int pageSize, int pageNum,
			Map<String, Object> params) {
			// 角色ID
			final Long roleID = (Long) params.get("roleID");
			// 账户名称匹配字段
			final String name = (String) params.get("name");
			// 状态
			final Integer status = (Integer) params.get("status");
			//最后登录时间段，查询条件开始时间
			final Date startDate = (Date) params.get("startDate");
			// 最后登录时间段，查询条件截至时间
			final Date endDate = (Date) params.get("endDate");
			
			final String flag = (String)params.get("flag");
			
			List<Account> accountArray = new ArrayList<Account>(50);
			Collection<Account> col = null;
			if (null != roleID)
			{
				col = readOnlyCacheModel.load(Role.class, roleID).getAccounts();
			}
			else
			{
				col = readOnlyCacheModel.query(Account.class, null);
			}
			
			List<Long> loginTimeIDs = null;
			// 从数据库查询能够登录时间条件
			if(null != startDate || null != endDate)
			{
				Map<String, Object> _params = new HashMap<String, Object>();
				_params.put("startTime", startDate);
				_params.put("endTime", endDate);
				_params.put("flag", flag);
				loginTimeIDs = this.privilegeDAO.queryAccountIDsByLoginTime(_params);
			}
			
			for (Account bo : col)
			{
				/*
				 * 条件查询
				 * 1、判断状态不能是逻辑删除；
				 * 2、用户名；
				 * 3、状态；
				 * 4、最后登录时间(返回)
				 */
				if (!Status.Element.REMOVED.getValue().equals(bo.getStatus().getValue())
						&& (StringUtils.isNotBlank(name)
								&& bo.getName().contains(name) || StringUtils
								.isBlank(name))
						&& (null == status || status.equals(bo.getStatus().getValue()))
						&& (null == loginTimeIDs || loginTimeIDs.contains(Long.valueOf(bo.getId()))))
				{
					accountArray.add(bo);
				}
			}
			return queryAccount(pageSize, pageNum, accountArray);
	}

	@Override
	@MultTransactional
	
	public PageResult queryRole(int pageSize, int pageNum) {
			List<Role> roles = readOnlyCacheModel.query(Role.class, null);
			return queryRole(roles, pageSize, pageNum);
	}

	private PageResult queryAccount(int pageSize, int pageNum,
			List<Account> list)
	{
			PageResult pr = new PageResult(list.size(), pageSize, pageNum);
			List<Account> dataList = new ArrayList<Account>(pr
					.getCurrentPageSize());

			Collections.sort(list, new Comparator<Account>()
			{
				@Override
				public int compare(Account o1, Account o2)
				{
					return o1.getName().compareTo(o2.getName());
				}
			});
			for (int i = pr.getStartRow(); i < pr.getStartRow()
					+ pr.getCurrentPageSize(); i++)
			{
				dataList.add(list.get(i).cloneWithoutRight());
			}
			pr.setResultList(dataList);
			return pr;
	}
	
	@Override
	@MultTransactional
	public void refreshCacheModle() {
//		init();
	}

	@Override
	@MultTransactional
	
	public void rejectAccount2Active(long[] ids) {
			resetAccountStatus(ids, Status.Element.REJECT.getValue());
			// 通知观察者
			observerUtil.notifyObserver(null);
	}

	@Override
	@MultTransactional
	
	public void removeAccount(long[] ids) {
			resetAccountStatus(ids, Status.Element.REMOVED.getValue());
			// 通知观察者
			observerUtil.notifyObserver(null);
	}

	@Override
	@MultTransactional
	
	public void removeExplorerIndex(long id) {
			privilegeDAO.delete(ExplorerIndex.class, id);
			ExplorerIndex bo = readOnlyCacheModel.load(ExplorerIndex.class, id);
			bo.deleteRelationShip();
			// 通知观察者
			observerUtil.notifyObserver(null);
	}

	@MultTransactional
	@Override
	public void removeRight(long id) {
			Right right = readOnlyCacheModel.load(Right.class, id);
			// 该权限是否还存在被关联的角色，如果有，则抛出业务异常
			if (right.getRoles().size() > 0)
			{
				throw new FrameworkBusinessException(
						"common.um.explorer.exception.hasBeanRoleUsed");
			}
			else if (right.getRights().size() > 0)
			{// 该权限是否还存在子权限节点，如果存在则抛出业务异常
				throw new FrameworkBusinessException(
						"common.um.explorer.exception.hasChildrenRightBO");
			}
			// 数据库删除权限
			privilegeDAO.delete(Right.class, id);
			// 通知观察者
			observerUtil.notifyObserver(null);
	}

	@MultTransactional
	
	@Override
	public void removeRole(long id) {
			Role role = readOnlyCacheModel.load(Role.class, id);
			// 该权限是否还存在被关联的角色，如果有，则抛出业务异常
			if (role.getAccounts().size() > 0)
			{
				throw new FrameworkBusinessException(
						"common.um.role.exception.cantRemoveWithAccounts");
			}
			// 数据库删除权限
			privilegeDAO.delete(Role.class, id);
			// 通知观察者
			observerUtil.notifyObserver(null);
	}

	@Override
	@MultTransactional
	public boolean validateAccount(final String name)
	{
			/*
			 *  edit by ahli
			 *  修改了方法，由之前的通过name来判断account帐户密码是否
			 *  已经使用，修改修改为，验证非逻辑删除状态下的帐户名是否有重复；
			 */
			List<Account> accountList = readOnlyCacheModel.query(Account.class, new QueryHandler<Account>(){
				@Override
				public boolean filter(Account account) {
					if(account.getName().equals(name) && !Status.Element.REMOVED.getValue().equals( account.getStatus().getValue()))
					{
						return true;
					}else{
						
					}
					return false;
				}});
			return accountList.size() > 0;
	}
	
	/* (non-Javadoc)
	 * @see ahli.framework.model.privilege.service.PrivilegeService#saveAccount(ahli.framework.model.privilege.domain.Account)
	 */
	@Override
	@MultTransactional
	
	public void saveAccount(Account bo) {
			// 校验重复
			if (validateAccount(bo.getName()))
			{
				throw new FrameworkBusinessException("common.um.account.exception.notExists");
			}
			// 从缓存中取角色对象
			Role role = readOnlyCacheModel.load(Role.class, bo.getRole().getId());
			if (null == role)
			{
				throw new FrameworkBusinessException("common.um.role.exception.notExists");
			}
			List<Right> list = new ArrayList<Right>();
			Set<Right> tempSet = bo.getRights();
			List<Long> rightIDs = new ArrayList<Long>(bo.getRights().size());
			Right right = null;
			for (Right temp : tempSet)
			{
				right = readOnlyCacheModel.load(Right.class, temp.getId());
				if (null == right)
				{
					throw new FrameworkBusinessException(
							"common.um.role.exception.notExists");
				}
				list.add(right);
				rightIDs.add(right.getId());
			}
			// 如果未定义bo的ID，则重新生成一个ID
			// 修改ID的生成方式，不在业务层显示调用。
//			if (Account.ID_UNDEFINED == bo.getId())
//			{
//				bo.setId(privilegeDAO.selectSequence(Account.class));
//			}
			// 将密码进行MD5加密
			final String password = MD5Util.getMd5Str(bo.getPassword());
			bo.setPassword(password);
			// 如果状态不存在，则设置为新增状态
			if (null == bo.getStatus())
			{
				bo.getStatus().setValue(Status.Element.NEW.getValue());
			}
			privilegeDAO.insert(bo);
			privilegeDAO.insertAccount2Right(bo.getId(), rightIDs);
			// 通知观察者
			observerUtil.notifyObserver(null);
	}

	@Override
	@MultTransactional
	
	public void saveExplorerIndex(ExplorerIndex bo) {
			final String rightCode = bo.getRight().getCode();
			ExplorerIndex parent = readOnlyCacheModel.load(ExplorerIndex.class, bo.getParent().getId());
			if (null == parent)
			{
				throw new FrameworkBusinessException("common.um.explorer.exception.parentNotExists");
			}

			Right right = Right.NULLOBJECT;
			if (StringUtils.isNotBlank(rightCode))
			{
				right = readOnlyCacheModel.load(Right.class, new QueryHandler<Right>() {
					@Override
					public boolean filter(Right obj) {
						return rightCode.equals(obj.getCode());
					}
				});
				if (null == right)
				{
					throw new FrameworkBusinessException(
							"common.um.explorer.exception.codeIsNotExists",
							new Object[]
							{
								bo.getRight().getCode()
							});
				}
			}

			bo.setRight(right);
			// 生成ID
//			bo.setId(privilegeDAO.selectSequence(ExplorerIndex.class));

			// 数据库DAO操作
			privilegeDAO.insert(bo);

			// 通知观察者
			observerUtil.notifyObserver(null);
	}
	
	@MultTransactional
	
	@Override
	public void saveRight(Right bo) {
			// 验证父节点是否存在
			final long parentID = bo.getParent().getId();
			Right parent = readOnlyCacheModel.load(Right.class, parentID);
			if (null == parent)
			{
				throw new FrameworkBusinessException(
						"common.um.explorer.exception.rightBOIsNotExists");
			}

			// 获取序列号作为ID
//			bo.setId(privilegeDAO.selectSequence(Right.class));
			// 数据库操作
			privilegeDAO.insert(bo);
			// 通知观察者
			observerUtil.notifyObserver(null);
	}

	@MultTransactional
	@Override
	public void saveRole(Role bo, List<Long> rightIDs) {
		List<Right> rights = new ArrayList<Right>(rightIDs.size());
		Right right = null;

		// 遍历从缓存中获取权限对象，并且进行验证
		for (Long rightID : rightIDs)
		{
			right = readOnlyCacheModel.load(Right.class, rightID.longValue());

			// 如果该权限ID无法获取到权限对象，则立即抛出业务异常信息。
				if (right == null)
				{
					throw new FrameworkBusinessException(
							"common.um.explorer.exception.codeIsNotExists",
							new Object[]
							{
								rightID
							});
				}

				// 将对象添加到对象List中
				rights.add(right);
			}

			// 如果未设置ID，则增加一个ID
//			if (Role.ID_UNDEFINED == bo.getId())
//			{
//				bo.setId(privilegeDAO.selectSequence(Role.class));
//			}

			// 数据库操作
			privilegeDAO.insert(bo);
			privilegeDAO.insertRole2Right(bo.getId(), rightIDs);
			
			// 通知观察者
			observerUtil.notifyObserver(null);
	}

	@MultTransactional
	
	@Override
	public void stopAccount(long[] ids) {
			resetAccountStatus(ids, Status.Element.STOPED.getValue());
			// 通知观察者
			observerUtil.notifyObserver(null);
	}

	@MultTransactional
	@Override
	public void updateAccountRightOnly(Account bo) {
			// 从缓存中加载账户信息
			List<Right> list = new ArrayList<Right>();
			List<Long> rightIDs = new ArrayList<Long>(bo.getRights().size());
			Set<Right> tempSet = bo.getRights();
			Right right = null;
			for (Right temp : tempSet)
			{
				right = readOnlyCacheModel.load(Right.class, temp.getId());
				if (null == right)
				{
					throw new FrameworkBusinessException(
							"common.um.role.exception.notExists");
				}
				list.add(right);
				rightIDs.add(right.getId());
			}

			privilegeDAO.deleteAccount2RightByAccount(bo.getId());
			privilegeDAO.insertAccount2Right(bo.getId(), rightIDs);

			// 通知观察者
			observerUtil.notifyObserver(null);
	}

	@MultTransactional
	@Override
	public void updateAccountWithoutRight(Account bo) {
			final Set<Right> newRightSet = new HashSet<Right>();
			List<Long> rightIDs = new ArrayList<Long>(bo.getRights().size());
			Set<Right> tempSet = bo.getRights();
			Right right = null;
			for (Right temp : tempSet)
			{
				right = readOnlyCacheModel.load(Right.class, temp.getId());
				if (null == right)
				{
					throw new FrameworkBusinessException(
							"common.um.role.exception.notExists");
				}
				newRightSet.add(right);
				rightIDs.add(right.getId());
			}
			privilegeDAO.update(bo);
			// 通知观察者
			observerUtil.notifyObserver(null);
	}

	@MultTransactional
	
	@Override
	public void updateExplorerIndex(ExplorerIndex bo) {
			final String rightCode = bo.getRight().getCode();
			Right right = Right.NULLOBJECT;
			if (StringUtils.isNotBlank(rightCode))
			{
				right = readOnlyCacheModel.load(Right.class, new QueryHandler<Right>() {

					@Override
					public boolean filter(Right obj) {
						return rightCode.equals(obj.getCode());
					}
				});
				if (null == right)
				{
					throw new FrameworkBusinessException(
							"common.um.explorer.exception.codeIsNotExists",
							new Object[]
							{
								bo.getRight().getCode()
							});
				}
			}
			bo.setRight(right);
			// 数据库保存操作
			privilegeDAO.update(bo);
			
			// 通知观察者
			observerUtil.notifyObserver(null);
	}

	@MultTransactional
	@Override
	public void updateRight(Right bo) {
			Right right = readOnlyCacheModel.load(Right.class, bo.getId());
			if (null == right)
			{
				throw new FrameworkBusinessException(
						"common.um.explorer.exception.rightBOIsNotExists");
			}
			// 数据库操作
			privilegeDAO.update(bo);
			right.setCode(bo.getCode());
			right.setDescription(bo.getDescription());
			right.setRightName(bo.getRightName());
			// 通知观察者
			observerUtil.notifyObserver(null);
	}
	
	@MultTransactional
	
	@Override
	public void updateRole(Role bo, List<Long> rightIDs) {
			// 从数据库加载角色
			Role role = readOnlyCacheModel.load(Role.class, bo.getId());
			if (null == role)
			{
				throw new FrameworkBusinessException("common.um.role.exception.notExists");
			}
			// 做权限对象关联
			List<Right> rights = new ArrayList<Right>(rightIDs.size());
			Right right = null;
			// 遍历从缓存中获取权限对象，并且进行验证
			for (Long rightID : rightIDs)
			{
				right = readOnlyCacheModel.load(Right.class, rightID.longValue());
				// 如果该权限ID无法获取到权限对象，则立即抛出业务异常信息。
				if (right == null)
				{
					throw new FrameworkBusinessException(
							"common.um.explorer.exception.codeIsNotExists",
							new Object[]
							{
								rightID
							});
				}

				// 将对象添加到对象List中
				rights.add(right);
			}
			// 数据库操作
			privilegeDAO.update(bo);
			privilegeDAO.deleteRole2RightByRole(bo.getId());
			privilegeDAO.insertRole2Right(bo.getId(), rightIDs);
			// 通知观察者
			observerUtil.notifyObserver(null);
	}

	@Override
	public boolean validatePersission(final long accountID,final String code) {
		/*
		 * 2010-08-31
		 * 修改了鉴权方法，减少了深度克隆的资源消耗
		 */
		return readOnlyCacheModel.executeWithoutClone(Account.class, new ExecuteHandler<Boolean, Account>(){
			@Override
			public Boolean execute(Map<Serializable, Account> container) {
				Account account = container.get(accountID);
				if(null == account || !account.getStatus().getValue().equals(Status.Element.ACTIVE.getValue())){
					return false;
				}else if(null == code){
					return true;
				}else{
					return account.validateRight(code);
				}
			}});
	}

	@Override
	@MultTransactional
	public boolean validatePersission(final String accountName, final String code) {
		/*
		 * edit by ahli 修改了权限验证方法，仅仅验证非逻辑删除的帐户权限；
		 */

		/*
		 * 2010-08-31 修改了鉴权方法，减少了深度克隆的资源消耗
		 */
		if(null == code){
			return true;
		}
		
		return readOnlyCacheModel.executeWithoutClone(Account.class,
				new ExecuteHandler<Boolean, Account>() {
					@Override
					public Boolean execute(Map<Serializable, Account> container) {
						
						for(Account ac : container.values()){
							if(ac.getName().equals(accountName) && Status.Element.ACTIVE.getValue().equals(ac.getStatus().getValue())){
								return ac.validateRight(code);
							}
						}
						return false;
					}
				});
	}

	@Override
	public boolean validatePersissionCode(final String persissionCode) {
		if (null == readOnlyCacheModel.load(Right.class, new QueryHandler<Right>() {

			@Override
			public boolean filter(Right obj) {
				return obj.getCode().equals(persissionCode);
			}
		})) {
			return false;
		} else {
			return true;
		}
	}

	private PageResult queryRole(List<Role> roles, int pageSize, int pageNum)
	{
			PageResult pr = new PageResult(roles.size(), pageSize, pageNum);
			List<Role> dataList = new ArrayList<Role>(pr.getCurrentPageSize());
			Collections.sort(roles, new Comparator<Role>()
			{
				@Override
				public int compare(Role o1, Role o2)
				{
					return o1.getName().compareTo(o2.getName());
				}
			});
			// 根据
			for (int i = pr.getStartRow(); i < pr.getStartRow()
					+ pr.getCurrentPageSize(); i++)
			{
				dataList.add(roles.get(i).cloneWithoutRight());
			}
			pr.setResultList(dataList);
			return pr;
	}

//	@Override
//	@Transactional
//	public Long generateAccountID()
//	{
//		return privilegeDAO.selectSequence(Account.class);
//	}

	@Override
	@MultTransactional
	public List<Account> listAccountByRightCode(final String code)
	{
		List<Account> accountArray = readOnlyCacheModel.query(Account.class, new QueryHandler<Account>() {
			@Override
			public boolean filter(Account obj) {
				return Status.Element.ACTIVE.getValue().equals(obj.getStatus().getValue()) && obj.validateRight(code);
			}
		});
		return accountArray;
	}

	private void updateLoginTime(Account account)
	{
		
		final Date currentDate = new Date();
		//记录时间
		Account _boProxy = new Account();
		_boProxy.setId(account.getId());
		_boProxy.setStatus(account.getStatus());
		_boProxy.setLastLoginTime(currentDate);
		privilegeDAO.update(_boProxy);
		
		//数据库操作
		Account _cacheProxy = readOnlyCacheModel.load(Account.class, account.getId());
		_cacheProxy.setLastLoginTime(currentDate);
	}

	@Override
	@MultTransactional
	public Account loadAccount(String accountName, String accountPSW,
			boolean flag)
	{
		// 将密码进行MD5加密
		final String password = MD5Util.getMd5Str(accountPSW);
		return loadAccountEncodePsw(accountName, password, flag);
	}

	@Override
	@MultTransactional
	public Account loadAccountEncodePsw(final String accountName, String accountPSW,
			boolean flag)
	{
			Account account = readOnlyCacheModel.load(Account.class, new QueryHandler<Account>() {

				@Override
				public boolean filter(Account obj) {
					// 排除删除帐户
					if(!Status.Element.REMOVED.getValue().equals(obj.getStatus().getValue()) &&
							obj.getName().equals(accountName)){
						return true;
					}else{
						return false;
					}
				}
			});
			if (null == account)
			{
				// 用户名不存在
				throw new FrameworkBusinessException(
						"common.user.exception.userNameIsNotExist", new Object[]
						{
							accountName
						});
			}
			if (!account.getPassword().equals(accountPSW))
			{
				// 用户密码验证失败
				throw new FrameworkBusinessException(
						"common.user.exception.userNameIsNotAvailable");
			}

			// 账户状态如果是停用或者逻辑删除，将无法登陆系统
			if (Status.Element.STOPED.getValue().equals(account.getStatus().getValue()) || 
					Status.Element.REMOVED.getValue().equals(account.getStatus().getValue()))
			{
				throw new FrameworkBusinessException(
						"common.user.exception.userStatusIsNotAvailable",
						new Object[]
						{
							account.getStatus()
						});
			}
			if (flag)
			{
				updateLoginTime(account);//更新登录时间
				privilegeDAO.insertLoginTime(account.getId(), account.getLastLoginTime());
			}
			return account;
	}
	
	/* (non-Javadoc)
	 * @see com.huawei.imp.framework.common.observer.ObserverAware#registObserver(com.huawei.imp.framework.common.observer.Observer)
	 */
	@Override
	public void registObserver(Observer<String> observer)
	{
		observerUtil.registObserver(observer);
	}

	@Override
	public void reload() {
		if(log.isInfoEnabled())
		{
			log.info("开始初始化用户权限数据......");
		}
		
		PrivilegeCacheModelImpl cacheModel = new PrivilegeCacheModelImpl();
		
		// 从数据库加载所有的权限、角色、用户数据到内存
		List<Account> accounts = privilegeDAO.selectAll(Account.class);
		List<Role> roles = privilegeDAO.selectAll(Role.class);
		List<Right> rights = privilegeDAO.selectAll(Right.class);
		List<Map<String, Long>> role2Rights = privilegeDAO.findAllRole2Right();
		List<Map<String, Long>> account2Rights = privilegeDAO.findAllAccount2Right();
		List<ExplorerIndex> explorerIndexs = privilegeDAO.selectAll(ExplorerIndex.class);
		
		// 将初始数据存入缓存
		for (Role role : roles)
		{
			cacheModel.save(role, role.getId());
		}

		for (Right right : rights)
		{
			cacheModel.save(right, right.getId());
		}

		// 维护权限对象树关系
		for (Right right : rights)
		{
			// 权限节点的父节点ID
			final long parentID = right.getParent().getId();

			// 判断父节点是否是固定的根节点，如果是根节点，则将该节点添加到根节点下
			if (parentID > -1)
			{
				Right parent = cacheModel.loadOrign(Right.class, parentID);
				parent.addChildRight(right);
			}
		}

		for (Account account : accounts)
		{
			long roleID = account.getRole().getId();
			Role role = cacheModel.loadOrign(Role.class, roleID);
			account.setRole(role);
			cacheModel.save(account, account.getId());
		}

		// 将根节点缓存，目的是为了统一对象存取
		for (ExplorerIndex explorer : explorerIndexs)
		{
			final Long rightID = explorer.getRight().getId();

			// 如果权限ID不为空，则从缓存中获取对象，并且建立对象关联
			if (null != rightID && rightID != Right.ID_DEFAULT)
			{
				Right right = cacheModel.loadOrign(Right.class, explorer.getRight().getId());
				explorer.setRight(right);
			}
			else
			{
				explorer.setRight(null);
			}
			cacheModel.save(explorer, explorer.getId());
		}

		for (ExplorerIndex explorer : explorerIndexs)
		{
			final Long parentID = explorer.getParent().getId();
			// 根目录的父节点配置为-1
			if (parentID > -1)
			{
				cacheModel.loadOrign(ExplorerIndex.class, parentID).addChildrenExplorerIndexB(
								explorer);
			}
		}

		// 维护账户角色关系
		for (Map<String, Long> account2Right : account2Rights)
		{
			Account account = cacheModel.loadOrign(Account.class, account2Right.get("accountID"));
			
			Right right = cacheModel.loadOrign(Right.class, account2Right.get("rightID"));
			account.getRights().add(right);
		}
		
		// 维护权限角色关系
		for (Map<String, Long> role2Right : role2Rights)
		{
			Right right = cacheModel.loadOrign(Right.class, role2Right.get("rightID"));
			Role role = cacheModel.loadOrign(Role.class, role2Right.get("roleID"));
			role.addRight(right);
		}
		
		// 处理外挂的缓存加载处理
		for(InitPrivilegeCacheModelHandler handler : initPrivilegeCacheModelHandlerArray){
			handler.initCacheModel(cacheModel);
		}
		
		// 替换缓存模型
		this.readOnlyCacheModel = cacheModel;
		if(log.isInfoEnabled())
		{
			log.info("完成初始化用户权限数据......");
		}
	}
	
	/**
	 * 初始化权限缓存
	 */
	private List<InitPrivilegeCacheModelHandler> initPrivilegeCacheModelHandlerArray =  new ArrayList<InitPrivilegeCacheModelHandler>();
	
	public List<InitPrivilegeCacheModelHandler> getInitPrivilegeCacheModelHandlerArray() {
		return initPrivilegeCacheModelHandlerArray;
	}

	public void setInitPrivilegeCacheModelHandlerArray(
			List<InitPrivilegeCacheModelHandler> initPrivilegeCacheModelHandlerArray) {
		this.initPrivilegeCacheModelHandlerArray = initPrivilegeCacheModelHandlerArray;
	}

	/**
	 * <p>
	 * 初始化权限缓存
	 * </p>
	 * @see 
	 * @author aohai.li
	 * @version CMSV100R001DB0SP0, 2010-8-17
	 * @since CMSV100R001DB0SP0
	 */
	public static interface InitPrivilegeCacheModelHandler{
		void initCacheModel(PrivilegeCacheModelImpl pcmi);
	}

	@Override
	public ReadOnlyCacheModel getCacheModel() {
		return this.readOnlyCacheModel;
	}
}
