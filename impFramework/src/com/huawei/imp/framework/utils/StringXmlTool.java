/* 
 * 文件名：StringXmlTool.java
 * 描述：处理XML字符串的工具类
 * 作者：刘超
 * 日期：Jun 4, 2008
 */
package com.huawei.imp.framework.utils;


/**
 * 类名：StringXmlTool 描述：处理XML字符串的工具类
 */
public class StringXmlTool
{
	/**
	 * 左方括号
	 */
	private static final String LEFT_BRACKETS = "<";

	/**
	 * 右方括号
	 */
	private static final String RIGHT_BRACKETS = ">";

	/**
	 * 正斜杠
	 */
	private static final String ENDTAG = "/";

	/**
	 * 描述：获取节点的值
	 * 
	 * @param String
	 *            xmlsrc 请求的XML字符串
	 * @param String
	 *            nodeName 节点名
	 * @return String nodeValue 节点的值
	 */
	public static String getNodeValue(String xmlsrc, String nodeName)
	{
		String nodeValue = "";
		if (xmlsrc != null)
		{
			String beginNode = LEFT_BRACKETS + nodeName + RIGHT_BRACKETS;
			String endNode = LEFT_BRACKETS + ENDTAG + nodeName + RIGHT_BRACKETS;
			int beginIndex = xmlsrc.indexOf(beginNode) + beginNode.length();
			int endIndex = xmlsrc.indexOf(endNode);
			if (beginIndex >= beginNode.length() && endIndex > 0)
			{
				nodeValue = xmlsrc.substring(beginIndex, endIndex);
			}
		}
		return nodeValue;
	}

	/**
	 * 描述：获取节点值数组
	 * 
	 * @param String
	 *            xmlsrc 请求的XML字符串
	 * @param String
	 *            nodeName 节点名
	 * @return String[] nodeValues 节点值数组
	 */
	public static String[] getNodeValues(String xmlsrc, String nodeName)
	{
		String[] nodeValues = null;
		if (xmlsrc != null)
		{
			String beginNode = LEFT_BRACKETS + nodeName + RIGHT_BRACKETS;
			String endNode = LEFT_BRACKETS + ENDTAG + nodeName + RIGHT_BRACKETS;
			String[] array = xmlsrc.split(endNode);
			nodeValues = new String[array.length - 1];
			for (int i = 0; i < array.length - 1; i++)
			{
				nodeValues[i] = array[i].substring(array[i]
						.lastIndexOf(beginNode)
						+ beginNode.length());
			}
		}
		return nodeValues;
	}
}