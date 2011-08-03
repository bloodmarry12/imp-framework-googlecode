package com.huawei.imp.framework.constant.dao.handler;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Element;
import org.objectweb.asm.Opcodes;

import com.huawei.imp.framework.core.classloader.DefineAbleClassLoader;
import com.huawei.imp.framework.jee.Path;
import com.huawei.imp.framework.logger.LogFactory;
import com.huawei.imp.framework.logger.Logger;
import com.huawei.imp.framework.utils.FileXmlTool;

/**
 * <p><strong>动态DAO实现类工厂</strong></p>
 * <p></p>
 * @see
 * @version v1.0
 * @since v2.0
 * @author ahli (edit in 2010-6-2)
 */
public class DynamicConstantTypeHandlerFactory implements Opcodes{

	private static final Logger log = LogFactory.getLogger(DynamicConstantTypeHandlerFactory.class);
	
	/**
	 * 基于框架的类加载器
	 */
	private DefineAbleClassLoader framework2ClassLoader;
	private FileXmlTool configFile = new FileXmlTool(Path.getClassesPath() + "/IbaticDAOImplGeneratorConfig.xml");
	
	/**
	 * 接口列表
	 */
	private List<String> constantModleIDs = new ArrayList<String>();
	
	/**
	 * 默认构造函数
	 * @param defineAbleClassLoader    支持自定义的Classloader实例
	 */
	@SuppressWarnings("unchecked")
	public DynamicConstantTypeHandlerFactory(DefineAbleClassLoader defineAbleClassLoader){
		super();
		log.info("加载Ibatis动态常量TypeHandler字节码生成工厂...");
		this.framework2ClassLoader = defineAbleClassLoader;
		//
		Iterator<Element> it = configFile.getChildNodeList("config.define-constants-typehandler");
		if(null != it){
			while(it.hasNext()){
				Element ele = it.next();
				constantModleIDs.add(ele.getText());
				log.info("auot generate bytecode ->" + ele.getText());
			}
		}
		try {
			createDynamicClass();
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.toString());
		}
	}
	
	/**
	 * 创建实现类，并且在Classloader中定义这些类
	 * @param interfaceNameSrc
	 * @throws Exception 
	 * @throws ClassNotFoundException 
	 */
	public void createDynamicClass() throws Exception{
		for(String modelID : constantModleIDs){
			byte[] classByte = new ConstantValueTypeHandlerClassWriter(ConstantValueTypeValueHandler.class.getName() + "$" + modelID, ConstantValueTypeValueHandler.class.getName(), modelID).createClassByteArray();
			byte[] classByte2 = new ConstantValueTypeHandlerClassWriter(ConstantValueTypeNameHandler.class.getName() + "$" + modelID, ConstantValueTypeNameHandler.class.getName(), modelID).createClassByteArray();
			framework2ClassLoader.defineClass(ConstantValueTypeValueHandler.class.getName() + "$" + modelID, classByte);
			framework2ClassLoader.defineClass(ConstantValueTypeNameHandler.class.getName() + "$" + modelID, classByte2);
			System.out.println(ConstantValueTypeValueHandler.class.getName() + "$" + modelID);
			System.out.println(ConstantValueTypeNameHandler.class.getName() + "$" + modelID);
		}
	}

	public List<String> getDaoInterfaces() {
		return constantModleIDs;
	}
}
