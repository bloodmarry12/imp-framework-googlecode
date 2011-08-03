/**
 * 
 */
package com.huawei.imp.framework.model.fileupload.servlet.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.huawei.imp.framework.model.fileupload.service.factory.FileUploadServiceFactory;
import com.huawei.imp.framework.model.fileupload.servlet.ActionSupport;

/**
 * @author ahli
 *
 */
public class CancelAction implements ActionSupport {

	/* (non-Javadoc)
	 * @see com.huawei.imp.framework.model.fileupload.servlet.ActionSupport#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public String doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/json; charset=UTF-8");
		String uuid = request.getParameter("uuid");
		FileUploadServiceFactory.getFileUploadService().cancelProcess(uuid);
		JSONObject jobj = new JSONObject();
		jobj.put("result", "1");
		jobj.put("message", "0");
		PrintWriter pw = response.getWriter();
		pw.append(jobj.toString());
		return null;
	}

	/* (non-Javadoc)
	 * @see com.huawei.imp.framework.model.fileupload.servlet.ActionSupport#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public String doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		return doGet(request, response);
	}
}
