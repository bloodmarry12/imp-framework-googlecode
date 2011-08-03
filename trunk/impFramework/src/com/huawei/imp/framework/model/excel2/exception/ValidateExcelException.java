package com.huawei.imp.framework.model.excel2.exception;

/**
 * Description:
 * Excel模板工具底层异常
 * @author ahli
 * Apr 30, 2009
 * 
 */
@SuppressWarnings("serial")
public class ValidateExcelException extends ExcelException
{

	/**
	 * 默认构造函数
	 */
	public ValidateExcelException()
	{
	}

	/**
	 * 构造Excel异常
	 * @param message	异常消息
	 */
	public ValidateExcelException(String message)
	{
		super(message);
	}

	/**
	 * 构造Excel异常
	 * @param cause		异常
	 */
	public ValidateExcelException(Throwable cause)
	{
		super(cause);
	}

	/**
	 * 构造Excel异常
	 * @param message	异常消息
	 * @param cause		异常
	 */
	public ValidateExcelException(String message, Throwable cause)
	{
		super(message, cause);
	}

}
