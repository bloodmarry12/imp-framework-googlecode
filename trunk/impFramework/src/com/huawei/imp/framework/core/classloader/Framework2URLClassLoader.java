package com.huawei.imp.framework.core.classloader;

import java.net.URLClassLoader;

/**
 * 支持自定义类的ClassLoader
 * 继承自java.net.URLClassLoader
 * @author ahli
 * @version 1.0, 2010-6-23
 * @see java.net.URLClassLoader
 * @see com.huawei.imp.framework.core.classloader.DefineAbleClassLoader
 * @since 1.0
 */
public class Framework2URLClassLoader extends URLClassLoader implements DefineAbleClassLoader {

	/**
	 * 默认构造函数
	 * @param parent    父类加载器
	 */
	public Framework2URLClassLoader(URLClassLoader parent) {
		super(parent.getURLs(), parent);
	}

	/* (non-Javadoc)
	 * @see com.huawei.imp.framework.core.classloader.DefineAbleClassLoader#defineClass(java.lang.String, byte[])
	 */
	@SuppressWarnings("unchecked")
	public Class defineClass(String name, byte[] b) {
		return defineClass(name, b, 0, b.length);
	}
}
