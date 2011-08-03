package com.huawei.imp.framework.jee.filter;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import com.huawei.imp.framework.jee.JEEConstant;
import com.huawei.imp.framework.jee.session.ServletSessionUtil;
import com.huawei.imp.framework.jee.webserver.Environment;
import com.huawei.imp.framework.logger.LogFactory;
import com.huawei.imp.framework.logger.Logger;
import com.huawei.imp.framework.utils.BeanHolder;

/**
 * Description: 用户登录验证过滤器 如果未登录系统，则直接跳转到登录页面
 * 
 * @author ahli Apr 12, 2009
 */
public class SessionLoginFilter implements Filter, JEEConstant {

	private static final Logger log = LogFactory.getLogger(SessionLoginFilter.class);
	
	/**
	 * 业务路径
	 */
	private String excludeUrlStr;
	
	/**
	 * 前台session过期返回页面，默认返回index.jsp页面
	 */
	private String returnUrl = "/index.jsp";
	
	/**
	 * 框架路径
	 */
	private String excludeFrameworkUrlStr;
	
	/**
	 * 后台session过期返回页面，默认返回/framework/index.do
	 */
	private String returnFrameworkUrl = "/framework/index.do";

	/**
	 * 配置排除的路径地址
	 */
	private Map<String,String>  excludeUrlMap = new ConcurrentHashMap<String,String>();
	
	/**
	 * 框架Session过滤，排除路径
	 */
	private Map<String,String>  excludeFrameworkUrlMap = new ConcurrentHashMap<String,String>();

	/**
	 * 基本排除路径
	 */
	private Map<String,String>  baseExcludeUrlMap = new ConcurrentHashMap<String,String>();

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;

		// 当前访问的路径
		final String curURL = httpServletRequest.getRequestURI();
		// 截取发布服务的根路径 
		Environment env = BeanHolder.getBean("environment");
		String context = env.getLocalContext();
		if("/".equals(context)){
			context = "";
		}
		final String currentURL = curURL.substring(context.length(),
				curURL.length());
		
		boolean sessionFlag = false;
		boolean sessionFrameworkFlag = false;
		
		HttpSession session = httpServletRequest.getSession(false);
		if(null != session){
			sessionFlag = validateSession(session, SESSION_LOGIN_TOKEN);
			sessionFrameworkFlag = validateFrameworkSession(session, SESSION_FRAMEWORK_LOGIN_TOKEN);
			
			if(log.isDebugEnabled()){
				log.debug("sessionFlag=" + sessionFlag + 
						",sessionFrameworkFlag=" + sessionFrameworkFlag + 
						" curURL:[" + curURL + "], currentURL:[" + currentURL + "]");
			}
		}

		// 如果访问的是基本路径，则直接调用
		if(excludeURL(currentURL, baseExcludeUrlMap)){
			chain.doFilter(request, response);
		}else if(currentURL.startsWith("/framework/")){// 进行路径判断，路径访问是否是指向的framework
			if (excludeURL(currentURL, excludeFrameworkUrlMap)){
				chain.doFilter(request, response);
			}else{
				if(sessionFrameworkFlag){
					chain.doFilter(httpServletRequest, httpServletResponse);
				}else{
					httpServletResponse.sendRedirect(httpServletRequest
							.getContextPath()
							+ returnFrameworkUrl);
				}
			}
		}else {
			if (excludeURL(currentURL, excludeUrlMap)){
				chain.doFilter(request, response);
			}else{
				if(sessionFlag){
					chain.doFilter(httpServletRequest, httpServletResponse);
				}else{
					httpServletResponse.sendRedirect(httpServletRequest
							.getContextPath()
							+ returnUrl);
				}
			}
		}
	}

	private boolean validateSession(HttpSession session, String token) {
		Long loginID = (Long)session.getAttribute(token);
		if(null != loginID){
			ServletSessionUtil.setSessionUserInfo(loginID, "password");
			return true;
		}else{
			return false;
		}
	}
	
	private boolean validateFrameworkSession(HttpSession session, String token){
		Long loginID = (Long)session.getAttribute(token);
		if(null != loginID){
			return true;
		}else{
			return false;
		}
	}
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
		excludeUrlStr = filterConfig.getInitParameter("excludeUrl");
		if (StringUtils.isNotBlank(excludeUrlStr)) {
			String[] excludeUrlStrArray = excludeUrlStr.split(",");
			
			for (int i = 0; i < excludeUrlStrArray.length; i++) {
				excludeUrlMap.put(excludeUrlStrArray[i].trim(), excludeUrlStrArray[i].trim());
			}
		}
		
		excludeFrameworkUrlStr = filterConfig.getInitParameter("excludeFrameworkUrl");
		if (StringUtils.isNotBlank(excludeFrameworkUrlStr)) {
			String[] excludeFrameworkUrlStrArray = excludeFrameworkUrlStr.split(",");
			
			for (int i = 0; i < excludeFrameworkUrlStrArray.length; i++) {
				excludeFrameworkUrlMap.put(excludeFrameworkUrlStrArray[i].trim(), excludeFrameworkUrlStrArray[i].trim());
			}
		}
		
		String _returnUrl = filterConfig.getInitParameter("returnUrl");
		if(StringUtils.isNotEmpty(_returnUrl)){
			returnUrl = _returnUrl;
		}
		
		String _returnFrameworkUrl = filterConfig.getInitParameter("returnFrameworkUrl");
		if(StringUtils.isNotEmpty(_returnFrameworkUrl)){
			returnFrameworkUrl = _returnFrameworkUrl;
		}
		
		
		// 构造基础排除列表
		baseExcludeUrlMap.put("/", "/");
		baseExcludeUrlMap.put("/index.jsp", "/index.jsp");
		baseExcludeUrlMap.put(returnUrl, returnUrl);
		baseExcludeUrlMap.put(returnFrameworkUrl, returnFrameworkUrl);

	}

	private boolean excludeURL(String url, Map<String,String> excludeUrlMap) {
		String value = excludeUrlMap.get(url);
        return value ==null ? false:true;
	}

	@Override
	public void destroy() {

	}
}
