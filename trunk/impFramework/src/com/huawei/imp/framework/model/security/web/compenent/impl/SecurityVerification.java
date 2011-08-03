package com.huawei.imp.framework.model.security.web.compenent.impl;

import java.io.Serializable;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * <p>
 * 安全验证对象
 * </p>
 * @see java.io.Serializable
 * @author aohai.li
 * @version CMSV100R001DB0SP04, 2010-8-16
 * @since CMSV100R001DB0SP04
 */
public class SecurityVerification implements Serializable {

	/**
	 * 默认的序列版本ID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 请求双向队列，保存请求时间
	 * key:sessionID, value:该session请求时间队列
	 */
	private Map<String, Deque<Long>> requestQueueMap = new HashMap<String, Deque<Long>>(200);
	
	/**
	 * ID
	 */
	private String id;
	
	/**
	 * 请求服务地址
	 */
	private String uri;
	
	/**
	 * 限制类型
	 */
	private String limitType;
	
	/**
	 * 限制时间
	 */
	private long limitTime = 0;
	
	/**
	 * 限制次数
	 */
	private int limitNum = 2;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getLimitType() {
		return limitType;
	}

	public void setLimitType(String limitType) {
		this.limitType = limitType;
	}

	public long getLimitTime() {
		return limitTime;
	}

	public void setLimitTime(long limitTime) {
		this.limitTime = limitTime;
	}

	public int getLimitNum() {
		return limitNum;
	}

	public void setLimitNum(int limitNum) {
		this.limitNum = limitNum;
	}
	
	/**
	 * 记录session最新访问时间，并且判断是否违反了安全限制
	 * @return    true-未违反安全限制；false-违反了安全限制
	 */
	public boolean sign(String sessionID) {
		// 申明同步队列
		Deque<Long> queue = null;
		// 当前时间
		Long t0 = System.currentTimeMillis();
		
		// 使用同步的方式访问请求会话队列映射Map，如果该session没有对应的队列，则创建一个新的同步队列
		// 并且关联到Map中。
		synchronized (requestQueueMap) {
			// 根据会话ID，从会话Map中获取同步访问队列
			queue = requestQueueMap.get(sessionID);
			// 如果该会话没有关联的同步访问队列
			if(null == queue){
				// 创建一个新的同步访问队列，并且关联到会话Map中
				queue = new LinkedList<Long>();
				requestQueueMap.put(sessionID, queue);
			}
		}
		Long t1 = null;
		// 队首的时间
		synchronized (queue) {
			queue.addFirst(t0);
			while(queue.size() > (this.limitNum - 1)){
				t1 = queue.pollLast();
			}
		}
		// 进行时间校验
		return (t0 - (t1 == null?0L:t1) > this.limitTime);
	}

	/**
	 * 清除制定sessionID的请求时间缓存
	 * @param sessionID
	 */
	public void clear(String sessionID){
		synchronized (requestQueueMap) {
			requestQueueMap.remove(sessionID);
		}
	}
}
