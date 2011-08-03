package com.huawei.imp.framework.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.dom4j.tree.DefaultAttribute;

/**
 * <一句话功能/>
 * <功能描述/>
 * @author ahli
 * @date 2009-8-12
 */
public class FileXmlTool 
{
    /**
     * 文件对象
     */
    private File file;

    /**
     * 文档对象
     */
    private Document doc;

    /**
     * 元素对象
     */
    private Element root;

    /**
     * SAX解析类对象
     */
    private SAXReader reader = new SAXReader();

    /**
     * 构造函数
     * 
     * @param xmlName
     *            xml文件名称
     */
    public FileXmlTool(String xmlName)
    {
        file = new File(xmlName);

        try
        {
            if (xmlName == null || "".equals(xmlName.trim()))
            {
                throw new Exception("file name invalid");
            }

            if (!file.exists())
            {
                throw new Exception("file directory is not exists");
            }

            if (!file.isFile())
            {
                throw new Exception("...file is not file...");
            }

            if (!"xml".equals(xmlName.substring(xmlName.lastIndexOf(".") + 1,
                    xmlName.length())))
            {
                throw new Exception("...not xml file...");
            }
        }
        catch (Exception e)
        {}

        try
        {
            doc =  reader.read(file);

            root = doc.getRootElement();

        }
        catch (DocumentException e)
        {
        	e.printStackTrace();
        }
    }

    /**
     * 构造函数
     * 
     * @param xmlPath
     * @param xmlName
     */
    public FileXmlTool(String xmlPath, String xmlName)
    {
        file = new File(xmlPath);
        try
        {

            if (xmlName == null || "".equals(xmlName.trim()))
            {
                throw new Exception("file name invalid");
            }

            if (!file.exists())
            {
                throw new Exception("file directory is not exists");
            }

            if (!file.isFile())
            {
                throw new Exception("...file is not file...");
            }

            if (!"xml".equals(xmlName.substring(xmlName.lastIndexOf(".") + 1,
                    xmlName.length())))
            {
                throw new Exception("...not xml file...");
            }
        }
        catch (Exception e)
        {
        	e.printStackTrace();
        }

        try
        {
            doc = reader.read(file);
            root = doc.getRootElement();
        }
        catch (DocumentException e)
        {
        	e.printStackTrace();
        }
    }

