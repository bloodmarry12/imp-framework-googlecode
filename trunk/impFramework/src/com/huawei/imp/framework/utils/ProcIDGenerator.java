package com.huawei.imp.framework.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Random;

import org.apache.commons.lang.StringUtils;


/**
 * 业务流水号创建器
 * @author ahli
 * @version IMPV100R001DA0, Jun 15, 2009 
 * @since CMS IMPV100R001DA0
 */
public class ProcIDGenerator
{

//	private static String XML_DEVICETYPE = ConfigFileUtil.getConfigFile().getString("configeration/CMSDeviceCode");
	private static String XML_DEVICETYPE = "4";
	private ProcIDGenerator(){}
	
	public static String generateProcID()
	{
		InetAddress addr;
		String DEVICE_TYPE = XML_DEVICETYPE;
		String ip = "000";
		Random r = new Random();
		String randomInt = StringUtils.leftPad(r.nextInt(9999) + "", 4, "0");
		try
		{
			addr = InetAddress.getLocalHost();
			ip = addr.getHostAddress();
			int i = ip.lastIndexOf(".");
			if (i > 0)
			{
				ip = StringUtils.leftPad(ip.substring(i + 1, ip.length()), 3,
						"0");
			}
		}
		catch (UnknownHostException e)
		{
			e.printStackTrace();
		}
		String procID = DEVICE_TYPE
				+ DateUtil.getCurDateStr("yyyyMMddHHmmssSSS") + randomInt + ip;
		return procID;
	};
}
