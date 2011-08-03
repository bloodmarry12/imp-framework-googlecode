package com.huawei.imp.framework.model.ibus;

/**
 * IBUS请求响应结果
 * @author ahli
 */
public interface IbusResponse
{

	/**
	 * 获取返回码
	 * @return    返回码
	 */
	public String getRspCode();
	
	/**
	 * 获取返回描述
	 * @return    返回描述
	 */
	public String getRspDesc();
	
	/**
	 * 获取报文
	 * @return    原始报文
	 */
	public String getSource();
	
}
