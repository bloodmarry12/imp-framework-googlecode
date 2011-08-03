/**
 * 
 */
package com.huawei.imp.framework.common.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * 观察者工具对象
 * 业务逻辑对象直接使用该类完成观察者模式
 * @author ahli
 * @version IMPV100R001DA0, 2009-12-16
 * @since CMS IMPV100R001DA0
 */
public class ObserverUtil<T>
{
	
//	private final static Logger log = LogFactory.getLogger(ObserverUtil.class);
	
	/**
	 * 观察者对象集合
	 */
	private final List<Observer<T>> observerArrays = new ArrayList<Observer<T>>();
	
	/**
	 * 注册观察者
	 * @param observer
	 */
	public void registObserver(Observer<T> observer)
	{
		observerArrays.add(observer);
	}
	
	/**
	 * 通知所有观察者
	 * @param t
	 */
	public void notifyObserver(T t)
	{
		for (Observer<T> observer : observerArrays)
		{
			observer.notify(t);
		}
	}
}
