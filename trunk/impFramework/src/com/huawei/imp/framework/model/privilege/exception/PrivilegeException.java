package com.huawei.imp.framework.model.privilege.exception;

import com.huawei.imp.framework.exception.FrameworkBusinessException;

/**
 * Description:
 * 权限异常
 * @author ahli
 * Apr 24, 2009
 * 
 */
public class PrivilegeException extends FrameworkBusinessException{

	private static final long serialVersionUID = 1L;
	
	/**
	 * 没有足够的权限执行该操作
	 */
	public static final String EXCEPTION_NO_PERMISSIONS = "common.user.exception.right.noRight";

	/**
	 * 默认构造器
	 */
	public PrivilegeException() {
		super();
	}

	public PrivilegeException(String messageKey, Object[] args) {
		super(messageKey, args);
	}

	public PrivilegeException(String messageKey, Throwable cause) {
		super(messageKey, cause);
	}

	public PrivilegeException(String messageKey) {
		super(messageKey);
	}

	public PrivilegeException(Throwable cause) {
		super(cause);
	}
}
