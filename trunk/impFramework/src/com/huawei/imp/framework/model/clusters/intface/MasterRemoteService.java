package com.huawei.imp.framework.model.clusters.intface;

/**
 * 主设备远程接口
 * @author ahli
 *
 */
public interface MasterRemoteService {

	/**
	 * 注册接口，用于从属设备的状态维持
	 * @param type    类型
	 * @param url     从属设备地址
	 */
	void registSlaveDevice(String url);
}
