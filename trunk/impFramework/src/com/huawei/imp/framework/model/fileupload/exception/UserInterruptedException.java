package com.huawei.imp.framework.model.fileupload.exception;

/**
 * 用户取消文件上传抛出该异常
 * @author heguang
 *
 */
public class UserInterruptedException extends RuntimeException
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4016993078064227115L;
	
	public UserInterruptedException()
	{
		super();
	}
	
	public UserInterruptedException(String message)
	{
		super(message);
	}
}
