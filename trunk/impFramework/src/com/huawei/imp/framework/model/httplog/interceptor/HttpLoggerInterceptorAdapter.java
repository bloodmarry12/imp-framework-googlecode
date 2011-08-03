/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */

package com.huawei.imp.framework.model.httplog.interceptor;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.huawei.imp.framework.jee.JEEConstant;
import com.huawei.imp.framework.jee.session.ServletSessionUtil;
import com.huawei.imp.framework.logger.LoggerUtil;
import com.huawei.imp.framework.model.httplog.HttpLogUtil;
import com.huawei.imp.framework.model.httplog.domain.HttpLoggerEntity;
import com.huawei.imp.framework.model.httplog.service.HttpLoggerService;
import com.huawei.imp.framework.utils.Constant;

/**
 * HTTP请求日志拦截器
 * @author ahli
 * @version IMPV100R001DA0, Oct 28, 2009 
 * @see org.springframework.web.servlet.handler.HandlerInterceptorAdapter
 * @since CMS IMPV100R001DA0
 */
public class HttpLoggerInterceptorAdapter extends HandlerInterceptorAdapter
	implements JEEConstant
{
	/**
	 * 日志实体前括号
	 */
	private static final String ST_BEGIN = "[";
	
	/**
	 * 日志实体后括号
	 */
	private static final String ST_END = "]";
	
	/**
	 * 日志键值对间隔符
	 */
	private static final String ST_EQ = ":";
	
	/**
	 * 排除列表
	 */
	private String excludePath = null;
	
	@Autowired
	@Qualifier("httpLoggerService")
	private HttpLoggerService service;
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.web.servlet.handler.HandlerInterceptorAdapter#afterCompletion(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse, java.lang.Object,
	 *      java.lang.Exception)
	 */
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object arg2, Exception exception)
			throws Exception
	{
		if (HttpLogUtil.isLogEnable())
		{
			HttpLoggerEntity log = HttpLogUtil.getCurHttpLoggerEntity();
			if (null != exception)
			{
				if (null != log.getDescription())
				{
					log.setDescription(log.getDescription() + ST_BEGIN
							+ exception.toString() + ST_END);
				}
				else
				{
					log.setDescription(exception.toString());
				}
			}
			log.setOperateTime((System.currentTimeMillis() - log.getOperateTime()));
			service.log(log);
		}
		super.afterCompletion(request, response, arg2, exception);
	}

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception
	{
		if(null != excludePath)
		{
			if (java.util.regex.Pattern.matches(excludePath, request
					.getRequestURI()))
			{
				return super.preHandle(request, response, handler);
			}
		}
		Long uid = ServletSessionUtil.getLoginID();
		HttpLoggerEntity log = new HttpLoggerEntity();
		log.setId(String.valueOf(LoggerUtil.getThreadLocalLog()));
		log.setSessionID(request.getSession(false) == null ? null
				: request.getSession().getId());
		log.setOperateDate(new Date());
		
		log.setParameters(map2String(request.getParameterMap()));
		log.setUrl(request.getRequestURI());
		log.setOperateTime(System.currentTimeMillis());
		log.setRemoteIP(request.getRemoteAddr());
		String lastUrl = request.getHeader("Referer");
		log.setLastUrl(lastUrl); // 从请求头中获取上一个请求地址
		HttpLogUtil.setCurHttpLoggerEntity(log);
		if (null != uid)
		{
			log.setUid(uid);
		}else{
			log.setUid(-1L);
		}
		return super.preHandle(request, response, handler);
	}
	
	@SuppressWarnings("unchecked")
	private static String map2String(Map map)
	{
		StringBuilder sb = new StringBuilder();
		sb.append(ST_BEGIN);
		boolean firstRun = true;
		for (Map.Entry entity : (Set<Map.Entry>) map.entrySet())
		{
			// 第二个参数开始，增加间隔符号
			if(firstRun)
			{
				firstRun = false;
			}
			else
			{
				sb.append(", ");
			}
			sb.append(Constant.LINE_SEPARATOR);
			sb.append(entity.getKey());
			sb.append(ST_EQ);
			if (entity.getValue().getClass().isArray())
			{
				sb.append(Arrays.toString((Object[]) entity.getValue()));
			}
			else
			{
				sb.append(entity.getValue());
			}
		}
		sb.append(ST_END);
		return sb.toString();
	}

	/**
	 * @param excludePath the excludePath to set
	 */
	public void setExcludePath(String excludePath)
	{
		this.excludePath = excludePath;
	}
}
