package com.huawei.imp.framework.core.classloader;

/**
 * 扩展支持动态定义类的ClassLoader接口
 * @author ahli
 * @version 1.0, 2010-6-23
 */
public interface DefineAbleClassLoader {
	
	/**
	 * 在运行环境中定义类
	 * @param name
	 * @param b
	 * @return
	 */
	Class<?> defineClass(String name, byte[] b);
	
	/**
	 * 加载类
	 * @param name
	 * @return
	 * @throws ClassNotFoundException
	 */
	Class<?> loadClass(String name) throws ClassNotFoundException;
}
