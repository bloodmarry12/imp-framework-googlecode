package com.huawei.imp.framework.jee.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.huawei.imp.framework.logger.LoggerUtil;

/**
 * 编码过滤器，设置所有请求的编码为指定编码格式
 * <功能描述/>
 * @author ahli
 * @date 2009-8-5
 */
public class LoginIDFilter implements Filter {

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	public void init(FilterConfig config) throws ServletException {
	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#destroy()
	 */
	public void destroy() {
	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	public void doFilter(ServletRequest srequest, ServletResponse sresponse,
			FilterChain chain) throws IOException, ServletException {
		// 进入http请求，增加一个线程ID
		LoggerUtil.createThreadLocalLog();
		chain.doFilter(srequest, sresponse);
	}
}
