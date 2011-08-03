/**
 * 
 */
package com.huawei.imp.framework.jee.webserver.config.impl;

import org.junit.Test;

import com.huawei.imp.framework.jee.webserver.config.ApplicationServerXML;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author ahli
 * @version IMPV100R001DA0, 2010-4-7
 * @see [相关类/方法]
 * @since CMS IMPV100R001DA0
 */
public class GlassFishXMLTestCase
{
	@Test
	public void test(){
		ApplicationServerXML appServerXML = new GlassFishXML("D:/workspace7(ME8)/imp4Framework/test/domain.xml");
		String contextRoot = appServerXML.getContextRoot();
		String port = appServerXML.getPort();
		System.out.println(contextRoot);
		System.out.println(port);
	}
}
