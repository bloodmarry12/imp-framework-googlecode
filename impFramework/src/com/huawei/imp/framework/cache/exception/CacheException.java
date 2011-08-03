/*
 * FileName: CacheException.java
 * Author:   ahli
 * createDate: 2009-6-6
 * Description: 
 * 
 */
package com.huawei.imp.framework.cache.exception;

/**
 * 缓存异常
 * @author ahli
 * @version IMPV100R001DA0, Jun 10, 2009 
 * @see java.lang.RuntimeException
 * @since CMS IMPV100R001DA0
 */
public class CacheException extends RuntimeException {

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = -673161570307871892L;

	/**
	 * 默认构造
	 */
	public CacheException() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 构造函数
	 * @param message    异常消息
	 */
	public CacheException(String message) {
		super(message);
	}

	/**
	 * 构造函数
	 * @param cause    异常
	 */
	public CacheException(Throwable cause) {
		super(cause);
	}

	/**
	 * 构造函数
	 * @param message    消息
	 * @param cause      异常
	 */
	public CacheException(String message, Throwable cause) {
		super(message, cause);
	}
}
