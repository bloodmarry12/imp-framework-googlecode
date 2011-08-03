package com.huawei.imp.framework.utils;

import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import net.sf.cglib.beans.BeanMap;

import org.apache.log4j.Logger;

/**
 * bean属性工具类。
 * <功能描述/>
 * @author ahli
 * @date 2009-8-6
 */
public class BeanProperUtil implements Constant
{
	
	// 日志对象
	private final static Logger log = Logger.getLogger(BeanProperUtil.class);
	
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
			sb.append(BeanProperUtil.LINE_SEPARATOR);
			sb.append("target = ");
			sb.append(BeanProperUtil.getBeanDesc(target));
			sb.append(BeanProperUtil.LINE_SEPARATOR);
			sb.append("source = ");
			sb.append(BeanProperUtil.getBeanDesc(source));
			sb.append(BeanProperUtil.LINE_SEPARATOR);
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
	 * 为一个已有的Bean设置属性
	 * 
	 * @param t
	 *            需要设置的Bean
	 * @param properties
	 *            属性名列表，要和values参数长度一致
	 * @param values
	 *            对应属性的值列表
	 * @return 设置属性后的Bean
	 */
	@SuppressWarnings("unchecked")
	public static <T> T setProperties(T t, String[] properties, Object[] values)
	{
		BeanMap beanMap = null;
		try
		{
			beanMap = BeanMap.create(t);
			for (int i = 0; i < properties.length; i++)
			{
				beanMap.put(properties[i], values[i]);
			}
		}
		catch (Exception e)
		{
			log.error("setProperties error", e);
		}
		return (T) beanMap.getBean();
	}
	
	/**
	 * 根据指定类型创建bean，并且根据属性和map对bean赋值；
	 * @param <T>           泛型类型
	 * @param clazz         类型
	 * @param beanMap       对象映射
	 * @param tarProperties    目标属性
	 * @param srcProperties    源属性
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public static <T> T createBean(Class<T> clazz, Map<String, Object> srcMap, 
			String[] tarProperties, 
			String[] srcProperties) throws InstantiationException, IllegalAccessException{
		T t;
		t = (T) clazz.newInstance();
		BeanMap beanMap = BeanMap.create(t);
		
		// 如果传入了参数属性，则按照参数进行对象赋值
		if(null != tarProperties && tarProperties.length > 0 && null != srcProperties && srcProperties.length > 0){
			int index = 0;
			for(String tarProperty : tarProperties){
				// 如果对象的属性包含.，则递归get访问，
				if(tarProperty.contains(".")){
					String[] _tarProperties = tarProperty.split("\\.");
					int _index = 0;
					Object _obj = t;
					do{
						_obj = BeanMap.create(_obj).get(_tarProperties[_index ++]);
					}while(_index < (_tarProperties.length -1));
					
					BeanMap.create(_obj).put(_tarProperties[_index], srcMap.get(srcProperties[index ++]));
					
				}else{
					Object val = srcMap.get(srcProperties[index ++]);
					beanMap.put(tarProperty, val);
				}
			}
		}
		return t;
	}
}