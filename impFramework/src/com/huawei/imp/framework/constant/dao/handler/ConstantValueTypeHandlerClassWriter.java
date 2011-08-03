/*
 * description: 
 * date:        上午12:47:43
 * author:      ahli
 */
package com.huawei.imp.framework.constant.dao.handler;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.LdcInsnNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.VarInsnNode;


/**
 * <p><strong></strong></p>
 * <p></p>
 * @see
 * @version v1.0
 * @since v2.0
 * @author ahli (edit in 2010-6-7)
 */
public class ConstantValueTypeHandlerClassWriter implements Opcodes{

	/**
	 * 
	 */
	private String modelID = null;
	
	/**
	 * 类节点
	 */
	private ClassNode classNode = new ClassNode();
	
	/**
	 * 接口名称路径，以/分割包和类
	 */
	private String parentClassName = null;
	
	/**
	 * 
	 */
	private String targetClassName = null;
	
	/**
	 * 默认构造函数
	 * @param targetClassName
	 * @param parentClassName
	 * @param modelID
	 * @param defineAbleClassLoader
	 */
	public ConstantValueTypeHandlerClassWriter(String targetClassName, String parentClassName, String modelID){
		// 调用默认无参构造函数
		this.modelID = modelID;
		this.targetClassName = targetClassName.replaceAll("\\.", "/");;
		this.parentClassName = parentClassName.replaceAll("\\.", "/");;
	}
	
	public byte[] createClassByteArray() throws Exception{
		createClass();
		ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
		this.classNode.accept(cw);
		return cw.toByteArray();
	}
	
	public void createClass() throws Exception{
		createClassInfo();
		// 创建构造函数
		createMethodConstruct();
	}

	/**
	 * 
	 */
	private void createClassInfo() {
		this.classNode.version = V1_6;
		this.classNode.access = ACC_PUBLIC;
		this.classNode.name = targetClassName;
		this.classNode.superName = parentClassName;
	}
	
	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	private void createMethodConstruct() {
		MethodNode mn_construct = new MethodNode(ACC_PUBLIC, "<init>", "()V", null, null);
		InsnList li = mn_construct.instructions;
		li.add(new VarInsnNode(ALOAD, 0));
		li.add(new LdcInsnNode(modelID));
		li.add(new MethodInsnNode(INVOKESPECIAL, parentClassName, "<init>", "(Ljava/lang/String;)V"));
		li.add(new InsnNode(RETURN));
		mn_construct.maxLocals = 1;
		mn_construct.maxStack = 2;
		this.classNode.methods.add(mn_construct);
	}
}
