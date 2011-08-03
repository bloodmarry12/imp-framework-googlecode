/**
 * 
 */
package com.huawei.imp.framework.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;

import com.caucho.hessian.io.HessianInput;
import com.caucho.hessian.io.HessianOutput;

/**
 * <p>
 * 序列化工具类，提供基于序列化的深度克隆、以及获取对象占用的内存大小。
 * </p>
 * @author aohai.li
 * @version CMSV100R001DB0SP04, 2010-8-16
 * @since CMSV100R001DB0SP04
 */
public class SeriUtils {

	/**
	 * 
	 * @param <T>
	 * @param src
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static final <T> T byteClone(T src) {
		// 如果对象是null，则克隆出来还是null。
		if(null == src){
			return src;
		}

		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream out = new ObjectOutputStream(baos);
			out.writeObject(src);
			out.close();
			ByteArrayInputStream bin = new ByteArrayInputStream(baos
					.toByteArray());
			ObjectInputStream in = new ObjectInputStream(bin);
			Object clone = in.readObject();
			in.close();
			return ((T) clone);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		} catch (StreamCorruptedException e) {
			throw new RuntimeException(e.toString());
		} catch (IOException e) {
			throw new RuntimeException(e.toString());
		}
	}

	/**
	 * 获取对象大小
	 * 
	 * @param obj
	 *            对象
	 * @return
	 */
	public static int getByteSize(Object obj) {
		if (null == obj) {
			return 0;
		} else {
			try {
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				ObjectOutputStream out = new ObjectOutputStream(baos);
				out.writeObject(obj);
				out.close();
				return baos.size();
			} catch (StreamCorruptedException e) {
				throw new RuntimeException(e.toString());
			} catch (IOException e) {
				throw new RuntimeException(e.toString());
			}
		}
	}
	
	/**
	 * 使用hession协议进行对象深度克隆
	 * @param <T>
	 * @param src
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T hessionClone(T src){
		if(null == src){
			return null;
		}
		
		try {
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			HessianOutput out = new HessianOutput(os);
			out.writeObject(src);
			InputStream is = new ByteArrayInputStream(os.toByteArray());
			HessianInput in = new HessianInput(is);
			Object obj = in.readObject(null);
			is.close();
			return (T)obj;
		} catch (IOException e) {
			throw new RuntimeException(e.toString());
		}
	}
}
