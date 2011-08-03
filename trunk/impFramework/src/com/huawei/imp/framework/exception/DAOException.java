/**
 * 
 */
package com.huawei.imp.framework.exception;

/**
 * Description:
 * 数据访问异常
 * @author ahli
 * Apr 9, 2009
 * 
 */
public class DAOException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public DAOException()
	{
		super();
	}

	public DAOException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public DAOException(String message)
	{
		super(message);
	}

	public DAOException(Throwable cause)
	{
		super(cause);
	}
}
