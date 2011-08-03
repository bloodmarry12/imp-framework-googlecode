package com.huawei.imp.framework.jee.filter;

import java.io.IOException;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.huawei.imp.framework.config.ConfigUtil;
import com.huawei.imp.framework.jee.JEEConstant;
import com.huawei.imp.framework.logger.LogFactory;
import com.huawei.imp.framework.logger.Logger;

/**
 * Description: 用户登录验证过滤器 如果未登录系统，则直接跳转到登录页面
 * 
 * @author ahli Apr 12, 2009
 */
public class RemotingHessianFilter implements Filter, JEEConstant {

	/**
	 * 日志对象
	 */
	private static final Logger log = LogFactory.getLogger(RemotingHessianFilter.class);
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;

		// 允许访问的客户端地址正则表达式
		String localReg = ConfigUtil.getSysConfigValue("imp.framework.clusters.addressdomain.reg");
		
		// 客户端IP
		String remoteAddr = httpServletRequest.getRemoteAddr();
		
		if(log.isDebugEnabled()){
			log.debug("reg:[" + localReg + "], remoteAddr:[" + remoteAddr + "]");
		}
		// 如果本地的服务地址
		if(StringUtils.isNotEmpty(localReg) && Pattern.matches(localReg, remoteAddr))
		{
			chain.doFilter(request, response);
		}
		else
		{
			log.warn("reg:[" + localReg + "], remoteAddr:[" + remoteAddr + "]");
			httpServletResponse.sendRedirect(httpServletRequest // 不满足条件跳出
					.getContextPath() + "/index.jsp");
		}		
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	@Override
	public void destroy() {

	}
}
