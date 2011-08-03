/**
 * 
 */
package com.huawei.imp.framework.model.privilege.observer;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.huawei.imp.framework.common.observer.Observer;
import com.huawei.imp.framework.common.observer.ObserverAware;
import com.huawei.imp.framework.model.clusters.service.ClustersService;
import com.huawei.imp.framework.model.clusters.service.ClustersSupport;
import com.huawei.imp.framework.model.privilege.service.PrivilegeService;

/**
 * 用户权限维护观察者,并将变化通知到所有集群服务器，最终刷新服务器对应配置项信息。
 * @author ahli
 * @version IMPV100R001DA0, 2009-12-30
 * @see com.huawei.imp.framework.common.observer.Observer<String>
 * @since CMS IMPV100R001DA0
 */
public class PrivilegeServiceEditObserver implements Observer<String>,ClustersSupport
{

	/**
	 * 配置关键字
	 */
	public static final String CLUSTERS_KEY_PRIVILEGE = "framework.privilege";
	
	@Autowired
	@Qualifier("privilegeService")
	private PrivilegeService privilegeService;
	
	@Autowired
	@Qualifier("privilegeService")
	private ObserverAware<String> observerAware;
	
	@Autowired
	@Qualifier("clustersService")
	private ClustersService clustersService;
	
	@PostConstruct
	public void init(){
		observerAware.registObserver(this);
		clustersService.registClustersSupport(CLUSTERS_KEY_PRIVILEGE, this);
	}
	
	/* (non-Javadoc)
	 * @see com.huawei.imp.framework.common.observer.Observer#notify(java.lang.Object)
	 */
	@Override
	public void notify(String t)
	{
		clustersService.flushSlaveDevice(CLUSTERS_KEY_PRIVILEGE);
	}

	/* (non-Javadoc)
	 * @see com.huawei.imp.framework.model.clusters.service.ClustersSupport#invokeByMaster()
	 */
	@Override
	public void invokeByMaster()
	{
		privilegeService.refreshCacheModle();
	}
}
