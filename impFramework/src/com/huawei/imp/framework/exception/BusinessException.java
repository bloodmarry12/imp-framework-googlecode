/**
 * 
 */
package com.huawei.imp.framework.exception;

import com.huawei.imp.framework.utils.BeanHolder;

/**
 * Description:
 * 业务异常
 * @author ahli
 * Apr 8, 2009
 * 
 */
public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public BusinessException() {
		super();
	}

	/**
	 * 业务异常
	 * @param messageKey	资源Key
	 * @param args			参数
	 */
	public BusinessException(String messageKey, Object[] args){
		super(BeanHolder.getMessage(messageKey, args));
	}
	
	/**
	 * @param messageKey
	 * @param cause
	 */
	public BusinessException(String messageKey, Throwable cause) {
		super(BeanHolder.getMessage(messageKey), cause);
	}

	/**
	 * @param messageKey
	 */
	public BusinessException(String messageKey) {
		super(BeanHolder.getMessage(messageKey));
	}

	/**
	 * @param cause
	 */
	public BusinessException(Throwable cause) {
		super(cause);
	}

//	public String getExceptionTypeName() {
//		return exceptionTypeName;
//	}
//
//	public void setExceptionTypeName(String exceptionTypeName) {
//		this.exceptionTypeName = exceptionTypeName;
//	}
}
