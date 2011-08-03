/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */

package com.huawei.imp.framework.jee;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.huawei.imp.framework.common.web.controller.BaseAjaxJsonController;
import com.huawei.imp.framework.exception.BusinessException;
import com.huawei.imp.framework.logger.LogFactory;
import com.huawei.imp.framework.logger.Logger;

/**
 * Description: CMS的http拦截请求处理适配器
 * 
 * @author ahli Apr 16, 2009
 */
public class BaseHandlerInterceptorAdapter extends HandlerInterceptorAdapter
	implements JEEConstant
{

	private static final Logger log = LogFactory.getLogger(BaseHandlerInterceptorAdapter.class);
	
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
		if (null != exception)
		{
			// 如果是业务异常，在非debug状态下，不打印日志。
			if (exception instanceof BusinessException)
			{
				if (log.isDebugEnabled())
				{
					log.error(exception.toString());
				}
			}
			// 非业务异常，在记录异常，在debug级别日志下，将会打印堆栈信息
			else
			{
				log.error(exception.toString());
				if (log.isDebugEnabled())
				{
					exception.printStackTrace();
				}
			}
		}
		
		super.afterCompletion(request, response, arg2, exception);
	}

	@Override
	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2) throws Exception
	{
		// AJAX-JSON类型
		if (arg2 instanceof BaseAjaxJsonController)
		{
			arg0.setAttribute(REQ_ATTR_CONTROLLER_TYPE,
					ControllerType.AJAX_JSON);
		}
		return super.preHandle(arg0, arg1, arg2);
	}

}
