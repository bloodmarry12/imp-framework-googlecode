package com.huawei.imp.framework.logger;


/**
 * 日志接口
 * @author ahli
 * @date 2009-8-12
 */
public interface LogEnabled
{
	/**
	 * debug级别日志是否开启
	 * @return
	 */
	public boolean isDebugEnabled();

	/**
	 * error级别日志是否开启
	 * @return
	 */
	public boolean isErrorEnabled();

	/**
	 * info级别日志是否开启
	 * @return
	 */
	public boolean isInfoEnabled();

	/**
	 * warn级别日志是否开启
	 * @return
	 */
	public boolean isWarnEnabled();
	
}
