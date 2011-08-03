package com.huawei.imp.framework.model.ibus;

import com.huawei.imp.framework.model.ibus.exception.IbusException;
import com.huawei.imp.framework.model.ibus.xmlparser.XMLParser;

/**
 * Ibus远程调用接口
 * @author ahli
 * @version IMPV100R001DA0, May 7, 2009
 * @since CMS IMPV100R001DA0
 */
public interface IbusRemoteInvoker
{
	/**
	 * 远程调用，返回对象包装
	 * @param ibusServ	Ibus服务名称
	 * @param node		传入的数据对象
	 * @return
	 * @throws IbusException 
	 */
	IbusResponse postData(String ibusServ, XMLParser xmlParser) throws IbusException;
	
	/**
	 * 远程调用，返回对象包装
	 * @param ibusServ	Ibus服务名称
	 * @param node		传入的数据对象
	 * @param procID	流水号
	 * @return
	 * @throws IbusException 
	 */
	IbusResponse postData(String ibusServ, XMLParser xmlParser, String procID) throws IbusException;
}
