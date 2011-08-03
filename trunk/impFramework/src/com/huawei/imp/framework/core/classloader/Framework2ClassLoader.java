package com.huawei.imp.framework.core.classloader;

/**
 * 支持自定义类的ClassLoader
 * 继承自java.lang.ClassLoader
 * @author ahli
 * @version 1.0, 2010-6-23
 * @see java.lang.ClassLoader
 * @see com.huawei.imp.framework.core.classloader.DefineAbleClassLoader
 * @since 1.0
 */
public class Framework2ClassLoader extends ClassLoader implements DefineAbleClassLoader {

	/**
	 * @param parent
	 */
	public Framework2ClassLoader(ClassLoader parent) {
		super(parent);
	}

	/* (non-Javadoc)
	 * @see com.huawei.imp.framework.core.classloader.DefineAbleClassLoader#defineClass(java.lang.String, byte[])
	 */
	@SuppressWarnings("unchecked")
	public Class defineClass(String name, byte[] b) {
		return defineClass(name, b, 0, b.length);
	}
}
