/*
 * 文件名：FrameworkBusinessException.java
 * 版权：  Copyright 2000-2009 Huawei Tech. Co. Ltd. All Rights Reserved.
 * 描述：〈描述〉
 * 修改人：ahli
 * 修改时间：Sep 5, 2009
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.huawei.imp.framework.exception;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author ahli
 * @version IMPV100R001DA0, Sep 5, 2009 
 * @see [相关类/方法]
 * @since CMS IMPV100R001DA0
 */

public class FrameworkBusinessException extends BusinessException
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FrameworkBusinessException()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	public FrameworkBusinessException(String messageKey, Object[] args)
	{
		super(messageKey, args);
		// TODO Auto-generated constructor stub
	}

	public FrameworkBusinessException(String messageKey, Throwable cause)
	{
		super(messageKey, cause);
		// TODO Auto-generated constructor stub
	}

	public FrameworkBusinessException(String messageKey)
	{
		super(messageKey);
		// TODO Auto-generated constructor stub
	}

	public FrameworkBusinessException(Throwable cause)
	{
		super(cause);
	}
}
