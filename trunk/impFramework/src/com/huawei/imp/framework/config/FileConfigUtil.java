package com.huawei.imp.framework.config;

import org.dom4j.Element;

/**
 * 系统文件配置工具
 * 用于系统启动的时候，加载配置信息。框架使用1+1框架配置方式。框架提供一个base配置文件，
 * 作为默认项。提供customer配置作为自定义配置项。base提供全部配置信息，customer仅仅在
 * 需要有别于默认值的时候进行配置。后期，框架升级，仅仅替换base文件。
 * @author ahli
 * @date 2009-8-12
 */
public class FileConfigUtil
{
	/**
	 * 框架使用配置文件名称
	 */
	protected final static String BASE_CONFIG_FILE = "base.config.xml";
	
	/**
	 * 系统使用配置文件名称
	 */
	protected final static String CUSTOMER_CONFIG_FILE = "customer.config.xml";
	
	/**
	 * 框架基本配置文件对象
	 */
	private static BaseConfigFile baseConfig = new BaseConfigFile(BASE_CONFIG_FILE);
	
	/**
	 * 自定义配置文件对象
	 */
	private static BaseConfigFile customerConfig = new BaseConfigFile(CUSTOMER_CONFIG_FILE);
	
	 /**
     * 
     * 方法描述：获取xml的节点值 </p>
     * 调用规则：要想获取节点name1的值可以通过 </br>
     *         getText("system.ota.name1") </br>
     *         getText("root\\stu1\\name1")</br>
     *         getText("root/stu1/name1")
     * @param nodeName
     *            节点名称
     * @return String 节点值
     */
    public static String getText(String name)
    {
    	final String str = customerConfig.getText(name);
    	if(null != str){
    		return str;
    	}else{
    		return baseConfig.getText(name);
    	}
    }

    /**
     * 方法描述：获取xml的节点值
     * @param name
     *            节点名称
     * @return String 节点值
     */
    public static String getString(String name)
    {
    	final String str = customerConfig.getString(name);
    	if(null != str){
    		return str;
    	}else{
    		return baseConfig.getString(name);
    	}
    }

    /**
     * 方法描述：获取xml的节点值,去掉空格
     * @param name
     *            节点名称
     * @return String 节点值
     */
    public static String getStringTrim(String name)
    {
        return getString(name).trim();
    }

    /**
     * 方法描述：获取节点text值，必须是int值
     * @param name
     *            节点名称
     * @return int 节点值
     */
    public static int getInt(String name)
    {
    	return Integer.parseInt(getString(name));
    }

    /**
     * 方法描述：获取节点text值，配置参数必须是float
     * @param name
     *            节点名称
     * @return float 节点值
     */
    public static float getFloat(String name)
    {
    	return Float.parseFloat(getString(name));
    }

    /**
     * 方法描述：获取节点text值，配置参数必须是double型
     * @param name
     *            节点名称
     * @return double 节点值
     */
    public static double getDouble(String name)
    {
    	return Double.parseDouble(getString(name));
    }

    /**
     * 方法描述：获取节点text值，配置参数必须是byte型
     * @param name
     *            节点名称
     * @return byte 节点值
     */
    public static byte getByte(String name)
    {
       return Byte.parseByte(getString(name));
    }

    /**
     * 方法描述：获取节点text值，配置参数必须是short型
     * @param name
     *            节点名称
     * @return short 节点值
     */
    public static short getShort(String name)
    {
    	return Short.parseShort(getString(name));
    }

    /**
     * 方法描述：获取节点text值，配置参数必须是long型
     * @param name
     *            节点名称
     * @return long 节点值
     */
    public static long getLong(String name)
    {
    	return Long.parseLong(getString(name));
    }

    /**
     * 方法描述：获取节点text值，配置参数必须是boolean型
     * @param name
     *            节点名称
     * @return boolean 节点值
     */
	public static boolean getBoolean(String name) {
		return Boolean.parseBoolean(getString(name));
	}
	
	public static Element getElement(String name){
		if(null != customerConfig.getElement(name)){
			return customerConfig.getElement(name);
		}else{
			return baseConfig.getElement(name);
		}
	}
}
