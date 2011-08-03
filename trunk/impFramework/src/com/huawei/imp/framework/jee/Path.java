package com.huawei.imp.framework.jee;

import java.io.File;
import java.net.URL;
import java.security.CodeSource;
import java.security.ProtectionDomain;

/**
 * 系统路径工具类
 * 提供静态方法，获取类基本路径，应用服务web根目录，或者是jar所在路径。
 * @author ahli
 */
public class Path
{
    /**
     * 方法描述：获取web应用的路径绝对路径
     * @return String 绝对路径
     */
    public static String getWebRootPath()
    {
        return getWebRootPath(Path.class);
    }

    /**
     * 方法描述：获取conf的绝对路径
     * @return String 获取conf的绝对路径
     */
    public static String getConfPath()
    {

        String filePath = getWebRootPath() + File.separator + "WEB-INF" + File.separator + "conf" + File.separator;
        File file = new File(filePath);

        if (!file.exists())
        {
            if(!file.mkdir())
            {
            	return null;
            }
        }

        return filePath;
    }

    /**
     * 方法描述：获取classes文件的绝对路径
     * @return String 绝对路径
     */
    public static String getClassesPath()
    {
    	return Thread.currentThread().getContextClassLoader().getResource("/").getPath().replaceAll("%20", " ");
//        ProtectionDomain pd = Path.class.getProtectionDomain();
//
//        if (pd == null)
//        {
//            return null;
//        }
//
//        CodeSource cs = pd.getCodeSource();
//
//        if (cs == null)
//        {
//            return null;
//        }
//        URL url = cs.getLocation();
//
//        if (url == null)
//        {
//            return null;
//        }
//
//        return url.getPath();
    }

    /**
     * 方法描述：根据class获取web应用的路径绝对路径(class可以在jar中)
     * @param c    用来定位的类
     * @return String 绝对路径
     */
    @SuppressWarnings("unchecked")
	private static String getWebRootPath(Class c)
    {
        ProtectionDomain pd = c.getProtectionDomain();

        if (pd == null)
        {
            return null;
        }
        CodeSource cs = pd.getCodeSource();

        if (cs == null)
        {
            return null;
        }

        URL url = cs.getLocation();

        if (url == null)
        {
            return null;
        }

        String strUrl = null;
        
        if(url.getPath().endsWith("jar")){
        	strUrl = url.getPath().substring(0, url.getPath().indexOf("lib"));
        }else{
        	strUrl = url.getPath().substring(0, url.getPath().indexOf("classes"));
        }
        
        strUrl = url.getPath().substring(0, url.getPath().indexOf("WEB-INF"));

        return strUrl;

    }
}