package com.huawei.imp.framework.utils.http;

import org.apache.commons.httpclient.methods.PostMethod;



/**
 * UTF-8的post提交方法
 * @author ahli
 * @version IMPV100R001DA0, Jun 11, 2009 
 * @see org.apache.commons.httpclient.methods.PostMethod
 * @since CMS IMPV100R001DA0
 */
public class UTF8PostMethod extends PostMethod
{
	
	public static final String ENCODE_UTF8 = "UTF-8";
	
	/**
	 * 默认构造函数
	 * @param url    地址
	 */
	public UTF8PostMethod(String url)
	{
		super(url);
		addRequestHeader("Connection", "close");
	}

	/* (non-Javadoc)
	 * @see org.apache.commons.httpclient.methods.EntityEnclosingMethod#getRequestCharSet()
	 */
	@Override
	public String getRequestCharSet()
	{
		return ENCODE_UTF8;
	}

	/* (non-Javadoc)
	 * @see org.apache.commons.httpclient.HttpMethodBase#getResponseCharSet()
	 */
	@Override
	public String getResponseCharSet()
	{
		return ENCODE_UTF8;
	}
}
