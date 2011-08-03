package com.huawei.imp.framework.model.security.web.compenent;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 安全验证组建
 * </p>
 * @see 
 * @author aohai.li
 * @version CMSV100R001DB0SP0, 2010-8-12
 * @since CMSV100R001DB0SP0
 */
public interface SecurityVerificationComponent {

	/**
	 * 安全验证
	 * @param request    请求对象
	 * @return
	 * 验证结果，如果为null，表示验证通过，否则返回错误信息
	 */
	String verify(HttpServletRequest request);
	
}
