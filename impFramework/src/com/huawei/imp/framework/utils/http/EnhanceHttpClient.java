package com.huawei.imp.framework.utils.http;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpConnectionManager;
import org.apache.commons.httpclient.params.HttpClientParams;

public class EnhanceHttpClient extends HttpClient {
	
	private int connectionTimeout = 5;
	private int soTimeout = 5;
	
	public EnhanceHttpClient()
	{
		super();
		enhanceSetTimeout(connectionTimeout,soTimeout);
	}
	
	public EnhanceHttpClient(HttpClientParams params)
	{
		super(params);
		enhanceSetTimeout(connectionTimeout,soTimeout);
	}
	
	public EnhanceHttpClient(HttpClientParams params, HttpConnectionManager httpConnectionManager)
	{
		super(params,httpConnectionManager);
		enhanceSetTimeout(connectionTimeout,soTimeout);
	}
	
	public EnhanceHttpClient(HttpConnectionManager httpConnectionManager)
	{
		super(httpConnectionManager);
		enhanceSetTimeout(connectionTimeout,soTimeout);
	}
	
	/**
	 * 设置超时时间
	 * 连接超时：connectionTimeout ，单位秒
	 * 读取数据超时：soTimeout ， 单位秒
	 */
	public void enhanceSetTimeout(int connectionTimeout,int soTimeout)
	{
		getHttpConnectionManager().getParams().setConnectionTimeout(connectionTimeout*1000); 
		getHttpConnectionManager().getParams().setSoTimeout(soTimeout*1000); 
		getParams().setBooleanParameter("http.protocol.expect-continue", false);
	}

}
