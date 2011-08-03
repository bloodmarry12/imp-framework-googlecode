package com.huawei.imp.framework.jee.session;

/**
 * <一句话功能/>
 * <功能描述/>
 * @author ahli
 * @date 2009-8-6
 */
public class ServletSessionUtil {

	private static final ThreadLocal<SessionUserInfo> threadLocal = new ThreadLocal<SessionUserInfo>();
	
	public static void setSessionUserInfo(Long id, String password){
		threadLocal.set(new SessionUserInfoImpl(id, password));
	}
	
	public static void setSessionUserInfo(Long id, String password, String name){
		threadLocal.set(new SessionUserInfoImpl(id, password, name));
	}
	
	public static Long getLoginID(){
		if(null != threadLocal.get()){
			return threadLocal.get().getAccountID();
		}else{
			return null;
		}
		
	}
	
	public static String getLoginPassword(){
		return threadLocal.get().getPassword();
	}
	
	/**
	 * 〈一句话功能简述〉
	 * 〈功能详细描述〉
	 * @author ahli
	 * @version IMPV100R001DA0, Aug 31, 2009 
	 * @see [相关类/方法]
	 * @since CMS IMPV100R001DA0
	 */
	public static class SessionUserInfoImpl implements SessionUserInfo, java.io.Serializable{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		private Long accountID;
		private String password;
		private String accountName;
		
		public SessionUserInfoImpl(Long accountID, String password){
			this.accountID = accountID;
			this.password = password;
		}
		
		public SessionUserInfoImpl(Long accountID, String password, String accountName){
			this.accountID = accountID;
			this.password = password;
			this.accountName = accountName;
		}
		
		@Override
		public Long getAccountID()
		{
			return accountID;
		}

		@Override
		public String getPassword()
		{
			return password;
		}

		public void setAccountID(Long accountID)
		{
			this.accountID = accountID;
		}

		public void setPassword(String password)
		{
			this.password = password;
		}

		@Override
		public String getAccountName()
		{
			return accountName;
		}

		public void setAccountName(String accountName)
		{
			this.accountName = accountName;
		}
	}
}
