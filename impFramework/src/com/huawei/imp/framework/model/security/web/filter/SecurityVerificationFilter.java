/**
 * 
 */
package com.huawei.imp.framework.model.security.web.filter;

import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.huawei.imp.framework.model.security.web.HttpServletResponseUtils;
import com.huawei.imp.framework.model.security.web.compenent.SecurityVerificationComponent;

/**
 * <p>
 * 安全加固过滤器
 * </p>
 * @see 
 * @author aohai.li
 * @version CMSV100R001DB0SP04, 2010-8-12
 * @since CMSV100R001DB0SP04
 */
public class SecurityVerificationFilter implements Filter {

	/**
	 * 验证器集合
	 */
	public static final List<SecurityVerificationComponent> securityVerificationComponents = Collections.synchronizedList(new LinkedList<SecurityVerificationComponent>());

	/**
	 * 静态方法
	 * @param component    注册验证组件
	 */
	public static void registSecurityVerificationComponent(SecurityVerificationComponent component){
		securityVerificationComponents.add(component);
	}
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		// 如果验证组建不为空，则进行安全校验
		if(!securityVerificationComponents.isEmpty()){
			HttpServletRequest httpServletRequest = (HttpServletRequest) arg0;
			HttpServletResponse httpServletResponse = (HttpServletResponse) arg1;
			
			// 迭代所有验证器
			for(SecurityVerificationComponent component : securityVerificationComponents){
				// 进行校验
				String ret = component.verify(httpServletRequest);
				// 如果验证失败
				if(null != ret){
					// 如果Session存在，则将session设置为不可用
					HttpSession session = httpServletRequest.getSession(false);
					if(null != session){
						session.invalidate();
					}
					
					// 重定向到首页
//					httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/index.jsp");
					HttpServletResponseUtils.writeResponse(ret, httpServletResponse);
					return;
				}
			}
		}
		// 验证通过，则将
		arg2.doFilter(arg0, arg1);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}
}
