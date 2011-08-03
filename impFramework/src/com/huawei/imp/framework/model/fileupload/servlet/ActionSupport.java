/**
 * 
 */
package com.huawei.imp.framework.model.fileupload.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author ahli
 *
 */
public interface ActionSupport {

	String doGet(HttpServletRequest request, HttpServletResponse response) throws IOException;
	
	String doPost(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
