package com.huawei.imp.framework.model.privilege.aspectj;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.annotation.Order;

import com.huawei.imp.framework.logger.LogFactory;
import com.huawei.imp.framework.logger.Logger;
import com.huawei.imp.framework.reload.ReloadSupport;

/**
 * <p>
 * 权限缓存刷新拦截切面
 * </p>
 * @see org.aspectj.lang.annotation.Aspect
 * @author aohai.li
 * @version CMSV100R001DB0SP04, 2010-8-17
 * @since CMSV100R001DB0SP04
 */
@Aspect
public class PrivilegeCacheModelAspectj
{
	/**
	 * 日志对象
	 */
	private static final Logger log = LogFactory.getLogger(PrivilegeCacheModelAspectj.class);
	
	@Autowired
	@Qualifier("privilegeService")
	private ReloadSupport privilegeService;
	
	@SuppressWarnings("unused")
	@Pointcut("execution(public * com.huawei.imp.framework.model.privilege.service..save*(..))")
	private void priSaveMethod(){}
	
	@SuppressWarnings("unused")
	@Pointcut("execution(public * com.huawei.imp.framework.model.privilege.service..update*(..))")
	private void priUpdateMethod(){}
	
	@SuppressWarnings("unused")
	@Pointcut("execution(public * com.huawei.imp.framework.model.privilege.service..reject*(..))")
	private void priRejMethod(){}
	
	@SuppressWarnings("unused")
	@Pointcut("execution(public * com.huawei.imp.framework.model.privilege.service..stop*(..))")
	private void priStopMethod(){}
	
	@SuppressWarnings("unused")
	@Pointcut("execution(public * com.huawei.imp.framework.model.privilege.service..remove*(..))")
	private void priRemoveMethod(){}
	
	@SuppressWarnings("unused")
	@Pointcut("execution(public * com.huawei.imp.framework.model.privilege.service..approve*(..))")
	private void priApproveMethod(){}
	
	@SuppressWarnings("unused")
	@Pointcut("execution(public * com.huawei.imp.framework.model.privilege.service..active*(..))")
	private void priActiveMethod(){}
	
	@SuppressWarnings("unused")
	@Pointcut("execution(public * com.huawei.imp.framework.model.privilege.service..change*(..))")
	private void priChangeMethod(){}
	
	/**
	 * 刷新缓存
	 */
	@AfterReturning("priSaveMethod() || priUpdateMethod() " +
			"|| priRejMethod() || priStopMethod() " +
			"|| priRemoveMethod() || priApproveMethod() " +
			"|| priActiveMethod() || priChangeMethod() ")
	@Order(value = 1)
	public void flushCacheModel(){
		if(log.isDebugEnabled()){
			log.debug("invoke -> flushCacheModel()");
		}
		privilegeService.reload();
	}
}
