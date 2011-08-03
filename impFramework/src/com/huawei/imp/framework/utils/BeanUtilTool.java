package com.huawei.imp.framework.utils;

import java.util.Set;
import java.util.Map.Entry;

import net.sf.cglib.beans.BeanMap;

import org.apache.log4j.Logger;


public class BeanUtilTool
{

	// 日志对象
	private final static Logger log = Logger.getLogger(BeanUtilTool.class);
	/**
	 * 换行符
	 */
	static final String LINE_SEPARATOR = System.getProperty("line.separator");
	
	/**
	 * 4个空格符
	 */
	static String BLANK = "    ";
	
	/**
	 * 默认的StringBuffer容量
	 */
	static int DEFAULT_SB_CAP = 500;
	
	
	/**
	 * 基本数据类型int
	 */
	static String CLASS_int = "int";
	
	/**
	 * 基本数据类型long
	 */
	static String CLASS_long = "long";
	
	/**
	 * 数组分割符
	 */
	static String ARRAYS_SEPARATOR = ",";
	
	/**
	 * 空字符串对象
	 */
	private static final String[] STRINGSARRAYS_EMPTY = new String[]{};
//	/**
//     * 将源对象的属性值拷贝给目标对象的同名属性
//     * @param dest 拷贝的目标对象
//     * @param String[] 需要拷贝的属性名数组
//     * @param src 拷贝的源对象
//     * @exception CMSException
//     */
//	public static void copyProperties(Object dest, String[] props, Object src)
//			
//	{
//		if (null == dest || null == props || null == src)
//		{
//			return;
//		}
//
//		for (int i = 0; i < props.length; i++)
//		{
//			try
//		    {
//				String key = props[i];
//				Object value = BeanUtils.getProperty(src, key);
//				BeanUtils.copyProperty(dest, key, value);
//			}
//			catch (Exception e)
//			{
//				System.out.println(e.toString());
//				if(log.isDebugEnabled()){
//					log.info("PROPS:"+props[i]+" ERROR!");
//					log.debug(e.toString());
//				}
//			}
//				
//		}
//
//	}
	
	/**
	 * 拷贝bean的属性值
	 * @param target        拷贝目标对象
	 * @param source        拷贝源对象
	 */
	public static void copyProperties(Object target, Object source){
		copyProperties(target, source, null);
	}
	
	/**
	 * 拷贝bean的属性值
	 * @param target        拷贝目标对象
	 * @param source        拷贝源对象
	 * @param properties    要拷贝的属性名称
	 */
	@SuppressWarnings("unchecked")
	public static void copyProperties(Object target, Object source, String[] properties)
	{
		if(log.isDebugEnabled()){
			StringBuffer sb = new StringBuffer(500);
			sb.append(BeanUtilTool.LINE_SEPARATOR);
			sb.append("target = ");
			sb.append(BeanProperUtil.getBeanDesc(target));
			sb.append(BeanUtilTool.LINE_SEPARATOR);
			sb.append("source = ");
			sb.append(BeanUtilTool.getBeanDesc(source));
			sb.append(BeanUtilTool.LINE_SEPARATOR);
			sb.append("properties = ");
//			sb.append(BeanProperUtil.getBeanDesc(properties));
			log.debug(sb.toString());
		}
		// 目标对象映射
		BeanMap targetBeanMap = BeanMap.create(target);
		
		// 源对象映射
		BeanMap srcBeanMap = BeanMap.create(source);
		
		// 获取源的属性键值对集合
		Set<Entry> propertySet = srcBeanMap.entrySet();
		Object property = null;
		
		// 迭代属性键值对
		for(Entry entry : propertySet){
			property = entry.getKey();
			
			// 如果指定了属性拷贝，则根据属性名称进行过滤并且拷贝。
			// 否则就对比两个对象，只要是相同的属性，就拷贝。
			if(null != properties && properties.length > 0){
				for(String propertyName : properties){
					if(property.equals(propertyName) && targetBeanMap.containsKey(property)){
						targetBeanMap.put(property, entry.getValue());
						break;
					}
				}
			}else{
				if(targetBeanMap.containsKey(property)){
					targetBeanMap.put(property, entry.getValue());
				}
			}
		}
	}

	/**
	 * 获取bean的描述信息，按格式打印类型名称与各属性。
	 * @param obj    bean对象
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String getBeanDesc(Object obj)
	{
		if(null == obj){
			return null;
		}
		final Class clazz = obj.getClass();
		StringBuffer bf = new StringBuffer(DEFAULT_SB_CAP);
		bf.append(LINE_SEPARATOR);
		bf.append("{");
		bf.append(LINE_SEPARATOR);
		bf.append(BLANK);
		bf.append("Class=");
		bf.append(clazz.getName());
		bf.append(LINE_SEPARATOR);
		
		BeanMap beanMap = BeanMap.create(obj);
		Set entrySet = beanMap.entrySet();
		for (java.util.Iterator it = entrySet.iterator(); it.hasNext();)
		{
			java.util.Map.Entry entry = (Entry) it.next();
			bf.append(BLANK).append(entry.getKey()).append(":");
			bf.append(entry.getValue()).append(LINE_SEPARATOR);
		}
		
		bf.append("}");
		return bf.toString();
	}
	
	/**
	 * 获取bean的所有属性名称，如果传入null，则返回new String[]{}
	 * add by ahli 2010-10-21
	 * @param obj    对象
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String[] getProperties(Object obj){
		if(null == obj){
			return STRINGSARRAYS_EMPTY;
		}else{
			BeanMap beanMap = BeanMap.create(obj);
			Set propertiesSet = beanMap.keySet();
			String[] retArray = new String[propertiesSet.size()];
			int i = 0;
			for(Object key : propertiesSet){
				retArray[i++] = key.toString();
			}
			return retArray;
		}
	}
}
