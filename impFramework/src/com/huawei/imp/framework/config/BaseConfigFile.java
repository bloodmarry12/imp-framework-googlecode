package com.huawei.imp.framework.config;

import org.dom4j.Document;
import org.dom4j.Element;

import com.huawei.imp.framework.jee.Path;
import com.huawei.imp.framework.utils.FileXmlTool;

/**
 * 系统配置文件类 文件路径为webroot/WEB-INF/conf/cms.config.xml
 * 
 * @author USER
 * @version 
 * @date 2009-4-25
 */

public class BaseConfigFile
{
    /** 
     * cms.config.xml的文件路径 
     */
    private String filePath;

    /** 
     * xml文件帮助类
     */
    private FileXmlTool xmlHelper;
    
    /**
     * 单态 私有构造函数
     *
     */
    BaseConfigFile(String fileName)
    {
    	filePath = Path.getConfPath() + fileName;
    	xmlHelper = new FileXmlTool(filePath);
    }

    /**
     * 方法描述：更新xml类，将xml文件再次更新到缓存里面
     */
    public void updateConfigXml()
    {
        xmlHelper = new FileXmlTool(filePath);
    }


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
    public String getText(String name)
    {
        return xmlHelper.getNodeText(name);
    }

    /**
     * 方法描述：获取xml的节点值
     * @param name
     *            节点名称
     * @return String 节点值
     */
    public String getString(String name)
    {
        return xmlHelper.getNodeText(name);
    }

    /**
     * 方法描述：获取xml的节点值,去掉空格
     * @param name
     *            节点名称
     * @return String 节点值
     */
    public String getStringTrim(String name)
    {
        return getString(name).trim();
    }

    /**
     * 方法描述：获取节点text值，必须是int值
     * @param name
     *            节点名称
     * @return int 节点值
     */
    public int getInt(String name)
    {
        return Integer.parseInt(getString(name));
    }

    /**
     * 方法描述：获取节点text值，配置参数必须是float
     * @param name
     *            节点名称
     * @return float 节点值
     */
    public float getFloat(String name)
    {
        return Float.parseFloat(getString(name));
    }

    /**
     * 方法描述：获取节点text值，配置参数必须是double型
     * @param name
     *            节点名称
     * @return double 节点值
     */
    public double getDouble(String name)
    {
        return Double.parseDouble(getString(name));
    }

    /**
     * 方法描述：获取节点text值，配置参数必须是byte型
     * @param name
     *            节点名称
     * @return byte 节点值
     */
    public byte getByte(String name)
    {
        return Byte.parseByte(getString(name));
    }

    /**
     * 方法描述：获取节点text值，配置参数必须是short型
     * @param name
     *            节点名称
     * @return short 节点值
     */
    public short getShort(String name)
    {
        return Short.parseShort(getString(name));
    }

    /**
     * 方法描述：获取节点text值，配置参数必须是long型
     * @param name
     *            节点名称
     * @return long 节点值
     */
    public long getLong(String name)
    {
        return Long.parseLong(getString(name));
    }

    /**
     * 方法描述：获取节点text值，配置参数必须是boolean型
     * @param name
     *            节点名称
     * @return boolean 节点值
     */
    public boolean getBoolean(String name)
    {
        return Boolean.parseBoolean(getString(name));
    }

    /**
     * 方法描述：获取节点对象
     * @param name
     *            节点名称
     * @return Object 节点对象
     */
    public Object getRootNode(String name)
    {
        return xmlHelper.getRoot(name);
    }

    /**
     * 方法描述：获取document对象
     * @return Object document对象
     */
    public Document getDocument()
    {
        return xmlHelper.getDoc();
    }

    /**
     * 方法描述：根据参数返回字符串
     * @param name
     *            节点名称
     * @param object
     *            对象数组
     * @return String 根据参数返回字符串
     */
    public String getTextByParam(String name, Object[] object)
    {
        return xmlHelper.getValueByParam(name, object);
    }
    

    /**
     * 方法描述：获取文件路径
     * @return String 文件路径
     */
    public String getFilePath()
    {
        return filePath;
    }

    /**
     * config方法描述：设置config.xml的文件路径
     * @param filePath
     *            文件路径
     */
    public void setFilePath(String filePath)
    {
        this.filePath = filePath;
    }
    
    public Element getElement(String path){
    	return xmlHelper.getRoot(path);
    }
}
