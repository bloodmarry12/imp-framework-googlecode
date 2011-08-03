/**
 * 
 */
package com.huawei.imp.framework.model.fileupload.servlet.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.huawei.imp.framework.model.fileupload.servlet.ActionSupport;

/**
 * @author ahli
 *
 */
public class TestAction implements ActionSupport {

	/* (non-Javadoc)
	 * @see com.huawei.imp.framework.model.fileupload.servlet.ActionSupport#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public String doGet(HttpServletRequest request, HttpServletResponse response) {
		return "/WEB-INF/jsp/framework/jsp/model/fileupload/test";
		
	}

	/* (non-Javadoc)
	 * @see com.huawei.imp.framework.model.fileupload.servlet.ActionSupport#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public String doPost(HttpServletRequest request, HttpServletResponse response) {
		return "/WEB-INF/jsp/framework/jsp/model/fileupload/test";
	}
}
