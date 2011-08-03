package com.huawei.imp.framework.utils.http;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 * Http请求方法
 * @author ahli
 * @version IMPV100R001DA0, Apr 28, 2009
 * @since CMS IMPV100R001DA0
 */
public class HttpInvoke
{

	/**
	 * 日志对象
	 */
	private static final Logger runLog = Logger.getLogger(HttpInvoke.class);
	
	/**
	 * 以Post方式发起HTTP请求；
	 * @param url          服务器端地址
	 * @param params       请求参数
	 * @return 返回的HTML字符串
	 * @throws HttpException   httpClient.executeMethod 抛出HttpException
	 * @throws IOException     postMethod.getResponseBodyAsString 抛出IOException
	 */
	public static String postData(String url, Map<String, Object> params)
			throws HttpException, IOException
	{
		return postData(url, params, null);
	}
	
	/**
	 * 以Post方式发起HTTP请求；
	 * @param url          服务器端地址
	 * @param params       请求参数
	 * @param timeOut      请求超时时间
	 * @return 返回的HTML字符串
	 * @throws HttpException   httpClient.executeMethod 抛出HttpException
	 * @throws IOException     postMethod.getResponseBodyAsString 抛出IOException
	 */
	public static String postData(String url, Map<String, Object> params, Integer timeOut)
			throws HttpException, IOException
	{
		return postData(url, params, null, timeOut);
	}
	
	/**
	 * 以Post方式发起HTTP请求；
	 * @param url          服务器端地址
	 * @param params       请求参数
	 * @param sessionID    会话ID
	 * @param timeOut      请求超时时间
	 * @return 返回的HTML字符串
	 * @throws HttpException   httpClient.executeMethod 抛出HttpException
	 * @throws IOException     postMethod.getResponseBodyAsString 抛出IOException
	 */
	public static String postData(String url, Map<String, Object> params,
			String sessionID, Integer timeOut) throws HttpException, IOException
	{
		String ret = null;
		EnhanceHttpClient httpClient = new EnhanceHttpClient();
		
		if (null != timeOut && timeOut.intValue() > 0)
		{
			// 设置超时时间
			httpClient.getParams().setSoTimeout(timeOut);
		}
		
		UTF8PostMethod postMethod = new UTF8PostMethod(url);

		NameValuePair[] data = new NameValuePair[params.size()];
		Set<Map.Entry<String, Object>> entrys = params.entrySet();
		int index = 0;
		for (Map.Entry<String, Object> entry : entrys)
		{
			data[index++] = new NameValuePair(entry.getKey(), String
					.valueOf(entry.getValue()));
		}
		
		//如果会话ID存在，则在请求头里面增加会话ID
		if (StringUtils.isNotBlank(sessionID))
		{
			postMethod.setRequestHeader("Cookie", "JSESSIONID=" + sessionID);
		}

		postMethod.setRequestBody(data);
		int statusCode = 0;
		statusCode = httpClient.executeMethod(postMethod);
		if (statusCode == HttpStatus.SC_MOVED_PERMANENTLY
				|| statusCode == HttpStatus.SC_MOVED_TEMPORARILY)
		{
			Header locationHeader = postMethod.getResponseHeader("location");
//			String location = null;
			if (locationHeader != null)
			{
//				location = locationHeader.getValue();
//				System.out.println("The page was redirected to:" + location);
			}
			else
			{
				System.err.println("Location field value is null.");
			}
		}
		else
		{
//			InputStream is = null;
//			BufferedReader in = null;
			try
			{
				ret = postMethod.getResponseBodyAsString();
				if(runLog.isDebugEnabled()){
					runLog.debug("ret: " + ret);
				}
			}
			finally
			{
				postMethod.releaseConnection();
			}
		}
		

		if (null == ret || "".equals(ret))
		{
			runLog.error("ret is null or empty.");
		}
		return ret;
	}
}
