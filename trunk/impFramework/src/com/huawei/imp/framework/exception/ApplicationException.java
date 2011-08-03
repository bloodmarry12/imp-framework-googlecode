/**
 * 
 */
package com.huawei.imp.framework.exception;

/**
 * <一句话功能/>
 * <功能描述/>
 * @author ahli
 * @date 2009-8-12
 */
public class ApplicationException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6644316837084760498L;

	/**
	 * 
	 */
	public ApplicationException() {
	}

	/**
	 * @param message
	 */
	public ApplicationException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public ApplicationException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public ApplicationException(String message, Throwable cause) {
		super(message, cause);
	}
}
