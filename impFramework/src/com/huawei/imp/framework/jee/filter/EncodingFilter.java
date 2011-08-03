package com.huawei.imp.framework.jee.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import com.huawei.imp.framework.logger.LogFactory;
import com.huawei.imp.framework.logger.Logger;
import com.huawei.imp.framework.utils.EncodingDetectUtils;

/**
 * 编码过滤器，设置所有请求的编码为指定编码格式
 * @author ahli
 * @date 2009-8-5
 */
public class EncodingFilter implements Filter {
	
	/**
	 * 日志对象
	 */
	private static final Logger log = LogFactory.getLogger(EncodingFilter.class);
	
	private static final String DEFAULT_ENCODING_UTF8 = "UTF-8";
	
	// default to UTF-8
	private String targetEncoding = null;

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	public void init(FilterConfig config) throws ServletException {
		String _targetEncoding = config.getInitParameter("encoding");
		this.targetEncoding = StringUtils.isNotEmpty(_targetEncoding)? _targetEncoding : DEFAULT_ENCODING_UTF8;
	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#destroy()
	 */
	public void destroy() {}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	@SuppressWarnings("unchecked")
	public void doFilter(ServletRequest srequest, ServletResponse sresponse,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) srequest;
		request.setCharacterEncoding(targetEncoding);
		sresponse.setCharacterEncoding(targetEncoding);
//		if(log.isDebugEnabled())
//		{
//			log.debug("encoding ->" + targetEncoding);
//			StringBuilder sb = new StringBuilder();
//			Map<String, Object> parameterMap = (Map<String, Object>)request.getParameterMap();
//			sb.append("parameter ->[");
//			for(Map.Entry<String, Object> entry : parameterMap.entrySet())
//			{
//							
//				sb.append(entry.getKey() + ":");
//				if(null != entry.getValue())
//				{
//					boolean isArrayClass = entry.getValue().getClass().isArray();
//					if(isArrayClass)
//					{
//						String value = Arrays.toString((Object[])entry.getValue());
//						sb.append(value);
//						sb.append("<" + EncodingDetectUtils.getInstance().detectEncoding(value.getBytes()).getName() + ">");
//						sb.append(", ");
//					}
//				}
//				else
//				{
//					sb.append("null, ");
//				}
//				
//			}
//			sb.append("]");
//			log.debug(sb.toString());
//		}
		
		// 设置框架子页面，让IE浏览器支持cookie
		//HttpServletResponse httpResponse = (HttpServletResponse) sresponse;
		//httpResponse.setHeader("P3P","CP='IDC DSP COR ADM DEVi TAIi PSA PSD IVAi IVDi CONi HIS OUR IND CNT'");
		chain.doFilter(srequest, sresponse);
	}
}
