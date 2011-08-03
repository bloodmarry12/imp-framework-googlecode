package com.huawei.imp.framework.jee.session;

/**
 * 会话
 * 〈功能详细描述〉
 * @author ahli
 * @version IMPV100R001DA0, Aug 31, 2009 
 * @see [相关类/方法]
 * @since CMS IMPV100R001DA0
 */
public interface SessionUserInfo
{

	/**
	 * 获取账户ID
	 * @return
	 */
	Long getAccountID();
	
	/**
	 * 获取密码
	 * @return
	 */
	String getPassword();
	
	/**
	 * @return
	 */
	String getAccountName();
}
