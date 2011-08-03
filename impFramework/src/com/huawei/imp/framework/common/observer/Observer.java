/**
 * 
 */
package com.huawei.imp.framework.common.observer;

/**
 * 业务观察者接口
 * 各个业务需要在被观察的业务发生时获取通知，需要实现该接口，并且将对象注入到被观察者中。
 * @author ahli
 * @version IMPV100R001DA0, 2009-12-16
 * @since CMS IMPV100R001DA0
 */
public interface Observer<T> {

	/**
	 * 被观察对象发生变化时，主动调用观察者的的方法入口;
	 * 如果该方法抛出异常，将会导致该观察者中集合中的其他观察者无法被通知到。
	 * 切记，根据具体业务做好异常处理。
	 * @param t
	 */
	void notify(T t);
}
