/**
 * 
 */
package com.huawei.imp.framework.model.clusters.intface;

/**
 * @author ahli
 *
 */
public interface SlaveRemoteService {

	
	/**
	 * 从属设备的状态检查
	 * @return
	 */
	void check();
	
	/**
	 * 指定刷新操作,
	 * 在广播方式通知所有服务器，服务器根据参数判断是否需要
	 * @param url          被刷新目标机器地址
	 * @param deviceID     被刷新目标机器设备ID
	 * @param actionKey    被刷新操作标识
	 */
	void flush(String url, String deviceID, String actionKey);
}