    public void addNode(String parentNodeName, String nodeName, String nodeValue)
    {

        Element element = getElementByString(parentNodeName);
        element.addElement(nodeName);
        Element el = FileXmlTool.getChildNodeByName(element, nodeName);
        el.addText(nodeValue);
        XMLWriter xmlWriter = null;
        try
        {
        	xmlWriter = new XMLWriter();
            xmlWriter.write(doc);
        }
        catch (UnsupportedEncodingException e)
        {}
        catch (FileNotFoundException e)
        {}
        catch (IOException e)
        {}
        finally
        {
        	if (null != xmlWriter)
        	{
        		try
				{
					xmlWriter.close();
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
        	}
        }
    }

    /**
     * 获取根节点对象
     * 
     * @param name
     * @return
     */
    public Element getRoot(String name)
    {
        return getElementByString(name);
    }

    /**
     * 获取指定的节点的指定属性值
     * 
     * 如果xml文件格式如下：
     * 
     * <root> <stu1> <name1 shortname="dog">zhangsan<name1> <address1>ddd</address1>
     * </stu1> <stu2> <name2 shortname="pig">lisi<name2> <address2>abc</address2>
     * </stu2> </root> 调用规则：
     * 
     * 要想获取节点属性值通过下面三种方式来获取
     * 
     * getContent("root.stu1.name1") getContent("root\\stu1\\name1")
     * getContent("root/stu1/name1")
     * 
     * @param nodeName
     *            节点名称，如：root.stu1.name
     * @param attrName
     *            属性名称，如：shortname
     * @return
     */
    public String getNodeAttrValue(String nodeName, String attrName)
    {
        return getNodeAttr(getElementByString(nodeName), attrName);
    }

    /**
     * 获取属性名称的列表
     * 
     * @param nodeName
     * @return
     */
    @SuppressWarnings("unchecked")
	public String[] getNodeAttrNameList(String nodeName)
    {
        Element start = getElementByString(nodeName);

        if (start == null)
        {
            return null;
        }

        String temp[] = new String[start.attributeCount()];
        int j = 0;
        DefaultAttribute defaultAttribute = null;

        for (Iterator i = start.attributeIterator(); i.hasNext();)
        {
            defaultAttribute = (DefaultAttribute) i.next();
            temp[j] = defaultAttribute.getName();
            j++;
        }

        return temp;
    }

    /**
     * 该方法可以获取节点下的所有名称和所有属性值，名称作为key,值为value 如xml格式为<dd3 abc="333"
     * def="123">abcd</dd3>
     * 
     * @param nodeName
     * @return
     */
    @SuppressWarnings("unchecked")
	public Map getNodeAttrValues(String nodeName)
    {
        Map map = new HashMap();
        Element start = getElementByString(nodeName);

        if (start == null)
        {
            return map;
        }

        DefaultAttribute defaultAttribute = null;

        for (Iterator i = start.attributeIterator(); i.hasNext();)
        {
            defaultAttribute = (DefaultAttribute) i.next();
            map.put(defaultAttribute.getName(), defaultAttribute.getText());
        }

        return map;
    }

    /**
     * 根据xml配置的摸版，替换参数，获取字符串，如：
     * 
     * 在配置文件中获取sql，xml文件格式如下：
     * 
     * <system> <sql> <module1> <queryUser>select * from user where userid = {0}
     * </module1> </sql> </system>
     * 
     * @param nodeName
     * @param params
     * @return
     */
    public String getValueByParam(String nodeName, Object[] params)
    {
        Element start = getElementByString(nodeName);

        if (start == null)
        {
            return null;
        }

        String str = start.getText();
       
        for (int i = 0; i < params.length; i++)
        {
            if (params[i] instanceof String)
            {
                str = str.replaceAll("\\{" + i + "\\}", "'"
                        + params[i].toString() + "'");
            }
            
            str = str.replaceAll("\\{" + i + "\\}", params[i].toString());

        }

        return str;

    }

    /**
     * 获取xml的节点值
     * 
     * 如果xml文件格式如下：
     * 
     * <root> <stu1> <name1 shortname="dog">zhangsan<name1> <address1>ddd</address1>
     * </stu1> <stu2> <name2 shortname="pig">lisi<name2> <address2>abc</address2>
     * </stu2> </root> 调用规则：
     * 
     * 要想获取节点name1的值可以通过 getContent("root.stu1.name1")
     * getContent("root\\stu1\\name1") getContent("root/stu1/name1")三种来获取
     * 
     * 目前只支持通过.的方式来获取
     * 
     * @param nodeName
     * @return
     */
    public String getNodeText(String nodeName)
    {
        Element tmpElement = getElementByString(nodeName);

        if (tmpElement == null)
        {
            return null;
        }

        return tmpElement.getText();
    }

    /**
     * 通过父节点和子节点名称查询子节点
     * 
     * @param start
     * @param childNodeName
     * @return
     */
    @SuppressWarnings("unchecked")
	private static Element getChildNodeByName(Element start,
            String childNodeName)
    {
        if (start == null || childNodeName == null || "".equals(childNodeName))
        {
            return null;
        }

        Element child = null;

        if (start.getNodeType() == Element.ELEMENT_NODE)
        {
            for (Iterator i = start.elementIterator(); i.hasNext();)
            {
                child = (Element) i.next();

                if (childNodeName.equals(child.getName()))
                {
                    return child;
                }
            }
        }
        return child;
    }

    /**
     * 根据分隔符来获取字符数组
     * 
     * @param str
     * @return
     */
    private static String[] getStringBySeparator(String str)
    {
        if (str == null)
        {
            return null;
        }

        if (str.indexOf(".") != -1)
        {
            return str.split("\\.");
        }

        if (str.indexOf("\\") != -1)
        {
            return str.split("\\\\");
        }

        if (str.indexOf("/") != -1)
        {
            return str.split("/");
        }

        return null;

    }

    /**
     * 根据字符串获取最底层的Element对象
     * 
     * @param elementName
     * @return
     */
    private Element getElementByString(String nodeName)
    {
        if (nodeName == null || "".equals(nodeName.trim()))
        {
            return null;
        }

        String tempNodeName = nodeName;
        String str[] = getStringBySeparator(tempNodeName);

        if (str == null)
        {
            if (root.getName().equals(nodeName))
            {
                // Log.system.debug("log.global.endMethod");
                return root;
            }

            return null;
        }

        Element node = root;
        for (int i = 1; i < str.length; i++)
        {
            node = getChildNodeByName(node, str[i]);

            if (node == null)
            {
                return null;
            }
        }

        return node;
    }

    /**
     * 根据节点属性名称获取属性的值
     * 
     * @param start
     * @param attrName
     * @return
     */
    @SuppressWarnings("unchecked")
	private String getNodeAttr(Element start, String attrName)
    {
        if (start == null || attrName == null || "".equals(attrName))
        {

            return null;
        }

        DefaultAttribute child = null;

        if (start.getNodeType() == Element.ELEMENT_NODE)
        {
            for (Iterator i = start.attributeIterator(); i.hasNext();)
            {
                child = (DefaultAttribute) i.next();

                if (attrName.equals(child.getName()))
                {
                    // Log.system.debug("log.global.endMethod");
                    return child.getText();
                }
            }
        }

        return null;
    }

    @SuppressWarnings("unchecked")
	public Iterator getChildNodeList(String start)
    {
        if (start == null)
        {
            return null;
        }

        Element tmpElement = getElementByString(start);

        return tmpElement.elementIterator();
    }


    public Document getDoc()
    {
        return doc;
    }

}
