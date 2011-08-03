/**
 * 
 */
package com.huawei.imp.framework.model.security.web.compenent.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.huawei.imp.framework.jee.webserver.Environment;
import com.huawei.imp.framework.logger.LogFactory;
import com.huawei.imp.framework.logger.Logger;
import com.huawei.imp.framework.model.security.dao.SecurityVerificationDAO;
import com.huawei.imp.framework.model.security.web.compenent.SecurityVerificationComponent;
import com.huawei.imp.framework.model.security.web.filter.SecurityVerificationFilter;
import com.huawei.imp.framework.reload.ReloadSupport;
import com.huawei.imp.framework.utils.Constant;

/**
 * <p>
 * 会话请求访问限制组建
 * </p>
 * @see 
 * @author aohai.li
 * @version CMSV100R001DB0SP04, 2010-8-12
 * @since CMSV100R001DB0SP04
 */
@Component
public class SessionRequestLimit implements SecurityVerificationComponent, ReloadSupport {

	private static final Logger log = LogFactory.getLogger(SessionRequestLimit.class);
	
	/**
	 * 安全验证数据访问接口
	 */
	@Autowired
	@Qualifier("FK_SecurityVerificationDAO")
	private SecurityVerificationDAO securityVerificationDAO;
	
	@Autowired
	private Environment environment;
	
	/**
	 * 
	 */
	private Map<String, SecurityVerification> securityVerificationMap = new HashMap<String, SecurityVerification>(500);
	
	/**
	 * 初始化验证其：<Br/>
	 * 1-加载数据库配置<Br/>
	 * 2-将该组件组测到Filter中。
	 * 3-启动队列清理线程
	 */
	@PostConstruct
	public void init(){
		if(log.isDebugEnabled()){
			log.debug("init()");
		}
		this.reload();
		SecurityVerificationFilter.registSecurityVerificationComponent(this);
		if(log.isDebugEnabled()){
			log.debug("SecurityVerificationFilter.registSecurityVerificationComponent(this) -> ok.");
		}
	}
	
	/* (non-Javadoc)
	 * @see com.huawei.imp.framework.model.security.web.compenent.SecurityVerificationComponent#verify(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public String verify(HttpServletRequest request) {
		HttpSession s = request.getSession(false);
		if(null != s){
			String uri = request.getRequestURI();
			SecurityVerification securityVerification = securityVerificationMap.get(uri);
			// 表示取得了安全验证器
			if(null != securityVerification){
				
				// 创建一个session监听，然后放入到session中
				if(null == s.getAttribute("security.securityVerification.SessionRequestLimit")){
					s.setAttribute("security.securityVerification.SessionRequestLimit", new SessionListener(securityVerification));
				}
				
				boolean ret = securityVerification.sign(s.getId());
				if(!ret){
					if(log.isWarnEnabled()){
						StringBuilder sb = new StringBuilder(400);
						sb.append("URI:" + uri).append(Constant.LINE_SEPARATOR);
						sb.append("SessionID:" + s.getId()).append(Constant.LINE_SEPARATOR);
						sb.append("RefereURL:" + request.getHeader("Referer")).append(Constant.LINE_SEPARATOR);
						sb.append("RemoteAddr:" + request.getRemoteAddr()).append(Constant.LINE_SEPARATOR);
						sb.append("RemoteHost:" + request.getRemoteHost()).append(Constant.LINE_SEPARATOR);
						sb.append("RemotePort:" + request.getRemotePort()).append(Constant.LINE_SEPARATOR);
						sb.append("parameters:" + map2String(request.getParameterMap()));
						log.warn(sb.toString());
					}
					return "The request has bean rejected by the CMS SecurityVerification filter.";
				}
			}
		}
		return null;
	}
	
	/* (non-Javadoc)
	 * @see com.huawei.imp.framework.reload.ReloadSupport#reload()
	 */
	@Override
	public void reload() {
		if(log.isDebugEnabled()){
			log.debug("reload()");
		}
		
		// 重新加载数据库中的配置项
		List<Map<String, Object>> list = securityVerificationDAO.querySecurityVerificationForList(null);
		
		Map<String, SecurityVerification> _securityVerificationMap = new HashMap<String, SecurityVerification>(500);
		
		for(Map<String, Object> record : list){
			final String id = (String)record.get("id");
			final String uri = (String)record.get("uri");
			final String limittype = (String)record.get("limittype");
			final Long limittime = (Long)record.get("limittime");
			final Integer limitnum = (Integer)record.get("limitnum");
			SecurityVerification sv = new SecurityVerification();
			sv.setId(id);
			sv.setLimitNum(limitnum);
			sv.setLimitTime(limittime);
			sv.setUri("/" + environment.getLocalContext() + uri);
			sv.setLimitType(limittype);
			_securityVerificationMap.put(sv.getUri(), sv);
			
		}
		// 刷新缓存，通过同步块
		this.securityVerificationMap = _securityVerificationMap;
	}
	
	/**
	 * 将Map转换为字符串用于记录日志
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private static String map2String(Map map)
	{
		StringBuilder sb = new StringBuilder();
		sb.append("[");
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
			sb.append(": ");
			if (entity.getValue().getClass().isArray())
			{
				sb.append(Arrays.toString((Object[]) entity.getValue()));
			}
			else
			{
				sb.append(entity.getValue());
			}
		}
		sb.append("]");
		return sb.toString();
	}
	
	/**
	 * <p>
	 * 
	 * </p>
	 * @see 
	 * @author aohai.li
	 * @version CMSV100R001DB0SP0, 2010-8-25
	 * @since CMSV100R001DB0SP0
	 */
	class SessionListener implements HttpSessionBindingListener {

		/**
		 * 安全验证接口
		 */
		private SecurityVerification sv;
		
		/**
		 * 
		 * @param sv
		 */
		public SessionListener(SecurityVerification sv){
			this.sv = sv;
		}
		
		/* (non-Javadoc)
		 * @see javax.servlet.http.HttpSessionBindingListener#valueBound(javax.servlet.http.HttpSessionBindingEvent)
		 */
		@Override
		public void valueBound(HttpSessionBindingEvent arg0) {
			// 不做任何事情
		}

		/* (non-Javadoc)
		 * @see javax.servlet.http.HttpSessionBindingListener#valueUnbound(javax.servlet.http.HttpSessionBindingEvent)
		 */
		@Override
		public void valueUnbound(HttpSessionBindingEvent arg0) {
			final String sessionID = arg0.getSession().getId();
			// 从缓存中移除该sessionID的记录
			sv.clear(sessionID);
		}
	}
}
