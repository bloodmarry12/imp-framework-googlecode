package com.huawei.imp.framework.model.ibus;

import org.dom4j.Element;
import org.junit.Test;

import com.huawei.imp.framework.model.ibus.exception.IbusException;
import com.huawei.imp.framework.model.ibus.impl.IbusRemoteInvokerImpl;
import com.huawei.imp.framework.model.ibus.xmlparser.XMLParser;
import com.huawei.imp.framework.test.AbstractFrameworkTests;

public class IbusRemoteInvokerTestCase extends AbstractFrameworkTests{

	IbusRemoteInvoker inboker = IbusRemoteInvokerImpl.getIbusRemoteInvoker();
	
	@Test
	public void testPostData() throws IbusException{
		
		IbusResponse res = inboker.postData("test1", new XMLParser(){
			@Override
			public void parseXML(Element svcCont) {
				Element ele_root = svcCont.addElement("RbtDeployNotifyReq");
				ele_root.addElement("BizCode").setText("000047510243");
				ele_root.addElement("ProvinceID").setText("04");
				ele_root.addElement("EventType").setText("1");
				ele_root.addElement("TransactionID").setText("000047510243");
				ele_root.addElement("ReqType").setText("6");
			}
		});
		
		System.out.println(res.getRspCode() + ":" + res.getRspDesc());
	}
}
