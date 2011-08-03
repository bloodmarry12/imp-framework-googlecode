/**
 * 
 */
package com.huawei.imp.framework.model.ibus.xmlparser;

import org.dom4j.Element;

/**
 * XML解析器节点报文解析器,作为匿名类使用
 * @author ahli
 */
public interface XMLParser {

	/**
	 * 解析xml的svcCont节点内的信息
	 * @param svcCont    svcCont节点
	 * @return
	 */
	public void parseXML(Element svcCont);
	
}
