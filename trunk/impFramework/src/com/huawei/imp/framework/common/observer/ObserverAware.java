/**
 * 
 */
package com.huawei.imp.framework.common.observer;

/**
 * 业务观察者模式,观察者感知接口
 * 业务对象实现该接口，
 * @author ahli
 * @version IMPV100R001DA0, 2009-12-16
 * @see 
 * @since CMS IMPV100R001DA0
 */
public interface ObserverAware<T>
{
	/**
	 * 注册业务观察者
	 * @param <T>         观察者调用参数
	 * @param observer    观察者
	 */
	void registObserver(Observer<T> observer);
}
