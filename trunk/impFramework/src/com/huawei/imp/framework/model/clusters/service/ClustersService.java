/**
 * 
 */
package com.huawei.imp.framework.model.clusters.service;

import java.util.List;

import com.huawei.imp.framework.model.clusters.domain.SlaveDevice;

/**
 * 集群配置业务接口
 * 用于多机集群内存刷新业务
 * @author ahli   
 */
public interface ClustersService {

	/**
	 * 主从设备配置类型
	 */
	static String TYPE = "imp.framework.clusters.type";
	
	/**
	 * 主设备 
	 */
	static String TYPE_VAL_MASTER = "1";
	
	/**
	 * 从属设备
	 */
	static String TYPE_VAL_SLAVE = "0";
	
	/**
	 * 主机地址
	 */
	static String MASTER_URL = "imp.framework.clusters.master.url";
	
	/**
	 * HTTP请求地址头
	 */
	static String PREURL = "http://";
	
	/**
	 * 分隔符
	 */
	static String SEPARATOR = ":";
	
	/**
	 * 远程调用地址后缀
	 */
	static String PATH_CLUSTERS_MASTER = "/remoting/MasterRemoteServiceExporter";
	
	static String PATH_CLUSTERS_SLAVE = "/remoting/SlaveRemoteServiceExporter";
	
	/**
	 * 刷新从属设备
	 * @param type    业务类型
	 */
	void flushSlaveDevice(String type);
	
	/**
	 * 广播的形式，刷新从属服务器的内存配置,
	 * @param url         从属服务器url
	 * @param deviceID    从属服务器deviceID
	 * @param type        刷新业务类型
	 */
	void flushSlaveDevice(String url, String deviceID, String type);
	
	/**
	 * 刷新指定从属服务器的内存配置
	 * @param url     从属服务器url
	 * @param type    刷新业务类型
	 */
	void flushSlaveDevice(String url, String type);
	
	/**
	 * 远程注册
	 */
	void registRemote();
	
	/**
	 * 注册集群支持接口
	 * @param type    集群操作类型
	 * @param clustersSupport    集群处理接口
	 */
	void registClustersSupport(String type, ClustersSupport clustersSupport);
	
	/**
	 * 查询所有已经注册的从属设备
	 * @return
	 */
	List<SlaveDevice> queryForAllSlaveDevice();
	
	/**从更新列表中删除服务器
	 * @param url
	 */
	void deleteSlaveDevice(String url);
	
	/**
	 * 添加从属设备
	 * 想本机的从属服务器列表中添加一个新的从属服务器，在添加的时候，验证服务器状态。
	 * @param device
	 */
	void addSlaveDevice(SlaveDevice device);
	
	/**
	 * 检查从属设备状态
	 * @param url
	 */
	void checkSlaveDevice(String url);
}
