package com.huawei.imp.framework.model.privilege;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.huawei.imp.framework.exception.FrameworkBusinessException;
import com.huawei.imp.framework.jee.session.ServletSessionUtil;
import com.huawei.imp.framework.model.privilege.cacheModel.ReadOnlyCacheModel.ExecuteHandler;
import com.huawei.imp.framework.model.privilege.domain.Account;
import com.huawei.imp.framework.model.privilege.service.PrivilegeService;
import com.huawei.imp.framework.utils.MD5Util;

/**
 * Description: 用户权限管理工具类,该类依赖于SessionUserUtil类
 * 
 * @author ahli Apr 12, 2009
 */
public class PrivilegeUtil
{

	/**
	 * 日志对象
	 */
	private static final Logger log = Logger.getLogger(PrivilegeUtil.class);

	/**
	 * 用户管理业务接口
	 */
	private static PrivilegeService service;

	/**
	 * 工具类，私有化构造函数
	 */
	private PrivilegeUtil(){}

	/**
	 * 验证操作当前进程的用户是否具有权限CODE的操作权限。
	 * 
	 * @param code
	 *            权限CODE
	 * @return true/false;
	 */
	public static boolean validateUserRight(String code)
	{
		validateService();
		if (null != service)
		{
			final long accountID = ServletSessionUtil.getLoginID();
			return service.validatePersission(accountID, code);
		}
		return false;
	}

	/**
	 * 获取当前账户对象
	 * @return    账户对象
	 */
	public static Account loadCurrentAccountBO(){
		validateService();
		if (null != service)
		{
			final long accountID = ServletSessionUtil.getLoginID();
			return service.loadAccount(accountID);
		}
		return null;
	}
	
	public static void init(PrivilegeService arg0)
	{
		setPrivilegeService(arg0);
		if(log.isDebugEnabled()){
			log.debug("PrivilegeUtil初始化完成。");
		}
	}
	
	private static void setPrivilegeService(PrivilegeService arg0){
		service = arg0;
	}

	/**
	 * 根据账户名，密码密文获取账户对象
	 * @param name	用户名
	 * @param psw	密码密文
	 * @return
	 * 账户对象的副本
	 * @exception
	 * 当用户名密码验证失败时抛出common.um.exception.accountIsNotAvalibal
	 */
	public static Account loadAccountByEncodePSW(String name, String psw)
	{
		validateService();
		try{
			if (null != service)
			{
				return service.loadAccountEncodePsw(name, psw);
			}
		}catch(FrameworkBusinessException e){
			//捕获到业务异常，说明验证失败
			log.error("common.um.exception.accountIsNotAvalibal", e);
			return null;
		}
		return null;
	}
	
	/**
	 * 根据账户名，密码密文获取账户对象
	 * @param name	用户名
	 * @param psw	密码密文
	 * @param flag	是否更新登录时间
	 * @return
	 * 账户对象的副本
	 * @exception
	 * 当用户名密码验证失败时抛出common.um.exception.accountIsNotAvalibal
	 */
	public static Account loadAccountByEncodePSW(String name, String psw, boolean flag)
	{
		validateService();
		try{
			if (null != service)
			{
				return service.loadAccountEncodePsw(name, psw, flag);
			}
		}catch(FrameworkBusinessException e){
			//捕获到业务异常，说明验证失败
			log.error("common.um.exception.accountIsNotAvalibal", e);
			return null;
		}
		return null;
	}
	
	/**
	 * 校验service对象是否null。如果是null，则抛出异常
	 */
	private static void validateService(){
		if(null == service){
			throw new FrameworkBusinessException("can not invoke this method before you init the Util. property[service] object is null." +
					"make sure you had invoke init(PrivilegeService arg0) at first, and arg0 is not null.");
		}
	}
	
	/**
	 * 获取用户对象
	 * @param name	用户名
	 * @param psw	密码明文
	 * @return
	 */
	public static Account loadAccountByPSW(String name, String psw){
		final String _psw = MD5Util.getMd5Str(psw);
		return loadAccountByEncodePSW(name, _psw);
	}
	
	/**
	 * 获取用户对象
	 * @param name	用户名
	 * @param psw	密码明文
	 * @return
	 */
	public static List<Account> loadAccountByRight(String code){
		validateService();
		return service.listAccountByRightCode(code);
	}
	
	/**
	 * 根据ID加载账户对象
	 * @param id
	 * @return
	 */
	public static Account loadAccount(Long id){
		validateService();
		return service.loadAccount(id);
	}
	
	/**
	 * 校验accountID对应的账户是否属于roleID对应的角色
	 * @param accountID    被校验账户ID
	 * @param roleID       被校验角色ID
	 * @return
	 */
	public static boolean validateAccountRole(final Long accountID, final Long roleID){
		boolean ret = false;
		// 修改了实现方式，直接访问缓存中的对象，避免复杂的对象深度克隆
		if(roleID != null && accountID != null){
			return service.getCacheModel().executeWithoutClone(Account.class, new ExecuteHandler<Boolean, Account>() {
				@Override
				public Boolean execute(Map<Serializable, Account> container) {
					Account ac = container.get(accountID);
					if(null != ac && roleID.equals(ac.getRole().getId())){
						return true;
					}
					return false;
				}
				
			});
		}
		return ret;
	}
	
	/**
	 * 校验当前登录账户是否属于指定roleID的角色
	 * @param roleID    被校验角色ID
	 * @return
	 */
	public static boolean validateCurAccountRole(Long roleID){
		final Long accountID = ServletSessionUtil.getLoginID();
		return validateAccountRole(accountID, roleID);
	}
}
