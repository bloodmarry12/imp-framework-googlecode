package com.huawei.imp.framework.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * 输入流与字符串转换类
 * 提供静态函数，用于输入流与字符串之间的转换
 * @author ahli
 * @version IMPV100R001DA0, 2009-12-10 
 * @since CMS IMPV100R001DA0
 */
public class StreamToString {

	/**
	 * 私有构造函数，避免该工具类被实例化
	 */
	private StreamToString(){}
	
	/**
	 * 将输入流转换为字符串
	 * 该方法需要慎用，输入流来子
	 * @param is    输入流
	 * @return
	 */
	public static String convertStreamToString(InputStream is) {    
        /*   
         * To convert the InputStream to String we use the BufferedReader.readLine()   
         * method. We iterate until the BufferedReader return null which means   
         * there's no more data to read. Each line will appended to a StringBuilder   
         * and returned as String.   
         */   
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));    
        StringBuilder sb = new StringBuilder();    
     
        String line = null;    
        try {    
        	int index = 0;
            while ((line = reader.readLine()) != null) {    
            	if(index++ > 0 ){
            		sb.append(System.getProperty("line.separator"));
            	}
                sb.append(line);
            }    
        } catch (IOException e) {    
            e.printStackTrace();    
        } finally {    
            try {    
                is.close();    
            } catch (IOException e) {    
                e.printStackTrace();    
            }    
        }    
     
        return sb.toString();    
    }    


}
